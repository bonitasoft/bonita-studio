/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.document.ui;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.document.DocumentNameValidator;
import org.bonitasoft.studio.document.SelectDocumentInBonitaStudioRepository;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ObservableExpressionContentProvider;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class DocumentWizardPage extends WizardPage {

    private final EObject context;
    private final Document document;
    private Text documentNameText;
    private Text documentDescriptionText;
    private Button internalCheckbox;
    private ExpressionViewer documentUrlViewer;
    private ExpressionViewer documentMimeTypeViewer;
    private Text documentTextId;
    private Button browseButton;
    private Button externalCheckbox;
    private Composite detailsComposite;
    private EMFDataBindingContext emfDataBindingContext;
    private ControlDecorationSupport decorationSupport;
    private WizardPageSupport pageSupport;


    public DocumentWizardPage(final EObject context,final Document document){
        super(DocumentWizardPage.class.getName());
        this.context = context;
        this.document = document;
        setTitle(Messages.bind(Messages.documentWizardPageTitle,getCurrentContextName()));
        setDescription(Messages.newDocumentWizardDescription);
        emfDataBindingContext = new EMFDataBindingContext();
        setPageComplete(false);
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(7, 7).create());
        createDetailsPanel(mainComposite);
        pageSupport =  WizardPageSupport.create(this, emfDataBindingContext) ;
        bindDetails();
        setControl(mainComposite);
    }

    private String getCurrentContextName(){
        String name = "---";
        EObject container = context;
        while (!(container instanceof Pool) && container.eContainer()!=null) {
            container = container.eContainer();
        }
        if(container!=null && container instanceof Pool){
            name = ((Pool) container).getName();
        }
        return name;
    }

    private void createDetailsPanel(final Composite mainComposite) {
        detailsComposite = new Composite(mainComposite,SWT.NONE);
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10, 5).create());
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createDocumentNameField(detailsComposite);
        createDocumentDescriptionField(detailsComposite);
        createDocumentMimeTypeField(detailsComposite);
        createDocumentTypeCheckbox(detailsComposite);
    }

    private void createDocumentNameField(final Composite detailsComposite) {
        final Label nameLabel = new Label(detailsComposite, SWT.NONE);
        nameLabel.setText(Messages.name);
        documentNameText = new Text(detailsComposite, SWT.BORDER);
        documentNameText.setText("");
        documentNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    private void createDocumentDescriptionField(final Composite detailsComposite) {
        final Label description = new Label(detailsComposite,SWT.NONE);
        description.setText(Messages.description);
        documentDescriptionText = new Text(detailsComposite, SWT.BORDER | SWT.V_SCROLL);
        documentDescriptionText.setText("");
        documentDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 60).create());
    }

    private void createDocumentMimeTypeField(final Composite detailsComposite) {
        final Label mimeTypeLabel = new Label(detailsComposite,SWT.NONE);
        mimeTypeLabel.setText(Messages.mimeType);

        documentMimeTypeViewer = new ExpressionViewer(detailsComposite,
                SWT.BORDER, ProcessPackage.Literals.DOCUMENT__MIME_TYPE);
        documentMimeTypeViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE }));
        documentMimeTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentMimeTypeViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentMimeTypeViewer.setExample(Messages.hintMimeTypeDocument);
        final ControlDecoration cd = new ControlDecoration(mimeTypeLabel, SWT.RIGHT);
        cd.setImage(Pics.getImage(PicsConstants.hint));
        cd.setDescriptionText(Messages.explanationMimeTypeDocument);
    }

    private void createDocumentTypeCheckbox(final Composite detailsComposite) {
        final Composite documentTypeComposite = new Composite(
                detailsComposite,SWT.NONE);
        documentTypeComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).spacing(10, 5).create());
        documentTypeComposite.setLayoutData(GridDataFactory.fillDefaults()
                .span(2, 2).grab(true, true).create());
        externalCheckbox = new Button(
                documentTypeComposite,  SWT.RADIO);
        externalCheckbox.setText(Messages.External);
        final ControlDecoration controlDecorationForExternal = new ControlDecoration(
                externalCheckbox, SWT.RIGHT);
        controlDecorationForExternal
        .setImage(Pics.getImage(PicsConstants.hint));
        controlDecorationForExternal
        .setDescriptionText(Messages.explanationExternalDocument);
        createDocumentURL(documentTypeComposite);
        internalCheckbox = new Button(
                documentTypeComposite,  SWT.RADIO);
        internalCheckbox.setText(Messages.Internal);
        final ControlDecoration controlDecorationForInternal = new ControlDecoration(
                internalCheckbox, SWT.RIGHT);
        controlDecorationForInternal
        .setImage(Pics.getImage(PicsConstants.hint));
        controlDecorationForInternal.setDescriptionText(Messages.bind(
                Messages.explanationInternalDocument,
                new Object[] { bosProductName }));
        createDocumentBrowse(documentTypeComposite);
    }

    private void createDocumentURL(final Composite slaveComposite) {
        documentUrlViewer = new ExpressionViewer(slaveComposite, SWT.BORDER,
                ProcessPackage.Literals.DOCUMENT__URL);
        documentUrlViewer.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE }));
        documentUrlViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        documentUrlViewer.getTextControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        documentUrlViewer.setExample(Messages.hintExternalUrl);
        documentUrlViewer
        .setContentProvider(new ObservableExpressionContentProvider());
    }

    private void createDocumentBrowse(final Composite slaveComposite) {
        final Composite browseWithTextComposite =new Composite(
                slaveComposite,SWT.NONE);
        browseWithTextComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).create());
        browseWithTextComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        documentTextId = new Text(browseWithTextComposite,SWT.BORDER);
        documentTextId.setText("");
        documentTextId.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        ;
        browseButton = new Button(browseWithTextComposite, SWT.FLAT);
        browseButton.setText(Messages.Browse);
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final SelectDocumentInBonitaStudioRepository selectDocumentInBonitaStudioRepository = new SelectDocumentInBonitaStudioRepository(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow());
                if (IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository
                        .open()) {
                    documentTextId
                    .setText(selectDocumentInBonitaStudioRepository
                            .getSelectedDocument().getDisplayName());
                }
            }
        });
    }

    protected void bindDetails() {
        final IObservableValue externalUrlObserved =EMFObservables.observeValue(
                document, ProcessPackage.Literals.DOCUMENT__URL);
        emfDataBindingContext.bindValue(ViewerProperties.singleSelection()
                .observe(documentUrlViewer), externalUrlObserved);
        documentUrlViewer.setInput(document);

        final IObservableValue mimeTypeObserved = EMFObservables.observeValue(document,
                ProcessPackage.Literals.DOCUMENT__MIME_TYPE);

        emfDataBindingContext.bindValue(ViewerProperties.singleSelection()
                .observe(documentMimeTypeViewer), mimeTypeObserved);
        documentMimeTypeViewer.setInput(document);
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();

        targetToModel.setAfterGetValidator(new InputLengthValidator(
                Messages.name, 50));
        targetToModel.setBeforeSetValidator(new GroovyReferenceValidator(
                Messages.name, false));
        targetToModel.setAfterConvertValidator(new DocumentNameValidator(context));

        final IObservableValue nameObserved = EMFObservables.observeValue(
                document, ProcessPackage.Literals.ELEMENT__NAME);
        emfDataBindingContext.bindValue(
                SWTObservables.observeDelayedValue(500, SWTObservables.observeText(documentNameText, SWT.Modify)),
                nameObserved,
                targetToModel,
                null);

        final IObservableValue descriptionObserved =EMFObservables.observeValue(
                document,
                ProcessPackage.Literals.ELEMENT__DOCUMENTATION);
        emfDataBindingContext.bindValue(SWTObservables
                .observeDelayedValue(500, SWTObservables.observeText(
                        documentDescriptionText, SWT.Modify)),
                        descriptionObserved);

        final IObservableValue internalTypeObserved = EMFObservables.observeValue(
                document,
                ProcessPackage.Literals.DOCUMENT__IS_INTERNAL);
        emfDataBindingContext.bindValue(
                SWTObservables.observeSelection(internalCheckbox),
                internalTypeObserved);

        final IObservableValue documentInternalIDObserved = EMFObservables.observeValue(document,
                ProcessPackage.Literals.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE);

        emfDataBindingContext
        .bindValue(
                SWTObservables.observeDelayedValue(500, SWTObservables
                        .observeText(documentTextId, SWT.Modify)),
                        documentInternalIDObserved);

        final IObservableValue externalTypeObserved = EMFObservables.observeValue(
                document,
                ProcessPackage.Literals.DOCUMENT__IS_INTERNAL);
        emfDataBindingContext.bindValue(
                SWTObservables.observeSelection(externalCheckbox),
                externalTypeObserved, new UpdateValueStrategy() {
                    @Override
                    public Object convert(final Object value) {
                        return super.convert(!(Boolean) value);
                    }
                }, new UpdateValueStrategy() {
                    @Override
                    public Object convert(final Object value) {
                        return super.convert(!(Boolean) value);
                    }
                });

        emfDataBindingContext.bindValue(SWTObservables
                .observeEnabled(documentUrlViewer.getTextControl()),
                externalTypeObserved, new UpdateValueStrategy() {
            @Override
            public Object convert(final Object value) {
                return super.convert(!(Boolean) value);
            }
        }, new UpdateValueStrategy() {
            @Override
            public Object convert(final Object value) {
                return super.convert(!(Boolean) value);
            }
        });
        emfDataBindingContext.bindValue(SWTObservables.observeEnabled(documentUrlViewer.getButtonControl()),
                externalTypeObserved,
                new UpdateValueStrategy() {
            @Override
            public Object convert(final Object value) {
                return super.convert(!(Boolean) value);
            }
        },
        new UpdateValueStrategy() {
            @Override
            public Object convert(final Object value) {
                return super.convert(!(Boolean) value);
            }
        });

        emfDataBindingContext.bindValue(
                SWTObservables.observeEnabled(documentTextId),
                internalTypeObserved);
        emfDataBindingContext.bindValue(
                SWTObservables.observeEnabled(browseButton),
                internalTypeObserved);
    }

    protected void resetDatabindingContext() {
        if (emfDataBindingContext != null) {
            emfDataBindingContext.dispose();
        }
        emfDataBindingContext = new EMFDataBindingContext();
    }

    public  EObject getContext(){
        return context;
    }

    public Document getDocument(){
        return document;
    }

    @Override
    public void dispose() {
        if(pageSupport != null){
            pageSupport.dispose();
        }
        if (emfDataBindingContext != null) {
            emfDataBindingContext.dispose();
            emfDataBindingContext = null;
        }
        super.dispose();
    }

}
