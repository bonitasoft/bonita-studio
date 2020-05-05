/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.IConnectorDefinitionContainer;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.extension.CustomWizardExtension;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.ConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorConstants;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorDriversWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectAdvancedConnectorDefinitionWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectDatabaseOutputTypeWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectEventConnectorNameAndDescWizardPage;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Preconditions;

/**
 * @author Romain Bioteau
 */
public class ConnectorWizard extends ExtensibleWizard implements
        IConnectorDefinitionContainer, IBonitaVariableContext {

    private static final String CUSTOM_WIZARD_ID = "org.bonitasoft.studio.connectors.connectorWizard";

    private static final String DATABASE_ID = "database";

    private static final String DATASOURCE_CONNECTOR_D = "database-datasource";

    protected final EObject container;

    protected Connector connectorWorkingCopy;

    private boolean editMode = false;

    protected Connector originalConnector;

    protected final Set<EStructuralFeature> featureToCheckForUniqueID;

    protected final EStructuralFeature connectorContainmentFeature;

    protected AbstractDefinitionSelectionImpementationWizardPage selectionPage;

    protected DefinitionResourceProvider messageProvider;

    protected CustomWizardExtension extension;

    private boolean isPageFlowContext = false;

    private List<CustomWizardExtension> contributions;

    private boolean useEvents = true;

    private final AvailableExpressionTypeFilter expressionTypeFilter = new ConnectorAvailableExpressionTypeFilter();

    private EMFModelUpdater<Connector> modelUpdater = new EMFModelUpdater<>();

    public ConnectorWizard(final EObject container,
            final EStructuralFeature connectorContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID) {
        this.container = container;
        connectorWorkingCopy = ProcessFactory.eINSTANCE.createConnector();
        final ConnectorConfiguration configuration = ConnectorConfigurationFactory.eINSTANCE
                .createConnectorConfiguration();
        configuration.setModelVersion(ModelVersion.CURRENT_VERSION);
        connectorWorkingCopy.setConfiguration(configuration);
        editMode = false;
        this.connectorContainmentFeature = connectorContainmentFeature;
        this.featureToCheckForUniqueID = new HashSet<>();
        this.featureToCheckForUniqueID.add(connectorContainmentFeature);
        setWindowTitle(Messages.connectors);
        setNeedsProgressMonitor(false);

    }

    public ConnectorWizard(final Connector connector,
            final EStructuralFeature connectorContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID) {
        Assert.isNotNull(connector);
        container = connector.eContainer();
        originalConnector = connector;
        this.connectorContainmentFeature = connectorContainmentFeature;
        connectorWorkingCopy = modelUpdater.from(connector).getWorkingCopy();
        editMode = true;
        this.featureToCheckForUniqueID = featureToCheckForUniqueID;
        setNeedsProgressMonitor(false);
    }

    /**
     * @param eObject
     * @param connectorFeature
     * @param connectorFeatureToCheckUniqueID
     * @param connectorEvent
     */
    public ConnectorWizard(final EObject eObject,
            final EStructuralFeature connectorFeature,
            final Set<EStructuralFeature> connectorFeatureToCheckUniqueID,
            final String connectorEvent) {

        this(eObject, connectorFeature, connectorFeatureToCheckUniqueID);
        Preconditions.checkArgument(connectorEvent
                .equals(ConnectorEvent.ON_FINISH.name())
                || connectorEvent.equals(ConnectorEvent.ON_ENTER.name()));
        connectorWorkingCopy.setEvent(connectorEvent);
    }

    protected void setEditMode(final boolean isEdit) {
        editMode = isEdit;
    }

    protected void initialize() {
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        messageProvider = initMessageProvider();

        initializeContainment();

        contributions = new ArrayList<>();
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager
                .getInstance().getConfigurationElements(CUSTOM_WIZARD_ID)) {
            contributions.add(new CustomWizardExtension(element));
        }
        final ConnectorDefinition def = getDefinition();
        final DefinitionResourceProvider resourceProvider = initMessageProvider();
        String connectorDefinitionLabel = resourceProvider
                .getConnectorDefinitionLabel(def);
        if (connectorDefinitionLabel == null && def != null) {
            connectorDefinitionLabel = def.getId();
        }
        if (connectorDefinitionLabel != null) {
            setWindowTitle(connectorDefinitionLabel + " (" + def.getVersion()
                    + ")");
        }
    }

    protected void initializeContainment() {
        if (container instanceof Element) {
            final AbstractProcess process = ModelHelper
                    .getParentProcess(container);
            final EObject processCopy = EcoreUtil.copy(process);
            EObject containerCopy = null;
            for (final EObject element : ModelHelper.getAllItemsOfType(
                    processCopy, container.eClass())) {
                if (element instanceof Element && container instanceof Element) {
                    final String containerName = ((Element) container)
                            .getName();
                    if (((Element) element).getName().equals(containerName)) {
                        containerCopy = element;
                        break;
                    }
                }
            }

            @SuppressWarnings("unchecked")
            final List<EObject> connectors = (List<EObject>) containerCopy
                    .eGet(connectorContainmentFeature);
            connectors.clear();
            connectors.add(connectorWorkingCopy);
        }
    }

    protected DefinitionResourceProvider initMessageProvider() {
        final IRepositoryStore<? extends IRepositoryFileStore> store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        return DefinitionResourceProvider.getInstance(store, ConnectorPlugin
                .getDefault().getBundle());
    }

    @Override
    public void addPages() {
        initialize();
        if (!editMode) {
            selectionPage = getSelectionPage(connectorWorkingCopy,
                    messageProvider);
            addPage(selectionPage);
        }
        final IWizardPage nameAndDescriptionPage = getNameAndDescriptionPage();
        if (nameAndDescriptionPage != null) {
            addPage(nameAndDescriptionPage);
        }

        if (editMode) {
            final IDefinitionRepositoryStore definitionStore = getDefinitionStore();
            final ConnectorDefinition definition = definitionStore
                    .getDefinition(connectorWorkingCopy.getDefinitionId(),
                            connectorWorkingCopy.getDefinitionVersion());
            final AbstractDefFileStore fStore = (AbstractDefFileStore) ((AbstractDefinitionRepositoryStore<?>) definitionStore)
                    .getChild(URI.decode(definition.eResource().getURI()
                            .lastSegment()), true);
            if (!fStore.isReadOnly() && cleanConfiguration(definition)) {
                MessageDialog.openWarning(
                        Display.getDefault().getActiveShell(),
                        Messages.configurationChangedTitle,
                        Messages.configurationChangedMsg);
            }
            extension = findCustomWizardExtension(definition);
            final List<IWizardPage> pages = getPagesFor(definition);
            for (final IWizardPage p : pages) {
                addAdditionalPage(p);
            }
            addOuputPage(definition);
        }else {
            selectionPage.addConnectorDefinitionFilter(new DeprecatedConnectorViewerFilter());
        }

    }

    /**
     * @param definition
     * @return true if configuration has been modified
     */
    protected boolean cleanConfiguration(final ConnectorDefinition definition) {
        final ConnectorConfiguration configuration = connectorWorkingCopy
                .getConfiguration();
        boolean changed = false;
        if (configuration != null) {
            final EList<Input> inputs = definition.getInput();
            final Set<String> inputNames = new HashSet<>();
            for (final Input in : inputs) {
                inputNames.add(in.getName());
            }
            final Set<String> connectorParamKey = new HashSet<>();
            for (final ConnectorParameter parameter : configuration
                    .getParameters()) {
                connectorParamKey.add(parameter.getKey());
            }

            if (!inputNames.equals(connectorParamKey)) {
                connectorParamKey.removeAll(inputNames);
                final List<ConnectorParameter> toRemove = new ArrayList<>();
                for (final ConnectorParameter parameter : configuration
                        .getParameters()) {
                    if (connectorParamKey.contains(parameter.getKey())) {
                        toRemove.add(parameter);
                    }
                }
                if (!toRemove.isEmpty()) {
                    changed = configuration.getParameters().removeAll(toRemove);
                }
            }
        }
        final EList<Output> outputs = definition.getOutput();
        final Set<String> outputNames = new HashSet<>();
        for (final Output out : outputs) {
            outputNames.add(out.getName());
        }
        for (final Operation op : connectorWorkingCopy.getOutputs()) {
            if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(op
                    .getRightOperand().getType())
                    && op.getRightOperand() != null
                    && op.getRightOperand().getContent() != null
                    && !outputNames.contains(op.getRightOperand().getContent())) {
                op.setRightOperand(ExpressionHelper.createConstantExpression(
                        "", String.class.getName()));
                changed = true;
            }
        }
        return changed;
    }

    protected AbstractDefinitionSelectionImpementationWizardPage getSelectionPage(
            final Connector connectorWorkingCopy,
            final DefinitionResourceProvider resourceProvider) {
        return new SelectAdvancedConnectorDefinitionWizardPage(
                connectorWorkingCopy,
                Collections.<ConnectorImplementation> emptyList(), getDefinitionStore().getDefinitions(),
                Messages.selectConnectorDefinitionTitle,
                Messages.selectConnectorDefinitionDesc, resourceProvider);
    }

    protected IWizardPage getNameAndDescriptionPage() {
        return useEvents ? new SelectEventConnectorNameAndDescWizardPage(container,
                connectorWorkingCopy, originalConnector,
                featureToCheckForUniqueID)
                : new SelectNameAndDescWizardPage(container,
                        connectorWorkingCopy, originalConnector,
                        featureToCheckForUniqueID);
    }

    public void setUseEvents(final boolean useEvents) {
        this.useEvents = useEvents;
    }

    protected IDefinitionRepositoryStore getDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(
                ConnectorDefRepositoryStore.class);
    }

    protected void addOuputPage(final ConnectorDefinition definition) {
        final IWizardPage outputPage = getOutputPageFor(definition);
        if (outputPage != null) {
            addAdditionalPage(outputPage);
        }
    }

    protected IWizardPage getOutputPageFor(final ConnectorDefinition definition) {
        AbstractConnectorOutputWizardPage outputPage = null;
        if (!definition.getOutput().isEmpty()) {
            if (!editMode && !supportsDatabaseOutputMode(getDefinition())) {
                connectorWorkingCopy.getOutputs().clear();
                createDefaultOutputs(definition);
            }
            if (extension != null && !extension.useDefaultOutputPage()) {
                outputPage = extension.getOutputPage();
            } else {
                if (supportsDatabaseOutputMode(definition)) {
                    outputPage = new DatabaseConnectorOutputWizardPage();
                } else {
                    outputPage = new ConnectorOutputWizardPage();
                }

            }
            outputPage.setMessageProvider(messageProvider);
            outputPage.setElementContainer(container);
            outputPage.setConnector(connectorWorkingCopy);
            outputPage.setDefinition(definition);
        }
        return outputPage;
    }

    protected boolean hasOutputPage() {
        return extension != null && !extension.useDefaultOutputPage()
                && extension.getOutputPage() != null
                || !getDefinition().getOutput().isEmpty();
    }

    protected void createDefaultOutputs(final ConnectorDefinition definition) {
        connectorWorkingCopy.getOutputs().clear();
        for (final Output output : definition.getOutput()) {
            final Operation operation = ExpressionHelper.createDefaultConnectorOutputOperation(output);
            connectorWorkingCopy.getOutputs().add(operation);
        }
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page.equals(selectionPage)) {
            final ConnectorDefinition definition = selectionPage
                    .getSelectedConnectorDefinition();
            if (definition != null) {
                extension = findCustomWizardExtension(definition);
                recreateConnectorConfigurationPages(definition, true);
            }
        } else if (page instanceof DatabaseConnectorDriversWizardPage && selectionPage != null) {
            final ConnectorDefinition definition = selectionPage
                    .getSelectedConnectorDefinition();
            DependencyRepositoryStore store = RepositoryManager.getInstance()
                    .getRepositoryStore(DependencyRepositoryStore.class);
            String defaultDriver = ((DatabaseConnectorDriversWizardPage) page).getDefaultDriver(definition.getId());
            if (defaultDriver != null && store.getChild(defaultDriver, true) == null) {
                URL resource = ConnectorPlugin.getDefault().getBundle().getResource("/drivers/" + defaultDriver);
                if (resource != null) {
                    try (InputStream is = resource.openStream()) {
                        store.importInputStream(defaultDriver, is);
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return super.getNextPage(page);
    }

    protected CustomWizardExtension findCustomWizardExtension(
            final ConnectorDefinition definition) {
        int priority = 0;
        CustomWizardExtension result = null;
        for (final CustomWizardExtension ext : contributions) {
            if (ext.appliesTo(definition) && ext.getPriority() > priority) {
                result = ext;
                priority = ext.getPriority();
            }
        }
        return result;
    }

    @Override
    public void recreateConnectorConfigurationPages(
            final ConnectorDefinition definition,
            final boolean clearConfiguration) {
        if (clearConfiguration) {
            clearConnectorConfiguration(definition);
        }
        final List<IWizardPage> pages = getPagesFor(definition);

        // Remove already generated page in case of return
        removeAllAdditionalPages();
        for (final IWizardPage p : pages) {
            addAdditionalPage(p); // Additional pages control will be created
                                  // lazily by the WizardContainer
        }
        addOuputPage(definition);
        initializeEmptyConnectorConfiguration(definition);
    }

    private void initializeEmptyConnectorConfiguration(
            final ConnectorDefinition definition) {
        final ConnectorConfiguration configuration = connectorWorkingCopy
                .getConfiguration();
        for (final Input input : definition.getInput()) {
            if (getConnectorParameter(configuration, input.getName()) == null) {
                final ConnectorParameter param = ConnectorConfigurationFactory.eINSTANCE
                        .createConnectorParameter();
                param.setKey(input.getName());
                param.setExpression(createExpression(definition, input));
                configuration.getParameters().add(param);
            }
        }
    }

    protected AbstractExpression createExpression(
            final ConnectorDefinition definition, final Input input) {
        final String inputClassName = input.getType();
        WidgetComponent widget = null;
        final List<WidgetComponent> widgets = ModelHelper.getAllItemsOfType(
                definition,
                ConnectorDefinitionPackage.Literals.WIDGET_COMPONENT);
        for (final WidgetComponent w : widgets) {
            if (w.getInputName().equals(input.getName())) {
                widget = w;
                break;
            }
        }
        if (widget instanceof Array) {
            final TableExpression expression = ExpressionFactory.eINSTANCE
                    .createTableExpression();
            return expression;
        } else if (widget instanceof org.bonitasoft.studio.connector.model.definition.List) {
            final ListExpression expression = ExpressionFactory.eINSTANCE
                    .createListExpression();
            return expression;
        } else if (widget != null) {
            final Expression expression = ExpressionFactory.eINSTANCE
                    .createExpression();
            expression.setReturnType(inputClassName);
            expression.setReturnTypeFixed(true);
            expression.setType(ExpressionConstants.CONSTANT_TYPE);
            expression.setName(input.getDefaultValue());
            expression.setContent(input.getDefaultValue());
            if (widget instanceof ScriptEditor) {
                expression.setType(ExpressionConstants.SCRIPT_TYPE);
                expression.setInterpreter(((ScriptEditor) widget)
                        .getInterpreter());
            } else if (widget instanceof TextArea) {
                expression.setType(ExpressionConstants.PATTERN_TYPE);
            }
            return expression;
        }
        return null;
    }

    private ConnectorParameter getConnectorParameter(
            final ConnectorConfiguration configuration, final String inputName) {
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (param.getKey().equals(inputName)) {
                return param;
            }
        }
        return null;
    }

    @Override
    public boolean canFinish() {
        if (extension != null && extension.hasCanFinishProvider()) {
            if (connectorWorkingCopy.getConfiguration() != null) {
                return extension.canFinish(connectorWorkingCopy
                        .getConfiguration());
            }
        }
        if (!isConfigurationValid(getDefinition(),
                connectorWorkingCopy.getConfiguration())) {
            return false;
        }
        if (supportsDatabaseOutputMode(getDefinition())) {
            return super.canFinish()
                    && !connectorWorkingCopy.getOutputs().isEmpty();
        }

        return super.canFinish();
    }

    private boolean isConfigurationValid(final ConnectorDefinition def,
            final ConnectorConfiguration configuration) {
        if (def == null) {
            return false;
        }
        for (final ConnectorParameter parameter : configuration.getParameters()) {
            final Input input = getConnectorInput(def, parameter.getKey());
            if (input != null && input.isMandatory()) {
                if (expressionIsEmpty(parameter.getExpression())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean expressionIsEmpty(final AbstractExpression expression) {
        if (expression == null) {
            return true;
        }
        if (expression instanceof Expression) {
            return ((Expression) expression).getContent() == null
                    || ((Expression) expression).getContent().isEmpty();
        } else if (expression instanceof TableExpression) {
            return ((TableExpression) expression).getExpressions().isEmpty();
        } else if (expression instanceof ListExpression) {
            return ((ListExpression) expression).getExpressions().isEmpty();
        }
        return false;
    }

    private Input getConnectorInput(final ConnectorDefinition def,
            final String inputName) {
        for (final Input input : def.getInput()) {
            if (input.getName().equals(inputName)) {
                return input;
            }
        }
        return null;
    }

    protected void clearConnectorConfiguration(
            final ConnectorDefinition definition) {
        final ConnectorConfiguration configuration = connectorWorkingCopy
                .getConfiguration();
        configuration.getParameters().clear();
    }

    protected List<IWizardPage> getPagesFor(final ConnectorDefinition definition) {
        final List<IWizardPage> result = new ArrayList<>();

        if (isDatabaseConnector(definition)) {// DRIVER SELECTION PAGE
            result.add(new DatabaseConnectorDriversWizardPage(definition
                    .getId()));
        }

        if (extension != null
                && (!extension.hasCanBeUsedProvider() || extension.canBeUsed(
                        definition, connectorWorkingCopy))) { // Extension page
            final List<AbstractConnectorConfigurationWizardPage> advancedPages = extension
                    .getPages();
            for (final AbstractConnectorConfigurationWizardPage p : advancedPages) {
                p.setIsPageFlowContext(isPageFlowContext);
                p.setMessageProvider(messageProvider);
                p.setConfiguration(connectorWorkingCopy.getConfiguration());
                p.setDefinition(definition);
                final int i = advancedPages.indexOf(p);
                if (definition.getPage().size() > i) {
                    p.setPage(definition.getPage().get(i));
                }
                p.setElementContainer(container);
                p.setExpressionTypeFilter(getExpressionTypeFilter());
                result.add(p);
            }

            if (extension.useDefaultGeneratedPages()) {
                for (final Page p : definition.getPage()) {
                    result.add(createDefaultConnectorPage(definition, p));
                }
            }
        } else { // Default page
            for (final Page p : definition.getPage()) {
                result.add(createDefaultConnectorPage(definition, p));
            }
        }

        final boolean alwaysUseScripting = BonitaStudioPreferencesPlugin
                .getDefault()
                .getPreferenceStore()
                .getBoolean(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE);
        if (!alwaysUseScripting && supportsDatabaseOutputMode(definition)) {// OUTPUT
                                                                            // TYPE
                                                                            // SELECTION
                                                                            // PAGE
            final SelectDatabaseOutputTypeWizardPage selectOutputPage = addDatabaseOutputModeSelectionPage(definition);
            result.add(selectOutputPage);
        }
        initializeEmptyConnectorConfiguration(definition);
        return result;
    }

    protected boolean supportsDatabaseOutputMode(final ConnectorDefinition def) {
        boolean containsOutputModeInput = false;
        if (def != null) {
            for (final Input input : def.getInput()) {
                if (DatabaseConnectorConstants.OUTPUT_TYPE_KEY.equals(input
                        .getName())) {
                    containsOutputModeInput = true;
                    break;
                }
            }
        }
        if (containsOutputModeInput) {
            boolean hasSingleOutput = false;
            boolean hasNRowOutput = false;
            boolean hasOneRowOutput = false;
            boolean hasTableOutput = false;
            for (final Output output : def.getOutput()) {
                if (DatabaseConnectorConstants.SINGLE_RESULT_OUTPUT
                        .equals(output.getName())) {
                    hasSingleOutput = true;
                } else if (DatabaseConnectorConstants.NROW_ONECOL_RESULT_OUTPUT
                        .equals(output.getName())) {
                    hasNRowOutput = true;
                } else if (DatabaseConnectorConstants.ONEROW_NCOL_RESULT_OUTPUT
                        .equals(output.getName())) {
                    hasOneRowOutput = true;
                } else if (DatabaseConnectorConstants.TABLE_RESULT_OUTPUT
                        .equals(output.getName())) {
                    hasTableOutput = true;
                }
            }
            return hasSingleOutput && hasNRowOutput && hasOneRowOutput
                    && hasTableOutput;
        }

        return false;
    }

    protected SelectDatabaseOutputTypeWizardPage addDatabaseOutputModeSelectionPage(
            final ConnectorDefinition definition) {
        final SelectDatabaseOutputTypeWizardPage selectOutputPage = new SelectDatabaseOutputTypeWizardPage(
                isEditMode());
        selectOutputPage.setIsPageFlowContext(isPageFlowContext);
        selectOutputPage.setMessageProvider(messageProvider);
        selectOutputPage.setConfiguration(connectorWorkingCopy
                .getConfiguration());
        selectOutputPage.setDefinition(definition);
        selectOutputPage.setElementContainer(container);
        selectOutputPage.setExpressionTypeFilter(getExpressionTypeFilter());
        return selectOutputPage;
    }

    protected IWizardPage createDefaultConnectorPage(
            final ConnectorDefinition def, final Page page) {
        final AbstractConnectorConfigurationWizardPage wizPage = new GeneratedConnectorWizardPage();
        wizPage.setIsPageFlowContext(isPageFlowContext);
        wizPage.setMessageProvider(messageProvider);
        wizPage.setConfiguration(connectorWorkingCopy.getConfiguration());
        wizPage.setDefinition(def);
        wizPage.setElementContainer(container);
        wizPage.setPage(page);
        wizPage.setExpressionTypeFilter(getExpressionTypeFilter());
        return wizPage;
    }

    protected AvailableExpressionTypeFilter getExpressionTypeFilter() {
        return expressionTypeFilter;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final EditingDomain editingDomain = AdapterFactoryEditingDomain
                .getEditingDomainFor(container);
        if (editMode) {
            modelUpdater.update();
        } else {
            editingDomain.getCommandStack().execute(
                    createPerformFinishCommandOnCreation(editingDomain));
        }
        return true;
    }

    protected CompoundCommand createPerformFinishCommandOnCreation(
            final EditingDomain editingDomain) {
        final CompoundCommand cc = new CompoundCommand("Add Connector");
        cc.append(AddCommand.create(editingDomain, container,
                connectorContainmentFeature, connectorWorkingCopy));
        return cc;
    }

    protected CompoundCommand createPerformFinishCommandOnEdition(
            final EditingDomain editingDomain) {
        final List<?> connectorsList = (List<?>) container
                .eGet(connectorContainmentFeature);
        final int index = connectorsList.indexOf(originalConnector);
        final CompoundCommand cc = new CompoundCommand("Update Connector");
        cc.append(RemoveCommand.create(editingDomain, container,
                connectorContainmentFeature, originalConnector));
        cc.append(AddCommand.create(editingDomain, container,
                connectorContainmentFeature, connectorWorkingCopy, index));
        return cc;
    }

    public Connector getOriginalConnector() {
        return originalConnector;
    }

    public Connector getWorkingCopyConnector() {
        return connectorWorkingCopy;
    }

    @Override
    public ConnectorDefinition getDefinition() {
        final IDefinitionRepositoryStore defStore = getDefinitionStore();
        if (originalConnector != null) {
            return defStore.getDefinition(originalConnector.getDefinitionId(),
                    originalConnector.getDefinitionVersion());
        } else {
            if (connectorWorkingCopy.getDefinitionId() != null
                    && !connectorWorkingCopy.getDefinitionId().isEmpty()) {
                return defStore.getDefinition(
                        connectorWorkingCopy.getDefinitionId(),
                        connectorWorkingCopy.getDefinitionVersion());
            }
        }
        return null;
    }

    @Override
    public IWizardPage getPreviousPage(final IWizardPage page) {
        final IWizardPage previousPage = super.getPreviousPage(page);
        if (previousPage != null && previousPage.equals(selectionPage)) {
            return null;
        }
        return previousPage;
    }

    public boolean isEditMode() {
        return editMode;
    }

    private boolean isDatabaseConnector(final ConnectorDefinition def) {
        final List<Category> categories = def.getCategory();
        for (final Category category : categories) {
            if (DATABASE_ID.equals(category.getId())
                    && !def.getId().equals(DATASOURCE_CONNECTOR_D)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext
     * (boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

}
