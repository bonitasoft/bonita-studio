/**
 * Copyright (C) 2014-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.CustomBufferFactory;

import com.google.common.base.Strings;

/**
 * @author Romain Bioteau
 */
public class ConstraintExpressionEditorValidator extends MultiValidator {

    private final IObservableValue expressionObservable;
    private final IObservableList dependenciesObservable;
    private final GroovyCompilationUnit groovyCompilationUnit;
    private final CompilationProblemRequestor compilationErrorRequestor;

    public ConstraintExpressionEditorValidator(final IObservableValue expressionObservable,
            final IObservableList dependenciesObservable,
            final GroovyCompilationUnit groovyCompilationUnit,
            final CompilationProblemRequestor compilationErrorRequestor) {
        this.expressionObservable = expressionObservable;
        this.dependenciesObservable = dependenciesObservable;
        this.groovyCompilationUnit = groovyCompilationUnit;
        this.compilationErrorRequestor = compilationErrorRequestor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        final String text = (String) expressionObservable.getValue();
        if (Strings.isNullOrEmpty(text)) {
            return ValidationStatus.error(Messages.emptyExpressionContent);
        }
        if (hasCompilationErrors()) {
            return ValidationStatus.error(compilationErrorRequestor.toString());
        }
        if (dependenciesObservable.isEmpty()) {
            return ValidationStatus.warning(Messages.noContractInputReferencedInExpression);
        }
        return ValidationStatus.ok();
    }

    private boolean hasCompilationErrors() {
        try {
            groovyCompilationUnit.getWorkingCopy(new NullProgressMonitor(), new CustomBufferFactory(), compilationErrorRequestor);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error("Failed to retrieve compilation unit working copy", e);
            return false;
        }
        return !compilationErrorRequestor.isEmpty();
    }

}
