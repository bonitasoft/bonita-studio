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
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.rulers.IColumnSupport;

import com.google.common.collect.Lists;

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

    private GroovyFileStore tmpGroovyFileStore;

    private Set<String> knowVariables;

    private boolean isPageFlowContext;

    private UnknownElementsIndexer unknownElementsIndexer;

    private IEclipseContext groovyEditorContext;

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
            groovyEditorContext = createGroovyEditorContext();
            final DummyEditorSite site = new DummyEditorSite(mainComposite.getShell(), editor);
            groovyEditorContext.set(ISources.ACTIVE_SITE_NAME, site);
            editor.init(site, this.input);
            editor.doSave(Repository.NULL_PROGRESS_MONITOR);
            editor.createPartControl(mainComposite);
        } catch (final Exception e1) {
            BonitaStudioLog.error(e1);
        }

        final StyledText styledText = getSourceViewer().getTextWidget();
        styledText.setTextLimit(MAX_SCRIPT_LENGTH);
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

        styledText.setData(BONITA_KEYWORDS_DATA_KEY, getProvidedVariables(null, null));
        styledText.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                groovyEditorContext.deactivate();
            }

            @Override
            public void focusGained(FocusEvent e) {
                groovyEditorContext.activate();
            }
        });
        mainComposite.getShell().addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(final DisposeEvent e) {
                dispose();
            }
        });
    }

    private IEclipseContext createGroovyEditorContext() {
        final IEclipseContext context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        final IEclipseContext activeLeaf = context.getActiveLeaf();
        IEclipseContext groovyEditorContext = activeLeaf.createChild("groovyEditorContext");
        groovyEditorContext
                .set("localContexts",
                        Lists.newLinkedList(Lists.newArrayList("org.eclipse.ui.contexts.window",
                                "org.eclipse.ui.contexts.dialogAndWindow",
                                "org.eclipse.ui.textEditorScope", "org.eclipse.jdt.ui.javaEditorScope",
                                "org.codehaus.groovy.eclipse.editor.groovyEditorScope")));
        return groovyEditorContext;
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
        unknownElementsIndexer.addJobChangeListener(
                new UpdateUnknownReferencesListener(getDocument(), getSourceViewer().getAnnotationModel()));
    }

    public List<ScriptVariable> getProvidedVariables(final EObject context, final ViewerFilter[] filters) {
        final List<ScriptVariable> providedScriptVariable = GroovyUtil.getBonitaVariables(context, filters,
                isPageFlowContext);
        final IExpressionProvider daoExpressionProvider = ExpressionProviderService.getInstance()
                .getExpressionProvider(ExpressionConstants.DAO_TYPE);
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
        final IColumnSupport columSupport = (IColumnSupport) editor.getAdapter(IColumnSupport.class);
        if (columSupport != null) {
            columSupport.dispose();
        }
        final CompositeRuler verticalRuler = (CompositeRuler) editor.getAdapter(IVerticalRulerInfo.class);
        if (verticalRuler != null && verticalRuler.getControl() != null) {
            verticalRuler.getControl().dispose();
        }
        editor.dispose();
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
