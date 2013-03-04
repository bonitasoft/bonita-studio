/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.Set;

import org.bonitasoft.engine.bpm.model.ConnectorEvent;
import org.bonitasoft.studio.common.widgets.LifeCycleWidget;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class SelectEventConnectorNameAndDescWizardPage extends SelectNameAndDescWizardPage {


    private LifeCycleWidget lifeCycle;


    public SelectEventConnectorNameAndDescWizardPage(EObject container, Connector connectorWorkingCopy,Connector originalConnector, Set<EStructuralFeature> featureToCheckForUniqueID) {
        super(container,connectorWorkingCopy, originalConnector,featureToCheckForUniqueID);
    }


    @Override
    protected Composite doCreateControl(Composite parent, EMFDataBindingContext context) {
        Composite composite = super.doCreateControl(parent, context);
        createLifecycle(composite) ;
        createConnectorFailsControls(composite,context);
        return composite ;
    }


    protected void createLifecycle(Composite composite) {
        if (container != null && activityHasLifecycle()
                && !ProcessPackage.Literals.CATCH_MESSAGE_EVENT.isSuperTypeOf(container.eClass())
                && !ProcessPackage.Literals.CATCH_SIGNAL_EVENT.isSuperTypeOf(container.eClass())
                && !ProcessPackage.Literals.ERROR_EVENT.isSuperTypeOf(container.eClass())
                && !ProcessPackage.Literals.TIMER_EVENT.isSuperTypeOf(container.eClass())) {

            Label eventLabel = new Label(composite, SWT.NONE);
            eventLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
            eventLabel.setText(Messages.selectActivityEvent);

            lifeCycle = new LifeCycleWidget(composite, getEvent(),null) ;

            GridData gd1 = new GridData(lifeCycle.getWidth(), lifeCycle.getHeight());
            gd1.horizontalIndent = 0 ;
            lifeCycle.setLayoutData(gd1);
            lifeCycle.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    setEvent(lifeCycle.getEvent());
                }
            });

            if(connector != null && connector.getEvent() != null){
                setEvent(connector.getEvent());
            } else {
                setEvent(ConnectorEvent.ON_FINISH.toString());
            }
        } else {
            setEvent(ConnectorEvent.ON_FINISH.toString());
        }
    }


    protected void createConnectorFailsControls(Composite composite,EMFDataBindingContext context) {
        Label connectorFailsLabel = new Label(composite, SWT.NONE);
        connectorFailsLabel.setText(Messages.connectorCrashLabel);
        connectorFailsLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Combo connectorFailsCombo = new Combo(composite, SWT.READ_ONLY);
        connectorFailsCombo.add(Messages.connectorFails_crash);
        connectorFailsCombo.add(Messages.connectorFails_ignore);

        // Throw Event are not allowed in Connector in forms
        if(!(container instanceof Form || container instanceof SubmitFormButton )){
        	connectorFailsCombo.add(Messages.connectorFails_throwEvent);
        }

        UpdateValueStrategy ignoreEventStrategyTarget = new UpdateValueStrategy() ;
        ignoreEventStrategyTarget.setConverter(new Converter(String.class,Boolean.class) {

            @Override
            public Object convert(Object from) {
                if(from != null){
                    return from.toString().equals(Messages.connectorFails_ignore) ;
                }
                return Boolean.FALSE;
            }
        }) ;
        UpdateValueStrategy  ignoreEventStrategyModel = new UpdateValueStrategy() ;
        ignoreEventStrategyModel.setConverter(new Converter(Boolean.class,String.class) {

            @Override
            public Object convert(Object from) {
                if((Boolean) from){
                    return Messages.connectorFails_ignore ;
                }else if(connector.isThrowErrorEvent()){
                    return Messages.connectorFails_throwEvent ;
                }
                return Messages.connectorFails_crash;
            }
        }) ;

        context.bindValue(WidgetProperties.text().observe(connectorFailsCombo), EMFObservables.observeValue(connector, ProcessPackage.Literals.CONNECTOR__IGNORE_ERRORS),ignoreEventStrategyTarget,ignoreEventStrategyModel) ;


        UpdateValueStrategy throwEventStrategyTarget = new UpdateValueStrategy() ;
        throwEventStrategyTarget.setConverter(new Converter(String.class,Boolean.class) {

            @Override
            public Object convert(Object from) {
                if(from != null){
                    return from.toString().equals(Messages.connectorFails_throwEvent) ;
                }
                return Boolean.FALSE;
            }
        }) ;

        UpdateValueStrategy throwEventStrategyModel = new UpdateValueStrategy() ;
        throwEventStrategyModel.setConverter(new Converter(Boolean.class,String.class) {

            @Override
            public Object convert(Object from) {
                if((Boolean) from){
                    return Messages.connectorFails_throwEvent ;
                }else if(connector.isIgnoreErrors()){
                    return Messages.connectorFails_ignore ;
                }
                return Messages.connectorFails_crash;
            }
        }) ;
        context.bindValue(WidgetProperties.text().observe(connectorFailsCombo), EMFObservables.observeValue(connector, ProcessPackage.Literals.CONNECTOR__THROW_ERROR_EVENT),throwEventStrategyTarget,throwEventStrategyModel) ;



        Label namedErrorEvent = new Label(composite, SWT.NONE);
        namedErrorEvent.setText(Messages.connectorFails_namedError);
        namedErrorEvent.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text namedErrorText = new Text(composite, SWT.BORDER);
        namedErrorText.setTextLimit(255);
        namedErrorText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        UpdateValueStrategy enableNamedErrorStrategyTarget = new UpdateValueStrategy() ;
        enableNamedErrorStrategyTarget.setConverter(new Converter(String.class,Boolean.class) {

            @Override
            public Object convert(Object from) {
                if(from != null){
                    return from.toString().equals(Messages.connectorFails_throwEvent) ;
                }
                return Boolean.FALSE;
            }
        }) ;

        context.bindValue(SWTObservables.observeText(namedErrorText, SWT.Modify), EMFObservables.observeValue(connector, ProcessPackage.Literals.CONNECTOR__NAMED_ERROR)) ;
        namedErrorText.setEnabled(connector.isThrowErrorEvent()) ;
        context.bindValue(WidgetProperties.text().observe(connectorFailsCombo),SWTObservables.observeEnabled(namedErrorText),enableNamedErrorStrategyTarget,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER)) ;

    }



    private boolean activityHasLifecycle() {
        return !container.eClass().equals(ProcessPackage.Literals.TIMER_EVENT) &&
                !container.eClass().equals(ProcessPackage.Literals.CATCH_MESSAGE_EVENT) &&
                !container.eClass().equals(ProcessPackage.Literals.CATCH_SIGNAL_EVENT) &&
                !container.eClass().equals(ProcessPackage.Literals.ERROR_EVENT) &&
                !container.eClass().equals(FormPackage.Literals.FORM) &&
                !FormPackage.Literals.WIDGET.isSuperTypeOf(container.eClass());
    }



    public void setEvent(String event) {
        connector.setEvent(event) ;
    }


    public String getEvent() {
        return connector.getEvent();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if(lifeCycle != null && !lifeCycle.isDisposed()){
            lifeCycle.dispose() ;
        }
    }
}
