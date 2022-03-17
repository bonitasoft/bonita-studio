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
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
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

public class ExtendedConnectorDefinition implements ConnectorDefinition, LocalizedConnectorDefinition {

    private ConnectorDefinition definition;
    private Image icon;
    private ResourceBundle resourceBundle;

    public ExtendedConnectorDefinition(ConnectorDefinition definition, Image icon, ResourceBundle resourceBundle) {
        this.definition = definition;
        this.icon = icon;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public EClass eClass() {
        return definition.eClass();
    }

    @Override
    public Resource eResource() {
        return definition.eResource();
    }

    @Override
    public EObject eContainer() {
        return definition.eContainer();
    }

    @Override
    public EStructuralFeature eContainingFeature() {
        return definition.eContainingFeature();
    }

    @Override
    public EReference eContainmentFeature() {
        return definition.eContainmentFeature();
    }

    @Override
    public EList<EObject> eContents() {
        return definition.eContents();
    }

    @Override
    public TreeIterator<EObject> eAllContents() {
        return definition.eAllContents();
    }

    @Override
    public boolean eIsProxy() {
        return definition.eIsProxy();
    }

    @Override
    public EList<EObject> eCrossReferences() {
        return definition.eCrossReferences();
    }

    @Override
    public Object eGet(EStructuralFeature feature) {
        return definition.eGet(feature);
    }

    @Override
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        return definition.eGet(feature, resolve);
    }

    @Override
    public void eSet(EStructuralFeature feature, Object newValue) {
        definition.eSet(feature, newValue);
    }

    @Override
    public boolean eIsSet(EStructuralFeature feature) {
        return definition.eIsSet(feature);
    }

    @Override
    public void eUnset(EStructuralFeature feature) {
        definition.eUnset(feature);
    }

    @Override
    public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
        return definition.eInvoke(operation, arguments);
    }

    @Override
    public EList<Adapter> eAdapters() {
        return definition.eAdapters();
    }

    @Override
    public boolean eDeliver() {
        return definition.eDeliver();
    }

    @Override
    public void eSetDeliver(boolean deliver) {
        definition.eSetDeliver(deliver);
    }

    @Override
    public void eNotify(Notification notification) {
        definition.eNotify(notification);
    }

    @Override
    public EList<Category> getCategory() {
        return definition.getCategory();
    }

    @Override
    public EList<Input> getInput() {
        return definition.getInput();
    }

    @Override
    public EList<Output> getOutput() {
        return definition.getOutput();
    }

    @Override
    public EList<Page> getPage() {
        return definition.getPage();
    }

    @Override
    public EList<String> getJarDependency() {
        return definition.getJarDependency();
    }

    @Override
    public String getIcon() {
        return definition.getIcon();
    }

    @Override
    public void setIcon(String value) {
        definition.setIcon(value);
    }

    @Override
    public String getId() {
        return definition.getId();
    }

    @Override
    public void setId(String value) {
        definition.setIcon(value);
    }

    @Override
    public String getVersion() {
        return definition.getVersion();
    }

    @Override
    public void setVersion(String value) {
        definition.setVersion(value);
    }

    public Image getImage() {
        return icon;
    }

    @Override
    public String getConnectorDefinitionLabel() {
        return getMessage(CONNECTOR_DEFINITION);
    }

    private String getMessage(String key) {
        try {
            return resourceBundle != null ? resourceBundle.getString(key) : null;
        } catch (MissingResourceException e) {
            return null;
        }
    }
    
    private String getMessageOrDefault(String key, String defaultValue) {
        try {
            return resourceBundle != null ? resourceBundle.getString(key) : defaultValue;
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }

    @Override
    public String getConnectorDefinitionDescription() {
        return getMessageOrDefault(CONNECTOR_DEFINITION_DESCRIPTION, "");
    }

    @Override
    public String getPageTitle(String pageId) {
        return getMessageOrDefault(buildKey(pageId, PAGE_TITLE), "");
    }

    @Override
    public String getPageDescription(String pageId) {
        return getMessageOrDefault(buildKey(pageId, PAGE_DESCRIPTION), "");
    }

    @Override
    public String getFieldLabel(String fieldId) {
        return getMessageOrDefault(buildKey(fieldId, PAGE_FIELD), "");
    }

    @Override
    public String getFieldDescription(String fieldId) {
        return getMessageOrDefault(buildKey(fieldId, FIELD_DESCRIPTION), "");
    }

    @Override
    public String getFieldExample(String fieldId) {
        return getMessageOrDefault(buildKey(fieldId, FIELD_EXAMPLE), "");
    }

    @Override
    public String getOutputsDescription() {
        return getMessageOrDefault(OUTPUTS_DESC, "");
    }

    @Override
    public String getOutputDescription(String outputName) {
        return getMessageOrDefault(buildKey(outputName, OUTPUT_DESC), "");
    }

    private String buildKey(String id, String suffix) {
        return String.format("%s.%s", id, suffix);
    }
    
    public void dispose() {
        if(icon != null) {
            icon.dispose();
        }
    }
    
   public ConnectorDefinition getConnectorDefinition(){
        return definition;
    }
}
