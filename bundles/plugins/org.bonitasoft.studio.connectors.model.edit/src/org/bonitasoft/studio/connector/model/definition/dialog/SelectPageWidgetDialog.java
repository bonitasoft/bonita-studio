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
package org.bonitasoft.studio.connector.model.definition.dialog;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Orientation;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.Widget;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.dialog.suport.CaptionEditingSupport;
import org.bonitasoft.studio.connector.model.definition.dialog.suport.RadioGroupItemEditingSupport;
import org.bonitasoft.studio.connector.model.definition.dialog.suport.SelectItemEditingSupport;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.scripting.extensions.IScriptLanguageProvider;
import org.bonitasoft.studio.scripting.extensions.ScriptLanguageService;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Romain Bioteau
 * @author Aurelie Zara
 */
public class SelectPageWidgetDialog extends Dialog {

    private DataBindingContext context;
    private Component component;
    private String displayName;
    private String description;

    private Section section;
    private final Set<String> existingWidgetIds;
    private Set<String> alreadyBoundInputs;
    private static List<EClass> widgetTypes;
    private List<Orientation> orientations;
    private final List<Input> inputs = new ArrayList<Input>();
    private DialogSupport dialogSupport;
    private ISWTObservableValue idTextObservable;
    private IViewerObservableValue inputSelectionObservable;

    static {
        widgetTypes = new ArrayList<EClass>();
        widgetTypes.add(ConnectorDefinitionPackage.Literals.TEXT);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.TEXT_AREA);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.PASSWORD);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.CHECKBOX);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.SELECT);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.RADIO_GROUP);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.GROUP);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.ARRAY);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.LIST);
        widgetTypes.add(ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR);
    }

    @SuppressWarnings("unchecked")
    public SelectPageWidgetDialog(Shell parentShell,
            ConnectorDefinition definition, Page currentPage,
            Component original, Component component) {
        super(parentShell);
        this.component = component;
        List<Component> allComponents = ModelHelper.getAllItemsOfType(
                definition, ConnectorDefinitionPackage.Literals.COMPONENT);
        allComponents.addAll((Collection<? extends Component>) ModelHelper
                .getAllItemsOfType(currentPage,
                        ConnectorDefinitionPackage.Literals.COMPONENT));
        existingWidgetIds = new HashSet<String>();
        for (Component c : allComponents) {
            existingWidgetIds.add(c.getId().toLowerCase());
        }
        if (original != null && original.getId() != null) {
            existingWidgetIds.remove(original.getId().toLowerCase());
        }
        alreadyBoundInputs = new HashSet<String>();
        for (Component c : allComponents) {
            if (c instanceof WidgetComponent) {
                alreadyBoundInputs.add(((Widget) c).getInputName());
            }

        }
        if (original != null && original instanceof WidgetComponent && ((WidgetComponent) original).getInputName() != null) {
            alreadyBoundInputs.remove(((WidgetComponent) original).getInputName());
        }
        orientations = new ArrayList<Orientation>();
        orientations.add(Orientation.HORIZONTAL);
        orientations.add(Orientation.VERTICAL);
        for (Input input : definition.getInput()) {
            inputs.add(input);
        }
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.addWidget);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).hint(400, SWT.DEFAULT).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 10).spacing(15, 5).create());

        context = new DataBindingContext();
        if (component == null) {
            component = ConnectorDefinitionFactory.eINSTANCE.createText();
        }
        final Label idLabel = new Label(mainComposite, SWT.NONE);
        idLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        idLabel.setText(Messages.widgetId + "*");

        final Text idText = new Text(mainComposite, SWT.BORDER);
        idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        idTextObservable = SWTObservables.observeText(idText, SWT.Modify);
        bindComponentId(idText);

        final Label widgetTypeLabel = new Label(mainComposite, SWT.NONE);
        widgetTypeLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        widgetTypeLabel.setText(Messages.widgetType);

        final ComboViewer typeViewer = new ComboViewer(mainComposite,
                SWT.BORDER | SWT.READ_ONLY);
        typeViewer.getCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        typeViewer.setContentProvider(new ArrayContentProvider());
        typeViewer.setLabelProvider(new WidgetLabelProvider());
        typeViewer.setInput(widgetTypes);

        typeViewer.setSelection(new StructuredSelection(component.eClass()));

        final Label inputLabel = new Label(mainComposite, SWT.NONE);
        inputLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        inputLabel.setText(Messages.input + " *");

        final ComboViewer inputViewer = new ComboViewer(mainComposite,
                SWT.BORDER | SWT.READ_ONLY);
        inputViewer.getCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        inputViewer.setContentProvider(new ArrayContentProvider());
        inputViewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                Input input = (Input) element;
                return super.getText(input.getName() + " (" + input.getType()
                        + ")");
            }
        });
        inputViewer.setInput(inputs);
        inputSelectionObservable = ViewersObservables.observeSingleSelection(inputViewer);
        bindComponentInput(inputViewer);

        final Label displayNameLabel = new Label(mainComposite, SWT.NONE);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        displayNameLabel.setText(Messages.displayName);

        final Text displayNameText = new Text(mainComposite, SWT.BORDER);
        displayNameText.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());

        context.bindValue(
                SWTObservables.observeText(displayNameText, SWT.Modify),
                PojoProperties.value(SelectPageWidgetDialog.class,
                        "displayName").observe(this));

        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());
        descriptionLabel.setText(Messages.description);

        final Text descriptionText = new Text(mainComposite, SWT.BORDER
                | SWT.MULTI);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).hint(SWT.DEFAULT, 60).create());

        context.bindValue(
                SWTObservables.observeText(descriptionText, SWT.Modify),
                PojoProperties.value(SelectPageWidgetDialog.class,
                        "description").observe(this));

        section = new Section(parent, Section.NO_TITLE);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .span(2, 1).create());
        section.setClient(createSectionClient(component));
        section.setExpanded(isExpanded(component));

        typeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                EClass eClass = (EClass) ((IStructuredSelection) event
                        .getSelection()).getFirstElement();
                String id = null;
                if (component != null) {
                    id = component.getId();
                }
                String input = null;
                if (component instanceof WidgetComponent) {
                    input = ((WidgetComponent) component).getInputName();
                }
                component = (Component) ConnectorDefinitionFactory.eINSTANCE
                        .create(eClass);
                component.setId(id);
                if (component instanceof WidgetComponent) {
                    ((WidgetComponent) component).setInputName(input);
                }
                bindComponentId(idText);
                bindComponentInput(inputViewer);

                section.setClient(createSectionClient(component));
                section.setExpanded(isExpanded(component));
                Shell shell = section.getShell();
                Point defaultSize = shell.getSize();
                Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                shell.setSize(defaultSize.x, size.y);
                shell.layout(true, true);
            }
        });

        dialogSupport = DialogSupport.create(this, context);

        return mainComposite;
    }

    protected void bindComponentInput(final ComboViewer inputViewer) {
        inputViewer.getCombo().setEnabled(component instanceof WidgetComponent);
        Iterator it = context.getBindings().iterator();
        Binding toRemove = null;
        while (it.hasNext()) {
            Binding object = (Binding) it.next();
            if (object.getTarget().equals(inputSelectionObservable)) {
                toRemove = object;
            }
        }
        if (toRemove != null) {
            toRemove.validateTargetToModel();
            context.removeBinding(toRemove);
        }
        if (component instanceof WidgetComponent) {
            UpdateValueStrategy targetToModel = new UpdateValueStrategy();
            targetToModel.setConverter(new Converter(Input.class, String.class) {

                @Override
                public Object convert(Object from) {
                    return from != null ? ((Input) from).getName() : null;
                }
            });

            targetToModel.setBeforeSetValidator(new IValidator() {

                @Override
                public IStatus validate(Object value) {
                    if (!(component instanceof WidgetComponent)) {
                        return Status.OK_STATUS;
                    }
                    if (value == null || value.toString().isEmpty()) {
                        return ValidationStatus.error(Messages.inputIsEmpty);
                    }
                    if (alreadyBoundInputs.contains(value.toString())) {
                        return ValidationStatus.error(Messages.inputAlreadyUseInAnotherWidget);
                    }
                    return Status.OK_STATUS;
                }
            });

            UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
            modelToTarget.setConverter(new Converter(String.class, Input.class) {

                @Override
                public Object convert(Object from) {
                    for (Input input : inputs) {
                        if (input.getName().equals(from)) {
                            return input;
                        }
                    }
                    return null;
                }
            });

            ControlDecorationSupport.create(context.bindValue(inputSelectionObservable,
                    EMFObservables
                            .observeValue(
                                    component,
                                    ConnectorDefinitionPackage.Literals.WIDGET_COMPONENT__INPUT_NAME),
                    targetToModel, modelToTarget), SWT.LEFT);
        }
    }

    protected void bindComponentId(final Text idText) {
        UpdateValueStrategy idStrategy = new UpdateValueStrategy();
        idStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if (value == null || value.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.idIsEmpty);
                }
                if (value.toString().contains(" ")) {
                    return ValidationStatus.error(Messages.noWhiteSpaceInPageID);
                }
                if (!FileUtil.isValidName(value.toString())) {
                    return ValidationStatus.error(Messages.idIsInvalid);
                }
                if (existingWidgetIds.contains(value.toString().toLowerCase())) {
                    return ValidationStatus.error(Messages.idAlreadyExists);
                }
                return Status.OK_STATUS;
            }
        });
        Iterator it = context.getBindings().iterator();
        Binding toRemove = null;
        while (it.hasNext()) {
            Binding object = (Binding) it.next();
            if (object.getTarget().equals(idTextObservable)) {
                toRemove = object;
            }
        }

        context.removeBinding(toRemove);
        ControlDecorationSupport.create(context.bindValue(idTextObservable,
                EMFObservables.observeValue(component,
                        ConnectorDefinitionPackage.Literals.COMPONENT__ID),
                idStrategy, null), SWT.LEFT);
    }

    @Override
    protected Button createButton(Composite parent, int id, String label,
            boolean defaultButton) {
        Button button = super.createButton(parent, id, label, defaultButton);
        if (id == IDialogConstants.OK_ID) {
            button.setEnabled(component != null && component.getId() != null
                    && !component.getId().isEmpty());
        }
        return button;
    }

    private boolean isExpanded(Component widget) {
        return widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.RADIO_GROUP)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.SELECT)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.ARRAY)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.GROUP)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.TEXT)
                || widget.eClass().equals(
                        ConnectorDefinitionPackage.Literals.LIST);
    }

    protected Control createSectionClient(Component widget) {
        if (section.getClient() != null) {
            section.getClient().dispose();
        }
        if (widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.RADIO_GROUP)) {
            return createRadioGroupComposite((RadioGroup) widget);
        } else if (widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.ARRAY)) {
            return createArrayComposite((Array) widget);
        } else if (widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.SELECT)) {
            return createSelectComposite((Select) widget);
        } else if (widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.GROUP)) {
            return createGroupComposite((Group) widget);
        } else if (widget.eClass().equals(
                ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR)) {
            return createScriptEditorComposite((ScriptEditor) widget);
        } else if (widget.eClass().equals(ConnectorDefinitionPackage.Literals.TEXT)
                || widget.eClass().equals(ConnectorDefinitionPackage.Literals.LIST)) {
            return createShowDocumentsComposite(widget);
        } else {
            return new Composite(section, SWT.NONE);
        }

    }

    private Control createShowDocumentsComposite(Component widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());
        final Button showDocumentButton = new Button(mainComposite, SWT.CHECK);
        showDocumentButton.setText(Messages.showDocuments);
        showDocumentButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        if (widget instanceof org.bonitasoft.studio.connector.model.definition.Text) {
            context.bindValue(SWTObservables.observeSelection(showDocumentButton),
                    EMFObservables.observeValue(widget,
                            ConnectorDefinitionPackage.Literals.TEXT__SHOW_DOCUMENTS));
        } else if (widget instanceof org.bonitasoft.studio.connector.model.definition.List) {
            context.bindValue(SWTObservables.observeSelection(showDocumentButton),
                    EMFObservables.observeValue(widget,
                            ConnectorDefinitionPackage.Literals.LIST__SHOW_DOCUMENTS));
        }

        return mainComposite;
    }

    private Control createScriptEditorComposite(ScriptEditor widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());

        final Label interpreterLabel = new Label(mainComposite, SWT.NONE);
        interpreterLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        interpreterLabel.setText(Messages.interpreter + " *");

        final ComboViewer interpreterViewer = new ComboViewer(mainComposite,
                SWT.BORDER | SWT.READ_ONLY);
        interpreterViewer.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        interpreterViewer.setContentProvider(new ArrayContentProvider());
        interpreterViewer.setLabelProvider(new LabelProvider());
        List<String> interpreters = new ArrayList<String>();
        for (IScriptLanguageProvider provider : ScriptLanguageService.getInstance().getScriptLanguageProviders()) {
            interpreters.add(provider.getLanguageName());
        }
        interpreterViewer.setInput(interpreters);
        context.bindValue(ViewersObservables.observeSingleSelection(interpreterViewer),
                EMFObservables.observeValue(widget,
                        ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR__INTERPRETER));

        return mainComposite;
    }

    private Control createGroupComposite(Group widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());

        final Label itemsLabel = new Label(mainComposite, SWT.NONE);
        itemsLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).indent(70, SWT.DEFAULT).create());

        final Button isCollapsed = new Button(mainComposite, SWT.CHECK);
        isCollapsed.setLayoutData(GridDataFactory.fillDefaults().create());
        isCollapsed.setText(Messages.isCollapsed);

        context.bindValue(SWTObservables.observeSelection(isCollapsed),
                EMFObservables.observeValue(widget,
                        ConnectorDefinitionPackage.Literals.GROUP__OPTIONAL));

        return mainComposite;
    }

    private Control createSelectComposite(final Select widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());

        final Label itemsLabel = new Label(mainComposite, SWT.NONE);
        itemsLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());
        itemsLabel.setText(Messages.items);

        final Composite itemComposite = new Composite(mainComposite, SWT.NONE);
        itemComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        itemComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(0, 0).create());

        final TableViewer itemViewer = new TableViewer(itemComposite,
                SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        itemViewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true)
                        .hint(SWT.DEFAULT, 80).create());
        itemViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100));
        itemViewer.getTable().setLayout(layout);

        final TableViewerColumn inputNameColumn = new TableViewerColumn(
                itemViewer, SWT.FILL);
        inputNameColumn.getColumn().setText(Messages.input);
        inputNameColumn.setEditingSupport(new SelectItemEditingSupport(
                itemViewer, widget));
        inputNameColumn.setLabelProvider(new ColumnLabelProvider());

        context.bindValue(ViewersObservables.observeInput(itemViewer),
                EMFObservables.observeValue(widget,
                        ConnectorDefinitionPackage.Literals.SELECT__ITEMS));

        final Composite buttonComposite = new Composite(itemComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.Add);
        addButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String item = generateItem(widget);
                widget.getItems().add(item);
                itemViewer.editElement(item, 0);
            }
        });

        final Button upButton = new Button(buttonComposite, SWT.FLAT);
        upButton.setText(Messages.up);
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .create());
        upButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getItems().indexOf(selected);
                if (i > 0) {
                    widget.getItems().move(i - 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button downButton = new Button(buttonComposite, SWT.FLAT);
        downButton.setText(Messages.down);
        downButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        downButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getItems().indexOf(selected);
                if (i < widget.getItems().size() - 1) {
                    widget.getItems().move(i + 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                widget.getItems().removeAll(
                        ((IStructuredSelection) itemViewer.getSelection())
                                .toList());
                itemViewer.refresh();
            }
        });

        return mainComposite;
    }

    private String generateItem(Select widget) {
        Set<String> names = new HashSet<String>();
        for (String item : widget.getItems()) {
            names.add(item);
        }

        return NamingUtils.generateNewName(names, Messages.item, 1);
    }

    private Control createArrayComposite(final Array widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());

        final Label nbColLabel = new Label(mainComposite, SWT.NONE);
        nbColLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        nbColLabel.setText(Messages.nbColumn);

        final Combo columnCombo = new Combo(mainComposite, SWT.BORDER
                | SWT.READ_ONLY);
        columnCombo.setLayoutData(GridDataFactory.fillDefaults().create());
        for (int i = 1; i < 11; i++) {
            columnCombo.add(String.valueOf(i));
        }

        if (widget.getCols() == null) {
            widget.setCols(new BigInteger("1"));
        }

        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel
                .setConverter(new Converter(String.class, BigInteger.class) {

                    @Override
                    public Object convert(Object from) {
                        return from != null ? new BigInteger((String) from) : null;
                    }
                });

        UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
        modelToTarget
                .setConverter(new Converter(BigInteger.class, String.class) {

                    @Override
                    public Object convert(Object from) {
                        return from != null ? String.valueOf(((BigInteger) from).intValue()) : null;
                    }
                });

        context.bindValue(SWTObservables.observeText(columnCombo),
                EMFObservables.observeValue(widget,
                        ConnectorDefinitionPackage.Literals.ARRAY__COLS),
                targetToModel, modelToTarget);

        final Label fixedColLabel = new Label(mainComposite, SWT.NONE);
        fixedColLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());

        final Button fixedColButton = new Button(mainComposite, SWT.CHECK);
        fixedColButton.setLayoutData(GridDataFactory.fillDefaults().create());
        fixedColButton.setText(Messages.fixedColumn);

        context.bindValue(SWTObservables.observeSelection(fixedColButton),
                EMFObservables.observeValue(widget,
                        ConnectorDefinitionPackage.Literals.ARRAY__FIXED_COLS));

        final Label colHeadersLabel = new Label(mainComposite, SWT.NONE);
        colHeadersLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());
        colHeadersLabel.setText(Messages.columnHeaders);

        final Composite itemComposite = new Composite(mainComposite, SWT.NONE);
        itemComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        itemComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(0, 0).create());

        final TableViewer itemViewer = new TableViewer(itemComposite,
                SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        itemViewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true)
                        .hint(SWT.DEFAULT, 80).create());
        itemViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100));
        itemViewer.getTable().setLayout(layout);

        final TableViewerColumn inputNameColumn = new TableViewerColumn(
                itemViewer, SWT.FILL);
        inputNameColumn.getColumn().setText(Messages.input);
        inputNameColumn.setEditingSupport(new CaptionEditingSupport(itemViewer,
                widget));
        inputNameColumn.setLabelProvider(new ColumnLabelProvider());

        context.bindValue(
                ViewersObservables.observeInput(itemViewer),
                EMFObservables
                        .observeValue(
                                widget,
                                ConnectorDefinitionPackage.Literals.ARRAY__COLS_CAPTION));

        final Composite buttonComposite = new Composite(itemComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.Add);
        addButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String item = generateCaption(widget);
                widget.getColsCaption().add(item);
                itemViewer.editElement(item, 0);
            }

        });

        final Button upButton = new Button(buttonComposite, SWT.FLAT);
        upButton.setText(Messages.up);
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .create());
        upButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getColsCaption().indexOf(selected);
                if (i > 0) {
                    widget.getColsCaption().move(i - 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button downButton = new Button(buttonComposite, SWT.FLAT);
        downButton.setText(Messages.down);
        downButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        downButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getColsCaption().indexOf(selected);
                if (i < widget.getColsCaption().size() - 1) {
                    widget.getColsCaption().move(i + 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                widget.getColsCaption().removeAll(
                        ((IStructuredSelection) itemViewer.getSelection())
                                .toList());
                itemViewer.refresh();
            }
        });

        return mainComposite;
    }

    private String generateCaption(Array widget) {
        Set<String> names = new HashSet<String>();
        for (String item : widget.getColsCaption()) {
            names.add(item);
        }

        return NamingUtils.generateNewName(names, Messages.header, 1);
    }

    private Control createRadioGroupComposite(final RadioGroup widget) {
        final Composite mainComposite = new Composite(section, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(15, 0).create());

        final Label orientationLabel = new Label(mainComposite, SWT.NONE);
        orientationLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        orientationLabel.setText(Messages.orientation);

        final ComboViewer orientationCombo = new ComboViewer(mainComposite,
                SWT.READ_ONLY | SWT.BORDER);
        orientationCombo.getCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        orientationCombo.setContentProvider(new ArrayContentProvider());
        orientationCombo.setLabelProvider(new LabelProvider());
        context.bindValue(
                ViewersObservables.observeInput(orientationCombo),
                PojoProperties.value(SelectPageWidgetDialog.class,
                        "orientations").observe(this));
        context.bindValue(
                ViewersObservables.observeSingleSelection(orientationCombo),
                EMFObservables
                        .observeValue(
                                widget,
                                ConnectorDefinitionPackage.Literals.RADIO_GROUP__ORIENTATION));

        final Label itemsLabel = new Label(mainComposite, SWT.NONE);
        itemsLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());
        itemsLabel.setText(Messages.items);

        final Composite itemComposite = new Composite(mainComposite, SWT.NONE);
        itemComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        itemComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(0, 0).create());

        final TableViewer itemViewer = new TableViewer(itemComposite,
                SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        itemViewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true)
                        .hint(SWT.DEFAULT, 80).create());
        itemViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100));
        itemViewer.getTable().setLayout(layout);

        final TableViewerColumn inputNameColumn = new TableViewerColumn(
                itemViewer, SWT.FILL);
        inputNameColumn.getColumn().setText(Messages.input);
        inputNameColumn.setEditingSupport(new RadioGroupItemEditingSupport(
                itemViewer, widget));
        inputNameColumn.setLabelProvider(new ColumnLabelProvider());

        context.bindValue(
                ViewersObservables.observeInput(itemViewer),
                EMFObservables
                        .observeValue(
                                widget,
                                ConnectorDefinitionPackage.Literals.RADIO_GROUP__CHOICES));

        final Composite buttonComposite = new Composite(itemComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.Add);
        addButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String item = generateRadioChoice(widget);
                widget.getChoices().add(item);
                itemViewer.editElement(item, 0);
            }

        });

        final Button upButton = new Button(buttonComposite, SWT.FLAT);
        upButton.setText(Messages.up);
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .create());
        upButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getChoices().indexOf(selected);
                if (i > 0) {
                    widget.getChoices().move(i - 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button downButton = new Button(buttonComposite, SWT.FLAT);
        downButton.setText(Messages.down);
        downButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        downButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String selected = (String) ((IStructuredSelection) itemViewer
                        .getSelection()).getFirstElement();
                int i = widget.getChoices().indexOf(selected);
                if (i < widget.getChoices().size() - 1) {
                    widget.getChoices().move(i + 1, selected);
                    itemViewer.refresh();
                }
            }
        });

        final Button removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                widget.getChoices().removeAll(
                        ((IStructuredSelection) itemViewer.getSelection())
                                .toList());
                itemViewer.refresh();
            }
        });

        return mainComposite;
    }

    private String generateRadioChoice(RadioGroup widget) {
        Set<String> names = new HashSet<String>();
        for (String item : widget.getChoices()) {
            names.add(item);
        }

        return NamingUtils.generateNewName(names, Messages.item, 1);
    }

    @Override
    public boolean close() {
        if (context != null) {
            context.dispose();
        }
        if (dialogSupport != null) {
            dialogSupport.dispose();
        }
        return super.close();
    }

    public Component getWidget() {
        return component;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }

    public void setOrientations(List<Orientation> orientations) {
        this.orientations = orientations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
