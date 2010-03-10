package com.cajhughes.jdev.override.util;

import oracle.ide.Context;
import oracle.ide.model.Node;
import oracle.jdeveloper.model.JavaSourceNode;

public final class NodeUtil {
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
