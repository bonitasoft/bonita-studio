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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.dependencies.ui.MissingDependenciesDecorator;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
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
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
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
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractDependenciesConfigurationWizardPage extends WizardPage
        implements IProcessConfigurationWizardPage, ICheckStateListener,
        ICheckStateProvider {

    /**
     * Label provider for the ListViewer.
     */
    class TabbedPropertySheetPageLabelProvider
            extends LabelProvider {

        @Override
        public String getText(final Object element) {
            if (element instanceof ITabDescriptor) {
                return ((ITabDescriptor) element).getLabel();
            }
            return null;
        }
    }

    private CheckboxTreeViewer treeViewer;
    private Configuration configuration;
    private Button removeJarButton;
    private Button addJarButton;
    private TableViewer rawViewer;

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
                return ((Fragment) element).getValue();
            }

            @Override
            public Image getImage(final Object element) {
                return Pics.getImage("jar.gif", ConfigurationPlugin.getDefault());
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
                    if (f.getValue().equals(value)) {
                        final FragmentContainer container = (FragmentContainer) ((EObject) f).eContainer();
                        containers.add(container);
                    }
                }

                final StringBuilder sb = new StringBuilder();
                for (final FragmentContainer dep : containers) {
                    sb.append(fragmentTypeLabelProvider.getText(dep));
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
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
                final SelectJarsDialog dialog = new SelectJarsDialog(Display.getDefault().getActiveShell());
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
                        treeViewer.refresh();
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
                    f.setType(FragmentTypes.JAR);
                    f.setKey(jarName);
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
                final Fragment selection = (Fragment) ((IStructuredSelection) treeViewer.getSelection())
                        .getFirstElement();
                final FragmentContainer fc = (FragmentContainer) selection.eContainer();
                fc.getFragments().remove(selection);
                treeViewer.refresh();
            }
        });

        treeViewer = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION);
        treeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final ILabelDecorator missingDependenciesDecorator = new MissingDependenciesDecorator(
                getDependencyRepositoryStore());
        treeViewer.getTree().setLinesVisible(true);
        treeViewer.setLabelProvider(
                new DecoratingLabelProvider(new DependenciesTreeLabelProvider(), missingDependenciesDecorator));
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
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#updatePage(org.bonitasoft.studio.model.process.AbstractProcess,
     * org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public void updatePage(final AbstractProcess process, final Configuration configuration) {
        if (process != null && configuration != null && treeViewer != null && !treeViewer.getTree().isDisposed()) {
            this.configuration = configuration;
            treeViewer.setContentProvider(new TreeDependenciesContentProvider());
            treeViewer.setInput(getViewerInput(configuration));
            treeViewer.expandAll();

            rawViewer.setContentProvider(new RawDependenciesContentProvider());
            rawViewer.setInput(getViewerInput(configuration));

            addJarButton.setEnabled(false);
            removeJarButton.setEnabled(false);
        }
    }

    protected abstract Object getViewerInput(Configuration configuration);

    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final Object element = event.getElement();
        if (element instanceof Fragment) {
            ((Fragment) element).setExported(event.getChecked());
        }
        if (element instanceof FragmentContainer) {
            updateChildrenState((FragmentContainer) element, event.getChecked());
        }
        updateParentSate(element);

        getContainer().updateMessage();
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
        if (f.eContainer() != null) {
            if (f.eContainer().eContainer() != null) {
                return ((Fragment) f).eContainer().eContainer().eContainingFeature();
            }
        }
        return null;
    }

}
