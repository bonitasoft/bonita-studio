/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.expression;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class CleanReferenceMigration extends CustomMigration {

	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		super.migrateAfter(model, metamodel);
		for (final Instance operation : model
				.getAllInstances("expression.Operation")) {
			final Instance expression = operation.get("leftOperand");
			if ((isExpressionOfVariableType(expression) || isExpressionOfConstantType(expression))
					&& isNoneReferencedElements(expression)) {
				final boolean hasAddedAreference = new StringToExpressionConverter(
						model, getScope(expression))
						.resolveDataDependencies(expression);
				if (hasAddedAreference
						&& isExpressionOfConstantType(expression)) {
					expression.set("type", ExpressionConstants.VARIABLE_TYPE);
				}
			}
		}
	}

	private boolean isExpressionOfConstantType(final Instance expression) {
		return expression != null
				&& ExpressionConstants.CONSTANT_TYPE.equals(expression
						.<String> get("type"));
	}

	private boolean isNoneReferencedElements(final Instance expression) {
		final Object referencedElements = expression.get("referencedElements");
		return referencedElements instanceof List<?>
				&& ((List<?>) expression.get("referencedElements")).isEmpty();
	}

	private boolean isExpressionOfVariableType(final Instance expression) {
		return expression != null
				&& ExpressionConstants.VARIABLE_TYPE.equals(expression
						.<String> get("type"));
	}

	/**
	 *
	 * @param element
	 * @return the parent process instance
	 */
	protected Instance getScope(final Instance element) {
		Instance container = element;
		while (container != null
				&& !container.instanceOf("process.AbstractProcess")) {
			container = container.getContainer();
		}
		return container;
	}

}
