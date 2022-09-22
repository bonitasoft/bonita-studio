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
package org.bonitasoft.studio.document.ui.wizardPage;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Iterator;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.control.DocumentDescriptionComposite;
import org.bonitasoft.studio.document.ui.control.DocumentNameComposite;
import org.bonitasoft.studio.document.ui.control.FileContractInputSelectionComposite;
import org.bonitasoft.studio.document.ui.control.MultipleFileContractInputSelectionComposite;
import org.bonitasoft.studio.document.ui.control.SingleFileContractInputSelectionComposite;
import org.bonitasoft.studio.document.ui.dialog.SelectResourceDialog;
import org.bonitasoft.studio.document.ui.validator.DocumentInitialContentValidator;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
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
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
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
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class DocumentWizardPage extends WizardPage {

    private final EObject context;
    private final Document document;
    private ExpressionViewer documentUrlViewer;
    private ExpressionViewer documentMimeTypeViewer;

    private StackLayout stack;

    protected static final String LINK = "link";
    protected static final String FIELD = "field";

    private Composite externalCompo;
    private Composite internalCompo;
    private Composite noneCompo;
    private Composite stackedComposite;
    private Composite mimeTypeComposition;
    private Link hideLink;
    private Composite manageLinkComposite;
    private StackLayout mimeStack;
    private Composite mimeCompo;

    final private int URL_SIZE_MAX = 1023;

    private StackLayout singleMultiplestack;
    private Composite singleComposite;
    private Composite multipleComposite;
    private Link manageLink;

    private FileContractInputSelectionComposite singleContractComposite;

    private FileContractInputSelectionComposite multipleContractComposite;

    private Composite scriptComposite;

    private Composite multipleStackedComposite;

    private StackLayout multipleStack;
    private Composite multiNoneCompo;

    public DocumentWizardPage(final EObject context, final Document document) {
        super(DocumentWizardPage.class.getName());
        this.context = context;
        this.document = document;
        setTitle(Messages.bind(Messages.documentWizardPageTitle, getCurrentContextName()));
        setDescription(Messages.newDocumentWizardDescription);
    }

    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext emfDataBindingContext = new EMFDataBindingContext();
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        new DocumentNameComposite(mainComposite).bindControl(document, context, emfDataBindingContext);
        new DocumentDescriptionComposite(mainComposite).bindControl(document, emfDataBindingContext);

        createSingleMultipleRadioGroup(mainComposite, emfDataBindingContext);

        final Group initialContentGroup = createInitialContentGroup(mainComposite);
        final Composite stackComposite = new Composite(initialContentGroup, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        singleMultiplestack = new StackLayout();
        stackComposite.setLayout(singleMultiplestack);
        createSingleComposite(stackComposite, emfDataBindingContext);
        createMultipleComposite(stackComposite, emfDataBindingContext);
        createMimeType(initialContentGroup, emfDataBindingContext);
        updateMimeTypeStack();
        updateSingleMultipleStack(document.isMultiple());
        updateStack(document.getDocumentType());
        WizardPageSupport.create(this, emfDataBindingContext);
        setControl(mainComposite);
    }

    protected Group createInitialContentGroup(final Composite parent) {
        final Group initialValueComposite = new Group(parent, SWT.NONE);
        initialValueComposite.setText(Messages.initialValueLabel);
        initialValueComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        initialValueComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        return initialValueComposite;
    }

    private String getCurrentContextName() {
        final Pool parentPool = ModelHelper.getParentPool(context);
        if (parentPool != null) {
            return parentPool.getName();
        }
        return "---";
    }

    private void createMimeType(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        final Composite mimeTypeContainer = new Composite(parent, SWT.NONE);
        mimeTypeContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mimeTypeContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        mimeCompo = new Composite(mimeTypeContainer, SWT.NONE);
        mimeCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        mimeCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mimeStack = new StackLayout();
        mimeCompo.setLayout(mimeStack);
        createDocumentMimeTypeField(mimeCompo, emfDataBindingContext);
        createDocumentManageMimeTypeLink(mimeCompo);
    }

    private void createDocumentMimeTypeField(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        mimeTypeComposition = new Composite(parent, SWT.NONE);
        mimeTypeComposition.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());
        mimeTypeComposition.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label mimeTypeLabel = new Label(mimeTypeComposition, SWT.NONE);
        mimeTypeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        mimeTypeLabel.setText(Messages.mimeType);

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
        final ExpressionViewer documentMimeTypeViewer = new ExpressionViewer(parent, SWT.BORDER);
        documentMimeTypeViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE));
        documentMimeTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentMimeTypeViewer.setExample(Messages.hintMimeTypeDocument);
        documentMimeTypeViewer.setMessage(Messages.explanationMimeTypeDocument);
        documentMimeTypeViewer.setInput(document);
        return documentMimeTypeViewer;
    }

    private void createDocumentManageMimeTypeLink(final Composite parent) {
        manageLinkComposite = new Composite(parent, SWT.NONE);
        manageLinkComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        manageLinkComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        manageLink = new Link(manageLinkComposite, SWT.NONE);
        manageLink.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).grab(true, false).create());
        manageLink.setText("<A>" + Messages.manageMimeType + "</A>");
        manageLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent arg0) {
                updateMimeTypeStack(FIELD);
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

    private void createSingleMultipleRadioGroup(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        //FILLER
        final Label filler = new Label(parent, SWT.NONE);
        filler.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());

        final Composite radioContainer = new Composite(parent, SWT.NONE);
        radioContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        radioContainer.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        final Button radioButtonSingle = createRadioButtonSingle(radioContainer);
        final Button radioButtonMultiple = createRadioButtonMultiple(radioContainer);

        final SelectObservableValue isMultipleObservableValue = new SelectObservableValue(Boolean.class);
        isMultipleObservableValue.addOption(false, SWTObservables.observeSelection(radioButtonSingle));
        isMultipleObservableValue.addOption(true, SWTObservables.observeSelection(radioButtonMultiple));

        final IObservableValue multipleObserveValue = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__MULTIPLE);
        emfDataBindingContext.bindValue(
                isMultipleObservableValue,
                multipleObserveValue);
        multipleObserveValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate(emfDataBindingContext);
            }
        });

    }

    private void updateMimeTypeStack() {
        if (document.getMimeType() == null || document.getMimeType().getContent() == null || document.getMimeType().getContent().isEmpty()) {
            updateMimeTypeStack(LINK);
        } else {
            updateMimeTypeStack(FIELD);
        }
    }

    private void createSingleComposite(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        singleComposite = new Composite(parent, SWT.NONE);
        singleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        singleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());

        createDocumentTypeRadioButtonComposition(singleComposite, emfDataBindingContext);

        stackedComposite = new Composite(singleComposite, SWT.NONE);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        stack = new StackLayout();
        stackedComposite.setLayout(stack);

        noneCompo = new Composite(stackedComposite, SWT.NONE);
        noneCompo.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());

        singleContractComposite = new SingleFileContractInputSelectionComposite(stackedComposite);
        singleContractComposite.bindControl(document, context, emfDataBindingContext);
        createLocalFileComposite(stackedComposite, emfDataBindingContext);
        createExternalURLComposite(stackedComposite, emfDataBindingContext);
    }

    protected String mandatoryFieldLabel(final String label) {
        return label + " *";
    }

    private void createMultipleComposite(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        multipleComposite = new Composite(parent, SWT.NONE);
        multipleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        multipleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        final Composite radioContainer = new Composite(multipleComposite, SWT.NONE);
        radioContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(10, 5).create());
        radioContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        final Button radioButtonNone = createRadioButtonNone(radioContainer);
        final Button radioButtonContract = createRadioButtonContract(radioContainer);
        final Button radioButtonScript = createRadioButtonInternal(radioContainer);
        radioButtonScript.setText(Messages.initialValueButtonScript);

        final SelectObservableValue documentTypeObservableValue = new SelectObservableValue(DocumentType.class);
        final ISWTObservableValue scriptObserveSelection = SWTObservables.observeSelection(radioButtonScript);
        documentTypeObservableValue.addOption(DocumentType.NONE, SWTObservables.observeSelection(radioButtonNone));
        documentTypeObservableValue.addOption(DocumentType.INTERNAL, scriptObserveSelection);
        documentTypeObservableValue.addOption(DocumentType.CONTRACT, SWTObservables.observeSelection(radioButtonContract));

        final IObservableValue documentTypeObservable = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE);
        emfDataBindingContext.bindValue(documentTypeObservableValue, documentTypeObservable, null,
                updateValueStrategy().withConverter(internalOrExternalToTrue())
                        .create());
        documentTypeObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate(emfDataBindingContext);
            }
        });

        multipleStackedComposite = new Composite(multipleComposite, SWT.NONE);
        multipleStackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        multipleStack = new StackLayout();
        multipleStackedComposite.setLayout(multipleStack);

        multiNoneCompo = new Composite(multipleStackedComposite, SWT.NONE);
        multiNoneCompo.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());

        multipleContractComposite = new MultipleFileContractInputSelectionComposite(multipleStackedComposite);
        multipleContractComposite.bindControl(document, context, emfDataBindingContext);
        scriptComposite = createScriptComposite(multipleStackedComposite, emfDataBindingContext);
    }

    private IConverter internalOrExternalToTrue() {
        return new Converter(DocumentType.class, DocumentType.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject == DocumentType.INTERNAL || fromObject == DocumentType.EXTERNAL ? DocumentType.INTERNAL : fromObject;
            }
        };
    }

    private Composite createScriptComposite(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        final Composite scriptComposite = new Composite(parent, SWT.NONE);
        scriptComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        scriptComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());

        final GroovyOnlyExpressionViewer multipleInitialContentExpressionViewer = new GroovyOnlyExpressionViewer(scriptComposite,  SWT.BORDER);
        multipleInitialContentExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        multipleInitialContentExpressionViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONTRACT_INPUT_TYPE));
        multipleInitialContentExpressionViewer.setMessage(Messages.documentListScriptToolTip);
        multipleInitialContentExpressionViewer.setInput(context);

        emfDataBindingContext.bindValue(ViewerProperties.singleSelection().observe(multipleInitialContentExpressionViewer),
                EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__INITIAL_MULTIPLE_CONTENT));
        return scriptComposite;
    }

    protected void validate(final EMFDataBindingContext emfDataBindingContext) {
        documentUrlViewer.validate();
        if (emfDataBindingContext != null) {
            final Iterator<?> iterator = emfDataBindingContext.getBindings().iterator();
            while (iterator.hasNext()) {
                final Binding binding = (Binding) iterator.next();
                binding.validateTargetToModel();
            }
        }
    }

    private Button createRadioButtonSingle(final Composite compo) {
        final Button radioButtonSingle = new Button(compo, SWT.RADIO);
        radioButtonSingle.setLayoutData(GridDataFactory.swtDefaults().create());
        radioButtonSingle.setText(Messages.radioButtonSingle);
        radioButtonSingle.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
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
        radioButtonMultiple.setLayoutData(GridDataFactory.swtDefaults().create());
        final ControlDecoration infoBonita = new ControlDecoration(radioButtonMultiple, SWT.RIGHT);
        infoBonita.show();
        infoBonita.setImage(Pics.getImage(PicsConstants.hint));
        infoBonita.setDescriptionText(Messages.radioButtonMultipleToolTip);

        radioButtonMultiple.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (radioButtonMultiple.getSelection()) {
                    updateSingleMultipleStack(true);
                }
            }
        });
        return radioButtonMultiple;
    }

    private void createExternalURLComposite(final Composite propertiesComposite, final EMFDataBindingContext emfDataBindingContext) {
        externalCompo = new Composite(propertiesComposite, SWT.NONE);
        externalCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
        externalCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label documentURLLabel = new Label(externalCompo, SWT.NONE);
        documentURLLabel.setText(mandatoryFieldLabel(Messages.documentExternalLabel));

        documentUrlViewer = new ExpressionViewer(externalCompo, SWT.BORDER, null, null, null, false);
        documentUrlViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE));
        documentUrlViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        documentUrlViewer.setExample(Messages.hintExternalUrl);
        documentUrlViewer.setContentProvider(new ObservableExpressionContentProvider());
        documentUrlViewer.setInput(document);
        documentUrlViewer.setExternalDataBindingContext(emfDataBindingContext);
        documentUrlViewer.addExpressionValidator(new DocumentInitialContentValidator(document, URL_SIZE_MAX));

        emfDataBindingContext.bindValue(ViewerProperties.singleSelection().observe(documentUrlViewer),
                EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__URL));
    }

    private void createLocalFileComposite(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        internalCompo = new Composite(parent, SWT.NONE);
        internalCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(5, 5).create());
        internalCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label documentBrowserLabel = new Label(internalCompo, SWT.NONE);
        documentBrowserLabel.setLayoutData(GridDataFactory.swtDefaults().create());
        documentBrowserLabel.setText(mandatoryFieldLabel(Messages.documentInternalLabel));

        final Text documentTextId = new Text(internalCompo, SWT.BORDER);
        documentTextId.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER)
                .grab(true, false).indent(10, 0).create());

        final UpdateValueStrategy uvsInternal = new UpdateValueStrategy();
        uvsInternal.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                return validateInternalDocumentId(value);
            }

        });

        ControlDecorationSupport.create(emfDataBindingContext.bindValue(
                SWTObservables.observeText(documentTextId, SWT.Modify),
                EMFObservables.observeValue(document,
                        ProcessPackage.Literals.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE),
                uvsInternal, null), SWT.LEFT);

        final Button browseButton = new Button(internalCompo, SWT.FLAT);
        browseButton.setText(Messages.Browse);
        browseButton.setLayoutData(GridDataFactory.swtDefaults().create());
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectResourceDialog selectDocumentInBonitaStudioRepository = new SelectResourceDialog(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow());
                if (IDialogConstants.OK_ID == selectDocumentInBonitaStudioRepository.open()) {
                    documentTextId.setText(selectDocumentInBonitaStudioRepository.getSelectedDocument().getDisplayName());
                }
            }
        });
    }

    private void createDocumentTypeRadioButtonComposition(final Composite parent, final EMFDataBindingContext emfDataBindingContext) {
        final Composite compo = new Composite(parent, SWT.NONE);
        compo.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).spacing(10, 5).create());
        compo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Button radioButtonNone = createRadioButtonNone(compo);
        final Button radioButtonContract = createRadioButtonContract(compo);
        final Button radioButtonInternal = createRadioButtonInternal(compo);
        final Button radioButtonExternal = createRadioButtonExternal(compo);

        final SelectObservableValue documentTypeObservableValue = new SelectObservableValue(DocumentType.class);
        documentTypeObservableValue.addOption(DocumentType.NONE, SWTObservables.observeSelection(radioButtonNone));
        documentTypeObservableValue.addOption(DocumentType.CONTRACT, SWTObservables.observeSelection(radioButtonContract));
        documentTypeObservableValue.addOption(DocumentType.EXTERNAL, SWTObservables.observeSelection(radioButtonExternal));
        documentTypeObservableValue.addOption(DocumentType.INTERNAL, SWTObservables.observeSelection(radioButtonInternal));

        final IObservableValue documentTypeObservable = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE);
        emfDataBindingContext.bindValue(
                documentTypeObservableValue,
                documentTypeObservable);
        documentTypeObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate(emfDataBindingContext);
            }
        });
    }

    private Button createRadioButtonContract(final Composite compo) {
        final Button radioButtonContract = new Button(compo, SWT.RADIO);
        radioButtonContract.setText(Messages.initialValueButtonContract);
        radioButtonContract.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonContract.getSelection()) {
                    updateStack(DocumentType.CONTRACT);
                    updateMimeTypeEnabled(false);
                }
            }

        });
        return radioButtonContract;
    }

    private Button createRadioButtonExternal(final Composite compo) {
        final Button radioButtonExternal = new Button(compo, SWT.RADIO);
        radioButtonExternal.setText(Messages.initialValueButtonExternal);
        final ControlDecoration infoExternal = new ControlDecoration(radioButtonExternal, SWT.RIGHT);
        infoExternal.show();
        infoExternal.setImage(Pics.getImage(PicsConstants.hint));
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
        infoBonita.setImage(Pics.getImage(PicsConstants.hint));
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
        radioButtonNone.setLayoutData(GridDataFactory.swtDefaults().create());
        radioButtonNone.setText(Messages.initialValueButtonNone);
        radioButtonNone.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (radioButtonNone.getSelection()) {
                    updateStack(DocumentType.NONE);
                    updateMimeTypeEnabled(false);
                }
            }

        });
        return radioButtonNone;
    }

    protected void updateStack(final DocumentType docType) {
        if (docType.equals(DocumentType.NONE)) {
            stack.topControl = noneCompo;
            multipleStack.topControl = multiNoneCompo;
        } else if (docType.equals(DocumentType.EXTERNAL)) {
            stack.topControl = externalCompo;
            multipleStack.topControl = scriptComposite;
        } else if (docType.equals(DocumentType.INTERNAL)) {
            stack.topControl = internalCompo;
            multipleStack.topControl = scriptComposite;
        } else if (docType.equals(DocumentType.CONTRACT)) {
            stack.topControl = singleContractComposite;
            multipleStack.topControl = multipleContractComposite;
        }
        stackedComposite.layout();
        multipleStackedComposite.layout();
    }

    protected void updateMimeTypeStack(final String type) {
        if (type.equals(LINK)) {
            mimeStack.topControl = manageLinkComposite;
        } else if (type.equals(FIELD)) {
            mimeStack.topControl = mimeTypeComposition;
        }
        mimeCompo.layout();
    }

    protected void updateSingleMultipleStack(final boolean isMultiple) {
        if (isMultiple) {
            singleMultiplestack.topControl = multipleComposite;
        } else {
            singleMultiplestack.topControl = singleComposite;
        }
        updateMimeTypeEnabled(isMimetypeEditable(isMultiple, document));
        multipleComposite.getParent().layout();
    }

    protected boolean isMimetypeEditable(final boolean isMultiple, final Document document) {
        if (isMultiple) {
            return false;
        } else {
            final DocumentType documentType = document.getDocumentType();
            return !(DocumentType.NONE.equals(documentType)
            || DocumentType.CONTRACT.equals(documentType));
        }
    }

    private void updateMimeTypeEnabled(final boolean isEnabled) {
        mimeCompo.setEnabled(isEnabled);
        manageLink.setEnabled(isEnabled);
        hideLink.setEnabled(isEnabled);
        documentMimeTypeViewer.getControl().setEnabled(isEnabled);
    }

}
