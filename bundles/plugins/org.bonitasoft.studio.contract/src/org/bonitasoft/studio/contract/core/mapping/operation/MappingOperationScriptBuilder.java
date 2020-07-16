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
package org.bonitasoft.studio.contract.core.mapping.operation;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.AbstractBusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory.BusinessObjectInitializerFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory.InitializerFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory.PropertyInitializerFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory.RelationPropertyInitializerFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.codehaus.groovy.eclipse.refactoring.formatter.DefaultGroovyFormatter;
import org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;

public class MappingOperationScriptBuilder {

    private static final IFormatterPreferences DEFAULT_FORMATTER_PREFS = new DefaultFormatterPreferences();
    private final BusinessObjectInitializerFactory businessObjectInitializerFactory;
    private static final int FORMAT_LEVEL = 0;
    private boolean needsDataDependency = false;
    private final BusinessObjectData data;
    private final FieldToContractInputMapping mapping;
    private final PropertyInitializerFactory propertyInitializerFactory;

    public MappingOperationScriptBuilder(final BusinessObjectData data,
            final FieldToContractInputMapping mapping) {
        this.data = data;
        this.mapping = mapping;
        final VariableNameResolver variableNameResolver = new VariableNameResolver();
        businessObjectInitializerFactory = new BusinessObjectInitializerFactory(variableNameResolver);
        propertyInitializerFactory = new PropertyInitializerFactory(
                new RelationPropertyInitializerFactory(variableNameResolver));
    }

    public String toInstanciationScript() throws BusinessObjectInstantiationException {
        return toScript(true);
    }

    public String toScript() throws BusinessObjectInstantiationException {
        return toScript(false);
    }

    private String toScript(boolean createMode) throws BusinessObjectInstantiationException {
        mapping.getContractInput();
        return format(
                buildPropertyInitializerTree(mapping, businessObjectInitializerFactory, data, createMode).getInitialValue());
    }

    private String format(final String initialValue) {
        final Document document = new Document(initialValue);
        try {
            new DefaultGroovyFormatter(document, DEFAULT_FORMATTER_PREFS, FORMAT_LEVEL).format().apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            BonitaStudioLog.error("Failed to format generated script", e);
        }
        return document.get();
    }

    private IPropertyInitializer buildPropertyInitializerTree(FieldToContractInputMapping mapping,
            InitializerFactory initializerFactory,
            BusinessObjectData data,
            boolean isOnPool) {
        Field field = mapping.getField();
        if (field instanceof SimpleField) {
            return propertyInitializerFactory.newPropertyInitializer(mapping, data, isOnPool);
        }
        if (field instanceof RelationField) {
            AbstractBusinessObjectInitializer scriptInitializer = (AbstractBusinessObjectInitializer) initializerFactory
                    .newPropertyInitializer(
                            mapping,
                            data,
                            isOnPool);
            for (FieldToContractInputMapping child : mapping.getChildren()) {
                if (child.isGenerated()) {
                    scriptInitializer.addPropertyInitializer(
                            buildPropertyInitializerTree(child, propertyInitializerFactory, data, isOnPool));
                }
            }
            needsDataDependency = scriptInitializer instanceof NewBusinessObjectInitializer;
            return scriptInitializer;
        }
        throw new UnsupportedOperationException(field.getClass().getName() + " type is not supported");
    }

    public boolean needsDataDependency() {
        return needsDataDependency;
    }

}
