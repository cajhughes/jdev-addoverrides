package com.cajhughes.jdev.override.view;

import com.cajhughes.jdev.override.OverrideCommand;
import oracle.ide.Context;
import oracle.ide.controller.ContextMenu;
import oracle.ide.controller.ContextMenuListener;
import oracle.ide.controller.IdeAction;

/**
 * This class implements the ContextMenuListener interface, and exists to add
 * the AddOverrides action to the context menu within the CodeEditor.
 *
 * @author Chris Hughes
 */
public class OverrideMenuListener implements ContextMenuListener {
    @Override
    public void menuWillShow(final ContextMenu contextMenu) {
        contextMenu.add(contextMenu.createMenuItem(IdeAction.find(OverrideCommand.actionId()),
                                                   OverrideCommand.WEIGHT_ADD_OVERRIDES));
    }

    @Override
    public void menuWillHide(ContextMenu contextMenu) {
    }

    @Override
    public boolean handleDefaultAction(Context context) {
        return false;
    }
}
