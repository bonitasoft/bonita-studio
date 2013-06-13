/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils.WidgetEnum;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * This Wizard page is the second page of the wizard which allow to select
 * multiples variables to generate a default Form
 * 
 * @author Baptiste Mesta
 * 
 */
public class FormWizardPageVars extends WizardSelectionPage {

    private HashMap<Button, WidgetEnum> checkBoxes;
    private HashMap<Data, Button> dataToCheckBoxes;
    private Text nameField;
    private Text descField;
    private Label idLabel;
    private List<Data> datas;
    private final Element pageFlow;
    private final EStructuralFeature feature;
    private List<Document> documents = null;
    private HashMap<Document, Button> documentToCheckboxes;


    /**
     * create a new instance of FormWizardPageVars with it's linked FormsSection
     * 
     * @param wizard
     */
    public FormWizardPageVars(Element pageFlow2, EStructuralFeature feature) {
        super("vars"); //$NON-NLS-1$
        pageFlow = pageFlow2;
        this.feature = feature;
        setTitle(Messages.createForm_title);
        setDescription(Messages.createForm_desc);
        setImageDescriptor(Pics.getWizban());
        datas = getDatas();
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(pageFlow2);
        if(parentProcess instanceof Pool){
            documents = ((Pool) parentProcess).getDocuments();
        }
        setPageComplete(true);
    }

    @Override
    public void createControl(Composite parent) {
        // main composite
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));

        // ----- TOP
        Composite topComposite = new Composite(composite, SWT.NONE);
        topComposite.setLayout(new GridLayout(2, false));
        topComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        GridData labelLayoutData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        GridData textWidgetData = new GridData(SWT.FILL, SWT.TOP, true, false);
        // ----------- name

        Label nameLabel = new Label(topComposite, SWT.LEFT);
        nameLabel.setLayoutData(labelLayoutData);
        nameLabel.setText(Messages.GeneralSection_Name);

        Composite nameAndId = new Composite(topComposite, SWT.NONE);
        nameAndId.setLayout(new GridLayout(2, false));
        nameAndId.setLayoutData(textWidgetData);

        nameField = new Text(nameAndId, SWT.BORDER);
        nameField.setText(generateDefaultFormName());
        GridData rd = new GridData(SWT.NONE, SWT.CENTER, false, false);
        rd.widthHint = 150;
        rd.grabExcessVerticalSpace = true;
        rd.horizontalIndent = -5;
        nameField.setLayoutData(rd);

        GridData rd1 = new GridData(SWT.NONE, SWT.CENTER, false, false);
        rd1.grabExcessVerticalSpace = false;
        rd1.widthHint = 200;

        idLabel = new Label(nameAndId, SWT.LEFT);
        idLabel.setText("(" + NamingUtils.convertToId(nameField.getText(), null) + ")"); //$NON-NLS-1$//$NON-NLS-2$
        idLabel.setLayoutData(rd1);
        nameField.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                idLabel.setText("(" + NamingUtils.convertToId(nameField.getText(), null) + ")"); //$NON-NLS-1$ //$NON-NLS-2$

            }
        });

        // ------------ Description
        Label descLabel = new Label(topComposite, SWT.LEFT);
        descLabel.setLayoutData(labelLayoutData);
        descLabel.setText(Messages.GeneralSection_Description);
        descField = new Text(topComposite, SWT.MULTI + SWT.BORDER);
        GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.heightHint = 45;
        descField.setLayoutData(gd);
        descField.setText(""); //$NON-NLS-1$

        if (datas.size() + documents.size() > 0) {

            Group datasGrp = new Group(composite, SWT.NONE);
            datasGrp.setText(Messages.FormsSection_wizardVarsGroup_Title);
            datasGrp.setToolTipText(Messages.FormsSection_wizardVarsGroup_Tooltip);
            datasGrp.setLayout(new GridLayout(1, false));
            datasGrp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
            datasGrp.setSize(datasGrp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            // --------------select unselect
            Composite selectUnselect = new Composite(datasGrp, SWT.NONE);
            selectUnselect.setLayout(new GridLayout(2, false));
            selectUnselect.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            // ------------------select
            Button selectB = new Button(selectUnselect, SWT.FLAT);
            selectB.setText(Messages.selectAll);
            selectB.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {

                    for (Button button : checkBoxes.keySet()) {
                        button.setSelection(true);
                    }
                }
            });
            // ------------------unselect
            Button unSelectB = new Button(selectUnselect, SWT.FLAT);
            unSelectB.setText(Messages.unselectAll);
            unSelectB.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {

                    for (Button button : checkBoxes.keySet()) {
                        button.setSelection(false);
                    }
                }
            });

            // --------- FIELD Section
            ScrolledComposite scrolledCompo = new ScrolledComposite(datasGrp,SWT.H_SCROLL | SWT.V_SCROLL);
            scrolledCompo.setLayout(new FillLayout());
            /*Need to limit the layout data height of the scrolledcomposite otherwise it will be grab all spaces that it need
             * and will be higher than the containing shell*/
            scrolledCompo.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(SWT.DEFAULT, 150).create());

            Composite scrollable = new Composite(scrolledCompo, SWT.NONE);
            scrollable.setLayout(new GridLayout(2, false));
            scrollable.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
            // --------------- fields
            checkBoxes = new HashMap<Button, WidgetEnum>();
            dataToCheckBoxes = new HashMap<Data, Button>();
            for (Data data : datas) {
                Button checkbox = new Button(scrollable, SWT.CHECK);
                checkbox.setText(data.getName());
                dataToCheckBoxes.put(data, checkbox);
                EClass dataEClass = data.getDataType().eClass();
                Label label = new Label(scrollable, SWT.LEFT);
                StringBuilder labelText = new StringBuilder();
                labelText.append("-> ");
                if (data.isMultiple()) {
                    labelText.append(Messages.multipleWidget);
                }
                // TODO type should not be hard coded
                if (dataEClass.equals(ProcessPackage.Literals.STRING_TYPE) ) {
                    labelText.append(Messages.FormsSection_widgetTypeText);
                    checkBoxes.put(checkbox, WidgetEnum.TEXT);
                } else if (dataEClass.equals(ProcessPackage.Literals.BOOLEAN_TYPE)) {
                    labelText.append(Messages.FormsSection_widgetTypeCheckBox);
                    checkBoxes.put(checkbox, WidgetEnum.CHECKBOX);
                } else if (dataEClass.equals(ProcessPackage.Literals.INTEGER_TYPE)) {
                    labelText.append(Messages.FormsSection_widgetTypeText);
                    checkBoxes.put(checkbox, WidgetEnum.TEXT);
                } else if (dataEClass.equals(ProcessPackage.Literals.DATE_TYPE)) {
                    labelText.append(Messages.FormsSection_widgetTypeDate);
                    checkBoxes.put(checkbox, WidgetEnum.DATE);
                } else if (dataEClass.equals(ProcessPackage.Literals.FLOAT_TYPE)) {
                    labelText.append(Messages.FormsSection_widgetTypeText);
                    checkBoxes.put(checkbox, WidgetEnum.TEXT);
                } else if (dataEClass.equals(ProcessPackage.Literals.ENUM_TYPE)) {
                    labelText.append(Messages.FormsSection_widgetTypeRadio);
                    checkBoxes.put(checkbox, WidgetEnum.RADIO);
                } else {
                    labelText.append(Messages.FormsSection_widgetTypeText);
                    checkBoxes.put(checkbox, WidgetEnum.TEXT);
                }

                if (data.isMultiple()) {
                    if (dataEClass.equals(ProcessPackage.Literals.BOOLEAN_TYPE)) {
                        checkBoxes.put(checkbox, WidgetEnum.CHECKBOX_LIST);
                    } else {
                        checkBoxes.put(checkbox, WidgetEnum.LIST);
                    }
                }

                label.setText(labelText.toString());

            }
            documentToCheckboxes = new HashMap<Document, Button>();
            if(documents != null){
                for (Document document : documents) {
                    Button checkbox = new Button(scrollable, SWT.CHECK);
                    checkbox.setText(NamingUtils.convertToId(document.getName()));
                    documentToCheckboxes.put(document, checkbox);
                    Label label = new Label(scrollable, SWT.LEFT);
                    StringBuilder labelText = new StringBuilder();
                    labelText.append("-> ");
                    labelText.append(Messages.FormsSection_widgetTypeFile);
                    label.setText(labelText.toString());
                    checkBoxes.put(checkbox, WidgetEnum.FILE);
                }
            }

            // TODO add choices between more widget types (combo boxes)
            for (Button button : checkBoxes.keySet()) {
                button.setSelection(true);
            }

            /*Add settings of the scrolled composite*/
            scrolledCompo.setMinSize(scrollable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            scrolledCompo.setAlwaysShowScrollBars(false);
            scrolledCompo.setExpandHorizontal(true);
            scrolledCompo.setExpandVertical(true);
            scrolledCompo.setContent(scrollable);
        } else {
            Label title = new Label(composite, SWT.CENTER);
            title.setText(Messages.createForm_noData);
        }

        setControl(composite);

    }

    private String generateDefaultFormName() {
        String baseName = pageFlow.getName();

        int i = ((List<?>) pageFlow.eGet(feature)).size();
        for (Iterator<?> iterator = ((List<?>) pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
            Form form = (Form) iterator.next();
            if(! form.getName().equals(baseName+i)){
                return baseName+(i<=0?"":"_"+i);
            }

        }
        return baseName+(i<=0?"":"_"+i);
    }

    /**
     * get all datas in the process and the current task
     * 
     * @return
     */
    private List<Data> getDatas() {
        datas = new ArrayList<Data>();
        if(pageFlow instanceof PageFlow){
            List<Data> allData = ModelHelper.getAccessibleDataInFormsWithNoRestriction(pageFlow, feature);
            for (Data data : allData) {
                EClass eClassData = data.getDataType().eClass();
                if(!ProcessPackage.eINSTANCE.getJavaType().isSuperTypeOf(eClassData)
                        && !ProcessPackage.eINSTANCE.getXMLType().isSuperTypeOf(eClassData)) {
                    datas.add(data);
                }
            }
        }
        return datas;
    }

    /**
     * @return the list of vars with a widget type to create a form with
     */
    public Map<Element, FormsUtils.WidgetEnum> getFormFields() {
        Map<Element, FormsUtils.WidgetEnum> result = new HashMap<Element, FormsUtils.WidgetEnum>();
        for (Data data : getDatas()) {
            if (dataToCheckBoxes.get(data) != null && dataToCheckBoxes.get(data).getSelection()) {// check
                // if
                // the
                // data
                // is selected
                result.put(data, checkBoxes.get(dataToCheckBoxes.get(data)));
                // put in the result the data
                // + the widget type
            }
        }
        if(documents != null){
            for(Document document : documents){
                if (documentToCheckboxes.get(document) != null && documentToCheckboxes.get(document).getSelection()) {// check
                    result.put(document, checkBoxes.get(documentToCheckboxes.get(document)));
                }
            }
        }
        return result;
    }

    @Override
    public boolean isPageComplete() {

        return super.isPageComplete();
    }

    /**
     * @return
     */
    public String getNameField() {
        return nameField.getText();
    }

    /**
     * @return
     */
    public String getdescField() {
        return descField.getText();
    }


}
