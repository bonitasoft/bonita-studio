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

import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.sqltools.parsers.sql.lexer.SQLVariableSymbols;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaSQLQueryParserManager extends SQLQueryParserManager {

	protected void setupCharacterKindMap(SQLQuerySourceFormat p_format)
	{
		setVariableCharacterKind(p_format.getStatementTerminator(), SQLVariableSymbols.STMT_TERM);
		setVariableCharacterKind('$', SQLVariableSymbols.HOST_VAR_PREFIX);
		setVariableCharacterKind(p_format.getParameterMarker(), SQLVariableSymbols.PARAM_MARK);
		setVariableCharacterKind(p_format.getDelimitedIdentifierQuote(), SQLVariableSymbols.DELIM_ID_QT);

	}

}
