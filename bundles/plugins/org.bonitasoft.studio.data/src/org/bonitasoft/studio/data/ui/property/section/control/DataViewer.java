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
package org.bonitasoft.studio.data.ui.property.section.control;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class DataViewer extends Composite {

    private final TableViewer tableViewer;
    private final TabbedPropertySheetWidgetFactory widgetFactory;
    private Button editButton;
    private Button removeButton;
    private IObservableValue dataContainerObservable;
    private final EStructuralFeature dataFeature;

    public DataViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final EStructuralFeature dataFeature) {
        super(parent, SWT.NONE);
        this.widgetFactory = widgetFactory;
        this.dataFeature = dataFeature;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 5, 10).create());

        createTitle();

        createButtons();

        tableViewer = new TableViewer(this, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
        widgetFactory.adapt(tableViewer.getTable(), false, false);
        tableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, getTableId());
        tableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        widgetFactory.adapt(tableViewer.getTable(), false, false);
        tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        tableViewer.setSorter(new ViewerSorter());
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                editData();
            }
        });
        tableViewer.getTable().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    removeData(dataContainerObservable, dataFeature);
                }
            }
        });
        addFilters(tableViewer);
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        tableViewer.setContentProvider(contentProvider);

        widgetFactory.adapt(this);
    }

    protected abstract String getTableId();

    protected abstract IBaseLabelProvider createLabelProvider(final IObservableMap[] labelMaps);

    protected abstract void addFilters(final StructuredViewer viewer);

    private void createTitle() {
        //FILLER
        widgetFactory.createLabel(this, "", SWT.NONE);

        final Label label = widgetFactory.createLabel(this, getTitle(), SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());

        final ControlDecoration controlDecoration = new ControlDecoration(label, SWT.RIGHT, this);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setDescriptionText(getTitleDescripiton());
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
    }

    protected abstract String getTitle();

    protected abstract String getTitleDescripiton();

    private void createButtons() {
        final Composite buttonsComposite = widgetFactory.createComposite(this, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(3, 3).create());

        addButtons(buttonsComposite);
    }

    protected void addButtons(final Composite buttonsComposite) {
        createAddButton(buttonsComposite);
        editButton = createEditButton(buttonsComposite);
        removeButton = createRemoveButton(buttonsComposite);
    }

    public void bindControl(final DataBindingContext context, final IObservableValue dataContainerObservable) {
        this.dataContainerObservable = dataContainerObservable;
        final IObservableSet knownElements = ((ObservableListContentProvider) tableViewer.getContentProvider()).getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements, new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                ProcessPackage.Literals.DATA__MULTIPLE,
                ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME, ProcessPackage.Literals.DATA__DATA_TYPE });
        tableViewer.setLabelProvider(createLabelProvider(labelMaps));
        tableViewer.setInput(CustomEMFEditObservables.observeDetailList(Realm.getDefault(), dataContainerObservable,
                dataFeature));

        context.bindValue(SWTObservables.observeEnabled(editButton),
                ViewersObservables.observeSingleSelection(tableViewer),
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(selectionNotNull()).create());

        context.bindValue(SWTObservables.observeEnabled(removeButton),
                ViewersObservables.observeSingleSelection(tableViewer),
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(selectionNotNull()).create());
    }

    protected IObservableValue getDataContainerObservable() {
        return dataContainerObservable;
    }

    private Converter selectionNotNull() {
        return new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }

        };
    }

    protected Button createButton(final Composite parent, final String text, final String widgetId) {
        final Button button = widgetFactory.createButton(parent, text, SWT.FLAT);
        button.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, widgetId);
        button.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        return button;
    }

    private void createAddButton(final Composite parent) {
        final Button addDataButton = createButton(parent, Messages.addData, getAddButtonId());
        addDataButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addData();
            }
        });
    }

    protected abstract String getAddButtonId();

    private Button createRemoveButton(final Composite buttonsComposite) {
        final Button removeButton = createButton(buttonsComposite, Messages.removeData, getRemoveButtonId());
        removeButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                removeData(dataContainerObservable, dataFeature);
            }

        });
        return removeButton;
    }

    protected abstract String getRemoveButtonId();

    private Button createEditButton(final Composite buttonsComposite) {
        final Button editButton = createButton(buttonsComposite, Messages.updateData, getEditDataId());
        editButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                editData();
            }
        });
        return editButton;
    }

    protected abstract String getEditDataId();

    protected IStructuredSelection getStructuredSelection() {
        return (IStructuredSelection) tableViewer.getSelection();
    }

    protected boolean onlyOneElementSelected(final IStructuredSelection selection) {
        if (selection.size() != 1) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.selectOnlyOneElementTitle, Messages.selectOnlyOneElementMessage);
            return false;
        }
        return true;
    }

    protected WizardDialog createWizardDialog(
            final Wizard wizard, final String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }

    protected abstract void addData();

    protected abstract void editData();

    protected abstract void removeData(IObservableValue observable, EStructuralFeature dataFeature);

}
