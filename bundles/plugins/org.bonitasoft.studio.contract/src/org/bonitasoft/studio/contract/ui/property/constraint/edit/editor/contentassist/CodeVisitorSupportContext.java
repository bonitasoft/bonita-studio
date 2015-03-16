/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.contentassist;

import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.eclipse.codeassist.creators.MethodProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;


/**
 * @author Romain Bioteau
 *
 */
public class CodeVisitorSupportContext {

    private final String prefix;
    private final JavaContentAssistInvocationContext context;
    private final ContentAssistContext contentAssistContext;
    private final ClassLoader classLoader;
    private final IJavaCompletionProposalComputer completionComputer;
    private final ModuleNode moduleNode;
    private final MethodProposalCreator methodProposalCreator;

    public CodeVisitorSupportContext(final String prefix,
            final JavaContentAssistInvocationContext context,
            final ContentAssistContext contentAssistContext,
            final ClassLoader classLoader,
            final IJavaCompletionProposalComputer completionComputer,
            final MethodProposalCreator methodProposalCreator,
            final ModuleNode moduleNode) {
        this.prefix = prefix;
        this.context = context;
        this.contentAssistContext = contentAssistContext;
        this.classLoader = classLoader;
        this.completionComputer = completionComputer;
        this.methodProposalCreator = methodProposalCreator;
        this.moduleNode = moduleNode;
    }

    public String getPrefix() {
        return prefix;
    }

    public JavaContentAssistInvocationContext getContext() {
        return context;
    }

    public ContentAssistContext getContenttAssistContext() {
        return contentAssistContext;
    }

    public IJavaCompletionProposalComputer getCompletionComputer() {
        return completionComputer;
    }

    public ModuleNode getModuleNode() {
        return moduleNode;
    }

    public MethodProposalCreator getMethodProposalCreator() {
        return methodProposalCreator;
    }

    public ClassLoader getContextClassloader() {
        return classLoader;
    }
}
