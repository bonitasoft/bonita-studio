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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;


/**
 * @author Romain Bioteau
 *
 */
public class PatternExpressionViewer extends Composite {

	private TextViewer viewer;
	private final IExpressionNatureProvider expressionNatureProvider = new ExpressionContentProvider();
	private ExpressionContentAssistProcessor contentAssisProcessor;
	private final Set<ViewerFilter> filters = new HashSet<ViewerFilter>();
	private Expression patternExpression;
	private List<Expression> filteredExpressions;
	private PatternLineStyleListener patternLineStyle;
	private ControlDecoration hintDecoration;


	public PatternExpressionViewer(Composite parent, int style) {
		super(parent, style);
		setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(10, 20, 0, 0).create()) ;
		createTextViewer();
	}


	protected void createTextViewer() {
		viewer = new TextViewer(this, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL) ;
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		final ControlDecoration helpDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.RIGHT);
		helpDecoration.hide();
		helpDecoration.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
		helpDecoration.setDescriptionText(Messages.patternViewerHelp);
		helpDecoration.setMarginWidth(2);
		helpDecoration.setShowOnlyOnFocus(true);

		hintDecoration = new ControlDecoration(viewer.getControl(), SWT.TOP | SWT.LEFT);
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

		viewer.getTextWidget().addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				patternExpression.getReferencedElements().clear();
				String content = viewer.getDocument().get();
				if(content != null && !content.isEmpty()){
					final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(viewer.getDocument());
					final Set<String> addedExp = new HashSet<String>();
					for(Expression exp : filteredExpressions){
						IRegion index;
						try {
							int i = 0;
							index = finder.find(0,exp.getName(), true, true, true, false);
							while (index != null) {
								if(PatternLineStyleListener.isNotEscapeWord(content, index.getOffset())){
									if(!addedExp.contains(exp.getName())){
										patternExpression.getReferencedElements().add(EcoreUtil.copy(exp));
										addedExp.add(exp.getName());
									}
								}
								i = i + index.getLength();
								index = finder.find(i,exp.getName(), true, true, true, false);
							}
						} catch (final BadLocationException e1) {
							// Just ignore them
						}
					}
				}
			}
		});
		helpDecoration.show();
	}

	/** Check if a word is contained as a sub-word in each element of a list of expressions
	 * 
	 * @param word word to search in the expressions of the list
	 * @param startIndexWord index of the word in the message
	 * @param message the currentContent
	 * @param eList list of Expressions in the current Content
	 * @return 
	 */
	public boolean isNotIncludedInWord(String word,  int startIndexWord, String message, EList<EObject> eList){
		for(Object exp : eList){
			if(exp instanceof Expression){
				String expWord = ((Expression)exp).getName();
				int offset = 0;
				while(message.indexOf( expWord, offset)!=-1){
					int startIndexReference = message.indexOf(expWord, offset);
					int endIndexReference = startIndexReference+expWord.length();
					if(startIndexWord-offset >= startIndexReference && startIndexWord < endIndexReference){
						return false;
					}
					offset=endIndexReference;
				}
			}
		}
		return true;
	}

	public void setContextInput(EObject input){
		manageNatureProviderAndAutocompletionProposal(input) ;
	}

	public void setPatternExpression(final Expression patternExpression){
		Assert.isLegal(patternExpression.getType() != null && patternExpression.getType().equals(ExpressionConstants.PATTERN_TYPE)) ;
		this.patternExpression = patternExpression;
	}


	protected void manageNatureProviderAndAutocompletionProposal(Object input) {
		if(expressionNatureProvider != null){
			expressionNatureProvider.setContext((EObject) input);
		}
		filteredExpressions =  getFilteredExpressions() ;
		Set<Expression> expressionSet = new HashSet<Expression>(filteredExpressions);
		contentAssisProcessor.setExpressions(expressionSet);
		patternLineStyle.setExpressions(expressionSet);
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
		Collections.sort(filteredExpressions, new Comparator<Expression>() {

			@Override
			public int compare(Expression exp0, Expression exp1) {
				return -Integer.valueOf(exp0.getName().length()).compareTo(Integer.valueOf(exp1.getName().length()));
			}

		});
		return filteredExpressions ;
	}

	public void addFilter(ViewerFilter viewerFilter) {
		filters.add(viewerFilter) ;
	}

	public StyledText getTextControl(){
		return viewer.getTextWidget() ;
	}


	public void setHint(String hint) {
		hintDecoration.setDescriptionText(hint);
		hintDecoration.show();
	}
}
