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
package org.bonitasoft.studio.identity.organization.editor.control.user;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class ManageCustomInformationSection {

    private static final Object ADD_CUSTOM_INFO_BUTTON_ID = "addCustomInfoButton";
    private static final Object REMOVE_CUSTOM_INFO_BUTTON_ID = "rmeoveCustommeInfoButtom";
    private static final Object CUSTOM_INFO_LIST_VIEWER_ID = "customInfoDefinitionViewer";
    private UserFormPage formPage;
    private DataBindingContext ctx;
    private ToolItem deleteCustomInfoItem;
    private TableViewer viewer;
    private IViewerObservableValue<CustomUserInfoDefinition> selectedCustomInfoObservable;
    private IObservableList<CustomUserInfoDefinition> input;

    public ManageCustomInformationSection(Composite parent, UserFormPage formPage, DataBindingContext ctx) {
        this.formPage = formPage;
        this.ctx = ctx;

        IObservableValue<CustomUserInfoDefinitions> definitions = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                formPage.observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS);

        if (definitions.getValue() == null) {
            definitions.setValue(OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
        }

        input = EMFObservables.observeDetailList(ctx.getValidationRealm(),
                definitions, OrganizationPackage.Literals.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION);

        Section customInfoSection = formPage.getToolkit().createSection(parent, Section.TWISTIE);
        customInfoSection.setLayout(GridLayoutFactory.fillDefaults().create());
        customInfoSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        customInfoSection.setText(Messages.manageCustomInfo);
        customInfoSection.setClient(createCustomInfoComposite(formPage, customInfoSection));
    }

    private Composite createCustomInfoComposite(UserFormPage formPage, Section customInfoSection) {
        Composite customInfoComposite = formPage.getToolkit().createComposite(customInfoSection);
        customInfoComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 1).create());
        customInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(customInfoComposite);
        createViewer(customInfoComposite);
        enableButtons();

        return customInfoComposite;
    }

    private void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteCustomInfoItem), new ComputedValueBuilder()
                .withSupplier(() -> selectedCustomInfoObservable.getValue() != null)
                .build());
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(600, SWT.DEFAULT).create());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, CUSTOM_INFO_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(viewer.getTable());
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);
        viewer.getTable().setHeaderVisible(true);
        selectedCustomInfoObservable = ViewerProperties.singleSelection(CustomUserInfoDefinition.class).observe(viewer);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        layout.addColumnData(new ColumnWeightData(2, 400, true));
        viewer.getTable().setLayout(layout);

        createNameColumn();
        createDescriptionColumn();

        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.setInput(input);
    }

    private void createDescriptionColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.description);
        column.setLabelProvider(new LabelProviderBuilder<CustomUserInfoDefinition>()
                .withTextProvider(CustomUserInfoDefinition::getDescription)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<CustomUserInfoDefinition>(viewer)
                .withValueProvider(CustomUserInfoDefinition::getDescription)
                .withValueUpdater((customInfo, description) -> customInfo.setDescription((String) description))
                .create());
    }

    private void createNameColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);
        EmptyInputValidator validator = new EmptyInputValidator(Messages.name);
        column.setLabelProvider(new LabelProviderBuilder<CustomUserInfoDefinition>()
                .withTextProvider(CustomUserInfoDefinition::getName)
                .withStatusProvider(customInfo -> validator.validate(customInfo.getName()))
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<CustomUserInfoDefinition>(viewer)
                .withValueProvider(CustomUserInfoDefinition::getName)
                .withValueUpdater((customInfo, name) -> {
                    String oldName = customInfo.getName();
                    customInfo.setName((String) name);
                    for (User user : formPage.observeWorkingCopy().getValue().getUsers().getUser()) {
                        user.getCustomUserInfoValues().getCustomUserInfoValue().stream()
                                .filter(aCustomInfo -> Objects.equals(aCustomInfo.getName(), oldName))
                                .forEach(aCustomInfo -> aCustomInfo.setName((String) name));
                    }
                    // TODO necessary since macos Bigsure, should be rmeove in the futur
                    if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
                        Display.getDefault().syncExec(() -> {
                            formPage.redrawCustomInfoTable();
                        });
                    }
                })
                .create());
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addCustomInfoItem = new ToolItem(toolBar, SWT.PUSH);
        addCustomInfoItem.setImage(Pics.getImage(PicsConstants.add_simple));
        addCustomInfoItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_CUSTOM_INFO_BUTTON_ID);
        addCustomInfoItem.setText(Messages.add);
        addCustomInfoItem.setToolTipText(Messages.addCustomInfoTooltip);
        addCustomInfoItem.addListener(SWT.Selection, e -> addCustomInfo());

        deleteCustomInfoItem = new ToolItem(toolBar, SWT.PUSH);
        deleteCustomInfoItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_CUSTOM_INFO_BUTTON_ID);
        deleteCustomInfoItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteCustomInfoItem.setText(Messages.delete);
        deleteCustomInfoItem.setToolTipText(Messages.deleteCustomInfoTooltip);
        deleteCustomInfoItem.addListener(SWT.Selection, e -> removeSelectedCustomInfo());
    }

    private void removeSelectedCustomInfo() {
        CustomUserInfoDefinition customInfoToRemove = selectedCustomInfoObservable.getValue();
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                Messages.otherInformationGroupRemoveDialogTitle,
                String.format(Messages.deleteCustomInfoConfirmation, customInfoToRemove.getName()))) {
            for (User user : formPage.observeWorkingCopy().getValue().getUsers().getUser()) {
                user.getCustomUserInfoValues().getCustomUserInfoValue()
                        .removeIf(aCustomInfo -> Objects.equals(aCustomInfo.getName(), customInfoToRemove.getName()));
            }
            input.remove(customInfoToRemove);
            // TODO necessary since macos Bigsure, should be rmeove in the futur
            if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
                Display.getDefault().asyncExec(() -> {
                    viewer.getTable().redraw();
                    formPage.redrawCustomInfoTable();
                });
            }
        }
    }

    private void addCustomInfo() {
        CustomUserInfoDefinition customUserInfo = OrganizationFactory.eINSTANCE.createCustomUserInfoDefinition();
        String newName = NamingUtils.generateNewNameCaseInsensitive(
                getExistingNames(), Messages.defaultCustomUserInformationName);
        customUserInfo.setName(newName);
        customUserInfo.setDescription("");
        input.add(customUserInfo);

        for (User user : formPage.observeWorkingCopy().getValue().getUsers().getUser()) {
            CustomUserInfoValue newCustomUserInfoValueType = OrganizationFactory.eINSTANCE.createCustomUserInfoValue();
            newCustomUserInfoValueType.setName(newName);
            newCustomUserInfoValueType.setValue("");
            if (user.getCustomUserInfoValues() == null) {
                user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
            }
            user.getCustomUserInfoValues().getCustomUserInfoValue().add(newCustomUserInfoValueType);
        }
    }

    private Set<String> getExistingNames() {
        return input.stream()
                .map(CustomUserInfoDefinition::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

}
