package com.cajhughes.jdev.override.view;

import com.cajhughes.jdev.override.OverrideCommand;
import com.cajhughes.jdev.override.util.NodeUtil;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.DynamicMenuListener;
import oracle.ide.controller.IdeAction;
import oracle.ide.controller.Menubar;

/**
 * This class implements the DynamicMenuListener interface, and exists so that
 * we can associate our action with a menu item in the dynamic, "Source", menu.
 *
 * @author Chris Hughes
 */
public final class OverrideDynamicMenuListener implements DynamicMenuListener {
    @Override
    public JComponent[] gatherDynamicActions(final Context context) {
        JComponent[] components = null;
        if (NodeUtil.isJavaSourceNode(context)) {
            Menubar menubar = Ide.getMenubar();
            components = new JComponent[1];
            JMenuItem override =
                menubar.createMenuItem(IdeAction.find(OverrideCommand.actionId()), OverrideCommand.WEIGHT_ADD_OVERRIDES);
            components[0] = override;
        }
        else {
            components = new JComponent[0];
        }
        return components;
    }
}
