/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.ArrayList;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.Password;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionSwitch;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.CheckBoxExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;



/**
 * @author Romain Bioteau
 *
 */
public class PageComponentSwitch extends ConnectorDefinitionSwitch<Component> implements IBonitaVariableContext {


    private final Composite parent;
    private final java.util.List<Section> sections = new ArrayList<>() ;
    protected final IWizardContainer iWizardContainer;
    protected final PageComponentSwitchBuilder componentBuilder;

    public PageComponentSwitch(final IWizardContainer iWizardContainer, final Composite parent, final PageComponentSwitchBuilder componentBuilder) {
        this.parent = parent ;
        this.iWizardContainer = iWizardContainer;
        this.componentBuilder = componentBuilder;
    }

    public PageComponentSwitch(final IWizardContainer iWizardContainer,
            final Composite parent,
            final EObject container, 
            final ExtendedConnectorDefinition definition,
            final ConnectorConfiguration connectorConfiguration, 
            final EMFDataBindingContext context, 
            final AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
        this(iWizardContainer, parent, new PageComponentSwitchBuilder(container, 
                definition, 
                connectorConfiguration, 
                context, 
                connectorExpressionContentTypeFilter));
    }

    @Override
    public Component caseArray(final Array object) {
        createArrayControl(parent,object) ;
        return object;
    }

    @Override
    public Component caseCheckbox(final Checkbox object) {
        createCheckboxControl(parent,object) ;
        return object;
    }

    @Override
    public Component caseScriptEditor(final ScriptEditor object) {
        createScriptEditorControl(parent,object) ;
        return object;
    }

    @Override
    public Component caseGroup(final Group object) {
        final Section section = createGroupControl(parent,object) ;
        final Composite client = new Composite(section, SWT.NONE) ;
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(5, 2).create()) ;
        final PageComponentSwitch groupSwitch = createGroupPageComponentSwitch(client);
        for(final Component component : object.getWidget()){
            groupSwitch.doSwitch(component) ;
        }

        section.setClient(client) ;
        final java.util.List<Section> sectionsToExpand = groupSwitch.getSectionsToExpand() ;
        sections.addAll(sectionsToExpand) ;
        if(!object.isOptional() && object.isSetOptional()){
            sections.add(section) ;
        }
        section.addExpansionListener(new IExpansionListener() {

            @Override
            public void expansionStateChanging(final ExpansionEvent event) {}

            @Override
            public void expansionStateChanged(final ExpansionEvent event) {
                final Shell shell = section.getShell();
                final Point defaultSize = shell.getSize() ;
                final Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true) ;
                shell.setSize(defaultSize.x, size.y) ;
                shell.layout(true, true) ;
            }
        }) ;

        return object;
    }

    protected PageComponentSwitch createGroupPageComponentSwitch(final Composite client) {
        return new PageComponentSwitch(iWizardContainer, client, componentBuilder);
    }


    public java.util.List<Section> getSectionsToExpand() {
        return sections  ;
    }

    @Override
    public Component caseList(final List object) {
        createListControl(parent,object) ;
        return object;
    }


    @Override
    public Component casePassword(final Password object) {
        createPasswordControl(parent,object) ;
        return object;
    }


    @Override
    public Component caseSelect(final Select object) {
        createSelectControl(parent,object) ;
        return object;
    }


    @Override
    public Component caseText(final Text object) {
        createTextControl(parent,object) ;
        return object;
    }


    @Override
    public Component caseRadioGroup(final RadioGroup object) {
        createRadioGroupControl(parent,object) ;
        return object;
    }


    @Override
    public Component caseTextArea(final TextArea object) {
        createTextAreaControl(parent,object) ;
        return object;
    }

    protected Composite createRadioGroupControl(final Composite composite, final RadioGroup object) {
        return componentBuilder.createRadioGroupControl(composite, object);

    }

    protected PatternExpressionViewer createTextAreaControl(final Composite composite, final TextArea object) {
        return componentBuilder.createTextAreaControl(composite, object);
    }

    protected CheckBoxExpressionViewer createCheckboxControl(final Composite composite, final Checkbox object) {
        return componentBuilder.createCheckboxControl(composite, object);
    }

    protected String getLabel(final String inputName) {
        return componentBuilder.getLabel(inputName);
    }

    protected ExpressionViewer createTextControl(final Composite composite, final Text object) {
        return componentBuilder.createTextControl(composite, object);
    }

    protected ExpressionViewer createScriptEditorControl(final Composite composite, final ScriptEditor object) {
        return componentBuilder.createScriptEditorControl(composite, object);
    }

    protected ExpressionCollectionViewer createArrayControl(final Composite composite, final Array object) {
        return componentBuilder.createArrayControl(composite, object);
    }

    protected Section createGroupControl(final Composite composite, final Group object) {
        return componentBuilder.createGroupControl(composite, object);
    }

    protected ExpressionCollectionViewer createListControl(final Composite composite, final List object) {
        return componentBuilder.createListControl(composite, object);
    }

    protected void createDescriptionDecorator(final Composite composite,
            final Label labelField, final String desc) {
        componentBuilder.createDescriptionDecorator(composite, labelField, desc);
    }

    protected ExpressionViewer createPasswordControl(final Composite composite, final Password object) {
        return componentBuilder.createPasswordControl(composite, object);
    }

    protected Combo createSelectControl(final Composite composite, final Select object) {
        return componentBuilder.createSelectControl(composite, object);
    }

    protected Input getConnectorInput(final String inputName) {
        return componentBuilder.getConnectorInput(inputName);
    }

    protected Label createFieldLabel(final Composite composite,final int verticalAlignment, final String id, final boolean isMandatory) {
        return componentBuilder.createFieldLabel(composite, verticalAlignment, id, isMandatory);
    }

    protected AbstractExpression createExpression(final WidgetComponent widget,final Input input) {
        final String inputClassName = input.getType() ;
        if( widget instanceof Array){
            final TableExpression expression = ExpressionFactory.eINSTANCE.createTableExpression() ;
            return expression ;
        }else if( widget instanceof org.bonitasoft.studio.connector.model.definition.List){
            final ListExpression expression = ExpressionFactory.eINSTANCE.createListExpression() ;
            return expression ;
        }else{
            final Expression expression = ExpressionFactory.eINSTANCE.createExpression() ;
            expression.setReturnType(inputClassName) ;
            expression.setReturnTypeFixed(true) ;
            expression.setType(ExpressionConstants.CONSTANT_TYPE) ;
            expression.setName(input.getDefaultValue()) ;
            expression.setContent(input.getDefaultValue()) ;
            if(widget instanceof ScriptEditor){
                expression.setType(ExpressionConstants.SCRIPT_TYPE);
                expression.setInterpreter(((ScriptEditor) widget).getInterpreter());
            }else if(widget instanceof TextArea){
                expression.setType(ExpressionConstants.PATTERN_TYPE);
            }
            return expression ;
        }
    }

    @Override
    public boolean isPageFlowContext() {
        return componentBuilder.isPageFlowContext();
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        componentBuilder.setIsPageFlowContext(isPageFlowContext);
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

    protected AvailableExpressionTypeFilter getConnectorExpressionContentTypeFilter() {
        return componentBuilder.connectorExpressionContentTypeFilter;
    }

    protected EObject getContainer() {
        return componentBuilder.container;
    }

    protected ExtendedConnectorDefinition getDefinition() {
        return componentBuilder.definition;
    }

    protected EMFDataBindingContext getContext() {
        return componentBuilder.context;
    }

}
