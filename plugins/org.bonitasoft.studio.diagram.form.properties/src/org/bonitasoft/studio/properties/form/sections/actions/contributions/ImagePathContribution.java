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
import org.bonitasoft.studio.expression.editor.filter.HiddenExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class ImagePathContribution implements IExtensibleGridPropertySectionContribution {

    private ImageWidget img;
    private TransactionalEditingDomain editingDomain;
    private ComboViewer documentSelectedViewer;
    private ExpressionViewer urlImgPathExpression;
    private Button browse;
    private EMFDataBindingContext context = null;
    private final String noDocumentComboItem = Messages.noDocument;


    private ControlDecoration controlDecoration;
    private final NotificationListener listener = new NotificationListener() {

        public void notifyChanged(Notification notification) {
            if(isUrlButton != null && !isUrlButton.isDisposed()){
                for(Listener l : isUrlButton.getListeners(SWT.Selection)){
                    if(l instanceof TypedListener){
                        Event e = new Event() ;
                        e.type = SWT.Selection;
                        e.widget = isUrlButton ;
                        ((TypedListener)l).handleEvent(e) ;
                    }
                }
            }

        }
    };
    private Button isUrlButton;
    private Button isADocumentButton;

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {

        GridLayout layout = new GridLayout(4, false);
        layout.marginHeight = InitialValueContribution.MARGIN_HEIGHT;
        layout.marginWidth = InitialValueContribution.MARGIN_WIDTH;
        composite.setLayout(layout);
        GridData gd = new GridData(SWT.FILL,SWT.CENTER,false,false);
        gd.widthHint = 300;
        gd.horizontalIndent = 5 ;

        if(context != null){
            context.dispose();
        }
        context = new EMFDataBindingContext();
        createDocumentPanel(composite, widgetFactory, gd);

        isUrlButton = widgetFactory.createButton(composite, Messages.imageIsAURL, SWT.RADIO);
        urlImgPathExpression = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH);
        urlImgPathExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(600, SWT.DEFAULT).indent(5, 0).create());
        urlImgPathExpression.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        ControlDecoration cd = new ControlDecoration(urlImgPathExpression.getControl(), SWT.LEFT);
        cd.setImage(Pics.getImage(PicsConstants.hint));
        cd.setDescriptionText(Messages.urlExplanation);

        browse = widgetFactory.createButton(composite, Messages.Browse, SWT.FLAT);
        browse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                final AbstractProcess parentProcess = ModelHelper.getParentProcess(img);
                if(parentProcess != null){
                    SelectFileStoreWizard selectImageFileStorWizard = new SelectFileStoreWizard(editingDomain, parentProcess, img.getImgPath().getContent());
                    WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), selectImageFileStorWizard);
                    if(wd.open() == IDialogConstants.OK_ID){
                        Expression expression = (Expression) ((IStructuredSelection) urlImgPathExpression.getSelection()).getFirstElement();
                        if(expression != null){
                            CompoundCommand cc = new CompoundCommand();
                            cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__CONTENT, selectImageFileStorWizard.getSelectedFilePath()));
                            cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__NAME, selectImageFileStorWizard.getSelectedFilePath()));
                            cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
                            cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
                            editingDomain.getCommandStack().execute(cc);
                        } else {
                            expression = ExpressionFactory.eINSTANCE.createExpression();
                            expression.setContent(selectImageFileStorWizard.getSelectedFilePath());
                            expression.setName(selectImageFileStorWizard.getSelectedFilePath());
                            expression.setReturnType(String.class.getName());
                            expression.setType(ExpressionConstants.CONSTANT_TYPE);
                            urlImgPathExpression.setSelection(new StructuredSelection(expression));
                        }
                    }
                }
            }

        });

        DiagramEventBroker.getInstance(editingDomain).addNotificationListener(img,FormPackage.eINSTANCE.getDuplicable_Duplicate(),listener) ;

        bindFields();
    }

    protected void createDocumentPanel(Composite composite,
            TabbedPropertySheetWidgetFactory widgetFactory, GridData gd) {
        isADocumentButton = widgetFactory.createButton(composite, Messages.imageIsAnAttachment, SWT.RADIO);

        documentSelectedViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
        documentSelectedViewer.setContentProvider(new ObservableListContentProvider(){
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
        documentSelectedViewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(Object element) {
                if(element instanceof Document){
                    return ((Document) element).getName();
                } else {
                    return super.getText(element);
                }
            }
        });
        documentSelectedViewer.getControl().setLayoutData(gd);
        widgetFactory.createLabel(composite, "");
        widgetFactory.createLabel(composite, "");

        controlDecoration = new ControlDecoration(documentSelectedViewer.getControl(), SWT.LEFT);
        controlDecoration.setImage(Pics.getImage("decoration/smartmode_co.gif"));
        controlDecoration.setDescriptionText(Messages.data_tooltip_image);
    }

    protected void bindFields() {
        UpdateValueStrategy updateValue = new UpdateValueStrategy(){
            @Override
            public Object convert(Object value) {
                if(value instanceof Boolean){
                    return !Boolean.valueOf((Boolean) value) ;
                }
                return super.convert(value);
            }
        };
        urlImgPathExpression.setInput(img);
        Expression imgExpression = img.getImgPath();
        if(imgExpression == null){
            imgExpression = ExpressionFactory.eINSTANCE.createExpression();
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, img, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH, imgExpression));
        }
        urlImgPathExpression.setSelection(new StructuredSelection(imgExpression));
        context.bindValue(
                ViewerProperties.singleSelection().observe(urlImgPathExpression),
                EMFEditProperties.value(editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH).observe(img));

        context.bindValue(
                WidgetProperties.selection().observe(isUrlButton),
                EMFObservables.observeValue(img, FormPackage.Literals.IMAGE_WIDGET__IS_ADOCUMENT),updateValue,updateValue);
        context.bindValue(
                WidgetProperties.selection().observe(isADocumentButton),
                EMFObservables.observeValue(img, FormPackage.Literals.IMAGE_WIDGET__IS_ADOCUMENT));


        context.bindValue(
                WidgetProperties.enabled().observe(urlImgPathExpression.getTextControl()),
                WidgetProperties.selection().observe(isUrlButton));
        context.bindValue(
                WidgetProperties.enabled().observe(urlImgPathExpression.getButtonControl()),
                WidgetProperties.selection().observe(isUrlButton));
        context.bindValue(
                WidgetProperties.enabled().observe(browse),
                WidgetProperties.selection().observe(isUrlButton));

        context.bindValue(
                WidgetProperties.enabled().observe(documentSelectedViewer.getControl()),
                WidgetProperties.selection().observe(isADocumentButton));
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(img);
        if(parentProcess != null){
            documentSelectedViewer.setInput(
                    EMFEditProperties.list(
                            editingDomain,
                            ProcessPackage.Literals.POOL__DOCUMENTS)
                            .observe(parentProcess));
            context.bindValue(
                    ViewerProperties.singleSelection().observe(documentSelectedViewer),
                    EMFEditProperties.value(editingDomain, FormPackage.Literals.IMAGE_WIDGET__DOCUMENT).observe(img),
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


    public void dispose() {
        if(context != null){
            context.dispose();
        }
        if(editingDomain != null){
            DiagramEventBroker.getInstance(editingDomain).removeNotificationListener(img,FormPackage.eINSTANCE.getDuplicable_Duplicate(),listener) ;
        }
    }

    public String getLabel() {
        return " ";
    }

    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof ImageWidget;
    }

    public void refresh() {
    }

    public void setEObject(EObject object) {
        img = (ImageWidget) object;

    }

    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(ISelection selection) {

    }

}
