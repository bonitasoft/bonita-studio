/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ArchiveLocalDependencyInputStreamSupplier;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.validator.DependencyLookupConflictHandler;
import org.bonitasoft.studio.importer.bos.wizard.MessagePanel.MessageControlSupplier;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class ExtensionsPreviewPage implements ControlSupplier {

    private static final String ERROR_PANEL_ID = "error.panel";
    private static final String LOCAL_PANEL_ID = "local.panel";
    private static final String CONFLICT_PANEL_ID = "conflict.panel";
    private static final String MAVEN_REPO_DOC_URL = "https://maven.apache.org/guides/introduction/introduction-to-repositories.html";
    private static final String MAVEN_REPO_CONFIG_DOC_URL = "https://maven.apache.org/settings.html#Repositories";

    private IObservableList<DependencyLookup> dependenciesLookup;
    private ImportBosArchivePage archiveModelSupplier;
    private ExceptionDialogHandler exceptionDialogHandler;
    private Map<String, List<DependencyLookup>> executions = new HashMap<>();
    private ImportArchiveModel importArchiveModel;
    private IObservableList<IRepository> mavenRepositories;
    private DependencyLookupConflictHandler dependencyConflictHandler;
    private CheckboxTableViewer dependenciesViewer;

    public ExtensionsPreviewPage(IObservableList<DependencyLookup> dependenciesLookup,
            IObservableList<IRepository> mavenRepositories,
            ImportBosArchivePage archiveModelSupplier,
            DependencyLookupConflictHandler dependencyConflictHandler,
            ExceptionDialogHandler exceptionDialogHandler) {
        this.dependenciesLookup = dependenciesLookup;
        this.mavenRepositories = mavenRepositories;
        this.archiveModelSupplier = archiveModelSupplier;
        this.dependencyConflictHandler = dependencyConflictHandler;
        this.exceptionDialogHandler = exceptionDialogHandler;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        IWizardPage selectedPage = (IWizardPage) event.getSelectedPage();
        if (Messages.extensionsPreviewTitle.equals(selectedPage.getTitle()) && (importArchiveModel == null
                || !executions
                        .containsKey(archiveModelSupplier.get().getBosArchive().getArchiveFile().getAbsolutePath()))) {
            analyzeDependencies(selectedPage.getWizard().getContainer());
        }
        if (Messages.extensionsPreviewTitle.equals(selectedPage.getTitle()) && dependenciesViewer != null
                && !dependenciesViewer.getControl().isDisposed()) {
            Display.getDefault().asyncExec(() -> dependenciesViewer.refresh());
        }
    }

    private void analyzeDependencies(IWizardContainer wizardContainer) {
        importArchiveModel = archiveModelSupplier.get();
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        Model mavenProject = bosArchive.getMavenProject();
        if (mavenProject == null) {
            executeDependenciesMigrationOperation(wizardContainer, bosArchive);
        } else {
            executeProjectDependenciesLookup(wizardContainer, bosArchive, mavenProject);
        }
        dependencyConflictHandler.resolve();
    }

    private void executeProjectDependenciesLookup(IWizardContainer wizardContainer,
            BosArchive bosArchive,
            Model mavenProject) {
        try {
            ProjectDependenciesLookupOperation operation = new ProjectDependenciesLookupOperation(mavenProject,
                    new ArchiveLocalDependencyInputStreamSupplier(bosArchive));
            mavenRepositories.stream()
                    .map(IRepository::getUrl)
                    .forEach(operation::addRemoteRespository);
            wizardContainer.run(true, false, operation);
            dependenciesLookup.clear();
            dependenciesLookup.addAll(operation.getResult().stream()
                    .map(dl -> {
                        dl.setSelected(true);
                        dl.setUsed(true);
                        return dl;
                    })
                    .collect(Collectors.toList()));
            executions.put(importArchiveModel.getBosArchive().getArchiveFile().getAbsolutePath(),
                    dependenciesLookup);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileLookingUpDependencies, e);
        }
    }

    private void executeDependenciesMigrationOperation(IWizardContainer wizardContainer, BosArchive bosArchive) {
        List<InputStreamSupplier> jars = new ArrayList<>();
        try {
            jars = bosArchive.loadJarInputStreamSuppliers();
            var projectDependenciesMigrationOperation = new ProjectDependenciesMigrationOperation(jars);
            mavenRepositories.stream()
                    .map(IRepository::getUrl)
                    .forEach(projectDependenciesMigrationOperation::addRemoteRespository);
            Set<String> usedDependencies = new HashSet<>();
            Set<String> usedDefinitions = new HashSet<>();
            wizardContainer.run(true, false, monitor -> usedDependencies
                    .addAll(ImportBosArchiveOperation.dependencyUsageAnalysis(importArchiveModel, monitor)));
            wizardContainer.run(true, false, monitor -> usedDefinitions
                    .addAll(ImportBosArchiveOperation.definitionUsageAnalysis(importArchiveModel, monitor)));
            projectDependenciesMigrationOperation
                    .addUsedDependencies(usedDependencies)
                    .addUsedDefinitions(usedDefinitions);
            wizardContainer.run(true, false, projectDependenciesMigrationOperation);
            dependenciesLookup.clear();
            dependenciesLookup.addAll(projectDependenciesMigrationOperation.getResult().stream()
                    .map(dl -> {
                        dl.setSelected(dl.isUsed());
                        return dl;
                    })
                    .collect(Collectors.toList()));
            executions.put(importArchiveModel.getBosArchive().getArchiveFile().getAbsolutePath(),
                    dependenciesLookup);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileLookingUpDependencies, e);
        } finally {
            for (InputStreamSupplier jar : jars) {
                try {
                    jar.close();
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    public boolean hasRunPreview(ImportArchiveModel archiveModel) {
        return executions.containsKey(archiveModel.getBosArchive().getArchiveFile().getAbsolutePath());
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        container.setLayoutData(GridDataFactory.fillDefaults().create());

        var localDepObservable = new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return dependenciesLookup.stream()
                        .map(DependencyLookup::getStatus)
                        .anyMatch(DependencyLookup.Status.LOCAL::equals);
            }
        };

        var notFoundDepObservable = new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return dependenciesLookup.stream()
                        .map(DependencyLookup::getStatus)
                        .anyMatch(DependencyLookup.Status.NOT_FOUND::equals);
            }
        };

        var conflictObservable = dependencyConflictHandler.getConflictObservable();

        localDepObservable.addValueChangeListener(createLocalDependenciesPanelEventHandler(container));
        notFoundDepObservable.addValueChangeListener(createDependenciesPanelEventHandler(container));
        conflictObservable.addValueChangeListener(createConflictingDependenciesPanelEventHandler(container));

        ExpandableComposite repositoriesExpandable = new ExpandableComposite(container, SWT.NONE,
                ExpandableComposite.TWISTIE | ExpandableComposite.NO_TITLE_FOCUS_BOX
                        | ExpandableComposite.CLIENT_INDENT);
        repositoriesExpandable.setLayout(GridLayoutFactory.fillDefaults().create());
        repositoriesExpandable.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        repositoriesExpandable.setText(Messages.remoteRepositories);

        Composite client = new Composite(repositoriesExpandable, SWT.NONE);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().create());

        Link description = new Link(client, SWT.NONE);
        description.setText(Messages.configuredRemoteRepositories);
        description.addListener(SWT.Selection, new OpenSystemBrowserListener(MAVEN_REPO_DOC_URL));
        var repositoriesViewer = new TableViewer(client);
        repositoriesViewer.getControl().setLayoutData(GridDataFactory
                .fillDefaults()
                .grab(true, true)
                .hint(SWT.DEFAULT, 60)
                .create());

        repositoriesViewer.setContentProvider(new ObservableListContentProvider<IRepository>());
        repositoriesViewer.setLabelProvider(new LabelProviderBuilder<IRepository>()
                .withTextProvider(repo -> repo.getId() + ": " + repo.getUrl())
                .createLabelProvider());
        repositoriesViewer.setFilters(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                IRepository repo = (IRepository) element;
                return repo.getMirrorId() == null;
            }
        });

        repositoriesViewer.setInput(mavenRepositories);
        repositoriesExpandable.setClient(client);

        dependenciesViewer = CheckboxTableViewer.newCheckList(container,
                SWT.BORDER | SWT.FULL_SELECTION);
        dependenciesViewer.getControl().setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true)
                .create());
        dependenciesViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_DEPENDENCIES_PREVIEW_TABLE);
        dependenciesViewer.getTable().setHeaderVisible(true);
        dependenciesViewer.setCheckStateProvider(new ICheckStateProvider() {

            @Override
            public boolean isGrayed(Object element) {
                return false;
            }

            @Override
            public boolean isChecked(Object element) {
                return ((DependencyLookup) element).isSelected();
            }
        });
        dependenciesViewer.addCheckStateListener(
                event -> {
                    ((DependencyLookup) event.getElement()).setSelected(event.getChecked());
                    dependencyConflictHandler.updateConflictStatus();
                });
        dependenciesViewer.setContentProvider(new ObservableListContentProvider<DependencyLookup>());
        TableViewerColumn resolutionColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        resolutionColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withImageProvider(this::resolutionIcon)
                .withTooltipProvider(this::resolutionTooltip)
                .createColumnLabelProvider());

        TableViewerColumn groupIdColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        groupIdColumn.getColumn().setText(Messages.groupId);
        groupIdColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withTextProvider(DependencyLookup::getGroupId)
                .createColumnLabelProvider());
        groupIdColumn.setEditingSupport(new EditingSupportBuilder<DependencyLookup>(dependenciesViewer)
                .withCanEditProvider(dep -> dep.getStatus() == Status.LOCAL)
                .withValueProvider(DependencyLookup::getGroupId)
                .withValueUpdater((dep, value) -> dep.setGroupId((String) value))
                .create());

        TableViewerColumn artifactIdColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        artifactIdColumn.getColumn().setText(Messages.artifactId);
        artifactIdColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withTextProvider(DependencyLookup::getArtifactId)
                .createColumnLabelProvider());
        artifactIdColumn.setEditingSupport(new EditingSupportBuilder<DependencyLookup>(dependenciesViewer)
                .withCanEditProvider(dep -> dep.getStatus() == Status.LOCAL)
                .withValueProvider(DependencyLookup::getArtifactId)
                .withValueUpdater((dep, value) -> dep.setArtifactId((String) value))
                .create());

        TableViewerColumn versionColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        versionColumn.getColumn().setText(Messages.version);
        versionColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withTextProvider(this::versionTextProvider)
                .withStatusProvider(dependencyConflictHandler::validate)
                .createColumnLabelProvider());
        versionColumn.setEditingSupport(new EditingSupportBuilder<DependencyLookup>(dependenciesViewer)
                .withCanEditProvider(dep -> dep.getStatus() == Status.LOCAL || dep.getConflictVersion() != null)
                .withValueProvider(this::versionValueProvider)
                .withValueUpdater((dep, value) -> {
                    if (dep.getConflictVersion() != null) {
                        var conflictVersion = dep.getConflictVersion();
                        if (Strings.isNullOrEmpty((String) value)) {
                            conflictVersion.markConflicting();
                        } else if (value.equals(conflictVersion.getOurVersionLabel())) {
                            conflictVersion.keepOurs();
                        } else if (value.equals(conflictVersion.getTheirVersionLabel())) {
                            conflictVersion.keepTheir();
                        }
                        dependencyConflictHandler.updateConflictStatus();
                    } else {
                        dep.setVersion((String) value);
                        dependencyConflictHandler.resolve();
                    }
                })
                .withCellEditorProvider(dep -> {
                    if (dep.getConflictVersion() != null) {
                        var conflictVersion = dep.getConflictVersion();
                        var cellEditor = new ComboBoxViewerCellEditor((Composite) dependenciesViewer.getControl(),
                                SWT.READ_ONLY);
                        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
                        cellEditor.setInput(
                                List.of("", conflictVersion.getOurVersionLabel(),
                                        conflictVersion.getTheirVersionLabel()));
                        return cellEditor;
                    } else {
                        return new TextCellEditor((Composite) dependenciesViewer.getControl());
                    }
                })
                .doNotReuseCellEditor()
                .create());

        Comparator<DependencyLookup> comparator = (d1, d2) -> Boolean.valueOf(d2.isSelected())
                .compareTo(Boolean.valueOf(d1.isSelected()));
        comparator = comparator.thenComparing((d1, d2) -> d1.getStatus().compareTo(d2.getStatus()));

        TableColumnSorter<DependencyLookup> tableColumnSorter = new TableColumnSorter<>(dependenciesViewer, comparator);
        tableColumnSorter.setColumn(resolutionColumn.getColumn());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(48, false));
        layout.addColumnData(new ColumnWeightData(2));
        layout.addColumnData(new ColumnWeightData(2));
        layout.addColumnData(new ColumnWeightData(1));
        dependenciesViewer.getTable().setLayout(layout);

        ColumnViewerToolTipSupport.enableFor(dependenciesViewer);

        dependenciesViewer.setInput(dependenciesLookup);

        ctx.addValidationStatusProvider(new ValidationStatusProvider() {

            @Override
            public IObservableValue<IStatus> getValidationStatus() {
                return dependencyConflictHandler.getConflictValidationStatusObservable();
            }

            @Override
            public IObservableList<IObservable> getTargets() {
                return new WritableList<>();
            }

            @Override
            public IObservableList<IObservable> getModels() {
                return new WritableList<>();
            }
        });

        return container;
    }

    private IValueChangeListener<? super Boolean> createLocalDependenciesPanelEventHandler(Composite container) {
        MessagePanel panel = new MessagePanel(LOCAL_PANEL_ID, IStatus.WARNING,
                unresolvedLocalDependenciesLinkSupplier());
        return new MessagePanelEventHandler(container, panel);
    }

    private IValueChangeListener<? super Boolean> createDependenciesPanelEventHandler(Composite container) {
        MessagePanel panel = new MessagePanel(ERROR_PANEL_ID, IStatus.ERROR, unresolvedDependenciesLinkSupplier());
        return new MessagePanelEventHandler(container, panel);
    }

    private IValueChangeListener<? super Boolean> createConflictingDependenciesPanelEventHandler(Composite container) {
        MessagePanel panel = new MessagePanel(CONFLICT_PANEL_ID, IStatus.WARNING,
                conflictingDependenciesLabelSupplier());
        return new MessagePanelEventHandler(container, panel);
    }

    private MessageControlSupplier<Label> conflictingDependenciesLabelSupplier() {
        return parent -> {
            Label conflictDependenciesInfoLabel = new Label(parent, SWT.WRAP);
            conflictDependenciesInfoLabel.setLayoutData(GridDataFactory.fillDefaults()
                    .hint(400, SWT.DEFAULT)
                    .grab(true, true)
                    .create());
            conflictDependenciesInfoLabel.setText(Messages.conflictingDependenciesMessage);
            return conflictDependenciesInfoLabel;
        };
    }

    private String versionValueProvider(DependencyLookup dep) {
        if (dep.getConflictVersion() != null) {
            return dep.toString();
        }
        return dep.getVersion();
    }

    private String versionTextProvider(DependencyLookup dep) {
        if (dep.getConflictVersion() != null) {
            return dep.getConflictVersion().toString();
        }
        return dep.getVersion();
    }

    private MessageControlSupplier<Link> unresolvedLocalDependenciesLinkSupplier() {
        return parent -> {
            Link unresolvedDependenciesInfoLabel = new Link(parent, SWT.WRAP);
            unresolvedDependenciesInfoLabel.setLayoutData(GridDataFactory.fillDefaults()
                    .hint(400, SWT.DEFAULT)
                    .grab(true, true)
                    .create());
            unresolvedDependenciesInfoLabel.setText(Messages.unresolvedLocalDependenciesMessage);
            unresolvedDependenciesInfoLabel.addListener(SWT.Selection,
                    new OpenSystemBrowserListener(MAVEN_REPO_CONFIG_DOC_URL));
            return unresolvedDependenciesInfoLabel;
        };
    }

    private MessageControlSupplier<Link> unresolvedDependenciesLinkSupplier() {
        return parent -> {
            Link unresolvedDependenciesInfoLabel = new Link(parent, SWT.WRAP);
            unresolvedDependenciesInfoLabel.setLayoutData(GridDataFactory.fillDefaults()
                    .hint(400, SWT.DEFAULT)
                    .grab(true, true)
                    .create());
            unresolvedDependenciesInfoLabel.setText(Messages.unresolvedDependenciesMessage);
            unresolvedDependenciesInfoLabel.addListener(SWT.Selection,
                    new OpenSystemBrowserListener(MAVEN_REPO_CONFIG_DOC_URL));
            return unresolvedDependenciesInfoLabel;
        };
    }

    private String resolutionTooltip(DependencyLookup dep) {
        String tooltip = null;
        switch (dep.getStatus()) {
            case FOUND:
                tooltip = String.format(Messages.resolvedFromRepository, dep.getRepository());
                break;
            case LOCAL:
                tooltip = Messages.cannotBeResolvedAgainstProvidedRepositoryInstalledLocally;
                break;
            case NOT_FOUND:
                tooltip = Messages.cannotBeResolvedAgainstProvidedRepository;
                break;
            default:
                throw new IllegalStateException("Unsupported status: " + dep.getStatus());
        }
        if (!dep.isUsed()) {
            tooltip = tooltip + System.lineSeparator() + Messages.unusedDependenciesWarning;
        }
        return tooltip;
    }

    private Image resolutionIcon(DependencyLookup dep) {
        ImageDescriptor descriptor = null;
        switch (dep.getStatus()) {
            case FOUND:
                descriptor = Pics.getImageDescriptor(PicsConstants.checkmark);
                break;
            case LOCAL:
                descriptor = Pics.getImageDescriptor(PicsConstants.warning);
                break;
            case NOT_FOUND:
                descriptor = Pics.getImageDescriptor(PicsConstants.error);
                break;
            default:
                throw new IllegalStateException("Unsupported status: " + dep.getStatus());
        }
        if (!dep.isUsed()) {
            return new DecorationOverlayIcon(descriptor,
                    Pics.getImageDescriptor("problem.gif", BosArchiveImporterPlugin.getDefault()),
                    IDecoration.TOP_RIGHT).createImage();
        }
        return descriptor.createImage();
    }

}
