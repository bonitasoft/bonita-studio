/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.document;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.ElementForIdLabelProvider;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ObservableExpressionContentProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Aurelien Pupier
 *
 */
public class DocumentPropertySection extends AbstractBonitaDescriptionSection {

	private ListViewer documentListViewer;
	private EMFDataBindingContext emfDataBindingContext;
	private DataBindingContext dataBindingContext;
	private Text documentNameText;
	private Text documentDescriptionText;
	private Button internalCheckbox;
	private ExpressionViewer documentUrlViewer;
	private ExpressionViewer documentMimeTypeViewer;
	private Button removeButton;
	private Text documentTextId;
	private Button browseButton;
	private Button externalCheckbox;
	private Composite detailsComposite;

	public DocumentPropertySection() {
		//keep it for reflective instanciation by Eclipse
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		if(emfDataBindingContext != null){
			emfDataBindingContext.dispose();
		}
		emfDataBindingContext = new EMFDataBindingContext();
		if(dataBindingContext != null){
			dataBindingContext.dispose();
		}
		dataBindingContext = new DataBindingContext();
		Composite mainComposite = getWidgetFactory().createComposite(parent);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		createMasterPanel(mainComposite);
		createDetailsPanel(mainComposite);
		documentListViewer.getList().setFocus();
	}

	protected void createMasterPanel(Composite mainComposite) {
		Composite masterComposite = getWidgetFactory().createComposite(mainComposite);
		masterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		masterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		// Label explanationLabel = getWidgetFactory().createLabel(masterComposite, Messages.explainDocumentPropertySection);
		// explanationLabel.setLayoutData(GridDataFactory.fillDefaults().span(2,1).create());
		getWidgetFactory().createLabel(masterComposite, "");//filler
		final Text documentListFilter = getWidgetFactory().createText(masterComposite, "", SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
		documentListFilter.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		documentListFilter.setMessage(WorkbenchMessages.FilteredTree_FilterMessage);
		documentListFilter.addModifyListener(new ModifyListener() {

			private ViewerFilter filter;

			@Override
			public void modifyText(ModifyEvent e) {
				final String textForFiltering = documentListFilter.getText();
				if(filter != null){
					documentListViewer.removeFilter(filter);
				}
				if(textForFiltering != null
						&& !textForFiltering.isEmpty()){
					filter = new ViewerFilter() {

						@Override
						public boolean select(Viewer viewer, Object parentElement, Object element) {
							return ((Element)element).getName().contains(textForFiltering);
						}
					};
					documentListViewer.addFilter(filter);
				}

			}
		});
		createButtons(masterComposite);
		documentListViewer = createList(masterComposite);
	}

	private ListViewer createList(Composite mainComposite) {
		List list = getWidgetFactory().createList(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL );
		list.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		ListViewer documentListViewer = new ListViewer(list);
		documentListViewer.setLabelProvider(new ElementForIdLabelProvider());
		documentListViewer.setContentProvider(new ObservableListContentProvider());
		return documentListViewer;
	}

	private void createButtons(Composite mainComposite) {
		Composite buttonComposite = getWidgetFactory().createComposite(mainComposite);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		createAddButton(buttonComposite);
		createRemoveButton(buttonComposite);
	}

	private void createAddButton(Composite buttonComposite) {
		Button addButton = getWidgetFactory().createButton(buttonComposite, Messages.AddSimple, SWT.FLAT);
		addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				Document newDocument = ProcessFactory.eINSTANCE.createDocument();
				Set<String> existingNames =  new HashSet<String>();
				for(Document existingDocument : getPool().getDocuments()){
					existingNames.add(existingDocument.getName());
				}
				newDocument.setName(NamingUtils.generateNewName(existingNames, Messages.documentDefaultName));
				newDocument.setUrl(ExpressionFactory.eINSTANCE.createExpression());
				final Expression mimetypeExpression = ExpressionFactory.eINSTANCE.createExpression();
				mimetypeExpression.setName("application/octet-stream");
				mimetypeExpression.setContent("application/octet-stream");
				mimetypeExpression.setReturnType(String.class.getName());
				mimetypeExpression.setType(ExpressionConstants.CONSTANT_TYPE);
				newDocument.setMimeType(mimetypeExpression);
				getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getPool().getDocuments(), newDocument));
				documentListViewer.setSelection(new StructuredSelection(newDocument));

				newDocument.eAdapters().add(new AdapterImpl(){
					@Override
					public void notifyChanged(Notification msg) {
						// TODO Auto-generated method stub
						super.notifyChanged(msg);
					}
				});
			}
		});
	}

	private void createRemoveButton(Composite buttonComposite) {
		removeButton = getWidgetFactory().createButton(buttonComposite, Messages.Remove, SWT.FLAT);
		removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				final Document documentToRemove = (Document)((IStructuredSelection)documentListViewer.getSelection()).getFirstElement();
				documentListViewer.remove(documentToRemove);
				getEditingDomain().getCommandStack().execute(DeleteCommand.create(getEditingDomain(), documentToRemove));
				documentListViewer.refresh();
				documentListViewer.setSelection(new StructuredSelection());
			}
		});
	}

	private void createDetailsPanel(Composite mainComposite) {
		detailsComposite = getWidgetFactory().createComposite(mainComposite);
		detailsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10, 5).create());
		detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		createDocumentNameField(detailsComposite);
		createDocumentDescriptionField(detailsComposite);
		createDocumentMimeTypeField(detailsComposite);
		createDocumentTypeCheckbox(detailsComposite);
	}

	private void createDocumentNameField(Composite detailsComposite) {
		getWidgetFactory().createLabel(detailsComposite, Messages.name);
		documentNameText = getWidgetFactory().createText(detailsComposite, "", SWT.BORDER);
		documentNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	}

	private void createDocumentDescriptionField(Composite detailsComposite) {
		getWidgetFactory().createLabel(detailsComposite, Messages.description);
		documentDescriptionText = getWidgetFactory().createText(detailsComposite, "", SWT.BORDER);
		documentDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	}

	private void createDocumentMimeTypeField(Composite detailsComposite) {
		Label mimeTypeLabel = getWidgetFactory().createLabel(detailsComposite, Messages.mimeType);
		documentMimeTypeViewer = new ExpressionViewer(detailsComposite, SWT.BORDER, getWidgetFactory(), getEditingDomain(), ProcessPackage.Literals.DOCUMENT__MIME_TYPE);
		documentMimeTypeViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE}));
		documentMimeTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		documentMimeTypeViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		documentMimeTypeViewer.setExample(Messages.hintMimeTypeDocument);
		ControlDecoration cd = new ControlDecoration(mimeTypeLabel, SWT.RIGHT);
		cd.setImage(Pics.getImage(PicsConstants.hint));
		cd.setDescriptionText(Messages.explanationMimeTypeDocument);
	}

	private void createDocumentTypeCheckbox(Composite detailsComposite) {
		Composite documentTypeComposite = getWidgetFactory().createComposite(detailsComposite);
		documentTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10,5).create());
		documentTypeComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 2).grab(true, true).create());
		externalCheckbox = getWidgetFactory().createButton(documentTypeComposite, Messages.External, SWT.RADIO);
		ControlDecoration controlDecorationForExternal = new ControlDecoration(externalCheckbox, SWT.RIGHT);
		controlDecorationForExternal.setImage(Pics.getImage(PicsConstants.hint));
		controlDecorationForExternal.setDescriptionText(Messages.explanationExternalDocument);
		createDocumentURL(documentTypeComposite);
		internalCheckbox = getWidgetFactory().createButton(documentTypeComposite, Messages.Internal, SWT.RADIO);
		ControlDecoration controlDecorationForInternal = new ControlDecoration(internalCheckbox, SWT.RIGHT);
		controlDecorationForInternal.setImage(Pics.getImage(PicsConstants.hint));
		controlDecorationForInternal.setDescriptionText(Messages.explanationInternalDocument);
		createDocumentBrowse(documentTypeComposite);
	}

	private void createDocumentURL(Composite slaveComposite) {
		documentUrlViewer = new ExpressionViewer(slaveComposite, SWT.BORDER, getWidgetFactory(), getEditingDomain(), ProcessPackage.Literals.DOCUMENT__URL);
		documentUrlViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE}));
		documentUrlViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		documentUrlViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		documentUrlViewer.setExample(Messages.hintExternalUrl);
		documentUrlViewer.setContentProvider(new ObservableExpressionContentProvider());
	}

	private void createDocumentBrowse(Composite slaveComposite) {
		Composite browseWithTextComposite = getWidgetFactory().createComposite(slaveComposite);
		browseWithTextComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		browseWithTextComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		documentTextId = getWidgetFactory().createText(browseWithTextComposite, "");
		documentTextId.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());;
		browseButton = getWidgetFactory().createButton(browseWithTextComposite, Messages.Browse, SWT.FLAT);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				final SelectDocumentInBonitaStudioRepository selectDocumentInBonitaStudioRepository = new SelectDocumentInBonitaStudioRepository(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
				if(IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository.open()){
					documentTextId.setText(selectDocumentInBonitaStudioRepository.getSelectedDocument().getName());
				}
			}
		});
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		resetDatabindingContext();
		bindList();

		final IObservableValue documentSelected = ViewersObservables.observeSingleSelection(documentListViewer);
		bindDetails(documentSelected);
		documentListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				final ISelection listSelection = event.getSelection();
				if(listSelection.isEmpty()){
					removeButton.setEnabled(false);
					detailsComposite.setVisible(false);
				} else {
					detailsComposite.setVisible(true);
					removeButton.setEnabled(true);
					final Document document = (Document) documentSelected.getValue();
					documentUrlViewer.getTextControl().setEnabled(!document.isIsInternal());
					documentUrlViewer.getButtonControl().setEnabled(!document.isIsInternal());
					documentTextId.setEnabled(document.isIsInternal());
					browseButton.setEnabled(document.isIsInternal());
				}

			}
		});

		documentListViewer.setSelection(new StructuredSelection());
	}

	protected void bindList() {
		final IObservableList documentsListObserved = EMFEditProperties.list(getEditingDomain(), ProcessPackage.Literals.POOL__DOCUMENTS).observe(getPool());
		emfDataBindingContext.bindList(
				WidgetProperties.items().observe(documentListViewer.getList()),
				documentsListObserved);
		documentListViewer.setInput(documentsListObserved);
	}

	protected void bindDetails(final IObservableValue documentSelected) {
		IObservableValue externalUrlObserved = EMFEditProperties.value(
				getEditingDomain(),
				ProcessPackage.Literals.DOCUMENT__URL).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				ViewerProperties.singleSelection().observe(documentUrlViewer),
				externalUrlObserved
				);
		documentUrlViewer.setInput(documentSelected);

		IObservableValue mimeTypeObserved = EMFEditProperties.value(
				getEditingDomain(),
				ProcessPackage.Literals.DOCUMENT__MIME_TYPE).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				ViewerProperties.singleSelection().observe(documentMimeTypeViewer),
				mimeTypeObserved
				);
		documentMimeTypeViewer.setInput(documentSelected);

		IObservableValue nameObserved = EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.ELEMENT__NAME).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				SWTObservables.observeDelayedValue(500, SWTObservables.observeText(documentNameText, SWT.Modify)),
				nameObserved);

		IObservableValue descriptionObserved = EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.ELEMENT__DOCUMENTATION).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				SWTObservables.observeDelayedValue(500, SWTObservables.observeText(documentDescriptionText, SWT.Modify)),
				descriptionObserved);

		IObservableValue internalTypeObserved = EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.DOCUMENT__IS_INTERNAL).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				SWTObservables.observeSelection(internalCheckbox),
				internalTypeObserved);

		IObservableValue documentInternalIDObserved = EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				SWTObservables.observeDelayedValue(500, SWTObservables.observeText(documentTextId, SWT.Modify)),
				documentInternalIDObserved);

		IObservableValue externalTypeObserved = EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.DOCUMENT__IS_INTERNAL).observeDetail(documentSelected);
		emfDataBindingContext.bindValue(
				SWTObservables.observeSelection(externalCheckbox),
				externalTypeObserved,
				new UpdateValueStrategy(){
					@Override
					public Object convert(Object value) {
						return super.convert(!(Boolean)value);
					}
				},
				new UpdateValueStrategy(){
					@Override
					public Object convert(Object value) {
						return super.convert(!(Boolean)value);
					}
				});

		emfDataBindingContext.bindValue(
				SWTObservables.observeEnabled(documentUrlViewer.getTextControl()),
				SWTObservables.observeSelection(externalCheckbox));
		emfDataBindingContext.bindValue(
				SWTObservables.observeEnabled(documentUrlViewer.getButtonControl()),
				SWTObservables.observeSelection(externalCheckbox));

		emfDataBindingContext.bindValue(
				SWTObservables.observeEnabled(documentTextId),
				SWTObservables.observeSelection(internalCheckbox));
		emfDataBindingContext.bindValue(
				SWTObservables.observeEnabled(browseButton),
				SWTObservables.observeSelection(internalCheckbox));
	}

	protected void resetDatabindingContext() {
		if(emfDataBindingContext != null){
			emfDataBindingContext.dispose();
		}
		emfDataBindingContext = new EMFDataBindingContext();
	}

	protected Pool getPool(){
		return (Pool)ModelHelper.getParentProcess(getEObject());
	}

	@Override
	public void dispose() {
		super.dispose();
		if(emfDataBindingContext != null){
			emfDataBindingContext.dispose();
		}
	}

	@Override
	public String getSectionDescription() {
		return Messages.documentPropertySectionDescription;
	}
}
