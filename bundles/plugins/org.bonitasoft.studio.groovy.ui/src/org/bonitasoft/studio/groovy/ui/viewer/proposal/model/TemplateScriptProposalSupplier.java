/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import org.eclipse.jface.text.templates.Template;


public class TemplateScriptProposalSupplier extends ScriptProposalSupplier {
    

    private Template template;

    public TemplateScriptProposalSupplier(Template template) {
        this.template = template;
    }
    
    @Override
    protected ScriptProposal toProposal() {
        ScriptProposal scriptProposal = new ScriptProposal(template.getName(), template);
        scriptProposal.setDescription(template.getDescription());
        return scriptProposal;
    }

}
