/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
import static org.bonitasoft.studio.common.Messages.bosProductName;
/**
 * 	wizard page used to gather info from user
 * 
 * @author Baptiste Mesta
 *
 */
public class BonitaRegisterWizardPage extends WizardPage {

	private static final String SEPARATOR = "-------";
	private Text emailText;
	private Combo countryCombo;
	private String currentCountry;
	private Composite composite;
	private String[] countryShortList = {"Brazil",
			"Canada",
			"France",
			"Germany",
			"India",
			"Italy",
			"Netherlands",
			"Spain",
			"United_Kingdom",
			"United_States"};
	private String [] countryList = {"Afghanistan",
			"Albania",
			"Algeria",
			"Andorra",
			"Angola",
			"Antigua_and_Barbuda",
			"Argentina",
			"Armenia",
			"Australia",
			"Austria",
			"Azerbaijan",
			"Bahamas",
			"Bahrain",
			"Bangladesh",
			"Barbados",
			"Belarus",
			"Belgium",
			"Belize",
			"Benin",
			"Bhutan",
			"Bolivia",
			"Bosnia_and_Herzegovina",
			"Botswana",
			"Brazil",
			"Brunei",
			"Bulgaria",
			"Burkina",
			"Burma",
			"Burundi",
			"Cambodia",
			"Cameroon",
			"Canada",
			"Cape_Verde",
			"Central_African_Republic",
			"Chad",
			"Chile",
			"China",
			"Colombia",
			"Comoros",
			"Congo",
			"Congo_The_Democratic_Republic_of_the",
			"Cook_Islands",
			"Costa_Rica",
			"Croatia",
			"Cuba",
			"Cyprus",
			"Czech_Republic",
			"Denmark",
			"Djibouti",
			"Dominica",
			"Dominican_Republic",
			"Ecuador",
			"Egypt",
			"El_Salvador",
			"Equatorial_Guinea",
			"Eritrea",
			"Estonia",
			"Ethiopia",
			"Fiji",
			"Finland",
			"France",
			"Gabon",
			"Gambia",
			"Georgia",
			"Germany",
			"Ghana",
			"Greece",
			"Grenada",
			"Guatemala",
			"Guinea",
			"Guinea-Bissau",
			"Guyana",
			"Haiti",
			"Holy_See",
			"Honduras",
			"Hungary",
			"Iceland",
			"India",
			"Indonesia",
			"Iran",
			"Iraq",
			"Ireland",
			"Israel",
			"Italy",
			"Ivory_Coast",
			"Jamaica",
			"Japan",
			"Jordan",
			"Kazakhstan",
			"Kenya",
			"Kiribati",
			"Kuwait",
			"Kyrgyzstan",
			"Laos",
			"Latvia",
			"Lebanon",
			"Lesotho",
			"Liberia",
			"Libya",
			"Liechtenstein",
			"Lithuania",
			"Luxembourg",
			"Macedonia_The_Former_Yugoslav_Republic_of",
			"Madagascar",
			"Malawi",
			"Malaysia",
			"Maldives",
			"Mali",
			"Malta",
			"Marshall_Islands",
			"Mauritania",
			"Mauritius",
			"Mexico",
			"Micronesia_The_Federated_States_of",
			"Moldova",
			"Monaco",
			"Mongolia",
			"Morocco",
			"Mozambique",
			"Namibia",
			"Nauru",
			"Nepal",
			"Netherlands",
			"New_Zealand",
			"Nicaragua",
			"Niger",
			"Nigeria",
			"Niue",
			"North_Korea",
			"Norway",
			"Oman",
			"Pakistan",
			"Palau",
			"Panama",
			"Papua_New_Guinea",
			"Paraguay",
			"Peru",
			"Philippines",
			"Poland",
			"Portugal",
			"Qatar",
			"Romania",
			"Russia",
			"Rwanda",
			"Saint_Kitts_and_Nevis",
			"Saint_Lucia",
			"Saint_Vincent_and_the_Grenadines",
			"San_Marino",
			"Sao_Tome_and_Principe",
			"Saudi_Arabia",
			"Senegal",
			"Seychelles",
			"Sierra_Leone",
			"Singapore",
			"Slovakia",
			"Slovenia",
			"Solomon_Islands",
			"Somalia",
			"South_Africa",
			"South_Korea",
			"Spain",
			"Sri_Lanka",
			"Sudan",
			"Suriname",
			"Swaziland",
			"Sweden",
			"Switzerland",
			"Syria",
			"Tajikistan",
			"Tanzania",
			"Thailand",
			"Togo",
			"Tonga",
			"Trinidad_and_Tobago",
			"Tunisia",
			"Turkey",
			"Turkmenistan",
			"Tuvalu",
			"Uganda",
			"Ukraine",
			"United_Arab_Emirates",
			"United_Kingdom",
			"United_States",
			"Uruguay",
			"Uzbekistan",
			"Vanuatu",
			"Venezuela",
			"Vietnam",
			"Western_Samoa",
			"Yemen",
			"Yougoslavia",
			"Zambia",
			"Zimbabwe"};
	private Map<String,String> labelMap;
	private Text firstName;
	private Text lastName;
	private Text phone;

	protected BonitaRegisterWizardPage(String pageName) {
		super(pageName);
        setTitle(Messages.RegisterWizardPage_Title);
        setDescription(Messages.bind(Messages.RegisterWizardPage_Desc, new Object[]{bosProductName}));
        setImageDescriptor(Pics.getWizban());
        initCurrentCountry();
	}
	

	public void createControl(Composite parent) {

		composite = new Composite(parent, SWT.NONE);
		
		Label notMandatory;
		Label mandatory;
		
		GridLayout layout = new GridLayout(3, false);
        composite.setLayout(layout);
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        GridData fieldGridData = new GridData(GridData.FILL_HORIZONTAL);
        
        Label firstNameLabel = new Label(composite, SWT.NONE);
        firstNameLabel.setText(Messages.RegisterWizardPage_firstName);
        firstNameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        firstName = new Text(composite, SWT.BORDER);
        firstName.setLayoutData(fieldGridData);
        notMandatory = new Label(composite,SWT.NONE);
        notMandatory.setText(" ");//$NON-NLS-1$
        
        Label lastNameLabel = new Label(composite, SWT.NONE);
        lastNameLabel.setText(Messages.RegisterWizardPage_lastName);
        lastNameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        lastName = new Text(composite, SWT.BORDER);
        lastName.setLayoutData(fieldGridData);
        notMandatory = new Label(composite,SWT.NONE);
        notMandatory.setText(" ");//$NON-NLS-1$
        
        Label phoneLabel = new Label(composite, SWT.NONE);
        phoneLabel.setText(Messages.RegisterWizardPage_phone);
        phoneLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        phone = new Text(composite, SWT.BORDER);
        phone.setLayoutData(fieldGridData);
        notMandatory = new Label(composite,SWT.NONE);
        notMandatory.setText(" ");//$NON-NLS-1$
        
        
        // Email
        Label emailLabel = new Label(composite, SWT.NONE);
        emailLabel.setText(Messages.RegisterWizardPage_email); 
        emailLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        emailText = new Text(composite, SWT.BORDER);
        emailText.setLayoutData(fieldGridData);
        mandatory = new Label(composite,SWT.NONE);
        mandatory.setText("*");//$NON-NLS-1$
        
        emailText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				BonitaRegisterWizardPage.this.setPageComplete(isPageComplete());
			}
		});
        
        // Country
        Label countryLabel = new Label(composite, SWT.NONE);
        countryLabel.setText(Messages.RegisterWizardPage_country); 
        countryLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        countryCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
        countryCombo.setLayoutData(fieldGridData);
        addCountryList(countryCombo);
        countryCombo.select(countryCombo.indexOf(currentCountry));
        notMandatory = new Label(composite,SWT.NONE);
        notMandatory.setText(" ");//$NON-NLS-1$
        
        Label mandatoryLabel = new Label(composite,SWT.NONE);
        mandatoryLabel.setText(Messages.RegisterWizardPage_mandatory);
        mandatoryLabel.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,true));
        
		setControl(composite);
	}

	private void addCountryList(final Combo combo) {
		List<Locale> locales = new ArrayList<Locale>();
		for (Locale locale : Locale.getAvailableLocales()) {
			locales.add(locale);
		}
		
		Collections.sort(locales,new LocalesComparator());
		ResourceBundle labels = ResourceBundle.getBundle("countries");
        
		ArrayList<String> shortList = new ArrayList<String>();
		labelMap = new HashMap<String,String>();
		for (String country : countryShortList) {
			try{
				shortList.add(labels.getString(country));
				labelMap.put(labels.getString(country), country);
			}catch(Exception e){
				BonitaStudioLog.error(e);
				shortList.add(country);
				labelMap.put(country, country);
			}
		}
		ArrayList<String> longList = new ArrayList<String>();
		for (String country : countryList) {
			try{
				longList.add(labels.getString(country));
				labelMap.put(labels.getString(country), country);
			}catch(Exception e){
				BonitaStudioLog.error(e);
				longList.add(country);
				labelMap.put(country, country);
			}
		}
		Collections.sort(shortList);
		Collections.sort(longList);
		for (String string : shortList) {
			combo.add(string);
		}
		combo.add(SEPARATOR);
		for (String string : longList) {
			combo.add(string);
		}
		combo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent arg0) {
				if(combo.getText() == SEPARATOR){
					combo.select(combo.getSelectionIndex()-1);
				}
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
		if(labelMap.get(countryCombo.getText())!= null){
			return labelMap.get(countryCombo.getText());
		}
		return countryCombo.getText();
	}

	@Override
	public boolean isPageComplete() {
		return getEmail()!= null && getEmail().matches(".+@.+?\\.[a-zA-Z]{2,6}");
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

class LocalesComparator implements Comparator<Locale>{
 
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Locale o1, Locale o2) {
		String string1 = o1.getDisplayCountry();
		String string2 = o2.getDisplayCountry();
		int comp = string1.compareTo(string2);
		return comp;
	}
	
}
