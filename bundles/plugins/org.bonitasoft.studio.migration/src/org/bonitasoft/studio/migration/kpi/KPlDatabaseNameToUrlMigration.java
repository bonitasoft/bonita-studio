/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.kpi;


import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author aurelie
 *
 */
public class KPlDatabaseNameToUrlMigration extends CustomMigration {

    private static final String URL_SEPARATOR = "/";

    @Override
    public void migrateBefore(Model model, Metamodel metamodel)
            throws MigrationException {
        for (Instance kpiInstance : model.getAllInstances("kpi.DatabaseKPIBinding")){
            final Instance dbName = kpiInstance.get("dbName");
            final Instance url = kpiInstance.get("jdbcUrl");
            if (dbName!=null && url!=null
                    && dbName.get(ExpressionPackage.Literals.EXPRESSION__CONTENT)!=null
                    && url.get(ExpressionPackage.Literals.EXPRESSION__CONTENT)!=null
                    && ExpressionConstants.CONSTANT_TYPE.equals(dbName.get(ExpressionPackage.Literals.EXPRESSION__TYPE))
                    && ExpressionConstants.CONSTANT_TYPE.equals(url.get(ExpressionPackage.Literals.EXPRESSION__TYPE)) ){
                String urlContent = url.get(ExpressionPackage.Literals.EXPRESSION__CONTENT);
                String dbNameContent = dbName.get(ExpressionPackage.Literals.EXPRESSION__CONTENT);
                if (urlContent.endsWith(URL_SEPARATOR)){
                    urlContent = urlContent+dbNameContent;
                } else {
                    urlContent = urlContent+URL_SEPARATOR+dbNameContent;
                }
                url.set(ExpressionPackage.Literals.EXPRESSION__CONTENT,urlContent);
            }
        }
    }


}
