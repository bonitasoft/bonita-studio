/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.registration;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.registration.i18n.Messages;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * wizard page used to gather info from user
 *
 * @author Baptiste Mesta
 *
 */
public class BonitaRegisterWizardPage extends WizardPage {

	private final class UpdateFinishButtonModifyListener implements
			ModifyListener {
		@Override
		public void modifyText(final ModifyEvent e) {
			setPageComplete(isPageComplete());
		}
	}

	private static final String SEPARATOR = "-------";
	private Text emailText;
	private Combo countryCombo;
	private String currentCountry;
	private Composite composite;
	private final String[] countryShortList = { "Brazil", "Canada", "France",
			"Germany", "India", "Italy", "Netherlands", "Spain",
			"United_Kingdom", "United_States" };
	private final String[] countryList = { "Afghanistan", "Albania", "Algeria",
			"Andorra", "Angola", "Antigua_and_Barbuda", "Argentina", "Armenia",
			"Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
			"Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bhutan", "Bolivia", "Bosnia_and_Herzegovina", "Botswana",
			"Brazil", "Brunei", "Bulgaria", "Burkina", "Burma", "Burundi",
			"Cambodia", "Cameroon", "Canada", "Cape_Verde",
			"Central_African_Republic", "Chad", "Chile", "China", "Colombia",
			"Comoros", "Congo", "Congo_The_Democratic_Republic_of_the",
			"Cook_Islands", "Costa_Rica", "Croatia", "Cuba", "Cyprus",
			"Czech_Republic", "Denmark", "Djibouti", "Dominica",
			"Dominican_Republic", "Ecuador", "Egypt", "El_Salvador",
			"Equatorial_Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji",
			"Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
			"Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
			"Guinea-Bissau", "Guyana", "Haiti", "Holy_See", "Honduras",
			"Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
			"Ireland", "Israel", "Italy", "Ivory_Coast", "Jamaica", "Japan",
			"Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
			"Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libya", "Liechtenstein", "Lithuania", "Luxembourg",
			"Macedonia_The_Former_Yugoslav_Republic_of", "Madagascar",
			"Malawi", "Malaysia", "Maldives", "Mali", "Malta",
			"Marshall_Islands", "Mauritania", "Mauritius", "Mexico",
			"Micronesia_The_Federated_States_of", "Moldova", "Monaco",
			"Mongolia", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New_Zealand", "Nicaragua", "Niger", "Nigeria",
			"Niue", "North_Korea", "Norway", "Oman", "Pakistan", "Palau",
			"Panama", "Papua_New_Guinea", "Paraguay", "Peru", "Philippines",
			"Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda",
			"Saint_Kitts_and_Nevis", "Saint_Lucia",
			"Saint_Vincent_and_the_Grenadines", "San_Marino",
			"Sao_Tome_and_Principe", "Saudi_Arabia", "Senegal", "Seychelles",
			"Sierra_Leone", "Singapore", "Slovakia", "Slovenia",
			"Solomon_Islands", "Somalia", "South_Africa", "South_Korea",
			"Spain", "Sri_Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand",
			"Togo", "Tonga", "Trinidad_and_Tobago", "Tunisia", "Turkey",
			"Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
			"United_Arab_Emirates", "United_Kingdom", "United_States",
			"Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
			"Western_Samoa", "Yemen", "Yougoslavia", "Zambia", "Zimbabwe" };
	private Map<String, String> labelMap;
	private Text firstName;
	private Text lastName;
	private Text phone;
	private Text organizationText;

	protected BonitaRegisterWizardPage(final String pageName) {
		super(pageName);
		setTitle(Messages.RegisterWizardPage_Title);
		setDescription(Messages.bind(Messages.RegisterWizardPage_Desc,
				new Object[] { bosProductName }));
		setImageDescriptor(Pics.getWizban());
		initCurrentCountry();
	}

	@Override
	public void createControl(final Composite parent) {

		composite = new Composite(parent, SWT.NONE);

		final GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(GridDataFactory.fillDefaults().create());

		final GridData fieldGridData = new GridData(GridData.FILL_HORIZONTAL);

		createFirstNameLine(fieldGridData);
		createLastNameLine(fieldGridData);
		createOrganizationLine(fieldGridData);
		createPhoneLine(fieldGridData);
		createEmailLine(fieldGridData);
		createCountryLine(fieldGridData);

		final Label mandatoryLabel = new Label(composite, SWT.NONE);
		mandatoryLabel.setText(Messages.RegisterWizardPage_mandatory);
		mandatoryLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				true));

		setControl(composite);
	}

	private void createOrganizationLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_organization);

		organizationText = new Text(composite, SWT.BORDER);
		organizationText.setLayoutData(fieldGridData);

		organizationText
				.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void createCountryLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_country);

		countryCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		countryCombo.setLayoutData(fieldGridData);
		addCountryList(countryCombo);
		countryCombo.select(countryCombo.indexOf(currentCountry));

		countryCombo.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void createEmailLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_email);

		emailText = new Text(composite, SWT.BORDER);
		emailText.setLayoutData(fieldGridData);

		emailText.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void createLeftLabelInLine(final String text) {
		final Label label = new Label(composite, SWT.NONE);
		label.setText(text + " *");
		label.setLayoutData(GridDataFactory.swtDefaults()
				.align(SWT.RIGHT, SWT.CENTER).create());
	}

	private void createPhoneLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_phone);

		phone = new Text(composite, SWT.BORDER);
		phone.setLayoutData(fieldGridData);

		phone.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void createLastNameLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_lastName);

		lastName = new Text(composite, SWT.BORDER);
		lastName.setLayoutData(fieldGridData);

		lastName.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void createFirstNameLine(final GridData fieldGridData) {
		createLeftLabelInLine(Messages.RegisterWizardPage_firstName);

		firstName = new Text(composite, SWT.BORDER);
		firstName.setLayoutData(fieldGridData);

		firstName.addModifyListener(new UpdateFinishButtonModifyListener());
	}

	private void addCountryList(final Combo combo) {
		final List<Locale> locales = new ArrayList<Locale>();
		for (final Locale locale : Locale.getAvailableLocales()) {
			locales.add(locale);
		}

		Collections.sort(locales, new LocalesComparator());
		final ResourceBundle labels = ResourceBundle.getBundle("countries");

		final ArrayList<String> shortList = new ArrayList<String>();
		labelMap = new HashMap<String, String>();
		for (final String country : countryShortList) {
			try {
				shortList.add(labels.getString(country));
				labelMap.put(labels.getString(country), country);
			} catch (final Exception e) {
				BonitaStudioLog.error(e);
				shortList.add(country);
				labelMap.put(country, country);
			}
		}
		final ArrayList<String> longList = new ArrayList<String>();
		for (final String country : countryList) {
			try {
				longList.add(labels.getString(country));
				labelMap.put(labels.getString(country), country);
			} catch (final Exception e) {
				BonitaStudioLog.error(e);
				longList.add(country);
				labelMap.put(country, country);
			}
		}
		Collections.sort(shortList);
		Collections.sort(longList);
		for (final String string : shortList) {
			combo.add(string);
		}
		combo.add(SEPARATOR);
		for (final String string : longList) {
			combo.add(string);
		}
		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(final ModifyEvent arg0) {
				if (combo.getText() == SEPARATOR) {
					combo.select(combo.getSelectionIndex() - 1);
				}
				setPageComplete(isPageComplete());
			}
		});
	}

	private void initCurrentCountry() {
		currentCountry = Locale.getDefault().getDisplayCountry();
	}

	public String getEmail() {
		return emailText.getText();
	}

	public String getCountry() {
		if (labelMap.get(countryCombo.getText()) != null) {
			return labelMap.get(countryCombo.getText());
		}
		return countryCombo.getText();
	}

	public String getOrganization() {
		return organizationText.getText();
	}

	@Override
	public boolean isPageComplete() {
		return isMailValid() && isFieldNotEmpty(lastName)
				&& isFieldNotEmpty(firstName)
				&& isFieldNotEmpty(organizationText) && isFieldNotEmpty(phone)
				&& isComboNotEmpty(countryCombo);
	}

	private boolean isComboNotEmpty(final Combo combo) {
		return combo.getText() != null && !combo.getText().isEmpty();
	}

	private boolean isFieldNotEmpty(final Text textToCheck) {
		System.out.println("is field empty? " + textToCheck.getText() != null
				&& !textToCheck.getText().isEmpty());
		return textToCheck.getText() != null
				&& !textToCheck.getText().isEmpty();
	}

	private boolean isMailValid() {
		return getEmail() != null
				&& getEmail().matches(".+@.+?\\.[a-zA-Z]{2,6}");
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone.getText();
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName.getText();
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName.getText();
	}

}

class LocalesComparator implements Comparator<Locale> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Locale o1, final Locale o2) {
		final String string1 = o1.getDisplayCountry();
		final String string2 = o2.getDisplayCountry();
		final int comp = string1.compareTo(string2);
		return comp;
	}

}
