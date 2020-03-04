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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public abstract class AbstractBusinessObjectInitializer implements IPropertyInitializer {

    protected final List<IPropertyInitializer> propertyInitializers = new ArrayList<>();
    protected InitializerContext context;
    private AbstractBusinessObjectInitializer parent;

    public AbstractBusinessObjectInitializer(final InitializerContext context) {
        this.context = context;
    }

    public InitializerContext getContext() {
        return context;
    }

    public void addPropertyInitializer(final IPropertyInitializer propertyInitializer) {
        propertyInitializer.setParent(this);
        propertyInitializers.add(propertyInitializer);
    }

    @Override
    public void setParent(final AbstractBusinessObjectInitializer parentInitializer) {
        parent = parentInitializer;
    }

    @Override
    public AbstractBusinessObjectInitializer getParent() {
        return parent;
    }

    @Override
    public String getPropertyName() {
        return context.getField().getName();
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final BusinessObject businessObject = context.getField().getReference();
        final StringBuilder scriptBuilder = new StringBuilder();
        addCommentBeforeConstructor(scriptBuilder, businessObject);
        final String localVariableName = context.getLocalVariableName();
        delcareVariable(scriptBuilder, localVariableName);
        scriptBuilder.append(" = ");
        constructor(scriptBuilder, businessObject);
        scriptBuilder.append(System.lineSeparator());
        initializeProperties(scriptBuilder, localVariableName);
        returnVar(scriptBuilder, localVariableName);
        return scriptBuilder.toString();
    }

    protected void initializeProperties(final StringBuilder scriptBuilder, final String localVariableName)
            throws BusinessObjectInstantiationException {
        for (IPropertyInitializer propertyInitializer : propertyInitializers) {
            if (!Objects.equals(propertyInitializer.getPropertyName(),
                    FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME)) {
                initializeProperty(scriptBuilder, propertyInitializer, localVariableName);
            }
        }
    }

    protected void addCommentBeforeConstructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        // Can be subclasssed
    }

    protected abstract boolean checkExistence();

    protected void checkNotNullableFields(final BusinessObject businessObject) throws BusinessObjectInstantiationException {
        final Set<String> uninitializedNonNullableFields = notNullableFieldNotInitialized(propertyInitializers,
                businessObject);
        if (!uninitializedNonNullableFields.isEmpty()) {
            throw new BusinessObjectInstantiationException(toArray(uninitializedNonNullableFields, String.class));
        }
    }

    protected void initializeProperty(final StringBuilder scriptBuilder, final IPropertyInitializer propertyInitializer,
            final String varName)
            throws BusinessObjectInstantiationException {
        scriptBuilder.append(varName);
        scriptBuilder.append(".");
        scriptBuilder.append(propertyInitializer.getPropertyName());
        scriptBuilder.append(" = ");
        scriptBuilder.append(propertyInitializer.getInitialValue());
        scriptBuilder.append(System.lineSeparator());
    }

    private Set<String> notNullableFieldNotInitialized(final List<IPropertyInitializer> propertyInitializers,
            final BusinessObject bo) {
        final Set<String> notNullableFields = newHashSet(transform(filter(bo.getFields(), notNullable()), toFieldName()));
        final Set<String> initializedFields = newHashSet(transform(propertyInitializers, initializerToFieldName()));
        notNullableFields.removeAll(initializedFields);
        return notNullableFields;
    }

    private Function<IPropertyInitializer, String> initializerToFieldName() {
        return new Function<IPropertyInitializer, String>() {

            @Override
            public String apply(final IPropertyInitializer input) {
                return input.getPropertyName();
            }
        };
    }

    private Function<Field, String> toFieldName() {
        return new Function<Field, String>() {

            @Override
            public String apply(final Field input) {
                return input.getName();
            }
        };
    }

    private Predicate<Field> notNullable() {
        return new Predicate<Field>() {

            @Override
            public boolean apply(final Field input) {
                return !input.isNullable();
            }
        };
    }

    protected void returnVar(final StringBuilder scriptBuilder, final String varName) {
        scriptBuilder.append("return");
        scriptBuilder.append(" ");
        scriptBuilder.append(varName);
    }

    protected void delcareVariable(final StringBuilder scriptBuilder, final String varName) {
        scriptBuilder.append("def");
        scriptBuilder.append(" ");
        scriptBuilder.append(varName);
    }

    protected String uncapitalizeFirst(final String value) {
        return Character.toLowerCase(value.charAt(0)) + value.substring(1, value.length());
    }

    protected abstract void constructor(final StringBuilder scriptBuilder, final BusinessObject bo);

    protected void addCommentLine(final StringBuilder scriptBuilder, final String comment) {
        scriptBuilder.append("//");
        scriptBuilder.append(comment);
        scriptBuilder.append(System.lineSeparator());
    }

    protected String iteratorName(BusinessObject bo) {
        return String.format("current%sInput", bo.getSimpleName());
    }

}
