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

import java.util.stream.Collectors;

import org.bonitasoft.studio.groovy.library.IFunction;
import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.eclipse.jface.text.templates.Template;

public class FunctionScriptProposalSupplier extends ScriptProposalSupplier {

    private IFunction function;

    public FunctionScriptProposalSupplier(IFunction function) {
        this.function = function;
    }

    @Override
    protected ScriptProposal toProposal() {
        Template template = new Template(function.getSignature() + " - " + function.getOwner(),
                function.getDocumentation(),
                GroovyQuickFixPlugin.GROOVY_CONTEXT_TYPE,
                createFunctionPattern(function),
                true);
        ScriptProposal scriptProposal = new ScriptProposal(function.getSignature() + " - " + function.getOwner(),
                template);
        scriptProposal.setDescription(template.getDescription());
        return scriptProposal;
    }

    private String createFunctionPattern(IFunction function) {
        String toInsert = "";
        if (function.isStatic()) {
            toInsert += "${:newType(" + function.getOwner() + ")}";
        } else {
            toInsert = "new ${:newType(" + function.getOwner() + ")}()";
        }
        toInsert += "." + function.getName();
        if (function.getParametersCount() > 0) {
            toInsert += "("
                    + function.getParameterNames().stream().map(p -> "${" + p + "}").collect(Collectors.joining(", "))
                    + ")";
        } else {
            toInsert += "()";
        }
        return toInsert;
    }
}
