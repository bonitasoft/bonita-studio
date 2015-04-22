/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.simulation.properties.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.BonitaNumberFormat;
import org.bonitasoft.studio.common.jface.databinding.validator.WrappingValidator;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.model.simulation.SimulationTransition;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.wizards.AddSimulationDataWizard;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.internal.databinding.validation.StringToDoubleValidator;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * 
 */
public class TransitionDataContribution extends AbstractPropertySectionContribution {

    private EMFDataBindingContext context;

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof SimulationTransition ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.transitionCondition ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory,
     * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
        composite.setLayout(new GridLayout(2, false));
        Composite radioComposite = widgetFactory.createComposite(composite);
        radioComposite.setLayout(new FillLayout());
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        Button expressionRadio = widgetFactory.createButton(radioComposite, "Expression", SWT.RADIO);
        Button probaRadio = widgetFactory.createButton(radioComposite, "Probability", SWT.RADIO);


        final Composite stackComposite = widgetFactory.createComposite(composite);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        final StackLayout stackLayout = new StackLayout();
        stackComposite.setLayout(stackLayout);



        final Composite probaComposite = widgetFactory.createComposite(stackComposite);
        probaComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create()) ;
        GridLayout layout = new GridLayout(1,false);
        layout.marginWidth = 10;
        probaComposite.setLayout(layout);
        Text probaText = widgetFactory.createText(probaComposite, "",SWT.BORDER);
        probaText.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).hint(50, SWT.DEFAULT).create()) ;
        ControlDecoration controlDecoration = new ControlDecoration(probaText, SWT.LEFT|SWT.TOP);
        FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
        controlDecoration.setImage(fieldDecoration.getImage());
        controlDecoration.setDescriptionText(Messages.mustBeAPercentage);


        final Composite expressionComposite = widgetFactory.createComposite(stackComposite);
        GridLayout layout2 = new GridLayout(2,false);
        layout2.marginWidth = 10;
        expressionComposite.setLayout(layout2);


        ExpressionViewer expressionText = new ExpressionViewer(expressionComposite, SWT.BORDER,widgetFactory,editingDomain, SimulationPackage.Literals.SIMULATION_TRANSITION__EXPRESSION);
        expressionText.getControl().setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).hint(250, SWT.DEFAULT).create());
       expressionText.addFilter(new AvailableExpressionTypeFilter(new String[]{
    		   ExpressionConstants.VARIABLE_TYPE,
    		   ExpressionConstants.GROOVY,
    		   ExpressionConstants.CONSTANT_TYPE,
    		   ExpressionConstants.PARAMETER_TYPE
       }));
        
        
        Expression selection = ((SimulationTransition)eObject).getExpression() ;
        if(selection == null){
            selection = ExpressionFactory.eINSTANCE.createExpression() ;
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_TRANSITION__EXPRESSION, selection)) ;
        }

		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();
		
		context.bindValue(ViewerProperties.singleSelection().observe(expressionText),
				EMFEditProperties.value(editingDomain, SimulationPackage.Literals.SIMULATION_TRANSITION__EXPRESSION).observe(eObject));
        expressionText.setInput(eObject);
        
        Button createDataButton = new Button(expressionComposite,SWT.FLAT) ;
        createDataButton.setText(Messages.createSimulationData) ;
        createDataButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AddSimulationDataWizard wiz = new AddSimulationDataWizard(ModelHelper.getParentProcess(eObject), editingDomain) ;
                WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wiz);
                if(dialog.open() == WizardDialog.OK){
                    //					expressionText.reset() ;
                    //					expressionText.setText(GroovyUtil.GROOVY_PREFIX + wiz.getCreatedData().getName() + GroovyUtil.GROOVY_SUFFIX);
                }

            }

        }) ;


        boolean useExpression = ((SimulationTransition) eObject).isUseExpression();
        if (useExpression) {
            stackLayout.topControl = expressionComposite;
        } else {
            stackLayout.topControl = probaComposite;
        }
        expressionRadio.setSelection(useExpression);
        probaRadio.setSelection(!useExpression);

        expressionRadio.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(((Button) e.getSource()).getSelection()){
                    stackLayout.topControl = expressionComposite;
                } else {
                    stackLayout.topControl = probaComposite;
                }
                stackComposite.layout();
            }
        });

        context = new EMFDataBindingContext();
        context.bindValue(SWTObservables.observeSelection(expressionRadio),EMFEditObservables.observeValue(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_TRANSITION__USE_EXPRESSION));
        context.bindValue(SWTObservables.observeText(probaText, SWT.Modify),EMFEditObservables.observeValue(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_TRANSITION__PROBABILITY),
                new UpdateValueStrategy().setConverter(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(),false))
                .setAfterGetValidator(new WrappingValidator(controlDecoration,new StringToDoubleValidator(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(),false))))
                ,new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(BonitaNumberFormat.getPercentInstance(),false)));

        if(eObject instanceof Connection){
            if (((Connection) eObject).getSource()  instanceof CatchLinkEvent) {
                radioComposite.setEnabled(false) ;
                probaText.setEnabled(false);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        if(context != null){
            context.dispose();
        }

    }

}
