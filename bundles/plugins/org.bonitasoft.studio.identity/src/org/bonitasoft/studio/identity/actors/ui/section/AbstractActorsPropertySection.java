/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.actors.ui.section;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigrator;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationToConnectorDefinitionConverter;
import org.bonitasoft.studio.connectors.ui.property.section.SelectDefinitionVersionDialog;
import org.bonitasoft.studio.connectors.ui.provider.StyledConnectorLabelProvider;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.ui.wizard.ActorFilterDefinitionWizardDialog;
import org.bonitasoft.studio.identity.actors.ui.wizard.AddActorWizard;
import org.bonitasoft.studio.identity.actors.ui.wizard.FilterWizard;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractActorsPropertySection extends AbstractBonitaDescriptionSection
        implements ISelectionChangedListener {

    public static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";

    protected ComboViewer actorComboViewer;
    protected EMFDataBindingContext emfDatabindingContext;
    protected StyledText filterText;
    protected ToolItem removeConnectorButton;
    protected Button updateConnectorButton;
    private StyledConnectorLabelProvider filterLabelProvider;
    protected Button setButton;
    private CommandExecutor commandExecutor = new CommandExecutor();
    private RepositoryAccessor repositoryAccessor;
    private ConnectorConfigurationMigratorFactory migrationFactory;
    private ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter;

    @Inject
    public AbstractActorsPropertySection(ConnectorConfigurationMigratorFactory migrationFactory,
            ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter,
            RepositoryAccessor repositoryAccessor) {
        this.migrationFactory = migrationFactory;
        this.configurationToDefinitionConverter = configurationToDefinitionConverter;
        this.repositoryAccessor = repositoryAccessor;

    }

    @Override
    protected void createContent(Composite parent) {
        TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

        Composite mainComposite = widgetFactory.createComposite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).extendedMargins(0, 25, 0, 25)
                .spacing(10, 15).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createRadioComposite(widgetFactory, mainComposite);

        createActorComposite(widgetFactory, mainComposite);

        filterLabelProvider = new StyledConnectorLabelProvider(
                repositoryAccessor.getRepositoryStore(ActorFilterDefRepositoryStore.class));

        createFilterSection(widgetFactory, mainComposite);

        updateDatabinding();
    }

    private void createFilterSection(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent) {
        var filterSection = widgetFactory.createSection(parent, Section.EXPANDED);
        filterSection.setLayout(GridLayoutFactory.fillDefaults().create());
        filterSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        filterSection.setText(Messages.actorFilter);

        var client = widgetFactory.createComposite(filterSection, SWT.NONE);
        client.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 10).create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var viewerComposite = getWidgetFactory().createPlainComposite(client, SWT.NONE);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());

        createFiltersViewer(viewerComposite);

        var separator = widgetFactory.createLabel(client, "", SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0, 10).create());

        createMarketplaceButton(client);

        filterSection.setClient(client);
    }

    private void createMarketplaceButton(Composite parent) {
        var parameters = new HashMap<String, Object>();
        parameters.put("types", Messages.actorFilterType);

        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .withLayoutData(
                        GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).create())
                .withToolkit(getWidgetFactory())
                .onClick(e -> commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, parameters))
                .createIn(parent);
    }

    private void createActorComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent) {
        var composite = widgetFactory.createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var actorsLabel = widgetFactory.createLabel(composite, Messages.selectActor);
        actorsLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        createActorComboViewer(composite);
        createAddActorButton(composite);
    }

    private void createAddActorButton(final Composite mainComposite) {
        final Button addActor = new Button(mainComposite, SWT.FLAT);
        addActor.setText(Messages.addActor);
        addActor.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final AddActorWizard actorWizard = new AddActorWizard(getEObject(), getEditingDomain());
                final WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), actorWizard);
                if (wizardDialog.open() == Dialog.OK) {
                    if (actorWizard.getNewActor() != null) {
                        actorComboViewer.setSelection(new StructuredSelection(actorWizard.getNewActor()));
                    }
                }
            }
        });
    }

    private void createActorComboViewer(final Composite mainComposite) {
        actorComboViewer = new ComboViewer(mainComposite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        actorComboViewer.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        actorComboViewer.setContentProvider(new ArrayContentProvider());
        actorComboViewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Actor) {
                    String doc = ((Actor) element).getDocumentation();
                    if (doc != null && !doc.isEmpty()) {
                        doc = " -- " + doc;
                    } else {
                        doc = "";
                    }
                    return ((Actor) element).getName() + doc;
                }
                return super.getText(element);
            }
        });
    }

    protected void createFiltersViewer(Composite parent) {
        setButton = createSetButton(parent);

        filterText = new StyledText(parent, SWT.BORDER | SWT.SINGLE | SWT.NO_FOCUS | SWT.READ_ONLY);
        filterText.setEnabled(false);
        getWidgetFactory().adapt(filterText, false, false);
        filterText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        updateConnectorButton = createUpdateButton(parent);
        removeConnectorButton = createRemoveButton(parent);
    }

    protected EStructuralFeature getFilterFeature() {
        return ProcessPackage.Literals.ASSIGNABLE__FILTERS;
    }

    protected ToolItem createRemoveButton(final Composite buttonsComposite) {
        final ToolBar toolBar = new ToolBar(buttonsComposite, SWT.FLAT | SWT.NO_FOCUS);
        getWidgetFactory().adapt(toolBar);
        final ToolItem toolItem = new ToolItem(toolBar, SWT.FLAT);
        toolItem.setImage(Pics.getImage(PicsConstants.clear));
        toolItem.setToolTipText(Messages.remove);
        toolItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.deleteDialogTitle,
                        createMessage())) {
                    final Assignable assignable = (Assignable) getEObject();
                    final ActorFilter filter = assignable.getFilters().get(0);
                    getEditingDomain().getCommandStack()
                            .execute(new RemoveCommand(getEditingDomain(), getEObject(), getFilterFeature(), filter));
                    filterText.setText("");
                    updateButtons();
                }

            }

            public String createMessage() {
                final StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
                res.append(' ');
                final Assignable assignable = (Assignable) getEObject();
                res.append(assignable.getFilters().get(0).getName());

                res.append(" ?"); //$NON-NLS-1$
                return res.toString();
            }
        });
        return toolItem;
    }

    protected Button createUpdateButton(final Composite buttonsComposite) {
        final Button updateButton = getWidgetFactory().createButton(buttonsComposite, Messages.edit, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final Assignable assignable = (Assignable) getEObject();
                final ActorFilter filter = assignable.getFilters().get(0);
                final ActorFilterDefRepositoryStore defStore = RepositoryManager.getInstance()
                        .getRepositoryStore(ActorFilterDefRepositoryStore.class);
                final ConnectorDefinition def = defStore.getDefinition(filter.getDefinitionId(),
                        filter.getDefinitionVersion());
                ConnectorConfigurationMigrator migrator = null;
                if (def == null) {
                    migrator = updateFilterDefinition(filter, defStore);
                }
                if (def != null || migrator != null) {
                    final WizardDialog wizardDialog = new ActorFilterDefinitionWizardDialog(
                            Display.getCurrent().getActiveShell(),
                            new FilterWizard(filter, getFilterFeature(), getFilterFeatureToCheckUniqueID(), migrator));
                    if (wizardDialog.open() == Dialog.OK) {
                        final Assignable newAssignable = (Assignable) getEObject();
                        final ActorFilter newfilter = newAssignable.getFilters().get(0);
                        updateFilterTextContent(newfilter);
                        updateButtons();
                    }
                }
            }

        });
        return updateButton;
    }

    private void updateFilterTextContent(ActorFilter newfilter) {
        StyledString styledString = filterLabelProvider.getStyledString(newfilter);
        filterText.setText(styledString.getString());
        filterText.setStyleRanges(styledString.getStyleRanges());
    }

    private ConnectorConfigurationMigrator updateFilterDefinition(Connector connector,
            ActorFilterDefRepositoryStore defStore) {
        List<ConnectorDefinition> otherDefinitions = defStore.getDefinitions().stream()
                .filter(definition -> Objects.equals(definition.getId(), connector.getDefinitionId()))
                .collect(Collectors.toList());
        ConnectorDefinition targetDefinition = null;
        if (otherDefinitions.size() > 1) {
            // Choose definitions
            SelectDefinitionVersionDialog selectDefinitionVersionDialog = new SelectDefinitionVersionDialog(
                    Display.getDefault().getActiveShell(), otherDefinitions);
            if (selectDefinitionVersionDialog.open() == IDialogConstants.NEXT_ID) {
                String targetVersion = selectDefinitionVersionDialog.getVersion();
                targetDefinition = otherDefinitions.stream()
                        .filter(def -> Objects.equals(def.getVersion(), targetVersion))
                        .findFirst()
                        .orElseThrow();
            }
        } else if (!otherDefinitions.isEmpty()) {
            targetDefinition = otherDefinitions.get(0);
        }
        if (targetDefinition != null) {
            return migrationFactory.create(configurationToDefinitionConverter.convert(connector), targetDefinition);
        }
        return null;
    }

    protected Set<EStructuralFeature> getFilterFeatureToCheckUniqueID() {
        final Set<EStructuralFeature> res = new HashSet<>();
        res.add(getFilterFeature());
        return res;
    }

    protected Button createSetButton(final Composite buttonsComposite) {
        final Button setButton = getWidgetFactory().createButton(buttonsComposite, Messages.set, SWT.FLAT);
        setButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        setButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                try {
                    PlatformUI.getWorkbench().getProgressService().busyCursorWhile(monitor -> Job.getJobManager()
                            .join(ProjectDependenciesStore.ANALYZE_PPROJECT_DEPENDENCIES_FAMILY, monitor));
                } catch (InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
                var registry = repositoryAccessor.getRepositoryStore(ActorFilterDefRepositoryStore.class)
                        .getResourceProvider().getConnectorDefinitionRegistry();
                if (registry.getDefinitions().isEmpty()) {
                    MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                            Messages.noActorFilterInstalled,
                            Messages.installActorFilterExtensionMsg);
                    var parameters = new HashMap<String, Object>();
                    parameters.put("types", Messages.actorFilterType);
                    commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, parameters);
                } else {
                    final WizardDialog wizardDialog = new ActorFilterDefinitionWizardDialog(
                            Display.getCurrent().getActiveShell(),
                            new FilterWizard(getEObject(), getFilterFeature(), getFilterFeatureToCheckUniqueID()));
                    if (wizardDialog.open() == Dialog.OK) {
                        final Assignable assignable = (Assignable) getEObject();
                        if (assignable.getFilters().size() > 1) {
                            getEditingDomain().getCommandStack()
                                    .execute(RemoveCommand.create(getEditingDomain(), assignable,
                                            assignable.getFilters(), assignable.getFilters().get(0)));
                        }
                        if (!assignable.getFilters().isEmpty()) {
                            final ActorFilter filter = assignable.getFilters().get(0);
                            updateFilterTextContent(filter);
                        }
                        updateButtons();
                    }
                }
            }
        });
        return setButton;
    }

    protected abstract void createRadioComposite(TabbedPropertySheetWidgetFactory widgetFactory,
            Composite mainComposite);

    @Override
    public void refresh() {
        super.refresh();
        if (filterText != null && !filterText.isDisposed()) {
            final Assignable assignable = (Assignable) getEObject();
            if (assignable != null && !assignable.getFilters().isEmpty()) {
                final ActorFilter filter = assignable.getFilters().get(0);
                updateFilterTextContent(filter);
            } else {
                filterText.setText("");
            }
            updateButtons();
        }
        updateDatabinding();
    }

    protected void updateDatabinding() {
        final Assignable assignable = (Assignable) getEObject();
        if (assignable != null) {
            if (emfDatabindingContext != null) {
                emfDatabindingContext.dispose();
            }
            emfDatabindingContext = new EMFDataBindingContext();
            final AbstractProcess process = ModelHelper.getParentProcess(assignable);
            if (process != null) {
                emfDatabindingContext.bindValue(ViewersObservables.observeInput(actorComboViewer),
                        EMFObservables.observeValue(process, ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS));
                emfDatabindingContext.bindValue(ViewersObservables.observeSingleSelection(actorComboViewer),
                        EMFEditObservables.observeValue(getEditingDomain(), assignable,
                                ProcessPackage.Literals.ASSIGNABLE__ACTOR));
            }
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons();
    }

    private void updateButtons() {
        if (filterText != null) {
            final Assignable assignable = (Assignable) getEObject();
            ActorFilter filter = null;
            if (!assignable.getFilters().isEmpty()) {
                filter = assignable.getFilters().get(0);
            }

            if (!removeConnectorButton.isDisposed()) {
                removeConnectorButton.setEnabled(filter != null);
            }

            if (!updateConnectorButton.isDisposed()) {
                if (filter != null) {
                    var defStore = repositoryAccessor.getRepositoryStore(ActorFilterDefRepositoryStore.class);
                    var definitionId = filter.getDefinitionId();
                    var def = defStore.getDefinition(definitionId, filter.getDefinitionVersion());
                    var nbOfDefinitions = defStore.getDefinitions().stream()
                            .filter(definition -> Objects.equals(definition.getId(), definitionId))
                            .count();
                    updateConnectorButton.setEnabled(def != null || nbOfDefinitions > 0);
                    updateConnectorButton.setText(def == null && nbOfDefinitions > 0
                            ? org.bonitasoft.studio.connectors.i18n.Messages.update
                            : Messages.edit);
                } else {
                    updateConnectorButton.setEnabled(false);
                }

            }
        }
    }

    @Override
    public String getSectionDescription() {
        final EObject selectedEobject = getEObject();
        if (selectedEobject instanceof AbstractProcess) {
            return Messages.addRemoveActors;
        } else if (selectedEobject instanceof Lane) {
            return Messages.actorDescriptionLane;
        } else {
            return Messages.actorDescriptionTask;
        }
    }
}
