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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.migration.JarInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class DependenciesPreviewControlSupplier implements ControlSupplier {

    private static final String MAVEN_REPO_DOC_URL = "https://maven.apache.org/guides/introduction/introduction-to-repositories.html";
    private static final String MAVEN_REPO_CONFIG_DOC_URL = "https://maven.apache.org/settings.html#Repositories";

    private IObservableList<DependencyLookup> dependenciesLookup;
    private ImportBosArchiveControlSupplier archiveModelSupplier;
    private ExceptionDialogHandler exceptionDialogHandler;
    private Map<String, List<DependencyLookup>> executions = new HashMap<>();
    private ImportArchiveModel importArchiveModel;
    private IObservableList<IRepository> mavenRepositories;

    public DependenciesPreviewControlSupplier(IObservableList<DependencyLookup> dependenciesLookup,
            IObservableList<IRepository> mavenRepositories,
            ImportBosArchiveControlSupplier archiveModelSupplier,
            ExceptionDialogHandler exceptionDialogHandler) {
        this.dependenciesLookup = dependenciesLookup;
        this.mavenRepositories = mavenRepositories;
        this.archiveModelSupplier = archiveModelSupplier;
        this.exceptionDialogHandler = exceptionDialogHandler;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        IWizardPage selectedPage = (IWizardPage) event.getSelectedPage();
        if (Messages.dependenciesPreviewTitle.equals(selectedPage.getTitle()) && (importArchiveModel == null
                || !executions
                        .containsKey(archiveModelSupplier.get().getBosArchive().getArchiveFile().getAbsolutePath()))) {
            runDependenciesMigrationOperation(selectedPage.getWizard().getContainer());
        }
    }

    private void runDependenciesMigrationOperation(IWizardContainer wizardContainer) {
        importArchiveModel = archiveModelSupplier.get();
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        List<JarInputStreamSupplier> jars = new ArrayList<>();
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
            executions.put(importArchiveModel.getBosArchive().getArchiveFile().getAbsolutePath(), dependenciesLookup);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileParsingBosArchive, e);
        } finally {
            for (JarInputStreamSupplier jar : jars) {
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

        IObservableValue<Boolean> unknownDepObservable = new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return dependenciesLookup.stream()
                        .map(DependencyLookup::getStatus)
                        .anyMatch(DependencyLookup.Status.NOT_FOUND::equals);
            }
        };

        unknownDepObservable.addValueChangeListener(showUnknownDependenciesPanel(container));

        ExpandableComposite repositoriesExpandable = new ExpandableComposite(container, SWT.NONE,
                ExpandableComposite.TWISTIE | ExpandableComposite.NO_TITLE_FOCUS_BOX
                        | ExpandableComposite.CLIENT_INDENT);
        repositoriesExpandable.setLayout(GridLayoutFactory.fillDefaults().create());
        repositoriesExpandable.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        repositoriesExpandable.setText("Remote repositories");

        Composite client = new Composite(repositoriesExpandable, SWT.NONE);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().create());

        Link description = new Link(client, SWT.NONE);
        description.setText("<a>Remote repositories</a> to use in your project.");
        description.addSelectionListener(new OpenBrowserListener(MAVEN_REPO_DOC_URL));
        TableViewer repositoriesViewer = new TableViewer(client);
        repositoriesViewer.getControl().setLayoutData(GridDataFactory
                .fillDefaults()
                .grab(true, true)
                .hint(SWT.DEFAULT, 80)
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
        repositoriesExpandable.addExpansionListener(new IExpansionListener() {

            @Override
            public void expansionStateChanging(ExpansionEvent e) {
            }

            @Override
            public void expansionStateChanged(ExpansionEvent e) {
                container.layout(true, true);
            }
        });

        CheckboxTableViewer dependenciesViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
        dependenciesViewer.getControl().setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true)
                .create());
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
                event -> ((DependencyLookup) event.getElement()).setSelected(event.getChecked()));
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
                .withCanEditProvider(dep -> dep.getStatus() == Status.NOT_FOUND)
                .withValueProvider(DependencyLookup::getGroupId)
                .withValueUpdater((dep, value) -> dep.setGroupId((String) value))
                .create());

        TableViewerColumn artifactIdColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        artifactIdColumn.getColumn().setText(Messages.artifactId);
        artifactIdColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withTextProvider(DependencyLookup::getArtifactId)
                .createColumnLabelProvider());
        artifactIdColumn.setEditingSupport(new EditingSupportBuilder<DependencyLookup>(dependenciesViewer)
                .withCanEditProvider(dep -> dep.getStatus() == Status.NOT_FOUND)
                .withValueProvider(DependencyLookup::getArtifactId)
                .withValueUpdater((dep, value) -> dep.setArtifactId((String) value))
                .create());

        TableViewerColumn versionColumn = new TableViewerColumn(dependenciesViewer, SWT.FILL);
        versionColumn.getColumn().setText(Messages.version);
        versionColumn.setLabelProvider(new LabelProviderBuilder<DependencyLookup>()
                .withTextProvider(DependencyLookup::getVersion)
                .createColumnLabelProvider());
        versionColumn.setEditingSupport(new EditingSupportBuilder<DependencyLookup>(dependenciesViewer)
                .withCanEditProvider(dep -> dep.getStatus() == Status.NOT_FOUND)
                .withValueProvider(DependencyLookup::getVersion)
                .withValueUpdater((dep, value) -> dep.setVersion((String) value))
                .create());

        Comparator<DependencyLookup> selectedComparator = (d1, d2) -> Boolean.valueOf(d2.isSelected())
                .compareTo(Boolean.valueOf(d1.isSelected()));
        Comparator<DependencyLookup> resolutionComparator = (d1, d2) -> Boolean
                .valueOf(d2.getStatus() == Status.NOT_FOUND)
                .compareTo(Boolean.valueOf(d1.getStatus() == Status.NOT_FOUND));

        TableColumnSorter<DependencyLookup> tableColumnSorter = new TableColumnSorter<>(dependenciesViewer,
                selectedComparator.thenComparing(resolutionComparator));
        tableColumnSorter.setColumn(resolutionColumn.getColumn());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(48, false));
        layout.addColumnData(new ColumnWeightData(2));
        layout.addColumnData(new ColumnWeightData(2));
        layout.addColumnData(new ColumnWeightData(1));
        dependenciesViewer.getTable().setLayout(layout);

        ColumnViewerToolTipSupport.enableFor(dependenciesViewer);

        dependenciesViewer.setInput(dependenciesLookup);

        return container;
    }

    private IValueChangeListener<? super Boolean> showUnknownDependenciesPanel(Composite container) {
        return event -> {
            if (event.diff.getNewValue()) {
                createUnknownDependenciesPanel(container);
            } else {
                findControl("info.panel", container)
                        .ifPresent(Control::dispose);
            }
            container.layout();
        };
    }

    private java.util.Optional<Control> findControl(String id, Composite container) {
        return Stream.of(container.getChildren())
                .filter(c -> Objects.equals(c.getData("id"), id))
                .findFirst();
    }

    private Composite createUnknownDependenciesPanel(Composite container) {
        Composite infoComposite = new Composite(container, SWT.NONE);
        infoComposite.setData("id", "info.panel");
        infoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        infoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        Label icon = new Label(infoComposite, SWT.NONE);
        Image image = Pics.getImageDescriptor("info_warning.png", BosArchiveImporterPlugin.getDefault())
                .createImage();
        icon.setImage(image);
        icon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.TOP).create());

        Link unresolvedDependenciesInfoLabel = new Link(infoComposite, SWT.WRAP);
        unresolvedDependenciesInfoLabel.setLayoutData(GridDataFactory.fillDefaults()
                .hint(400, SWT.DEFAULT)
                .grab(true, true)
                .create());
        unresolvedDependenciesInfoLabel.setText(Messages.unresolvedDependenciesMessage);
        unresolvedDependenciesInfoLabel.addSelectionListener(new OpenBrowserListener(MAVEN_REPO_CONFIG_DOC_URL));

        return infoComposite;
    }

    private String resolutionTooltip(DependencyLookup dep) {
        String tooltip = dep.getStatus() == DependencyLookup.Status.FOUND
                ? String.format(Messages.resolvedFromRepository, dep.getRepository())
                : Messages.cannotBeResolvedAgainstProvidedRepository;
        if (!dep.isUsed()) {
            tooltip = tooltip + System.lineSeparator() + Messages.unusedDependenciesWarning;
        }
        return tooltip;
    }

    private Image resolutionIcon(DependencyLookup dep) {
        ImageDescriptor descriptor = dep.getStatus() == DependencyLookup.Status.FOUND
                ? Pics.getImageDescriptor("checkmark.png", BosArchiveImporterPlugin.getDefault())
                : Pics.getImageDescriptor("info_warning.png", BosArchiveImporterPlugin.getDefault());
        if (!dep.isUsed()) {
            return new DecorationOverlayIcon(descriptor,
                    Pics.getImageDescriptor("problem.gif", BosArchiveImporterPlugin.getDefault()),
                    IDecoration.TOP_RIGHT).createImage();
        }
        return descriptor.createImage();
    }

    class OpenBrowserListener extends SelectionAdapter {
        
        private String url;

        OpenBrowserListener(String url){
            this.url = url;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(url));
            } catch (final IOException | URISyntaxException ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }

}
