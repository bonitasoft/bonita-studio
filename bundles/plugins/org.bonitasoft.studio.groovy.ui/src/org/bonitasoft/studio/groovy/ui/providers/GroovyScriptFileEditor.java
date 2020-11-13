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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.providers;

import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptFileEditor extends GroovyScriptExpressionEditor {

    public static final String CONTEXT_DATA_KEY = "context";

    public static final String BONITA_KEYWORDS_DATA_KEY = "bonita.keywords";

    public static final String PROCESS_VARIABLES_DATA_KEY = "process.variables";

    public GroovyScriptFileEditor() {
        super();
    }

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        createProposalComposite(mainComposite, ctx);
        createGroovyEditor(mainComposite, false);
        return mainComposite;
    }

    @Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context,
            final Expression inputExpression, final ViewerFilter[] filters,
            final ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        this.context = context;

        groovyViewer.getDocument().set(inputExpression.getContent());
        IExpressionNatureProvider natureProvider = null;
        if (expressionViewer != null) {
            natureProvider = expressionViewer.getExpressionNatureProvider();
        }
        groovyViewer.setContext(null, context, filters, natureProvider);
        groovyViewer.getSourceViewer().getTextWidget().setData(BONITA_KEYWORDS_DATA_KEY, null);
        groovyViewer.getSourceViewer().getTextWidget().setData(PROCESS_VARIABLES_DATA_KEY, null);
        groovyViewer.getSourceViewer().getTextWidget().setData(CONTEXT_DATA_KEY, null);
        sourceViewer.getDocument().addDocumentListener(new IDocumentListener() {

            @Override
            public void documentChanged(final DocumentEvent event) {
                GroovyScriptFileEditor.this.inputExpression.setContent(event.getDocument().get());
            }

            @Override
            public void documentAboutToBeChanged(final DocumentEvent event) {
            }
        });
    }

}
