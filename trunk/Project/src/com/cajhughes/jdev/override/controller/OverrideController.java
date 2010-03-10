package com.cajhughes.jdev.override.controller;

import com.cajhughes.jdev.override.util.AnnotationUtil;
import com.cajhughes.jdev.override.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.javatools.parser.java.v2.model.JavaFile;
import oracle.javatools.parser.java.v2.model.SourceFile;
import oracle.jdeveloper.java.provider.ProjectFileProvider;
import oracle.jdeveloper.model.JavaSourceNode;

public class OverrideController implements Controller {
    @Override public boolean handleEvent(IdeAction ideAction, Context context) {
        return false;
    }

    @Override public boolean update(final IdeAction action, final Context context) {
        boolean enabled = false;
        if (context != null && NodeUtil.isEditableJavaSourceNode(context)) {
            JavaSourceNode node = (JavaSourceNode)context.getNode();
            ProjectFileProvider provider = ProjectFileProvider.getInstance(Ide.getActiveProject());
            if (provider != null) {
                JavaFile file = provider.getFile(node.getURL());
                if (file != null && file instanceof SourceFile) {
                    enabled = AnnotationUtil.containsUndeclaredOverrides((SourceFile)file);
                }
            }
        }
        action.setEnabled(enabled);
        return true;
    }
}
