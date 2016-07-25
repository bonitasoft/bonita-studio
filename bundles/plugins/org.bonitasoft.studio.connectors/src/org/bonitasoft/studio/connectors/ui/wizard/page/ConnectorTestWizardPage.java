///**
// * Copyright (C) 2009 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.connectors.ui.wizard.page;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.bonitasoft.studio.connector.definition.ConnectorDefinition;
//import org.bonitasoft.studio.connectors.i18n.Messages;
//import org.bonitasoft.studio.connectors.ui.wizard.DefaultSetConnectorFieldsWizard;
//import org.bonitasoft.studio.pics.Pics;
//import org.eclipse.jface.wizard.IWizardPage;
//import org.eclipse.jface.wizard.WizardPage;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.ScrolledComposite;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Text;
//
///**
// * @author Mickael Istria
// * @author Romain Bioteau
// */
//public class ConnectorTestWizardPage extends WizardPage implements IWizardPage {
//
//    private ScrolledComposite generalComposite;
//    private Composite mainComposite;
//    private Map<String, Object> testParameters ;
//    private final DefaultSetConnectorFieldsWizard sourceWizard;
//    private final Map<Label, Control> widgetMap ;
//
//    public ConnectorTestWizardPage(DefaultSetConnectorFieldsWizard wizard, ConnectorDefinition connector) {
//        super(ConnectorTestWizardPage.class.getName());
//        setTitle(Messages.testConnectorTitle);
//        setDescription(Messages.testConnectorDescription);
//        setWizard(wizard);
//        wizard.setWindowTitle(connector.getId());
//        setImageDescriptor(Pics.getWizban());
//        sourceWizard = wizard ;
//        widgetMap = new HashMap<Label, Control>();
//    }
//
//    /* (non-Javadoc)
//     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
//     */
//    @Override
//    public void createControl(final Composite arg0) {
//
//        Composite parent = new Composite(arg0,SWT.NONE);
//        parent.setLayout(new GridLayout(1, false));
//        parent.setLayoutData(GridData.FILL_BOTH);
//        generalComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
//        generalComposite.setLayout(new GridLayout(1, false));
//        GridData gd = new GridData(GridData.FILL_BOTH);
//        gd.heightHint = 400 ;
//        generalComposite.setLayoutData(gd);
//        mainComposite = new Composite(generalComposite,SWT.NONE);
//        mainComposite.setLayout(new GridLayout(2, false));
//        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
//
//        //new Label(generalComposite, SWT.NONE);//FILLER
//        Button testButton = new Button(parent, SWT.FLAT);
//        testButton.setText(Messages.testConnector);
//        gd = new GridData(SWT.END, SWT.DEFAULT, true, false) ;
//        testButton.setLayoutData(gd);
//        //        testButton.addListener(SWT.Selection, new Listener() {
//        //            @Override
//        //            public void handleEvent(Event event) {
//        //                //TODO :  find a better way to know the connector type
//        //                Class<? extends Connector> connectorClass = connector.getConnectorClass();
//        //                String name = connectorClass.getName();
//        //                String connectorType;
//        //                /*WARNING: the order is important*/
//        //                if(Filter.class.isAssignableFrom(connectorClass)){
//        //                    connectorType = TestConnector.FILTER_TYPE;
//        //                } else if (RoleResolver.class.isAssignableFrom(connectorClass)) {
//        //                    connectorType = TestConnector.GROUP_TYPE;
//        //                } else {
//        //                    connectorType = TestConnector.CONNECTOR_TYPE;
//        //                }
//        //                new TestConnector(name, getCurrentConfiguration(), connectorType).test();
//        //            }
//        //        });
//        //        setTitleIcon();
//        //        setControl(parent);
//        //        updateScrolledComposite();
//    }
//
//    protected Map<String, Object> getCurrentConfiguration() {
//        if(widgetMap.isEmpty()){
//            return sourceWizard.getInputParameters() ;
//        }else{
//            HashMap<String, Object> result = new HashMap<String, Object>();
//            Iterator<Entry<Label, Control>> it = widgetMap.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<org.eclipse.swt.widgets.Label, org.eclipse.swt.widgets.Control> entry = it.next();
//                if(entry.getValue() instanceof Text){
//                    result.put("set"+entry.getKey().getText(),((Text) entry.getValue()).getText()) ; //$NON-NLS-1$
//                }else if(entry.getValue() instanceof Button){
//                    result.put("set"+entry.getKey().getText(),((Button) entry.getValue()).getSelection()) ; //$NON-NLS-1$
//                }else if(entry.getValue() instanceof Combo){
//                    result.put("set"+entry.getKey().getText(),((Combo) entry.getValue()).getText()) ; //$NON-NLS-1$
//                }
//            }
//            return result;
//        }
//    }
//
//    @Override
//    public void setVisible(boolean visible) {
//        super.setVisible(visible);
//        if(visible){
//            Map<String, Object> fielsValue = sourceWizard.getInputParameters() ;
//            testParameters = new HashMap<String, Object>(fielsValue);
//            update(mainComposite,testParameters);
//        }
//    }
//
//    private void update(Composite composite , Map<String, Object> fielsValue) {
//        //        for(org.eclipse.swt.widgets.Control c : composite.getChildren()){
//        //            c.dispose();
//        //        }
//        //        widgetMap.clear();
//        //        Iterator<Entry<String, Object>> it = fielsValue.entrySet().iterator();
//        //        while (it.hasNext()) {
//        //            Entry<String, Object> var = it.next();
//        //
//        //            final Label varLabel = new Label(composite, SWT.NONE);
//        //            String textLabel = var.getKey().substring(3);
//        //            varLabel.setText(textLabel);
//        //
//        //            GridData gd = null;
//        //            String type = getWidgetType(var.getKey()) ;
//        //            if(type != null){
//        //                if(type.equals(Checkbox.class.getName())){
//        //                    Button checkboxButton = new Button(composite, SWT.CHECK);
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    checkboxButton.setLayoutData(gd);
//        //                    if(var.getValue() instanceof Boolean){
//        //                        checkboxButton.setSelection((Boolean)var.getValue());
//        //                    }else{
//        //                        checkboxButton.setSelection(Boolean.parseBoolean(var.getValue().toString()));
//        //                    }
//        //                    widgetMap.put(varLabel, checkboxButton);
//        //                }else if(type.equals(Password.class.getName())){
//        //                    Text passwordText = new Text(composite, SWT.BORDER);
//        //                    passwordText.setEchoChar('*');
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    passwordText.setLayoutData(gd);
//        //
//        //                    passwordText.setText(var.getValue().toString());
//        //
//        //                    widgetMap.put(varLabel, passwordText);
//        //
//        //                }else if(type.equals(Select.class.getName())){
//        //                    Combo combo = new Combo(composite, SWT.READ_ONLY);
//        //
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    combo.setLayoutData(gd);
//        //
//        //
//        //
//        //                    for(Object o : getSelectWidgetValues(var.getKey())){
//        //                        combo.add(o.toString());
//        //                    }
//        //                    if(var.getValue() != null){
//        //                        combo.setText(getSelectWidgetDefaultValues((var.getKey())));
//        //                    }
//        //
//        //                    widgetMap.put(varLabel, combo);
//        //
//        //                }else if(type.equals(RadioGroup.class.getName())){
//        //                    Button radio = new Button(composite, SWT.RADIO);
//        //
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    radio.setLayoutData(gd);
//        //
//        //
//        //                    if(var.getValue() instanceof Boolean){
//        //                        radio.setSelection((Boolean) var.getValue());
//        //                    }else{
//        //                        radio.setSelection(Boolean.parseBoolean(var.getValue().toString()));
//        //                    }
//        //                    widgetMap.put(varLabel,radio);
//        //
//        //                }else if(type.equals(TextArea.class.getName())){
//        //                    Text varValueText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    gd.heightHint = 60 ;
//        //                    varValueText.setLayoutData(gd);
//        //                    if(var.getValue() != null){
//        //                        varValueText.setText(var.getValue().toString());
//        //                    }
//        //                    widgetMap.put(varLabel, varValueText);
//        //
//        //                }else if(type.equals(org.bonitasoft.studio.connector.definition.Text.class.getName())){
//        //                    Text varValueText = new Text(composite, SWT.BORDER);
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    varValueText.setLayoutData(gd);
//        //                    if(var.getValue() != null){
//        //                        varValueText.setText(var.getValue().toString());
//        //                    }
//        //                    widgetMap.put(varLabel, varValueText);
//        //                } else{
//        //                    Text varValueText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
//        //                    gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                    varValueText.setLayoutData(gd);
//        //                    if(var.getValue() != null){
//        //                        varValueText.setText(var.getValue().toString());
//        //                    }
//        //                    //widgetMap.put(varLabel, varValueText);
//        //                }
//        //
//        //            }else{
//        //                Text varValueText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
//        //                gd = new GridData(GridData.FILL_HORIZONTAL);
//        //                varValueText.setLayoutData(gd);
//        //                if(var.getValue() != null){
//        //                    varValueText.setText(var.getValue().toString());
//        //                }
//        //                //widgetMap.put(varLabel, varValueText);
//        //            }
//        //        }
//        //        composite.layout(true);
//        //   updateScrolledComposite();
//    }
//
//    //    private String getWidgetType(String key) {
//    //        List<Component> components = connector.getAllInputs() ;
//    //        for(Component c : components){
//    //            if(c instanceof Widget){
//    //                Widget widget = (Widget)c;
//    //                if(widget.getSetter().getSetterName().equals(key)) {
//    //                    return widget.getClass().getName();
//    //                }
//    //            }if(c instanceof Group){
//    //                for(WidgetComponent w: ((Group)c).getWidgets()){
//    //                    if(w.getSetter().getSetterName().equals(key)) {
//    //                        return w.getClass().getName();
//    //                    }
//    //                }
//    //            }
//    //        }
//    //        return null;
//    //    }
//
//    //    private Object[] getSelectWidgetValues(String key) {
//    //        List<Component> components = connector.getAllInputs() ;
//    //        for(Component c : components){
//    //            if(c instanceof Widget){
//    //                Widget widget = (Widget) c;
//    //                if(widget.getSetter().getSetterName().equals(key)){
//    //                    return ((Select)widget).getValues().keySet().toArray();
//    //                }
//    //
//    //            }else if(c instanceof Group){
//    //                for(WidgetComponent w : ((Group) c).getWidgets()){
//    //                    if(w.getSetter().getSetterName().equals(key)){
//    //                        return ((Select)w).getValues().keySet().toArray();
//    //                    }
//    //                }
//    //            }
//    //        }
//    //        return null;
//    //    }
//    //
//    //    private String getSelectWidgetDefaultValues(String key) {
//    //        List<Component> components = connector.getAllInputs() ;
//    //        for(Component c : components){
//    //            if(c instanceof Widget){
//    //                Widget widget = (Widget) c;
//    //                if(widget.getSetter().getSetterName().equals(key)){
//    //                    return ((Select)widget).getSetter().getParameters()[0].toString();
//    //                }
//    //
//    //            }else if(c instanceof Group){
//    //                for(WidgetComponent w : ((Group) c).getWidgets()){
//    //                    if(w.getSetter().getSetterName().equals(key)){
//    //                        return ((Select)w).getSetter().getParameters()[0].toString();
//    //                    }
//    //                }
//    //            }
//    //        }
//    //        return null;
//    //    }
//    //
//    //
//    //    private void setTitleIcon() {
//    //        InputStream is = connector.getIcon();
//    //        if (is != null) {
//    //            ImageData imageData = new ImageData(is);
//    //            imageData = imageData.scaledTo(16, 16);
//    //            Image image = new Image(PlatformUI.getWorkbench().getDisplay(), imageData);
//    //            getShell().setImage(image);
//    //            try {
//    //                is.close() ;
//    //            } catch (IOException e) {
//    //                BonitaStudioLog.log(e);
//    //            }
//    //        }
//    //
//    //    }
//    //
//    //
//    //    /**
//    //     * Update the size and content of the scrolledComposite
//    //     */
//    //    private void updateScrolledComposite() {
//    //        generalComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//    //        generalComposite.setAlwaysShowScrollBars(false);
//    //        generalComposite.setExpandHorizontal(true);
//    //        generalComposite.setExpandVertical(true);
//    //        generalComposite.setContent(mainComposite);
//    //    }
//
//
//}
