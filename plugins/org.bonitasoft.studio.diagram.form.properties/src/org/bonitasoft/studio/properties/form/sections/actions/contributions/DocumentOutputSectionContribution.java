///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
//package org.bonitasoft.studio.properties.form.sections.actions.contributions;
//
//import org.bonitasoft.studio.common.ExpressionConstants;
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
//import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
//import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
//import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
//import org.bonitasoft.studio.form.properties.i18n.Messages;
//import org.bonitasoft.studio.model.expression.Expression;
//import org.bonitasoft.studio.model.expression.ExpressionFactory;
//import org.bonitasoft.studio.model.expression.ExpressionPackage;
//import org.bonitasoft.studio.model.expression.Operation;
//import org.bonitasoft.studio.model.expression.Operator;
//import org.bonitasoft.studio.model.form.Duplicable;
//import org.bonitasoft.studio.model.form.FileWidget;
//import org.bonitasoft.studio.model.form.Form;
//import org.bonitasoft.studio.model.form.FormPackage;
//import org.bonitasoft.studio.model.form.ViewForm;
//import org.bonitasoft.studio.model.form.Widget;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Document;
//import org.bonitasoft.studio.model.process.Pool;
//import org.bonitasoft.studio.model.process.ProcessPackage;
//import org.bonitasoft.studio.pics.Pics;
//import org.bonitasoft.studio.pics.PicsConstants;
//import org.eclipse.core.databinding.UpdateValueStrategy;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.core.databinding.observable.value.IValueChangeListener;
//import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.emf.databinding.EMFDataBindingContext;
//import org.eclipse.emf.databinding.EMFObservables;
//import org.eclipse.emf.databinding.edit.EMFEditObservables;
//import org.eclipse.emf.databinding.edit.EMFEditProperties;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.edit.command.SetCommand;
//import org.eclipse.emf.transaction.TransactionalEditingDomain;
//import org.eclipse.jface.databinding.swt.SWTObservables;
//import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
//import org.eclipse.jface.databinding.viewers.ViewerProperties;
//import org.eclipse.jface.databinding.viewers.ViewersObservables;
//import org.eclipse.jface.fieldassist.ControlDecoration;
//import org.eclipse.jface.layout.GridDataFactory;
//import org.eclipse.jface.layout.GridLayoutFactory;
//import org.eclipse.jface.viewers.ComboViewer;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.ui.forms.widgets.Section;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
//
///**
// * @author Baptiste Mesta
// * @author Aurelien Pupier - allow to create a data from here
// */
//public class DocumentOutputSectionContribution implements IExtensibleGridPropertySectionContribution {
//
//    protected FileWidget element;
//    protected TransactionalEditingDomain editingDomain;
//    protected EMFDataBindingContext dataBinding;
//    protected EMFDataBindingContext checkBoxdataBinding;
//    private ExpressionViewer newDocumentExpressionViewer;
//    private ComboViewer documentViewer;
//    private final String noDocumentComboItem = Messages.noDocumentOutput;
//    private Button updateDocumentCheckbox;
//    private Section section;
//    private TabbedPropertySheetWidgetFactory widgetFactory;
//    private ExpressionViewer documentListExpressionViewer;
//    private ControlDecoration checkboxDeco;
//    private Button newDocumentRadio;
//    private boolean multiple;
//
//
//    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
//        this.widgetFactory = widgetFactory;
//
//        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
//
//        final Composite radioComposite = widgetFactory.createComposite(composite);
//        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
//
//
//        updateDocumentCheckbox = widgetFactory.createButton(radioComposite,Messages.updateExistingDocument ,SWT.RADIO);
//        checkboxDeco = new ControlDecoration(updateDocumentCheckbox, SWT.LEFT);
//        checkboxDeco.setDescriptionText(getHint());
//        checkboxDeco.setImage(Pics.getImage(PicsConstants.hint));
//        updateDocumentCheckbox.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                updateOutputType();
//            }
//        });
//
//        newDocumentRadio = widgetFactory.createButton(radioComposite,Messages.createAnewDocument ,SWT.RADIO);
//        newDocumentRadio.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                updateOutputType();
//            }
//        });
//
//        section = widgetFactory.createSection(composite, Section.NO_TITLE | Section.CLIENT_INDENT) ;
//        section.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
//        section.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
//        section.setExpanded(true) ;
//
//        bindCheckBox();
//
//        if(element.isUpdateDocument()){
//            updateDocumentCheckbox.setSelection(true);
//            newDocumentRadio.setSelection(false);
//            updateDocumentCheckbox.notifyListeners(SWT.Selection,new Event());
//        }else{
//            updateDocumentCheckbox.setSelection(false);
//            newDocumentRadio.setSelection(true);
//            updateDocumentCheckbox.notifyListeners(SWT.Selection,new Event());
//        }
//        updateDocumentCheckbox.setEnabled(!element.isDownloadOnly());
//        newDocumentRadio.setEnabled(!element.isDownloadOnly());
//    }
//
//    private String getHint() {
//        if(element.isDuplicate()){
//            return Messages.updateExistingForMultipleDocumentHint ;
//        }else{
//            return Messages.updateExistingDocumentHint ;
//        }
//    }
//
//    protected void bindCheckBox() {
//        if(checkBoxdataBinding != null){
//            checkBoxdataBinding.dispose();
//        }
//        checkBoxdataBinding = new EMFDataBindingContext();
//        checkBoxdataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(updateDocumentCheckbox),new UpdateValueStrategy(){
//            @Override
//            public Object convert(Object value) {
//                return !(Boolean)value;
//            }
//        },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//        checkBoxdataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(newDocumentRadio),new UpdateValueStrategy(){
//            @Override
//            public Object convert(Object value) {
//                return !(Boolean)value;
//            }
//        },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//        IObservableValue value = EMFObservables.observeValue(element, FormPackage.Literals.DUPLICABLE__DUPLICATE);
//        value.addValueChangeListener(new IValueChangeListener() {
//
//            public void handleValueChange(ValueChangeEvent event) {
//                if(updateDocumentCheckbox != null && !updateDocumentCheckbox.isDisposed()){
//                    updateOutputType();
//                }
//            }
//        });
//    }
//
//    protected Control createOperationViewer(Composite composite,TabbedPropertySheetWidgetFactory widgetFactory) {
//        final Composite client  = widgetFactory.createComposite(composite);
//        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
//
//        newDocumentExpressionViewer = new ExpressionViewer(client,SWT.BORDER,widgetFactory,getEditingDomain(),ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
//        newDocumentExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(500, SWT.DEFAULT).create()) ;
//        newDocumentExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
//        		ExpressionConstants.VARIABLE_TYPE,
//        		ExpressionConstants.PARAMETER_TYPE,
//        		ExpressionConstants.SCRIPT_TYPE}));
//        newDocumentExpressionViewer.setMessage(Messages.newDocumentNameHint, IStatus.INFO);
//        boolean enabled = !element.isDownloadOnly();
//        newDocumentExpressionViewer.getTextControl().setEnabled(enabled);
//        newDocumentExpressionViewer.getToolbar().setEnabled(enabled);
//
//        newDocumentExpressionViewer.setContext(element);
//        bindOperationViewer();
//        return client;
//    }
//
//    protected void bindOperationViewer() {
//        if(newDocumentExpressionViewer != null && !newDocumentExpressionViewer.getControl().isDisposed()){
//            if(dataBinding != null){
//                dataBinding.dispose();
//            }
//            dataBinding = new EMFDataBindingContext();
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(newDocumentExpressionViewer.getToolbar()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(newDocumentExpressionViewer.getEraseControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(newDocumentExpressionViewer.getTextControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(newDocumentExpressionViewer.getButtonControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//            Operation action = element.getAction() ;
//            if(action == null){
//                action = ExpressionFactory.eINSTANCE.createOperation() ;
//                Operator op = ExpressionFactory.eINSTANCE.createOperator() ;
//                op.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR) ;
//                op.setExpression("=") ;
//                action.setOperator(op) ;
//
//                Expression variableExp = ExpressionFactory.eINSTANCE.createExpression() ;
//                variableExp.setReturnType(String.class.getName());
//                variableExp.setReturnTypeFixed(true);
//                Expression actionExp = ExpressionFactory.eINSTANCE.createExpression() ;
//                action.setLeftOperand(variableExp) ;
//                action.setRightOperand(actionExp) ;
//                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.WIDGET__ACTION, action));
//            }
//            newDocumentExpressionViewer.setInput(action);
//            dataBinding.bindValue(ViewersObservables.observeSingleSelection(newDocumentExpressionViewer),EMFEditObservables.observeValue(getEditingDomain(), action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND));
//        }
//    }
//
//
//    protected Control createDocumentViewer(Composite composite,TabbedPropertySheetWidgetFactory widgetFactory) {
//        final Composite client  = widgetFactory.createComposite(composite);
//        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
//        documentViewer = new ComboViewer(client, SWT.BORDER | SWT.READ_ONLY);
//        documentViewer.setContentProvider(new ObservableListContentProvider(){
//            @Override
//            public Object[] getElements(Object inputElement) {
//                final Object[] elements = super.getElements(inputElement);
//                final Object[] copyOf = new Object[elements.length +1];
//                for(int i = 0; i< elements.length; i++){
//                    copyOf[i] = elements[i];
//                }
//                copyOf[elements.length] = noDocumentComboItem;
//                return copyOf;
//            }
//        });
//        documentViewer.setLabelProvider(new LabelProvider(){
//            @Override
//            public String getText(Object element) {
//                if(element instanceof Document){
//                    return ((Document) element).getName();
//                } else {
//                    return super.getText(element);
//                }
//            }
//        });
//        GridData gData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
//        gData.widthHint = 200;
//        documentViewer.getControl().setLayoutData(gData);
//        documentViewer.getControl().setEnabled(!element.isDownloadOnly());
//        bindDocumentViewer();
//        return client;
//    }
//
//
//
//    protected void updateOutputType() {
//        if(updateDocumentCheckbox.getSelection() && (section.getClient() == null || multiple != ((Duplicable)element).isDuplicate() || !element.isUpdateDocument())){
//            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__UPDATE_DOCUMENT, true));
//            if(!((Duplicable)element).isDuplicate()){
//                multiple = false ;
//                if(section.getClient() != null){
//                    section.getClient().dispose();
//                }
//                section.setClient(createDocumentViewer(section,widgetFactory));
//            }else{
//                multiple = true ;
//                if(section.getClient() != null){
//                    section.getClient().dispose();
//                }
//                section.setClient(createMultipleDocumentExpressionViewer(section,widgetFactory));
//            }
//        }else if(newDocumentRadio.getSelection() && (section.getClient() == null || multiple != ((Duplicable)element).isDuplicate() || element.isUpdateDocument())){
//            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__UPDATE_DOCUMENT, false));
//            if(!((Duplicable)element).isDuplicate()){
//                multiple = false ;
//                if(section.getClient() != null){
//                    section.getClient().dispose();
//                }
//                section.setClient(createOperationViewer(section,widgetFactory));
//            }else{
//                multiple = true ;
//                if(section.getClient() != null){
//                    section.getClient().dispose();
//                }
//                section.setClient(createOperationViewer(section,widgetFactory));
//            }
//        }
//        section.setExpanded(true);
//        checkboxDeco.setDescriptionText(getHint());
//    }
//
//
//
//    protected Control createMultipleDocumentExpressionViewer(Section composite, TabbedPropertySheetWidgetFactory widgetFactory) {
//        final Composite client  = widgetFactory.createComposite(composite);
//        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
//
//        AvailableExpressionTypeFilter expressionFilter =  new AvailableExpressionTypeFilter(new String[]{
//    			ExpressionConstants.CONSTANT_TYPE,
//    			ExpressionConstants.VARIABLE_TYPE,
//    			ExpressionConstants.FORM_FIELD_TYPE,
//    			ExpressionConstants.SCRIPT_TYPE
//        }) ;
//
//        documentListExpressionViewer = new ExpressionViewer(client, SWT.BORDER, widgetFactory, editingDomain, FormPackage.Literals.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION);
//        documentListExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//        documentListExpressionViewer.addFilter(expressionFilter);
//        documentListExpressionViewer.setMessage(Messages.data_tooltip_multiple_document,IStatus.INFO);
//
//        if(element.getOutputDocumentListExpression() == null){
//            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION, ExpressionFactory.eINSTANCE.createExpression()));
//        }
//        documentListExpressionViewer.setInput(element);
//
//        boolean enabled = !element.isDownloadOnly();
//        documentListExpressionViewer.getButtonControl().setEnabled(enabled);
//        documentListExpressionViewer.getTextControl().setEnabled(enabled);
//
//        bindDocumentListViewer();
//        return client;
//    }
//
//    protected void bindDocumentListViewer() {
//        if(dataBinding != null){
//            dataBinding.dispose();
//        }
//        dataBinding = new EMFDataBindingContext();
//        if(documentListExpressionViewer != null){
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(documentListExpressionViewer.getTextControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(documentListExpressionViewer.getButtonControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//
//            dataBinding.bindValue(
//                    ViewerProperties.singleSelection().observe(documentListExpressionViewer),
//                    EMFEditProperties.value(editingDomain, FormPackage.Literals.FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION).observe(element));
//        }
//    }
//
//    protected TransactionalEditingDomain getEditingDomain() {
//        return editingDomain;
//    }
//
//    protected void bindDocumentViewer() {
//        if(dataBinding != null){
//            dataBinding.dispose();
//        }
//        dataBinding = new EMFDataBindingContext();
//        final AbstractProcess parentProcess = ModelHelper.getParentProcess(element);
//        if(documentViewer != null && parentProcess != null){
//            documentViewer.setInput(
//                    EMFEditProperties.list(
//                            editingDomain,
//                            ProcessPackage.Literals.POOL__DOCUMENTS)
//                            .observe(parentProcess));
//
//            dataBinding.bindValue(EMFObservables.observeValue(element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), SWTObservables.observeEnabled(documentViewer.getControl()),new UpdateValueStrategy(){
//                @Override
//                public Object convert(Object value) {
//                    return !(Boolean)value;
//                }
//            },new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
//
//            dataBinding.bindValue(
//                    ViewerProperties.singleSelection().observe(documentViewer),
//                    EMFEditProperties.value(editingDomain, FormPackage.Literals.FILE_WIDGET__OUTPUT_DOCUMENT_NAME).observe(element),
//                    new UpdateValueStrategy(){
//                        @Override
//                        public Object convert(Object value) {
//                            if(noDocumentComboItem.equals(value)){
//                                return null;
//                            } else if(value instanceof Document){
//                                return ((Document) value).getName();
//                            } else {
//                                return super.convert(value);
//                            }
//                        }
//                    },
//                    new UpdateValueStrategy(){
//                        @Override
//                        public Object convert(Object value) {
//                            if(value == null){
//                                return noDocumentComboItem;
//                            }else{
//                                if(value.equals(noDocumentComboItem)){
//                                    return noDocumentComboItem;
//                                }else{
//                                    for(Document d : ((Pool)parentProcess).getDocuments()){
//                                        if(d.getName().equals(value.toString())){
//                                            return d;
//                                        }
//                                    }
//                                }
//                                return super.convert(value);
//                            }
//                        }
//                    });
//        }
//    }
//
//
//    public void dispose() {
//        if(dataBinding!= null){
//            dataBinding.dispose();
//        }
//        if(newDocumentExpressionViewer != null){
//            newDocumentExpressionViewer.getControl().dispose() ;
//        }
//    }
//
//    public String getLabel() {
//        return Messages.outputDocumentStorage;
//    }
//
//    public boolean isRelevantFor(EObject eObject) {
//        if(eObject instanceof FileWidget && ! ModelHelper.isInDuplicatedGrp(eObject)){
//            Form form = ModelHelper.getForm((Widget) eObject);
//            return !(form instanceof ViewForm);
//        }else{
//            return false;
//        }
//
//    }
//
//    public void refresh() {
//
//    }
//
//    public void setEObject(EObject object) {
//        element = (FileWidget) object;
//    }
//
//    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
//        this.editingDomain = editingDomain;
//    }
//
//    public void setSelection(ISelection selection) {
//
//    }
//
//}
