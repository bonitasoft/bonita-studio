/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex.builder;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLCompletionEngine;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLCompletionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

/**
 * @author Romain Bioteau
 */
public class BonitaSQLCompletionEngine extends SQLCompletionEngine {

    private org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaSQLCompletionProposalFactory proposalFactory;
    private ExpressionLabelProvider labelProvider;

    public BonitaSQLCompletionEngine() {
        labelProvider = new ExpressionLabelProvider();
    }

    public ICompletionProposal[] computeBonitaProposals(IDocument doc,
            ITypedRegion partition, int docOffset) {

        /* If the document location is bad, bail out. */
        if (docOffset < 0)
            return null;

        /*
         * Get syntactic content assist proposals that are specific to the current
         * statement type.
         */
        int offset = getPartitionOffset(doc, partition, docOffset, "SELECT ", 1); //$NON-NLS-1$

        Expression[] expressions = proposalFactory.getExpressions();
        List<ICompletionProposal> bonitaProposalArray = new ArrayList<ICompletionProposal>();
        for (Expression exp : expressions) {
            String tmpStr = exp.getName();
            if (exp.getReturnType().equals(String.class.getName())) {
                tmpStr = "'${" + tmpStr + "}'";
            } else {
                tmpStr = "${" + tmpStr + "}";
            }
            IContextInformation info = null;
            // RATLC01136221 bgp 14Jan2007 - begin
            SQLCompletionProposal sqlProposal = null;

            if (docOffset > offset)
                sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, labelProvider.getImage(exp), labelProvider.getText(exp),
                        info);
            else
                sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, 0, labelProvider.getImage(exp), labelProvider.getText(exp), info);
            sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
            bonitaProposalArray.add(sqlProposal);
        }

        return bonitaProposalArray.toArray(new ICompletionProposal[bonitaProposalArray.size()]);
    }

    private Expression getExpression(String name, Expression[] expressions) {
        for (Expression e : expressions) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public void setProposalFactory(BonitaSQLCompletionProposalFactory proposalFactory) {
        this.proposalFactory = proposalFactory;

    }

}
