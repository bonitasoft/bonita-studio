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

import java.util.List;

import org.bonitasoft.studio.expression.editor.pattern.GroovyExpressionPartitioner;
import org.bonitasoft.studio.expression.editor.pattern.TextViewerDocumentViewerDelegate;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.helper.BonitaExpressionBinding;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITypedRegion;

/**
 * @author Romain Bioteau
 */
public class BonitaSQLDomainModel extends SQLDomainModel {

    private GroovyExpressionPartitioner partitioner = new GroovyExpressionPartitioner();

    @Override
    public QueryStatement parse(final String sqlStr) throws SQLParserException, SQLParserInternalException {
        final String tmpSqlString = hangleGroovyVariables(sqlStr);
        return super.parse(tmpSqlString);
    }

    private String hangleGroovyVariables(String tmpSqlString) {
        final Document document = new Document(tmpSqlString);
        partitioner.connect(document);
        final ITypedRegion[] groovyPartitions = partitioner.computePartitioning(0, document.getLength());
        int varCpt = 0;
        for (final ITypedRegion r : groovyPartitions) {
            if (TextViewerDocumentViewerDelegate.GROOVY_EXPRESSION_CONTENT_TYPE.equals(r.getType())) {
                try {
                    final String exp = document.get(r.getOffset(), r.getLength());
                    BonitaExpressionBinding.bind("var" + varCpt, exp);
                    document.replace(r.getOffset(), r.getLength(), "$var" + varCpt);
                    varCpt++;
                } catch (final BadLocationException e) {

                }
            }
        }
        partitioner.disconnect();
        return document.get();
    }

    @Override
    public QueryStatement parse(final String sqlStr, final boolean createObjectTree)
            throws SQLParserException, SQLParserInternalException {
        final String tmpSqlString = hangleGroovyVariables(sqlStr);
        return super.parse(tmpSqlString, createObjectTree);

    }

    @Override
    public QueryStatement parse(final String sqlStr, final List errorList)
            throws SQLParserException, SQLParserInternalException {
        final String tmpSqlString = hangleGroovyVariables(sqlStr);
        return super.parse(tmpSqlString, errorList);

    }

    /**
     * Gets an instance of the parser manager to use with this statement.
     *
     * @return the parser manager object
     */
    @Override
    protected SQLQueryParserManager getParserManager() {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        SQLQueryParserManager parserManager = null;
        final DatabaseDefinition dbDef = getDatabaseDefinition();
        if (dbDef != null) {
            /* Create an instance of the ParserManager and TODO: keep the instance. */
            parserManager = new BonitaSQLQueryParserManager();

            // TODO: should only configure the Parser Manager and P3's once, rather than every time
            final PostParseProcessorConfiguration p3config = getPostParseProcessorConfiguration();
            final SQLQuerySourceFormat srcFormat = getSqlSourceFormat();
            srcFormat.setHostVariablePrefix('$');
            parserManager.setSourceFormat(srcFormat);
            parserManager.configPostParseProcessors(p3config);
        }

        return (SQLQueryParserManager) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parserManager);
    }

}
