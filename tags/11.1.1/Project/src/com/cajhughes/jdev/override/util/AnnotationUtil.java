package com.cajhughes.jdev.override.util;

import java.util.Collection;
import oracle.javatools.parser.java.v2.model.JavaAnnotation;
import oracle.javatools.parser.java.v2.model.JavaMethod;
import oracle.javatools.parser.java.v2.model.SourceClass;
import oracle.javatools.parser.java.v2.model.SourceFile;

public final class AnnotationUtil {
    private static final String OVERRIDE = "Override";

    public static boolean containsUndeclaredOverrides(final SourceFile file) {
        boolean result = false;
        if (file != null) {
            @SuppressWarnings("unchecked")
            Collection<SourceClass> sourceClasses = file.getSourceClasses();
            for (SourceClass sourceClass : sourceClasses) {
                @SuppressWarnings("unchecked")
                Collection<JavaMethod> methods = sourceClass.getDeclaredMethods();
                for (JavaMethod method : methods) {
                    if (overrides(method) && !hasOverrideAnnotation(method)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static boolean hasOverrideAnnotation(final JavaMethod method) {
        boolean result = false;
        if (method != null) {
            @SuppressWarnings("unchecked")
            Collection<JavaAnnotation> annotations = method.getDeclaredAnnotations();
            for (JavaAnnotation annotation : annotations) {
                if (annotation.getAnnotationType().getName().equals(OVERRIDE)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean overrides(final JavaMethod method) {
        boolean result = false;
        if (method != null) {
            result = (method.getOverriddenMethods().size() > 0);
        }
        return result;
    }
}
