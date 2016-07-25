package org.bonitasoft.studio.condition.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_Boolean;
import org.bonitasoft.studio.condition.conditionModel.Expression_Double;
import org.bonitasoft.studio.condition.conditionModel.Expression_Integer;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Expression_String;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Unary;
import org.bonitasoft.studio.condition.services.ConditionModelGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class ConditionModelSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ConditionModelGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ConditionModelPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ConditionModelPackage.EXPRESSION_BOOLEAN:
				if(context == grammarAccess.getExpression_BooleanRule() ||
				   context == grammarAccess.getExpression_TerminalRule()) {
					sequence_Expression_Boolean(context, (Expression_Boolean) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.EXPRESSION_DOUBLE:
				if(context == grammarAccess.getExpression_DoubleRule() ||
				   context == grammarAccess.getExpression_TerminalRule()) {
					sequence_Expression_Double(context, (Expression_Double) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.EXPRESSION_INTEGER:
				if(context == grammarAccess.getExpression_IntegerRule() ||
				   context == grammarAccess.getExpression_TerminalRule()) {
					sequence_Expression_Integer(context, (Expression_Integer) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.EXPRESSION_PROCESS_REF:
				if(context == grammarAccess.getExpression_ProcessRefRule() ||
				   context == grammarAccess.getExpression_TerminalRule()) {
					sequence_Expression_ProcessRef(context, (Expression_ProcessRef) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.EXPRESSION_STRING:
				if(context == grammarAccess.getExpression_StringRule() ||
				   context == grammarAccess.getExpression_TerminalRule()) {
					sequence_Expression_String(context, (Expression_String) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_COMPARE:
				if(context == grammarAccess.getOperation_CompareRule()) {
					sequence_Operation_Compare(context, (Operation_Compare) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_EQUALS:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_EqualsRule()) {
					sequence_Operation_Equals(context, (Operation_Equals) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_GREATER:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_GreaterRule()) {
					sequence_Operation_Greater(context, (Operation_Greater) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_GREATER_EQUALS:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_Greater_EqualsRule()) {
					sequence_Operation_Greater_Equals(context, (Operation_Greater_Equals) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_LESS:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_LessRule()) {
					sequence_Operation_Less(context, (Operation_Less) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_LESS_EQUALS:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_Less_EqualsRule()) {
					sequence_Operation_Less_Equals(context, (Operation_Less_Equals) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_NOT_UNARY:
				if(context == grammarAccess.getOperation_NotUnaryRule() ||
				   context == grammarAccess.getUnary_OperationRule()) {
					sequence_Operation_NotUnary(context, (Operation_NotUnary) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_NOT_EQUALS:
				if(context == grammarAccess.getOperationRule() ||
				   context == grammarAccess.getOperation_Not_EqualsRule()) {
					sequence_Operation_Not_Equals(context, (Operation_Not_Equals) semanticObject); 
					return; 
				}
				else break;
			case ConditionModelPackage.OPERATION_UNARY:
				if(context == grammarAccess.getOperation_UnaryRule() ||
				   context == grammarAccess.getUnary_OperationRule()) {
					sequence_Operation_Unary(context, (Operation_Unary) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     value=BOOLEAN
	 */
	protected void sequence_Expression_Boolean(EObject context, Expression_Boolean semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.EXPRESSION_BOOLEAN__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.EXPRESSION_BOOLEAN__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression_BooleanAccess().getValueBOOLEANTerminalRuleCall_1_0(), semanticObject.isValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=DOUBLE
	 */
	protected void sequence_Expression_Double(EObject context, Expression_Double semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.EXPRESSION_DOUBLE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.EXPRESSION_DOUBLE__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression_DoubleAccess().getValueDOUBLETerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=LONG
	 */
	protected void sequence_Expression_Integer(EObject context, Expression_Integer semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.EXPRESSION_INTEGER__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.EXPRESSION_INTEGER__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression_IntegerAccess().getValueLONGTerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=[EObject|ID]
	 */
	protected void sequence_Expression_ProcessRef(EObject context, Expression_ProcessRef semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression_ProcessRefAccess().getValueEObjectIDTerminalRuleCall_1_0_1(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_Expression_String(EObject context, Expression_String semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.EXPRESSION_STRING__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.EXPRESSION_STRING__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression_StringAccess().getValueSTRINGTerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (op=Operation | op=Unary_Operation)
	 */
	protected void sequence_Operation_Compare(EObject context, Operation_Compare semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Equals(EObject context, Operation_Equals semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Greater_Equals(EObject context, Operation_Greater_Equals semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_Greater_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_Greater_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Greater(EObject context, Operation_Greater semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_GreaterAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_GreaterAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Less_Equals(EObject context, Operation_Less_Equals semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_Less_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_Less_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Less(EObject context, Operation_Less semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_LessAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_LessAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=Expression_Terminal
	 */
	protected void sequence_Operation_NotUnary(EObject context, Operation_NotUnary semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.UNARY_OPERATION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.UNARY_OPERATION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_NotUnaryAccess().getValueExpression_TerminalParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression_Terminal right=Expression_Terminal)
	 */
	protected void sequence_Operation_Not_Equals(EObject context, Operation_Not_Equals semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__LEFT));
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.OPERATION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_Not_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getOperation_Not_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=Expression_Terminal
	 */
	protected void sequence_Operation_Unary(EObject context, Operation_Unary semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConditionModelPackage.Literals.UNARY_OPERATION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConditionModelPackage.Literals.UNARY_OPERATION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOperation_UnaryAccess().getValueExpression_TerminalParserRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
}
