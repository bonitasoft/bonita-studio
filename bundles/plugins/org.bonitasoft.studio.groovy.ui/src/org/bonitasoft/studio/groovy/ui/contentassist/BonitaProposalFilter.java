/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.codeassist.processors.IProposalFilter;
import org.codehaus.groovy.eclipse.codeassist.proposals.GroovyMethodProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;

/**
 * @author Romain Bioteau
 */
public class BonitaProposalFilter implements IProposalFilter {

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.codeassist.processors.IProposalFilter#filterProposals(java.util.List,
     * org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext, org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext)
     */
    @Override
    public List<IGroovyProposal> filterProposals(final List<IGroovyProposal> proposals,
            final ContentAssistContext assist, final JavaContentAssistInvocationContext context) {
        final List<IGroovyProposal> filteredProposals = new ArrayList<IGroovyProposal>();
        for (final IGroovyProposal p : proposals) {
            if (p instanceof GroovyMethodProposal) {
                if (!(((GroovyMethodProposal) p).getMethod().getDeclaringClass().getName().equals("org.codehaus.groovy.runtime.DefaultGroovyMethods")
                        || ((GroovyMethodProposal) p).getMethod().getDeclaringClass().getName()
                                .equals("org.codehaus.groovy.runtime.DefaultGroovyStaticMethods"))) {
                    filteredProposals.add(p);
                }
            } else {
                filteredProposals.add(p);
            }
        }
        return filteredProposals;
    }

}
