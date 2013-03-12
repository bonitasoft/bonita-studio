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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * @author Baptiste Mesta
 * 
 */
public class AvailableValueContribution extends InitialValueContribution {

    private final Map<String,Data> datas = new HashMap<String, Data>();

    @Override
    public void createControl(final Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,ExtensibleGridPropertySection extensibleGridPropertySection) {

        List<Data> dataList = ModelHelper.getAccessibleData(widget,ProcessPackage.eINSTANCE.getEnumType());
        for (Data data : dataList) {
            datas.put(data.getName(),data);
        }
        super.createControl(composite, widgetFactory, extensibleGridPropertySection);
    }

    @Override
    protected void doCreateControl(TabbedPropertySheetWidgetFactory widgetFactory) {
        /*Create combo for availavle value*/
        composite.setLayout(new GridLayout(3, false));
        expressionViewer = new ExpressionViewer(composite,SWT.BORDER,widgetFactory,editingDomain, FormPackage.Literals.WIDGET__INPUT_EXPRESSION, true) ;
       
        expressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{
    			ExpressionConstants.CONSTANT_TYPE,
    			ExpressionConstants.PARAMETER_TYPE,
    			ExpressionConstants.VARIABLE_TYPE,
    			ExpressionConstants.SCRIPT_TYPE
        }));
        expressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        if(widget instanceof SuggestBox){
            expressionViewer.setMessage(Messages.data_tooltip_list+" ("+Messages.mapUnsupported+")",IStatus.INFO);
        }else{
            expressionViewer.setMessage(Messages.data_tooltip_list,IStatus.INFO);
        }

        Button generateButton = widgetFactory.createButton(composite,"Generate expression from a list of option...", SWT.PUSH);
        generateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                generateScript();
            }
        });

        /*Create checkbox to allow HTML*/
        createAllowHtmlButton(composite, widgetFactory);
    }

    protected void generateScript() {
        ElementListSelectionDialog d = new ElementListSelectionDialog(Display.getDefault().getActiveShell(),new LabelProvider(){
            @Override
            public String getText(Object element) {
                String name =  ((EnumType)element).getName();
                String doc =  ((EnumType)element).getDocumentation();
                String label = name ;
                if(doc != null && !doc.isEmpty()){
                    label = label + " -- " + doc ;
                }
                return label;
            }
        });
        d.setTitle(Messages.selectListOfOptions);
        d.setMessage(Messages.selectListOfOptions);
        MainProcess prc = ModelHelper.getMainProcess(widget);
        List<EnumType> selectedElements = ModelHelper.getAllUserDatatype(prc);
        d.setAllowDuplicates(false);
        d.setEmptyListMessage(Messages.noListOfOptionsAvailable);
        d.setEmptySelectionMessage(Messages.noListOfOptionSelected);
        d.setElements(selectedElements.toArray());
        if( d.open() == Dialog.OK){
            final EnumType type = (EnumType) d.getResult()[0];
            final Expression generatedExp = ExpressionHelper.createExpressionFromEnumType(type);
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, widget, FormPackage.Literals.WIDGET__INPUT_EXPRESSION, generatedExp));
            expressionViewer.setSelection(new StructuredSelection(generatedExp));
        }
    }

    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof MultipleValuatedFormField;
    }


    @Override
    public String getLabel() {
        return Messages.Action_AvailableValues;
    }


}
