/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.MandatoryEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.ReadOnlyEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.WidgetTypeEditingSupport;
import org.bonitasoft.studio.properties.sections.forms.wizard.filters.BusinessDataMappingViewerFilter;
import org.bonitasoft.studio.properties.sections.forms.wizard.filters.ProcessDataMappingViewerFilter;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.MandatoryCheckboxLabelProvider;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.ReadOnlyCheckboxLabelProvider;
import org.bonitasoft.studio.properties.sections.forms.wizard.provider.WidgetMappingTreeContentProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

public class SelectGeneratedWidgetsWizardPage extends WizardSelectionPage implements ICheckStateListener {

    private static final int MAX_DEPTH = 2;

    protected DataBindingContext databindingContext;

    private String formName;

    private String formDescription;

    protected List<EObject> inputElements;

    private IViewerObservableSet observeCheckedElements;

    private final BusinessObjectModelRepositoryStore store;

    private boolean initialValueGenerated;

    private List<? extends WidgetMapping> processDataInput;

    private List<? extends WidgetMapping> businessDataInput;

    private final boolean isInstanciationForm;

    public SelectGeneratedWidgetsWizardPage(final Element pageFlow, final String defautFormName,
            final List<EObject> inputElements,
            final BusinessObjectModelRepositoryStore store) {
        super(SelectGeneratedWidgetsWizardPage.class.getName());
        setFormName(defautFormName);
        initialValueGenerated = false;
        isInstanciationForm = pageFlow instanceof Pool;
        this.inputElements = inputElements;
        this.store = store;
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
        descriptionText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).hint(SWT.DEFAULT, 45).create());
        databindingContext.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify),
                PojoProperties.value("formDescription").observe(this));

        if (hasWidgetToGenerated()) {
            createWidgetSelectionGroup(composite);
        } else {
            final Label title = new Label(composite, SWT.CENTER);
            title.setText(Messages.createForm_noData);
        }

        WizardPageSupport.create(this, databindingContext);
        setControl(composite);
    }

    protected Collection<? extends EObject> filterBusinessData(
            final List<EObject> inputElements) {
        final List<EObject> filterdInputElements = new ArrayList<>();
        for (final EObject in : inputElements) {
            if (!(in instanceof BusinessObjectData)) {
                filterdInputElements.add(in);
            }
        }
        return filterdInputElements;
    }

    protected void createProcessDataTab(final TabFolder tabFolder) {
        final TabItem processDataItem = new TabItem(tabFolder, SWT.BORDER);
        processDataItem.setText(Messages.processData);
        final Composite treeContainerComposite = createTreeContainer(tabFolder);
        processDataInput = asWidgetMappingList(filterBusinessData(inputElements));
        if (!processDataInput.isEmpty()) {
            final CheckboxTreeViewer viewer = createProcessDataMappingTreeViewer(treeContainerComposite, processDataInput);
            viewer.addFilter(new BusinessDataMappingViewerFilter());
        } else {
            final Label noDataLabel = new Label(treeContainerComposite, SWT.NONE);
            noDataLabel.setText(Messages.noProcessDataAvailable);
        }
        processDataItem.setControl(treeContainerComposite);
    }

    private TabFolder createTabFolder(final Composite composite) {
        final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return tabFolder;
    }

    protected void createWidgetSelectionGroup(final Composite composite) {
        final TabFolder tabFolder = createTabFolder(composite);
        createBusinessDataTab(tabFolder);
        createProcessDataTab(tabFolder);
    }

    protected void createBusinessDataTab(final TabFolder tabFolder) {
        final TabItem businessDataItem = new TabItem(tabFolder, SWT.BORDER);
        businessDataItem.setText(Messages.businessData);
        final Composite businessTreeContainerComposite = createTreeContainer(tabFolder);
        businessDataInput = asWidgetMappingList(filterProcessData(inputElements));
        if (!businessDataInput.isEmpty()) {
            final CheckboxTreeViewer viewer = createProcessDataMappingTreeViewer(businessTreeContainerComposite,
                    businessDataInput);
            viewer.addFilter(new ProcessDataMappingViewerFilter());
            final Button generateInitialValueButton = new Button(businessTreeContainerComposite, SWT.CHECK);
            generateInitialValueButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());
            generateInitialValueButton.setText(Messages.generateInitialValueForBusinessData);
            generateInitialValueButton.setEnabled(!isInstanciationForm);
            databindingContext.bindValue(SWTObservables.observeSelection(generateInitialValueButton),
                    PojoObservables.observeValue(this, "initialValueGenerated"));

            final ControlDecoration hint = new ControlDecoration(generateInitialValueButton, SWT.RIGHT);
            hint.setShowOnlyOnFocus(false);
            hint.setImage(Pics.getImage(PicsConstants.hint));
            hint.setDescriptionText(Messages.generateInitialValueForBusinessDataHint);

        } else {
            final Label noDataLabel = new Label(businessTreeContainerComposite, SWT.NONE);
            noDataLabel.setText(Messages.noBusinessDataAvailable);
        }
        businessDataItem.setControl(businessTreeContainerComposite);
    }

    protected Collection<? extends EObject> filterProcessData(
            final List<EObject> inputElements) {
        final List<EObject> filterdInputElements = new ArrayList<>();
        for (final EObject in : inputElements) {
            if (in instanceof BusinessObjectData) {
                filterdInputElements.add(in);
            }
        }
        return filterdInputElements;
    }

    protected CheckboxTreeViewer createProcessDataMappingTreeViewer(
            final Composite treeContainerComposite, final List<? extends WidgetMapping> input) {

        final Button selectAllCheckbox = new Button(treeContainerComposite, SWT.CHECK);
        selectAllCheckbox.setText(Messages.selectAll);

        final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(treeContainerComposite,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        final WidgetMappingTreeContentProvider widgetMappingTreeContentProvider = new WidgetMappingTreeContentProvider();
        treeViewer.setContentProvider(widgetMappingTreeContentProvider);
        treeViewer.addCheckStateListener(this);
        treeViewer.setCheckStateProvider(getCheckStateProvider(treeViewer));

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
                if (element != null) {
                    cell.setText(widgetTypeEditingSupport.getText(element.eClass(), mapping));
                }

            }
        });

        final TreeViewerColumn mandatoryTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.CENTER);
        mandatoryTreeViewerColumn.getColumn().setText(Messages.mandatory);
        mandatoryTreeViewerColumn.getColumn().setWidth(90);
        mandatoryTreeViewerColumn
                .setLabelProvider(new MandatoryCheckboxLabelProvider(mandatoryTreeViewerColumn.getViewer()));
        mandatoryTreeViewerColumn.setEditingSupport(createMandatoryEditingSupport(treeViewer));

        final TreeViewerColumn readOnlyTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.CENTER);
        readOnlyTreeViewerColumn.getColumn().setText(Messages.readOnly);
        readOnlyTreeViewerColumn.getColumn().setWidth(90);
        readOnlyTreeViewerColumn.setLabelProvider(new ReadOnlyCheckboxLabelProvider(readOnlyTreeViewerColumn.getViewer()));
        readOnlyTreeViewerColumn.setEditingSupport(createReadOnlyEditingSupport(treeViewer));

        final WritableValue dataAndDocumentList = new WritableValue();
        dataAndDocumentList.setValue(input);
        databindingContext.bindValue(ViewersObservables.observeInput(treeViewer), dataAndDocumentList);

        observeCheckedElements = ViewersObservables.observeCheckedElements(treeViewer, WidgetMapping.class);
        observeCheckedElements.addSetChangeListener(new ISetChangeListener() {

            @Override
            public void handleSetChange(final SetChangeEvent event) {
                updateGeneratedMappings(event);
                final List<WidgetMapping> allMappings = new ArrayList<>();
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
                for (final WidgetMapping mapping : input) {
                    treeViewer.setSubtreeChecked(mapping, selectAllCheckbox.getSelection());
                    setMappingEnabledRecursivly(mapping, selectAllCheckbox.getSelection());
                    treeViewer.setGrayed(mapping, isGrayed(treeViewer, mapping));
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

    protected boolean isGrayed(final CheckboxTreeViewer checkboxTreeViewer, final WidgetMapping mapping) {
        boolean grayed = false;
        WidgetMapping map = mapping;
        if (mapping.getParent() != null) {
            map = mapping.getParent();
        }
        for (final WidgetMapping c : map.getChildren()) {
            if (!checkboxTreeViewer.getChecked(c)) {
                grayed = true;
            }
        }
        return grayed;
    }

    protected ICheckStateProvider getCheckStateProvider(final CheckboxTreeViewer viewer) {
        return new ICheckStateProvider() {

            @Override
            public boolean isChecked(final Object element) {
                return ((WidgetMapping) element).isGenerated();
            }

            @Override
            public boolean isGrayed(final Object element) {
                return SelectGeneratedWidgetsWizardPage.this.isGrayed(viewer, (WidgetMapping) element);
            }
        };
    }

    private void createWidgetTypeEditors(final CheckboxTreeViewer treeViewer,
            final WidgetTypeEditingSupport widgetTypeEditingSupport,
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
        final CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) event.getSource();
        checkboxTreeViewer.setSubtreeChecked(mapping, event.getChecked());

        // DESELECT PARENT IF NO CHILD SELECTED
        if (!event.getChecked() && mapping.getParent() != null) {
            boolean deselect = true;
            for (final WidgetMapping m : mapping.getParent().getChildren()) {
                if (checkboxTreeViewer.getChecked(m)) {
                    deselect = false;
                }
            }
            // ALL CHILD ARE UNCHECKED
            if (deselect) {
                checkboxTreeViewer.setChecked(mapping.getParent(), deselect);
            }
        }

        //COMPUTE GRAYED
        WidgetMapping map = mapping;
        if (mapping.getParent() != null) {
            map = mapping.getParent();
        }
        checkboxTreeViewer.setGrayed(map, isGrayed(checkboxTreeViewer, map));
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
        mapping.setGenerated(isGenerated && mapping.getChildren().isEmpty());
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
                } else if (element instanceof Field) {
                    cell.setText(((Field) element).getName());
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
        final ArrayList<WidgetMapping> result = new ArrayList<>();
        for (final EObject e : elements) {
            final WidgetMapping mappingForModelElement = createMappingForModelElement(e);
            if (mappingForModelElement != null) {
                result.add(mappingForModelElement);
            }
        }
        return result;
    }

    protected WidgetMapping createMappingForModelElement(final EObject modelElement) {
        final WidgetMapping widgetMapping = createWidgetMapping(modelElement);
        if (modelElement instanceof BusinessObjectData) {
            final BusinessObject bo = getBusinessObjectFromData((BusinessObjectData) modelElement);
            if (bo == null) {
                return null;
            }
            addChildMapping(widgetMapping, bo, 0);
        }
        return widgetMapping;
    }

    protected WidgetMapping createWidgetMapping(final EObject modelElement) {
        return new WidgetMapping(modelElement);
    }

    protected WidgetMapping createWidgetMapping(final Field modelElement) {
        return new WidgetMapping(modelElement);
    }

    protected void addChildMapping(final WidgetMapping mapping, final BusinessObject bo, final int deepLevel) {
        for (final Field field : bo.getFields()) {
            if (field instanceof SimpleField) {
                if (deepLevel != MAX_DEPTH - 1) {
                    final WidgetMapping childMapping = createWidgetMapping(field);
                    mapping.addChild(childMapping);
                    // int nextDeepLevel = getDeepLevel(childMapping);
                    // if(field.getType() instanceof EClass && deepLevel < MAX_DEPTH){
                    // addChildMapping(childMapping, (EClass) field.getEType(),nextDeepLevel);
                    // }
                }
            }
        }
    }

    protected BusinessObject getBusinessObjectFromData(final BusinessObjectData currentData) {
        final String className = currentData.getClassName();
        Assert.isNotNull(className, "className");

        final Optional<BusinessObjectModelFileStore> fileStore = store.getChildByQualifiedName(className);
        if (!fileStore.isPresent()) {
            return null;
        }

        final BusinessObject bo = fileStore.get().getBusinessObject(currentData.getClassName());
        Assert.isNotNull(bo, "BusinessObject");
        return bo;
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
        final List<WidgetMapping> result = new ArrayList<>();
        if (processDataInput != null) {
            result.addAll(processDataInput);
        }
        if (businessDataInput != null) {
            result.addAll(businessDataInput);
        }
        return result;
    }

    public boolean isInitialValueGenerated() {
        return initialValueGenerated;
    }

    public void setInitialValueGenerated(final boolean isInitialValueGenerated) {
        initialValueGenerated = isInitialValueGenerated;
    }

}
