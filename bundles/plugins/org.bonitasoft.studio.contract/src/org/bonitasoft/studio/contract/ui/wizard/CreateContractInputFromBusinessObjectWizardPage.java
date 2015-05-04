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
package org.bonitasoft.studio.contract.ui.wizard;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Function;

/**
 * @author aurelie
 */
public class CreateContractInputFromBusinessObjectWizardPage extends WizardPage implements ICheckStateListener, ICheckStateProvider {

    private final WritableValue selectedDataObservable;
    private CheckboxTreeViewer treeViewer;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private List<FieldToContractInputMapping> mappings;
    private String rootName;
    private final Contract contract;

    /**
     * @param selectedDataObservable
     * @param businessObjectStore
     * @param pageName
     */
    protected CreateContractInputFromBusinessObjectWizardPage(final Contract contract, final WritableValue selectedDataObservable,
            final FieldToContractInputMappingFactory fieldToContractInputMappingFactory) {
        super(CreateContractInputFromBusinessObjectWizardPage.class.getName());
        setTitle(Messages.selectFieldToGenerateTitle);
        setDescription(Messages.selectFieldToGenerateDescription);
        this.selectedDataObservable = selectedDataObservable;
        this.fieldToContractInputMappingFactory = fieldToContractInputMappingFactory;
        this.contract = contract;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createRootNameControl(dbc, composite);
        createProcessDataMappingTreeViewer(composite, dbc);
        setControl(composite);
        WizardPageSupport.create(this, dbc);
    }

    /**
     * @param dbc
     * @param composite
     */
    private void createRootNameControl(final EMFDataBindingContext dbc, final Composite composite) {
        final Composite rootNameComposite = new Composite(composite, SWT.NONE);
        rootNameComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        rootNameComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Label label = new Label(rootNameComposite, SWT.NONE);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        label.setText(Messages.rootContractInputName);
        final Text prefixText = new Text(rootNameComposite, SWT.BORDER);
        prefixText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final IObservableValue prefixObservable = PojoObservables.observeValue(this, "rootName");
        dbc.bindValue(prefixObservable,
                EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME),
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(dataNameToRootContractInputName()).create());
        dbc.bindValue(SWTObservables.observeText(prefixText, SWT.Modify),
                prefixObservable, updateValueStrategy().withValidator(uniqueValidator().onProperty("name").in(contract.getInputs())).create(),
                null);
    }

    /**
     * @return
     */
    private IConverter dataNameToRootContractInputName() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                final String className = (String) EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable,
                        ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME).getValue();
                if (className != null) {
                    final String name = fromObject + NamingUtils.getSimpleName(className);
                    return NamingUtils.generateNewName(newHashSet(transform(contract.getInputs(), toContactInputName())), name, 0);
                }
                return "";
            }
        };
    }

    private Function<ContractInput, String> toContactInputName() {
        return new Function<ContractInput, String>() {

            @Override
            public String apply(final ContractInput input) {
                return input.getName();
            }
        };
    }

    private void createProcessDataMappingTreeViewer(final Composite composite, final EMFDataBindingContext dbc) {
        treeViewer = new CheckboxTreeViewer(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.addCheckStateListener(this);
        treeViewer.setCheckStateProvider(this);
        treeViewer.setContentProvider(new ObservableListTreeContentProvider(new IObservableFactory() {

            @Override
            public IObservable createObservable(final Object target) {
                if (target instanceof List<?>) {
                    return new WritableList((List<?>) target, FieldToContractInputMapping.class);
                } else if (target instanceof RelationFieldToContractInputMapping) {
                    return new WritableList(((RelationFieldToContractInputMapping) target).getChildren(), FieldToContractInputMapping.class);
                }
                return new WritableList();
            }
        }, new TreeStructureAdvisor() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.databinding.viewers.TreeStructureAdvisor#getParent(java.lang.Object)
             */
            @Override
            public Object getParent(final Object element) {
                return ((FieldToContractInputMapping) element).getParent();
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.databinding.viewers.TreeStructureAdvisor#hasChildren(java.lang.Object)
             */
            @Override
            public Boolean hasChildren(final Object element) {
                return !((FieldToContractInputMapping) element).getChildren().isEmpty();
            }
        }));

        final TreeViewerColumn nameTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        nameTreeViewerColumn.getColumn().setText(Messages.name);
        nameTreeViewerColumn.getColumn().setWidth(250);
        nameTreeViewerColumn.setLabelProvider(createNameColumnLabelProvider());

        final TreeViewerColumn typeTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        typeTreeViewerColumn.getColumn().setText(Messages.type);
        typeTreeViewerColumn.getColumn().setWidth(250);
        typeTreeViewerColumn.setLabelProvider(createTypeColumnLabelProvider());

        dbc.bindValue(ViewersObservables.observeInput(treeViewer),
                selectedDataObservable,
                null,
                updateValueStrategy().withConverter(selectedDataToFieldMappings()).create());
    }

    private IConverter selectedDataToFieldMappings() {
        return new Converter(BusinessObjectData.class, List.class) {

            @Override
            public Object convert(final Object selectedData) {
                if (selectedData == null) {
                    return Collections.emptyList();
                }
                mappings = fieldToContractInputMappingFactory.createMappingForBusinessObjectType(((BusinessObjectData) selectedData).getClassName());
                return mappings;
            }
        };
    }

    /**
     * @return
     */
    private CellLabelProvider createNameColumnLabelProvider() {
        return new CellLabelProvider() {

            @Override
            public void update(final ViewerCell cell) {
                final Object element = cell.getElement();
                if (element instanceof FieldToContractInputMapping) {
                    cell.setText(((FieldToContractInputMapping) element).getField().getName());
                }
            }
        };
    }

    /**
     * @return
     */
    private CellLabelProvider createTypeColumnLabelProvider() {
        return new CellLabelProvider() {

            @Override
            public void update(final ViewerCell cell) {
                final Object element = cell.getElement();
                if (element instanceof SimpleFieldToContractInputMapping) {
                    final SimpleFieldToContractInputMapping mapping = (SimpleFieldToContractInputMapping) element;
                    cell.setText(((SimpleField) mapping.getField()).getType().name());
                } else {
                    if (element instanceof RelationFieldToContractInputMapping) {
                        final RelationFieldToContractInputMapping mapping = (RelationFieldToContractInputMapping) element;
                        cell.setText(((RelationField) mapping.getField()).getReference().getQualifiedName());
                    }
                }
            }
        };
    }

    public List<FieldToContractInputMapping> getMappings() {
        return mappings;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
     */
    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final FieldToContractInputMapping mapping = (FieldToContractInputMapping) event.getElement();
        mapping.setGenerated(event.getChecked());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateProvider#isChecked(java.lang.Object)
     */
    @Override
    public boolean isChecked(final Object element) {
        return ((FieldToContractInputMapping) element).isGenerated();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateProvider#isGrayed(java.lang.Object)
     */
    @Override
    public boolean isGrayed(final Object element) {
        return false;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(final String rootName) {
        this.rootName = rootName;
    }

}
