/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.views.extension.card.zoom.usage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.dashboard.ProjectDashboardEditorPart;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectViewerComparator;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICheckable;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ConnectorUsagesControlSupplier implements ControlSupplier {

    private Composite composite;
    protected ExtendedConnectorDefinition definition;
    private IWizardContainer wizardContainer;
    private IViewerObservableValue<Element> selectionObservable;
    private CheckboxTreeViewer viewer;
    private DataBindingContext ctx;
    private IObservableSet<Element> checkedElements = new WritableSet<>();
    private List<Element> elementsToFilter = new ArrayList<>();
    private Map<AbstractProcess, List<Element>> usages;
    private Set<AbstractProcess> expandedProcesses = new HashSet<>();

    public ConnectorUsagesControlSupplier(ExtendedConnectorDefinition definition) {
        this.definition = definition;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;
        this.ctx = ctx;
        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var descriptionComposite = new Composite(composite, SWT.NONE);
        descriptionComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 5).create());
        descriptionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var description = new Label(descriptionComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        description.setText(getDescriptionText());

        ctx.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                return checkedElements.isEmpty()
                        ? ValidationStatus.error("")
                        : ValidationStatus.ok();
            }
        });

        return composite;
    }

    protected String getDescriptionText() {
        return String.format(Messages.connectorUsagesDescription, definition.getConnectorDefinitionLabel());
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        Display.getDefault().asyncExec(() -> {
            try {
                wizardContainer.run(true, false, this::findUsages);
            } catch (InvocationTargetException | InterruptedException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }
        });
    }

    private void findUsages(IProgressMonitor monitor) {
        monitor.beginTask(Messages.findingUsages, IProgressMonitor.UNKNOWN);
        usages = computeUsages();

        Display.getDefault().syncExec(() -> {
            var viewerComposite = new Composite(composite, SWT.NONE);
            viewerComposite.setLayout(
                    GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).numColumns(2)
                            .margins(5, 5)
                            .create());
            viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            if (!usages.isEmpty()) {
                createSearchField(viewerComposite);
                createToolbar(viewerComposite);
                createUsagesTree(viewerComposite, usages);
            } else {
                createNoUsagesLabel(viewerComposite);
            }
            composite.layout();
        });

        monitor.done();
    }

    protected Map<AbstractProcess, List<Element>> computeUsages() {
        Map<AbstractProcess, List<Element>> connectorUsages = new HashMap<>();
        RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class).getAllProcesses()
                .stream()
                .forEach(p -> ModelHelper.getAllElementOfTypeIn(p, Connector.class)
                        .stream()
                        .filter(c -> !(c instanceof ActorFilter))// COnnectableEelement
                        .filter(c -> Objects.equals(c.getDefinitionId(), definition.getId()))
                        .filter(c -> Objects.equals(c.getDefinitionVersion(), definition.getVersion()))
                        .map(EObject::eContainer)
                        .filter(ConnectableElement.class::isInstance)
                        .map(ConnectableElement.class::cast)
                        .distinct()
                        .forEach(element -> {
                            if (!connectorUsages.containsKey(p)) {
                                connectorUsages.put(p, new ArrayList<>());
                            }
                            if (!Objects.equals(p, element)) {
                                connectorUsages.get(p).add(element);
                            }
                        }));
        return connectorUsages;
    }

    private void createNoUsagesLabel(Composite viewerComposite) {
        var noUsagesLabel = new Label(viewerComposite, SWT.WRAP);
        noUsagesLabel
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.FILL).create());
        noUsagesLabel.setText(String.format(Messages.noConnectorUsagesFound, definition.getConnectorDefinitionLabel()));
        noUsagesLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.ITALIC_0_FONT_ID));
    }

    protected void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> Display.getDefault().asyncExec(() -> {
            String search = searchObservableValue.getValue().toLowerCase();
            List<AbstractProcess> expandedElements = Arrays.asList(viewer.getExpandedElements()).stream()
                    .filter(AbstractProcess.class::isInstance)
                    .map(AbstractProcess.class::cast)
                    .collect(Collectors.toList());
            expandedProcesses.removeIf(process -> !expandedElements.contains(process)
                    && usages.get(process).stream().anyMatch(elt -> !elementsToFilter.contains(elt)));
            expandedProcesses.addAll(expandedElements);

            elementsToFilter.clear();
            usages.entrySet().forEach(entry -> {
                long filteredElementsCount = entry.getValue().stream()
                        .filter(elt -> !elt.getName().toLowerCase().contains(search))
                        .peek(elementsToFilter::add)
                        .count();
                if (filteredElementsCount == entry.getValue().size()
                        && !entry.getKey().getName().toLowerCase().contains(search)) {
                    elementsToFilter.add(entry.getKey());
                }
            });
            viewer.refresh();
            expandedProcesses.forEach(elt -> viewer.expandToLevel(elt, 1));
        }));
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !elementsToFilter.contains(element);
            }
        };
    }

    protected TextWidget createSearchWidget(Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 0).create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.search)
                .createIn(searchComposite);
    }

    private void createToolbar(Composite parent) {
        var toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 0).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create());

        ToolBar toolBar = new ToolBar(toolbarComposite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        ToolItem expandItem = new ToolItem(toolBar, SWT.PUSH);
        expandItem.setImage(Pics.getImage(PicsConstants.expandAll));
        expandItem.setToolTipText(Messages.expandAll);
        expandItem.addListener(SWT.Selection, e -> viewer.expandAll());

        ToolItem collapseItem = new ToolItem(toolBar, SWT.PUSH);
        collapseItem.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseItem.setToolTipText(Messages.collapseAll);
        collapseItem.addListener(SWT.Selection, e -> viewer.collapseAll());
    }

    private void createUsagesTree(Composite parent, Map<AbstractProcess, List<Element>> usages) {
        var treeComposite = new Composite(parent, SWT.NONE);
        treeComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 0).create());
        treeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        viewer = new CheckboxTreeViewer(treeComposite,
                SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.addDoubleClickListener(e -> {
            if (selectionObservable.getValue() instanceof AbstractProcess) {
                viewer.setExpandedState(selectionObservable.getValue(),
                        !viewer.getExpandedState(selectionObservable.getValue()));
            } else {
                viewer.setChecked(selectionObservable.getValue(), !viewer.getChecked(selectionObservable.getValue()));
            }
        });

        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);
        viewer.setContentProvider(new ConnectorUsageTreeContentProvider(usages));
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(
                new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)));
        viewer.setComparator(new BusinessObjectViewerComparator());
        viewer.setFilters(createSearchFilter());
        viewer.setInput(usages);
        selectionObservable = ViewerProperties.singleSelection(Element.class).observe(viewer);
        ctx.bindSet(ViewerProperties.checkedElements(Element.class).observe((ICheckable) viewer), checkedElements);
        viewer.expandAll();
        treeComposite.layout();
    }

    public Set<Element> getCheckedElements() {
        return checkedElements;
    }

}
