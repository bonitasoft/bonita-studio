/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.pattern.contentAssist;

import static com.google.common.base.Predicates.not;

import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.groovy.contentassist.ExtendedJavaContentAssistInvocationContext;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.eclipse.codeassist.processors.IProposalFilter;
import org.codehaus.groovy.eclipse.codeassist.proposals.GroovyFieldProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.GroovyMethodProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.runtime.ProcessGroovyMethods;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import groovy.lang.GroovyObjectSupport;
import groovy.lang.Script;

public class GenericProposalFilter implements IProposalFilter {

    private static final Set<String> GENERIC_CLASSES;
    static {
        GENERIC_CLASSES = Sets.newHashSet(Object.class.getName(), GroovyObjectSupport.class.getName(), Script.class.getName(),
                ProcessGroovyMethods.class.getName());
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.codeassist.processors.IProposalFilter#filterProposals(java.util.List,
     * org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext, org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext)
     */
    @Override
    public List<IGroovyProposal> filterProposals(List<IGroovyProposal> proposals, ContentAssistContext contentAssistContext,
            JavaContentAssistInvocationContext invovationContext) {
        if (invovationContext instanceof ExtendedJavaContentAssistInvocationContext) {
            filterProposals(proposals);
        }
        return proposals;
    }

    private void filterProposals(List<IGroovyProposal> proposals) {
        Iterables.removeIf(proposals, not(validProposal()));
    }

    private Predicate<? super IGroovyProposal> validProposal() {
        return new Predicate<IGroovyProposal>() {

            @Override
            public boolean apply(IGroovyProposal proposal) {
                if (proposal instanceof GroovyFieldProposal) {
                    final FieldNode field = ((GroovyFieldProposal) proposal).getField();
                    final ClassNode declaringClass = field.getDeclaringClass();
                    return compatibleClasses(declaringClass.getName());
                }
                if (proposal instanceof GroovyMethodProposal) {
                    final MethodNode method = ((GroovyMethodProposal) proposal).getMethod();
                    final ClassNode declaringClass = method.getDeclaringClass();
                    return compatibleClasses(declaringClass.getName());
                }
                return false;
            }

            private boolean compatibleClasses(String name) {
                return !GENERIC_CLASSES.contains(name);
            }

        };
    }
}
