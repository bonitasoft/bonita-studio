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

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
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
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class DataViewer extends Composite {

    private TableViewer tableViewer;
    protected TabbedPropertySheetWidgetFactory widgetFactory;
    protected Button addButton;
    private Button editButton;
    private Button removeButton;
    private IObservableValue dataContainerObservable;
    private final EStructuralFeature dataFeature;

    public DataViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory,
            final EStructuralFeature dataFeature) {
        super(parent, SWT.NONE);
        this.widgetFactory = widgetFactory;
        this.dataFeature = dataFeature;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1)
                .extendedMargins(10, 10, 5, 10).create());

        createContent(this);
        widgetFactory.adapt(this);
    }

    protected void createContent(Composite parent) {
        //FILLER
        widgetFactory.createLabel(parent, "", SWT.NONE);

        createTitle(parent);
        createButtons(parent);
        createViewer(parent, widgetFactory, dataFeature);
    }

    private void createViewer(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
            EStructuralFeature dataFeature) {
        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
        widgetFactory.adapt(tableViewer.getTable(), false, false);
        tableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, getTableId());
        tableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        widgetFactory.adapt(tableViewer.getTable(), false, false);
        tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        tableViewer.setComparator(new ViewerComparator());
        tableViewer.addDoubleClickListener(e -> editData());
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
        ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        tableViewer.setContentProvider(contentProvider);
    }

    protected abstract String getTableId();

    protected abstract IBaseLabelProvider createLabelProvider(final IObservableMap[] labelMaps);

    protected abstract void addFilters(final StructuredViewer viewer);

    protected void createTitle(Composite parent) {
        Composite titleComposite = widgetFactory.createComposite(parent);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Label label = widgetFactory.createLabel(titleComposite, getTitle(), SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());

        ControlDecoration controlDecoration = new ControlDecoration(label, SWT.RIGHT, titleComposite);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setDescriptionText(getTitleDescripiton());
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));

        Composite toolBarComposite = widgetFactory.createComposite(titleComposite);
        toolBarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        toolBarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).grab(true, false).create());
        ToolBar toolBar = new ToolBar(toolBarComposite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        widgetFactory.adapt(toolBar);
        if (!PreferenceUtil.isDarkTheme()) {
            toolBar.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
        }
        createToolItems(toolBar);
    }

    protected abstract void createToolItems(ToolBar toolBar);

    protected abstract String getTitle();

    protected abstract String getTitleDescripiton();

    private void createButtons(Composite parent) {
        Composite buttonsComposite = widgetFactory.createComposite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(3, 3).create());

        addButtons(buttonsComposite);
    }

    protected void addButtons(final Composite buttonsComposite) {
        addButton = createAddButton(buttonsComposite);
        editButton = createEditButton(buttonsComposite);
        removeButton = createRemoveButton(buttonsComposite);
    }

    public void bindControl(final DataBindingContext context, final IObservableValue dataContainerObservable) {
        this.dataContainerObservable = dataContainerObservable;
        IObservableSet knownElements = ((ObservableListContentProvider) tableViewer.getContentProvider())
                .getKnownElements();
        IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements,
                new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                        ProcessPackage.Literals.DATA__MULTIPLE,
                        ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME, ProcessPackage.Literals.DATA__DATA_TYPE });
        tableViewer.setLabelProvider(createLabelProvider(labelMaps));
        tableViewer.setInput(CustomEMFEditObservables.observeDetailList(Realm.getDefault(), dataContainerObservable,
                dataFeature));

        IViewerObservableValue<Object> selectionObservable = ViewerProperties.singleSelection().observe(tableViewer);
        UpdateValueStrategy selectionNotNullUpdateValueStrategy = updateValueStrategy().withConverter(selectionNotNull())
                .create();

        context.bindValue(WidgetProperties.enabled().observe(editButton),
                selectionObservable,
                neverUpdateValueStrategy().create(),
                selectionNotNullUpdateValueStrategy);

        context.bindValue(WidgetProperties.enabled().observe(removeButton),
                selectionObservable,
                neverUpdateValueStrategy().create(),
                selectionNotNullUpdateValueStrategy);
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

    protected Button createAddButton(final Composite parent) {
        Composite addButtonComposite = widgetFactory.createComposite(parent);
        addButtonComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        addButtonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        Button addDataButton = createButton(addButtonComposite, Messages.addData, getAddButtonId());
        addDataButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addDataButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addData();
            }
        });
        return addDataButton;
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
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.selectOnlyOneElementTitle,
                    Messages.selectOnlyOneElementMessage);
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
