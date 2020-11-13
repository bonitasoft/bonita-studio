/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.providers;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.jface.databinding.observables.DocumentObservable;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.SelectDependencyDialog;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.contentassist.BonitaConstantsTypeLookup;
import org.bonitasoft.studio.groovy.ui.dialog.GroovyEditorDocumentationDialogTray;
import org.bonitasoft.studio.groovy.ui.dialog.TestGroovyScriptDialog;
import org.bonitasoft.studio.groovy.ui.filter.ScriptProposalViewerFilter;
import org.bonitasoft.studio.groovy.ui.job.ComputeScriptDependenciesJob;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.ScriptExpressionProposalViewer;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.DescriptionProvider;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptExpressionContext;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

import com.google.common.collect.Lists;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptExpressionEditor extends SelectionAwareExpressionEditor {

    private static final String LIGHT_MODE_COLOR = "#000";
    private static final String LIGHT_MODE_BACKGROUND_COLOR = "#fff";
    private static final String DARK_MODE_NO_DESC_COLOR = "rgb(186, 186, 186)";
    private static final String DARK_MODE_BACKGROUND_COLOR = "#2F2F2F";
    private static final String DARK_MODE_COLOR = "#fff";
    private static final String LIGHT_MODE_NO_DESC_COLOR = "rgb(85, 85, 85)";

    private static URL EXPRESSION_AND_SCRIPTS_URL;
    static {
        try {
            EXPRESSION_AND_SCRIPTS_URL = new URL(
                    String.format(
                            "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=678&bos_redirect_product=bos&bos_redirect_major_version=%s",
                            ProductVersion.majorVersion()));
        } catch (MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected Expression inputExpression;

    protected SourceViewer sourceViewer;

    protected List<ScriptVariable> nodes;

    private TableViewer dependenciesViewer;

    private final ComposedAdapterFactory adapterFactory;

    private final AdapterFactoryLabelProvider adapterLabelProvider;

    private Button automaticResolutionButton;

    private Button addDependencyButton;

    private Button removeDependencyButton;

    private ComputeScriptDependenciesJob dependencyJob;

    protected GroovyViewer groovyViewer;

    protected EObject context;

    private IDocument document;

    private Section depndencySection;

    private ScriptExpressionProposalViewer proposalsViewer;

    private Button testButton;

    private Link writeOperationWarning;

    private List<ScriptVariable> input;

    private List<ScriptProposal> proposalToFilter = new ArrayList<>();

    private ScriptExpressionContext scriptExpressionContext;
    private DropTarget dropTarget;
    private RepositoryAccessor repositoryAccessor;

    public GroovyScriptExpressionEditor() {
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        SashForm sashForm = new SashForm(mainComposite, SWT.HORIZONTAL);
        sashForm.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        sashForm.setLayout(GridLayoutFactory.fillDefaults().create());
        sashForm.setSashWidth(5);

        createProposalComposite(sashForm, ctx);
        createGroovyEditor(sashForm, true);

        sashForm.setWeights(new int[] { 2, 5 });

        createButtonBar(mainComposite);
        createDependencyViewer(mainComposite);

        return mainComposite;
    }

    protected void createProposalComposite(Composite parent, EMFDataBindingContext ctx) {
        SashForm proposalSash = new SashForm(parent, SWT.VERTICAL);
        proposalSash.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        proposalSash.setLayout(GridLayoutFactory.fillDefaults().create());
        proposalSash.setSashWidth(5);

        Composite proposalTreeComposite = new Composite(proposalSash, SWT.NONE);
        proposalTreeComposite
                .setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        proposalTreeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbarComposite(proposalTreeComposite);
        proposalsViewer = new ScriptExpressionProposalViewer(proposalTreeComposite, SWT.BORDER);

        proposalsViewer.addFilter(new ScriptProposalViewerFilter(proposalToFilter));
        IViewerObservableValue<Object> selectionObservable = ViewerProperties.singleSelection().observe(proposalsViewer);

        proposalsViewer.addDoubleClickListener(e -> {
            if (selectionObservable.getValue() instanceof Category) {
                proposalsViewer.setExpandedState(selectionObservable.getValue(),
                        !proposalsViewer.getExpandedState(selectionObservable.getValue()));
            } else if (selectionObservable.getValue() instanceof ScriptProposal) {
                ScriptProposal proposal = (ScriptProposal) selectionObservable.getValue();
                proposal.apply(groovyViewer.getEditor());
            }
        });
        createDescriptionComposite(ctx, proposalSash, selectionObservable);

        proposalSash.setWeights(new int[] { 5, 2 });
    }

    private void createDescriptionComposite(EMFDataBindingContext ctx, SashForm proposalSash,
            IViewerObservableValue<Object> selectionObservable) {
        Composite descriptionComposite = new Composite(proposalSash, SWT.BORDER);
        descriptionComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        descriptionComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        descriptionComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.TABLE_BACKGROUND_COLOR);

        Browser descriptionBrowser = new Browser(descriptionComposite, SWT.WRAP);
        descriptionBrowser.setLayoutData(GridDataFactory
                .fillDefaults()
                .grab(true, true)
                .create());
        descriptionBrowser.setBackground(proposalsViewer.getTree().getBackground());
        descriptionBrowser.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.TABLE_BACKGROUND_COLOR);
        descriptionBrowser.setText(noDecription());
        selectionObservable.addValueChangeListener(e -> {
            Object selection = e.diff.getNewValue();
            if (hasDescription(selection)) {
                descriptionBrowser.setText(htmlFormat(((DescriptionProvider) selection).getDescription()), true);
            } else {
                descriptionBrowser.setText(noDecription());
            }
            descriptionBrowser.getParent().layout(true, true);
        });
    }

    private boolean hasDescription(Object selection) {
        return selection instanceof DescriptionProvider
                && ((DescriptionProvider) selection).getDescription() != null
                && !((DescriptionProvider) selection).getDescription().isEmpty();
    }

    private String htmlFormat(String content) {
        return String.format(
                "<body style=\"background-color:%s;color: %s;\"><span style=\"font-size: small;\">%s</span></body>",
                getBackgroundColor(),
                getColor(),
                content);
    }

    private String getBackgroundColor() {
        return PreferenceUtil.isDarkTheme() ? DARK_MODE_BACKGROUND_COLOR : LIGHT_MODE_BACKGROUND_COLOR;
    }

    private String getColor() {
        return PreferenceUtil.isDarkTheme() ? DARK_MODE_COLOR : LIGHT_MODE_COLOR;
    }

    private String getNoDescriptionColor() {
        return PreferenceUtil.isDarkTheme() ? DARK_MODE_NO_DESC_COLOR : LIGHT_MODE_NO_DESC_COLOR;
    }

    private String noDecription() {
        return String.format(
                "<body style=\"background-color:%s;color: %s;\"><span style=\"font-size: small;font-style: italic;\">%s</span></body>",
                getBackgroundColor(),
                getNoDescriptionColor(),
                Messages.noDescription);
    }

    private void createToolbarComposite(Composite parent) {
        Composite toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createToolbar(toolbarComposite);
        createSearch(toolbarComposite);
    }

    private void createSearch(Composite parent) {
        TextWidget searchWidget = new SearchWidget.Builder()
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .withPlaceholder(Messages.search)
                .createIn(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                proposalToFilter.clear();
                filterCategories(search, scriptExpressionContext.getCategories());
                proposalsViewer.refresh();
                if (!search.isEmpty()) {
                    proposalsViewer.expandAll();
                }
            });
        });
    }

    private void filterCategories(String search, List<Category> categories) {
        categories.forEach(category -> {
            if (!category.getSubcategories().isEmpty()) {
                filterCategories(search, category.getSubcategories());
            }
            filterProposals(search, category.getProposals());
        });
    }

    /**
     * Iterate over the the proposals and their children (recursively).
     * - If a proposal match the search, then it's not added to the filter list and we don't iterate over its children.
     * - If a proposal doesn't match the search, then it's added to the filter list if and only if none of its children match
     * the search.
     * 
     * @return true if one of the proposals matchs the search.
     */
    private boolean filterProposals(String search, List<ScriptProposal> proposals) {
        boolean anyMatch = false;
        for (ScriptProposal proposal : proposals) {
            if (proposal.getName().toLowerCase().contains(search)
                    || filterProposals(search, proposal.getChildren())) {
                anyMatch = true;
            } else {
                proposalToFilter.add(proposal);
            }
        }
        return anyMatch;
    }

    private void createToolbar(Composite toolbarComposite) {
        ToolBar toolBar = new ToolBar(toolbarComposite, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);

        ToolItem expandItem = new ToolItem(toolBar, SWT.PUSH);
        expandItem.setImage(Pics.getImage(PicsConstants.expandAll));
        expandItem.setToolTipText(org.bonitasoft.studio.businessobject.i18n.Messages.expandAll);
        expandItem.addListener(SWT.Selection, e -> proposalsViewer.expandAll());

        ToolItem collapseItem = new ToolItem(toolBar, SWT.PUSH);
        collapseItem.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseItem.setToolTipText(org.bonitasoft.studio.businessobject.i18n.Messages.collapseAll);
        collapseItem.addListener(SWT.Selection, e -> proposalsViewer.collapseAll());
    }

    protected void createDependencyViewer(final Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        automaticResolutionButton = new Button(mainComposite, SWT.CHECK);
        automaticResolutionButton.setText(Messages.automaticResolution);
        automaticResolutionButton.setLayoutData(GridDataFactory.fillDefaults().create());
        automaticResolutionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (automaticResolutionButton.getSelection()) {
                    removeDependencyButton.setEnabled(false);
                }
                depndencySection.setExpanded(!automaticResolutionButton.getSelection());
            }
        });

        ControlDecoration controlDecoration = new ControlDecoration(automaticResolutionButton, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.automaticResolutionHint);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.show();

        depndencySection = new Section(mainComposite, Section.NO_TITLE);
        depndencySection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        depndencySection.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        depndencySection.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);

        final Composite dependenciesComposite = new Composite(depndencySection, SWT.NONE);
        dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        dependenciesViewer = new TableViewer(dependenciesComposite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        dependenciesViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 40).create());
        dependenciesViewer.setContentProvider(new ArrayContentProvider());
        dependenciesViewer.setLabelProvider(new DependencyLabelProvider(adapterLabelProvider));
        dependenciesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty() && !automaticResolutionButton.getSelection()) {
                    removeDependencyButton.setEnabled(true);
                }

            }
        });

        final Composite addRemoveComposite = new Composite(dependenciesComposite, SWT.NONE);
        addRemoveComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        addRemoveComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        addDependencyButton = new Button(addRemoveComposite, SWT.FLAT);
        addDependencyButton.setText(Messages.add);
        addDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create());

        removeDependencyButton = new Button(addRemoveComposite, SWT.FLAT);
        removeDependencyButton.setText(Messages.remove);
        removeDependencyButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                for (final Object sel : ((IStructuredSelection) dependenciesViewer.getSelection()).toList()) {
                    inputExpression.getReferencedElements().remove(sel);
                }
            }
        });
        removeDependencyButton.setEnabled(false);
        removeDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create());

        depndencySection.setClient(dependenciesComposite);
    }

    protected void createGroovyEditor(Composite parent, boolean restrictSciptSize) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        container.setLayout(GridLayoutFactory.fillDefaults().create());
        new OperatorsToolBar(container, this);

        Composite groovyViewerComposite = new Composite(container, SWT.NONE);
        groovyViewerComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        groovyViewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        groovyViewerComposite.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, "groovyViewer");

        groovyViewer = new GroovyViewer(groovyViewerComposite, false, restrictSciptSize);
        sourceViewer = groovyViewer.getSourceViewer();
        document = groovyViewer.getDocument();

        dropTarget = new DropTarget(sourceViewer.getTextWidget(), DND.DROP_MOVE);
        dropTarget.setTransfer(TextTransfer.getInstance());

        groovyViewerComposite.getChildren()[0].setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }

    private void createButtonBar(Composite parent) {
        Composite buttonBarComposite = new Composite(parent, SWT.NONE);
        buttonBarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        buttonBarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        writeOperationWarning = new Link(buttonBarComposite, SWT.NONE);
        writeOperationWarning.addListener(SWT.Selection,
                e -> new OpenBrowserOperation(EXPRESSION_AND_SCRIPTS_URL).execute());
        writeOperationWarning
                .setText(Messages.onlyReadOnly);
        writeOperationWarning.setVisible(false);
        writeOperationWarning
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        testButton = new Button(buttonBarComposite, SWT.PUSH);
        testButton.setText(Messages.testButtonLabel);
        testButton.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.END, SWT.FILL).create());
        testButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Map<String, Serializable> variables = TestGroovyScriptUtil.createVariablesMap(
                        groovyViewer.getGroovyCompilationUnit(),
                        nodes == null ? Lists.<ScriptVariable> newArrayList() : nodes);

                if (variables.isEmpty()) {
                    final ManageConnectorJarDialog mcjd = new ManageConnectorJarDialog(
                            Display.getDefault().getActiveShell());
                    final int retCode = mcjd.open();
                    if (retCode == Window.OK) {
                        try {
                            TestGroovyScriptUtil.evaluateExpression(groovyViewer.getGroovyCompilationUnit().getSource(),
                                    inputExpression.getReturnType(), Collections.EMPTY_MAP, mcjd.getSelectedJars());
                        } catch (final JavaModelException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                } else {
                    new TestGroovyScriptDialog(Display.getDefault().getActiveShell(), nodes, groovyViewer
                            .getGroovyCompilationUnit(), inputExpression.getReturnType(), variables).open();
                }
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish
     * ()
     */
    @Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context,
            final Expression inputExpression,
            final ViewerFilter[] filters, final ExpressionViewer viewer) {

        GroovyDocumentUtil.refreshUserLibrary(RepositoryManager.getInstance().getCurrentRepository());

        this.inputExpression = inputExpression;
        this.context = context;

        final IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);
        final IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES);

        inputExpression.setType(ExpressionConstants.SCRIPT_TYPE);
        inputExpression.setInterpreter(ExpressionConstants.GROOVY);

        groovyViewer.setContext(viewer, context, filters, viewer.getExpressionNatureProvider());
        nodes = new ArrayList<>(groovyViewer.getFieldNodes());

        input = groovyViewer.getProvidedVariables(context, filters);
        input.addAll(nodes);
        scriptExpressionContext = ScriptExpressionContext.computeProposals(repositoryAccessor, input, context);
        proposalsViewer.setInput(scriptExpressionContext);
        dropTarget.addDropListener(
                new DropProposalTargetEffect(sourceViewer.getTextWidget(), getEditor(), scriptExpressionContext));

        dataBindingContext.bindValue(ViewersObservables.observeInput(dependenciesViewer), dependenciesModelObservable);

        final UpdateValueStrategy opposite = new UpdateValueStrategy();
        opposite.setConverter(new BooleanInverserConverter());

        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton),
                autoDepsModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), SWTObservables
                .observeEnabled(addDependencyButton), opposite,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        autoDepsModelObservable.addChangeListener(new IChangeListener() {

            @Override
            public void handleChange(final ChangeEvent event) {
                if ((Boolean) autoDepsModelObservable.getValue()) {
                    if (dependencyJob != null) {
                        dependencyJob.schedule();
                    }
                }
            }
        });
        depndencySection.setExpanded(!automaticResolutionButton.getSelection());

        addDependencyButton.setEnabled(!inputExpression.isAutomaticDependencies());

        dependencyJob = new ComputeScriptDependenciesJob(groovyViewer.getGroovyCompilationUnit());
        dependencyJob.setContext(context);
        nodes.addAll(groovyViewer.getProvidedVariables(context, filters));
        dependencyJob.setNodes(nodes);
        BonitaConstantsTypeLookup.setBonitaVariables(nodes);

        final InputLengthValidator lenghtValidator = new InputLengthValidator("", GroovyViewer.MAX_SCRIPT_LENGTH);
        String content = inputExpression.getContent();
        if (content == null) {
            content = "";
        }
        sourceViewer.getTextWidget().setText(content);
        sourceViewer.getDocument().addDocumentListener(new IDocumentListener() {

            @Override
            public void documentChanged(final DocumentEvent event) {
                final String text = event.getDocument().get();
                if (lenghtValidator.validate(text).isOK()) {
                    GroovyScriptExpressionEditor.this.inputExpression.setContent(text);
                }
                if (!automaticResolutionButton.isDisposed() && automaticResolutionButton.getSelection()) {
                    dependencyJob.schedule();
                }

            }

            @Override
            public void documentAboutToBeChanged(final DocumentEvent event) {
            }
        });

        dependencyJob.addJobChangeListener(new IJobChangeListener() {

            @Override
            public void sleeping(final IJobChangeEvent event) {
            }

            @Override
            public void scheduled(final IJobChangeEvent event) {
            }

            @Override
            public void running(final IJobChangeEvent event) {
            }

            @Override
            public void done(final IJobChangeEvent event) {
                if (dependencyJob != null
                        && GroovyScriptExpressionEditor.this.inputExpression.isAutomaticDependencies()) {
                    final List<EObject> deps = dependencyJob.getDependencies(document.get());
                    EList<EObject> referencedElements = GroovyScriptExpressionEditor.this.inputExpression
                            .getReferencedElements();
                    if (deps != null) {
                        mergeList(referencedElements, new ArrayList<>(deps));
                    }
                    Display.getDefault().asyncExec(() -> {
                        if (writeOperationWarning != null && !writeOperationWarning.isDisposed()) {
                            writeOperationWarning.setVisible(
                                    referencedElements.stream().filter(Expression.class::isInstance)
                                            .map(Expression.class::cast)
                                            .map(Expression::getName).anyMatch("apiAccessor"::equals));
                            writeOperationWarning.getParent().layout(true);
                        }
                    });

                }
            }

            private void mergeList(EList<EObject> referencedElements, List<EObject> deps) {
                Set<EObject> removedDependencies = new HashSet<>();
                Set<EObject> alreadyExistDependency = new HashSet<>();
                for (EObject originalDep : referencedElements) {
                    boolean removed = true;
                    for (EObject newDep : deps) {
                        if (EcoreUtil.equals(originalDep, newDep)) {
                            removed = false;
                            alreadyExistDependency.add(newDep);
                        }
                    }
                    if (removed) {
                        removedDependencies.add(originalDep);
                    }
                }
                deps.removeAll(alreadyExistDependency);
                referencedElements.removeAll(removedDependencies);
                for (EObject newDep : deps) {
                    referencedElements.add(newDep);
                }
            }

            @Override
            public void awake(final IJobChangeEvent event) {
            }

            @Override
            public void aboutToRun(final IJobChangeEvent event) {
            }

        });

        final ExpressionContentProvider provider = ExpressionContentProvider.getInstance();
        final Set<Expression> filteredExpressions = new HashSet<>();
        final Expression[] expressions = provider.getExpressions(context);
        if (expressions != null) {
            filteredExpressions.addAll(Arrays.asList(expressions));
            if (context != null && filters != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : filters) {
                        if (filter != null && !filter.select(groovyViewer.getSourceViewer(), context, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
        }

        addDependencyButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectDependencyDialog dialog = new SelectDependencyDialog(Display.getDefault().getActiveShell(),
                        filteredExpressions, GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements());
                dialog.open();
            }
        });

        final UpdateValueStrategy evaluateStrategy = new UpdateValueStrategy();
        evaluateStrategy.setConverter(new Converter(String.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject == null || fromObject.toString().isEmpty()) {
                    return false;
                }
                return true;
            }
        });
        dataBindingContext.bindValue(SWTObservables.observeEnabled(testButton),
                SWTObservables.observeText(groovyViewer.getSourceViewer().getTextWidget(), SWT.Modify), null,
                evaluateStrategy);

        sourceViewer.getUndoManager().reset();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (dependencyJob != null) {
            dependencyJob.cancel();
            try {
                dependencyJob.join();
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (groovyViewer != null) {
            groovyViewer.dispose();
        }
    }

    @Override
    public void okPressed() {
        if (dependencyJob != null) {
            dependencyJob.cancel();
            dependencyJob.schedule();
            try {
                dependencyJob.join();
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public boolean provideDialogTray() {
        return true;
    }

    @Override
    public DialogTray createDialogTray() {
        return new GroovyEditorDocumentationDialogTray(groovyViewer);
    }

    @Override
    public Control getTextControl() {
        return sourceViewer.getTextWidget();
    }

    public SourceViewer getSourceViewer() {
        return sourceViewer;
    }

    @Override
    public IObservable getContentObservable() {
        return new DocumentObservable(sourceViewer);
    }

    public GroovyEditor getEditor() {
        return groovyViewer.getEditor();
    }
}
