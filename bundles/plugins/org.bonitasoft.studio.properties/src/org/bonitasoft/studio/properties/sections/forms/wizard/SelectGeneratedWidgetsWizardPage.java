/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.MandatoryEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.ReadOnlyEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.WidgetTypeEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.MandatoryCheckboxLabelProvider;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.ReadOnlyCheckboxLabelProvider;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.WidgetMappingTreeContentProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 * This Wizard page is the second page of the wizard which allow to select
 * multiples variables to generate a default Form
 * 
 * @author Baptiste Mesta
 * 
 */
public class SelectGeneratedWidgetsWizardPage extends WizardSelectionPage implements ICheckStateListener {

    protected DataBindingContext databindingContext;

    private String formName;

    private String formDescription;

    protected List<EObject> inputElements;

    private IViewerObservableSet observeCheckedElements;

    private List<? extends WidgetMapping> widgetMappingList;

    public SelectGeneratedWidgetsWizardPage(final String defautFormName, final List<EObject> inputElements) {
        super(SelectGeneratedWidgetsWizardPage.class.getName());
        setFormName(defautFormName);
        this.inputElements = inputElements;
    }

    @Override
    public void createControl(final Composite parent) {
        databindingContext = new DataBindingContext();
        // main composite
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));

        // ----- TOP
        final Composite topComposite = new Composite(composite, SWT.NONE);
        topComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10, 5).create());
        topComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        final Label nameLabel = new Label(topComposite, SWT.LEFT);
        nameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.GeneralSection_Name);

        final Text nameText = new Text(topComposite, SWT.BORDER);

        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final UpdateValueStrategy stratetgy = new UpdateValueStrategy();
        stratetgy.setBeforeSetValidator(new InputLengthValidator(Messages.name, 50));
        final ISWTObservableValue observeText = SWTObservables.observeText(nameText, SWT.Modify);
        observeText.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                getContainer().updateButtons();
            }
        });
        databindingContext.bindValue(observeText, PojoProperties.value("formName").observe(this), stratetgy, null);

        // ------------ Description
        final Label descLabel = new Label(topComposite, SWT.LEFT);
        descLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).create());
        descLabel.setText(Messages.GeneralSection_Description);

        final Text descriptionText = new Text(topComposite, SWT.MULTI + SWT.BORDER);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).hint(SWT.DEFAULT, 45).create());
        databindingContext.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify), PojoProperties.value("formDescription").observe(this));

        if (hasWidgetToGenerated()) {
            createWidgetSelectionGroup(composite);
        } else {
            final Label title = new Label(composite, SWT.CENTER);
            title.setText(Messages.createForm_noData);
        }

        WizardPageSupport.create(this, databindingContext);
        setControl(composite);
    }

    protected void createWidgetSelectionGroup(final Composite composite) {
        final Group parentGroup = createWidgetGroup(composite);
        final Composite treeContainerComposite = createTreeContainer(parentGroup);
        widgetMappingList = asWidgetMappingList(inputElements);
        createProcessDataMappingTreeViewer(treeContainerComposite, widgetMappingList);
    }

    protected CheckboxTreeViewer createProcessDataMappingTreeViewer(
            final Composite treeContainerComposite, final List<? extends WidgetMapping> input) {

        final Button selectAllCheckbox = new Button(treeContainerComposite, SWT.CHECK);
        selectAllCheckbox.setText(Messages.selectAll);

        final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(treeContainerComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.setContentProvider(new WidgetMappingTreeContentProvider());
        treeViewer.addCheckStateListener(this);

        final TreeViewerColumn nameTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        nameTreeViewerColumn.getColumn().setText(Messages.name);
        nameTreeViewerColumn.getColumn().setWidth(250);
        nameTreeViewerColumn.setLabelProvider(createNameColumnLabelProvider());

        final TreeViewerColumn widgetTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        widgetTreeViewerColumn.getColumn().setText(Messages.wiget);
        widgetTreeViewerColumn.getColumn().setWidth(200);

        final WidgetTypeEditingSupport widgetTypeEditingSupport = createWidgetTypeEditingSupport(treeViewer);
        widgetTreeViewerColumn.setLabelProvider(new CellLabelProvider() {

            @Override
            public void update(final ViewerCell cell) {
                final WidgetMapping mapping = (WidgetMapping) cell.getElement();
                final Widget element = ((WidgetMapping) cell.getElement()).getWidgetType();
                cell.setText(widgetTypeEditingSupport.getText(element.eClass(), mapping));
            }
        });
        //        widgetTreeViewerColumn.setEditingSupport(widgetTypeEditingSupport);

        final TreeViewerColumn mandatoryTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.CENTER);
        mandatoryTreeViewerColumn.getColumn().setText(Messages.mandatory);
        mandatoryTreeViewerColumn.getColumn().setWidth(90);
        mandatoryTreeViewerColumn.setLabelProvider(new MandatoryCheckboxLabelProvider(treeViewer.getControl()));
        mandatoryTreeViewerColumn.setEditingSupport(createMandatoryEditingSupport(treeViewer));

        final TreeViewerColumn readOnlyTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.CENTER);
        readOnlyTreeViewerColumn.getColumn().setText(Messages.readOnly);
        readOnlyTreeViewerColumn.getColumn().setWidth(90);
        readOnlyTreeViewerColumn.setLabelProvider(new ReadOnlyCheckboxLabelProvider(treeViewer.getControl()));
        readOnlyTreeViewerColumn.setEditingSupport(createReadOnlyEditingSupport(treeViewer));

        final WritableValue dataAndDocumentList = new WritableValue();
        dataAndDocumentList.setValue(input);
        databindingContext.bindValue(ViewersObservables.observeInput(treeViewer), dataAndDocumentList);

        observeCheckedElements = ViewersObservables.observeCheckedElements(treeViewer, WidgetMapping.class);
        observeCheckedElements.addSetChangeListener(new ISetChangeListener() {

            @Override
            public void handleSetChange(final SetChangeEvent event) {
                updateGeneratedMappings(event);
                final List<WidgetMapping> allMappings = new ArrayList<WidgetMapping>();
                fillAllMappings(allMappings, input);
                selectAllCheckbox.setSelection(event.getObservableSet().size() == allMappings.size());
            }

        });
        selectAllCheckbox.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (selectAllCheckbox.getSelection()) {
                    for (final WidgetMapping mapping : input) {
                        treeViewer.setSubtreeChecked(mapping, true);
                        setMappingEnabledRecursivly(mapping, true);
                    }
                } else {
                    for (final WidgetMapping mapping : input) {
                        treeViewer.setSubtreeChecked(mapping, false);
                        setMappingEnabledRecursivly(mapping, false);
                    }
                }
            }
        });

        if (treeViewer.getSelection().isEmpty()) {
            treeViewer.getTree().select(treeViewer.getTree().getItem(0));
        }
        treeViewer.expandAll();
        createWidgetTypeEditors(treeViewer, widgetTypeEditingSupport, treeViewer.getTree().getItems());
        return treeViewer;
    }

    private void createWidgetTypeEditors(final CheckboxTreeViewer treeViewer, final WidgetTypeEditingSupport widgetTypeEditingSupport,
            final TreeItem[] treeItems) {
        for (final TreeItem ti : treeItems) {
            final TreeEditor editor = new TreeEditor(treeViewer.getTree());
            editor.grabHorizontal = true;
            editor.horizontalAlignment = SWT.FILL;
            editor.setEditor(widgetTypeEditingSupport.createControl(ti.getData()), ti, 1);
            createWidgetTypeEditors(treeViewer, widgetTypeEditingSupport, ti.getItems());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
     */
    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final WidgetMapping mapping = (WidgetMapping) event.getElement();

        // SELECT/DESELECT ALL CHILDREN
        ((CheckboxTreeViewer) event.getSource()).setSubtreeChecked(mapping, event.getChecked());

        // DESELECT PARENT IF NO CHILD SELECTED
        if (!event.getChecked() && mapping.getParent() != null) {
            for (final WidgetMapping m : mapping.getParent().getChildren()) {
                if (((CheckboxTreeViewer) event.getSource()).getChecked(m)) {
                    return;
                }
            }
            // ALL CHILD ARE UNCHECKED
            ((CheckboxTreeViewer) event.getSource()).setChecked(mapping.getParent(), false);
        }
    }

    protected void fillAllMappings(final List<WidgetMapping> allMappings,
            final List<? extends WidgetMapping> mappings) {
        allMappings.addAll(mappings);
        for (final WidgetMapping child : mappings) {
            fillAllMappings(allMappings, child.getChildren());
        }
    }

    protected void updateGeneratedMappings(final SetChangeEvent event) {
        for (final Object added : event.diff.getAdditions()) {
            if (added instanceof WidgetMapping) {
                setMappingEnabledRecursivly((WidgetMapping) added, true);
            }
        }
        for (final Object removed : event.diff.getRemovals()) {
            if (removed instanceof WidgetMapping) {
                setMappingEnabledRecursivly((WidgetMapping) removed, false);
            }
        }
    }

    protected void setMappingEnabledRecursivly(final WidgetMapping mapping, final boolean isGenerated) {
        mapping.setGenerated(isGenerated);
        for (final WidgetMapping m : mapping.getChildren()) {
            setMappingEnabledRecursivly(m, isGenerated);
        }
    }

    protected CellLabelProvider createNameColumnLabelProvider() {
        return new CellLabelProvider() {

            @Override
            public void update(final ViewerCell cell) {
                final Object element = ((WidgetMapping) cell.getElement()).getModelElement();
                if (element instanceof Element) {
                    cell.setText(((Element) element).getName());
                }
            }
        };
    }

    protected EditingSupport createMandatoryEditingSupport(
            final ColumnViewer columnViewerViewer) {
        return new MandatoryEditingSupport(columnViewerViewer);
    }

    protected WidgetTypeEditingSupport createWidgetTypeEditingSupport(
            final ColumnViewer columnViewerViewer) {
        return new WidgetTypeEditingSupport(columnViewerViewer);
    }

    protected EditingSupport createReadOnlyEditingSupport(
            final ColumnViewer columnViewerViewer) {
        return new ReadOnlyEditingSupport(columnViewerViewer);
    }

    protected List<? extends WidgetMapping> asWidgetMappingList(final Collection<? extends EObject> elements) {
        final ArrayList<WidgetMapping> result = new ArrayList<WidgetMapping>();
        for (final EObject e : elements) {
            final WidgetMapping mappingForModelElement = createMappingForModelElement(e);
            if (mappingForModelElement != null) {
                result.add(mappingForModelElement);
            }
        }
        return result;
    }

    protected WidgetMapping createMappingForModelElement(final EObject modelElement) {
        return new WidgetMapping(modelElement);
    }

    protected Composite createTreeContainer(final Composite parent) {
        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return container;
    }

    protected Group createWidgetGroup(final Composite composite) {
        final Group datasGrp = new Group(composite, SWT.NONE);
        datasGrp.setText(Messages.FormsSection_wizardVarsGroup_Title);
        datasGrp.setToolTipText(Messages.FormsSection_wizardVarsGroup_Tooltip);
        datasGrp.setLayout(new GridLayout(1, false));
        datasGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        datasGrp.setSize(datasGrp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return datasGrp;
    }

    protected boolean hasWidgetToGenerated() {
        return !inputElements.isEmpty();
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(final String name) {
        formName = name;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(final String description) {
        formDescription = description;
    }

    public List<? extends WidgetMapping> getWidgetMappings() {
        final List<WidgetMapping> result = new ArrayList<WidgetMapping>();
        if (widgetMappingList != null) {
            result.addAll(widgetMappingList);
        }
        return result;
    }

}
