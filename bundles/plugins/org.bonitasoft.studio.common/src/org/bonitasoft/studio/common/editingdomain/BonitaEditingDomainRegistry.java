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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.kpi.KpiPackage;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.decision.DecisionPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EditingDomainManager;
import org.eclipse.gmf.runtime.notation.NotationPackage;

public final class BonitaEditingDomainRegistry implements TransactionalEditingDomain.Registry {

    static {
        List.of(ProcessPackage.eINSTANCE,
                ConnectorDefinitionPackage.eINSTANCE,
                ExpressionPackage.eINSTANCE,
                ParameterPackage.eINSTANCE,
                ConnectorImplementationPackage.eINSTANCE,
                KpiPackage.eINSTANCE,
                ConnectorConfigurationPackage.eINSTANCE,
                DecisionPackage.eINSTANCE,
                NotationPackage.eINSTANCE);
    }

    public static final  BonitaEditingDomainRegistry INSTANCE = new BonitaEditingDomainRegistry();

    private final Map<String, TransactionalEditingDomain> domains = new HashMap<>();

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
                EditingDomainManager.getInstance().deconfigureListeners(registeredId, result);
            }
        }
    }

}
