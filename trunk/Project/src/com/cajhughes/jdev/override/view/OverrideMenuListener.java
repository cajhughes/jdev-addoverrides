package com.cajhughes.jdev.override.view;

import com.cajhughes.jdev.override.OverrideCommand;
import oracle.ide.Context;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.controller.IdeAction;

public class OverrideMenuListener implements ContextMenuListener {
    @Override
    public void menuWillShow(final ContextMenu contextMenu) {
        contextMenu.add(contextMenu.createMenuItem(IdeAction.find(OverrideCommand.actionId())));
    }

    @Override
    public void menuWillHide(ContextMenu contextMenu) {
    }

    @Override
    public boolean handleDefaultAction(Context context) {
        return false;
    }
}
