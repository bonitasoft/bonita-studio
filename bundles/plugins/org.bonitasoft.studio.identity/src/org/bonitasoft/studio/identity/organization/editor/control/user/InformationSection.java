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

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.ContactData;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValuesType;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class InformationSection {

    public static final int SHORT_FIELD_MAX_LENGTH = 50;
    public static final int LONG_FIELD_MAX_LENGTH = 255;
    public static final String CUSTOM_INFO_LIST_VIEWER_ID = "customInfoViewer";

    private UserFormPage formPage;
    private Section section;
    private DataBindingContext ctx;
    private IObservableValue<User> selectedUserObservable;
    private NativeTabFolderWidget informationsTabFolder;
    private NativeTabItemWidget professionnalTab;
    private TableViewer customInfoViewer;

    public InformationSection(Composite parent, UserFormPage formPage, DataBindingContext ctx,
            IObservableValue<User> selectedUserObservable) {
        this.formPage = formPage;
        this.ctx = ctx;
        this.selectedUserObservable = selectedUserObservable;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        section.setText(Messages.informations);

        section.setClient(createInformationsComposite());
    }

    private Composite createInformationsComposite() {
        Composite informationComposite = formPage.getToolkit().createComposite(section);
        informationComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        informationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createInformationsTabFolder(informationComposite);

        return informationComposite;
    }

    private void createInformationsTabFolder(Composite informationParent) {
        informationsTabFolder = new NativeTabFolderWidget.Builder().createIn(informationParent);
        informationsTabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
        informationsTabFolder
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        professionnalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.professionalData)
                .withControl(createProfessionalInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        new NativeTabItemWidget.Builder()
                .withText(Messages.personalData)
                .withControl(createPersonalInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        new NativeTabItemWidget.Builder()
                .withText(Messages.other)
                .withStyle(SWT.SCROLL_LINE)
                .withControl(createCustomInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        informationsTabFolder.setSelection(professionnalTab);
    }

    private Composite createPersonalInformationComposite(Composite parent) {
        ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(sc);

        Composite personalInfoComposite = formPage.getToolkit().createComposite(sc);
        personalInfoComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10)
                .spacing(10, LayoutConstants.getSpacing().y).numColumns(2).create());
        personalInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        IObservableValue<ContactData> contactDataObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__PERSONAL_DATA);

        createContactInfoFields(personalInfoComposite, contactDataObservable);

        sc.setContent(personalInfoComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);
        sc.setMinHeight(personalInfoComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

        return sc;
    }

    private Composite createProfessionalInformationComposite(Composite parent) {
        ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(sc);

        Composite professionalInfoComposite = formPage.getToolkit().createComposite(sc);
        professionalInfoComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10)
                .spacing(10, LayoutConstants.getSpacing().y).numColumns(2).equalWidth(true).create());
        professionalInfoComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        IObservableValue<ContactData> contactDataObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__PROFESSIONAL_DATA);

        createContactInfoFields(professionalInfoComposite, contactDataObservable);

        sc.setContent(professionalInfoComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);
        sc.setMinHeight(professionalInfoComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

        return sc;
    }

    protected void createContactInfoFields(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        Section contactSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        contactSection.setLayout(GridLayoutFactory.fillDefaults().create());
        contactSection.setLayoutData(GridDataFactory.fillDefaults().create());
        contactSection.setText(Messages.contact);

        Composite contactComposite = formPage.getToolkit().createComposite(contactSection);
        contactComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).create());
        contactComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createEmailField(contactComposite, contactDataObservable);
        createPhoneField(contactComposite, contactDataObservable);
        createMobileField(contactComposite, contactDataObservable);
        createFaxField(contactComposite, contactDataObservable);
        createWebSiteField(contactComposite, contactDataObservable);

        contactSection.setClient(contactComposite);

        Section locationSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        locationSection.setLayout(GridLayoutFactory.fillDefaults().create());
        locationSection.setLayoutData(GridDataFactory.fillDefaults().create());
        locationSection.setText(Messages.location);

        Composite locationComposite = formPage.getToolkit().createComposite(locationSection);
        locationComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).create());
        locationComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createBuildingInfoField(locationComposite, contactDataObservable);
        createPersonalRoomField(locationComposite, contactDataObservable);
        createAddressField(locationComposite, contactDataObservable);
        createCityField(locationComposite, contactDataObservable);
        createStateField(locationComposite, contactDataObservable);
        createZipcodeField(locationComposite, contactDataObservable);
        createCountryField(locationComposite, contactDataObservable);

        locationSection.setClient(locationComposite);
    }

    private void createPersonalRoomField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__ROOM,
                Messages.roomLabel, Messages.roomHint, SHORT_FIELD_MAX_LENGTH, 1);
    }

    private void createBuildingInfoField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__BUILDING,
                Messages.buildingLabel, Messages.buildingHint, SHORT_FIELD_MAX_LENGTH, 1);
    }

    private void createCountryField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__COUNTRY,
                Messages.countryLabel, Messages.coutryHint, LONG_FIELD_MAX_LENGTH, 1);
    }

    private void createZipcodeField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__ZIP_CODE,
                Messages.zipCodeLabel, Messages.zipCodeHint, SHORT_FIELD_MAX_LENGTH, 1);
    }

    private void createStateField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__STATE,
                Messages.stateLabel, Messages.stateHint, LONG_FIELD_MAX_LENGTH, 1);
    }

    private void createCityField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__CITY,
                Messages.cityLabel, Messages.cityHint, LONG_FIELD_MAX_LENGTH, 1);
    }

    private void createAddressField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__ADDRESS,
                Messages.addressLabel, Messages.addressHint, LONG_FIELD_MAX_LENGTH, 2);
    }

    private void createWebSiteField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__WEBSITE,
                Messages.websiteLabel, Messages.websiteHint, LONG_FIELD_MAX_LENGTH, 2);
    }

    private void createFaxField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__FAX_NUMBER,
                Messages.faxLabel, Messages.faxHint, SHORT_FIELD_MAX_LENGTH, 2);
    }

    private void createMobileField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__MOBILE_NUMBER,
                Messages.mobileLabel, Messages.phoneHint, SHORT_FIELD_MAX_LENGTH, 1);
    }

    private void createPhoneField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__PHONE_NUMBER,
                Messages.phoneLabel, Messages.mobileHint, SHORT_FIELD_MAX_LENGTH, 1);
    }

    private void createEmailField(Composite parent, IObservableValue<ContactData> contactDataObservable) {
        createTextField(parent, contactDataObservable, OrganizationPackage.Literals.CONTACT_DATA__EMAIL,
                Messages.emailLabel, Messages.emailHint, LONG_FIELD_MAX_LENGTH, 2);
    }

    private void createTextField(Composite parent, IObservableValue<ContactData> contactDataObservable,
            EStructuralFeature feature, String label, String placeholder, int maxLength, int horizontalSpan) {
        IObservableValue<String> fieldObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                contactDataObservable, feature);
        new TextWidget.Builder()
                .withLabel(label)
                .labelAbove()
                .withPlaceholder(placeholder)
                .adapt(formPage.getToolkit())
                .fill()
                .widthHint(horizontalSpan * 200)
                .horizontalSpan(horizontalSpan)
                .bindTo(fieldObservable)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(maxLengthValidator(label, maxLength)).create())
                .inContext(ctx)
                .createIn(parent);
    }

    private Composite createCustomInformationComposite(Composite parent) {
        Composite customInfoComposite = formPage.getToolkit().createComposite(parent);
        customInfoComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        customInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createCustomInfoViewer(customInfoComposite);

        return customInfoComposite;
    }

    private void createCustomInfoViewer(Composite parent) {
        IObservableValue<CustomUserInfoValuesType> customInfoValuesType = EMFObservables.observeDetailValue(
                Realm.getDefault(), selectedUserObservable, OrganizationPackage.Literals.USER__CUSTOM_USER_INFO_VALUES);
        IObservableList<CustomUserInfoValue> customUserInfoValues = EMFObservables.observeDetailList(Realm.getDefault(),
                customInfoValuesType, OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE);

        customInfoViewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        customInfoViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        customInfoViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, CUSTOM_INFO_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(customInfoViewer.getTable());
        ColumnViewerToolTipSupport.enableFor(customInfoViewer);
        customInfoViewer.setUseHashlookup(true);
        customInfoViewer.getTable().setHeaderVisible(true);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        customInfoViewer.getTable().setLayout(layout);

        createNameColumn();
        createValueColumn();

        customInfoViewer.setContentProvider(new ObservableListContentProvider());
        customInfoViewer.setInput(customUserInfoValues);
    }

    private void createValueColumn() {
        TableViewerColumn column = new TableViewerColumn(customInfoViewer, SWT.NONE);
        column.getColumn().setText(Messages.value);
        column.setLabelProvider(new LabelProviderBuilder<CustomUserInfoValue>()
                .withTextProvider(CustomUserInfoValue::getValue)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<CustomUserInfoValue>(customInfoViewer)
                .withValueProvider(CustomUserInfoValue::getValue)
                .withValueUpdater((customInfo, value) -> customInfo.setValue((String) value))
                .create());
    }

    private void createNameColumn() {
        TableViewerColumn column = new TableViewerColumn(customInfoViewer, SWT.NONE);
        column.getColumn().setText(Messages.name);
        column.setLabelProvider(new LabelProviderBuilder<CustomUserInfoValue>()
                .withTextProvider(CustomUserInfoValue::getName)
                .createColumnLabelProvider());
    }

    public void redrawCustomInfoTable() {
        customInfoViewer.getTable().redraw();
    }

}
