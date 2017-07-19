/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.jface.TreeExplorer;
import org.bonitasoft.studio.connector.model.definition.AbstractUniqueDefinitionContentProvider;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionExplorerLabelProvider;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractDefinitionSelectionImpementationWizardPage extends NewTypeWizardPage
        implements ISelectionChangedListener {

    protected ConnectorImplementation implementation;
    protected ConnectorDefinition selectedDefinition;
    protected EMFDataBindingContext context;
    protected UpdateValueStrategy defIdStrategy;
    protected UpdateValueStrategy defModelStrategy;
    protected ComboViewer versionCombo;
    protected TreeExplorer explorer;
    private WizardPageSupport pageSupport;
    private final Boolean checkOnlyCustom;
    private Button onlyCustomCheckbox;
    private final DefinitionResourceProvider messageProvider;
    protected final List<ConnectorDefinition> definitions;
    private final ViewerFilter customConnectorFilter = new ViewerFilter() {

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            if (element instanceof ConnectorDefinition) {
                final Resource eResource = ((ConnectorDefinition) element).eResource();
                if (eResource != null) {
                    final IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
                    return rootPath.isPrefixOf(Path.fromOSString(eResource.getURI().toFileString()));
                }
            }
            return false;
        }
    };

    public AbstractDefinitionSelectionImpementationWizardPage(final ConnectorImplementation implementation,
            final List<ConnectorImplementation> existingImpl, final List<ConnectorDefinition> definitions,
            final String pageTitle, final String pageDescription, final DefinitionResourceProvider messageProvider) {
        super(true, AbstractDefinitionSelectionImpementationWizardPage.class.getName());
        setTitle(pageTitle);
        setDescription(pageDescription);
        this.implementation = implementation;
        this.messageProvider = messageProvider;
        this.definitions = definitions;
        checkOnlyCustom = implementation.getDefinitionId() == null;
    }

    public AbstractDefinitionSelectionImpementationWizardPage(final List<ConnectorImplementation> existingImpl,
            final List<ConnectorDefinition> definitions, final String pageTitle, final String pageDescription,
            final DefinitionResourceProvider messageProvider) {
        super(true, AbstractDefinitionSelectionImpementationWizardPage.class.getName());
        setTitle(pageTitle);
        setDescription(pageDescription);
        this.messageProvider = messageProvider;
        this.definitions = definitions;
        checkOnlyCustom = false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        context = new EMFDataBindingContext();

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        explorer = createTreeExplorer(mainComposite);

        final Label definitionVersionLabel = new Label(mainComposite, SWT.NONE);
        definitionVersionLabel.setText(Messages.definitionVersion);
        definitionVersionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        versionCombo = new ComboViewer(mainComposite, SWT.READ_ONLY | SWT.BORDER);
        versionCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().create());
        versionCombo.setContentProvider(new ArrayContentProvider());
        versionCombo.setLabelProvider(new LabelProvider());
        versionCombo.getCombo().setEnabled(false);
        versionCombo.setSorter(new ViewerSorter());

        final Group descriptionGroup = new Group(mainComposite, SWT.NONE);
        descriptionGroup.setText(Messages.description);
        descriptionGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        descriptionGroup
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 70).span(2, 1).create());

        final Label descriptionLabel = new Label(descriptionGroup, SWT.WRAP);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        explorer.getRightTableViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object sel = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (sel instanceof ConnectorDefinition) {
                    final String defId = ((ConnectorDefinition) sel).getId();
                    final List<String> versions = new ArrayList<String>();
                    for (final ConnectorDefinition def : definitions) {
                        if (defId.equals(def.getId())) {
                            versions.add(def.getVersion());
                        }
                    }
                    versionCombo.setInput(versions);
                    String version = null;
                    if (implementation != null) {
                        version = implementation.getDefinitionVersion();
                    }
                    if (version != null && versions.contains(version)) {
                        versionCombo.setSelection(new StructuredSelection(version));
                    } else {
                        versionCombo.setSelection(new StructuredSelection(
                                versionCombo.getCombo().getItem(versionCombo.getCombo().getItemCount() - 1)));
                    }

                    versionCombo.getCombo().setEnabled(versions.size() > 1);
                    if (versions.size() == 1) {
                        for (final ConnectorDefinition def : definitions) {
                            if (defId.equals(def.getId())) {
                                descriptionLabel.setText(messageProvider.getConnectorDefinitionDescription(def));
                                descriptionGroup.layout(true);
                                break;
                            }
                        }

                    }
                } else {
                    versionCombo.setInput(Collections.emptyList());
                    descriptionLabel.setText("");
                    descriptionGroup.layout(true);
                }

            }
        });

        versionCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object sel = ((IStructuredSelection) explorer.getRightTableViewer().getSelection()).getFirstElement();
                if (sel instanceof ConnectorDefinition) {
                    final String defId = ((ConnectorDefinition) sel).getId();
                    final String version = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
                    if (defId != null && version != null) {
                        for (final ConnectorDefinition def : definitions) {
                            if (defId.equals(def.getId()) && version.equals(def.getVersion())) {
                                descriptionLabel.setText(messageProvider.getConnectorDefinitionDescription(def));
                                descriptionGroup.layout(true);
                                break;
                            }
                        }
                    }
                } else {
                    descriptionLabel.setText("");
                    descriptionGroup.layout(true);
                }
            }
        });

        defIdStrategy = new UpdateValueStrategy();
        defIdStrategy.setConverter(new Converter(ConnectorDefinition.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ConnectorDefinition) {
                    return ((ConnectorDefinition) from).getId();
                }
                return null;
            }

        });

        defModelStrategy = new UpdateValueStrategy();
        defModelStrategy.setConverter(new Converter(String.class, ConnectorDefinition.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof String) {
                    return getConnectorDefinitionFromId((String) from);
                }
                return null;
            }

        });
        updateOnlyCustomCheckbox();
        bindValue();

        setControl(mainComposite);
    }

    @SuppressWarnings("unchecked")
    protected ConnectorDefinition getConnectorDefinitionFromId(final String definitionId) {
        final List<Object> definitions = (List<Object>) explorer.getRightTableViewer().getInput();
        for (final Object c : definitions) {
            if (c instanceof ConnectorDefinition && ((ConnectorDefinition) c).getId().equals(definitionId)) {
                return (ConnectorDefinition) c;
            }
        }
        return null;
    }

    protected TreeExplorer createTreeExplorer(final Composite mainComposite) {
        final TreeExplorer explorer = new TreeExplorer(mainComposite, SWT.NONE);
        explorer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 290).span(2, 1).create());

        final Composite additionalComposite = explorer.getAdditionalComposite();
        additionalComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        onlyCustomCheckbox = new Button(additionalComposite, SWT.CHECK);
        onlyCustomCheckbox.setText(Messages.onlyCustomConnector);
        onlyCustomCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        onlyCustomCheckbox.setSelection(checkOnlyCustom);

        final ITreeContentProvider contentProvider = getContentProvider();
        final ITreeContentProvider customContentProvider = getCustomContentProvider();
        explorer.setContentProvider(customContentProvider);
        explorer.setLabelProvider(new ConnectorDefinitionExplorerLabelProvider(messageProvider));
        explorer.addRightTreeFilter(customConnectorFilter);
        explorer.addLeftTreeFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (AbstractUniqueDefinitionContentProvider.ROOT.equals(element)) {
                    return true;
                }
                if (element instanceof Category) {
                    if (!((ITreeContentProvider) ((ContentViewer) viewer).getContentProvider()).hasChildren(element)) {
                        return false;
                    }
                    for (final Object c : ((ITreeContentProvider) ((ContentViewer) viewer).getContentProvider())
                            .getChildren(element)) {
                        if (c instanceof ConnectorDefinition) {
                            return true;
                        } else {
                            if (select(viewer, element, c)) {
                                return true;
                            }
                        }
                    }
                } else if (element instanceof ConnectorDefinition) {
                    return false;

                }
                return false;
            }
        });

        explorer.addRightTreeFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return element instanceof ConnectorDefinition;
            }
        });
        explorer.setLeftHeader(Messages.categoriesLabel);
        explorer.setRightHeader(getRightHeaderMessage());
        explorer.setInput(new Object());
        explorer.geLeftTreeViewer().setExpandedElements(new Object[] { AbstractUniqueDefinitionContentProvider.ROOT });
        onlyCustomCheckbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateOnlyCustomCheckbox();
            }
        });
        final Object[] rootElement = contentProvider.getElements(new Object());
        final List<Object> flattenTree = new ArrayList<Object>();
        getFlattenTree(flattenTree, rootElement, contentProvider);
        explorer.getRightTableViewer().setInput(flattenTree);
        explorer.getRightTableViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                getContainer().showPage(getNextPage());
            }
        });
        return explorer;
    }

    protected String getRightHeaderMessage() {
        return Messages.connectorDefinition;
    }

    private void updateOnlyCustomCheckbox() {
        final ITreeContentProvider customContentProvider = getCustomContentProvider();
        final ITreeContentProvider contentProvider = getContentProvider();
        if (onlyCustomCheckbox.getSelection()) {
            explorer.setContentProvider(customContentProvider);
            explorer.addRightTreeFilter(customConnectorFilter);
        } else {
            explorer.setContentProvider(contentProvider);
            explorer.removeRightTreeFilter(customConnectorFilter);
        }
        explorer.setInput(new Object());
        explorer.geLeftTreeViewer().setExpandedElements(new Object[] { AbstractUniqueDefinitionContentProvider.ROOT });
    }

    private void getFlattenTree(final List<Object> flattenTree, final Object[] rootElement,
            final ITreeContentProvider contentProvider) {
        for (final Object element : rootElement) {
            flattenTree.add(element);
            if (contentProvider.hasChildren(element)) {
                getChildrenFlattenTree(flattenTree, element, contentProvider);
            }
        }

    }

    private void getChildrenFlattenTree(final List<Object> flattenTree, final Object parentElement,
            final ITreeContentProvider contentProvider) {
        for (final Object element : contentProvider.getChildren(parentElement)) {
            flattenTree.add(element);
            if (contentProvider.hasChildren(element)) {
                getChildrenFlattenTree(flattenTree, element, contentProvider);
            }
        }
    }

    protected abstract ITreeContentProvider getContentProvider();

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible && pageSupport == null) {
            pageSupport = WizardPageSupport.create(this, context);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        if (context != null) {
            context.dispose();
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {

    }

    protected abstract ITreeContentProvider getCustomContentProvider();

    protected abstract void bindValue();

    public void setSelectedConnectorDefinition(final ConnectorDefinition selectedDefinition) {
        this.selectedDefinition = selectedDefinition;
    }

    public ConnectorDefinition getSelectedConnectorDefinition() {
        return selectedDefinition;
    }
}
