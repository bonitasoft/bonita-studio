/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.refactoring.core;

import static com.google.common.collect.Iterables.tryFind;

import java.util.List;

import org.bonitasoft.studio.refactoring.core.script.ReferenceDiff;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.control.SourceUnit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class ProcessVariableRenamer extends ClassCodeVisitorSupport {

    private final MultiTextEdit edits;

    private List<ReferenceDiff> diffs;

    public ProcessVariableRenamer() {
        edits = new MultiTextEdit();
    }

    public MultiTextEdit rename(final ASTNode node, final List<ReferenceDiff> diffs) {
        this.diffs = diffs;
        // no need to visit parameters since they have already been changed
        node.visit(this);
        return edits;
    }

    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        final Variable accessedVar = expression.getAccessedVariable();
        // look for dynamic variables since the parameters already have the
        // new names, the actual references to the parameters are using the
        // old names
        if (accessedVar instanceof DynamicVariable) {
            final String newName = findReplacement(accessedVar.getName());
            if (newName != null) {
                edits.addChild(new ReplaceEdit(expression.getStart(), expression.getLength(), newName));
            }
        }
    }

    private String findReplacement(final String name) {
        final Optional<ReferenceDiff> diff = tryFind(diffs, withOldReference(name));
        if (diff.isPresent()) {
            return diff.get().getNewRef();
        }
        return null;
    }

    /**
     * @param name
     * @return
     */
    private Predicate<ReferenceDiff> withOldReference(final String name) {
        return new Predicate<ReferenceDiff>() {

            @Override
            public boolean apply(final ReferenceDiff input) {
                return input.getOldRef().equals(name);
            }
        };
    }

    @Override
    protected SourceUnit getSourceUnit() {
        return null;
    }

}
