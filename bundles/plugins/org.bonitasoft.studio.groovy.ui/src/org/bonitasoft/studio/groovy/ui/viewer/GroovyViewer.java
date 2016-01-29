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
package org.bonitasoft.studio.groovy.ui.viewer;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ICustomExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.job.UnknownElementsIndexer;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.core.preferences.PreferenceConstants;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * @author Romain Bioteau
 */
public class GroovyViewer implements IDocumentListener {

    public static final String CONTEXT_DATA_KEY = "context";

    public static final String BONITA_KEYWORDS_DATA_KEY = "bonita.keywords";

    public static final String PROCESS_VARIABLES_DATA_KEY = "process.variables";

    public static final int MAX_SCRIPT_LENGTH = 65535;

    private GroovyEditor editor;

    private IEditorInput input;

    private List<ScriptVariable> nodes;

    private IHandlerActivation fHandlerActivation;

    private AbstractHandler triggerAssistantHandler;

    private GroovyFileStore tmpGroovyFileStore;

    private Set<String> knowVariables;

    private boolean isPageFlowContext;

    private UnknownElementsIndexer unknownElementsIndexer;

    public GroovyViewer(final Composite mainComposite) {
        this(mainComposite, null, null);
    }

    public GroovyViewer(final Composite mainComposite, final boolean isPageFlowContext) {
        this(mainComposite, null, isPageFlowContext);
    }

    public GroovyViewer(final Composite mainComposite, final IEditorInput input, final boolean isPageFlowContext) {
        this(mainComposite, input, null);
        this.isPageFlowContext = isPageFlowContext;
    }

    public GroovyViewer(final Composite mainComposite, final IEditorInput input, final GroovyEditor groovyEditor) {
        final IPreferenceStore groovyStore = org.codehaus.groovy.eclipse.GroovyPlugin.getDefault().getPreferenceStore();
        groovyStore.setDefault(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, false);
        groovyStore.setValue(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, false);
        if (input == null) {
            final ProvidedGroovyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(
                    ProvidedGroovyRepositoryStore.class);
            tmpGroovyFileStore = store.createRepositoryFileStore("script" + System.currentTimeMillis() + ".groovy");
            tmpGroovyFileStore.save("");
            this.input = new FileEditorInput(tmpGroovyFileStore.getResource());
        } else {
            this.input = input;
        }
        editor = groovyEditor;
        if (editor == null) {
            editor = new BonitaGroovyEditor(GroovyPlugin.getDefault().getPreferenceStore());
        }
        try {
            editor.getDocumentProvider().connect(input);
            editor.init(new DummyEditorSite(mainComposite.getShell(), editor), this.input);
            editor.createPartControl(mainComposite);
            editor.createJavaSourceViewerConfiguration();
        } catch (final Exception e1) {
            BonitaStudioLog.error(e1);
        }
        final GroovyEditorActionFactory actionFactory = new GroovyEditorActionFactory(editor);
        getSourceViewer().getTextWidget().setTextLimit(MAX_SCRIPT_LENGTH);
        getSourceViewer().addTextListener(new ITextListener() {

            private boolean isReconciling;

            @Override
            public void textChanged(final TextEvent event) {
                if (!isReconciling) {
                    isReconciling = true;
                    try {
                        JavaModelUtil.reconcile(editor.getGroovyCompilationUnit());
                    } catch (final JavaModelException e) {

                    } finally {
                        isReconciling = false;
                    }
                }
            }
        });

        // Set up content assist in the viewer
        triggerAssistantHandler = new AbstractHandler() {

            @Override
            public Object execute(final ExecutionEvent event) throws ExecutionException {
                if (getSourceViewer().canDoOperation(ISourceViewer.CONTENTASSIST_PROPOSALS)) {
                    getSourceViewer().doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                }
                return null;
            }
        };

        getSourceViewer().getTextWidget().addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(final KeyEvent e) {
                if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'z') {
                    actionFactory.getUndoAction().run();
                } else if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'y') {
                    actionFactory.getRedoAction().run();
                } else if (e.stateMask == (SWT.CTRL | SWT.SHIFT) && e.keyCode == 'o') {
                    actionFactory.getOrganizeImportAction().run();
                }

            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    actionFactory.getDeleteAction().run();
                } else
                if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'i') {
                    actionFactory.getFormatAction().run();
                }
            }

        });
        enableContextAssitShortcut();

        getSourceViewer().getTextWidget().setData(BONITA_KEYWORDS_DATA_KEY, getProvidedVariables(null, null));
        mainComposite.getShell().addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(final DisposeEvent e) {
                dispose();
            }
        });
    }

    public void enableContextAssitShortcut() {
        final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
        fHandlerActivation = handlerService.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, triggerAssistantHandler);
    }

    public void disableContextAssitShortcut() {
        if (fHandlerActivation != null) {
            final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
            if (handlerService != null) {
                handlerService.deactivateHandler(fHandlerActivation);
            }
            fHandlerActivation.clearResult();
        }
    }

    public IDocument getDocument() {
        return editor.getDocumentProvider().getDocument(input);
    }

    @SuppressWarnings("restriction")
    public SourceViewer getSourceViewer() {
        return (SourceViewer) editor.getViewer();
    }

    public void setLayoutData(final Object layoutData) {
        getSourceViewer().getTextWidget().setLayoutData(layoutData);
    }

    public void setContext(final ExpressionViewer viewer, final EObject context, final ViewerFilter[] filters,
            final IExpressionNatureProvider expressionProvider) {
        nodes = new ArrayList<ScriptVariable>();

        IExpressionNatureProvider provider = expressionProvider;
        if (!(provider instanceof ICustomExpressionNatureProvider)) {
            provider = ExpressionContentProvider.getInstance();
        }
        final Set<Expression> filteredExpressions = new HashSet<Expression>();
        final Expression[] expressions = provider.getExpressions(context);
        if (expressions != null) {
            filteredExpressions.addAll(Arrays.asList(expressions));
            if (context != null && filters != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : filters) {
                        if (filter != null && !filter.select(viewer, context, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
        }

        for (final Expression e : filteredExpressions) {
            final ScriptVariable v = GroovyUtil.createScriptVariable(e, context);
            if (context != null && ExpressionConstants.PARAMETER_TYPE.equals(e.getType())) {
                final AbstractProcess proc = ModelHelper.getParentProcess(context);
                final ProcessConfigurationRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(
                        ProcessConfigurationRepositoryStore.class);
                final ProcessConfigurationFileStore fileStore = store.getChild(ModelHelper.getEObjectID(proc) + "."
                        + ProcessConfigurationRepositoryStore.CONF_EXT);
                if (fileStore != null) {
                    final Configuration c = fileStore.getContent();
                    for (final Parameter p : c.getParameters()) {
                        if (p.getName().equals(v.getName())) {
                            v.setDefaultValue(p.getValue());
                        }
                    }
                }
            }
            if (v != null) {
                nodes.add(v);
            }
        }

        // Add context in TextWidget to access it in content assist
        getSourceViewer().getTextWidget().setData(PROCESS_VARIABLES_DATA_KEY, nodes);
        final List<ScriptVariable> providedVariables = getProvidedVariables(context, filters);
        getSourceViewer().getTextWidget().setData(BONITA_KEYWORDS_DATA_KEY, providedVariables);
        getSourceViewer().getTextWidget().setData(CONTEXT_DATA_KEY, context);
        getSourceViewer().getDocument().addDocumentListener(this);

        knowVariables = new HashSet<String>();
        if (nodes != null) {
            for (final ScriptVariable n : nodes) {
                knowVariables.add(n.getName());
            }
        }
        if (providedVariables != null) {
            for (final ScriptVariable n : providedVariables) {
                knowVariables.add(n.getName());
            }
        }
        unknownElementsIndexer = new UnknownElementsIndexer(knowVariables, getGroovyCompilationUnit());
        unknownElementsIndexer.addJobChangeListener(new UpdateUnknownReferencesListener(getDocument(), getSourceViewer().getAnnotationModel()));
    }

    public List<ScriptVariable> getProvidedVariables(final EObject context, final ViewerFilter[] filters) {
        final List<ScriptVariable> providedScriptVariable = GroovyUtil.getBonitaVariables(context, filters, isPageFlowContext);
        final IExpressionProvider daoExpressionProvider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.DAO_TYPE);
        if (daoExpressionProvider != null) {
            final List<Expression> expressions = newArrayList(daoExpressionProvider.getExpressions(null));
            Collections.sort(expressions, new ExpressionComparator());
            for (final Expression e : expressions) {
                final ScriptVariable scriptVariable = new ScriptVariable(e.getName(), e.getReturnType());
                providedScriptVariable.add(scriptVariable);
            }
        }
        return providedScriptVariable;
    }

    public List<ScriptVariable> getFieldNodes() {
        return nodes;
    }

    public void dispose() {
        if (tmpGroovyFileStore != null) {
            tmpGroovyFileStore.delete();
        }
        disableContextAssitShortcut();
    }

    public GroovyCompilationUnit getGroovyCompilationUnit() {
        return editor.getGroovyCompilationUnit();
    }

    public void setInput(final IEditorInput input) {
        try {
            this.input = input;
            editor.getDocumentProvider().connect(input);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void setFieldNodes(final List<ScriptVariable> fieldNodes) {
        nodes = fieldNodes;
        getSourceViewer().getTextWidget().setData(PROCESS_VARIABLES_DATA_KEY, fieldNodes);
    }

    @Override
    public void documentAboutToBeChanged(final DocumentEvent event) {

    }

    @Override
    public void documentChanged(final DocumentEvent event) {
        if (unknownElementsIndexer != null) {
            unknownElementsIndexer.schedule();
        }

    }

}
