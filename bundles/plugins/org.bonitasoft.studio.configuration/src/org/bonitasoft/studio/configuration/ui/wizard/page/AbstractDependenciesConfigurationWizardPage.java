/**
 * Copyright (C) 2026 BonitaSoft S.A.
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.plugin.analyze.report.model.Artifact;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.plugin.analyze.report.model.Implementation;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.bpm.model.util.FragmentUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.ui.jface.TableColumnSorter;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.dependencies.ui.MissingDependenciesDecorator;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
/**
 * @author Romain Bioteau
 */
public abstract class AbstractDependenciesConfigurationWizardPage extends WizardPage
        implements IProcessConfigurationWizardPage, ICheckStateListener,
        ICheckStateProvider {

    /** Beyond that, the page message lists a count instead of every library name. */
    private static final int MAX_LISTED_CONFLICTS = 3;

    private CheckboxTreeViewer treeViewer;
    protected Configuration configuration;
    private Button removeJarButton;
    private Button addJarButton;
    private TableViewer rawViewer;
    private DependenciesTreeLabelProvider treeLabelProvider;
    private MissingDependenciesDecorator missingDependenciesDecorator;
    public AbstractDependenciesConfigurationWizardPage(final String pageId) {
        super(pageId);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite tabComposite = new Composite(parent, SWT.NONE);
        tabComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tabComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Label descriptionLabel = new Label(tabComposite, SWT.WRAP);
        descriptionLabel.setText(getDescription());
        descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        final TabFolder tabFolder = new TabFolder(tabComposite, SWT.TOP);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tabFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (treeViewer != null) {
                    treeViewer.refresh();
                }
                if (rawViewer != null) {
                    rawViewer.refresh();
                }
            }
        });

        final TabItem treeClasspathItem = new TabItem(tabFolder, SWT.NONE);
        treeClasspathItem.setText(Messages.hiearachical);
        treeClasspathItem.setControl(createTreeClasspathControl(tabFolder));

        final TabItem rawClasspathItem = new TabItem(tabFolder, SWT.NONE);
        rawClasspathItem.setText(Messages.raw);
        rawClasspathItem.setControl(createRawClasspathControl(tabFolder));

        setControl(tabComposite);
    }

    protected Control createRawClasspathControl(final TabFolder parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 10).create());

        final Label rawViewDesc = new Label(mainComposite, SWT.WRAP);
        rawViewDesc.setText(Messages.rawViewDesc);
        rawViewDesc.setLayoutData(GridDataFactory.fillDefaults().create());

        final Text searchBox = new Text(mainComposite, SWT.SEARCH | SWT.CANCEL | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
        searchBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        rawViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION);
        rawViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        rawViewer.getTable().setHeaderVisible(true);
        rawViewer.getTable().setLinesVisible(true);
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(2));
        rawViewer.getTable().setLayout(tableLayout);
        rawViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                final String searchQuery = searchBox.getText();
                if (searchQuery == null || searchQuery.isEmpty()
                        || ((Fragment) element).getValue() != null
                                && ((Fragment) element).getValue().toLowerCase().contains(searchQuery.toLowerCase())) {
                    return true;
                }
                return false;
            }
        });
        searchBox.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                rawViewer.refresh();
            }
        });

        final TableViewerColumn jarColumn = new TableViewerColumn(rawViewer, SWT.NONE);
        jarColumn.getColumn().setText(Messages.jarName);
        jarColumn.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Fragment) {
                    String value = ((Fragment) element).getValue();
                    return value != null ? value : "";
                }
                return "";
            }

            @Override
            public Image getImage(final Object element) {
                ConfigurationPlugin plugin = ConfigurationPlugin.getDefault();
                return plugin != null ? Pics.getImage("jar.gif", plugin) : null;
            }
        });

        new TableColumnSorter(rawViewer).setColumn(jarColumn.getColumn());

        final TableViewerColumn dependencyColumn = new TableViewerColumn(rawViewer, SWT.NONE);
        dependencyColumn.getColumn().setText(Messages.inculdedBy);
        dependencyColumn.setLabelProvider(new ColumnLabelProvider() {

            private final FragmentTypeLabelProvider fragmentTypeLabelProvider = new FragmentTypeLabelProvider();

            @Override
            public String getText(final Object element) {
                final String value = ((Fragment) element).getValue();
                final List<FragmentContainer> fragmentContainers = (List<FragmentContainer>) getViewerInput(
                        configuration);
                final List<Fragment> fragment = new ArrayList<Fragment>();
                for (final FragmentContainer fc : fragmentContainers) {
                    fragment.addAll((Collection<? extends Fragment>) ModelHelper.getAllItemsOfType(fc,
                            ConfigurationPackage.Literals.FRAGMENT));
                }
                final Set<FragmentContainer> containers = new HashSet<FragmentContainer>();
                for (final Fragment f : fragment) {
                    if (value != null && value.equals(f.getValue())) {
                        final FragmentContainer container = (FragmentContainer) ((EObject) f).eContainer();
                        containers.add(container);
                    }
                }

                final StringBuilder sb = new StringBuilder();
                for (final FragmentContainer dep : containers) {
                    sb.append(fragmentTypeLabelProvider.getText(dep));
                    sb.append(", ");
                }
                if (sb.length() >= 2) {
                    sb.delete(sb.length() - 2, sb.length());
                }
                return sb.toString();
            }
        });

        return mainComposite;
    }

    protected Control createTreeClasspathControl(final TabFolder parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10)
                .equalWidth(false).create());

        final Label hierarchicalViewDesc = new Label(mainComposite, SWT.WRAP);
        hierarchicalViewDesc.setText(Messages.hiearachicalViewDesc);
        hierarchicalViewDesc.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).spacing(0, 3).create());

        addJarButton = new Button(buttonComposite, SWT.FLAT);
        addJarButton.setLayoutData(GridDataFactory.fillDefaults().create());
        addJarButton.setText(Messages.add);
        addJarButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectJarsDialog dialog = new SelectJarsDialog(Display.getDefault().getActiveShell(),
                        collectManagedJarNames());
                if (dialog.open() == Window.OK) {
                    final Object selection = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
                    final FragmentContainer fc = getFragmentContainer(selection);
                    if (fc != null) {
                        for (final DependencyFileStore file : dialog.getSelectedJars()) {
                            final String jarName = file.getName();
                            addJarFragment(fc, jarName);
                            file.getTransitiveDependencies()
                                    .stream()
                                    .map(File::getName)
                                    .forEach(fileName -> addJarFragment(fc, fileName));
                        }
                        // added jars come in selected, so they can collide with a version already there
                        refreshVersionConflicts();
                    }

                }
            }

            private FragmentContainer getFragmentContainer(final Object selection) {
                FragmentContainer fc = null;
                if (selection instanceof FragmentContainer) {
                    fc = (FragmentContainer) selection;
                } else if (selection instanceof Fragment) {
                    fc = (FragmentContainer) ((Fragment) selection).eContainer();
                }
                return fc;
            }

            private void addJarFragment(FragmentContainer fc, final String jarName) {
                if (fc.getFragments().stream()
                        .noneMatch(f -> Objects.equals(jarName, f.getValue()))) {
                    final Fragment f = ConfigurationFactory.eINSTANCE.createFragment();
                    f.setExported(true);
                    f.setValue(jarName);
                    f.setKey(jarName);
                    f.setType(FragmentTypes.JAR);
                    fc.getFragments().add(f);
                }
            }
        });

        removeJarButton = new Button(buttonComposite, SWT.FLAT);
        removeJarButton.setLayoutData(GridDataFactory.fillDefaults().create());
        removeJarButton.setText(Messages.remove);
        removeJarButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                var firstElement = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
                if (!(firstElement instanceof Fragment selection)) {
                    return;
                }
                final FragmentContainer fc = (FragmentContainer) selection.eContainer();
                fc.getFragments().remove(selection);
                // removing the selected version may leave the ones it shadowed free to be picked again
                refreshVersionConflicts();
            }
        });

        treeViewer = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION);
        treeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        missingDependenciesDecorator = new MissingDependenciesDecorator(getDependencyRepositoryStore());
        treeViewer.getTree().setLinesVisible(true);
        treeLabelProvider = new DependenciesTreeLabelProvider();
        treeViewer.setLabelProvider(
                new DecoratingLabelProvider(treeLabelProvider, missingDependenciesDecorator));
        treeViewer.setCheckStateProvider(this);
        treeViewer.addCheckStateListener(this);
        treeViewer.addFilter(hideEmptyCategories());
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent arg0) {
                updateButtons();
            }
        });

        return mainComposite;
    }

    private ViewerFilter hideEmptyCategories() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (element instanceof FragmentContainer
                        && ((FragmentContainer) element).getParent() == null
                        && !FragmentTypes.OTHER.equals(((FragmentContainer) element).getId())
                        && ((FragmentContainer) element).getFragments().isEmpty()) {
                    for (final FragmentContainer c : ((FragmentContainer) element).getChildren()) {
                        if (!c.getChildren().isEmpty() || !c.getFragments().isEmpty()) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
        };
    }

    protected DependencyRepositoryStore getDependencyRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#updatePage(org.bonitasoft.bpm.model.process.AbstractProcess,
     * org.bonitasoft.bpm.model.configuration.Configuration)
     */
    @Override
    public void updatePage(final Pool process, final Configuration configuration) {
        if (process != null && configuration != null && treeViewer != null && !treeViewer.getTree().isDisposed()) {
            this.configuration = configuration;
            // before the viewers are populated: the decorator is asked for every row they paint
            refreshVersionConflicts();
            treeViewer.setContentProvider(new TreeDependenciesContentProvider());
            treeViewer.setInput(getViewerInput(configuration));
            treeViewer.expandAll();

            rawViewer.setContentProvider(new RawDependenciesContentProvider());
            rawViewer.setInput(getViewerInput(configuration));

            addJarButton.setEnabled(false);
            removeJarButton.setEnabled(false);
        }
    }

    /**
     * Recompute the selected versions, dim the ones that step aside and report the leftover conflicts.
     * <p>
     * The grouping comes from {@code FragmentUtils}, the very same one the pom generation uses to decide
     * whether it can pin a version, so the warning cannot drift from the actual behaviour. Unlike the
     * version mismatch and the ambiguous group id, this conflict is readable from the configuration
     * alone, without resolving anything, which is why it belongs here rather than in the build log.
     * </p>
     * <p>
     * No attempt is made to announce which version would win: that depends on Maven arbitration, which
     * is unknown until the dependencies are resolved. Naming a winner here and being wrong would be
     * worse than staying silent about it.
     * </p>
     */
    protected void refreshVersionConflicts() {
        refreshVersionConflicts(List.of());
    }

    /**
     * @param justUnselected the jars this very click dropped, reported once so the automatic unselection
     *        is not silent, never {@code null}
     * @see #refreshVersionConflicts()
     */
    protected void refreshVersionConflicts(List<String> justUnselected) {
        if (missingDependenciesDecorator == null) {
            return;
        }
        // one walk for both: conflictingArtifactBases would redo this very index
        var selectedVersionsByBase = FragmentUtils.selectedVersionsByArtifactBase(configuration);
        missingDependenciesDecorator.setSelectedVersionsByBase(selectedVersionsByBase);
        var conflictingBases = selectedVersionsByBase.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Entry::getKey)
                .toList();
        if (treeViewer != null && !treeViewer.getTree().isDisposed()) {
            treeViewer.refresh();
        }
        if (rawViewer != null && !rawViewer.getTable().isDisposed()) {
            rawViewer.refresh();
        }
        if (!conflictingBases.isEmpty()) {
            setMessage(versionConflictMessage(conflictingBases), IMessageProvider.WARNING);
        } else if (!justUnselected.isEmpty()) {
            setMessage(NLS.bind(Messages.versionAutomaticallyUnselected,
                    String.join(", ", justUnselected)), IMessageProvider.INFORMATION);
        } else {
            setMessage(null);
        }
        // updatePage runs while the page may not be shown yet, so the container has to be told
        if (getContainer() != null && getContainer().getCurrentPage() != null) {
            getContainer().updateMessage();
        }
    }

    /**
     * Build a single warning listing the conflicting libraries, truncated so that the page message stays
     * readable when many of them collide.
     */
    private String versionConflictMessage(Collection<String> conflictingBases) {
        var names = new ArrayList<>(conflictingBases);
        String listed;
        if (names.size() <= MAX_LISTED_CONFLICTS) {
            listed = String.join(", ", names);
        } else {
            listed = NLS.bind(Messages.severalVersionsSelectedAndMore,
                    String.join(", ", names.subList(0, MAX_LISTED_CONFLICTS)),
                    names.size() - MAX_LISTED_CONFLICTS);
        }
        return NLS.bind(Messages.severalVersionsSelected, listed);
    }

    protected abstract Object getViewerInput(Configuration configuration);

    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final Object element = event.getElement();
        List<String> justUnselected = List.of();
        if (element instanceof Fragment fragment) {
            fragment.setExported(event.getChecked());
            if (event.getChecked()) {
                justUnselected = unselectOtherVersions(fragment);
            }
        }
        if (element instanceof FragmentContainer) {
            updateChildrenState((FragmentContainer) element, event.getChecked());
        }
        updateParentSate(element);

        refreshVersionConflicts(justUnselected);
    }

    /**
     * Drop the other versions of the library that was just selected.
     * <p>
     * Two versions of the same library cannot both be embedded: a {@code dependencyManagement} entry is
     * indexed by {@code groupId:artifactId}, so the pom generation abstains and Maven arbitration
     * decides. Rather than let the user build a selection that cannot be honoured, the last picked
     * version wins and the others step aside -- one click to change one's mind, where refusing the click
     * would have meant hunting down the version in place first.
     * </p>
     * <p>
     * Checking a whole container still selects everything it holds, conflicts included: there is no
     * telling which version the user meant, so that case keeps the warning instead.
     * </p>
     * <p>
     * The candidates are read through {@code FragmentUtils.selectedJarNames}, the same precedence the
     * archive is filtered with: a jar the user excluded in one place is not embedded because another
     * fragment still exports it, so flipping that fragment would change nothing and announcing it would
     * describe a decision that was never taken.
     * </p>
     * <p>
     * That precedence is read on the picked jar too: when the same file name is excluded in another
     * container, checking this one embeds nothing, so nothing steps aside. Otherwise the archive would
     * end up without any version of the library, against a selection the user just made.
     * </p>
     *
     * @return the jars that were unselected, in configuration order, never {@code null}
     */
    protected List<String> unselectOtherVersions(Fragment selected) {
        String lib = selected.getValue();
        if (lib == null || configuration == null) {
            return List.of();
        }
        String version = FragmentUtils.extractArtifactVersion(lib).orElse(null);
        if (version == null) {
            // no version in the file name: nothing to arbitrate, and nothing the pom could pin either
            return List.of();
        }
        String base = FragmentUtils.extractArtifactBase(lib);
        List<Fragment> allFragments = configuration.getProcessDependencies().stream()
                .flatMap(FragmentUtils::walkAllFragments)
                .collect(Collectors.toList());
        Set<String> selectedJars = FragmentUtils.selectedJarNames(allFragments);
        if (!selectedJars.contains(lib)) {
            // the same jar is excluded in another container, and that exclusion wins: checking this one
            // embeds nothing. Dropping the version in place would leave the archive without any version
            // of the library, and announce a swap that did not happen.
            return List.of();
        }
        var unselected = allFragments.stream()
                .filter(fragment -> fragment != selected)
                // selectedJarNames already drops what is unselected, excluded elsewhere, or unnamed
                .filter(fragment -> selectedJars.contains(fragment.getValue())
                        && base.equals(FragmentUtils.extractArtifactBase(fragment.getValue()))
                        && isOtherVersionOf(version, fragment.getValue()))
                .collect(Collectors.toList());
        unselected.forEach(fragment -> fragment.setExported(false));
        return unselected.stream()
                .map(Fragment::getValue)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Whether {@code lib} carries a readable version of the same library other than {@code version}.
     * <p>
     * A jar whose file name holds no readable version shares its base with every version of the same
     * library -- {@code myConnector.jar}, the generated connector jar, shares it with
     * {@code myConnector-1.0.jar} -- yet it is no conflict: the pom pins nothing for it, and dropping it
     * would break the connector it belongs to. Mirrors
     * {@code MissingDependenciesDecorator.isShadowedByAnotherVersion}, so no row is unselected that was
     * not dimmed beforehand.
     * </p>
     */
    private boolean isOtherVersionOf(String version, String lib) {
        return FragmentUtils.extractArtifactVersion(lib)
                .map(other -> !version.equals(other))
                .orElse(Boolean.FALSE);
    }

    private void updateChildrenState(final FragmentContainer element, final boolean isChecked) {
        treeViewer.setGrayChecked(element, false);
        treeViewer.setChecked(element, isChecked);
        applyOnChildren(element, isChecked);
    }

    private void updateParentSate(final Object element) {
        FragmentContainer parent = null;
        if (element instanceof FragmentContainer) {
            parent = ((FragmentContainer) element).getParent();
        } else if (element instanceof Fragment) {
            parent = (FragmentContainer) ((Fragment) element).eContainer();
        }

        if (parent != null) {
            final boolean isChecked = isChecked(parent);
            if (isChecked) {
                treeViewer.setGrayChecked(parent, false);
                treeViewer.setChecked(parent, isChecked);
            } else {
                treeViewer.setGrayChecked(parent, isGrayed(parent));
            }
            updateParentSate(parent);
        }
    }

    protected void applyOnChildren(final FragmentContainer element, final boolean checked) {
        final Set<Object> result = new HashSet<Object>();
        getAllChildren(element, result);
        final Object[] children = result.toArray();
        if (children.length == 0) {
            treeViewer.setChecked(element, false);
        } else {
            for (final Object child : children) {
                if (child instanceof Fragment) {
                    ((Fragment) child).setExported(checked);
                }
                treeViewer.setChecked(child, checked);
            }
        }

    }

    @Override
    public boolean isChecked(final Object element) {
        if (element instanceof Fragment) {
            return ((Fragment) element).isExported();
        } else if (element instanceof FragmentContainer) {
            final Set<Object> result = new HashSet<Object>();
            getAllChildren((FragmentContainer) element, result);
            for (final Object child : result) {
                if (!isChecked(child)) {
                    return false;
                }
            }

            return !result.isEmpty();
        }

        return false;
    }

    @Override
    public boolean isGrayed(final Object element) {
        if (element instanceof Fragment) {
            return false;
        } else if (element instanceof FragmentContainer) {
            final Set<Object> result = new HashSet<Object>();
            getAllChildren((FragmentContainer) element, result);
            final Object[] children = result.toArray();
            boolean isGrayed = false;
            boolean isSelected = false;
            if (children.length == 0) {
                return false;
            }
            isGrayed = isChecked(children[0]);
            for (final Object child : children) {
                isSelected = isChecked(child);
                if (isSelected != isGrayed) {
                    treeViewer.setGrayChecked(element, true);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void getAllChildren(final FragmentContainer element, final Set<Object> result) {
        for (final Fragment f : element.getFragments()) {
            result.add(f);
        }
        for (final FragmentContainer fc : element.getChildren()) {
            result.add(fc);
            getAllChildren(fc, result);
        }
    }

    private Set<String> collectManagedJarNames() {
        ProjectDependenciesStore depStore = RepositoryManager.getInstance().getCurrentRepository()
                .map(repo -> repo.getProjectDependenciesStore())
                .orElse(null);
        if (depStore == null) {
            return Set.of();
        }
        Stream<Artifact> implementationJars = Stream.concat(
                depStore.getConnectorImplementations().stream(),
                depStore.getActorFilterImplementations().stream())
                .map(Implementation::getArtifact);

        Stream<Artifact> definitionJars = Stream.concat(
                depStore.getConnectorDefinitions().stream(),
                depStore.getActorFilterDefinitions().stream())
                .map(Definition::getArtifact);

        return Stream.concat(implementationJars, definitionJars)
                .filter(Objects::nonNull)
                .map(Artifact::getFile)
                .filter(Objects::nonNull)
                .map(file -> new File(file).getName())
                .collect(Collectors.toSet());
    }

    protected void updateButtons() {
        final Object selection = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
        if (addJarButton != null && !addJarButton.isDisposed()) {
            if (selection instanceof Fragment) {
                final FragmentContainer fc = (FragmentContainer) ((Fragment) selection).eContainer();
                addJarButton.setEnabled(fc.getId().equals(FragmentTypes.OTHER));
            } else if (selection instanceof FragmentContainer) {
                addJarButton.setEnabled(((FragmentContainer) selection).getId().equals(FragmentTypes.OTHER));
            } else {
                addJarButton.setEnabled(false);
            }
        }

        if (removeJarButton != null && !removeJarButton.isDisposed()) {
            if (selection instanceof Fragment) {
                final FragmentContainer fc = (FragmentContainer) ((Fragment) selection).eContainer();
                removeJarButton.setEnabled(fc.getId().equals(FragmentTypes.OTHER));
            } else {
                removeJarButton.setEnabled(false);
            }
        }

    }

    protected EStructuralFeature getContainingFeature(final EObject f) {
        if (f instanceof Fragment && f.eContainer() != null && f.eContainer().eContainer() != null) {
            return f.eContainer().eContainer().eContainingFeature();
        }
        return null;
    }

}
