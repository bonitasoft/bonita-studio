package org.bonitasoft.studio.businessobject.ui.wizard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

public abstract class AbstractBusinessObjectWizard extends Wizard {

    protected Set<String> computeExistingNames(DataAware container) {
        /* Search to check at the same level */
        Set<String> existingNames = new HashSet<String>();

        /* Search above level */
        List<Data> allData = getAllAccessibleData(container);
        for (final Data object : allData) {
            if (object instanceof Data && !(object.eContainer() instanceof Expression)) {
                final Data otherData = (Data) object;
                existingNames.add(otherData.getName());
            }
        }
        return existingNames;
    }

    protected List<Data> getAllAccessibleData(EObject container) {
        List<Data> allData = ModelHelper.getAccessibleData(container, true);
        for (Object o : ModelHelper.getAllItemsOfType(container, ProcessPackage.Literals.DATA)) {
            if (o instanceof Data) {
                if (!allData.contains(o)) {
                    allData.add((Data) o);
                }
            }
        }
        return allData;
    }

}
