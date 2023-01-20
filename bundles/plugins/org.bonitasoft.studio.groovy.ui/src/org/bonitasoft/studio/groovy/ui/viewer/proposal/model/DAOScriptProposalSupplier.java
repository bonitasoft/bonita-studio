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

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.groovy.library.GroovyFunction;
import org.bonitasoft.studio.groovy.library.IFunction;
import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jface.text.templates.Template;

public class DAOScriptProposalSupplier extends ScriptProposalSupplier {

    private IMethod method;
    private String daoName;
    private BusinessObjectModel bdm;

    public DAOScriptProposalSupplier(IMethod method, String daoName, BusinessObjectModel bdm) {
        this.method = method;
        this.daoName = daoName;
        this.bdm = bdm;
    }

    @Override
    protected ScriptProposal toProposal() {
        GroovyFunction function = new GroovyFunction(method);
        Template template = new Template(function.getSignature(),
                function.getDocumentation(),
                GroovyQuickFixPlugin.GROOVY_CONTEXT_TYPE,
                createDAOFunctionPattern(daoName, function),
                true);
        ScriptProposal scriptProposal = new ScriptProposal(function.getName(),
                template);
        findQuery(method, bdm).ifPresent(q -> scriptProposal.setDescription(q.getDescription()));
        return scriptProposal;
    }

    private static Optional<Query> findQuery(IMethod method, BusinessObjectModel bdm) {
        String daoFullyQualifiedName = method.getDeclaringType().getFullyQualifiedName();
        return ScriptExpressionContext.findBusinessObjectFromDAO(daoFullyQualifiedName, bdm)
                .flatMap(bo -> bo.getQueries().stream()
                        .filter(q -> q.getName().equals(method.getElementName()))
                        .findFirst())
                .filter(Objects::nonNull);
    }

    private static String createDAOFunctionPattern(String daoName, IFunction function) {
        String toInsert = daoName + "." + function.getName();
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
