/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IDocumentPartitioningListener;
import org.eclipse.jface.text.ITypedRegion;


public class PatternExpressionModelBuilder implements IDocumentPartitioningListener {

    private List<Expression> scope;
    private Expression expression;


    public void setScope(List<Expression> scope) {
        this.scope = scope;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.IDocumentPartitioningListener#documentPartitioningChanged(org.eclipse.jface.text.IDocument)
     */
    @Override
    public void documentPartitioningChanged(IDocument document) {
        if (expression == null) {
            return;
        }
        expression.getReferencedElements().clear();
        final Set<String> addedExp = new HashSet<>();
        final IDocumentPartitioner partitioner = document.getDocumentPartitioner();
        final ITypedRegion[] regions = partitioner.computePartitioning(0, document.getLength());
        final List<ITypedRegion> groovyPartions = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            final ITypedRegion partition = regions[i];
            if (PatternExpressionViewer.GROOVY_EXPRESSION_CONTENT_TYPE.equals(partition.getType())) {
                groovyPartions.add(partition);
            }
        }

        for (final ITypedRegion region : groovyPartions) {
            try {
                final String expressionContent = strip(document.get(region.getOffset(), region.getLength()));
                if (expressionContent != null && !addedExp.contains(expressionContent)) {
                    final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(expressionContent, String.class.getName());
                    groovyScriptExpression.setName(expressionContent);
                    final Document expDoc = new Document();
                    expDoc.set(expressionContent);
                    final FindReplaceDocumentAdapter expDocFinder = new FindReplaceDocumentAdapter(expDoc);
                    for (final Expression exp : scope) {
                        if (expDocFinder.find(0, exp.getName(), true, true, true, false) != null) {
                            groovyScriptExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(exp));
                        }
                    }
                    expression.getReferencedElements().add(groovyScriptExpression);
                    addedExp.add(expressionContent);
                }
            } catch (final BadLocationException e) {

            }
        }
    }

    private String strip(String expression) {
        return expression.substring(2, expression.length() - 1);
    }
}
