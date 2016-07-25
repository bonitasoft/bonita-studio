/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.editingdomain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EditingDomainManager;

public final class BonitaEditingDomainRegistry implements TransactionalEditingDomain.Registry {

    public static BonitaEditingDomainRegistry INSTANCE = new BonitaEditingDomainRegistry();

    private final Map<String, TransactionalEditingDomain> domains =
            new java.util.HashMap<String, TransactionalEditingDomain>();

    // Documentation copied from the inherited specification
    @Override
    public synchronized TransactionalEditingDomain getEditingDomain(final String id) {
        TransactionalEditingDomain result = domains.get(id);

        if (result == null) {
            result = CustomDiagramEditingDomainFactory.getInstance().createEditingDomain();
            if (result != null) {
                addImpl(id, result);
            }
        }

        return result;
    }

    // Documentation copied from the inherited specification
    @Override
    public synchronized void add(final String id, final TransactionalEditingDomain domain) {
        // remove previously registered domain, if any (which applies the
        //    static registration constraint)
        remove(id);

        addImpl(id, domain);
    }

    /**
     * Adds the specified domain into the registry under the given ID. This
     * method must only be invoked after it has been determined that this
     * ID can be registered.
     *
     * @param id the editing domain ID
     * @param domain the domain to register
     */
    void addImpl(final String id, final TransactionalEditingDomain domain) {
        if (!id.equals(domain.getID())) {
            domain.setID(id); // ensure that the domain's id is set
        }

        domains.put(id, domain);

        EditingDomainManager.getInstance().configureListeners(id, domain);
    }

    // Documentation copied from the inherited specification
    @Override
    public synchronized TransactionalEditingDomain remove(final String id) {
        EditingDomainManager.getInstance().assertDynamicallyRegistered(id);

        final TransactionalEditingDomain result = domains.remove(id);

        if (result != null) {
            EditingDomainManager.getInstance().deconfigureListeners(id, result);
        }

        return result;
    }

    public synchronized void removeAll() {
        final Set<String> registeredIds = new HashSet<String>(domains.keySet());
        for (final String registeredId : registeredIds) {
            EditingDomainManager.getInstance().assertDynamicallyRegistered(registeredId);

            final TransactionalEditingDomain result = domains.remove(registeredId);

            if (result != null) {
                for (final Resource r : result.getResourceSet().getResources()) {
                    r.unload();
                }
                EditingDomainManager.getInstance().deconfigureListeners(registeredId, result);
            }
        }
    }

}
