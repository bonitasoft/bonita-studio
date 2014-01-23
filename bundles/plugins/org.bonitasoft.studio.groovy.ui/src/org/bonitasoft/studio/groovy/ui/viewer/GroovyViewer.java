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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ICustomExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.core.preferences.PreferenceConstants;
import org.codehaus.groovy.eclipse.refactoring.actions.FormatKind;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.javaeditor.JavaMarkerAnnotation;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
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
import org.eclipse.ui.texteditor.TextOperationAction;

/**
 * @author Romain Bioteau
 */
public class GroovyViewer {

	public static final String CONTEXT_DATA_KEY = "context";
	public static final String BONITA_KEYWORDS_DATA_KEY = "bonita.keywords";
	public static final String PROCESS_VARIABLES_DATA_KEY = "process.variables";
	public static final int MAX_SCRIPT_LENGTH = 65535;
	private BonitaGroovyEditor editor;
	private IEditorInput input;
	private List<ScriptVariable> nodes;
	private IHandlerActivation fHandlerActivation;
	private AbstractHandler triggerAssistantHandler;
	private boolean isComputing = false;
	private GroovyFileStore tmpGroovyFileStore;
	private boolean contextInitialized = false;
	private HashSet<String> knowVariables;
	private boolean isPageFlowContext;



	public GroovyViewer(final Composite mainComposite) {
		this(mainComposite, null);
	}

	public GroovyViewer(final Composite mainComposite,boolean isPageFlowContext){
		this(mainComposite,null,isPageFlowContext);
	}


	public GroovyViewer(final Composite mainComposite,final IEditorInput input,boolean isPageFlowContext){
		this(mainComposite,input);
		this.isPageFlowContext=isPageFlowContext;
	}


	public GroovyViewer(final Composite mainComposite, final IEditorInput input) {
		IPreferenceStore groovyStore = org.codehaus.groovy.eclipse.GroovyPlugin.getDefault().getPreferenceStore();
		groovyStore.setDefault(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING,false) ;
		groovyStore.setValue(PreferenceConstants.GROOVY_SEMANTIC_HIGHLIGHTING,false);
		if (input == null) {
			final ProvidedGroovyRepositoryStore store = (ProvidedGroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProvidedGroovyRepositoryStore.class);
			tmpGroovyFileStore = store.createRepositoryFileStore("script"+System.currentTimeMillis()+".groovy");
			tmpGroovyFileStore.save("");
			this.input = new FileEditorInput(tmpGroovyFileStore.getResource());
		} else {
			this.input = input;
		}

		editor = new BonitaGroovyEditor(GroovyPlugin.getDefault().getPreferenceStore());
		try {
			editor.getDocumentProvider().connect(input);
			editor.init(new DummyEditorSite(mainComposite.getShell(), editor), this.input);
			editor.createPartControl(mainComposite);
			editor.createJavaSourceViewerConfiguration();
		} catch (final Exception e1) {
			BonitaStudioLog.error(e1);
		}

		getSourceViewer().getTextWidget().setTextLimit(MAX_SCRIPT_LENGTH);


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
					final TextOperationAction action = new TextOperationAction(
							ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Undo.", editor, ITextOperationTarget.UNDO); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				} else if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'y') {
					final TextOperationAction action = new TextOperationAction(
							ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Redo.", editor, ITextOperationTarget.REDO); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				}
			}

			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					final TextOperationAction action = new TextOperationAction(
							ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Delete.", editor, ITextOperationTarget.DELETE); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				} else if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'i') {
					final BonitaFormatGroovyAction action = new BonitaFormatGroovyAction(editor.getEditorSite(), FormatKind.FORMAT, editor, editor
							.getGroovyCompilationUnit());
					action.run();
				}
			}
		});
		enableContextAssitShortcut();

		getSourceViewer().getTextWidget().setData(BONITA_KEYWORDS_DATA_KEY, GroovyUtil.getBonitaKeyWords(null,null,isPageFlowContext));
		getSourceViewer().getDocument().addDocumentListener(new IDocumentListener() {

			private Object previousContent;

			@Override
			public void documentChanged(DocumentEvent event) {
				if(contextInitialized){
					final String currentContent = event.getText();
					if (!isComputing && !currentContent.equals(previousContent)) {
						previousContent = currentContent;
						isComputing = true;
						final IAnnotationModel model = getSourceViewer().getAnnotationModel();
						final List<ScriptVariable> emptyList = Collections.emptyList();
						final Map<String, Serializable> result = TestGroovyScriptUtil.createVariablesMap(getGroovyCompilationUnit(), emptyList);

						final Iterator<?> it = model.getAnnotationIterator();
						while (it.hasNext()) {
							final Object annotation = it.next();
							model.removeAnnotation((Annotation) annotation);
						}
						for (final Entry<String, Serializable> entry : result.entrySet()) {
							if (!knowVariables.contains(entry.getKey())) {
								createAnnotation(entry.getKey());
							}
						}
						isComputing = false;
					}
				}
			}

			@Override
			public void documentAboutToBeChanged(DocumentEvent event) {

			}
		});
		mainComposite.getShell().addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
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
			if(handlerService != null){
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

	public void setContext(final EObject context, final ViewerFilter[] filters,IExpressionNatureProvider expressionProvider) {
		nodes = new ArrayList<ScriptVariable>();

		IExpressionNatureProvider provider = expressionProvider;
		if(!(provider instanceof ICustomExpressionNatureProvider)){
			provider = new ExpressionContentProvider();
		}
		provider.setContext(context);

		final Set<Expression> filteredExpressions = new HashSet<Expression>();
		final Expression[] expressions = provider.getExpressions();
		final EObject input = provider.getContext();
		if (expressions != null) {
			filteredExpressions.addAll(Arrays.asList(expressions));
			if (input != null && filters != null) {
				for (final Expression exp : expressions) {
					for (final ViewerFilter filter : filters) {
						if (filter != null && !filter.select(getSourceViewer(), input, exp)) {
							filteredExpressions.remove(exp);
						}
					}
				}
			}
		}

		for (final Expression e : filteredExpressions) {
			final ScriptVariable v = GroovyUtil.createScriptVariable(e,context);
			if (context != null && ExpressionConstants.PARAMETER_TYPE.equals(e.getType())) {
				final AbstractProcess proc = ModelHelper.getParentProcess(context);
				final ProcessConfigurationRepositoryStore store = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(
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
		final List<String> bonitaKeyWords = GroovyUtil.getBonitaKeyWords(context,filters,isPageFlowContext);
		getSourceViewer().getTextWidget().setData(BONITA_KEYWORDS_DATA_KEY, bonitaKeyWords);
		getSourceViewer().getTextWidget().setData(CONTEXT_DATA_KEY, context);

		knowVariables = new HashSet<String>();
		if (nodes != null) {
			for (final ScriptVariable n : nodes) {
				knowVariables.add(n.getName());
			}
		}
		knowVariables.addAll(bonitaKeyWords);

		contextInitialized  = true;
	}

	public List<ScriptVariable> getFieldNodes() {
		return nodes;
	}

	public void dispose() {
		if(tmpGroovyFileStore != null){
			tmpGroovyFileStore.delete();
		}

		disableContextAssitShortcut();
		if( editor.getViewer() != null &&  editor.getViewer().getTextWidget() != null){
			editor.dispose();
		}
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

	private void createAnnotation(final String key) {
		if (getSourceViewer() != null) {
			final IAnnotationModel model = getSourceViewer().getAnnotationModel();
			final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(getSourceViewer().getDocument());
			final String expression = getSourceViewer().getDocument().get();
			try {
				IRegion region = finder.find(0, key, true, true, true, false);
				while (region != null) {
					final Position position = new Position(region.getOffset(), region.getLength());
					if(!isInAStringExpression(key, region,expression)){
						model.addAnnotation(new Annotation(JavaMarkerAnnotation.WARNING_ANNOTATION_TYPE, false, createDescription(key)), position);
					}
					region = finder.find(position.getOffset() + position.getLength(), key, true, true, true, false);

				}
			} catch (final BadLocationException e) {

			}
		}
	}

	private boolean isInAStringExpression(String name, IRegion index,String expression) {
		if(index.getOffset() > 0){
			int nbStringChars1 = 0;
			int nbStringChars2 = 0;

			for(int i = 0 ; i<index.getOffset();i++){
				char c = expression.charAt(i);
				if('"' == c){
					nbStringChars1++;
				}else if('\'' == c){
					nbStringChars2++;
				}
			}
			return !(nbStringChars1 % 2 == 0 && nbStringChars2 % 2 == 0);

		}
		return false;
	}

	private String createDescription(final String key) {
		return key +" "+ Messages.groovyUnresolved;
	}

}
