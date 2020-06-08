package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.groovy.search.AbstractSimplifiedTypeLookup;
import org.eclipse.jdt.groovy.search.VariableScope;

import groovy.lang.GroovyClassLoader;

public class BonitaConstantsTypeLookup extends AbstractSimplifiedTypeLookup {

    public static List<ScriptVariable> bonitaVariables;
    private GroovyCompilationUnit unit;

    @Override
    protected TypeAndDeclaration lookupTypeAndDeclaration(final ClassNode declaringType, final String name,
            final VariableScope scope) {
        return bonitaVariables == null ? null : bonitaVariables.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .map(v -> {
                    GroovyClassLoader classLoader = unit.getModuleNode().getUnit().getClassLoader();
                    try {
                        ClassNode type = new ClassNode(classLoader.loadClass(v.getType()));
                        return new TypeAndDeclaration(type, null);
                    } catch (ClassNotFoundException e) {
                        BonitaStudioLog.error(e);
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public void initialize(final GroovyCompilationUnit unit, final VariableScope topScope) {
        this.unit = unit;
    }

    public static void setBonitaVariables(List<ScriptVariable> nodes) {
        bonitaVariables = nodes;
    }

}
