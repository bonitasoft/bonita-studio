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
package org.bonitasoft.studio.expression.editor.operation;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IOperatorEditor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.XMLData;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Section;


/**
 * @author Romain Bioteau
 *
 */
public class OperatorSelectionDialog extends Dialog implements ISelectionChangedListener {

    private static final String OPERATOR_EDITOR_CONTRIBUTION_ID = "org.bonitasoft.studio.expression.operatorEditor";
    private final Operation operation;
    private final Operator operator;
    private final EMFDataBindingContext context;
    private Section section;
    private List<IOperatorEditor> operatorEditors;
    private final static  List<String> operatorTypeList = new ArrayList<String>() ;
    static{
        operatorTypeList.add(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
        operatorTypeList.add(ExpressionConstants.JAVA_METHOD_OPERATOR) ;
        operatorTypeList.add(ExpressionConstants.XPATH_UPDATE_OPERATOR) ;
    }

    protected OperatorSelectionDialog(Shell parentShell,Operation operation) {
        super(parentShell);
        this.operation = operation ;
        operator = EcoreUtil.copy(operation.getOperator()) ;
        context = new EMFDataBindingContext() ;
        initializeOperatorEditors() ;
    }

    private void initializeOperatorEditors() {
        if(operatorEditors == null){
            operatorEditors = new ArrayList<IOperatorEditor>() ;
            for(IConfigurationElement elem :  BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(OPERATOR_EDITOR_CONTRIBUTION_ID)){
                try{
                    IOperatorEditor editor = (IOperatorEditor) elem.createExecutableExtension("class") ;
                    operatorEditors.add(editor) ;
                }catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
    }

    @Override
    public boolean close() {
        boolean closed =  super.close();
        if(closed){
            if(context != null){
                context.dispose() ;
            }
        }
        return closed ;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, SWT.DEFAULT).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10,10).create()) ;
        final Label operatorType = new Label(mainComposite, SWT.NONE) ;
        operatorType.setText(Messages.operatorType) ;

        final ComboViewer operatorViewer = new ComboViewer(mainComposite,SWT.READ_ONLY | SWT.BORDER);
        operatorViewer.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        operatorViewer.setContentProvider(new ArrayContentProvider()) ;
        operatorViewer.setLabelProvider(new OperatorLabelProvider()) ;
        operatorViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                Expression exp = operation.getLeftOperand() ;
                if(exp != null && !exp.getReferencedElements().isEmpty() && ExpressionConstants.VARIABLE_TYPE.equals(exp.getType())){
                    EObject data = exp.getReferencedElements().get(0) ;
                    if(data instanceof JavaObjectData){
                        return element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR) ||  element.equals(ExpressionConstants.JAVA_METHOD_OPERATOR);
                    }else if(data instanceof XMLData){
                        return element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR) ||  element.equals(ExpressionConstants.XPATH_UPDATE_OPERATOR);
                    }else{
                        return element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
                    }
                }
                return element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
            }
        }) ;


        operatorViewer.setInput(operatorTypeList) ;

        section = new Section(mainComposite, Section.NO_TITLE) ;
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;

        Expression exp = operation.getLeftOperand() ;
        createOperatorEditorFor(section, operator.getType(), operator, exp) ;

        context.bindValue(ViewersObservables.observeSingleSelection(operatorViewer), EMFObservables.observeValue(operator, ExpressionPackage.Literals.OPERATOR__TYPE)) ;
        operatorViewer.addSelectionChangedListener(this) ;

        return mainComposite ;
    }


    protected void enableOKButton(boolean enabled) {
        final Button button = getButton(OK) ;
        if(button != null){
            button.setEnabled(enabled) ;
        }
    }


    public Operator getOperator(){
        return operator ;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        Expression exp = operation.getLeftOperand() ;
        String operatorType = (String) ((IStructuredSelection) event.getSelection()).getFirstElement() ;


        createOperatorEditorFor(section, operatorType, operator, exp) ;

        relayout() ;
        if(!operatorType.equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
        	enableOKButton(false) ;
        }else{
        	enableOKButton(true) ;
        }
    }

    private void relayout() {
        Shell shell = section.getShell();
        Point defaultSize = shell.getSize() ;
        Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true) ;
        shell.setSize(defaultSize.x, size.y) ;
        shell.layout(true, true) ;
    }


    protected void createOperatorEditorFor(Section parentSection,String operatorType,Operator operator,Expression sourceExpression){
        Composite client = null ;
        boolean expand = false;
        if(parentSection.getClient() != null){
            parentSection.getClient().dispose() ;
        }
        for(final IOperatorEditor editor : operatorEditors){
            if(editor.appliesTo(operatorType)){
                expand = true;
                client = editor.createOpeartorEditor(parentSection, operator, sourceExpression) ;
                editor.addSelectionChangedListener(new ISelectionChangedListener() {

                    @Override
                    public void selectionChanged(SelectionChangedEvent event) {
                        enableOKButton(editor.canFinish()) ;
                    }
                }) ;
            }
        }
        if(client == null){
            client = new Composite(parentSection, SWT.NONE) ;
        }
        parentSection.setClient(client) ;
        parentSection.setExpanded(expand) ;
    }

}
