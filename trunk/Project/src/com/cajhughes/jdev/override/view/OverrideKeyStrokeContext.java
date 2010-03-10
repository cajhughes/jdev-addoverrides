package com.cajhughes.jdev.override.view;

import com.cajhughes.jdev.override.view.resource.OverrideResourceUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oracle.ide.controller.IdeAction;
import oracle.ide.keyboard.KeyStrokeContext;
import oracle.ide.keyboard.KeyStrokeMap;
import oracle.ide.keyboard.KeyStrokes;

public final class OverrideKeyStrokeContext implements KeyStrokeContext {
    private final Set<IdeAction> actions = new HashSet<IdeAction>();
    private final KeyStrokeMap keyStrokeMap = new KeyStrokeMap();

    public void add(final IdeAction action, final KeyStrokes keyStrokes) {
        actions.add(action);
        keyStrokeMap.put(keyStrokes, action.getCommandId());
    }

    @Override public String getAcceleratorFile() {
        return null;
    }

    @Override public Set getAllActions(final boolean global) {
        if (global) {
            return actions;
        }
        else {
            return Collections.emptySet();
        }
    }

    @Override public List getAllPresets() {
        return Collections.emptyList();
    }

    @Override public String getName() {
        return OverrideResourceUtil.getString("EXTENSION_NAME");
    }

    @Override public KeyStrokeMap getPresetKeyStrokeMap(final Object object, final boolean global) {
        if (global) {
            return keyStrokeMap;
        }
        else {
            return null;
        }
    }
}
