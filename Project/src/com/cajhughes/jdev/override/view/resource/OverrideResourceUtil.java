package com.cajhughes.jdev.override.view.resource;

import java.util.ResourceBundle;

public final class OverrideResourceUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("com.cajhughes.jdev.override.Resource");

    public static String getString(final String key) {
        return bundle.getString(key);
    }
}
