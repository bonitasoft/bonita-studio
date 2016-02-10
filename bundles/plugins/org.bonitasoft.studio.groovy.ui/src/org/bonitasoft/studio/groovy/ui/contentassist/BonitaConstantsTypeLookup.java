package org.bonitasoft.studio.groovy.ui.contentassist;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.groovy.search.AbstractSimplifiedTypeLookup;
import org.eclipse.jdt.groovy.search.VariableScope;

public class BonitaConstantsTypeLookup extends AbstractSimplifiedTypeLookup {

    @Override
    protected TypeAndDeclaration lookupTypeAndDeclaration(final ClassNode declaringType, final String name, final VariableScope scope) {
        final org.codehaus.groovy.ast.VariableScope variableScope = scope.getEnclosingModuleNode().getStatementBlock().getVariableScope();
        final Variable variable = variableScope.getDeclaredVariable(name);
        if (variable != null) {
            return new TypeAndDeclaration(variable.getType(), variable.getOriginType());
        }
        return null;
    }

    @Override
    public void initialize(final GroovyCompilationUnit unit, final VariableScope topScope) {
    }

}
