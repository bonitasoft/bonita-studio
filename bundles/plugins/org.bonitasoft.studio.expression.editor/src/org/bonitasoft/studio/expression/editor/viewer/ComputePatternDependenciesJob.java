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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * @author Romain Bioteau
 */
public class ComputePatternDependenciesJob extends Job {

	private final Map<String, List<EObject>> cache;
	private IDocument document;
	private List<Expression> filteredExpressions;

	public void setFilteredExpressions(List<Expression> filteredExpressions) {
		this.filteredExpressions = filteredExpressions;
	}

	public ComputePatternDependenciesJob(final IDocument document,List<Expression> filteredExpressions) {
		super(ComputePatternDependenciesJob.class.getName());
		cache = new HashMap<String, List<EObject>>();
		this.document = document;
		this.filteredExpressions = filteredExpressions;
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		final String expression = getTextContent();
		if (cache.get(expression) == null) {
			final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
			final Set<String> addedExp = new HashSet<String>();
			final List<EObject> deps = new ArrayList<EObject>();
			for(Expression exp : filteredExpressions){
				IRegion index;
				try {
					int i = 0;
					index = finder.find(0,exp.getName(), true, true, true, false);
					while (index != null) {
						if(!addedExp.contains(exp.getName())){
							deps.add(ExpressionHelper.createDependencyFromEObject(exp.getReferencedElements().get(0)));
							addedExp.add(exp.getName());
						}
						i = i + index.getLength();
						index = finder.find(i,exp.getName(), true, true, true, false);
					}
				} catch (final BadLocationException e1) {
					// Just ignore them
				}
			}
			cache.put(expression, deps);
		}
		return Status.OK_STATUS;
	}

	protected String getTextContent() {
		return document.get();
	}

	public List<EObject> getDependencies(final String expression) {
		return cache.get(expression);
	}

	public IDocument getDocument() {
		return document;
	}

	public void setDocument(final IDocument document) {
		this.document = document;
	}
}
