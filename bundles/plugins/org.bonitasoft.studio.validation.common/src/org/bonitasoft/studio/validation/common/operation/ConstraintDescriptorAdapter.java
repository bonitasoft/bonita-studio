/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IConstraintDescriptor;


public abstract class ConstraintDescriptorAdapter implements IConstraintDescriptor {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPluginId() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public ConstraintSeverity getSeverity() {
        return null;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public EvaluationMode<?> getEvaluationMode() {
        return null;
    }

    @Override
    public boolean targetsTypeOf(EObject eObject) {
        return false;
    }

    @Override
    public boolean targetsEvent(Notification notification) {
        return false;
    }

    @Override
    public boolean isBatch() {
        return false;
    }

    @Override
    public boolean isLive() {
        return false;
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public void setError(Throwable exception) {

    }

    @Override
    public Set<Category> getCategories() {
        return Set.of();
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public String getMessagePattern() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }

}
