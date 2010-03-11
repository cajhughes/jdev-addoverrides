package com.cajhughes.jdev.override;

import com.cajhughes.jdev.override.controller.OverrideDynamicMenuListener;
import com.cajhughes.jdev.override.controller.OverrideEditorListener;
import com.cajhughes.jdev.override.view.OverrideKeyStrokeContext;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import oracle.ide.Addin;
import oracle.ide.Ide;
import oracle.ide.controller.IdeAction;
import oracle.ide.editor.EditorManager;
import oracle.ide.keyboard.KeyStrokes;

public final class OverrideAddin implements Addin {
    private static final KeyStroke overrideShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK);

    @Override
    public void initialize() {
        // Add an EditorListener, so that we can add our action to the editor toolbar
        EditorManager.getEditorManager().addEditorListener(new OverrideEditorListener());

        // Add a DynamicMenuListener, so that we can add our action into the code menu
        Ide.getMainWindow().addDynamicMenuListener(new OverrideDynamicMenuListener());

        // Define the default shortcut key as Alt-O
        OverrideKeyStrokeContext trimContext = new OverrideKeyStrokeContext();
        trimContext.add(IdeAction.find(OverrideCommand.actionId()), new KeyStrokes(overrideShortcut));
        Ide.getKeyStrokeContextRegistry().addContext(trimContext);
    }
}
