package org.bonitasoft.studio.condition.validation;



import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression;
import org.bonitasoft.studio.condition.conditionModel.Expression_Boolean;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.util.ConditionModelSwitch;
import org.bonitasoft.studio.condition.i18n.Messages;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.StringType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;


public class ConditionModelJavaValidator extends AbstractConditionModelJavaValidator {

	private ResourceSet rSet;

	@Check
	public void checkCompatibleTypes(Operation_Compare operation){
		final String errorMessage = new ConditionModelSwitch<String>(){

			public String caseUnary_Operation(org.bonitasoft.studio.condition.conditionModel.Unary_Operation object) {
				return validateUnaryOperation(object.getValue());
			}

			public String caseOperation(Operation object) {
				return compareExpressionsType(object.getLeft(),object.getRight());
			}

		}.doSwitch(operation.getOp());
		if(errorMessage != null){
			error(errorMessage,operation.eContainingFeature());
		}
	}


	private String validateUnaryOperation(Expression e){
		if (!(e instanceof Expression_ProcessRef && ConditionModelPackage.Literals.EXPRESSION_BOOLEAN.getName().equals(getDataType((Expression_ProcessRef) e))) 
				&& ! (e instanceof Expression_Boolean)){
			return Messages.notBooleanType;
		}
		return null;
	}
	private String compareExpressionsType(Expression e1,Expression e2){
		String e1Type =e1.eClass().getName();
		String e2Type = e2.eClass().getName();
		if (e1 instanceof Expression_ProcessRef ){
			Expression_ProcessRef data1=(Expression_ProcessRef)e1;
			e1Type = getDataType(data1);			
		}
		if (e2 instanceof Expression_ProcessRef){
			Expression_ProcessRef data2 = (Expression_ProcessRef)e2;
			e2Type = getDataType(data2);
		}
		if (e1Type.equals(ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName()) || e1Type.equals(ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName())) {
			if ((!e2Type.equals(ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName()) && !e2Type.equals(ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName()))){
				return Messages.incompatibleTypes;
			}
		}else {
			if (!e1Type.equals(e2Type)){
				return Messages.incompatibleTypes;

			}
		} 
		return null;
	}

	private String getDataType(Expression_ProcessRef e){
		EObject proxy = e.getValue();
		rSet = null;
		if(proxy.eIsProxy() && EcoreUtil.getURI(proxy).lastSegment().endsWith(".proc")){
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if(editor != null){
						final DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
						if(diagramEditPart != null){
							final EObject resolveSemanticElement = diagramEditPart.resolveSemanticElement();
							if(resolveSemanticElement != null){
								final Resource eResource = resolveSemanticElement.eResource();
								if(eResource != null){
									rSet = eResource.getResourceSet();
								}
							}
						}						
					}
				}
			});
		}
		EObject data = EcoreUtil2.resolve(proxy, rSet);
		if(rSet != null){
			rSet.getResources().remove(e.eResource());
		}
		if (data instanceof JavaObjectData){
			JavaObjectData javaData = (JavaObjectData)data;
			String className = javaData.getClassName();
			if (className.equals(Boolean.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_BOOLEAN.getName();
			}
			if (className.equals(String.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_STRING.getName();
			}
			if (className.equals(Integer.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
			}
			if (className.equals(Long.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
			}
			if (className.equals(Float.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
			}
			if (className.equals(Double.class.getName())){
				return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
			} 
		} else  {
			if(data instanceof Data){
				DataType type = ((Data) data).getDataType();
				if (type instanceof BooleanType){
					return ConditionModelPackage.Literals.EXPRESSION_BOOLEAN.getName();
				}
				if (type instanceof StringType){
					return ConditionModelPackage.Literals.EXPRESSION_STRING.getName();
				} 
				if (type instanceof IntegerType){
					return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
				}
				if (type instanceof FloatType){
					return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
				}
				if (type instanceof DoubleType){
					return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
				}
				if (type instanceof LongType){
					return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
				}
				if (type instanceof EnumType){
					return ConditionModelPackage.Literals.EXPRESSION_STRING.getName();
				}
			}else if(data instanceof Parameter){
				String type = ((Parameter) data).getTypeClassname();
				if (Boolean.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_BOOLEAN.getName();
				}
				if (String.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_STRING.getName();
				} 
				if (Integer.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
				}
				if (Float.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
				}
				if (Double.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_DOUBLE.getName();
				}
				if (Long.class.getName().equals(type)){
					return ConditionModelPackage.Literals.EXPRESSION_INTEGER.getName();
				}
			}
		}
		return null;
	}
}
