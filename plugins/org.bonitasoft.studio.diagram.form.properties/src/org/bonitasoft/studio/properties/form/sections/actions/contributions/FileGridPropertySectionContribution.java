/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.sections.document.SelectDocumentInBonitaStudioRepository;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * Allow user to choose a File (fileData) for the file widget
 * 
 * @author Baptiste Mesta
 * 
 */
public class FileGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private ComboViewer inputDocumentViewer;
    private FileWidget element;
    private TransactionalEditingDomain editingDomain;
    private Button downloadOnly;
    private EMFDataBindingContext dataBindingContext;
    private Button imagePreview;
    private final String noDocumentComboItem = Messages.noDocument;
    private Section initialValueSection;
    private Button useDocumentButton;
    private Button useURLButton;
    private Button useResourceButton;
    private TabbedPropertySheetWidgetFactory widgetFactory;
    private ExpressionViewer inputURLExpressionViewer;
    private Text resourceText;
    private TableViewer resourceTableViewer;
    private ExpressionViewer inputDocumentExpressionViewer;
    private boolean multiple;

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory,
     * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    public void createControl(Composite mainComposite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {

        this.widgetFactory = widgetFactory ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        int col = 3 ;
        if(ModelHelper.isAnEntryPageFlowOnAPool(ModelHelper.getParentForm(element))){
            col = 2 ;
        }
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());

        downloadOnly = widgetFactory.createButton(mainComposite, Messages.downloadOnly, SWT.CHECK);
        imagePreview = widgetFactory.createButton(mainComposite, Messages.previewAttachment, SWT.CHECK);
        widgetFactory.createLabel(mainComposite, "");

        final Composite radioComposite = widgetFactory.createComposite(mainComposite) ;
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL,SWT.CENTER).grab(true, false).span(3, 1).create());
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(col).margins(0, 0).create()) ;

        FileWidgetInputType initialInputType = element.getInputType();
		if(!ModelHelper.isAnEntryPageFlowOnAPool(ModelHelper.getParentForm(element))){
            useDocumentButton = widgetFactory.createButton(radioComposite, Messages.useDocument, SWT.RADIO) ;
            useDocumentButton.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                    if(initialValueSection != null && !initialValueSection.isDisposed()){
                        if(useDocumentButton.getSelection() && (element.getInputType() != FileWidgetInputType.DOCUMENT || element.isDuplicate() != multiple || initialValueSection.getClient() == null)){
                            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INPUT_TYPE, FileWidgetInputType.DOCUMENT));
                            if(initialValueSection.getClient() != null){
                                initialValueSection.getClient().dispose() ;
                            }
                            if(element.isDuplicate()){
                                multiple = true ;
                                initialValueSection.setClient(createMultipleDocumentComposite(initialValueSection, FileGridPropertySectionContribution.this.widgetFactory)) ;
                            }else{
                                multiple = false ;
                                initialValueSection.setClient(createDocumentComposite(initialValueSection, FileGridPropertySectionContribution.this.widgetFactory)) ;
                            }

                            initialValueSection.setExpanded(true) ;
                            bindFields();
                        }
                    }
                }
            });
        }else{
        	initialInputType = FileWidgetInputType.URL;
        }

        useURLButton = widgetFactory.createButton(radioComposite, Messages.useUrl, SWT.RADIO) ;
        useResourceButton = widgetFactory.createButton(radioComposite, Messages.useResource, SWT.RADIO) ;



        useURLButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if(initialValueSection != null && !initialValueSection.isDisposed()){
                    if(useURLButton.getSelection() && (element.getInputType() != FileWidgetInputType.URL || element.isDuplicate() != multiple || initialValueSection.getClient() == null)){
                        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INPUT_TYPE, FileWidgetInputType.URL));
                        if(initialValueSection.getClient() != null){
                            initialValueSection.getClient().dispose() ;
                        }
                        if(element.isDuplicate()){
                            multiple = true ;
                            initialValueSection.setClient(createURLComposite(initialValueSection,FileGridPropertySectionContribution.this.widgetFactory)) ;
                        }else{
                            multiple = false ;
                            initialValueSection.setClient(createURLComposite(initialValueSection,FileGridPropertySectionContribution.this.widgetFactory)) ;
                        }

                        initialValueSection.setExpanded(true) ;
                        bindFields();
                    }
                }
            }
        });

        useResourceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if(initialValueSection != null && !initialValueSection.isDisposed()){
                    if(useResourceButton.getSelection() && (element.getInputType() != FileWidgetInputType.RESOURCE || element.isDuplicate() != multiple || initialValueSection.getClient() == null)){
                        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INPUT_TYPE, FileWidgetInputType.RESOURCE));
                        if(initialValueSection.getClient() != null){
                            initialValueSection.getClient().dispose() ;
                        }
                        if(element.isDuplicate()){
                            multiple = true ;
                            initialValueSection.setClient(createMultipleResourceComposite(initialValueSection, FileGridPropertySectionContribution.this.widgetFactory)) ;
                        }else{
                            multiple = false ;
                            initialValueSection.setClient(createResourceComposite(initialValueSection, FileGridPropertySectionContribution.this.widgetFactory)) ;
                        }

                        initialValueSection.setExpanded(true) ;
                        bindFields();
                    }
                }
            }
        });

        initialValueSection = widgetFactory.createSection(mainComposite, Section.NO_TITLE | Section.CLIENT_INDENT) ;
        initialValueSection.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        initialValueSection.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).span(3, 1).create()) ;
        initialValueSection.setExpanded(true) ;

        if(initialInputType == FileWidgetInputType.DOCUMENT){
            useDocumentButton.setSelection(true);
            useDocumentButton.notifyListeners(SWT.Selection,new Event());
        }else if(initialInputType == FileWidgetInputType.URL){
            useURLButton.setSelection(true);
            useURLButton.notifyListeners(SWT.Selection,new Event());
        }else if(initialInputType == FileWidgetInputType.RESOURCE){
            useResourceButton.setSelection(true);
            useResourceButton.notifyListeners(SWT.Selection,new Event());
        }


        bindFields();
    }

    protected Control createResourceComposite(Section section, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite client  = widgetFactory.createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        resourceText = widgetFactory.createText(client, "");
        resourceText.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        final ControlDecoration cd = new ControlDecoration(resourceText, SWT.LEFT);
        cd.setImage(Pics.getImage(PicsConstants.hint));
        cd.setDescriptionText(Messages.filewidget_resource_hint);

        final Button browseResourceButton = widgetFactory.createButton(client, Messages.Browse, SWT.PUSH);
        browseResourceButton.setLayoutData(GridDataFactory.fillDefaults().create());
        browseResourceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final SelectDocumentInBonitaStudioRepository selectDocumentInBonitaStudioRepository = new SelectDocumentInBonitaStudioRepository(Display.getDefault().getActiveShell());
                if(IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository.open()){
                    resourceText.setText(selectDocumentInBonitaStudioRepository.getSelectedDocument().getName());
                }
            }
        });
        return client;
    }

    protected Control createMultipleResourceComposite(Section section, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite client  = widgetFactory.createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        resourceTableViewer = new TableViewer(client, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
        resourceTableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT,60).create());
        resourceTableViewer.setContentProvider(new ArrayContentProvider());
        resourceTableViewer.setLabelProvider(new LabelProvider());
        final ControlDecoration cd = new ControlDecoration(resourceTableViewer.getControl(), SWT.TOP | SWT.LEFT);
        cd.setImage(Pics.getImage(PicsConstants.hint));
        cd.setDescriptionText(Messages.filewidget_resource_hint_multiple);

        final Composite buttonComposite = widgetFactory.createComposite(client);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        final Button addResourceButton = widgetFactory.createButton(buttonComposite, Messages.Add, SWT.PUSH);
        addResourceButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        addResourceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final SelectDocumentInBonitaStudioRepository selectDocumentInBonitaStudioRepository = new SelectDocumentInBonitaStudioRepository(Display.getDefault().getActiveShell());
                if(IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository.open()){
                    editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INTIAL_RESOURCE_LIST, selectDocumentInBonitaStudioRepository.getSelectedDocument().getName()));
                    resourceTableViewer.refresh();
                }
            }
        });
        final Button removeResourceButton = widgetFactory.createButton(buttonComposite, Messages.Remove, SWT.PUSH);
        removeResourceButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        removeResourceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INTIAL_RESOURCE_LIST,((IStructuredSelection) resourceTableViewer.getSelection()).toList()));
                resourceTableViewer.refresh();
            }
        });

        resourceTableViewer.setInput(
                EMFEditProperties.list(
                        editingDomain,
                        FormPackage.Literals.FILE_WIDGET__INTIAL_RESOURCE_LIST)
                        .observe(element));

        return client;
    }

    protected Control createURLComposite(Section section, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite client  = widgetFactory.createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        inputURLExpressionViewer = new ExpressionViewer(client,SWT.BORDER,widgetFactory,editingDomain, FormPackage.Literals.WIDGET__INPUT_EXPRESSION) ;
        inputURLExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.PARAMETER_TYPE})) ;
        inputURLExpressionViewer.setMessage(getURLHint(),IStatus.INFO);
        inputURLExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        return client;
    }

    protected Control createMultipleDocumentComposite(Section section, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite client  = widgetFactory.createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        inputDocumentExpressionViewer = new ExpressionViewer(client,SWT.BORDER,widgetFactory,editingDomain, FormPackage.Literals.WIDGET__INPUT_EXPRESSION) ;
        inputDocumentExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.PARAMETER_TYPE})) ;
        inputDocumentExpressionViewer.setMessage(Messages.data_tooltip_multiple_document,IStatus.INFO);
        inputDocumentExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        return client;
    }

    private String getURLHint() {
        if(element.isDuplicate()){
            return Messages.data_tooltip_url_multiple ;
        }else{
            return Messages.data_tooltip_url ;
        }

    }

    protected Control createDocumentComposite(Section section, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite client  = widgetFactory.createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        inputDocumentViewer = new ComboViewer(client, SWT.BORDER | SWT.READ_ONLY);
        inputDocumentViewer.setContentProvider(new ObservableListContentProvider(){
            @Override
            public Object[] getElements(Object inputElement) {
                final Object[] elements = super.getElements(inputElement);
                final Object[] copyOf = new Object[elements.length +1];
                for(int i = 0; i< elements.length; i++){
                    copyOf[i] = elements[i];
                }
                copyOf[elements.length] = noDocumentComboItem;
                return copyOf;
            }
        });
        inputDocumentViewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(Object element) {
                if(element instanceof Document){
                    return ((Document) element).getName();
                } else {
                    return super.getText(element);
                }
            }
        });
        GridData gData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gData.widthHint = 200;
        inputDocumentViewer.getControl().setLayoutData(gData);
        return client ;
    }

    protected void bindFields() {
        if(dataBindingContext != null){
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();
        if(downloadOnly != null){
            if(ModelHelper.getForm(element) instanceof ViewForm){
                downloadOnly.setSelection(true);
                downloadOnly.setEnabled(false);
            }else{
                dataBindingContext.bindValue(
                        SWTObservables.observeSelection(downloadOnly),
                        EMFEditObservables.observeValue(
                                editingDomain,
                                element,
                                FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY));
            }
        }

        dataBindingContext.bindValue(SWTObservables.observeSelection(imagePreview), EMFEditObservables.observeValue(editingDomain, element,
                FormPackage.Literals.FILE_WIDGET__USE_PREVIEW));

        IObservableValue value = EMFObservables.observeValue(element, FormPackage.Literals.DUPLICABLE__DUPLICATE);

        value.addValueChangeListener(new IValueChangeListener() {

            public void handleValueChange(ValueChangeEvent arg0) {
                if(useDocumentButton != null &&!useDocumentButton.isDisposed() && element.getInputType() == FileWidgetInputType.DOCUMENT){
                    useDocumentButton.notifyListeners(SWT.Selection,new Event());
                }else if(!useResourceButton.isDisposed() && element.getInputType() == FileWidgetInputType.URL){
                    useURLButton.notifyListeners(SWT.Selection,new Event());
                }else if(!useResourceButton.isDisposed() && element.getInputType() == FileWidgetInputType.RESOURCE){
                    useResourceButton.notifyListeners(SWT.Selection,new Event());
                }
                if(inputURLExpressionViewer!=null && !getURLHint().equals(inputURLExpressionViewer.getMessage(IStatus.INFO))){
                    inputURLExpressionViewer.setMessage(getURLHint(),IStatus.INFO);
                }

            }
        });


        bindDocumentViewer();
        bindURLExpressionViewer();
        bindResourceText();
        bindMultipleDocumentExpressionViewer();
    }

    protected void bindResourceText() {
        if(resourceText != null && element.getInputType() == FileWidgetInputType.RESOURCE && !resourceText.isDisposed()){
            dataBindingContext.bindValue(SWTObservables.observeText(resourceText, SWT.Modify), EMFEditObservables.observeValue(editingDomain, element, FormPackage.Literals.FILE_WIDGET__INITIAL_RESOURCE_PATH));
        }
    }

    protected void bindDocumentViewer() {
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(element);
        if(inputDocumentViewer != null && !element.isDuplicate() && element.getInputType() == FileWidgetInputType.DOCUMENT && parentProcess != null){
            inputDocumentViewer.setInput(
                    EMFEditProperties.list(
                            editingDomain,
                            ProcessPackage.Literals.POOL__DOCUMENTS)
                            .observe(parentProcess));

            dataBindingContext.bindValue(
                    ViewerProperties.singleSelection().observe(inputDocumentViewer),
                    EMFEditProperties.value(editingDomain, FormPackage.Literals.FILE_WIDGET__DOCUMENT).observe(element),
                    new UpdateValueStrategy(){
                        @Override
                        public Object convert(Object value) {
                            if(noDocumentComboItem.equals(value)){
                                return null;
                            } else {
                                return super.convert(value);
                            }

                        }
                    },
                    new UpdateValueStrategy(){
                        @Override
                        public Object convert(Object value) {
                            if(value == null){
                                return noDocumentComboItem;
                            } else {
                                return super.convert(value);
                            }
                        }
                    });
        }
    }

    protected void bindURLExpressionViewer() {
        if(inputURLExpressionViewer != null && element.getInputType() == FileWidgetInputType.URL && !inputURLExpressionViewer.getControl().isDisposed()){
            Expression input = element.getInputExpression() ;
            if(input == null){
                input = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.WIDGET__INPUT_EXPRESSION, input));
            }

            inputURLExpressionViewer.setEditingDomain(editingDomain) ;
            dataBindingContext.bindValue(
                    ViewersObservables.observeSingleSelection(inputURLExpressionViewer),
                    EMFEditObservables.observeValue(editingDomain,element, FormPackage.Literals.WIDGET__INPUT_EXPRESSION));
            inputURLExpressionViewer.setInput(element) ;
        }
    }

    protected void bindMultipleDocumentExpressionViewer() {
        if(inputDocumentExpressionViewer != null && element.isDuplicate() && element.getInputType() == FileWidgetInputType.DOCUMENT && !inputDocumentExpressionViewer.getControl().isDisposed()){
            Expression input = element.getInputExpression() ;
            if(input == null){
                input = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.WIDGET__INPUT_EXPRESSION, input));
            }

            inputDocumentExpressionViewer.setEditingDomain(editingDomain) ;

            dataBindingContext.bindValue(
                    ViewersObservables.observeSingleSelection(inputDocumentExpressionViewer),
                    EMFEditObservables.observeValue(editingDomain,element, FormPackage.Literals.WIDGET__INPUT_EXPRESSION));
            inputDocumentExpressionViewer.setInput(element) ;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return Messages.Action_InitialValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof FileWidget;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#refresh()
     */
    public void refresh() {

    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(EObject object) {
        element = (FileWidget) object;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(ISelection selection) {
        // NOTHING
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

}
