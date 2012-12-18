package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.syntax.Types;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.groovy.search.GenericsMapper;
import org.eclipse.jdt.groovy.search.ITypeLookupExtension;
import org.eclipse.jdt.groovy.search.TypeLookupResult;
import org.eclipse.jdt.groovy.search.TypeLookupResult.TypeConfidence;
import org.eclipse.jdt.groovy.search.VariableScope;
import org.eclipse.jdt.internal.compiler.codegen.Opcodes;

public class BonitaConstantsTypeLookup implements ITypeLookupExtension {


	private GroovyCompilationUnit unit;

	public void initialize(GroovyCompilationUnit unit, VariableScope topLevelScope) {
		this.unit = unit;
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
