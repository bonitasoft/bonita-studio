/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class PatternExpressionMigration extends CustomMigration {

	private final Map<String, String> expressions = new HashMap<String, String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(final Instance expression : model.getAllInstances("expression.Expression")){
			if(ExpressionConstants.PATTERN_TYPE.equals(expression.get("type"))){
				final String content = expression.get("content");
				if(content != null && !content.isEmpty()){
					expressions.put(expression.getUuid(),content);
				}
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(final Instance expression : model.getAllInstances("expression.Expression")){
			if(expressions.containsKey(expression.getUuid())){
                expression.set("content", replaceSpecialCharacter(expressions.get(expression.getUuid())));
			}
		}
	}

	/**Replace all character defined by "&nbsp;" by a whitespace
	 * Without the replace, this character is shown as a sharp (#) instead of a whitespace.
	 * @param content
	 * @return 
	 */
	private String replaceSpecialCharacter(String content) {
		return content.replaceAll("&nbsp;", " ");
	}

}
