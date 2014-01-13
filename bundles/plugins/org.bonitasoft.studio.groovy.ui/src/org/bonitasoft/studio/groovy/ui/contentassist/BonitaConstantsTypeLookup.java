package org.bonitasoft.studio.groovy.ui.contentassist;

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

	public void initialize(GroovyCompilationUnit unit, VariableScope topLevelScope) {
		
	}

	public TypeLookupResult lookupType(Expression node, VariableScope scope, ClassNode objectExpressionType) {
		return lookupType(node, scope, objectExpressionType, false);
	}

	public TypeLookupResult lookupType(Expression node, VariableScope scope, ClassNode objectExpressionType,
			boolean isStaticObjectExpression) {
		if(node instanceof VariableExpression){
			String name = ((VariableExpression) node).getName();
			Class typeForKeyWord = BonitaSyntaxHighlighting.getTypeForKeyWord(name);
			if(typeForKeyWord != null){
				ClassNode classNode = new ClassNode(typeForKeyWord);
				return new TypeLookupResult(classNode, classNode, node, TypeConfidence.EXACT, scope);
			}
			
		}
		return null;
	}

	public TypeLookupResult lookupType(FieldNode node, VariableScope scope) {
		return new TypeLookupResult(node.getType(), node.getDeclaringClass(), node, TypeConfidence.EXACT, scope);
	}

	public TypeLookupResult lookupType(MethodNode node, VariableScope scope) {
		return new TypeLookupResult(node.getReturnType(), node.getDeclaringClass(), node, TypeConfidence.EXACT, scope);
	}

	public TypeLookupResult lookupType(AnnotationNode node, VariableScope scope) {
		ClassNode baseType = node.getClassNode();
		return new TypeLookupResult(baseType, baseType, baseType, TypeConfidence.EXACT, scope);
	}

	public TypeLookupResult lookupType(ImportNode node, VariableScope scope) {
		return null;
	}

	/**
	 * always return the passed in node
	 */
	public TypeLookupResult lookupType(ClassNode node, VariableScope scope) {
		return new TypeLookupResult(node, node, node, TypeConfidence.EXACT, scope);
	}

	public TypeLookupResult lookupType(Parameter node, VariableScope scope) {
		return null;
	}

	@Override
	public void lookupInBlock(BlockStatement arg0, VariableScope arg1) {
		
	}


}
