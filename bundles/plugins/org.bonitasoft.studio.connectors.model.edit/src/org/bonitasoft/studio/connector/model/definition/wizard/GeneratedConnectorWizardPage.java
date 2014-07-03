/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.Section;

/**
 * *
 * 
 * @author Romain Bioteau
 */
public class GeneratedConnectorWizardPage extends AbstractConnectorConfigurationWizardPage {

    protected PageComponentSwitch componentSwitch;

    public GeneratedConnectorWizardPage(final String pageName){
        super(pageName);
    }

    public GeneratedConnectorWizardPage(){
        super();
    }

    @Override
    public Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Page page = getPage();
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, pageComposite);
        componentSwitch.setIsPageFlowContext(isPageFlowContext());

        for(final Component component : page.getWidget()){
            componentSwitch.doSwitch(component) ;
        }
        for(final Section section : componentSwitch.getSectionsToExpand()){
            section.setExpanded(true) ;
        }
        return mainComposite;
    }

    protected PageComponentSwitch getPageComponentSwitch(
            final EMFDataBindingContext context, final Composite pageComposite) {
        if(componentSwitch == null){
            final PageComponentSwitchBuilder builder = new PageComponentSwitchBuilder(getElementContainer(), getDefinition(), getConfiguration(), context,
                    getMessageProvider(), getExpressionTypeFilter());
            componentSwitch = new PageComponentSwitch(getContainer(), pageComposite, builder);
        }
        return componentSwitch;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

}
