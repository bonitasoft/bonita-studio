/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.dialog.AdditionalResourceWizardDialog;
import org.bonitasoft.studio.document.ui.styler.AdditionalResourceStyler;
import org.bonitasoft.studio.document.ui.wizard.AdditionalResourceWizard;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Strings;

public class AdditionalResourcesPropertySection extends AbstractBonitaDescriptionSection {

    private IObservableList<AdditionalResource> additionalResourcesObservable;
    private TableViewer viewer;
    private IViewerObservableValue<AdditionalResource> selectionObservable;
    private AdditionalResourceStyler descriptionStyler;
    private Button editButton;
    private Button removeButton;

    public AdditionalResourcesPropertySection() {
        // keep it for reflective instantiation by Eclipse
    }

    @Override
    protected void createContent(Composite parent) {
        Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), mainComposite);
        descriptionStyler = new AdditionalResourceStyler(resourceManager.createColor(ColorConstants.SIMPLE_TYPE_RGB));

        createButtons(mainComposite);
        createViewer(mainComposite);

        DataBindingContext ctx = new DataBindingContext();
        ComputedValue<Boolean> selectionNotNullObservable = new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build();
        ctx.bindValue(WidgetProperties.enabled().observe(editButton), selectionNotNullObservable);
        ctx.bindValue(WidgetProperties.enabled().observe(removeButton), selectionNotNullObservable);
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        getWidgetFactory().adapt(viewer.getControl(), true, true);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, SWT.DEFAULT).create());
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);

        createNameColumn();

        viewer.setContentProvider(new ObservableListContentProvider());
        selectionObservable = ViewerProperties.singleSelection(AdditionalResource.class).observe(viewer);
        viewer.addDoubleClickListener(e -> editAdditionalResource());
        viewer.getControl().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    removeSelectedAdditionResource();
                }
            }
        });
    }

    private void createNameColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<AdditionalResource>()
                .withStyledStringProvider(this::getStyledLabel)
                .createStyledCellLabelProvider());
    }

    private StyledString getStyledLabel(AdditionalResource additionalResource) {
        StyledString styledString = new StyledString(additionalResource.getName());
        if (!Strings.isNullOrEmpty(additionalResource.getDescription())) {
            styledString.append(" - ", descriptionStyler);
            styledString.append(additionalResource.getDescription(), descriptionStyler);
        }
        return styledString;
    }

    private void createButtons(Composite parent) {
        Composite composite = getWidgetFactory().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        Button addButton = getWidgetFactory().createButton(composite, Messages.AddSimple, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        addButton.addListener(SWT.Selection, e -> addAdditionalResource());

        editButton = getWidgetFactory().createButton(composite, Messages.edit, SWT.FLAT);
        editButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        editButton.addListener(SWT.Selection, e -> editAdditionalResource());

        removeButton = getWidgetFactory().createButton(composite, Messages.remove, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, e -> removeSelectedAdditionResource());
    }

    private void editAdditionalResource() {
        AdditionalResourceWizard wizard = new AdditionalResourceWizard(getPool(), selectionObservable.getValue());
        Dialog dialog = new AdditionalResourceWizardDialog(Display.getDefault().getActiveShell(), wizard, false);
        dialog.open();
        viewer.refresh();
    }

    private void addAdditionalResource() {
        AdditionalResourceWizard wizard = new AdditionalResourceWizard(getPool());
        Dialog dialog = new AdditionalResourceWizardDialog(Display.getDefault().getActiveShell(), wizard, true);
        dialog.open();
    }

    private void removeSelectedAdditionResource() {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                Messages.deleteAdditionalResourceConfirmTitle,
                String.format(Messages.deleteAdditionalResourceConfirm, selectionObservable.getValue().getName()))) {
            Pool pool = getPool();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(pool);
            domain.getCommandStack()
                    .execute(new RemoveCommand(domain, pool.getAdditionalResources(), selectionObservable.getValue()));
        }
    }

    protected Pool getPool() {
        return (Pool) ModelHelper.getParentProcess(getEObject());
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        additionalResourcesObservable = EMFEditProperties
                .list(getEditingDomain(), ProcessPackage.Literals.POOL__ADDITIONAL_RESOURCES).observe(getPool());
        viewer.setInput(additionalResourcesObservable);
    }

    @Override
    public String getSectionDescription() {
        return Messages.additionalResourcesHelp;
    }

}
