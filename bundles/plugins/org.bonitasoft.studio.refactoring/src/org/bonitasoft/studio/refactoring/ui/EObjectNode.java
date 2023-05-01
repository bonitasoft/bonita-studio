/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.refactoring.ui;

import org.eclipse.compare.ITypedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

public class EObjectNode implements ITypedElement {

    private final EObject eObject;
    private final AdapterFactoryLabelProvider labelProvider;

    public EObjectNode(final EObject eObject, final AdapterFactoryLabelProvider labelProvider) {
        this.eObject = eObject;
        this.labelProvider = labelProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.compare.ITypedElement#getName()
     */
    @Override
    public String getName() {
        return labelProvider.getText(eObject);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.compare.ITypedElement#getImage()
     */
    @Override
    public Image getImage() {
        return labelProvider.getImage(eObject);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.compare.ITypedElement#getType()
     */
    @Override
    public String getType() {
        return ITypedElement.FOLDER_TYPE;
    }

    public EObject getElement() {
        return eObject;
    }

}
