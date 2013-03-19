/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.provider;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class FormFieldExpressionProvider implements IExpressionProvider {

	private final ComposedAdapterFactory adapterFactory;
	private final AdapterFactoryLabelProvider adapterLabelProvider;

	public FormFieldExpressionProvider(){
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterLabelProvider  = new AdapterFactoryLabelProvider(adapterFactory) ;
	}

	public Set<Expression> getExpressions(EObject context) {
		Set<Expression> result = new HashSet<Expression>() ;
		EObject relevantParent = getRelevantParent(context) ;
		if (relevantParent instanceof Widget) {
			result.add(createExpression((Widget) relevantParent) ) ;
			for(WidgetDependency dep : ((Widget) relevantParent).getDependOn()){
				if(dep.getWidget()!=null){
					result.add(createExpression(dep.getWidget())) ;
				}
			}

			// for the Submit button only, add fields of other widgets
			if(relevantParent instanceof SubmitFormButton){
				if(relevantParent.eContainer()!= null && relevantParent.eContainer() instanceof Form){
					Form f = ModelHelper.getParentForm(relevantParent);
					for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
						if (w instanceof FormField){
							result.add( createExpression(w) ) ;
						}
					}
				}
			}

			//Add all widgets from the pageflow not in the same form

			final AbstractPageFlow pageFlow = ModelHelper.getPageFlow((Widget) relevantParent);
			if (pageFlow != null ) {
				List<? extends Form> forms= getForms(pageFlow, ModelHelper.getForm((Widget) relevantParent));
				Form parentForm = ModelHelper.getParentForm(relevantParent);
				for (Form f : forms){
					if(!f.equals(parentForm)){
						for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
							if (w instanceof FormField || w instanceof NextFormButton){
								result.add( createExpression(w) ) ;
							}
						}
					}
				}
			}
		}else  if (relevantParent instanceof Form && relevantParent.eContainer() != null) {
			if(relevantParent.eContainer() instanceof AbstractPageFlow){
				// get all fields from pageflow
				final AbstractPageFlow pageFlow = (AbstractPageFlow) relevantParent.eContainer();
				if(pageFlow != null){
					List<? extends Form> forms= getForms(pageFlow, (Form) relevantParent);					
					for (Form f : forms){
						for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
							if (w instanceof FormField){
								result.add( createExpression(w) ) ;
							}
						}
					}
				}
			}
		}else if(relevantParent instanceof AbstractPageFlow){
			// get all fields from pageflow
			if(relevantParent instanceof PageFlow){
				if(relevantParent != null){
					for (Form f : ((PageFlow) relevantParent).getForm()){
						for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
							if (w instanceof FormField || w instanceof NextFormButton){
								result.add( createExpression(w) ) ;
							}
						}
					}
					if(relevantParent instanceof ViewPageFlow){
						for (Form f : ((ViewPageFlow) relevantParent).getViewForm()){
							for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
								if (w instanceof FormField || w instanceof NextFormButton){
									result.add( createExpression(w) ) ;
								}
							}
						}
					}
					if(relevantParent instanceof RecapFlow){
						for (Form f : ((RecapFlow) relevantParent).getRecapForms()){
							for (Widget w : ModelHelper.getAllWidgetInsideForm(f)) {
								if (w instanceof FormField || w instanceof NextFormButton){
									result.add( createExpression(w) ) ;
								}
							}
						}
					}
				}
			}
		}


		return result;
	}

	private List<? extends Form> getForms(AbstractPageFlow pageFlow, Form form) {
		EStructuralFeature eContainingFeature = form.eContainingFeature();
		if(ProcessPackage.Literals.PAGE_FLOW__FORM.equals(eContainingFeature)){
			return ((PageFlow) pageFlow).getForm();
		} else if(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM.equals(eContainingFeature)){
			return ((ViewPageFlow) pageFlow).getViewForm();
		} else if(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS.equals(eContainingFeature)){
			return ((RecapFlow) pageFlow).getRecapForms();
		}
		return Collections.emptyList();
	}

	private EObject getRelevantParent(EObject context) {
		EObject parent = context ;
		while(parent != null && (!(parent instanceof Form) && !(parent instanceof Widget)) && !(parent instanceof PageFlow)){
			parent = parent.eContainer() ;
		}
		return parent;
	}

	private Expression createExpression(Widget w) {
		Expression exp = ExpressionFactory.eINSTANCE.createExpression() ;
		exp.setType(getExpressionType()) ;
		exp.setContent("field_"+w.getName()) ;
		exp.setName("field_"+w.getName()) ;
		if(w instanceof Duplicable && ((Duplicable) w).isDuplicate()){
			exp.setReturnType(List.class.getName()) ; 
		}else{
			if(w instanceof TextFormField && w.getReturnTypeModifier() != null ){
				exp.setReturnType(w.getReturnTypeModifier()) ;
			}else{
				exp.setReturnType(w.getAssociatedReturnType()) ;
			}
		}
		exp.getReferencedElements().add(EcoreUtil.copy(w)) ;
		return exp;
	}

	public String getExpressionType() {
		return ExpressionConstants.FORM_FIELD_TYPE;
	}

	public Image getIcon(Expression expression) {
		if(expression.getReferencedElements().isEmpty()){
			return null ;
		}
		return adapterLabelProvider.getImage(expression.getReferencedElements().get(0)) ;
	}

	public String getProposalLabel(Expression expression) {
		return expression.getName() ;
	}

	public boolean isRelevantFor(EObject context) {
		return context instanceof EObject;
	}

	public Image getTypeIcon() {
		return Pics.getImage(PicsConstants.form);
	}

	public String getTypeLabel() {
		return Messages.formFieldTypeLabel;
	}

	public IExpressionEditor getExpressionEditor(Expression expression,EObject context) {
		return new FormFieldExpressionEditor();
	}



}
