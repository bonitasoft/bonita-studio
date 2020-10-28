/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.providers;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptExpressionContext;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.StyledTextDropTargetEffect;
import org.eclipse.swt.dnd.DropTargetEvent;

public class DropProposalTargetEffect extends StyledTextDropTargetEffect {

    private GroovyEditor editor;
    private ScriptExpressionContext context;

    public DropProposalTargetEffect(StyledText styledText, GroovyEditor editor, ScriptExpressionContext context) {
        super(styledText);
        this.editor = editor;
        this.context = context;
    }

    @Override
    public void drop(DropTargetEvent event) {
        super.drop(event);
        String proposalId = (String) event.data;
        ScriptExpressionContext.findProposalById(proposalId, context.getCategories())
            .ifPresent(proposal -> proposal.apply(editor));
    }

}
