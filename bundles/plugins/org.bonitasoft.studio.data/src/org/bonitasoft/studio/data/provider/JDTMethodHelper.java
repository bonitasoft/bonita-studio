/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.data.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.corext.codemanipulation.GetterSetterUtil;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.corext.util.JdtFlags;
import org.eclipse.jdt.ui.JavaElementLabels;

public class JDTMethodHelper {

    public static String retrieveQualifiedType(final String typeErasure, final IType declaringType) {
        String qualifiedType = Object.class.getName();
        try {
            qualifiedType = JavaModelUtil.getResolvedTypeName(typeErasure, declaringType);
            qualifiedType = handlePrimitiveTypes(qualifiedType);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        } catch (final IllegalArgumentException e) {
            BonitaStudioLog.error(e);
        }
        return qualifiedType;
    }

    protected static String handlePrimitiveTypes(String qualifiedType) {
        if ("int".equals(qualifiedType)) {
            qualifiedType = Integer.class.getName();
        } else if ("boolean".equals(qualifiedType)) {
            qualifiedType = Boolean.class.getName();
        } else if ("long".equals(qualifiedType)) {
            qualifiedType = Long.class.getName();
        } else if ("float".equals(qualifiedType)) {
            qualifiedType = Float.class.getName();
        } else if ("double".equals(qualifiedType)) {
            qualifiedType = Double.class.getName();
        } else if ("short".equals(qualifiedType)) {
            qualifiedType = Short.class.getName();
        } else if ("byte".equals(qualifiedType)) {
            qualifiedType = Byte.class.getName();
        } else if ("E".equals(qualifiedType)) {
            qualifiedType = Object.class.getName();
        } else if ("V".equals(qualifiedType)) {
            qualifiedType = Object.class.getName();
        }
        return qualifiedType;
    }

    public static List<IMethod> allPublicMethodWithOneParameter(final IType type) throws JavaModelException {
        final List<IMethod> methods = new ArrayList<>();
        if (isGroovySourceType(type)) {
            methods.addAll(listPropertySetterMethods(type));
        }
        for (final IMethod method : type.getMethods()) {
            if (isValidSetterMethod(method, methods)) {
                methods.add(method);
            }
        }
        ITypeHierarchy typeHierarchy = type.newSupertypeHierarchy(Repository.NULL_PROGRESS_MONITOR);
        if (typeHierarchy != null) {
            Stream.of(typeHierarchy.getAllSuperclasses(type))
                    .filter(t -> !Object.class.getName().equals(t.getElementName()))
                    .flatMap(t -> {
                        try {
                            return Stream.of(t.getMethods());
                        } catch (JavaModelException e) {
                            return Stream.empty();
                        }
                    })
                    .filter(m -> isValidSetterMethod(m, methods))
                    .forEach(methods::add);
        }
        return methods;
    }

    private static boolean isValidSetterMethod(IMethod method, List<IMethod> methods) {
        try {
            return Flags.isPublic(method.getFlags())
                    && method.getParameterTypes().length == 1
                    && !method.isConstructor()
                    && doesNotContainSignature(method, methods);
        } catch (JavaModelException e) {
            return false;
        }
    }

    private static boolean isValidGetterMethod(final List<IMethod> methods, final IMethod method) {
        try {
            return Flags.isPublic(method.getFlags())
                    && method.getParameterTypes().length == 0
                    && !method.isConstructor()
                    && !method.getReturnType().equals("V")
                    && doesNotContainSignature(method, methods);
        } catch (JavaModelException e) {
            return false;
        }
    }

    private static boolean doesNotContainSignature(IMethod method, List<IMethod> methods) {
        return !methods.stream().map(m -> methodSignature(m)).collect(Collectors.toSet())
                .contains(methodSignature(method));
    }

    private static String methodSignature(IMethod m) {
        return JavaElementLabels.getElementLabel(m, JavaElementLabels.ALL_DEFAULT);
    }

    public static List<IMethod> allPublicMethodWithoutParameterReturningNonVoid(final IType type)
            throws JavaModelException {
        List<IMethod> methods = new ArrayList<>();
        if (isGroovySourceType(type)) {
            methods.addAll(listPropertyGetterMethods(type));
        }
        for (final IMethod method : type.getMethods()) {
            if (isValidGetterMethod(methods, method)) {
                methods.add(method);
            }
        }
        ITypeHierarchy typeHierarchy = type.newSupertypeHierarchy(Repository.NULL_PROGRESS_MONITOR);
        if (typeHierarchy != null) {
            Stream.of(typeHierarchy.getAllSuperclasses(type))
                    .filter(t -> !Object.class.getName().equals(t.getElementName()))
                    .flatMap(t -> {
                        try {
                            return Stream.of(t.getMethods());
                        } catch (JavaModelException e) {
                            return Stream.empty();
                        }
                    })
                    .filter(m -> isValidGetterMethod(methods, m))
                    .forEach(methods::add);
        }
        return methods;
    }

    private static List<IMethod> listPropertySetterMethods(final IType type) throws JavaModelException {
        return getAllDeclaredFields(type).stream()
                .filter(field -> isValidSetter(field))
                .map(field -> createSetterMethod(type, field))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static List<IField> getAllDeclaredFields(final IType type) throws JavaModelException {
        List<IField> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(type.getFields()));
        ITypeHierarchy typeHierarchy = type.newSupertypeHierarchy(Repository.NULL_PROGRESS_MONITOR);
        Stream.of(typeHierarchy.getAllSuperclasses(type))
                .filter(t -> !Object.class.getName().equals(t.getElementName()))
                .filter(t -> isGroovySourceType(t))
                .flatMap(t -> {
                    try {
                        return Stream.of(t.getFields());
                    } catch (JavaModelException e) {
                        return Stream.empty();
                    }
                })
                .forEach(fields::add);
        return fields;
    }

    private static IMethod createSetterMethod(final IType type, IField field) {
        try {
            return new GroovyFieldAccessorMethod(type, GetterSetterUtil.getSetterName(field, null), "V",
                    new String[] { field.getElementName() }, new String[] { field.getTypeSignature() });
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private static boolean isValidSetter(IField field) {
        try {
            return !JdtFlags.isEnum(field)
                    && !JdtFlags.isFinal(field)
                    && !JdtFlags.isStatic(field);
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

    private static boolean isValidGetter(IField field) {
        try {
            return !JdtFlags.isEnum(field)
                    && !JdtFlags.isStatic(field);
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

    public static boolean isGroovySourceType(final IType type) {
        return type instanceof SourceType && type.getCompilationUnit() instanceof GroovyCompilationUnit;
    }

    private static List<IMethod> listPropertyGetterMethods(IType type) throws JavaModelException {
        return getAllDeclaredFields(type).stream()
                .filter(field -> isValidGetter(field))
                .map(field -> createGetterMethod(type, field))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static IMethod createGetterMethod(final IType type, IField field) {
        try {
            return new GroovyFieldAccessorMethod(type, GetterSetterUtil.getGetterName(field, null),
                    field.getTypeSignature(), new String[] {}, new String[] {});
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }
}
