/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.pattern.contentAssist.ExpressionContentAssistProcessor;
import org.bonitasoft.studio.expression.editor.pattern.contentAssist.PatternExpressionCompletionProcessor;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionNameResolver;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;

import com.google.common.collect.Sets;

/**
 * @author Romain Bioteau
 */
public class PatternExpressionViewer extends Composite {

    private static final String GROOVY_START_TAG = "${";
    private static final String GROOVY_END_TAG = "}";
    public static final String GROOVY_EXPRESSION_CONTENT_TYPE = "__groovy_partition_content_type";
    private static final int UNDO_REDO_HISTORY_SIZE = 25;
    private TextViewer viewer;
    private ExpressionViewer expressionViewer;
    private final IExpressionNatureProvider expressionNatureProvider = ExpressionContentProvider.getInstance();
    private final Set<ViewerFilter> filters = new HashSet<>();
    protected Expression expression;
    private ControlDecoration hintDecoration;
    private final MagicComposite mc;
    private EMFDataBindingContext context;
    protected String mandatoryFieldLabel;
    private EObject contextInput;
    private Binding patternBinding;
    private ControlDecoration helpDecoration;
    private Object input;
    private PatternExpressionModelBuilder patternExpressionModelBuilder;

    public PatternExpressionViewer(final Composite parent, final int style) {
        super(parent, style);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        mc = new MagicComposite(this, SWT.INHERIT_DEFAULT);
        mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
        mc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createTextViewer();
        createExpressionViewer();
        createEditorSwitch();
        mc.hide(getViewerControl());
        mc.show(expressionViewer.getControl());

    }

    private void createEditorSwitch() {
        final Link switchControl = new Link(mc, SWT.NONE);
        switchControl.setText(Messages.switchEditor);
        switchControl.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        switchControl.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                switchEditorType();
            }
        });
    }

    protected void initializeEditorType() {
        if (ExpressionConstants.PATTERN_TYPE.equals(expression.getType())) {
            expression.setName("<pattern-expression>");
            mc.hide(expressionViewer.getControl());
            mc.show(getViewerControl());
            helpDecoration.show();
            mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
            bindPatternExpression();
        } else {
            mc.hide(getViewerControl());
            mc.show(expressionViewer.getControl());
            helpDecoration.hide();
            mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
            bindExpressionViewer();
        }
        mc.layout(true, true);
    }

    protected void switchEditorType() {
        if (MessageDialog.openQuestion(mc.getShell(), Messages.eraseExpressionTitle, Messages.eraseExpressionMsg)) {
            if (!expressionMode()) {
                showExpressionViewer();
                bindExpressionViewer();
                mc.layout(true, true);
            } else {
                showTextViewer();
                bindPatternExpression();
            }
        }
    }

    protected void showTextViewer() {
        mc.hide(expressionViewer.getControl());
        mc.show(getViewerControl());
        expression.setContent("");
        expression.setName("<pattern-expression>");
        expression.setInterpreter(null);
        expression.setType(ExpressionConstants.PATTERN_TYPE);
        bindPatternExpression();
        mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
        mc.layout(true, true);
        helpDecoration.show();
        if (hintDecoration.getDescriptionText() != null) {
            hintDecoration.show();
        }
    }

    protected Control getViewerControl() {
        return viewer.getControl();
    }

    protected void showExpressionViewer() {
        helpDecoration.hide();
        hintDecoration.hide();
        mc.hide(getViewerControl());
        mc.show(expressionViewer.getControl());
        expression.setContent("");
        expression.setName("");
        expression.setInterpreter(null);
        expression.setType(ExpressionConstants.CONSTANT_TYPE);
        expression.getReferencedElements().clear();
        expressionViewer.setSelection(new StructuredSelection(expression));
        mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
    }

    protected boolean expressionMode() {
        return expressionViewer.getControl().isVisible();
    }

    private void bindExpressionViewer() {
        expressionViewer.setContext(contextInput);
        expressionViewer.setInput(input);
        if (mandatoryFieldLabel != null) {
            expressionViewer.setMandatoryField(mandatoryFieldLabel, context);
        }
        expressionViewer.setExternalDataBindingContext(context);
        expressionViewer.setSelection(new StructuredSelection(expression));
    }

    protected void bindPatternExpression() {
        final UpdateValueStrategy startegy = new UpdateValueStrategy();
        if (mandatoryFieldLabel != null) {
            startegy.setAfterConvertValidator(new EmptyInputValidator(mandatoryFieldLabel));
        }
        if (patternBinding != null) {
            patternBinding.dispose();
            patternBinding = null;
        }
        expression.getReferencedElements().clear();
        patternExpressionModelBuilder.setExpression(expression);
        patternBinding = context.bindValue(SWTObservables.observeText(viewer.getTextWidget(), SWT.Modify),
                EMFObservables.observeValue(expression, ExpressionPackage.Literals.EXPRESSION__CONTENT), startegy, null);

    }

    protected void createExpressionViewer() {
        expressionViewer = new ExpressionViewer(mc, SWT.BORDER);
        expressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    protected void createTextViewer() {
        viewer = createViewer(mc);
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        configureTextViewer();
        addLineStyleListener();
        helpDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.RIGHT, this);
        helpDecoration.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
        helpDecoration.setDescriptionText(Messages.patternViewerHint);
        helpDecoration.setMarginWidth(2);
        helpDecoration.hide();

        hintDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.LEFT, this);
        hintDecoration.setImage(Pics.getImage(PicsConstants.hint));
        hintDecoration.setMarginWidth(2);
        hintDecoration.setShowHover(true);
        hintDecoration.setShowOnlyOnFocus(true);
        hintDecoration.hide();

        viewer.addTextListener(new ITextListener() {

            @Override
            public void textChanged(final TextEvent event) {
                viewer.getTextWidget().notifyListeners(SWT.Modify, new Event());
            }
        });

        helpDecoration.show();
    }

    protected void addLineStyleListener() {
        viewer.getTextWidget().addLineStyleListener(new PatternLineStyleListener(viewer.getDocument()));
    }

    protected void configureTextViewer() {
        final Document document = new Document();
        final IDocumentPartitioner partitioner = new FastPartitioner(
                createScanner(),
                new String[] { GROOVY_EXPRESSION_CONTENT_TYPE });
        partitioner.connect(document);
        document.setDocumentPartitioner(partitioner);
        patternExpressionModelBuilder = new PatternExpressionModelBuilder();
        document.addDocumentListener(patternExpressionModelBuilder);
        viewer.setDocument(document);
        final TextViewerUndoManager undoManager = new TextViewerUndoManager(UNDO_REDO_HISTORY_SIZE);
        viewer.setUndoManager(undoManager);
        undoManager.connect(viewer);
        viewer.getTextWidget().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.stateMask == SWT.MOD1) && e.keyCode == 'z') {
                    undoManager.undo();
                } else if ((e.stateMask == SWT.MOD1) && e.keyCode == 'y') {
                    undoManager.redo();
                } else if ((e.stateMask == SWT.MOD1 + SWT.SHIFT) && e.keyCode == 'z') {
                    undoManager.redo();
                }
            }
        });

    }


    private IPartitionTokenScanner createScanner() {
        final IToken string = new Token(GROOVY_EXPRESSION_CONTENT_TYPE);
        final RuleBasedPartitionScanner scanner = new RuleBasedPartitionScanner();
        scanner.setPredicateRules(new IPredicateRule[] {
                new MultiLineRule(GROOVY_START_TAG, GROOVY_END_TAG, string)
        });
        return scanner;
    }

    protected TextViewer createViewer(final Composite parent) {
        return new TextViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
    }


    public void setContextInput(final EObject input) {
        contextInput = input;
        final List<Expression> filteredExpressions = getFilteredExpressions();
        patternExpressionModelBuilder.setScope(filteredExpressions);
        final ContentAssistant assistant = new ContentAssistant();
        final FakeEditorPart fakeEditorPart = new FakeEditorPart();
        final PatternExpressionCompletionProcessor javaCompletionProcessor = new PatternExpressionCompletionProcessor(fakeEditorPart, filteredExpressions);
        javaCompletionProcessor.setCompletionProposalAutoActivationCharacters(new char[] { '.' });
        assistant.setContentAssistProcessor(javaCompletionProcessor, GROOVY_EXPRESSION_CONTENT_TYPE);
        assistant.setShowEmptyList(true);
        assistant.enableAutoActivation(true);

        final ExpressionContentAssistProcessor contentAssisProcessor = new ExpressionContentAssistProcessor();
        contentAssisProcessor.setExpressions(Sets.newHashSet(filteredExpressions));
        assistant.setContentAssistProcessor(contentAssisProcessor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setShowEmptyList(true);
        assistant.install(viewer);

        viewer.getTextWidget().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == SWT.SPACE) {
                    assistant.showPossibleCompletions();
                }
            }
        });
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
        initializeEditorType();
    }

    private List<Expression> getFilteredExpressions() {
        final List<Expression> filteredExpressions = new ArrayList<>();
        final Expression[] expressions = expressionNatureProvider.getExpressions(contextInput);
        if (expressions != null) {
            filteredExpressions.addAll(Arrays.asList(expressions));
            if (contextInput != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : filters) {
                        if (filter != null && !filter.select(expressionViewer, input, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
        }
        return filteredExpressions;
    }

    public void addFilter(final ViewerFilter viewerFilter) {
        filters.add(viewerFilter);
        expressionViewer.addFilter(viewerFilter);
    }

    public StyledText getTextControl() {
        return viewer.getTextWidget();
    }

    public void setHint(final String hint) {
        hintDecoration.setDescriptionText(hint);
        hintDecoration.show();
        expressionViewer.setMessage(hint, IStatus.INFO);
    }

    public void setEMFBindingContext(final EMFDataBindingContext context) {
        this.context = context;
    }

    public void setMandatoryField(final String mandatoryFieldLabel) {
        this.mandatoryFieldLabel = mandatoryFieldLabel;
    }

    public void setInput(final Object input) {
        this.input = input;
        expressionViewer.setInput(input);
    }

    public void setExpressionNameResolver(final ExpressionNameResolver expressionNameResolver) {
        expressionViewer.setExpressionNameResolver(expressionNameResolver);
    }
}
