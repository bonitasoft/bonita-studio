/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.callActivity;

import org.bonitasoft.studio.common.jface.selection.EObjectAdaptableSelectionProvider;
import org.bonitasoft.studio.model.process.CallActivity;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;

@Creatable
public class CallActivitySelectionProvider extends EObjectAdaptableSelectionProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.pagedesigner.ui.part.AdaptableSelectionProvider#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(final Class adapter) {
        if (EObject.class.equals(adapter)) {
            final Object object = unwrap(selection);
            if (object instanceof EObject) {
                return asCallActivity((EObject) object);
            } else if (object != null) {
                return asCallActivity((EObject) ((IAdaptable) object).getAdapter(EObject.class));
            }
        }
        return null;
    }

    private static CallActivity asCallActivity(final EObject semanticElement) {
        if (semanticElement instanceof CallActivity) {
            return (CallActivity) semanticElement;
        }
        return null;
    }

}
