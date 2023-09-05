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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ICustomExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.ui.Activator;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.job.UnknownElementsIndexer;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.preferences.PreferenceConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.ASTProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
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

    private BonitaGroovyEditor editor;

    private IEditorInput input;

    private List<ScriptVariable> nodes;

    private Set<String> knowVariables;

    private boolean isPageFlowContext;

    private UnknownElementsIndexer unknownElementsIndexer;

    private IEclipseContext groovyEditorContext;

    private boolean restrictScriptSize = true;

    public GroovyViewer(final Composite mainComposite) {
        this(mainComposite, null, null, true);
    }

    public GroovyViewer(final Composite mainComposite, final boolean isPageFlowContext, boolean restrictScriptSize) {
        this(mainComposite, null, isPageFlowContext, restrictScriptSize);

    }

    public GroovyViewer(final Composite mainComposite, final IEditorInput input, final boolean isPageFlowContext,
            boolean restrictScriptSize) {
        this(mainComposite, input, null, restrictScriptSize);
        this.isPageFlowContext = isPageFlowContext;

    }

    public GroovyViewer(final Composite mainComposite, final IEditorInput input, final BonitaGroovyEditor groovyEditor,
            boolean restrictScriptSize) {
        this.restrictScriptSize = restrictScriptSize;
        enbaleSyntaxHighligthing();
        if (input == null) {
            this.input = new FileEditorInput(createTempSourceFile());
        } else {
            this.input = input;
        }
        editor = groovyEditor == null ? new BonitaGroovyEditor() : groovyEditor;
        try {
            groovyEditorContext = createGroovyEditorContext();
            final DummyEditorSite site = new DummyEditorSite(mainComposite.getShell(), editor);
            groovyEditorContext.set(ISources.ACTIVE_SITE_NAME, site);
            editor.init(site, this.input);
            editor.doSave(AbstractRepository.NULL_PROGRESS_MONITOR);
            ASTProvider astProvider = JavaPlugin.getDefault().getASTProvider();
            org.eclipse.jdt.groovy.core.util.ReflectionUtils.executePrivateMethod(ASTProvider.class,
                    "activeJavaEditorChanged", new Class[] { IWorkbenchPart.class }, astProvider,
                    new Object[] { editor });
            editor.createPartControl(mainComposite);

        } catch (final Exception e1) {
            BonitaStudioLog.error(e1);
        }

        final StyledText styledText = getSourceViewer().getTextWidget();
        if (restrictScriptSize) {
            styledText.setTextLimit(MAX_SCRIPT_LENGTH);
        }

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

    private IFile createTempSourceFile() {
        var appProject = RepositoryManager.getInstance().getCurrentProject().orElseThrow().getAppProject();
        var tmpScriptFile = appProject.getFile(AppProjectConfiguration.GENERATED_GROOVY_SOURCES_FODLER + "/"
                + GroovyFileStore.EXPRESSION_SCRIPT_NAME);
        try {
            if(tmpScriptFile.exists()) {
                tmpScriptFile.delete(true, new NullProgressMonitor());
            }
            if(!tmpScriptFile.getParent().exists()) {
                Files.createDirectories(tmpScriptFile.getLocation().toFile().toPath().getParent());
                tmpScriptFile.getParent().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
            }
            tmpScriptFile.create(new ByteArrayInputStream(new byte[0]), true, new NullProgressMonitor());
            tmpScriptFile.setDerived(false, new NullProgressMonitor());
        } catch (CoreException | IOException e) {
            BonitaStudioLog.error(e);
        }
        return tmpScriptFile;
    }

    private IEclipseContext createGroovyEditorContext() {
        final IEclipseContext context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        final IEclipseContext activeLeaf = context.getActiveLeaf();
        IEclipseContext groovyEditorContext = activeLeaf.createChild("groovyEditorContext");
        groovyEditorContext.set("localContexts",
                Lists.newLinkedList(Lists.newArrayList("org.eclipse.ui.contexts.window",
                        "org.eclipse.ui.contexts.dialogAndWindow", "org.eclipse.ui.textEditorScope",
                        "org.eclipse.jdt.ui.javaEditorScope", "org.codehaus.groovy.eclipse.editor.groovyEditorScope")));
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
        nodes = new ArrayList<>();

        IExpressionNatureProvider provider = expressionProvider;
        if (!(provider instanceof ICustomExpressionNatureProvider)) {
            provider = ExpressionContentProvider.getInstance();
        }
        final Set<Expression> filteredExpressions = new HashSet<>();
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
                final ProcessConfigurationRepositoryStore store = RepositoryManager.getInstance()
                        .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
                final ProcessConfigurationFileStore fileStore = store
                        .getChild(ModelHelper.getEObjectID(proc) + "." + ProcessConfigurationRepositoryStore.CONF_EXT,
                                true);
                if (fileStore != null) {
                    Configuration c;
                    try {
                        c = fileStore.getContent();
                        for (final Parameter p : c.getParameters()) {
                            if (p.getName().equals(v.getName())) {
                                v.setDefaultValue(p.getValue());
                            }
                        }
                    } catch (ReadFileStoreException e1) {
                        BonitaStudioLog.warning(e1.getMessage(), Activator.PLUGIN_ID);
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

        knowVariables = new HashSet<>();
        if (nodes != null) {
            for (final ScriptVariable n : nodes) {
                knowVariables.add(n.getName());
            }
        }
        for (final ScriptVariable n : providedVariables) {
            knowVariables.add(n.getName());
        }
        unknownElementsIndexer = new UnknownElementsIndexer(getGroovyCompilationUnit());
        unknownElementsIndexer.addJobChangeListener(
                new UpdateUnknownReferencesListener(getDocument(), getSourceViewer().getAnnotationModel()));

        Map<String, ScriptVariable> c = new HashMap<>();
        nodes.forEach(n -> c.put(n.getName(), n));
        providedVariables.forEach(n -> c.put(n.getName(), n));
        editor.setContext(c);
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
                scriptVariable.setCategory(ExpressionConstants.DAO_TYPE);
                providedScriptVariable.add(scriptVariable);
            }
        }
        return providedScriptVariable;
    }

    public List<ScriptVariable> getFieldNodes() {
        return nodes;
    }

    public void dispose() {
        SourceViewer viewer = (SourceViewer) editor.getViewer();
        if (viewer != null) {
            // Stops reconciler thread before deleting groovy file
            viewer.unconfigure();
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

    public BonitaScriptGroovyCompilationUnit getGroovyCompilationUnit() {
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
    public void documentChanged(final DocumentEvent event) {
        if (restrictScriptSize && event.getDocument().get().length() >= MAX_SCRIPT_LENGTH) {
            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.maxScriptLengthTitle,
                    Messages.maxScriptLength);
        }
        if (unknownElementsIndexer != null) {
            unknownElementsIndexer.schedule();
        }

    }

    @Override
    public void documentAboutToBeChanged(DocumentEvent event) {
        // TODO Auto-generated method stub

    }

    public void disableSyntaxHighligthing() {
        final IPreferenceStore groovyStore = org.codehaus.groovy.eclipse.GroovyPlugin.getDefault().getPreferenceStore();
        groovyStore.setDefault(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, false);
        groovyStore.setValue(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, false);
    }

    public void enbaleSyntaxHighligthing() {
        final IPreferenceStore groovyStore = org.codehaus.groovy.eclipse.GroovyPlugin.getDefault().getPreferenceStore();
        groovyStore.setDefault(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, true);
        groovyStore.setValue(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING, true);
    }

    public GroovyEditor getEditor() {
        return editor;
    }

}
