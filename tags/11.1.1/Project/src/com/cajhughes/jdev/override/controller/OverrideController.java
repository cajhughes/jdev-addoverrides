package com.cajhughes.jdev.override.controller;

import com.cajhughes.jdev.override.util.AnnotationUtil;
import com.cajhughes.jdev.override.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.jdeveloper.model.JavaSourceNode;

public class OverrideController implements Controller {
    @Override
    public boolean handleEvent(IdeAction ideAction, Context context) {
        return false;
    }

    @Override
    public boolean update(final IdeAction action, final Context context) {
        boolean enabled = false;
        if (context != null && NodeUtil.isEditableJavaSourceNode(context)) {
            JavaSourceNode node = (JavaSourceNode)context.getNode();
            enabled = AnnotationUtil.containsUndeclaredOverrides(NodeUtil.getSourceFile(node));
        }
        action.setEnabled(enabled);
        return true;
    }
}
