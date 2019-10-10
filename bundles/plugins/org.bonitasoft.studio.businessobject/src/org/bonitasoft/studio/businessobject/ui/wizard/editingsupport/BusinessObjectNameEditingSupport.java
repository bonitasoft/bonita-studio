/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.BusinessObjectNameCellEditorValidator;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectNameEditingSupport extends ObservableValueEditingSupport {

    private IDiffLogger diffLogger;
    private IViewerObservableValue selectionObservable;
    private CellEditor cellEditor;
    private DataBindingContext ctx;
    private BusinessObjectModel businessObjectModel;
    private PackageHelper packageHelper;
    private TreeViewer viewer;
    private ISWTObservableValue editionGroupTextObservable;

    public BusinessObjectNameEditingSupport(BusinessObjectModel businessObjectModel,
            IViewerObservableValue selectionObservable, TreeViewer viewer,
            DataBindingContext ctx,
            IDiffLogger diffLogger) {
        super(viewer, ctx);
        this.ctx = ctx;
        this.viewer = viewer;
        this.diffLogger = diffLogger;
        this.businessObjectModel = businessObjectModel;
        this.selectionObservable = selectionObservable;
        this.cellEditor = createCellEditor();
        this.packageHelper = PackageHelper.getInstance();
    }

    private CellEditor createCellEditor() {
        final TextCellEditor textCellEditor = new TextCellEditor((Composite) getViewer().getControl());
        final Text textControl = (Text) textCellEditor.getControl();
        textControl.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_BO_NAME_TEXTEDITOR);
        textControl.setTextLimit(BusinessObjectNameCellEditorValidator.MAX_TABLE_NAME_LENGTH + 5);
        return textCellEditor;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return cellEditor;
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, ViewerCell cell) {
        if (element instanceof BusinessObject) {
            IObservableValue qualifiedNameObservable = PojoProperties.value("qualifiedName").observe(element);
            qualifiedNameObservable.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
            qualifiedNameObservable.addValueChangeListener(e -> {
                BusinessObject businessObject = (BusinessObject) selectionObservable.getValue();
                String oldName = (String) e.diff.getOldValue();
                String newName = (String) e.diff.getNewValue();
                diffLogger.boRenamed(oldName, newName);
                for (Query q : businessObject.getQueries()) {
                    if (q.getReturnType().equals(oldName)) {
                        q.setReturnType(newName);
                    }
                }
                editionGroupTextObservable.setValue(NamingUtils.getSimpleName(newName));
            });
            return qualifiedNameObservable;
        }
        IObservableValue<String> packageNameObservable = new WritableValue<>();
        packageNameObservable.setValue(element.toString());
        packageNameObservable.addValueChangeListener(e -> {
            String oldPackageName = e.diff.getOldValue();
            String newPackageName = e.diff.getNewValue();
            updatePackageName(oldPackageName, newPackageName);
        });
        return packageNameObservable; // TODO package -> ya sans doute juste besoin de creer une writable value, de mettre un value change dessus et de faire la logique dans le value change ici.
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(CellEditor cellEditor) {
        return WidgetProperties.text(SWT.Modify).observe(cellEditor.getControl());
    }

    @Override
    protected Binding createBinding(IObservableValue target, IObservableValue model) {
        return isBusinessObject()
                ? createBindingForBusinessObjectEdition(target, model)
                : createBindingForPackageObjectEdition(target, model);
    }

    private Binding createBindingForPackageObjectEdition(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel = UpdateStrategyFactory.convertUpdateValueStrategy().create();
        targetToModel.setAfterGetValidator(
                new BusinessObjectNameCellEditorValidator(businessObjectModel, selectionObservable));
        UpdateValueStrategy modelToTarget = UpdateStrategyFactory.updateValueStrategy().create();
        return ctx.bindValue(target, model, targetToModel, modelToTarget);
    }

    private Binding createBindingForBusinessObjectEdition(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel = UpdateStrategyFactory.convertUpdateValueStrategy()
                .withConverter(ConverterBuilder.<String, String> newConverter()
                        .fromType(String.class)
                        .toType(String.class)
                        .withConvertFunction(this::toNewQualifiedName)
                        .create())
                .create();
        targetToModel
                .setAfterGetValidator(new BusinessObjectNameCellEditorValidator(businessObjectModel, selectionObservable));
        UpdateValueStrategy modelToTarget = UpdateStrategyFactory.updateValueStrategy()
                .withConverter(ConverterBuilder.<String, String> newConverter()
                        .fromType(String.class)
                        .toType(String.class)
                        .withConvertFunction(this::toSimpleName)
                        .create())
                .create();
        return ctx.bindValue(target, model, targetToModel, modelToTarget);
    }

    private String toNewQualifiedName(String newSimpleName) {
        BusinessObject bo = (BusinessObject) selectionObservable.getValue();
        String packageName = packageHelper.getPackageName(bo);
        String newQualifiedName = String.format("%s.%s", packageName, newSimpleName);
        return newQualifiedName;
    }

    private String toSimpleName(String qualifiedName) {
        return NamingUtils.getSimpleName(qualifiedName);
    }

    private boolean isBusinessObject() {
        return selectionObservable.getValue() instanceof BusinessObject;
    }

    private void updatePackageName(String oldPackageName, String newPackageName) {
        if (!Objects.equals(oldPackageName, newPackageName)) {
            if (!packageHelper.packageAlreadyExists(businessObjectModel, newPackageName)
                    || confirmMergePackage(oldPackageName, newPackageName)) {
                List<BusinessObject> businessObjects = packageHelper.getAllBusinessObjects(businessObjectModel,
                        oldPackageName);
                businessObjects.stream()
                        .forEach(bo -> {
                            String oldQualifiedName = bo.getQualifiedName();
                            String newQualifiedName = String.format("%s.%s", newPackageName, bo.getSimpleName());
                            updateBusinessObjectName(bo, oldQualifiedName, newQualifiedName);
                        });
                Display.getDefault().asyncExec(() -> {
                    List<Object> elementsToExpend = new ArrayList();
                    for (Object o : viewer.getExpandedElements()) {
                        Object toExpend = Objects.equals(o, oldPackageName) ? newPackageName : o;
                        elementsToExpend.add(toExpend);
                    }
                    viewer.refresh();
                    selectionObservable.setValue(newPackageName);
                    viewer.setExpandedElements(elementsToExpend.toArray());
                });
            }
        }
    }

    private boolean confirmMergePackage(String package1, String package2) {
        return MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                Messages.mergePackageConfirmTitle,
                String.format(Messages.mergePackageConfirm, package1, package2));
    }

    private void updateBusinessObjectName(BusinessObject element, String oldQualifiedName, String newQualifiedName) {
        element.setQualifiedName(newQualifiedName);
        diffLogger.boRenamed(oldQualifiedName, newQualifiedName);
        element.getQueries().stream()
                .filter(query -> Objects.equals(query.getReturnType(), oldQualifiedName))
                .forEach(query -> query.setReturnType(newQualifiedName));
    }

    public void setEditionGroupTextObservable(ISWTObservableValue editionGroupTextObservable) {
        this.editionGroupTextObservable = editionGroupTextObservable;
    }

}
