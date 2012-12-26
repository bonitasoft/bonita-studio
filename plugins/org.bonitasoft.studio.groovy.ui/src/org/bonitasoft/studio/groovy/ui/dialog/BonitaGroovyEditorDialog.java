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

package org.bonitasoft.studio.groovy.ui.dialog;

import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.jface.BonitaSashForm;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.bonitasoft.studio.groovy.library.IFunctionsCategories;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.groovy.ui.wizard.OpenScriptWizard;
import org.bonitasoft.studio.groovy.ui.wizard.ProcessVariableContentProvider;
import org.bonitasoft.studio.groovy.ui.wizard.ProcessVariableLabelProvider;
import org.bonitasoft.studio.groovy.ui.wizard.SaveScriptWizard;
import org.codehaus.groovy.ast.FieldNode;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaGroovyEditorDialog extends Dialog {

    private static final String GROOVY_DOC_LINK = "<a href=\"http://groovy.codehaus.org/Getting+Started+Guide\">"+Messages.groovyDocumentationLink+"</a>"; //$NON-NLS-1$ //$NON-NLS-2$

    private static final String BONITA_GROOVY_EXPRESSION_START = "${"; //$NON-NLS-1$

    protected static final String GROOVY_BROWSER_ID = "org.bonitasoft.studio.groovy.browser"; //$NON-NLS-1$
    private FileEditorInput input = null ;
    private SourceViewer viewer;
    private String expression;
    private IDocument document;
    private Composite evaluationComposite;
    private GridData gd;
    private ToolBar operatorGroupComposite;
    private Composite functionsGroupComposite;
    private IFunctionsCategories categories ;
    private FilteredTree functionsList;
    private Text documenationText;
    private ListViewer categoriesList;
    private IFile groovyFile;
    private EObject context;
    private GroovyViewer groovyViewer;
    private ComboViewer dataCombo;
    private List<ScriptVariable> nodes;

    private IRepositoryFileStore currentFile;

    private GroovyFileStore tmpGroovyFileStore;


    public BonitaGroovyEditorDialog(Shell parentShell, IFile groovyFile) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.CLOSE | SWT.APPLICATION_MODAL | SWT.RESIZE);
        setExpression(""); //$NON-NLS-1$
        categories = FunctionsRepositoryFactory.getFunctionCatgories();
        if(groovyFile == null){
            final ProvidedGroovyRepositoryStore store = (ProvidedGroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProvidedGroovyRepositoryStore.class);
            tmpGroovyFileStore = store.createRepositoryFileStore("script"+System.currentTimeMillis()+".groovy");
            tmpGroovyFileStore.save("");
            this.groovyFile = tmpGroovyFileStore.getResource() ;
        }else{
            this.groovyFile = groovyFile;
        }
        GroovyDocumentUtil.refreshUserLibrary();

    }


    public BonitaGroovyEditorDialog(Shell parentShell,String groovyExpression,IFile groovyFile) {
        this(parentShell,groovyFile);
        setExpression(toDocumentExpression(groovyExpression));
    }

    public BonitaGroovyEditorDialog(Shell shell, String initialValue, EObject context) {
        this(shell,initialValue,(IFile)null);
        this.context = context;
    }

    public BonitaGroovyEditorDialog(Shell shell, String initialValue, List<ScriptVariable> nodes) {
        this(shell,initialValue,(IFile)null);
        this.nodes = nodes;
    }


    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.groovyEditorTitle);

    }

    private String toDocumentExpression(String groovyExpression) {
        if(groovyExpression.startsWith(BONITA_GROOVY_EXPRESSION_START)) {
            return groovyExpression.substring(2,groovyExpression.length()-1);
        } else {
            return groovyExpression ;
        }
    }

    @Override
    protected Control createContents(Composite parent) {
        // create the top level composite for the dialog
        parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        layout.numColumns = 2;
        layout.marginRight = 5 ;
        composite.setLayout(layout);

        applyDialogFont(composite);
        // initialize the dialog units
        initializeDialogUnits(composite);

        //create the data chooser area
        createDataChooserArea(composite);


        //create the operators area
        createOperatorArea(composite);

        SashForm verticalMainPanel = new BonitaSashForm(composite, SWT.VERTICAL);
        verticalMainPanel.setLayout(new GridLayout(1, false));


        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2 ;
        verticalMainPanel.setLayoutData(gd);

        SashForm editorPanel = new BonitaSashForm(verticalMainPanel, SWT.HORIZONTAL);

        editorPanel.setLayout(new GridLayout(2, false));

        // create the editor area
        dialogArea = createDialogArea(editorPanel);
        //create the test area with variable list and evaluate result
        createTestArea(editorPanel);
        editorPanel.setWeights(new int[]{3,1});

        createFunctionsArea(verticalMainPanel);
        verticalMainPanel.setWeights(new int[]{2,1});

        createSaveButtonBar(composite);

        if (context == null && nodes == null) {
            dataCombo.getCombo().add(Messages.noProcessVariableAvailable);
            dataCombo.getCombo().setText(Messages.noProcessVariableAvailable);
            dataCombo.getCombo().setEnabled(false);
        }else if(nodes != null){
            dataCombo.setInput(nodes);
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
        }else{
            dataCombo.setInput(groovyViewer.getFieldNodes());
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
        }

        buttonBar = createButtonBar(composite);
        return composite;
    }


    protected void createDataChooserArea(Composite composite) {

        Composite combosComposite = new Composite(composite, SWT.NONE);
        combosComposite.setLayout(new GridLayout(4,false));
        gd = new GridData() ;
        gd.horizontalSpan = 2 ;
        gd.horizontalIndent = 10 ;
        combosComposite.setLayoutData(gd);

        Label processVariableLabel = new Label(combosComposite,SWT.NONE);
        processVariableLabel.setText(Messages.processVariableLabel);

        dataCombo = new ComboViewer(combosComposite, SWT.READ_ONLY);
        dataCombo.setLabelProvider(new ProcessVariableLabelProvider());
        dataCombo.setContentProvider(new ProcessVariableContentProvider());



        Label bonitaVariableLabel = new Label(combosComposite,SWT.NONE);
        bonitaVariableLabel.setText(Messages.bonitaKeyWord);
        gd = new GridData() ;
        gd.horizontalIndent = 10 ;
        bonitaVariableLabel.setLayoutData(gd);

        final ComboViewer bonitaDataCombo = new ComboViewer(combosComposite, SWT.READ_ONLY);


        bonitaDataCombo.setLabelProvider(new ProcessVariableLabelProvider());
        bonitaDataCombo.setContentProvider(new ProcessVariableContentProvider());

        bonitaDataCombo.setInput(GroovyUtil.getBonitaVariables(context));
        bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));

        bonitaDataCombo.getCombo().addListener(SWT.Modify,new Listener() {

            @Override
            public void handleEvent(Event event) {
                Object selected = ((IStructuredSelection)bonitaDataCombo.getSelection()).getFirstElement();
                if (selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                    return;
                }

                FieldNode f = (FieldNode) selected;
                StyledText control = viewer.getTextWidget();
                try{
                    int offset = control.getCaretOffset();
                    String before = document.get(0, offset);
                    if(offset == document.get().length()){
                        document.set(before+f.getName());
                    }else{
                        String after = document.get().substring(offset, document.get().length());
                        document.set(before+f.getName()+after);
                    }

                    control.setCaretOffset(offset+f.getName().length());
                    control.setFocus();

                    bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                }catch (Exception e1) {
                    GroovyPlugin.logError(e1);
                }
            }
        });


        dataCombo.getCombo().addListener(SWT.Modify,new Listener() {

            @Override
            public void handleEvent(Event event) {
                Object selected = ((IStructuredSelection)dataCombo.getSelection()).getFirstElement();
                if(selected != null){
                    if (selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                        return;
                    }

                    ScriptVariable f = (ScriptVariable) selected;
                    StyledText control = viewer.getTextWidget();
                    try{
                        int offset = control.getCaretOffset();
                        String before = document.get(0, offset);
                        if(offset == document.get().length()){
                            document.set(before+f.getName());
                        }else{
                            String after = document.get().substring(offset, document.get().length());
                            document.set(before+f.getName()+after);
                        }

                        control.setCaretOffset(offset+f.getName().length());
                        control.setFocus();

                        dataCombo.getCombo().setText(""); //$NON-NLS-1$
                        dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                    }catch (Exception e1) {
                        GroovyPlugin.logError(e1);
                    }
                }
            }
        });

    }


    protected void createSaveButtonBar(final Composite composite) {

        Composite buttonComposite = new Composite(composite, SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        buttonComposite.setLayoutData(gd);

        RowLayout rl = new RowLayout(SWT.HORIZONTAL);
        rl.spacing = 5 ;
        rl.marginLeft = 10 ;
        rl.center = true ;
        buttonComposite.setLayout(rl);

        RowData rd = new RowData(110, 25);

        Button saveButton = new Button(buttonComposite,SWT.FLAT);
        saveButton.setText(Messages.saveButtonLabel);
        saveButton.setLayoutData(rd);
        saveButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                String defaultName = getInputFIleStore() != null ? getInputFIleStore().getName() : null ;

                SaveScriptWizard wizard = new SaveScriptWizard(defaultName,document);
                CustomWizardDialog dialog = new CustomWizardDialog(Display.getDefault().getActiveShell(),wizard, Messages.saveButtonLabel);
                if(Dialog.OK == dialog.open()){
                    GroovyDocumentUtil.refreshUserLibrary() ;
                    categoriesList.refresh();
                    functionsList.getViewer().refresh();
                    documenationText.update();
                    currentFile = wizard.getFile() ;
                }

            }
        });




        Button loadButton = new Button(buttonComposite,SWT.FLAT);
        loadButton.setText(Messages.loadButtonLabel);
        loadButton.setLayoutData(rd);
        loadButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                OpenScriptWizard wizard = new OpenScriptWizard(false, currentFile);
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                CustomWizardDialog dialog = new CustomWizardDialog(shell,wizard, Messages.openScriptLabel);
                if(	dialog.open() == Dialog.OK && MessageDialog.openQuestion(shell, Messages.warningLooseCurrentWorkTitle, Messages.warningLooseCurrentWorkMessage)){
                    try {
                        GroovyFileStore newInputFile = (GroovyFileStore) wizard.getFile();
                        input = new FileEditorInput(newInputFile.getResource());
                        groovyViewer.setInput(input) ;
                        groovyViewer.getDocument().set(newInputFile.getContent());
                        currentFile = wizard.getFile();
                    } catch (Exception e1) {
                        GroovyPlugin.logError(e1);
                    }
                }
                categories = FunctionsRepositoryFactory.getFunctionCatgories();
                categoriesList.setInput(categories);
                IFunctionCategory cat =(IFunctionCategory) ((IStructuredSelection)categoriesList.getSelection()).getFirstElement();
                functionsList.getViewer().setInput(cat);


            }
        });

        Link docLinkText = new Link(buttonComposite, SWT.NONE);
        docLinkText.setText(GROOVY_DOC_LINK);
        docLinkText.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {


                try {
                    IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(GROOVY_BROWSER_ID);
                    browser.openURL(new URL(event.text));
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }


            }
        });

    }


    protected IRepositoryFileStore getInputFIleStore() {
        return currentFile;
    }


    protected void createFunctionsArea(Composite composite) {
        functionsGroupComposite = new Composite(composite,SWT.NONE);
        //functionsGroupComposite.setText(Messages.functionsTitleLabel);
        gd = new GridData(SWT.FILL, SWT.END, true, true);
        gd.verticalIndent = 5 ;
        gd.horizontalIndent = 10 ;
        gd.heightHint = 160 ;
        gd.horizontalSpan = 2 ;
        functionsGroupComposite.setLayoutData(gd);
        GridLayout rl = new GridLayout(3,false);
        rl.marginLeft = 5 ;
        functionsGroupComposite.setLayout(rl);

        createFunctionCategories(functionsGroupComposite);
        createFunctionsList(functionsGroupComposite);
        createFunctionDocumentaion(functionsGroupComposite);
    }


    private void createFunctionCategories(Composite parent) {
        Composite catComposite = new Composite(parent, SWT.WRAP);
        catComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
        catComposite.setLayout(new GridLayout(1, true));

        Label catTitle = new Label(catComposite, SWT.NONE);
        catTitle.setText(Messages.categoriesTitle);

        categoriesList = new ListViewer(catComposite, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
        categoriesList.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

        categoriesList.setLabelProvider(new CategoryLabelProvider());
        categoriesList.setContentProvider(new FunctionCategoriesProvider());
        categoriesList.setInput(categories);
        categoriesList.getList().setSelection(0);



        categoriesList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IFunctionCategory cat =(IFunctionCategory) ((IStructuredSelection)event.getSelection()).getFirstElement();
                functionsList.getViewer().setInput(cat);
            }
        });
    }


    private void createFunctionsList(Composite parent) {
        Composite funcComposite = new Composite(parent, SWT.WRAP);
        funcComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        funcComposite.setLayout(new GridLayout(1, true));

        Label funcTitle = new Label(funcComposite, SWT.NONE);
        funcTitle.setText(Messages.functionTitle);

        functionsList = new FilteredTree(funcComposite,  SWT.BORDER | SWT.V_SCROLL, new PatternFilter(), true) ;
        functionsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        functionsList.getViewer().setLabelProvider(new FunctionLabeProvider());
        functionsList.getViewer().setContentProvider(new FunctionContentProvider());
        IFunctionCategory cat =(IFunctionCategory) ((IStructuredSelection)categoriesList.getSelection()).getFirstElement();
        functionsList.getViewer().setInput(cat);

        functionsList.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IFunction f = (IFunction) ((IStructuredSelection)event.getSelection()).getFirstElement();
                if(documenationText != null && !documenationText.isDisposed() && f != null && f.getDocumentation() != null) {
                    documenationText.setText(f.getDocumentation());
                }


            }
        });

        functionsList.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                try {
                    IFunction f = (IFunction) ((IStructuredSelection)event.getSelection()).getFirstElement();

                    if(f != null){
                        int offset = viewer.getTextWidget().getCaretOffset();
                        String before;

                        before = document.get(0, offset);
                        String toInsert = "";
                        if(f.isStatic()) {
                            toInsert += f.getOwner();
                        } else {
                            toInsert = "new " + f.getOwner() + "()";
                        }
                        toInsert += "."+f.getName();
                        if (f.getParametersCount() > 0) {
                            toInsert += "(" + f.getParameters() + ")";
                        } else {
                            toInsert += "()";
                        }

                        if(offset == document.get().length()){
                            document.set(before + toInsert);
                        } else {
                            String after = document.get().substring(offset, document.get().length());
                            document.set(before + toInsert + after);
                        }

                        viewer.getTextWidget().setCaretOffset(offset + toInsert.length());

                        viewer.getTextWidget().setFocus();
                    }
                } catch (BadLocationException e) {
                    GroovyPlugin.logError(e);
                }

            }
        });

    }


    private void createFunctionDocumentaion(Composite parent) {
        Composite docComposite = new Composite(parent, SWT.WRAP);
        docComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        docComposite.setLayout(new GridLayout(1, true));

        Label docTitle = new Label(docComposite, SWT.NONE);
        docTitle.setText(Messages.functionDocTitle);

        documenationText = new Text(docComposite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

        documenationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        documenationText.setBackground(ColorConstants.white);
    }


    protected void createOperatorArea(Composite composite) {

        Composite toolbarComposite = new Composite(composite, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        gl.marginBottom = 5 ;
        toolbarComposite.setLayout(gl);
        gd = new GridData();
        gd.horizontalSpan = 2 ;
        gd.horizontalIndent = 10 ;
        //	gd.grabExcessVerticalSpace = true ;
        //gd.verticalAlignment = SWT.CENTER ;

        //	gd.grabExcessHorizontalSpace = false ;
        toolbarComposite.setLayoutData(gd);

        operatorGroupComposite = new ToolBar(toolbarComposite,SWT.HORIZONTAL);
        gd = new GridData(GridData.FILL);
        operatorGroupComposite.setLayoutData(gd);

        Button clearButton = new Button(toolbarComposite, SWT.FLAT);
        gd = new GridData(GridData.END);
        gd.horizontalAlignment = GridData.END ;
        clearButton.setText(Messages.clearLabel);
        clearButton.setLayoutData(gd);
        clearButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(MessageDialog.openConfirm(getShell(), Messages.clearContentTitle, Messages.clearContentMessage))
                {
                    document.set(""); //$NON-NLS-1$
                }
            }
        });

    }

    /**
     * Workaround to avoid NPE if the Editor is opened but any diagram is
     */
    @Override
    public int open() {
        return super.open() ;
    }

    private void addOperators(final ToolBar parent) {


        StyledText control = viewer.getTextWidget();

        ToolItem plusOperatorButton = new ToolItem(parent, SWT.FLAT);
        plusOperatorButton.setText("+"); //$NON-NLS-1$
        plusOperatorButton.addSelectionListener(new OperatorSelectionAdapter("+", document,control ));


        ToolItem minusOperatorButton = new ToolItem(parent, SWT.FLAT);
        minusOperatorButton.setText("-"); //$NON-NLS-1$
        minusOperatorButton.addSelectionListener(new OperatorSelectionAdapter("-", document, control));

        ToolItem multiplyOperatorButton = new ToolItem(parent, SWT.FLAT);
        multiplyOperatorButton.setText("*"); //$NON-NLS-1$
        multiplyOperatorButton.setToolTipText(Messages.multiplyTooltip);
        multiplyOperatorButton.addSelectionListener(new OperatorSelectionAdapter("*", document,control));

        ToolItem divideOperatorButton = new ToolItem(parent, SWT.FLAT);
        divideOperatorButton.setText("/"); //$NON-NLS-1$
        divideOperatorButton.setToolTipText(Messages.divideTooltip);
        divideOperatorButton.addSelectionListener(new OperatorSelectionAdapter("/", document, control));

        new ToolItem(parent, SWT.SEPARATOR);

        ToolItem equalsOperatorButton = new ToolItem(parent, SWT.FLAT);
        equalsOperatorButton.setText("=="); //$NON-NLS-1$
        equalsOperatorButton.setToolTipText(Messages.equalsTooltip);
        equalsOperatorButton.addSelectionListener(new OperatorSelectionAdapter("==", document, control));

        ToolItem lessOperatorButton = new ToolItem(parent, SWT.FLAT);
        lessOperatorButton.setText("<"); //$NON-NLS-1$
        lessOperatorButton.setToolTipText(Messages.lessThanTooltip);

        lessOperatorButton.addSelectionListener(new OperatorSelectionAdapter("<", document, control));

        ToolItem lessOrEqualsOperatorButton = new ToolItem(parent, SWT.FLAT);
        lessOrEqualsOperatorButton.setText("<="); //$NON-NLS-1$
        lessOrEqualsOperatorButton.setToolTipText(Messages.lessThanOrEqualTooltip);

        lessOrEqualsOperatorButton.addSelectionListener(new OperatorSelectionAdapter("<=", document, control)); //$NON-NLS-1$

        ToolItem notEqualsOperatorButton = new ToolItem(parent, SWT.FLAT);
        notEqualsOperatorButton.setText("!="); //$NON-NLS-1$
        notEqualsOperatorButton.setToolTipText(Messages.notEqualTooltip);

        notEqualsOperatorButton.addSelectionListener(new OperatorSelectionAdapter("!=", document, control)); //$NON-NLS-1$

        ToolItem greaterOrEqualsOperatorButton = new ToolItem(parent, SWT.FLAT);
        greaterOrEqualsOperatorButton.setText(">="); //$NON-NLS-1$
        greaterOrEqualsOperatorButton.setToolTipText(Messages.greaterThanOrEqualsTooltip);

        greaterOrEqualsOperatorButton.addSelectionListener(new OperatorSelectionAdapter(">=", document, control)); //$NON-NLS-1$

        ToolItem greaterOperatorButton = new ToolItem(parent, SWT.FLAT);
        greaterOperatorButton.setText(">"); //$NON-NLS-1$
        greaterOperatorButton.setToolTipText(Messages.greaterThanTooltip);

        greaterOperatorButton.addSelectionListener(new OperatorSelectionAdapter(">", document, control)); //$NON-NLS-1$

        new ToolItem(parent, SWT.SEPARATOR);

        ToolItem andOperatorButton = new ToolItem(parent, SWT.FLAT);
        andOperatorButton.setText("And"); //$NON-NLS-1$

        andOperatorButton.addSelectionListener(new OperatorSelectionAdapter("&&", document, control)); //$NON-NLS-1$

        ToolItem orOperatorButton = new ToolItem(parent, SWT.FLAT);
        orOperatorButton.setText("Or"); //$NON-NLS-1$
        orOperatorButton.addSelectionListener(new OperatorSelectionAdapter("||", document, control)); //$NON-NLS-1$

        ToolItem notOperatorButton = new ToolItem(parent, SWT.FLAT);
        notOperatorButton.setText("Not"); //$NON-NLS-1$

        notOperatorButton.addSelectionListener(new OperatorSelectionAdapter("!", document, control)); //$NON-NLS-1$

        ToolItem trueOperatorButton = new ToolItem(parent, SWT.FLAT);
        trueOperatorButton.setText("True"); //$NON-NLS-1$
        trueOperatorButton.addSelectionListener(new OperatorSelectionAdapter("true", document, control)); //$NON-NLS-1$

        ToolItem falseOperatorButton = new ToolItem(parent, SWT.FLAT);
        falseOperatorButton.setText("False"); //$NON-NLS-1$
        falseOperatorButton.addSelectionListener(new OperatorSelectionAdapter("false", document, control)); //$NON-NLS-1$

        new ToolItem(parent, SWT.SEPARATOR);

        ToolItem parenthesisOpenOperatorButton = new ToolItem(parent, SWT.FLAT);
        parenthesisOpenOperatorButton.setText("(");
        parenthesisOpenOperatorButton.addSelectionListener(new OperatorSelectionAdapter("(", document, control));

        ToolItem parenthesisCloseOperatorButton = new ToolItem(parent, SWT.FLAT);
        parenthesisCloseOperatorButton.setText(")");
        parenthesisCloseOperatorButton.addSelectionListener(new OperatorSelectionAdapter(")", document, control));



    }


    protected void createTestArea(Composite composite) {
        evaluationComposite = new Composite(composite, SWT.NONE) ;
        evaluationComposite.setLayout(new FillLayout());
        gd = new GridData();
        gd.verticalAlignment = GridData.FILL ;
        gd.horizontalAlignment = GridData.FILL ;
        evaluationComposite.setLayoutData( new GridData(SWT.FILL, SWT.FILL, true, true));
        new TestVariableGroup(evaluationComposite,groovyViewer.getFieldNodes(),groovyViewer.getGroovyCompilationUnit(),String.class.getName()) ;
    }



    @Override
    protected Control createDialogArea(Composite parent) {

        final Composite composite = (Composite) super.createDialogArea(parent) ;
        composite.setLayout(new FillLayout());

        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        composite.setLayoutData(gd);

        initializeInput();

        groovyViewer = new GroovyViewer(composite, input) ;
        if(nodes != null){
            groovyViewer.setFieldNodes(nodes) ;
        }else if(context != null){
            groovyViewer.setContext(context,null) ;
        }


        viewer = groovyViewer.getSourceViewer() ;
        document = groovyViewer.getDocument() ;

        addOperators(operatorGroupComposite);

        return composite;

    }


    private FileEditorInput initializeInput(){
        if(input == null){
            try {
                groovyFile.setCharset("UTF-8", new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            input = new FileEditorInput(groovyFile);
        }
        return input;
    }

    @Override
    public boolean close() {
        if(tmpGroovyFileStore != null){
            tmpGroovyFileStore.delete();
        }
        groovyViewer.dispose() ;
        return super.close() ;
    }

    /**
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    @Override
    protected void okPressed() {
        super.okPressed();
        String documentExpression = document.get();
        if(documentExpression.length() == 0){
            setExpression("");
        }else{
            setExpression(documentExpression.trim());
        }

        systemExitWarning(documentExpression);
    }

    private void systemExitWarning(String content) {
        if(content.contains("System.exit")){
            Shell shell = null;
            if(PlatformUI.getWorkbench() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null){
                shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            } else {
                /* Have a shell in any conditions but will not be correctly positioned */
                final Display display = PlatformUI.getWorkbench().getDisplay();
                shell = new Shell(display);
            }
            MessageDialog.openWarning(shell, Messages.warning, Messages.warningMessageSystemExit);
        }
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        if(expression != null){
            expression = expression.replaceAll("\r\n", String.valueOf(SWT.CR)) ;
            expression = expression.replace('\n', SWT.CR) ;
        }
        this.expression = expression ;
    }

    /**
     * Prevent the wizard to close accidentally by pressing escape (or the red cross)
     * @see org.eclipse.jface.window.Window#canHandleShellCloseEvent()
     */
    @Override
    protected boolean canHandleShellCloseEvent() {
        Boolean close = MessageDialog.openQuestion(getShell(), org.bonitasoft.studio.common.Messages.handleShellCloseEventTitle, org.bonitasoft.studio.common.Messages.handleShellCloseEventMessage);
        if(close){
            return super.canHandleShellCloseEvent();
        } else {
            return false;
        }
    }

    @Override
    protected void constrainShellSize() {
        super.constrainShellSize();
        getShell().setMinimumSize(getInitialSize());
    }
}
