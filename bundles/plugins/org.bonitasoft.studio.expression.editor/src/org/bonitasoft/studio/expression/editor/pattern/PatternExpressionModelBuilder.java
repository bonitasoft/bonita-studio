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
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;

import com.google.common.collect.Sets;

public class PatternExpressionModelBuilder implements IDocumentListener {

    private static final String GROOVY_START_TAG = "${";
    private static final String GROOVY_END_TAG = "}";

    private List<Expression> scope;
    private Expression expression;

    public void setScope(List<Expression> scope) {
        this.scope = scope;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    private List<ITypedRegion> allGroovyPartitions(IDocument document) {
        final IDocumentPartitioner partitioner = document.getDocumentPartitioner();
        final ITypedRegion[] regions = partitioner.computePartitioning(0, document.getLength());
        final List<ITypedRegion> groovyPartions = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            final ITypedRegion partition = regions[i];
            if (TextViewerDocumentViewerDelegate.GROOVY_EXPRESSION_CONTENT_TYPE.equals(partition.getType())) {
                groovyPartions.add(partition);
            }
        }
        return groovyPartions;
    }

    private String strip(String expression) {
        String strippedExpression = expression;
        if (strippedExpression.startsWith(GROOVY_START_TAG)) {
            strippedExpression = strippedExpression.substring(2);
        }
        if (strippedExpression.endsWith(GROOVY_END_TAG)) {
            strippedExpression = strippedExpression.substring(0, strippedExpression.length() - 1);
        }
        return strippedExpression;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.IDocumentListener#documentChanged(org.eclipse.jface.text.DocumentEvent)
     */
    @Override
    public void documentChanged(DocumentEvent event) {
        final IDocument document = event.getDocument();
        if (shouldBuild()) {
            doBuild(document, allGroovyPartitions(document));
        }
    }

    private boolean shouldBuild() {
        return expression != null;
    }

    private void doBuild(IDocument document, List<ITypedRegion> groovyPartitions) {
        final Set<String> parsedExpressions = Sets.newHashSet();
        expression.getReferencedElements().clear();
        for (final ITypedRegion region : groovyPartitions) {
            try {
                final String expressionContent = strip(document.get(region.getOffset(), region.getLength()));
                if (expressionContent != null && !parsedExpressions.contains(expressionContent)) {
                    final Expression groovyScriptExpression = ExpressionHelper
                            .createGroovyScriptExpression(expressionContent, String.class.getName());
                    groovyScriptExpression.setName(expressionContent);
                    final Document expDoc = new Document();
                    expDoc.set(expressionContent);
                    final FindReplaceDocumentAdapter expDocFinder = new FindReplaceDocumentAdapter(expDoc);
                    for (final Expression exp : scope) {
                        if (expDocFinder.find(0, exp.getName(), true, true, true, false) != null) {
                            groovyScriptExpression.getReferencedElements()
                                    .add(ExpressionHelper.createDependencyFromEObject(exp));
                        }
                    }
                    expression.getReferencedElements().add(groovyScriptExpression);
                    parsedExpressions.add(expressionContent);
                }
            } catch (final BadLocationException e) {

            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.IDocumentListener#documentAboutToBeChanged(org.eclipse.jface.text.DocumentEvent)
     */
    @Override
    public void documentAboutToBeChanged(DocumentEvent event) {

    }

}
