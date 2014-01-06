/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.viewer;


import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.DataExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class ReadOnlyExpressionViewer extends ExpressionViewer {

	public ReadOnlyExpressionViewer(Composite composite, int style,
			TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain, EReference expressionReference) {
		super(composite, style, widgetFactory, editingDomain, expressionReference);
	}

	@Override
	protected void createTextControl(int style,
			TabbedPropertySheetWidgetFactory widgetFactory) {
		super.createTextControl(style | SWT.READ_ONLY , widgetFactory);
		getTextControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		getTextControl().setMessage(Messages.selectTarget);
		IConfigurationElement[] configurationElements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.expression.editor.caretDestroyer");
		if(configurationElements.length == 1){
			try {
				FocusListener caretDestroyerFocusListener = (FocusListener) configurationElements[0].createExecutableExtension("class");
				getTextControl().addFocusListener(caretDestroyerFocusListener);
			} catch (CoreException e1) {
				BonitaStudioLog.error(e1);
			}
		}	
	}

	@Override
	public void manageNatureProviderAndAutocompletionProposal(Object input) {
		super.manageNatureProviderAndAutocompletionProposal(input);
		setProposalsFiltering(false);
	}

	/**
	 * Override to remove Form transient data that cannot be set anywhere
	 */
	@Override
	protected Set<Expression> getFilteredExpressions() {
		Set<Expression> result = super.getFilteredExpressions();
		Set<Expression> toRemove = new HashSet<Expression>();

		boolean isATransientDataInitialization = isTransientDataInitialization(expressionNatureProvider);
		if(!isATransientDataInitialization){
			for(Expression e : result){
				if(ExpressionConstants.VARIABLE_TYPE.equals(e.getType())){
					if(!e.getReferencedElements().isEmpty() && e.getReferencedElements().get(0) instanceof Data){
						Data d = (Data) e.getReferencedElements().get(0);
						final String dsId = d.getDatasourceId();
						if(dsId != null){
							if(DatasourceConstants.PAGEFLOW_DATASOURCE.equals(dsId)){
								toRemove.add(e);
							}
						}
					}
				}
			}
		}
		result.removeAll(toRemove);
		return result;
	}

	private boolean isTransientDataInitialization(IExpressionNatureProvider expressionNatureProvider) {
		if(expressionNatureProvider instanceof DataExpressionNatureProvider){
			EStructuralFeature dataFeature = ((DataExpressionNatureProvider) expressionNatureProvider).getDataFeature();
			if(dataFeature.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA)
					|| dataFeature.equals(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)
					|| dataFeature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)){
				return true;
			}
		}
		EObject context = expressionNatureProvider.getContext();
		if(context != null){
			if(context instanceof Operation && context.eContainer() instanceof Connector){
				return  context.eContainer().eContainmentFeature().equals(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS);
			}
		}
		return false;
	}

	@Override
	protected void bindEditableText(IObservableValue typeObservable) {

	}

	@Override
	protected ToolItem createEditToolItem(ToolBar tb) {
		return null;
	}


}
