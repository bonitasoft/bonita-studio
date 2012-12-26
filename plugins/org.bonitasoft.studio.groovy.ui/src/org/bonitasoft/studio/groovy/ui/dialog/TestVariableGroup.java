/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.groovy.ui.dialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class TestVariableGroup extends Composite {

    private static final String NULL_RESULT = "null";
    private static final String LIST = "List";
    private static final String ATTACHMENT = "AttachmentInstance";

    private Map<Label, Control> widgetMap;
    private final Composite group;
    private GridData gd;
    private final Button initButton;
    private final Text evaluationText;
    private final Button testButton;
    private final ScrolledComposite scrollComposite;
    private final GroovyCompilationUnit unit;
    private final List<ScriptVariable> fieldNodes;
    protected String returnType;
    public TestVariableGroup(Composite parent,List<ScriptVariable> fieldNodes , GroovyCompilationUnit unit,String returnType) {
        super(parent, SWT.NONE);
        this.unit = unit ;
        this.fieldNodes = fieldNodes;
        this.returnType = returnType;
        setLayout(new GridLayout(2,false));

        initButton = new Button(this,SWT.FLAT);
        initButton.setText(Messages.initVariable);
        initButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Map<String, Serializable> variables = TestGroovyScriptUtil.createVariablesMap(TestVariableGroup.this.unit, TestVariableGroup.this.fieldNodes) ;
                update(variables);
                if(variables.keySet().isEmpty()){
                    MessageDialog.openInformation(getShell(), Messages.noVariableToSetTitle, Messages.noVariableToSetMessage) ;
                }
            }

        });
        gd = new GridData();
        gd.horizontalSpan = 2 ;
        gd.grabExcessHorizontalSpace = true ;
        gd.horizontalAlignment = SWT.FILL ;
        initButton.setLayoutData(gd);

        scrollComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        gd = new GridData();
        gd.horizontalSpan = 2 ;
        gd.grabExcessHorizontalSpace = true ;
        gd.grabExcessVerticalSpace = true ;
        gd.verticalAlignment = SWT.FILL ;
        gd.horizontalAlignment = SWT.FILL ;

        scrollComposite.setLayoutData(gd);
        scrollComposite.setLayout(new GridLayout(2, false));

        group = new Composite(scrollComposite,SWT.NONE) ;
        group.setLayout(new GridLayout(2, false));
        gd = new GridData();
        gd.horizontalSpan = 2 ;
        gd.grabExcessHorizontalSpace = true ;
        gd.horizontalAlignment = SWT.FILL ;

        group.setLayoutData(gd);

        testButton = new Button(this, SWT.FLAT);
        testButton.setText(Messages.testButtonLabel);
        gd = new GridData() ;
        gd.widthHint = 80 ;
        testButton.setLayoutData(gd);

        testButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        Object result = null ;
                        try {
                            @SuppressWarnings("restriction")
                            String expression = TestVariableGroup.this.unit.getSource()  ;
                            result = TestGroovyScriptUtil.testScript(expression,TestVariableGroup.this.returnType,TestGroovyScriptUtil.getVariableValues(TestVariableGroup.this.unit,TestVariableGroup.this.fieldNodes,widgetMap)) ;
                            if(result != null){
                                if( result instanceof Object[] ){
                                    ArrayList<Object> resTab = new ArrayList<Object>();
                                    for(Object o : ((Object[]) result)){
                                        resTab.add(o);
                                    }
                                    evaluationText.setText(resTab.toString());
                                }else{
                                    evaluationText.setText(result.toString());
                                }
                            }else{
                                evaluationText.setText(NULL_RESULT);
                            }

                        } catch (Exception e) {
                            MessageDialog.openError(getShell(), Messages.evaluationErrorTitle, e.getLocalizedMessage());
                        }
                    }
                });
            }
        });

        scrollComposite.setMinSize(group.getSize());
        scrollComposite.setAlwaysShowScrollBars(true);
        scrollComposite.setExpandHorizontal(true);
        scrollComposite.setExpandVertical(true);
        scrollComposite.setContent(group);

        evaluationText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL) ;
        evaluationText.setLayoutData(gd);

    }


    public void update(Map<String, Serializable> newVariableMap){

        for(org.eclipse.swt.widgets.Control c : group.getChildren()){
            if(!(c instanceof Button)){
                c.dispose();
            }
        }
        widgetMap.clear();
        Iterator<Entry<String, Serializable>> it = newVariableMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Serializable> var = it.next();

            final Label varLabel = new Label(group, SWT.NONE);
            varLabel.setText(var.getKey());

            if(var.getValue().equals(LIST)){
                Combo varValueCombo = new Combo(group, SWT.READ_ONLY);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                varValueCombo.setLayoutData(gd);
                ScriptVariable node = TestGroovyScriptUtil.getProcessVariable(var.getKey(),fieldNodes);

                List<String> values = org.bonitasoft.studio.groovy.GroovyUtil.getTypeValues(node.getType());
                for(String f : values){
                    varValueCombo.add(f);
                }

                widgetMap.put(varLabel, varValueCombo);
            }else if(var.getValue().equals(ATTACHMENT)){
                final Composite c = new Composite(group, SWT.NONE);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                c.setLayoutData(gd);
                c.setLayout(new GridLayout(2, false));

                final Text varValueText = new Text(c, SWT.BORDER);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                varValueText.setLayoutData(gd);

                Button browseButton = new Button(c, SWT.FLAT);
                browseButton.setText(Messages.browseButtonLabel);
                browseButton.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        FileDialog fd = new FileDialog(c.getShell(), SWT.OPEN);
                        String res = fd.open();

                        if(res != null){
                            varValueText.setText(res);
                        }
                    }

                });


                widgetMap.put(varLabel, varValueText);

//            }else if(var.getKey().equals(GroovyUtil.USER_LOCALE)){
//                Combo varValueCombo = new Combo(group, SWT.READ_ONLY);
//                gd = new GridData(GridData.FILL_HORIZONTAL);
//                varValueCombo.setLayoutData(gd);
//                for(Locale l : 	Locale.getAvailableLocales()){
//                    varValueCombo.add(l.getLanguage());
//                }
//
//                widgetMap.put(varLabel, varValueCombo);
            }else{

                Text varValueText = new Text(group, SWT.BORDER);
                gd = new GridData(GridData.FILL_HORIZONTAL);
                varValueText.setLayoutData(gd);
                widgetMap.put(varLabel, varValueText);
            }
        }

        scrollComposite.setAlwaysShowScrollBars(true);
        scrollComposite.setExpandHorizontal(true);
        scrollComposite.setExpandVertical(true);
        scrollComposite.setContent(group);
        scrollComposite.setMinSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));


    }

    /**
     * @return the widgetMap
     */
    public Map<Label, Control> getWidgetMap() {
        return widgetMap;
    }

}
