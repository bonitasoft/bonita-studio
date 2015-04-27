/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.refactoring.core.script;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IRegion;

public class TextExpressionScriptContainer extends ExpressionScriptContrainer {

    public TextExpressionScriptContainer(final Expression expression, final EAttribute dependencyNameAttribute) {
        super(expression, dependencyNameAttribute);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#updateScript(java.util.List)
     */
    @Override
    public void updateScript(final List<ReferenceDiff> referenceDiffs, final IProgressMonitor monitor) {
        String textRefactored = getScript();
        for (final ReferenceDiff diff : referenceDiffs) {
            textRefactored = performTextReplacement(toPattern(diff.getOldRef()), toPattern(diff.getNewRef()), textRefactored);
        }
        setNewScript(textRefactored);
    }

    private String toPattern(final String reference) {
        if (getModelElement().getType().equals(ExpressionConstants.PATTERN_TYPE)) {
            return "${" + reference + "}";
        }
        return reference;
    }

    private String performTextReplacement(final String elementNameToUpdate, final String newElementName, final String script) {
        final Document document = new Document(script);
        final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
        IRegion region;
        try {
            int i = 0;
            region = finder.find(0, elementNameToUpdate, true, true, false, false);
            while (region != null) {
                i = i + region.getLength();
                finder.replace(newElementName, false);
                region = finder.find(i, elementNameToUpdate, true, true, false, false);
            }
        } catch (final BadLocationException e1) {
            // Just ignore them
        }
        return document.get();
    }

}
