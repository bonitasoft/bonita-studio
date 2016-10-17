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
import org.eclipse.jface.text.Document;
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

    public ComputePatternDependenciesJob(final IDocument document, List<Expression> filteredExpressions) {
        super(ComputePatternDependenciesJob.class.getName());
        cache = new HashMap<>();
        this.document = document;
        this.filteredExpressions = filteredExpressions;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        final String expression = getTextContent();
        if (cache.get(expression) == null) {
            final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
            final Set<String> addedExp = new HashSet<>();
            final List<EObject> deps = new ArrayList<>();
            IRegion index;
            try {
                int i = 0;
                index = finder.find(0, "${", true, true, false, false);
                while (index != null) {
                    final IRegion end = finder.find(index.getOffset(), "}", true, true, false, false);
                    String expressionContent = "";
                    if (end != null) {
                        expressionContent = document.get(index.getOffset() + 2, end.getOffset() - index.getOffset() - 2);
                    }
                    if (!addedExp.contains(expressionContent)) {
                        final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(expressionContent, String.class.getName());
                        groovyScriptExpression.setName(expressionContent);
                        final Document expDoc = new Document();
                        expDoc.set(expressionContent);
                        final FindReplaceDocumentAdapter expDocFinder = new FindReplaceDocumentAdapter(expDoc);
                        for (final Expression exp : filteredExpressions) {
                            if (expDocFinder.find(0, exp.getName(), true, true, true, false) != null) {
                                groovyScriptExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(exp));
                            }
                        }

                        deps.add(groovyScriptExpression);
                        addedExp.add(expressionContent);
                    }
                    i = end.getOffset();
                    index = finder.find(i, "${", true, true, false, false);
                }
            } catch (final BadLocationException e1) {
                e1.printStackTrace();
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
