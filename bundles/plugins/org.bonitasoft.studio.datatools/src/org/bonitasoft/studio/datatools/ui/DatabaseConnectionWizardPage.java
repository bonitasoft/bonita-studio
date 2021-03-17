/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.datatools.ui;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.datatools.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public abstract class DatabaseConnectionWizardPage extends AbstractConnectorConfigurationWizardPage {

    protected ExpressionViewer driverClassExpressionViewer;
    protected ExpressionViewer jdbcURLExpressionViewer;
    protected ExpressionViewer userExpressionViewer;
    protected ExpressionViewer passwordExpressionViewer;

    public DatabaseConnectionWizardPage(){
        super();
        setTitle(Messages.databaseJdbcConnectionPageTitle) ;
        setDescription(Messages.databaseJdbcConnectionPageDesc) ;
    }

    @Override
    protected Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create()) ;

        createTemplateChoice(mainComposite);

        final Label driverClassLabel = new Label(mainComposite, SWT.NONE);
        driverClassLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;
        driverClassLabel.setText(Messages.driverClassname) ;

        driverClassExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER);
        driverClassExpressionViewer.setIsPageFlowContext(isPageFlowContext());
        driverClassExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        driverClassExpressionViewer.setMessage(Messages.driverClassnameHint) ;

        final Label jdbcUrlLabel = new Label(mainComposite, SWT.NONE);
        jdbcUrlLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;
        jdbcUrlLabel.setText(Messages.jdbcURL) ;

        jdbcURLExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER);
        jdbcURLExpressionViewer.setIsPageFlowContext(isPageFlowContext());
        jdbcURLExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        jdbcURLExpressionViewer.setMessage(Messages.jdbcURLHint) ;

        final Label userLabel = new Label(mainComposite, SWT.NONE);
        userLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;
        userLabel.setText(Messages.user) ;

        userExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER);
        userExpressionViewer.setIsPageFlowContext(isPageComplete());
        userExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());


        final Label passwordLabel = new Label(mainComposite, SWT.NONE);
        passwordLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;
        passwordLabel.setText(Messages.password) ;

        passwordExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER | SWT.PASSWORD);
        passwordExpressionViewer.setIsPageFlowContext(isPageFlowContext());
        passwordExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        bindWidgets(context) ;
        return mainComposite;
    }

    protected void createTemplateChoice(final Composite mainComposite) {
        final Label templateLabel = new Label(mainComposite, SWT.NONE) ;
        templateLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        templateLabel.setText(Messages.driverTemplate);

        final ComboViewer driversCombo = new ComboViewer(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        driversCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        driversCombo.setContentProvider(new ArrayContentProvider());
        driversCombo.setLabelProvider(new DriverTemplateLabelProvider());
        driversCombo.setInput(getDriverTemplates()) ;
        driversCombo.setSelection(new StructuredSelection(((List<?>) getDriverTemplates()).get(0)));


        final Button applyTemplate = new Button(mainComposite, SWT.PUSH);
        applyTemplate.setText(Messages.applyTemplate);
        applyTemplate.setLayoutData(GridDataFactory.fillDefaults().create());
        applyTemplate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                applyChanged(((IStructuredSelection) driversCombo.getSelection()).getFirstElement()) ;
            }
        });
    }

    protected abstract EReference getPasswordExpressionReference() ;

    protected abstract EReference getUserExpressionReference();


    protected abstract EReference getJDBCExpressionReference();

    protected abstract EReference getDriverExpressionReference();

    private Object getDriverTemplates() {
        final List<TemplateDescriptor> descriptors = new ArrayList<TemplateDescriptor>();
        TemplateDescriptor td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.connectivity.db.generic.genericDriverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.enablement.ibm.db2.luw.driverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.enablement.mysql.5_1.driverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.enablement.postgresql.postgresqlDriverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.enablement.oracle.11.driverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        td = TemplateDescriptor.getDriverTemplateDescriptor("org.eclipse.datatools.enablement.msft.sqlserver.2008.driverTemplate");
        if(td != null){
            descriptors.add(td);
        }
        return descriptors;
    }

    protected abstract void bindWidgets(EMFDataBindingContext context) ;


    public void applyChanged(final Object selection) {
        if(MessageDialog.openConfirm(getShell(), Messages.applyTemplateConfirmationTitle,  Messages.applyTemplateConfirmationMsg)){
            final TemplateDescriptor template = (TemplateDescriptor) selection;
            if(template != null){
                final String driverClassName = template.getPropertyValueFromId(IJDBCDriverDefinitionConstants.DRIVER_CLASS_PROP_ID);
                if(driverClassName != null && !driverClassName.isEmpty()){
                    ((Text) driverClassExpressionViewer.getTextControl()).setText(driverClassName) ;
                }
                final String url = template.getPropertyValueFromId(IJDBCDriverDefinitionConstants.URL_PROP_ID);
                if(url != null && !url.isEmpty()){
                    ((Text) jdbcURLExpressionViewer.getTextControl()).setText(url);
                }
                final String user = template.getPropertyValueFromId(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
                if(user != null && !user.isEmpty()){
                    ((Text) userExpressionViewer.getTextControl()).setText(user) ;
                }
            }
        }
    }
}
