/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
 
package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.eclipse.codeassist.ProposalUtils;
import org.codehaus.groovy.eclipse.codeassist.creators.AbstractProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.creators.IProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.proposals.GroovyMethodProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaMethodProposalCreator extends AbstractProposalCreator
		implements IProposalCreator {

	@Override
	public List<IGroovyProposal> findAllProposals(ClassNode type,
			Set<ClassNode> categories, String prefix, boolean isStatic,
			boolean isPrimary) {
		List<MethodNode> allMethods = type.getAllDeclaredMethods();
        List<IGroovyProposal> groovyProposals = new LinkedList<IGroovyProposal>();
       
        for (MethodNode method : allMethods) {
        	if ((!isStatic || method.isStatic()) &&
                    checkName(method.getName()) &&
                    ProposalUtils.looselyMatches(prefix, method.getName())) {
        		groovyProposals.add(new GroovyMethodProposal(method));
            }
        }
        return groovyProposals;
	}

}
