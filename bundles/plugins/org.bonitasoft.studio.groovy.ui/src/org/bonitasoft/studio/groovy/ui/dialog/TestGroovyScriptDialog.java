/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.dialog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class TestGroovyScriptDialog extends Dialog {

	private final List<ScriptVariable> nodes;
	private final GroovyCompilationUnit unit;
	private final Map<Label,Control> widgetMap;
	private final String returnType;
	private final Map<String, Serializable> variables;


	public TestGroovyScriptDialog(Shell parentShell,List<ScriptVariable> nodes,GroovyCompilationUnit unit,String returnType,Map<String, Serializable> variables) {
		super(parentShell);
		this.nodes = nodes ;
		this.unit = unit ;
		this.returnType = returnType;
		widgetMap = new HashMap<Label, Control>();
		this.variables = variables;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		//  super.createButtonsForButtonBar(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().hint(400, SWT.DEFAULT).create());
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(15, 15, 10, 0).spacing(0,15).create());
		final Label descriptionLabel=new Label(mainComposite, SWT.WRAP);
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		descriptionLabel.setText(Messages.testGroovyScriptDialogDescription);
		final Map<String,Serializable> procVariables = getProcessVariables(variables);
		final Map<String,Serializable> unknownVariables = getUnknownVariables(variables);

		if(!procVariables.isEmpty()){
			createVariableGroup(mainComposite,Messages.processVariableLabel,procVariables);
		}

		if(!unknownVariables.isEmpty()){
			createVariableGroup(mainComposite,Messages.unknownVariableLabel,unknownVariables);
		}
		
		final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false,false).align(SWT.END, SWT.FILL).create());
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		
		final Button closeButton = new Button(buttonComposite, SWT.PUSH) ;
		closeButton.setLayoutData(GridDataFactory.fillDefaults().hint(100, SWT.DEFAULT).create());
		closeButton.setText(IDialogConstants.CLOSE_LABEL);
		closeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		
		final Button testButton = new Button(buttonComposite, SWT.PUSH) ;
		testButton.setLayoutData(GridDataFactory.fillDefaults().hint(100, SWT.DEFAULT).create());
		testButton.setText(Messages.testButtonLabel) ;
		testButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ManageConnectorJarDialog mcjd = new ManageConnectorJarDialog(Display.getDefault().getActiveShell());
					int retCode =mcjd.open();
					if(retCode == Window.OK){
						TestGroovyScriptUtil.evaluateExpression(unit.getSource(),returnType, TestGroovyScriptUtil.getVariableValues(unit, nodes, widgetMap), mcjd.getSelectedJars());
					}
				} catch (JavaModelException e1) {
					BonitaStudioLog.error(e1);
				}
			}
		}) ;

		DataBindingContext dbc= new DataBindingContext();
		for(Entry<Label, Control> entry  : widgetMap.entrySet()){
			final UpdateValueStrategy strategy = new UpdateValueStrategy();
			//strategy.setAfterGetValidator(new EmptyInputValidator(entry.getKey().getText()));
			strategy.setConverter(new Converter(String.class,Boolean.class){

				@Override
				public Object convert(Object fromObject) {
					if(fromObject == null || fromObject.toString().isEmpty()){
						return false;
					}
					return allWidgetFilled();
				}

			});
			dbc.bindValue( SWTObservables.observeEnabled(testButton),SWTObservables.observeText(entry.getValue(),SWT.Modify),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), strategy);
		}
		return mainComposite ;
	}

	protected boolean allWidgetFilled() {
		for(Control c : widgetMap.values()){
			if(c instanceof Text){
				if(((Text) c).getText().isEmpty()){
					return false;
				}
			}else if(c instanceof Combo){
				if(((Combo) c).getText().isEmpty()){
					return false;
				}
			}
		}
		return true;
	}

	private void createVariableGroup(Composite parent,String groupTitle, Map<String, Serializable> groupVariables) {
		final Group group = new Group(parent, SWT.NONE) ;
		group.setText(groupTitle) ;
		group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
		group.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

		Iterator<Entry<String, Serializable>> it = groupVariables.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Serializable> var = it.next();

			final Label varLabel = new Label(group, SWT.NONE);
			varLabel.setText(var.getKey());
			if(var.getValue().equals(TestGroovyScriptUtil.LIST)){
				Combo varValueCombo = new Combo(group, SWT.READ_ONLY);
				varValueCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
				ScriptVariable node = TestGroovyScriptUtil.getProcessVariable(var.getKey(),nodes);
				List<String> values = org.bonitasoft.studio.groovy.GroovyUtil.getTypeValues(node.getType());
				for(String f : values){
					varValueCombo.add(f);
				}
				widgetMap.put(varLabel, varValueCombo);
				//            }else if(var.getKey().equals(GroovyUtil.USER_LOCALE)){
				//                Combo varValueCombo = new Combo(group, SWT.READ_ONLY);
				//                varValueCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
				//                for(Locale l :  Locale.getAvailableLocales()){
				//                    varValueCombo.add(l.getLanguage());
				//                }
				//                widgetMap.put(varLabel, varValueCombo);
			}else{
				Text varValueText = new Text(group, SWT.BORDER);
				varValueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
				ScriptVariable v = getScriptVariable(var.getKey());
				if(v != null && v.getDefaultValue() != null){
					varValueText.setText(v.getDefaultValue());
				}
				widgetMap.put(varLabel, varValueText);
			}
		}

	}

	private ScriptVariable getScriptVariable(String name) {
		for(ScriptVariable sv : nodes){
			if(sv.getName().equals(name)){
				return sv;
			}
		}
		return null;
	}

	private Map<String, Serializable> getProcessVariables(Map<String, Serializable> initialMap) {
		final Map<String, Serializable> result = new HashMap<String, Serializable>();
		for(String varName : initialMap.keySet()){
			if(processContains(varName)){
				result.put(varName,initialMap.get(varName));
			}
		}
		return result;
	}

	private Map<String, Serializable> getUnknownVariables(Map<String, Serializable> initialMap) {
		final Map<String, Serializable> result = new HashMap<String, Serializable>();
		for(String varName : initialMap.keySet()){
			if(!processContains(varName)){
				result.put(varName,initialMap.get(varName));
			}
		}
		return result;
	}

	private boolean processContains(String varName) {
		for(ScriptVariable v : nodes){
			if(v.getName().equals(varName)){
				return true;
			}
		}
		return false;
	}


}
