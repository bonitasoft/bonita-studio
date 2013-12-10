package org.bonitasoft.studio.common.refactoring;




import java.util.ArrayList;
import java.util.List;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class BonitaGroovyRefactoringAction implements IWorkbenchWindowActionDelegate{

	public static final int REFACTOR_OPERATION=0;
	public static final int REMOVE_OPERATION=1;


	private String elementToRefactorName;
	private List<Expression> groovyScriptExpressions;
	private String newElementName;
	private BonitaCompareEditorInput editorInput;
	private  String regex;
	private  CompoundCommand cc;
	private EditingDomain domain;
	private boolean status=false;
	public boolean canExecute;
	private int currentOperation;

	public BonitaGroovyRefactoringAction(String elementToRefactorName,String newElementName,List<Expression> groovyScriptExpressions,CompoundCommand cc,EditingDomain domain,int operation) throws JavaModelException{
		this.elementToRefactorName = elementToRefactorName;
		this.groovyScriptExpressions = groovyScriptExpressions;
		this.newElementName = newElementName;
		this.regex =elementToRefactorName;
		this.cc = cc;
		this.domain = domain;
		this.currentOperation =operation;
		initGroovyRefactoring();
	}


	private void initGroovyRefactoring() {


		final CompareConfiguration config=new CompareConfiguration();
		config.setRightEditable(true);
		config.setLeftEditable(false);
		config.setLeftLabel(Messages.currentScript);
		config.setRightLabel(Messages.refactoredScript);
		config.setProperty(CompareConfiguration.USE_OUTLINE_VIEW, true);
		editorInput =new BonitaCompareEditorInput(config,groovyScriptExpressions,performRefactoringForAllScripts(),currentOperation,elementToRefactorName,newElementName);
		editorInput.setCompoundCommand(cc, domain);

	}


	@Override
	public void run(IAction action) {
		CompareUI.openCompareDialog(editorInput);
		status = editorInput.getStatus();

	}

	private List<Expression> performRefactoringForAllScripts(){
		List<Expression> newExpressions = new ArrayList<Expression>(groovyScriptExpressions.size());
		for (Expression expr:groovyScriptExpressions){
			Expression newExpr = EcoreUtil.copy(expr);
			newExpr.setContent(performRefactoring(expr.getContent()));
			newExpressions.add(newExpr);
		}
		return newExpressions;
	}

	private String performRefactoring(String script){
		//String regex = "([\\D\\W^_])"+elementToRefactorName+"[\\D\\W]";
		String contextRegex="[\\D\\W^_]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(script);
		StringBuffer buf=new StringBuffer();
		while(m.find()){
			String prefix=null;
			String suffix=null;
			if (m.start()>0 ){
				prefix =script.substring(m.start()-1, m.start());
			}
				if (m.end()<script.length()){
					suffix = script.substring(m.end(),m.end()+1);
				}
				if (prefix==null && suffix==null){
					m.appendReplacement(buf, newElementName);
				} else {
					if (prefix!=null && prefix.matches(contextRegex) && suffix==null){
						m.appendReplacement(buf, newElementName);
					} else {
						if (prefix==null && suffix!=null && suffix.matches(contextRegex)){
							m.appendReplacement(buf, newElementName);
						} else {
							if (prefix!=null && suffix!=null && prefix.matches(contextRegex) && suffix.matches(contextRegex)){
								m.appendReplacement(buf, newElementName);
							}
						}
					}
				}

			}
			m.appendTail(buf);
		return buf.toString();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}


	@Override
	public void dispose() {

	}


	@Override
	public void init(IWorkbenchWindow window) {

	}

	public boolean getStatus(){
		return status;
	}
}
