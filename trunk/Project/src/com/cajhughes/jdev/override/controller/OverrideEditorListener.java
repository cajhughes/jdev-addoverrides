package com.cajhughes.jdev.override.controller;

import com.cajhughes.jdev.override.OverrideCommand;
import com.cajhughes.jdev.override.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.controller.IdeAction;
import oracle.ide.controls.Toolbar;
import oracle.ide.editor.Editor;
import oracle.ide.editor.EditorListener;

public final class OverrideEditorListener implements EditorListener {

    public void editorOpened(Editor editor) {
        Context context = editor.getContext();
        if (NodeUtil.isJavaSourceNode(context)) {
            Toolbar toolbar = editor.getToolbar();
            toolbar.add(IdeAction.find(OverrideCommand.actionId()));
        }
    }

    public void editorActivated(Editor editor) {
    }

    public void editorDeactivated(Editor editor) {
    }

    public void editorClosed(Editor editor) {
    }
}
