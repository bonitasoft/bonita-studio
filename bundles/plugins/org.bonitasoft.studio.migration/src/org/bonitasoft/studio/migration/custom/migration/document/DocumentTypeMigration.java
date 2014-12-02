/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.document;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


/**
 * @author florine
 *
 */
public class DocumentTypeMigration extends CustomMigration {


    private final Map<String, Integer> list = new HashMap<String, Integer>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {
        final EList<Instance> documents = model.getAllInstances("process.Document");
        for (final Instance inst : documents) {
            final Integer documentType = retrieveNewDocumentType(inst);
            list.put(inst.getUuid(), documentType);
        }
    }

    private Integer retrieveNewDocumentType(final Instance inst) {
        final Boolean isDocumentInternal = (Boolean) inst.get("isInternal");
        if (isDocumentInternal) {
            return retriveNewDocumentTypeForOldInternalType(inst);
        } else {
            return retrieveNewDocumentTypeForOldExternalType(inst);
        }
    }

    private Integer retrieveNewDocumentTypeForOldExternalType(final Instance inst) {
        final Instance urlExpression = inst.get("url");
        if (isEmptyUrlExpression(urlExpression)) {
            return DocumentType.NONE_VALUE;
        } else {
            return DocumentType.EXTERNAL_VALUE;
        }
    }

    private Integer retriveNewDocumentTypeForOldInternalType(final Instance inst) {
        final String defaultValueId = inst.get("defaultValueIdOfDocumentStore");
        if (defaultValueId == null || defaultValueId.isEmpty()) {
            return DocumentType.NONE_VALUE;
        } else {
            return DocumentType.INTERNAL_VALUE;
        }
    }

    private boolean isEmptyUrlExpression(final Instance urlExpression) {
        if (urlExpression != null) {
            final String content = urlExpression.get("content");
            return content == null || content.isEmpty();
        }
        return true;
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        final EList<Instance> documents = model.getAllInstances("process.Document");
        for (final Instance inst : documents) {
            final int newDocumentType = list.get(inst.getUuid());
            final EEnum eEnum = metamodel.getEEnum("process.DocumentType");
            inst.set("documentType", eEnum.getEEnumLiteral(newDocumentType));
        }
    }

}
