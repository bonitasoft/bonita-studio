/**
 *
 */
package org.bonitasoft.studio.migration.custom.migration.document;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;


/**
 * @author florine
 *
 */
public class DocumentTypeMigration extends CustomMigration {


    Map<String, Boolean> list = new HashMap<String, Boolean>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {

        final EList<Instance> documents = model.getAllInstances("process.Document");

        for(final Instance inst : documents){
            list.put(inst.getUuid(), (Boolean) inst.get("isInternal"));
        }

    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        final EList<Instance> documents = model.getAllInstances("process.Document");
        for (final Instance inst : documents) {
            final boolean isInternal = list.get(inst.getUuid());

            final EEnum eEnum = metamodel.getEEnum("process.DocumentType");

            inst.set("documentType", eEnum.getEEnumLiteral(DocumentType.NONE_VALUE));
            if (isInternal) {
                inst.set("documentType", eEnum.getEEnumLiteral(DocumentType.INTERNAL_VALUE));
            } else {
                inst.set("documentType", eEnum.getEEnumLiteral(DocumentType.EXTERNAL_VALUE));
            }
        }
    }

}
