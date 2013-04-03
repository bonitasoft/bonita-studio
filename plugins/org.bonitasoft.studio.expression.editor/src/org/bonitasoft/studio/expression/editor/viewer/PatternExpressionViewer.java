/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;



/**
 * @author Romain Bioteau
 *
 */
public class PatternExpressionViewer extends Composite {

	private TextViewer viewer;
	private ExpressionViewer expressionViewer;
	private final IExpressionNatureProvider expressionNatureProvider = new ExpressionContentProvider();
	private ExpressionContentAssistProcessor contentAssisProcessor;
	private final Set<ViewerFilter> filters = new HashSet<ViewerFilter>();
	private Expression expression;
	private List<Expression> filteredExpressions;
	private PatternLineStyleListener patternLineStyle;
	private ControlDecoration hintDecoration;
	private ComputePatternDependenciesJob dependencyJob;
	private MagicComposite mc;
	private EMFDataBindingContext context;
	private String mandatoryFieldLabel;
	private EObject contextInput;
	private Binding patternBinding;
	private ControlDecoration helpDecoration;

	public PatternExpressionViewer(Composite parent, int style) {
		super(parent, style);
		setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create()) ;
		mc = new MagicComposite(this, SWT.INHERIT_DEFAULT);
		mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
		mc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		createTextViewer();
		createExpressionViewer();
		createEditorSwitch();
		mc.hide(viewer.getControl());
		mc.show(expressionViewer.getControl());

	}

	private void createEditorSwitch() {
		final Link switchControl = new Link(mc, SWT.NONE);
		switchControl.setText(Messages.switchEditor);
		switchControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switchEditorType();
			}
		});
	}

	protected void initializeEditorType() {
		if(ExpressionConstants.PATTERN_TYPE.equals(expression.getType())){
			expression.setName("<pattern-expression>");
			mc.hide(expressionViewer.getControl());
			mc.show(viewer.getControl());
			helpDecoration.show();
			mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
		}else{
			mc.hide(viewer.getControl());
			mc.show(expressionViewer.getControl());
			helpDecoration.hide();
			mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
		}
		bindExpression();
		mc.layout(true, true);
	}

	protected void switchEditorType() {
		if(MessageDialog.openQuestion(mc.getShell(), Messages.eraseExpressionTitle,Messages.eraseExpressionMsg)){
			if(!expressionViewer.getControl().isVisible()){
				helpDecoration.hide();
				mc.hide(viewer.getControl());
				mc.show(expressionViewer.getControl());
				expression.setContent("");
				expression.setName("");
				expression.setInterpreter(null);
				expression.setType(ExpressionConstants.CONSTANT_TYPE);
				expressionViewer.setSelection(new StructuredSelection(expression));
				mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
			}else{
				mc.hide(expressionViewer.getControl());
				mc.show(viewer.getControl());
				expression.setContent("");
				expression.setName("<pattern-expression>");
				expression.setInterpreter(null);
				expression.setType(ExpressionConstants.PATTERN_TYPE);
				helpDecoration.show();
				bindPatternExpression();
				mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(15, 25, 0, 0).create());
			}
			mc.layout(true, true);
		}
	}


	private void bindExpression() {
		if(context != null){
			bindPatternExpression();
			bindExpressionViewer();
		}
	}


	private void bindExpressionViewer() {
		expressionViewer.setContext(contextInput);
		if(mandatoryFieldLabel != null){
			expressionViewer.setMandatoryField(mandatoryFieldLabel, context);
		}
		expressionViewer.setSelection(new StructuredSelection(expression));
	}


	private void bindPatternExpression() {
		UpdateValueStrategy startegy = new UpdateValueStrategy();
		if(mandatoryFieldLabel != null){
			startegy.setAfterConvertValidator(new EmptyInputValidator(mandatoryFieldLabel));
		}
		if(patternBinding != null){
			patternBinding.dispose();
			patternBinding = null;
		}
		patternBinding = context.bindValue(SWTObservables.observeText(viewer.getTextWidget(),SWT.Modify), EMFObservables.observeValue(expression, ExpressionPackage.Literals.EXPRESSION__CONTENT),startegy,null);
	}


	protected void createExpressionViewer() {
		expressionViewer = new ExpressionViewer(mc, SWT.BORDER, null);
		expressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
	}



	protected void createTextViewer() {
		viewer = new TextViewer(mc, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL) ;
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		helpDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.RIGHT,this);
		helpDecoration.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
		helpDecoration.setDescriptionText(Messages.patternViewerHelp);
		helpDecoration.setMarginWidth(2);

		hintDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.LEFT,this);
		hintDecoration.setImage(Pics.getImage(PicsConstants.hint));
		hintDecoration.setMarginWidth(2);
		hintDecoration.setShowHover(true);
		hintDecoration.setShowOnlyOnFocus(true);
		hintDecoration.hide();


		viewer.setDocument(new Document());
		patternLineStyle = new PatternLineStyleListener(viewer.getDocument());
		viewer.getTextWidget().addLineStyleListener(patternLineStyle) ;
		viewer.addTextListener(new ITextListener() {

			@Override
			public void textChanged(TextEvent event) {
				viewer.getTextWidget().notifyListeners(SWT.Modify,new Event());
			}
		});
		contentAssisProcessor = new ExpressionContentAssistProcessor(viewer.getDocument()) ;
		final ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(contentAssisProcessor,IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setShowEmptyList(true);
		assistant.install(viewer);
		assistant.enableAutoActivation(true);
		viewer.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.keyCode) {
				case SWT.F1:
					assistant.showPossibleCompletions();
					break;
				default:
					//ignore everything else
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == SWT.SPACE){
					assistant.showPossibleCompletions();
				}
			}
		});



		ISWTObservableValue observable = SWTObservables.observeDelayedValue(400, SWTObservables.observeText(getTextControl(), SWT.Modify));
		observable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				updateExpressionDependencies();
			}
		});

		helpDecoration.show();
	}

	private void updateExpressionDependencies() {
		dependencyJob.schedule();
	}

	@Override
	public void dispose() {
		super.dispose();
		if(dependencyJob != null){
			dependencyJob.cancel();
		}
	}

	public void setContextInput(EObject input){
		this.contextInput = input;
		manageNatureProviderAndAutocompletionProposal(input) ;
	}

	public void setExpression(final Expression expression){
		this.expression = expression;
		initializeEditorType();
	}


	protected void manageNatureProviderAndAutocompletionProposal(Object input) {
		if(expressionNatureProvider != null){
			expressionNatureProvider.setContext((EObject) input);
		}
		filteredExpressions =  getFilteredExpressions() ;
		Set<Expression> expressionSet = new HashSet<Expression>(filteredExpressions);
		contentAssisProcessor.setExpressions(expressionSet);
		patternLineStyle.setExpressions(expressionSet);
		dependencyJob = new ComputePatternDependenciesJob(viewer.getDocument(),filteredExpressions) ;
		dependencyJob.addJobChangeListener(new IJobChangeListener() {

			@Override
			public void aboutToRun(IJobChangeEvent event) {}

			@Override
			public void awake(IJobChangeEvent event) {}

			@Override
			public void done(IJobChangeEvent event) {
				if(dependencyJob != null){
					List<EObject> deps = dependencyJob.getDependencies(viewer.getDocument().get()) ;
					expression.getReferencedElements().clear() ;
					if(deps != null && !deps.isEmpty()){
						expression.getReferencedElements().addAll(deps) ;
					}
				}
			}

			@Override
			public void running(IJobChangeEvent event) {}

			@Override
			public void scheduled(IJobChangeEvent event) {}

			@Override
			public void sleeping(IJobChangeEvent event) {}
		});
	}

	private List<Expression> getFilteredExpressions() {
		List<Expression> filteredExpressions = new ArrayList<Expression>() ;
		Expression[] expressions = expressionNatureProvider.getExpressions();
		EObject input =  expressionNatureProvider.getContext() ;
		if(expressions != null){
			filteredExpressions.addAll(Arrays.asList(expressions)) ;
			if(input != null){
				for(Expression exp : expressions) {
					for(ViewerFilter filter : filters){
						if(filter != null && !filter.select(viewer, input, exp)){
							filteredExpressions.remove(exp) ;
						}
					}
				}
			}
		}
		return filteredExpressions ;
	}

	public void addFilter(ViewerFilter viewerFilter) {
		filters.add(viewerFilter) ;
		expressionViewer.addFilter(viewerFilter);
	}

	public StyledText getTextControl(){
		return viewer.getTextWidget() ;
	}


	public void setHint(String hint) {
		hintDecoration.setDescriptionText(hint);
		hintDecoration.show();
		expressionViewer.setMessage(hint, IStatus.INFO);
	}


	public void setEMFBindingContext(EMFDataBindingContext context) {
		this.context = context;
	}

	public void setMandatoryField(String mandatoryFieldLabel) {
		this.mandatoryFieldLabel = mandatoryFieldLabel;
	}
}
