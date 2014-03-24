/**
 * 
 */
package org.bonitasoft.studio.common.dialog;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author aurelie Zara
 *
 */
public class OutlineFilter extends ViewerFilter {

	private EObject elementToDisplay;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (elementToDisplay!=null ){
			if (element instanceof Expression){
				return isReferencedInExpression((Expression)element);
			} else {
				return isLeafCanBeDisplayed((EObject)element);
			}
		}
		return true;
	}

	public EObject getElementToDisplay() {
		return elementToDisplay;
	}

	public void setElementToDisplay(EObject elementToDisplay) {
		this.elementToDisplay = elementToDisplay;
	}
	
	
	public boolean isLeafCanBeDisplayed(EObject element){
		List<Expression> exprs= ModelHelper.getAllItemsOfType(element, ExpressionPackage.Literals.EXPRESSION);
		for (Expression expr:exprs){
			if (isReferencedInExpression(expr)){
				return true;
			}
		}
		return false;
		
	}
	
	private boolean isReferencedInExpression(Expression expr){
		for (EObject referencedElement:expr.getReferencedElements()){
			if (referencedElement instanceof Parameter && elementToDisplay instanceof Parameter){
				return ((Parameter)referencedElement).getName().equals(((Parameter)elementToDisplay).getName());
			}
			
			if (referencedElement instanceof SearchIndex && elementToDisplay instanceof SearchIndex){
				return ((SearchIndex)referencedElement).getName().getName().equals(((SearchIndex)elementToDisplay).getName().getName());
			}
			if (referencedElement instanceof Element && elementToDisplay instanceof Element){
				return ((Element)referencedElement).getName().equals(((Element)elementToDisplay).getName());
			}
		}
		return false;
	}
	
	

}
