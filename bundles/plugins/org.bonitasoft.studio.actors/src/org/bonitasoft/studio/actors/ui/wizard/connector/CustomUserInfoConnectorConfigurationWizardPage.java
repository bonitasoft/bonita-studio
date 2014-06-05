/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.studio.actors.ui.wizard.connector;

import org.bonitasoft.studio.actors.preference.ActorsPreferenceConstants;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;


/**
 * @author Elias Ricken de Medeiros
 *
 */
public class CustomUserInfoConnectorConfigurationWizardPage extends AbstractConnectorConfigurationWizardPage {

    private PageComponentSwitch componentSwitch;

    public CustomUserInfoConnectorConfigurationWizardPage() {
        super(CustomUserInfoConnectorConfigurationWizardPage.class.getName());
    }

    @Override
    protected Control doCreateControl(Composite parent, EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE) ;
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Page page = getPage() ;
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, pageComposite) ;
        componentSwitch.setIsPageFlowContext(isPageFlowContext());
        for(Component component : page.getWidget()){
            if(component.getId().equals("customUserInfoName")){
                createCustomExpressionViewerWithCustomInfo(pageComposite,(Text) component,context);
            }else{
                componentSwitch.doSwitch(component) ;
            }
        }
        for(Section section : componentSwitch.getSectionsToExpand()){
            section.setExpanded(true) ;
        }
        return mainComposite ;
    }

    private void createCustomExpressionViewerWithCustomInfo(Composite composite, Text object, EMFDataBindingContext context) {
        final Input input = getInput(object.getInputName()) ;
        if(input != null){
            final ConnectorParameter parameter = getConnectorParameter(input) ;
            if(parameter != null){
                final Label fieldLabel = new Label(composite, SWT.NONE);
                if(object.getId() != null){
                    String label = getLabel(object.getId()) ;
                    if(input.isMandatory()){
                        label = label + " *" ;
                    }
                    fieldLabel.setText(label) ;
                }
                fieldLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

                final ExpressionViewer viewer = new ExpressionViewer(composite,SWT.BORDER, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION) ;
                viewer.setIsPageFlowContext(false);
                viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
                OrganizationRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
                String activeOrganization = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(ActorsPreferenceConstants.DEFAULT_ORGANIZATION) ;
                String fileName = activeOrganization + "." + OrganizationRepositoryStore.ORGANIZATION_EXT;
                viewer.setExpressionNatureProvider(new CustomUserInfoNameExpressionProvider(store, fileName));
                viewer.setContext(getElementContainer());
                if(input.isMandatory()){
                    viewer.setMandatoryField(getLabel(object.getId()),context) ;
                }

                viewer.addFilter(getExpressionTypeFilter());
                viewer.setInput(parameter) ;
                context.bindValue(ViewersObservables.observeSingleSelection(viewer), EMFObservables.observeValue(parameter, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));
            }
        } else {
            //Should we create a label to warn final user?
            BonitaStudioLog.log("WARNING: No input found with name "+object.getInputName());
        }
    }

    protected String getLabel(String inputName) {
        String label =  getMessageProvider().getFieldLabel(getDefinition(), inputName) ;
        if(label == null){
            label = "" ;
        }

        return label ;
    }

    protected PageComponentSwitch getPageComponentSwitch(
            EMFDataBindingContext context, final Composite pageComposite) {
        if(componentSwitch == null){
            componentSwitch = new PageComponentSwitch(getContainer(),pageComposite,getElementContainer(),getDefinition(),getConfiguration(),context,getMessageProvider(),getExpressionTypeFilter());
        }
        return componentSwitch;
    }


}
