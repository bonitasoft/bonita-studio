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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.document.DocumentInitialContentValidator;
import org.bonitasoft.studio.document.DocumentNameValidator;
import org.bonitasoft.studio.document.SelectDocumentInBonitaStudioRepository;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.DefaultExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.GroovyOnlyExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ObservableExpressionContentProvider;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class DocumentWizardPage extends WizardPage {

    private final EObject context;
    private final Document document;
    private ExpressionViewer documentUrlViewer;
    private ExpressionViewer documentMimeTypeViewer;
    private Composite detailsComposite;
    private EMFDataBindingContext emfDataBindingContext;
    private WizardPageSupport pageSupport;


    private StackLayout stack;

    protected static final String LINK = "link";
    protected static final String FIELD = "field";

    private Composite externalCompo;
    private Composite internalCompo;
    private Composite noneCompo;
    private Composite stackedComposite;
    private Composite mimeTypeComposition;
    private Link hideLink;
    private Composite manageLinkComposition;
    private StackLayout mimeStack;
    private Composite mimeCompo;
    private Label mimeTypeLabel;
    private ControlDecoration cd;
    private final DocumentInitialContentValidator externalValidator;

    final private int URL_SIZE_MAX = 1023;

    private Composite SMComposite;
    private StackLayout singleMultiplestack;
    private Composite singleComposite;
    private Composite multipleComposite;
    private Link manageLink;
    private GroovyOnlyExpressionViewer multipleInitialContentExpressionViewer;
    private IObservableValue multipleInitialContentObserved;
    private IViewerObservableValue multipleInitialContentObservedWidget;
    private AvailableExpressionTypeFilter availableExpressionTypeFilter;
    private Binding internalFileIdbinding;

    public DocumentWizardPage(final EObject context,final Document document){
        super(DocumentWizardPage.class.getName());
        this.context = context;
        this.document = document;
        setTitle(Messages.bind(Messages.documentWizardPageTitle, getCurrentContextName()));
        setDescription(Messages.newDocumentWizardDescription);
        externalValidator = new DocumentInitialContentValidator(URL_SIZE_MAX);
    }

    @Override
    public void createControl(final Composite parent) {
        emfDataBindingContext = createDatabindingContext();
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(7, 7).create());
        createDetailsPanel(mainComposite);

        pageSupport =  WizardPageSupport.create(this, emfDataBindingContext) ;
        setControl(mainComposite);
    }

    private String getCurrentContextName() {
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

        createDocumentSingleMultipleComposition(detailsComposite);
    }

    private void createMimeType(final Composite detailsComposite) {
        mimeTypeLabel = new Label(detailsComposite, SWT.NONE);
        mimeTypeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        mimeTypeLabel.setText(Messages.mimeType);
        mimeTypeLabel.setAlignment(SWT.CENTER);
        cd = new ControlDecoration(mimeTypeLabel, SWT.RIGHT);
        cd.setImage(getHintImage());
        cd.setDescriptionText(Messages.explanationMimeTypeDocument);

        mimeCompo = new Composite(detailsComposite, SWT.NONE);
        mimeCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        mimeCompo.setLayoutData(GridDataFactory.fillDefaults().create());
        mimeStack = new StackLayout();
        mimeCompo.setLayout(mimeStack);
        createDocumentMimeTypeField(mimeCompo);
        createDocumentManageMimeTypeLink(mimeCompo);
    }

    private void createDocumentNameField(final Composite detailsComposite) {
        final Label nameLabel = new Label(detailsComposite, SWT.NONE);
        nameLabel.setText(Messages.name + " *");
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        final Text documentNameText = new Text(detailsComposite, SWT.BORDER);
        documentNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        bindDocumentName(documentNameText, emfDataBindingContext);
    }

    protected void bindDocumentName(final Text documentNameText, final DataBindingContext dbc) {
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new InputLengthValidator(Messages.name, 50));
        targetToModel.setBeforeSetValidator(new GroovyReferenceValidator(Messages.name, true));
        targetToModel.setAfterConvertValidator(new DocumentNameValidator(context, document != null ? document.getName() : null));

        dbc.bindValue(SWTObservables.observeText(documentNameText, SWT.Modify),
                EMFObservables.observeValue(document, ProcessPackage.Literals.ELEMENT__NAME),
                targetToModel,
                null);
    }

    private void createDocumentDescriptionField(final Composite detailsComposite) {
        final Label description = new Label(detailsComposite, SWT.NONE);
        description.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        description.setText(Messages.description);

        final Text documentDescriptionText = new Text(detailsComposite, SWT.BORDER | SWT.V_SCROLL);
        documentDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 60).create());

        emfDataBindingContext.bindValue(SWTObservables.observeText(documentDescriptionText, SWT.Modify),
                EMFObservables.observeValue(document, ProcessPackage.Literals.ELEMENT__DOCUMENTATION));
    }

    private void createDocumentMimeTypeField(final Composite detailsComposite) {
        mimeTypeComposition = new Composite(detailsComposite, SWT.NONE);
        mimeTypeComposition.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mimeTypeComposition.setLayoutData(GridDataFactory.fillDefaults().create());

        documentMimeTypeViewer = createDocumentMIMETypeExpressionViewer(mimeTypeComposition);
        emfDataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(documentMimeTypeViewer),
                EMFObservables.observeValue(document,
                        ProcessPackage.Literals.DOCUMENT__MIME_TYPE));

        hideLink = new Link(mimeTypeComposition, SWT.NONE);
        hideLink.setText("<A>" + Messages.hideMimeType + "</A>");
        hideLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                updateMimeTypeStack(LINK);
            }
        });
    }

    protected ExpressionViewer createDocumentMIMETypeExpressionViewer(final Composite parent) {
        final ExpressionViewer documentMimeTypeViewer = createExpressionViewer(parent, ProcessPackage.Literals.DOCUMENT__MIME_TYPE);
        documentMimeTypeViewer.addFilter(getConstantTypeOnlyExpressionViewerFilter());
        documentMimeTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentMimeTypeViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentMimeTypeViewer.setExample(Messages.hintMimeTypeDocument);
        documentMimeTypeViewer.setInput(document);
        return documentMimeTypeViewer;
    }

    protected ExpressionViewer createExpressionViewer(final Composite parent, final EReference reference) {
        return new ExpressionViewer(parent, SWT.BORDER);
    }

    protected GroovyOnlyExpressionViewer createExpressionViewerWithGroovyScriptOnly(final Composite parent) {
        return new GroovyOnlyExpressionViewer(parent, SWT.READ_ONLY | SWT.BORDER);
    }

    private void createDocumentManageMimeTypeLink(final Composite detailsComposite) {
        manageLinkComposition = new Composite(detailsComposite, SWT.NONE);
        manageLinkComposition.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        manageLinkComposition.setLayoutData(GridDataFactory.fillDefaults().create());

        manageLink = new Link(manageLinkComposition, SWT.NONE);
        manageLink.setText("<A>" + Messages.manageMimeType + "</A>");
        manageLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent arg0) {
                updateMimeTypeStack(FIELD);
            }
        });
    }

    protected void createDocumentURL(final Composite slaveComposite, final DataBindingContext dbc) {
        documentUrlViewer = createExpressionViewer(slaveComposite, ProcessPackage.Literals.DOCUMENT__URL);
        documentUrlViewer.addFilter(getConstantTypeOnlyExpressionViewerFilter());
        documentUrlViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentUrlViewer.getTextControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentUrlViewer.setExample(Messages.hintExternalUrl);
        documentUrlViewer.setContentProvider(new ObservableExpressionContentProvider());
        documentUrlViewer.setInput(document);
        documentUrlViewer.setExternalDataBindingContext(emfDataBindingContext);

        documentUrlViewer.addExpressionValidator(new DefaultExpressionValidator() {

            @Override
            public IStatus validate(final Object value) {
                return externalValidator.validate(document);
            }
        });

        final IObservableValue externalUrlObserved = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__URL);
        dbc.bindValue(ViewerProperties.singleSelection().observe(documentUrlViewer), externalUrlObserved);
    }

    private void createDocumentBrowse(final Composite slaveComposite) {
        final Composite browseWithTextComposite = new Composite(slaveComposite, SWT.NONE);
        browseWithTextComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        browseWithTextComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Text documentTextId = new Text(browseWithTextComposite, SWT.BORDER);
        documentTextId.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER)
                .grab(true, false).indent(10, 0).create());


        final UpdateValueStrategy uvsInternal = new UpdateValueStrategy();
        uvsInternal.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                return validateInternalDocumentId(value);
            }

        });

        internalFileIdbinding = emfDataBindingContext.bindValue(
                SWTObservables.observeText(documentTextId, SWT.Modify),
                EMFObservables.observeValue(document,
                        ProcessPackage.Literals.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE),
                uvsInternal, null);
        ControlDecorationSupport.create(internalFileIdbinding, SWT.LEFT);


        final Button browseButton = new Button(browseWithTextComposite, SWT.FLAT);
        browseButton.setText(Messages.Browse);
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectDocumentInBonitaStudioRepository selectDocumentInBonitaStudioRepository = new SelectDocumentInBonitaStudioRepository(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow());
                if (IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository.open()) {
                    documentTextId.setText(selectDocumentInBonitaStudioRepository.getSelectedDocument().getDisplayName());
                }
            }
        });
    }

    protected IStatus validateInternalDocumentId(final Object value) {
        if (!document.isMultiple()
                && DocumentType.INTERNAL.equals(document.getDocumentType())
                && (value == null || ((String) value).isEmpty())) {
            return ValidationStatus.error(Messages.error_documentDefaultIDEmpty);
        }
        return ValidationStatus.ok();
    }

    private void createDocumentSingleMultipleComposition(final Composite parent) {
        // dummy for label
        new Composite(parent, SWT.NONE);

        createMultipleRadioButtonComposition(parent);

        SMComposite = new Composite(parent, SWT.NONE);
        SMComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).span(2, 1).create());
        singleMultiplestack = new StackLayout();
        SMComposite.setLayout(singleMultiplestack);

        createSingleComposition(SMComposite);
        createMultipleComposition(SMComposite);

        createMimeType(parent);
        updateMimeTypeStack();

        updateSingleMultipleStack(document.isMultiple());
    }

    private void updateMimeTypeStack() {
        if (document.getMimeType() == null || document.getMimeType().getContent() == null || document.getMimeType().getContent().isEmpty()) {
            updateMimeTypeStack(LINK);
        } else {
            updateMimeTypeStack(FIELD);
        }
    }

    private void createSingleComposition(final Composite parent) {
        singleComposite = new Composite(parent, SWT.NONE);
        singleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        singleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createDocumentInitialSingleContent(singleComposite);
    }

    private void createMultipleComposition(final Composite parent) {
        multipleComposite = new Composite(parent, SWT.NONE);
        multipleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        multipleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label initialContentsLabel = new Label(multipleComposite, SWT.NONE);
        initialContentsLabel.setText(Messages.multipleInitialContentsLabel);
        initialContentsLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).indent(0, 5).create());

        createDocumentInitialMultipleContent(multipleComposite, emfDataBindingContext);
    }

    private void createMultipleRadioButtonComposition(final Composite parent) {
        final Composite compo = new Composite(parent, SWT.NONE);
        compo.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(20, 0).create());
        compo.setLayoutData(GridDataFactory.fillDefaults().indent(0, 25).create());

        final Button radioButtonSingle = createRadioButtonSingle(compo);
        final Button radioButtonMultiple = createRadioButtonMultiple(compo);

        final SelectObservableValue isMultipleObservableValue = new SelectObservableValue(ProcessPackage.DOCUMENT__MULTIPLE);
        isMultipleObservableValue.addOption(false, SWTObservables.observeSelection(radioButtonSingle));
        isMultipleObservableValue.addOption(true, SWTObservables.observeSelection(radioButtonMultiple));

        final IObservableValue multipleObserveValue = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__MULTIPLE);
        emfDataBindingContext.bindValue(
                isMultipleObservableValue,
                multipleObserveValue);
        multipleObserveValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate();
            }
        });
    }

    protected void validate() {
        documentUrlViewer.validate();
        if (internalFileIdbinding != null && !internalFileIdbinding.isDisposed()) {
            internalFileIdbinding.validateTargetToModel();
        }
    }

    private Button createRadioButtonSingle(final Composite compo) {
        final Button radioButtonSingle = new Button(compo, SWT.RADIO);
        radioButtonSingle.setText(Messages.radioButtonSingle);
        radioButtonSingle.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonSingle.getSelection()) {
                    updateSingleMultipleStack(false);
                }
            }
        });
        return radioButtonSingle;
    }

    private Button createRadioButtonMultiple(final Composite compo) {
        final Button radioButtonMultiple = new Button(compo, SWT.RADIO);
        radioButtonMultiple.setText(Messages.radioButtonMultiple);

        final ControlDecoration infoBonita = new ControlDecoration(radioButtonMultiple, SWT.RIGHT);
        infoBonita.show();
        infoBonita.setImage(getHintImage());
        infoBonita.setDescriptionText(Messages.radioButtonMultipleToolTip);

        radioButtonMultiple.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonMultiple.getSelection()) {
                    updateSingleMultipleStack(true);
                }
            }
        });
        return radioButtonMultiple;
    }

    protected Image getHintImage() {
        return Pics.getImage(PicsConstants.hint);
    }

    protected void createDocumentInitialMultipleContent(final Composite parent, final DataBindingContext dbc) {
        multipleInitialContentExpressionViewer = createExpressionViewerWithGroovyScriptOnly(parent);
        multipleInitialContentExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        multipleInitialContentExpressionViewer.setInput(document);

        dbc.bindValue(
                ViewerProperties.singleSelection().observe(multipleInitialContentExpressionViewer),
                EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__INITIAL_MULTIPLE_CONTENT));
    }

    private void createDocumentInitialSingleContent(final Composite parent) {
        final Label radioButtonLabel = new Label(parent, SWT.NONE);
        radioButtonLabel.setText(Messages.initialValueLabel);
        radioButtonLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).indent(0, 5).create());
        createDocumentTypeRadioButtonComposition(parent);

        new Composite(parent, SWT.NONE);

        stackedComposite = new Composite(parent, SWT.NONE);
        stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        stack = new StackLayout();
        stackedComposite.setLayout(stack);

        noneCompo = new Composite(stackedComposite, SWT.NONE);
        createInternalComposition(stackedComposite);
        createExternalComposition(stackedComposite);

        updateStack();
    }

    private void updateStack() {
        if (document.getDocumentType().equals(DocumentType.NONE)) {
            updateStack(DocumentType.NONE);
        } else if (document.getDocumentType().equals(DocumentType.INTERNAL)) {
            updateStack(DocumentType.INTERNAL);
        } else {
            updateStack(DocumentType.EXTERNAL);
        }
    }

    private void createExternalComposition(final Composite propertiesComposite) {
        externalCompo = new Composite(propertiesComposite, SWT.NONE);
        externalCompo.setLayout(new GridLayout(2, false));
        externalCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Label documentURLLabel = new Label(externalCompo, SWT.NONE);
        documentURLLabel.setText(Messages.documentExternalLabel + " *");
        createDocumentURL(externalCompo, emfDataBindingContext);
    }

    private void createInternalComposition(final Composite propertiesComposite) {
        internalCompo = new Composite(propertiesComposite, SWT.NONE);
        internalCompo.setLayout(new GridLayout(2, false));
        internalCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Label documentBrowserLabel = new Label(internalCompo, SWT.NONE);
        documentBrowserLabel.setText(Messages.documentInternalLabel + " *");
        createDocumentBrowse(internalCompo);
    }

    private void createDocumentTypeRadioButtonComposition(final Composite parent) {
        final Composite compo = new Composite(parent, SWT.NONE);
        compo.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).spacing(20, 0).create());
        compo.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button radioButtonNone = createRadioButtonNone(compo);
        final Button radioButtonInternal = createRadioButtonInternal(compo);
        final Button radioButtonExternal = createRadioButtonExternal(compo);

        final SelectObservableValue documentTypeObservableValue = new SelectObservableValue(ProcessPackage.DOCUMENT_TYPE);
        final IObservableValue btnDocumentTypeNone = SWTObservables.observeSelection(radioButtonNone);
        documentTypeObservableValue.addOption(DocumentType.NONE, btnDocumentTypeNone);

        final IObservableValue btnDocumentTypeExternal = SWTObservables.observeSelection(radioButtonExternal);
        documentTypeObservableValue.addOption(DocumentType.EXTERNAL, btnDocumentTypeExternal);

        final IObservableValue btnDocumentTypeInternal = SWTObservables.observeSelection(radioButtonInternal);
        documentTypeObservableValue.addOption(DocumentType.INTERNAL, btnDocumentTypeInternal);

        final IObservableValue documentTypeObservable = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE);
        emfDataBindingContext.bindValue(
                documentTypeObservableValue,
                documentTypeObservable);
        documentTypeObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate();
            }
        });
    }

    private Button createRadioButtonExternal(final Composite compo) {
        final Button radioButtonExternal = new Button(compo, SWT.RADIO);
        radioButtonExternal.setText(Messages.initialValueButtonExternal);
        final ControlDecoration infoExternal = new ControlDecoration(radioButtonExternal, SWT.RIGHT);
        infoExternal.show();
        infoExternal.setImage(getHintImage());
        infoExternal.setDescriptionText(Messages.initialValueButtonExternalToolTip);
        radioButtonExternal.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonExternal.getSelection()) {
                    updateStack(DocumentType.EXTERNAL);
                    updateMimeTypeEnabled(true);
                }
            }

        });
        return radioButtonExternal;
    }

    private Button createRadioButtonInternal(final Composite compo) {
        final Button radioButtonInternal = new Button(compo, SWT.RADIO);
        radioButtonInternal.setText(Messages.initialValueButtonInternal);
        final ControlDecoration infoBonita = new ControlDecoration(radioButtonInternal, SWT.RIGHT);
        infoBonita.show();
        infoBonita.setImage(getHintImage());
        infoBonita.setDescriptionText(Messages.initialValueButtonInternalToolTip);
        radioButtonInternal.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonInternal.getSelection()) {
                    updateStack(DocumentType.INTERNAL);
                    updateMimeTypeEnabled(true);
                }
            }

        });
        return radioButtonInternal;
    }

    private Button createRadioButtonNone(final Composite compo) {
        final Button radioButtonNone = new Button(compo, SWT.RADIO);
        radioButtonNone.setText(Messages.initialValueButtonNone);
        radioButtonNone.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if(radioButtonNone.getSelection()){
                    updateStack(DocumentType.NONE);
                    updateMimeTypeEnabled(false);
                }
            }

        });
        return radioButtonNone;
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

    protected void updateStack(final DocumentType docType) {
        if (docType.equals(DocumentType.NONE)) {
            stack.topControl = noneCompo;
        } else if (docType.equals(DocumentType.EXTERNAL)) {
            stack.topControl = externalCompo;
        } else if (docType.equals(DocumentType.INTERNAL)) {
            stack.topControl = internalCompo;
        }
        stackedComposite.layout();
    }

    protected void updateMimeTypeStack(final String type) {
        if (type.equals(LINK)) {
            mimeStack.topControl = manageLinkComposition;
            mimeTypeLabel.setVisible(false);
            cd.hide();
        } else if (type.equals(FIELD)) {
            mimeStack.topControl = mimeTypeComposition;
            mimeTypeLabel.setVisible(true);
            cd.show();
        }
        mimeCompo.layout();
    }


    protected void updateSingleMultipleStack(final boolean isMultiple) {
        if (isMultiple) {
            singleMultiplestack.topControl = multipleComposite;
            updateMimeTypeEnabled(false);
        } else {
            singleMultiplestack.topControl = singleComposite;
            updateMimeTypeEnabled(!document.getDocumentType().equals(DocumentType.NONE));
        }
        SMComposite.layout();
    }

    private void updateMimeTypeEnabled(final boolean isEnabled) {
        mimeTypeLabel.setEnabled(isEnabled);
        mimeCompo.setEnabled(isEnabled);
        manageLink.setEnabled(isEnabled);
        hideLink.setEnabled(isEnabled);
        documentMimeTypeViewer.getControl().setEnabled(isEnabled);
    }

    private EMFDataBindingContext createDatabindingContext() {
        return new EMFDataBindingContext();
    }


    protected AvailableExpressionTypeFilter getConstantTypeOnlyExpressionViewerFilter() {
        if (availableExpressionTypeFilter == null) {
            availableExpressionTypeFilter = new AvailableExpressionTypeFilter(
                    new String[] { ExpressionConstants.CONSTANT_TYPE });
        }
        return availableExpressionTypeFilter;
    }

}
