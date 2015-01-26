/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.form;

import java.util.List;

import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


/**
 * @author Florine Boudin
 *
 */
public class FileWidgetDocumentMigration extends CustomMigration {

    private EList<Instance> documents;
    private EList<Instance> fileWidgets;

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {
        super.migrateBefore(model, metamodel);
        fileWidgets = model.getAllInstances("form.FileWidget");

        // remove old documents
        for (final Instance fileWidget : fileWidgets) {
            if (fileWidget.get("document") != null) {
                final Instance element = fileWidget.get("document");
                fileWidget.remove("document", element);
            }
        }


    }


    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        super.migrateAfter(model, metamodel);
        documents = model.getAllInstances("process.Document");

        final EList<Instance> fileWidgets = model.getAllInstances("form.FileWidget");

        for (final Instance fileWidget : fileWidgets) {
            if (fileWidget.get("action") != null) {
                final Instance action = fileWidget.get("action");
                final Instance leftOperand = action.get("leftOperand");
                final Instance operator = action.get("operator");

                if (leftOperand != null && ExpressionConstants.DOCUMENT_REF_TYPE.equals(leftOperand.get("type"))) {
                    final List<Instance> list = leftOperand.get("referencedElements");
                    // find the document referenced element
                    if (list.isEmpty()) {
                        final Instance refDoc = getReferencedDocument(leftOperand);
                        if (refDoc != null) {
                            leftOperand.add("referencedElements", refDoc.copy());
                        }
                    }
                } else if (leftOperand != null && "".equals(leftOperand.get("name")) && "".equals(leftOperand.get("content"))) {
                    // Set the operator type as he should be if designed in 6.4.0
                    operator.set("type", OperatorType.ASSIGNMENT.toString());
                }
            }
        }
    }

    private Instance getReferencedDocument(final Instance leftOperand) {
        for (final Instance doc : documents) {
            if (doc.get("name").equals(leftOperand.get("name"))) {
                return doc;
            }
        }
        return null;
    }


}
