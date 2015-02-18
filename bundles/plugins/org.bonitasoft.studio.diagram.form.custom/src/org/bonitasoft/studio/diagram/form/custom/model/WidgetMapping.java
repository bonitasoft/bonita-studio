/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.form.custom.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class WidgetMapping {

    protected static List<EClass> singleValuatedTextWidgetType;
    static {
        singleValuatedTextWidgetType = new ArrayList<EClass>();
        singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_FORM_FIELD);
        singleValuatedTextWidgetType.add(FormPackage.Literals.PASSWORD_FORM_FIELD);
        singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_AREA_FORM_FIELD);
        singleValuatedTextWidgetType.add(FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD);
        singleValuatedTextWidgetType.add(FormPackage.Literals.TEXT_INFO);
        singleValuatedTextWidgetType.add(FormPackage.Literals.MESSAGE_INFO);
    }

    private boolean isGenerated;

    private boolean isMandatory;

    private boolean isReadOnly;

    private Object modelElement;

    private Widget widgetType;

    private final List<WidgetMapping> children;

    private WidgetMapping parent;

    public WidgetMapping(final Object modelElement) {
        Assert.isNotNull(modelElement);
        this.modelElement = modelElement;
        children = new ArrayList<WidgetMapping>();
        if (modelElement instanceof Field) {
            setMandatory(((Field) modelElement).isNullable() != null && !((Field) modelElement).isNullable());
        }
        setWidgetType(initializeWidgetType());

    }

    /**
     * @return
     */
    private Widget initializeWidgetType() {
        if (modelElement instanceof Document) {
            return FormFactory.eINSTANCE.createFileWidget();
        }
        return initializeWidgetTypeForData();
    }

    protected Widget initializeWidgetTypeForData() {
        final List<EClass> widgetType = getCompatibleWidgetTypes();
        if (!widgetType.isEmpty()) {
            final EClass eClass = widgetType.get(0);
            return (Widget) FormFactory.eINSTANCE.create(eClass);
        }
        return null;
    }

    public boolean isGenerated() {
        return isGenerated;
    }

    public void setGenerated(final boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public Object getModelElement() {
        return modelElement;
    }

    public void setModelElement(final EObject modelElement) {
        this.modelElement = modelElement;
    }

    public Widget getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(final Widget widgetType) {
        //        Assert.isNotNull(widgetType);
        //        Assert.isLegal(getCompatibleWidgetTypes().contains(widgetType.eClass()),widgetType.eClass().getName() + " is not compatible with "+ modelElement);
        this.widgetType = widgetType;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(final boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(final boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public List<EClass> getCompatibleWidgetTypes() {
        if (modelElement instanceof BusinessObjectData) {
            return getCompatibleWidgetTypesForBusinessObjectData((BusinessObjectData) modelElement);
        } else if (modelElement instanceof Data) {
            return getCompatibleWidgetTypesForData((Data) modelElement);
        } else if (modelElement instanceof Document) {
            return getCompatibleWidgetTypesForDocument((Document) modelElement);
        } else if (modelElement instanceof SimpleField) {
            return getCompatibleWidgetTypesForField((SimpleField) modelElement);
        }
        return Collections.<EClass> emptyList();
    }

    protected List<EClass> getCompatibleWidgetTypesForField(final SimpleField field) {
        Assert.isNotNull(field);
        final FieldType fieldType = field.getType();
        Assert.isNotNull(fieldType);
        final boolean isMultiple = field.isCollection() != null && field.isCollection();
        if (isMultiple) {
            final List<EClass> compatibleTypes = new ArrayList<EClass>();
            switch (fieldType) {
                case BOOLEAN:
                    compatibleTypes.add(FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD);
                    compatibleTypes.add(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
                    break;
                default:
                    compatibleTypes.add(FormPackage.Literals.LIST_FORM_FIELD);
                    compatibleTypes.add(FormPackage.Literals.TEXT_FORM_FIELD);
                    break;
            }
            return compatibleTypes;
        } else {
            switch (fieldType) {
                case BOOLEAN:
                    return Collections.<EClass> singletonList(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
                case DATE:
                    return Collections.<EClass> singletonList(FormPackage.Literals.DATE_FORM_FIELD);
                case STRING:
                    return singleValuatedTextWidgetType;
                case TEXT:
                    return Arrays.asList(FormPackage.Literals.TEXT_AREA_FORM_FIELD, FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD,
                            FormPackage.Literals.MESSAGE_INFO);
                default:
                    return Collections.<EClass> singletonList(FormPackage.Literals.TEXT_FORM_FIELD);
            }
        }
    }

    protected List<EClass> getCompatibleWidgetTypesForBusinessObjectData(
            final BusinessObjectData modelElement) {
        return Collections.emptyList();
    }

    protected List<EClass> getCompatibleWidgetTypesForDocument(final Document currentDocument) {
        final DocumentType docType = currentDocument.getDocumentType();
        if (docType == null) {
            throw new IllegalStateException(currentDocument + " should have a DocumentType");
        }
        // Multiple widget with documents ?
        //        if (currentDocument.isMultiple()) {
        //            return null;
        //        } else {
        return Collections.<EClass> singletonList(FormPackage.Literals.FILE_WIDGET);
        //        }
    }

    protected List<EClass> getCompatibleWidgetTypesForData(final Data currentData) {
        final DataType dataType = currentData.getDataType();
        if (dataType == null) {
            throw new IllegalStateException(currentData + " should have a DataType");
        }
        if (currentData.isMultiple()) {
            if (dataType instanceof BooleanType) {
                return Collections.<EClass> singletonList(FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD);
            }
            return Collections.<EClass> singletonList(FormPackage.Literals.LIST_FORM_FIELD);
        } else {
            return getWidgetTypeForSingleData(dataType);
        }
    }

    protected List<EClass> getWidgetTypeForSingleData(final DataType dataType) {
        return new ProcessSwitch<List<EClass>>() {

            @Override
            public List<EClass> caseDateType(final org.bonitasoft.studio.model.process.DateType object) {
                return Collections.<EClass> singletonList(FormPackage.Literals.DATE_FORM_FIELD);
            }

            @Override
            public List<EClass> caseBooleanType(final org.bonitasoft.studio.model.process.BooleanType object) {
                return Collections.<EClass> singletonList(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
            }

            @Override
            public List<EClass> caseEnumType(final org.bonitasoft.studio.model.process.EnumType object) {
                final List<EClass> result = new ArrayList<EClass>();
                result.add(FormPackage.Literals.RADIO_FORM_FIELD);
                result.add(FormPackage.Literals.SELECT_FORM_FIELD);
                return result;
            }

            @Override
            public java.util.List<EClass> caseStringType(final org.bonitasoft.studio.model.process.StringType object) {
                return singleValuatedTextWidgetType;
            }

            @Override
            public List<EClass> defaultCase(final org.eclipse.emf.ecore.EObject object) {
                return Collections.<EClass> singletonList(FormPackage.Literals.TEXT_FORM_FIELD);
            }

        }.doSwitch(dataType);
    }

    public List<? extends WidgetMapping> getChildren() {
        return children;
    }

    public void addChild(final WidgetMapping child) {
        if (!children.contains(child)) {
            child.setParent(this);
            children.add(child);
        }
    }

    public WidgetMapping getParent() {
        return parent;
    }

    public void setParent(final WidgetMapping parent) {
        this.parent = parent;
    }

}
