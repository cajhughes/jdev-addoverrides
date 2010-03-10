package com.cajhughes.jdev.override.util;

import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.model.Node;
import oracle.javatools.parser.java.v2.model.JavaFile;
import oracle.javatools.parser.java.v2.model.SourceFile;
import oracle.jdeveloper.java.provider.ProjectFileProvider;
import oracle.jdeveloper.model.JavaSourceNode;

public final class NodeUtil {
    public static SourceFile getSourceFile(final JavaSourceNode node) {
        SourceFile sourceFile = null;
        if (node != null) {
            ProjectFileProvider provider = ProjectFileProvider.getInstance(Ide.getActiveProject());
            if (provider != null) {
                JavaFile javaFile = provider.getFile(node.getURL());
                if (javaFile != null && javaFile instanceof SourceFile) {
                    sourceFile = (SourceFile)javaFile;
                }
            }
        }
        return sourceFile;
    }

    public static boolean isJavaSourceNode(final Context context) {
        boolean result = false;
        if (context != null) {
            Node node = context.getNode();
            result = (node instanceof JavaSourceNode);
        }
        return result;
    }

    public static boolean isEditableJavaSourceNode(final Context context) {
        boolean result = false;
        if (isJavaSourceNode(context)) {
            result = !context.getNode().isReadOnly();
        }
        return result;
    }
}
