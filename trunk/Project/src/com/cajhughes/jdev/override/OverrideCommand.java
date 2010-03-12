package com.cajhughes.jdev.override;

import com.cajhughes.jdev.override.util.AnnotationUtil;
import com.cajhughes.jdev.override.util.NodeUtil;
import com.cajhughes.jdev.override.view.resource.OverrideResourceUtil;
import java.util.Collection;
import javax.swing.undo.UndoableEdit;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Command;
import oracle.ide.model.Node;
import oracle.javatools.buffer.TextBuffer;
import oracle.javatools.parser.java.v2.model.SourceClass;
import oracle.javatools.parser.java.v2.model.SourceFile;
import oracle.javatools.parser.java.v2.model.SourceMethod;

import oracle.jdeveloper.model.JavaSourceNode;

public class OverrideCommand extends Command {
    public static final float WEIGHT_ADD_OVERRIDES = 405.0f;

    private static final Node affectedNodes[] = { };
    private static final String EXTENSION_ID = "com.cajhughes.jdev.AddOverrides";
    private static final String EXTENSION_NAME = OverrideResourceUtil.getString("EXTENSION_NAME");
    private static final String OVERRIDE_ANNOTATION = "@Override ";
    private static final int ANNOTATION_LENGTH = OVERRIDE_ANNOTATION.length();

    private UndoableEdit undo;

    public OverrideCommand() {
        super(actionId(), NORMAL);
        undo = null;
    }

    public static int actionId() {
        Integer cmdId = Integer.valueOf(Ide.findOrCreateCmdID(EXTENSION_ID));
        if (cmdId == null) {
            throw new IllegalStateException("Action, " + EXTENSION_ID + ", not found.");
        }
        else {
            return cmdId.intValue();
        }
    }

    @Override
    public int doit() {
        Context context = getContext();
        if (context != null) {
            if (NodeUtil.isEditableJavaSourceNode(context)) {
                JavaSourceNode node = (JavaSourceNode)context.getNode();
                if (AnnotationUtil.containsUndeclaredOverrides(NodeUtil.getSourceFile(node))) {
                    try {
                        Ide.getWaitCursor().show();
                        TextBuffer buffer = node.acquireTextBuffer();
                        try {
                            buffer.writeLock();
                            if (addOverrides(node, buffer)) {
                                node.markDirty(true);
                            }
                        }
                        finally {
                            buffer.writeUnlock();
                        }
                    }
                    finally {
                        node.releaseTextBuffer();
                        Ide.getWaitCursor().hide();
                    }
                }
            }
        }
        return OK;
    }

    @Override
    public Node[] getAffectedNodes() {
        return affectedNodes;
    }

    @Override
    public String getName() {
        return EXTENSION_NAME;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int undo() {
        if (undo != null) {
            Context context = getContext();
            if (context != null) {
                if (NodeUtil.isEditableJavaSourceNode(context)) {
                    JavaSourceNode node = (JavaSourceNode)context.getNode();
                    try {
                        Ide.getWaitCursor().show();
                        TextBuffer buffer = node.acquireTextBuffer();
                        try {
                            buffer.writeLock();
                            undo.undo();
                        }
                        finally {
                            buffer.writeUnlock();
                        }
                    }
                    finally {
                        node.releaseTextBuffer();
                        Ide.getWaitCursor().hide();
                    }
                }
            }
        }
        return OK;
    }

    private boolean addOverrides(final JavaSourceNode node, final TextBuffer buffer) {
        int count = 0;
        SourceFile sourceFile = NodeUtil.getSourceFile(node);
        if (sourceFile != null) {
            @SuppressWarnings("unchecked")
            Collection<SourceClass> sourceClasses = sourceFile.getSourceClasses();
            buffer.beginEdit();
            for (SourceClass sourceClass : sourceClasses) {
                @SuppressWarnings("unchecked")
                Collection<SourceMethod> methods = sourceClass.getSourceMethods();
                for (SourceMethod method : methods) {
                    if (AnnotationUtil.overrides(method) && !AnnotationUtil.hasOverrideAnnotation(method)) {
                        buffer.insert(method.getStartOffset() + (count * ANNOTATION_LENGTH),
                                      OVERRIDE_ANNOTATION.toCharArray());
                        count++;
                    }
                }
            }
            undo = buffer.endEdit();
        }
        return (count > 0);
    }
}
