/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.wizard.sforce.pages;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.progress.IProgressService;

import com.sforce.ws.ConnectionException;

/**
 * @author Maxence Raoux
 */
public class SForceConnectionWizardPage extends AbstractSforceWizardPage {

    private Button connectButton;
    private Button disconectButton;
    private static final String USERNAME_INPUT = "username";
    private static final String PASSWORD_INPUT = "password";
    private static final String TOKEN_INPUT = "securityToken";
    private static final String AUTH_ENDPOINT_INPUT = "authEndpoint";
    private static final String SERVICE_ENDPOINT_INPUT = "serviceEndpoint";

    @Override
    public Control doCreateControl(Composite parent,
            EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1)
                .margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());

        createConnectionGroup(pageComposite);

        final Page page = getPage();
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(
                context, pageComposite);

        for (Component component : page.getWidget()) {
            componentSwitch.doSwitch(component);
        }
        for (Section section : componentSwitch.getSectionsToExpand()) {
            section.setExpanded(true);
        }

        updateButtons();
        return mainComposite;
    }

    private void createConnectionGroup(final Composite pageComposite) {
        final Group connectionGroup = new Group(pageComposite, SWT.NONE);
        connectionGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1)
                .create());
        connectionGroup.setText(Messages.testConnectTitle);
        connectionGroup.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).margins(5, 5).create());

        final Label connectionLabel = new Label(connectionGroup, SWT.NONE);
        connectionLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1)
                .create());
        connectionLabel.setText(Messages.testConnectExplanation);

        connectButton = new Button(connectionGroup, SWT.PUSH);
        connectButton.setText(Messages.connect);
        connectButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                final CredentialDialog dialog = new CredentialDialog(Display
                        .getDefault().getActiveShell(), SWT.SHEET);
                final Expression exprUsername = (Expression) getConnectorParameter(
                        getInput(USERNAME_INPUT)).getExpression();
                final Expression exprPassword = (Expression) getConnectorParameter(
                        getInput(PASSWORD_INPUT)).getExpression();
                final Expression exprToken = (Expression) getConnectorParameter(
                        getInput(TOKEN_INPUT)).getExpression();
                final Expression authEndpoint = ((Expression) getConnectorParameter(
                        getInput(AUTH_ENDPOINT_INPUT)).getExpression());
                dialog.setUsername(exprUsername.getContent());
                dialog.setPassword(exprPassword.getContent());
                dialog.setToken(exprToken.getContent());
                dialog.setAuthEndpoint(authEndpoint.getContent());
                int status = dialog.open();
                if (status == Dialog.OK) {
                    IProgressService service = PlatformUI.getWorkbench()
                            .getProgressService();
                    try {
                        service.run(true, false, new IRunnableWithProgress() {

                            @Override
                            public void run(IProgressMonitor monitor)
                                    throws InvocationTargetException,
                                    InterruptedException {
                                completeWizardFields(dialog);
                                monitor.beginTask(Messages.connecting,
                                        IProgressMonitor.UNKNOWN);
                                connectToSalesForce(dialog.getUsername(),
                                        dialog.getPassword(), dialog.getToken());
                            }
                        });
                    } catch (InvocationTargetException e1) {
                        BonitaStudioLog.log(e1.getMessage());
                    } catch (InterruptedException e1) {
                        BonitaStudioLog.log(e1.getMessage());
                    }

                }
            }
        });

        disconectButton = new Button(connectionGroup, SWT.PUSH);
        disconectButton.setText(Messages.disconnect);
        disconectButton.setEnabled(false);
        disconectButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                try {
                    sfTool.logout();
                } catch (ConnectionException e1) {
                    BonitaStudioLog.log(e1.getMessage());
                }
                updateButtons();
            }
        });
    }

    private void completeWizardFields(CredentialDialog dialog) {
        Expression expr = ExpressionHelper.createConstantExpression(
                dialog.getUsername(), String.class.getName());
        getConnectorParameter(getInput(USERNAME_INPUT)).setExpression(expr);
        expr = ExpressionHelper.createConstantExpression(dialog.getPassword(),
                String.class.getName());
        getConnectorParameter(getInput(PASSWORD_INPUT)).setExpression(expr);
        expr = ExpressionHelper.createConstantExpression(dialog.getToken(),
                String.class.getName());
        getConnectorParameter(getInput(TOKEN_INPUT)).setExpression(expr);
        expr = ExpressionHelper.createConstantExpression(dialog.getAuthEndpoint(),
                String.class.getName());
        getConnectorParameter(getInput(AUTH_ENDPOINT_INPUT)).setExpression(expr);
        expr = ExpressionHelper.createConstantExpression(dialog.getAuthEndpoint(),
                String.class.getName());
        getConnectorParameter(getInput(SERVICE_ENDPOINT_INPUT)).setExpression(expr);
    }

    private void connectToSalesForce(String username, String password,
            String token) {
        try {
            sfTool.logout();
            final String password_securityToken = password + "" + token;
            final String authEndpoint = ((Expression) getConnectorParameter(
                    getInput(AUTH_ENDPOINT_INPUT)).getExpression()).getContent();
            final String serviceEndpoint = ((Expression) getConnectorParameter(
                    getInput("serviceEndpoint")).getExpression()).getContent();
            final String restEndpoint = ((Expression) getConnectorParameter(
                    getInput("restEndpoint")).getExpression()).getContent();
            final String proxyHost = ((Expression) getConnectorParameter(
                    getInput("proxyHost")).getExpression()).getContent();
            final int proxyPort = Integer
                    .parseInt(((Expression) getConnectorParameter(
                            getInput("proxyPort")).getExpression())
                            .getContent());
            final String proxyUsername = ((Expression) getConnectorParameter(
                    getInput("proxyUsername")).getExpression()).getContent();
            final String proxyPassword = ((Expression) getConnectorParameter(
                    getInput("proxyPassword")).getExpression()).getContent();
            final int connectionTimeout = Integer
                    .parseInt(((Expression) getConnectorParameter(
                            getInput("connectionTimeout")).getExpression())
                            .getContent());
            final int readTimeout = Integer
                    .parseInt(((Expression) getConnectorParameter(
                            getInput("readTimeout")).getExpression())
                            .getContent());
            sfTool.initialize(username, password_securityToken, authEndpoint,
                    serviceEndpoint, restEndpoint, proxyHost, proxyPort,
                    proxyUsername, proxyPassword, connectionTimeout,
                    readTimeout);
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openInformation(Display.getCurrent()
                            .getActiveShell(), Messages.testConnectTitle,
                            Messages.successConnectingMessage);
                    updateButtons();
                }
            });
        } catch (ConnectionException e) {
            errorDialog(e);
        } catch (NumberFormatException e) {
            errorDialog(e);
        }
    }

    private void errorDialog(Exception e) {
        final Exception error = e;
        BonitaStudioLog.log(e.getMessage());
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                ErrorDialog d = new ErrorDialog(Display.getDefault()
                        .getActiveShell(), Messages.testConnectTitle,
                        Messages.errorConnectingMessage, new Status(
                                IStatus.ERROR, "salesforce",
                                error.getMessage(), error), IStatus.ERROR
                                | IStatus.CANCEL | IStatus.WARNING);
                d.open();
            }
        });
    }

    private void updateButtons() {
    	connectButton.setEnabled(sfTool == null || !sfTool.isLogged());
    	disconectButton.setEnabled(sfTool != null && sfTool.isLogged());
    }

    class CredentialDialog extends Dialog {

        private Text usernameText;
        private Text passwordText;
        private Text tokenText;
        private Text urlText;

        private String username;
        private String authEndpoint;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setAuthEndpoint(String authEndpoint) {
            this.authEndpoint = authEndpoint;
        }

        private String password;

        public void setPassword(String password) {
            this.password = password;
        }

        private String token;


        public void setToken(String token) {
            this.token = token;
        }

        public CredentialDialog(Shell newShell, int style) {
            super(newShell);
            setShellStyle(style);
        }

        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText(Messages.connect);
        }

        @Override
        protected Control createDialogArea(Composite parent) {
            Composite main = new Composite(parent, SWT.NONE);
            main.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                    .create());
            main.setLayout(GridLayoutFactory.swtDefaults().create());

            Label authEndpointUrlLabel = new Label(main, SWT.NONE);
            authEndpointUrlLabel.setText(Messages.authEndpointURL);
            authEndpointUrlLabel.setLayoutData(GridDataFactory.swtDefaults()
                    .align(SWT.BEGINNING, SWT.CENTER).create());
            urlText = new Text(main, SWT.BORDER);
            urlText.setLayoutData(GridDataFactory.fillDefaults()
                    .grab(true, false).hint(350, SWT.DEFAULT).create());
            if (urlText != null) {
                urlText.setText(authEndpoint);
            }

            Label name = new Label(main, SWT.NONE);
            name.setLayoutData(GridDataFactory.swtDefaults()
                    .align(SWT.BEGINNING, SWT.CENTER).create());
            name.setText(Messages.login);
            usernameText = new Text(main, SWT.BORDER);
            usernameText.setLayoutData(GridDataFactory.fillDefaults()
                    .grab(true, false).hint(350, SWT.DEFAULT).create());
            if (username != null) {
                usernameText.setText(username);
            }
            Label pass = new Label(main, SWT.NONE);
            pass.setLayoutData(GridDataFactory.swtDefaults()
                    .align(SWT.BEGINNING, SWT.CENTER).create());
            pass.setText(Messages.password);
            passwordText = new Text(main, SWT.PASSWORD | SWT.BORDER);
            passwordText.setLayoutData(GridDataFactory.fillDefaults()
                    .grab(true, false).hint(350, SWT.DEFAULT).create());
            if (password != null) {
                passwordText.setText(password);
            }
            Label tokenLabel = new Label(main, SWT.NONE);
            tokenLabel.setLayoutData(GridDataFactory.swtDefaults()
                    .align(SWT.BEGINNING, SWT.CENTER).create());
            tokenLabel.setText(Messages.token);
            tokenText = new Text(main, SWT.PASSWORD | SWT.BORDER);
            tokenText.setLayoutData(GridDataFactory.fillDefaults()
                    .grab(true, false).hint(350, SWT.DEFAULT).create());
            if (token != null) {
                tokenText.setText(token);
            }
            return main;
        }

        @Override
        protected void okPressed() {
            username = usernameText.getText();
            password = passwordText.getText();
            token = tokenText.getText();
            authEndpoint = urlText.getText();
            super.okPressed();
        }

        protected String getUsername() {
            return username;
        }

        protected String getPassword() {
            return password;
        }

        protected String getToken() {
            return token;
        }

        protected String getAuthEndpoint() {
            return authEndpoint;
        }
    }

}
