/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.groovy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.GroovyFunction;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author Romain Bioteau
 */
public class GroovyDocumentUtil {

    private static List<String> defaultMethodsNames;

    public static List<String> getDeclaredMethodsName(GroovyCompilationUnit unit) {

        List<IMethod> methods = getModuleMethods(unit);
        List<String> methodNames = new ArrayList<>();

        for (IMethod m : methods) {
            methodNames.add(m.getElementName());
        }
        return methodNames;

    }

    @SuppressWarnings("restriction")
    public static List<IMethod> getModuleMethods(GroovyCompilationUnit unit) {
        List<IMethod> methods = new ArrayList<>();

        try {
            IType[] types = unit.getAllTypes();
            for (IType t : types) {
                for (IMethod m : t.getMethods()) {
                    if (!m.isBinary() && !m.isMainMethod() && !m.isConstructor() && !m.getElementName().equals("run")) {
                        methods.add(m);
                    }
                }
            }

        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
        }

        return methods;

    }

    public static VariableScope getVariableScope(GroovyCompilationUnit unit) {
        if (unit != null) {
            CompletionNodeFinder finder = new CompletionNodeFinder(0, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
            ContentAssistContext assistContext = finder.findContentAssistContext(unit);

            org.codehaus.groovy.ast.ASTNode astNode = null;
            if (assistContext != null) {
                astNode = assistContext.containingCodeBlock;
            }

            if (astNode instanceof BlockStatement) {
                return ((BlockStatement) astNode).getVariableScope();
            } else if (astNode instanceof ClassNode && ((ClassNode) astNode).isScript()) {
                // use scope of the run method
                ClassNode clazz = (ClassNode) astNode;
                MethodNode method = clazz.getMethod("run", new Parameter[0]); //$NON-NLS-1$
                if (method != null && method.getCode() instanceof BlockStatement) {
                    return ((BlockStatement) method.getCode()).getVariableScope();
                }
            } else if (astNode instanceof ImportNode) {
                int i = 0;
                while (astNode instanceof ImportNode) {
                    finder = new CompletionNodeFinder(i, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
                    assistContext = finder.findContentAssistContext(unit);
                    astNode = assistContext.containingCodeBlock;
                    i++;
                }
                if (astNode instanceof ClassNode && ((ClassNode) astNode).isScript()) {
                    // use scope of the run method
                    ClassNode clazz = (ClassNode) astNode;
                    MethodNode method = clazz.getMethod("run", new Parameter[0]); //$NON-NLS-1$
                    if (method != null && method.getCode() instanceof BlockStatement) {
                        return ((BlockStatement) method.getCode()).getVariableScope();
                    }
                } else if (astNode instanceof BlockStatement) {
                    return ((BlockStatement) astNode).getVariableScope();
                }
            }
        }
        return null;

    }

    public static void addToVariableScope(GroovyCompilationUnit unit, List<FieldNode> nodes) {
        VariableScope scope = getVariableScope(unit);

        while (scope != null) {
            for (FieldNode f : nodes) {
                scope.putDeclaredVariable(f);
            }
            scope = scope.getParent();
        }
    }

    public static void refreshUserLibrary(Repository repository) {
        try {
            FunctionsRepositoryFactory.getUserFunctionCatgory().removeAllFunctions();
            GroovyRepositoryStore store = repository.getRepositoryStore(GroovyRepositoryStore.class);
            IJavaProject javaProject = repository.getJavaProject();
            for (IRepositoryFileStore artifact : store.getChildren()) {
                IType type = javaProject.findType(artifact.getDisplayName());
                if (type != null) {
                    for (IMethod m : type.getMethods()) {
                        if (m.getFlags() == (Flags.AccStatic | Flags.AccPublic)) {
                            FunctionsRepositoryFactory.getUserFunctionCatgory()
                                    .addFunction(new GroovyFunction(m, FunctionsRepositoryFactory.getUserFunctionCatgory()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            GroovyPlugin.logError(e);
        }
    }

    public static synchronized List<String> getDefaultMethodsName() {
        if (defaultMethodsNames == null) {
            defaultMethodsNames = new ArrayList<>();
            for (Method m : DefaultGroovyMethods.class.getDeclaredMethods()) {
                defaultMethodsNames.add(m.getName());
            }
        }
        return defaultMethodsNames;
    }

    public static List<String> getVariableName(List<FieldNode> processVariables) {
        List<String> result = new ArrayList<>();
        for (FieldNode f : processVariables) {
            result.add(f.getName());
        }
        return result;
    }

}
