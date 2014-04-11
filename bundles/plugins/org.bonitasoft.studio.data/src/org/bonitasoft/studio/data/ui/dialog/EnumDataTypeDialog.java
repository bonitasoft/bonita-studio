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

package org.bonitasoft.studio.data.ui.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.dialog.editingsupport.DataTypeDescriptionEditingSupport;
import org.bonitasoft.studio.data.ui.dialog.editingsupport.DataTypeNameEditingSupport;
import org.bonitasoft.studio.data.ui.dialog.editingsupport.LiteralEditingSupport;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class EnumDataTypeDialog extends Dialog implements ISelectionChangedListener {

    private final Map<EnumType,EnumType> enumTypes;
    private final MainProcess mainProc;
    private TableViewer optionsList;
    private TableViewer typeList;
    private LiteralEditingSupport literalEditingSupport;
    private Button removeOptionButton;
    private Button downButton;
    private Button upButton;
    private Button addOptionButton;
    private Button removeButton;
    private EnumType selectedType;


    public EnumDataTypeDialog(Shell parentShell,MainProcess mainProc) {
        super(parentShell);
        this.mainProc = mainProc ;
        enumTypes = new HashMap<EnumType,EnumType>() ;
        for(EnumType enumType : ModelHelper.getAllUserDatatype(mainProc)){
            enumTypes.put(EcoreUtil.copy(enumType),enumType) ;
        }
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.listOfOptionsTitle) ;
    }

    @Override
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(newShellStyle  | SWT.SHEET);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(SWT.DEFAULT, 250).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).spacing(20, 5).equalWidth(true).create());

        createEnumTypeTable(composite) ;
        createEnumOptionsTable(composite) ;
        updateButtons() ;

        return composite;
    }


    protected void createEnumOptionsTable(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label choiceLabel = new Label(composite, SWT.NONE);
        choiceLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        choiceLabel.setText(Messages.choicesExplanation);

        optionsList = new TableViewer(composite,SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION) ;
        optionsList.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        TableLayout layout = new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(1)) ;
        optionsList.getTable().setLayout(layout) ;
        optionsList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                updateButtons() ;
            }
        }) ;
        optionsList.getTable().setHeaderVisible(true) ;
        optionsList.setContentProvider(new ArrayContentProvider()) ;
        optionsList.setSorter(null) ;

        TableViewerColumn columnNameViewer = new TableViewerColumn(optionsList,SWT.NONE) ;
        columnNameViewer.setLabelProvider(new ColumnLabelProvider()) ;
        literalEditingSupport = new LiteralEditingSupport(optionsList) ;
        columnNameViewer.setEditingSupport(literalEditingSupport) ;
        columnNameViewer.getColumn().setText(Messages.options) ;


        final Composite buttonComposite = new Composite(composite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false,false).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0,3).create()) ;

        addOptionButton = new Button(buttonComposite, SWT.FLAT) ;
        addOptionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        addOptionButton.setText(Messages.Add) ;
        addOptionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                getShell().setFocus();
                EnumType type = (EnumType) ((IStructuredSelection) typeList.getSelection()).getFirstElement() ;
                String newOption = generateLiteral(type) ;
                type.getLiterals().add(newOption) ;
                optionsList.refresh();
                optionsList.editElement(newOption, 0) ;
            }
        }) ;

        upButton = new Button(buttonComposite, SWT.FLAT) ;
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        upButton.setText(Messages.up) ;
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int sel = optionsList.getTable().getSelectionIndex();
                getShell().setFocus();
                optionsList.getTable().setFocus();
                optionsList.getTable().select(sel);
                EnumType enumType = (EnumType)((IStructuredSelection) typeList.getSelection()).getFirstElement();
                String selected = (String) ((StructuredSelection) optionsList.getSelection()).getFirstElement();
                if (selected != null) {
                    int i = enumType.getLiterals().indexOf(selected);
                    if (i > 0) {
                        ((EDataTypeUniqueEList<String>) enumType.getLiterals()).move(i - 1, selected);
                        optionsList.refresh();
                        optionsList.setSelection(new StructuredSelection(selected));
                    }
                }
            }
        }) ;

        downButton = new Button(buttonComposite, SWT.FLAT) ;
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        downButton.setText(Messages.down) ;
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int sel = optionsList.getTable().getSelectionIndex();
                getShell().setFocus();
                optionsList.getTable().setFocus();
                optionsList.getTable().select(sel);
                EnumType enumType = (EnumType)((IStructuredSelection) typeList.getSelection()).getFirstElement();
                String selected = (String) ((StructuredSelection) optionsList.getSelection()).getFirstElement();
                if (selected != null) {
                    int i = enumType.getLiterals().indexOf(selected);
                    if (i < enumType.getLiterals().size() - 1) {
                        ((EDataTypeUniqueEList<String>) enumType.getLiterals()).move(i + 1, selected);
                        optionsList.refresh();
                        optionsList.setSelection(new StructuredSelection(selected));
                    }
                }
            }
        }) ;


        removeOptionButton = new Button(buttonComposite, SWT.FLAT) ;
        removeOptionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        removeOptionButton.setText(Messages.removeLiteralLabel) ;
        removeOptionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EnumType type = (EnumType) ((IStructuredSelection) typeList.getSelection()).getFirstElement() ;
                String literal = (String) ((IStructuredSelection) optionsList.getSelection()).getFirstElement() ;
                type.getLiterals().remove(literal) ;
                optionsList.refresh();
            }
        }) ;

    }


    private String generateLiteral(EnumType type) {
        int cpt = 1 ;
        for(String e : type.getLiterals()){
            if(e.startsWith(Messages.defaultLiteral)){
                cpt++;
                while(e.equals(Messages.defaultLiteral + cpt)){
                    cpt++;
                }
            }
        }
        return Messages.defaultLiteral + cpt ;
    }

    protected void createEnumTypeTable(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label typeLabel = new Label(composite, SWT.NONE);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        typeLabel.setText(Messages.listOfOptionsExplanation);

        typeList = new TableViewer(composite,SWT.BORDER | SWT.SINGLE) ;
        typeList.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(300, SWT.DEFAULT).create()) ;
        typeList.addSelectionChangedListener(this) ;
        TableLayout layout = new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(30)) ;
        layout.addColumnData(new ColumnWeightData(70)) ;
        typeList.getTable().setLayout(layout) ;
        typeList.setContentProvider(new ArrayContentProvider()) ;
        typeList.setSorter(new ViewerSorter(){
            @Override
            public int compare(Viewer viewer, Object e1, Object e2) {
                EnumType t1 = (EnumType) e1 ;
                EnumType t2 = (EnumType) e2 ;
                return t1.getName().toLowerCase().compareTo(t2.getName().toLowerCase());
            }
        }) ;

        TableViewerColumn columnNameViewer = new TableViewerColumn(typeList,SWT.NONE) ;
        columnNameViewer.setEditingSupport(new DataTypeNameEditingSupport(typeList, enumTypes.keySet())) ;
        columnNameViewer.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((DataType)element).getName() ;
            }
        }) ;
        columnNameViewer.getColumn().setText(Messages.name) ;

        TableViewerColumn columnDescriptionViewer = new TableViewerColumn(typeList,SWT.NONE) ;
        columnDescriptionViewer.setEditingSupport(new DataTypeDescriptionEditingSupport(typeList)) ;
        columnDescriptionViewer.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((DataType)element).getDocumentation() ;
            }
        }) ;
        columnDescriptionViewer.getColumn().setText(Messages.dataDescriptionLabel)  ;

        typeList.getTable().setHeaderVisible(true) ;

        typeList.setInput(enumTypes.keySet()) ;

        final Composite buttonComposite = new Composite(composite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false,false).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0,3).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        addButton.setText(Messages.Add) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EnumType newType = ProcessFactory.eINSTANCE.createEnumType() ;
                newType.setName(generateTypeName()) ;
                enumTypes.put(newType,null) ;
                typeList.setInput(enumTypes.keySet()) ;
                typeList.editElement(newType, 0) ;
            }
        }) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        removeButton.setText(Messages.removeLiteralLabel) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection) typeList.getSelection() ;
                enumTypes.remove(selection.getFirstElement());
                typeList.setInput(enumTypes.keySet()) ;
                typeList.setSelection(new StructuredSelection()) ;
            }
        }) ;
    }

    @Override
    protected void okPressed() {
        getShell().setFocus();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(mainProc) ;

        for(EnumType orignialType : ModelHelper.getAllUserDatatype(mainProc)){
            if(!enumTypes.values().contains(orignialType)){
                editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, mainProc, ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES, orignialType)) ;
            }
        }

        for(Entry<EnumType,EnumType> type : enumTypes.entrySet()){
            EnumType workingCopy = type.getKey() ;
            EnumType existingCopy = type.getValue() ;

            if(existingCopy == null){//New Type
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, mainProc, ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES, workingCopy)) ;
            }else{ //Update Type
                CompoundCommand cc = new CompoundCommand() ;
                cc.append(SetCommand.create(editingDomain, existingCopy, ProcessPackage.Literals.ELEMENT__NAME, workingCopy.getName())) ;
                cc.append(SetCommand.create(editingDomain, existingCopy, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, workingCopy.getDocumentation())) ;
                cc.append(SetCommand.create(editingDomain, existingCopy, ProcessPackage.Literals.ENUM_TYPE__LITERALS, workingCopy.getLiterals())) ;
                editingDomain.getCommandStack().execute(cc) ;
            }
        }
        EnumType selected = (EnumType) ((IStructuredSelection) typeList.getSelection()).getFirstElement();
        if(selected != null){
            for(DataType t : mainProc.getDatatypes()){
                if(t instanceof EnumType && t.getName().equals(selected.getName())){
                    selectedType = (EnumType) t;
                }
            }
        }
        super.okPressed();
    }

    private String generateTypeName() {
        int cpt = 1 ;
        for(EnumType e : enumTypes.keySet()){
            if(e.getName().startsWith(Messages.defaultTypeName)){
                cpt++;
            }
        }
        return Messages.defaultTypeName + cpt ;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        if(!event.getSelection().isEmpty()){
            EnumType enumType = (EnumType)((IStructuredSelection) event.getSelection()).getFirstElement();
            optionsList.setInput(enumType.getLiterals()) ;
            literalEditingSupport.setEnumType(enumType) ;
        }else{
            optionsList.setInput(new ArrayList<String>()) ;
        }
        updateButtons() ;
    }

    private void updateButtons() {
        if(removeButton != null && !removeButton.isDisposed()){
            removeButton.setEnabled(!typeList.getSelection().isEmpty()) ;
        }
        if(addOptionButton != null && !addOptionButton.isDisposed()){
            addOptionButton.setEnabled(!typeList.getSelection().isEmpty()) ;
        }
        if(removeOptionButton != null && !removeOptionButton.isDisposed()){
            removeOptionButton.setEnabled(!optionsList.getSelection().isEmpty()) ;
        }
        if(downButton != null && !downButton.isDisposed()){
            downButton.setEnabled(!optionsList.getSelection().isEmpty()) ;
        }
        if(upButton != null && !upButton.isDisposed()){
            upButton.setEnabled(!optionsList.getSelection().isEmpty()) ;
        }
    }

    public EnumType getSelectedEnum() {
        return selectedType;
    }

}
