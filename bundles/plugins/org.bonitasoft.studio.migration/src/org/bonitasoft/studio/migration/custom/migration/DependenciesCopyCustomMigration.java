/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;


/**
 * @author Romain Bioteau
 *
 */
public class DependenciesCopyCustomMigration extends CustomMigration {

    /* (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.migration.Model, org.eclipse.emf.edapt.migration.Metamodel)
     */
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        List<Instance> allReferencesToBeDeleted = new ArrayList<Instance>();
        for(Instance expInstance : model.getAllInstances("expression.Expression")){
            List<Instance> referencedElements = expInstance.get("referencedElements");
            List<Instance> newReferencedElements = new ArrayList<Instance>();
            List<Instance> referencesToBeDeleted = new ArrayList<Instance>();
            for(Instance refElement : referencedElements){
                Instance newCleanedDependency = null;
                if(refElement.instanceOf("form.Widget")){
                    newCleanedDependency = newCleanedWidgetDependency(refElement,model);
                    referencesToBeDeleted.add(refElement);
                }else if(refElement.instanceOf("process.Data")){
                    newCleanedDependency = newCleaneDataDependency(refElement,model);
                    referencesToBeDeleted.add(refElement);
                }else if(refElement.instanceOf("process.Document")){
                    newCleanedDependency = newCleanedDocumentDependency(refElement,model);
                    referencesToBeDeleted.add(refElement);
                }
                if(newCleanedDependency != null){
                    newReferencedElements.add(newCleanedDependency);
                }
            }
            for(Instance instanceToDelete : referencesToBeDeleted){
                expInstance.remove("referencedElements", instanceToDelete);
            }
            allReferencesToBeDeleted.addAll(referencesToBeDeleted);
            for(Instance newInstance : newReferencedElements){
                expInstance.add("referencedElements", newInstance);
            }
        }
        for(Instance instance : allReferencesToBeDeleted){
            model.delete(instance);
        }
        allReferencesToBeDeleted.clear();
        for(Instance expInstance : model.getAllInstances("expression.Expression")){
            if(expInstance.getContainer() == null){
                allReferencesToBeDeleted.add(expInstance);
            }
        }
        for(Instance instance : allReferencesToBeDeleted){
            model.delete(instance);
        }
        allReferencesToBeDeleted.clear();
    }


    protected Instance newCleanedDocumentDependency(Instance refElement, Model model) {
        Instance newInstance = model.newInstance(refElement.getEClass());
        newInstance.set("name", refElement.get("name"));
        return newInstance;
    }


    protected Instance newCleaneDataDependency(Instance refElement, Model model) {
        Instance newInstance = refElement.copy();
        newInstance.set("defaultValue", null);
        return newInstance;
    }


    protected Instance newCleanedWidgetDependency(Instance refElement, Model model) {
        Instance newInstance = model.newInstance(refElement.getEClass());
        newInstance.set("name", refElement.get("name"));
        Object modifier = refElement.get("returnTypeModifier");
        if(modifier == null){
            modifier = String.class.getName();
        }
        newInstance.set("returnTypeModifier", modifier);
        if(newInstance.instanceOf("form.Duplicable")){
            newInstance.set("duplicate", refElement.get("duplicate"));
        }
        return newInstance;
    }

}
