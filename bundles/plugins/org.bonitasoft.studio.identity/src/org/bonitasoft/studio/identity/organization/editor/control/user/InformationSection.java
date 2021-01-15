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

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.ContactData;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class InformationSection {

    public static final int SHORT_FIELD_MAX_LENGTH = 50;
    public static final int LONG_FIELD_MAX_LENGTH = 255;

    private UserFormPage formPage;
    private Section section;
    private Section customInfoSection;
    private DataBindingContext ctx;
    private IObservableValue<User> selectedUserObservable;
    private NativeTabFolderWidget informationsTabFolder;
    private NativeTabItemWidget personalTab;
    private NativeTabItemWidget professionnalTab;
    private NativeTabItemWidget customTab;

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
        createCustomInformationsSection(informationComposite);

        return informationComposite;
    }

    // TODO -> Bouger cette section en dessous de la user liste, c'est independent. 
    private void createCustomInformationsSection(Composite parent) {
        customInfoSection = formPage.getToolkit().createSection(parent, Section.TWISTIE);
        customInfoSection.setLayout(GridLayoutFactory.fillDefaults().create());
        customInfoSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        customInfoSection.setText(Messages.manageCustomInfo);

        Composite customInfoComposite = formPage.getToolkit().createComposite(customInfoSection);
        customInfoComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        customInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        customInfoSection.setClient(customInfoComposite);
    }

    private void createInformationsTabFolder(Composite informationParent) {
        informationsTabFolder = new NativeTabFolderWidget.Builder().createIn(informationParent);
        informationsTabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
        informationsTabFolder
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        professionnalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.professionalData)
                .withControl(createProfessionalInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        personalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.personalData)
                .withControl(createPersonalInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        customTab = new NativeTabItemWidget.Builder()
                .withText(Messages.other)
                .withStyle(SWT.SCROLL_LINE)
                .withControl(createCustomInformationComposite(informationsTabFolder.getTabFolder()))
                .createIn(informationsTabFolder);

        informationsTabFolder.setSelection(professionnalTab);
    }

    private Composite createPersonalInformationComposite(Composite parent) {
        Composite personalInfoComposite = formPage.getToolkit().createComposite(parent);
        personalInfoComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10)
                .spacing(10, LayoutConstants.getSpacing().y).numColumns(2).create());
        personalInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        IObservableValue<ContactData> contactDataObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__PERSONAL_DATA);

        createContactInfoFields(personalInfoComposite, contactDataObservable);

        return personalInfoComposite;
    }

    private Composite createProfessionalInformationComposite(Composite parent) {
        Composite professionalInfoComposite = formPage.getToolkit().createComposite(parent);
        professionalInfoComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10)
                .spacing(10, LayoutConstants.getSpacing().y).numColumns(2).equalWidth(true).create());
        professionalInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        IObservableValue<ContactData> contactDataObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__PROFESSIONAL_DATA);

        createContactInfoFields(professionalInfoComposite, contactDataObservable);

        return professionalInfoComposite;
    }

    protected void createContactInfoFields(Composite parent, IObservableValue<ContactData> contactDataObservable) {

        Section contactSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        contactSection.setLayout(GridLayoutFactory.fillDefaults().create());
        contactSection.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        contactSection.setText(Messages.contact);

        Composite contactComposite = formPage.getToolkit().createComposite(contactSection);
        contactComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).create());
        contactComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createEmailField(contactComposite, contactDataObservable);
        createPhoneField(contactComposite, contactDataObservable);
        createMobileField(contactComposite, contactDataObservable);
        createFaxField(contactComposite, contactDataObservable);
        createWebSiteField(contactComposite, contactDataObservable);

        contactSection.setClient(contactComposite);

        Section locationSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        locationSection.setLayout(GridLayoutFactory.fillDefaults().create());
        locationSection.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        locationSection.setText(Messages.location);

        Composite locationComposite = formPage.getToolkit().createComposite(locationSection);
        locationComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).create());
        locationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

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

        formPage.getToolkit().createLabel(customInfoComposite, "CUSTOM");

        return customInfoComposite;
    }

}
