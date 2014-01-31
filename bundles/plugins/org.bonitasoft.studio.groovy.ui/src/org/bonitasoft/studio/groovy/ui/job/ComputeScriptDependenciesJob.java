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
package org.bonitasoft.studio.groovy.ui.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * @author Romain Bioteau
 */
public class ComputeScriptDependenciesJob extends Job {

	private final Map<String, List<EObject>> cache;

	private List<ScriptVariable> nodes;

	private EObject context;

	private IDocument document;

	public ComputeScriptDependenciesJob(final IDocument document) {
		super(ComputeScriptDependenciesJob.class.getName());
		cache = new HashMap<String, List<EObject>>();
		this.document = document;
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		final String expression = document.get();
		if (cache.get(expression) == null) {
			final Set<String> foundVariable = new HashSet<String>();
			final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
			for (final ScriptVariable f : nodes) {
				IRegion index;
				try {
					index = finder.find(0, f.getName(), true, true, true, false);
					while (index != null && !foundVariable.contains(f.getName())) {
						if (index != null && !isInAStringExpression(f.getName(),index,expression)) {
							foundVariable.add(f.getName());
						}
						index = finder.find(index.getOffset()+index.getLength(), f.getName(), true, true, true, false);
					}
				} catch (final BadLocationException e) {
					// Just ignore them
				}
			}

			final List<EObject> deps = new ArrayList<EObject>();
			addDependenciesForFoundVariables(foundVariable, deps);
			cache.put(expression, deps);
		}
		return Status.OK_STATUS;
	}

	protected void addDependenciesForFoundVariables(
			final Set<String> foundVariable, final List<EObject> deps) {
		variablesloop: for (final String name : foundVariable) {
			final Expression engineConstantExpression = GroovyUtil.getEngineConstantExpression(name);
			if (engineConstantExpression != null) {
				deps.add(EcoreUtil.copy(engineConstantExpression));
				continue variablesloop;
			}
			for(IExpressionProvider provider : ExpressionEditorService.getInstance().getExpressionProviders()){
				if(provider.isRelevantFor(context)){
					for(Expression exp : provider.getExpressions(context)){
						if(exp.getName().equals(name) && !exp.getReferencedElements().isEmpty()){
							deps.add(ExpressionHelper.createDependencyFromEObject(exp.getReferencedElements().get(0)));
							continue variablesloop;
						}
					}
				}
			}
		}
	}

	private boolean isInAStringExpression(String name, IRegion index,String expression) {
		if(index.getOffset() > 0){
			int nbStringChars1 = 0;
			for(int i = 0 ; i<index.getOffset();i++){
				char c = expression.charAt(i);
				if('"' == c &&  (i == 0 || expression.charAt(i-1) != '\\')){
					nbStringChars1++;
				}
			}
			return !(nbStringChars1 % 2 == 0 );

		}
		return false;
	}

	public List<EObject> getDependencies(final String expression) {
		return cache.get(expression);
	}

	public List<ScriptVariable> getNodes() {
		return nodes;
	}

	public void setNodes(final List<ScriptVariable> nodes) {
		this.nodes = new ArrayList<ScriptVariable>(nodes);
	}

	public EObject getContext() {
		return context;
	}

	public void setContext(final EObject context) {
		this.context = context;
	}

	public IDocument getDocument() {
		return document;
	}

	public void setDocument(final IDocument document) {
		this.document = document;
	}
}
