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
package org.bonitasoft.studio.sqlbuilder.ex.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.datatools.ConnectionProfileUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.pattern.GroovyExpressionPartitioner;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionModelBuilder;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.sqlbuilder.ex.SQLBuilderExPlugin;
import org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaSQLBuilder;
import org.bonitasoft.studio.sqlbuilder.ex.util.EditorInputUtil;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.sqlbuilder.IContentChangeListener;
import org.eclipse.datatools.sqltools.sqlbuilder.ParseException;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractConnectDBWizardWizardPage extends AbstractConnectorConfigurationWizardPage implements IContentChangeListener {

    private static final String BONITA_SQL_TMP_FILE = ".bonita.sql";
    private static final String DRIVER_CLASSNAME_INPUT = "driver";
    private static final String JDBC_URL_INPUT = "url";
    private static final String USERNAME_INPUT = "username";
    private static final String PASSWORD_INPUT = "password";
    private static final String SCRIPT_INPUT = "script";
    private static final String WIZARD_MODE_INPUT = null;
    private static final String MODE_PARAMETER = "wizardMode";

    private IConnectionProfile profile;
    private Button connectionButton;
    private Button disconnectButton;
    private Button useGraphical;
    private Button useTextual;
    private BonitaSQLBuilder _sqlBuilder;
    private final DatabaseConnectorPropertiesRepositoryStore store;
    private boolean noActiveDriver = false;
    private Group group;
    private final IExpressionNatureProvider expressionNatureProvider = ExpressionContentProvider.getInstance();
    private Expression scriptExp;
    private SelectObservableValue radioGroupObservable;
    private GroovyExpressionPartitioner groovyExpressionPartitioner;

    public AbstractConnectDBWizardWizardPage() {
        super(AbstractConnectDBWizardWizardPage.class.getName());
        setTitle(Messages.connectDBWizardWizardPageTitle);
        setDescription(Messages.connectDBWizardWizardPageDescription);
        store = RepositoryManager.getInstance().getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final DatabaseConnectorPropertiesFileStore fileStore = store.getChild(getDefinition().getId() + "."
                    + DatabaseConnectorPropertiesRepositoryStore.CONF_EXT, true);
            noActiveDriver = fileStore == null || fileStore.getDefault() == null || fileStore.getDefault().isEmpty();
            if (noActiveDriver) {
                setMessage(Messages.noActiveDriver, DialogPage.WARNING);
                useGraphical.setEnabled(false);
                group.setEnabled(false);
                radioGroupObservable.setValue(Boolean.FALSE.toString());
            } else {
                setMessage(null, DialogPage.NONE);
                group.setEnabled(true);
                useGraphical.setEnabled(true);
            }
            updateButtons();
        }
    }

    @Override
    protected Control doCreateControl(final Composite parent,
            final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 10).create());

        useGraphical = new Button(mainComposite, SWT.RADIO);
        useGraphical.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        useGraphical.setText(Messages.useGraphicalQueryBuilder);

        group = createConnectWizardGroup(mainComposite, context);

        new Label(mainComposite, SWT.NONE);

        useTextual = new Button(mainComposite, SWT.RADIO);
        useTextual.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        useTextual.setText(Messages.useSimpleQuery);
        useTextual.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                group.setEnabled(!useTextual.getSelection());
                updateButtons();
                getContainer().updateButtons();
            }
        });

        final ConnectorParameter param = getAdvancedModeParameter();
        radioGroupObservable = new SelectObservableValue(String.class);
        radioGroupObservable.addOption(Boolean.TRUE.toString(), SWTObservables.observeSelection(useGraphical));
        radioGroupObservable.addOption(Boolean.FALSE.toString(), SWTObservables.observeSelection(useTextual));

        context.bindValue(radioGroupObservable, EMFObservables.observeValue(param.getExpression(), ExpressionPackage.Literals.EXPRESSION__CONTENT));

        updateButtons();

        createSQLBuilder();

        return mainComposite;
    }

    @Override
    public void dispose() {
        if (isConnected()) {
            if (profile != null) {
                profile.disconnect();
            }
        }
        if (_sqlBuilder != null) {
            _sqlBuilder.removeContentChangeListener(this);
        }
        final IFile f = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile(BONITA_SQL_TMP_FILE);
        if (f.exists()) {
            try {
                f.delete(true, Repository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        super.dispose();
    }

    private Group createConnectWizardGroup(final Composite parent,
            final EMFDataBindingContext context) {

        final Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.databaseConnection);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 0).create());
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(2, 5).extendedMargins(5, 5, 5, 5).create());

        final Label connectionExplanation = new Label(group, SWT.WRAP);
        connectionExplanation.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        connectionExplanation.setText(Messages.connectionExplanation);

        connectionButton = new Button(group, SWT.PUSH);
        connectionButton.setText(Messages.connect);
        connectionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ConnectionInfoDialog dialog = new ConnectionInfoDialog(Display.getDefault().getActiveShell(),
                        SWT.SHEET,
                        getDefinition().getId(),
                        getDriverClassname(),
                        getJDBCUrl(),
                        getUsername(),
                        getPassword());
                final int status = dialog.open();
                if (status == Dialog.OK) {
                    try {
                        getContainer().run(true, false, new IRunnableWithProgress() {

                            @Override
                            public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                                    InterruptedException {
                                monitor.beginTask(Messages.connecting, IProgressMonitor.UNKNOWN);
                                try {
                                    if (profile != null) {
                                        profile.disconnect();
                                    }
                                    profile = connect(dialog.getClassName(), dialog.getJdbcUrl(), dialog.getUsername(), dialog.getPassword());
                                } catch (final ConnectionProfileException e) {
                                    BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
                                } catch (final ClassNotFoundException e) {
                                    BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
                                } catch (final UnsupportedEncodingException e) {
                                    BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
                                }
                            }

                        });
                        if (isConnected()) {
                            boolean update = true;
                            if (showConfirmation(dialog.getClassName(), dialog.getJdbcUrl(), dialog.getUsername(), dialog.getPassword())) {
                                if (!MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.updateConfigurationTitle,
                                        Messages.updateConfigurationMsg)) {
                                    update = false;
                                }
                            }
                            if (update) {
                                final Expression dExp = (Expression) getDriverclassNameParameter().getExpression();
                                if (dExp.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                                    dExp.setContent(dialog.getClassName());
                                    dExp.setName(dialog.getClassName());
                                }
                                final Expression urlExp = (Expression) getJDBCUrlParameter().getExpression();
                                if (urlExp.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                                    urlExp.setContent(dialog.getJdbcUrl());
                                    urlExp.setName(dialog.getJdbcUrl());
                                }
                                final Expression userExp = (Expression) getUsernameParameter().getExpression();
                                if (userExp.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                                    userExp.setContent(dialog.getUsername());
                                    userExp.setName(dialog.getUsername());
                                }
                                final Expression passwordExp = (Expression) getPasswordParameter().getExpression();
                                if (passwordExp.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                                    passwordExp.setContent(dialog.getPassword());
                                    passwordExp.setName(dialog.getPassword());
                                }
                            }
                        }
                        updateButtons();
                        getContainer().updateButtons();
                    } catch (final InvocationTargetException e1) {
                        BonitaStudioLog.error(e1, SQLBuilderExPlugin.PLUGIN_ID);
                    } catch (final InterruptedException e1) {
                        BonitaStudioLog.error(e1, SQLBuilderExPlugin.PLUGIN_ID);
                    }

                }
            }
        });

        disconnectButton = new Button(group, SWT.PUSH);
        disconnectButton.setText(Messages.disconnect);
        disconnectButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (profile != null) {
                    profile.disconnect();
                }
                updateButtons();
                getContainer().updateButtons();
            }
        });
        return group;
    }

    protected boolean showConfirmation(final String driverClassName, final String jdbcUrl, final String username, final String password) {
        boolean atLeastOnExpressionisConstant = false;
        final Expression dExp = (Expression) getDriverclassNameParameter().getExpression();
        if (dExp != null
                && ExpressionConstants.CONSTANT_TYPE.equals(dExp.getType())
                && driverClassName != null
                && !driverClassName.equals(dExp.getContent())) {
            atLeastOnExpressionisConstant = true;
        }
        final Expression urlExp = (Expression) getJDBCUrlParameter().getExpression();
        if (urlExp != null
                && ExpressionConstants.CONSTANT_TYPE.equals(urlExp.getType())
                && jdbcUrl != null
                && !jdbcUrl.equals(urlExp.getContent())) {
            atLeastOnExpressionisConstant = true;
        }
        final Expression userExp = (Expression) getUsernameParameter().getExpression();
        if (userExp != null
                && ExpressionConstants.CONSTANT_TYPE.equals(userExp.getType())
                && username != null
                && !username.equals(userExp.getContent())) {
            atLeastOnExpressionisConstant = true;
        }
        final Expression passwordExp = (Expression) getPasswordParameter().getExpression();
        if (passwordExp != null
                && ExpressionConstants.CONSTANT_TYPE.equals(passwordExp.getType())
                && password != null
                && !password.equals(passwordExp.getContent())) {
            atLeastOnExpressionisConstant = true;
        }
        return atLeastOnExpressionisConstant;
    }

    protected void updateButtons() {
        if (useGraphical.getSelection()) {
            final boolean isConnected = isConnected();
            disconnectButton.setEnabled(isConnected);
            connectionButton.setEnabled(!isConnected);
        }
        if (!group.isEnabled()) {
            disconnectButton.setEnabled(false);
            connectionButton.setEnabled(false);
        }

    }

    protected IConnectionProfile connect(final String className, final String jdbcUrl, final String username, final String password)
            throws ConnectionProfileException, ClassNotFoundException, UnsupportedEncodingException {
        final IConnectionProfile profile = getConnectionProfile(className, jdbcUrl, username, password);
        final IStatus connectionStatus = profile.connectWithoutJob();
        if (connectionStatus.isOK() && profile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.connect, Messages.connectionSuccessMsg);
                }
            });

        } else {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    if (connectionStatus.isOK()) {
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.connect, Messages.connectionFailedMsg, new Status(IStatus.ERROR,
                                SQLBuilderExPlugin.PLUGIN_ID, Messages.connectionFailedMsg), IStatus.ERROR).open();
                    } else {
                        IStatus errorStaus = connectionStatus;
                        if (connectionStatus instanceof MultiStatus) {
                            if (((MultiStatus) connectionStatus).getChildren().length > 0) {
                                errorStaus = new Status(IStatus.ERROR, ((MultiStatus) connectionStatus).getChildren()[0].getPlugin(),
                                        ((MultiStatus) connectionStatus).getChildren()[0].getMessage());

                            }
                        }
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.connect, Messages.connectionFailedMsg, errorStaus, IStatus.ERROR
                                | IStatus.WARNING).open();
                    }

                }
            });
            return null;
        }

        final IFile f = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile(BONITA_SQL_TMP_FILE); //$NON-NLS-1$
        String currentQuery = getQuery();
        if (currentQuery == null) {
            currentQuery = "";
        }
        ByteArrayInputStream is = null;
        final byte[] bytes = currentQuery.toString().getBytes();
        is = new ByteArrayInputStream(bytes);

        try {
            if (f.exists()) {
                f.delete(true, null);
            }
            f.create(is, IResource.FORCE, null);

        } catch (final CoreException e1) {
            BonitaStudioLog.error(e1);
        } finally {
            try {
                is.close();
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }

        final SQLBuilderStorageEditorInput editorInput = EditorInputUtil.createSQLBuilderStorageEditorInputFromStringViaFile(f);
        final OmitSchemaInfo schemaInfo = new OmitSchemaInfo();
        schemaInfo.setOmitCurrentSchema(true);
        editorInput.setOmitSchemaInfo(schemaInfo);
        final Properties connectionProperties = profile
                .getBaseProperties();
        editorInput.setConnectionInfo(new SQLEditorConnectionInfo(
                new DatabaseVendorDefinitionId(connectionProperties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID),
                        connectionProperties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID)),
                ConnectionProfileUtil.DEFAULT_PROFILE_NAME, connectionProperties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID)));
        try {
            _sqlBuilder.setInput(editorInput);
        } catch (final PartInitException e) {
            BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
        } catch (final ParseException e) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.unparsableQueryTitle, Messages.unparsableQueryMsg, e).open();
                }
            });
            BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
        }
        return profile;
    }

    protected void createSQLBuilder() {
        if (_sqlBuilder == null) {
            _sqlBuilder = new BonitaSQLBuilder(getFilteredExpressions());
            _sqlBuilder.setLoadOnConnection(true);
            _sqlBuilder.addContentChangeListener(this);
        }
    }

    public BonitaSQLBuilder getSqlBuilder() {
        return _sqlBuilder;
    }

    protected String getQuery() {
        final Input input = getInput(SCRIPT_INPUT);
        if (scriptExp == null) {
            scriptExp = (Expression) getConnectorParameter(input).getExpression();
            if (!ExpressionConstants.PATTERN_TYPE.equals(scriptExp.getType())) {
                scriptExp.setType(ExpressionConstants.PATTERN_TYPE);
            }
            if (scriptExp.getName() == null) {
                scriptExp.setName("query");
            }
        }
        return scriptExp.getContent();
    }

    @Override
    public boolean canFlipToNextPage() {
        updateButtons();
        if (useTextual.getSelection()) {
            return true;
        } else {
            return isConnected();
        }
    }

    public boolean isConnected() {
        if (profile != null) {
            return profile.getConnectionState() == IConnectionProfile.CONNECTED_STATE;
        }
        return false;
    }

    private ConnectorParameter getPasswordParameter() {
        final Input input = getInput(PASSWORD_INPUT);
        return getConnectorParameter(input);
    }

    private ConnectorParameter getUsernameParameter() {
        final Input input = getInput(USERNAME_INPUT);
        return getConnectorParameter(input);
    }

    private ConnectorParameter getJDBCUrlParameter() {
        final Input input = getInput(JDBC_URL_INPUT);
        return getConnectorParameter(input);
    }

    private ConnectorParameter getDriverclassNameParameter() {
        final Input input = getInput(DRIVER_CLASSNAME_INPUT);
        return getConnectorParameter(input);
    }

    private ConnectorParameter getAdvancedModeParameter() {
        final Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName(MODE_PARAMETER);
        input.setType(Boolean.class.getName());
        input.setMandatory(false);
        input.setDefaultValue(Boolean.TRUE.toString());
        return getConnectorParameter(input);
    }

    protected String getPassword() {
        final Expression exp = (Expression) getPasswordParameter().getExpression();
        if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())) {
            return exp.getContent();
        }
        return null;
    }

    protected String getUsername() {
        final Expression exp = (Expression) getUsernameParameter().getExpression();
        if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())) {
            return exp.getContent();
        }
        return null;
    }

    protected String getJDBCUrl() {
        final Expression exp = (Expression) getJDBCUrlParameter().getExpression();
        if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())) {
            return exp.getContent();
        }
        return null;
    }

    protected String getDriverClassname() {
        final Expression exp = (Expression) getDriverclassNameParameter().getExpression();
        if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())) {
            return exp.getContent();
        }
        return null;
    }

    public abstract IConnectionProfile getConnectionProfile(String className, String jdbcUrl, String username, String password)
            throws ConnectionProfileException, ClassNotFoundException, UnsupportedEncodingException;

    public boolean useQueryBuilder() {
        return useGraphical.getSelection();
    }

    @Override
    public void notifyContentChange() {
        scriptExp.setContent(_sqlBuilder.getSourceViewer().getText());
        final IDocument document = new Document();
        document.set(scriptExp.getContent());
        groovyExpressionPartitioner = new GroovyExpressionPartitioner();
        groovyExpressionPartitioner.connect(document);
        document.setDocumentPartitioner(groovyExpressionPartitioner);
        final PatternExpressionModelBuilder patternExpressionModelBuilder = new PatternExpressionModelBuilder();
        patternExpressionModelBuilder.setScope(getFilteredExpressions());
        patternExpressionModelBuilder.setExpression(scriptExp);
        patternExpressionModelBuilder.documentChanged(new DocumentEvent(document, 0, document.getLength(), document.get()));
        groovyExpressionPartitioner.disconnect();
        getContainer().updateButtons();
    }

    private List<Expression> getFilteredExpressions() {
        final List<Expression> filteredExpressions = new ArrayList<Expression>();
        if (expressionNatureProvider != null) {
            final Expression[] expressions = expressionNatureProvider.getExpressions(getElementContainer());
            final EObject input = getElementContainer();
            if (expressions != null) {
                filteredExpressions.addAll(Arrays.asList(expressions));
                if (input != null) {
                    final ViewerInput viewer = new ViewerInput(getConfiguration());
                    for (final Expression exp : expressions) {
                        final AvailableExpressionTypeFilter filter = getExpressionTypeFilter();
                        if (filter != null && !filter.select(viewer, input, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
            Collections.sort(filteredExpressions, new Comparator<Expression>() {

                @Override
                public int compare(final Expression exp0, final Expression exp1) {
                    return -Integer.valueOf(exp0.getName().length()).compareTo(Integer.valueOf(exp1.getName().length()));
                }

            });
        }
        return filteredExpressions;

    }

    protected String parseDatabaseFromURL(final String jdbcURL) throws UnsupportedEncodingException {
        if (jdbcURL == null || jdbcURL.length() <= 5 || jdbcURL.contains("\\")) {
            return null;
        }

        final JDBCURLSplitter splitter = new JDBCURLSplitter(jdbcURL);
        final String database = splitter.database;
        return database != null && !database.isEmpty() ? database : null;
    }

}
