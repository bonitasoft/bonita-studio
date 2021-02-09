/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.provider;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.connector.model.definition.Category;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Image;

public class ExtendedCategory implements Category {

    private Category category;
    private Image icon;
    private String label;
    
    public ExtendedCategory(Category category, Image icon, String label) {
        this.category = category;
        this.icon = icon;
        this.label = label;
    }

    @Override
    public EClass eClass() {
        return category.eClass();
    }

    @Override
    public Resource eResource() {
        return category.eResource();
    }

    @Override
    public EObject eContainer() {
        return category.eContainer();
    }

    @Override
    public EStructuralFeature eContainingFeature() {
        return category.eContainingFeature();
    }

    @Override
    public EReference eContainmentFeature() {
        return category.eContainmentFeature();
    }

    @Override
    public EList<EObject> eContents() {
        return category.eContents();
    }

    @Override
    public TreeIterator<EObject> eAllContents() {
        return category.eAllContents();
    }

    @Override
    public boolean eIsProxy() {
        return category.eIsProxy();
    }

    @Override
    public EList<EObject> eCrossReferences() {
        return category.eCrossReferences();
    }

    @Override
    public Object eGet(EStructuralFeature feature) {
        return category.eGet(feature);
    }

    @Override
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        return category.eGet(feature, resolve);
    }

    @Override
    public void eSet(EStructuralFeature feature, Object newValue) {
        category.eSet(feature, newValue);
    }

    @Override
    public boolean eIsSet(EStructuralFeature feature) {
        return category.eIsSet(feature);
    }

    @Override
    public void eUnset(EStructuralFeature feature) {
        category.eUnset(feature);
    }

    @Override
    public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
        return category.eInvoke(operation, arguments);
    }

    @Override
    public EList<Adapter> eAdapters() {
        return category.eAdapters();
    }

    @Override
    public boolean eDeliver() {
        return category.eDeliver();
    }

    @Override
    public void eSetDeliver(boolean deliver) {
        category.eSetDeliver(deliver);
    }

    @Override
    public void eNotify(Notification notification) {
        category.eNotify(notification);
    }

    @Override
    public String getIcon() {
        return category.getIcon();
    }

    @Override
    public void setIcon(String value) {
        category.setIcon(value);
    }

    @Override
    public String getId() {
        return category.getId();
    }

    @Override
    public void setId(String value) {
        category.setId(value);
    }

    @Override
    public String getParentCategoryId() {
        return category.getParentCategoryId();
    }

    @Override
    public void setParentCategoryId(String value) {
        category.setParentCategoryId(value);
    }

    public Image getImage() {
        return icon;
    }
    
    public String getLabel() {
        return label;
    }

    public Category getCategory() {
        return category;
    }
}
