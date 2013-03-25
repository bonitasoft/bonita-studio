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

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
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
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class ImagePathContribution implements IExtensibleGridPropertySectionContribution {

	private ImageWidget img;
	private TransactionalEditingDomain editingDomain;
	private ComboViewer documentSelectedViewer;
	private ExpressionViewer urlImgPathExpression;
	private Button browse;
	private EMFDataBindingContext context = null;
	private final String noDocumentComboItem = Messages.noDocument;

	private TabbedPropertySheetWidgetFactory widgetFactory;
	private ControlDecoration controlDecoration;
	private Button isUrlButton;
	private Button isADocumentButton;
	private Section initialValueSection;
	private Boolean lastMultiple = Boolean.FALSE;

	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		this.widgetFactory = widgetFactory;
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		initialValueSection = widgetFactory.createSection(composite, Section.NO_TITLE | Section.CLIENT_INDENT | Section.EXPANDED) ;
		initialValueSection.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
		initialValueSection.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;

		if(img.isDuplicate()){
			initialValueSection.setClient(createMultipleControl(initialValueSection, widgetFactory));
		}else{
			initialValueSection.setClient(createDefaultControl(initialValueSection, widgetFactory));
		}
		final IObservableValue duplicableObservable = EMFEditObservables.observeValue(editingDomain, img, FormPackage.Literals.DUPLICABLE__DUPLICATE);
		duplicableObservable.addValueChangeListener(new IValueChangeListener() {

			public void handleValueChange(ValueChangeEvent event) {
				Boolean isMultiple = (Boolean) event.diff.getNewValue();
				if( isMultiple.booleanValue() != lastMultiple.booleanValue() ){
					if(initialValueSection != null && !initialValueSection.isDisposed()){
						if(initialValueSection.getClient() != null){
							initialValueSection.getClient().dispose() ;
						}
						if(isMultiple){
							initialValueSection.setClient(createMultipleControl(initialValueSection, ImagePathContribution.this.widgetFactory));
						}else{
							initialValueSection.setClient(createDefaultControl(initialValueSection, ImagePathContribution.this.widgetFactory));
						}
						initialValueSection.setExpanded(true);
						bindFields();
						lastMultiple = isMultiple;
					}
				}
			}
		});
		bindFields();
		initialValueSection.setExpanded(true) ;
	}

	private Control createMultipleControl(Section section,TabbedPropertySheetWidgetFactory widgetFactory) {

		final Composite composite = widgetFactory.createComposite(section);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

		isADocumentButton = widgetFactory.createButton(composite, Messages.imageIsAnAttachment, SWT.RADIO);
		isUrlButton = widgetFactory.createButton(composite, Messages.imageIsAURL, SWT.RADIO);
		urlImgPathExpression = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH);
		urlImgPathExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(500, SWT.DEFAULT).span(2, 1).indent(5, 0).create());
		urlImgPathExpression.setMessage(Messages.multipleExplanation, IStatus.INFO);

		return composite;
	}

	private Control createDefaultControl(Section section,
			TabbedPropertySheetWidgetFactory widgetFactory) {

		final Composite composite = widgetFactory.createComposite(section);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());

		createDocumentPanel(composite, widgetFactory);

		isUrlButton = widgetFactory.createButton(composite, Messages.imageIsAURL, SWT.RADIO);
		urlImgPathExpression = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH);
		urlImgPathExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(500, SWT.DEFAULT).indent(5, 0).create());
		urlImgPathExpression.setMessage(Messages.urlExplanation, IStatus.INFO);

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

		return composite;
	}

	protected void createDocumentPanel(Composite composite,TabbedPropertySheetWidgetFactory widgetFactory) {
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
		documentSelectedViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).hint(300, SWT.DEFAULT).indent(5, 0).create());
		widgetFactory.createLabel(composite, "");
		widgetFactory.createLabel(composite, "");

		controlDecoration = new ControlDecoration(documentSelectedViewer.getControl(), SWT.LEFT);
		controlDecoration.setImage(Pics.getImage("decoration/smartmode_co.gif"));
		controlDecoration.setDescriptionText(Messages.data_tooltip_image);
	}

	protected void bindFields() {
		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();

		
		bindDefaultControl();

		
	}


	private void bindDefaultControl() {
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
		}else{
			if(img.isDuplicate()){
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, imgExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, List.class.getName()));
			}else{
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, imgExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
			}
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

		if(!img.isDuplicate()){
			context.bindValue(
					WidgetProperties.enabled().observe(urlImgPathExpression.getTextControl()),
					WidgetProperties.selection().observe(isUrlButton));
			context.bindValue(
					WidgetProperties.enabled().observe(urlImgPathExpression.getToolbar()),
					WidgetProperties.selection().observe(isUrlButton));
			context.bindValue(
					WidgetProperties.enabled().observe(browse),
					WidgetProperties.selection().observe(isUrlButton));
		}

		if(documentSelectedViewer != null && !documentSelectedViewer.getControl().isDisposed()){
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
	}

	public void dispose() {
		if(context != null){
			context.dispose();
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
