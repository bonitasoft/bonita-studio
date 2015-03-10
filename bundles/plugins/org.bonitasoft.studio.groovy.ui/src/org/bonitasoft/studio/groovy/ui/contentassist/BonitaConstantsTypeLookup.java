package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.Locale;

import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.engine.expression.ExpressionConstantsResolver;
import org.bonitasoft.forms.server.api.IFormExpressionsAPI;
import org.bonitasoft.forms.server.validator.AbstractFormValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.groovy.search.ITypeLookupExtension;
import org.eclipse.jdt.groovy.search.TypeLookupResult;
import org.eclipse.jdt.groovy.search.TypeLookupResult.TypeConfidence;
import org.eclipse.jdt.groovy.search.VariableScope;

public class BonitaConstantsTypeLookup implements ITypeLookupExtension {

    @Override
    public void initialize(final GroovyCompilationUnit unit, final VariableScope topLevelScope) {

    }

    @Override
    public TypeLookupResult lookupType(final Expression node, final VariableScope scope, final ClassNode objectExpressionType) {
        return lookupType(node, scope, objectExpressionType, false);
    }

    @Override
    public TypeLookupResult lookupType(final Expression node, final VariableScope scope, final ClassNode objectExpressionType,
            final boolean isStaticObjectExpression) {
        if (node instanceof VariableExpression) {
            final String name = ((VariableExpression) node).getName();
            final Class<?> typeForKeyWord = getTypeForKeyWord(name);
            if (typeForKeyWord != null) {
                final ClassNode classNode = new ClassNode(typeForKeyWord);
                return new TypeLookupResult(classNode, classNode, node, TypeConfidence.EXACT, scope);
            }

        }
        return null;
    }

    public static Class<?> getTypeForKeyWord(final String keyWord) {
        if (keyWord != null) {
            final ExpressionConstants expressionConstantsFromName = ExpressionConstantsResolver.getExpressionConstantsFromName(keyWord);
            if (expressionConstantsFromName != null) {
                try {
                    return Class.forName(expressionConstantsFromName.getReturnType());
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            } else if (keyWord.equals(IFormExpressionsAPI.USER_LOCALE)) {
                return Locale.class;
            } else if (keyWord.equals(AbstractFormValidator.CLICKED_BUTTON_VARNAME)) {
                return String.class;
            }
        }
        return null;
    }

    @Override
    public TypeLookupResult lookupType(final FieldNode node, final VariableScope scope) {
        return new TypeLookupResult(node.getType(), node.getDeclaringClass(), node, TypeConfidence.EXACT, scope);
    }

    @Override
    public TypeLookupResult lookupType(final MethodNode node, final VariableScope scope) {
        return new TypeLookupResult(node.getReturnType(), node.getDeclaringClass(), node, TypeConfidence.EXACT, scope);
    }

    @Override
    public TypeLookupResult lookupType(final AnnotationNode node, final VariableScope scope) {
        final ClassNode baseType = node.getClassNode();
        return new TypeLookupResult(baseType, baseType, baseType, TypeConfidence.EXACT, scope);
    }

    @Override
    public TypeLookupResult lookupType(final ImportNode node, final VariableScope scope) {
        return null;
    }

    /**
     * always return the passed in node
     */
    @Override
    public TypeLookupResult lookupType(final ClassNode node, final VariableScope scope) {
        return new TypeLookupResult(node, node, node, TypeConfidence.EXACT, scope);
    }

    @Override
    public TypeLookupResult lookupType(final Parameter node, final VariableScope scope) {
        return null;
    }

    @Override
    public void lookupInBlock(final BlockStatement arg0, final VariableScope arg1) {

    }

}
