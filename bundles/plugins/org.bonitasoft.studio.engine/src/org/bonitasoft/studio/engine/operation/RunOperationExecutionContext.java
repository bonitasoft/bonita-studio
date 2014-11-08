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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.operation;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class RunOperationExecutionContext {

    private boolean runSynchronously = false;
    private final String configurationId;
    private Set<EObject> excludedObject = new HashSet<EObject>();

    public RunOperationExecutionContext(final String configurationId) {
        this.configurationId = configurationId;
    }

    public void setSynchronousExecution(final boolean synchronousExecution) {
        runSynchronously = synchronousExecution;
    }

    public boolean synchronousExecution() {
        return runSynchronously;
    }

    public Set<EObject> getExcludedObject() {
        return excludedObject;
    }

    public void setExcludedObject(final Set<EObject> excludedObjects) {
        excludedObject = excludedObjects;
    }

    public String getConfigurationId() {
        return configurationId;
    }

}
