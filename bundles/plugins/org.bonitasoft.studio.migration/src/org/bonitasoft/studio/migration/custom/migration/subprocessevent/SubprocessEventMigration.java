/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.subprocessevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Romain Bioteau
 *
 */
public class SubprocessEventMigration extends CustomMigration {

    private final Map<String, List<Instance>> connectionsMap = new HashMap<String, List<Instance>>();
    private final Map<String, List<Instance>> elementsMap = new HashMap<String, List<Instance>>();
    private final Map<String, Instance> elementsByUUID = new HashMap<String, Instance>();
    private final Map<String, Instance> elementsMapping = new HashMap<String, Instance>();
    private final Map<String, String> subprocNames = new HashMap<String, String>();
    private final Map<String, String> subprocDescription = new HashMap<String, String>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance subprocessevent : model.getAllInstances("process.SubProcessEvent")){
            subprocNames.put(subprocessevent.getUuid(), (String) subprocessevent.get("name"));
            subprocDescription.put(subprocessevent.getUuid(), (String) subprocessevent.get("documentation"));
            final Instance process = getParentProcess(subprocessevent);
            if(process == null){
                throw new MigrationException("No parent process found for subprocess event "+subprocessevent.get("name"), null);
            }

            for (final Instance data : subprocessevent.<List<Instance>> get("data")) {
                process.add("data",data.copy());
            }

            final List<Instance> elements =  new ArrayList<Instance>();
            for (final Instance element : subprocessevent.<List<Instance>> get("elements")) {
                subprocessevent.remove("elements", element);
                process.add("elements", element);
                final Instance copy = element;
                copy.setUuid(element.getUuid());
                for(final Instance view : model.getAllInstances(NotationPackage.Literals.VIEW)){
                    final Instance elem =  view.get("element");
                    if(elem != null && elem.getUuid().equals(element.getUuid())){
                        elementsMapping.put(element.getUuid(),view);
                    }
                }
                elementsByUUID.put(element.getUuid(), copy);
                elements.add(copy);
            }
            elementsMap.put(subprocessevent.getUuid(), elements);
            for (final Instance connection : subprocessevent.<List<Instance>> get("connections")) {
                subprocessevent.remove("connections", connection);
                process.add("connections", connection);
            }

        }
    }

    private Instance getParentProcess(final Instance subprocessevent) {
        Instance current= subprocessevent;
        while (current != null && (current.equals(subprocessevent) || !current.instanceOf("process.AbstractProcess"))) {
            current = current.getContainer();
        }
        return current;
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance subprocessevent : model.getAllInstances("process.SubProcessEvent")){
            subprocessevent.set("name",subprocNames.get(subprocessevent.getUuid()));
            subprocessevent.set("documentation",subprocDescription.get(subprocessevent.getUuid()));
            final Instance process = getParentProcess(subprocessevent);
            if(process == null){
                throw new MigrationException("No parent process found for subprocess event "+subprocessevent.get("name"), null);
            }
            final List<Instance> elements = elementsMap.get(subprocessevent.getUuid());
            if(elements != null){
                for(final Instance element : elements){
                    process.remove("elements", element);
                    subprocessevent.add("elements",element);
                    if(elementsMapping.containsKey(element.getUuid())){
                        elementsMapping.get(element.getUuid()).set("element", element);
                    }
                }
            }

            final List<Instance> connections = connectionsMap.get(subprocessevent.getUuid());
            if(connections != null){
                final List<Instance> procConnections = process.get("connections");
                for(final Instance connection : connections){
                    procConnections.add(connection);
                    if(elementsMapping.containsKey(connection.getUuid())){
                        elementsMapping.get(connection.getUuid()).set("element", connection);
                    }
                }
            }
        }
    }


}
