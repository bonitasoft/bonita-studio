/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.validator;

import java.util.Arrays;
import java.util.Optional;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

/**
 * Perform all the validations on this business object / package (field validation, query validation ect),
 * so any error can be see at any time on the UI
 */
public class BusinessObjectListValidator implements IValidator<Object> {

    private AggregationAndCompositionValidator aggregationAndCompositionValidator;
    private AttributeReferenceExitenceValidator attributeReferenceExitenceValidator;
    private PackageNameValidator packageNameValidator;
    private BusinessObjectNameValidator businessObjectNameValidator;
    private CyclicCompositionValidator cyclicCompositionValidator;
    private EmptyFieldsValidator emptyFieldsValidator;
    private FieldNameValidator fieldNameValidator;
    private MultipleAggregationToItselfValidator multipleAggregationToItselfValidator;
    private SeveralCompositionReferencesValidator severalCompositionReferencesValidator;
    private UniqueConstraintFieldsValidator uniqueConstraintFieldsValidator;
    private UniqueConstraintNameValidator uniqueConstraintNameValidator;
    private QueryParameterNameValidator queryParameterNameValidator;
    private QueryNameValidator queryNameValidator;
    private IndexNameValidator indexNameValidator;
    private IndexFieldsValidator indexFieldsValidator;

    private IObservableValue<BusinessObject> boObservable = new WritableValue<>();
    private IObservableValue<Query> queryObservable = new WritableValue<>();

    public BusinessObjectListValidator(IObservableValue<BusinessObjectModel> businessObjectModelObservable) {
        packageNameValidator = new PackageNameValidator();
        businessObjectNameValidator = new BusinessObjectNameValidator(businessObjectModelObservable);
        aggregationAndCompositionValidator = new AggregationAndCompositionValidator(businessObjectModelObservable);
        attributeReferenceExitenceValidator = new AttributeReferenceExitenceValidator(businessObjectModelObservable);
        cyclicCompositionValidator = new CyclicCompositionValidator();
        emptyFieldsValidator = new EmptyFieldsValidator();
        fieldNameValidator = new FieldNameValidator();
        indexNameValidator = new IndexNameValidator(businessObjectModelObservable);
        multipleAggregationToItselfValidator = new MultipleAggregationToItselfValidator();
        severalCompositionReferencesValidator = new SeveralCompositionReferencesValidator(businessObjectModelObservable);
        uniqueConstraintFieldsValidator = new UniqueConstraintFieldsValidator();
        uniqueConstraintNameValidator = new UniqueConstraintNameValidator();
        queryNameValidator = new QueryNameValidator(boObservable);
        queryParameterNameValidator = new QueryParameterNameValidator(queryObservable);
        indexFieldsValidator = new IndexFieldsValidator();
    }

    @Override
    public IStatus validate(Object element) {
        MultiStatus globalStatus = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        if (element instanceof BusinessObject) {
            validateBusinessObject(globalStatus, (BusinessObject) element, false);
        } else if (element instanceof Package) {
            validatePackage(globalStatus, (Package) element);
        }

        return globalStatus;
    }

    private void validatePackage(MultiStatus globalStatus, Package pakage) {
        validate(packageNameValidator, pakage, globalStatus, Optional.empty());
        pakage.getBusinessObjects().forEach(bo -> validateBusinessObject(globalStatus, bo, true));
    }

    private void validateBusinessObject(MultiStatus globalStatus, BusinessObject businessObject, boolean addBoName) {
        Optional<String> boName = addBoName ? Optional.of(businessObject.getSimpleName()) : Optional.empty();
        validate(businessObjectNameValidator, businessObject, globalStatus, boName);
        validate(aggregationAndCompositionValidator, businessObject, globalStatus, boName);
        validate(attributeReferenceExitenceValidator, businessObject, globalStatus, boName);
        validate(cyclicCompositionValidator, businessObject, globalStatus, boName);
        validate(emptyFieldsValidator, businessObject, globalStatus, boName);
        validate(multipleAggregationToItselfValidator, businessObject, globalStatus, boName);
        validate(severalCompositionReferencesValidator, businessObject, globalStatus, boName);
        validateFields(businessObject, globalStatus, boName);
        validateConstraints(businessObject, globalStatus, boName);
        validateQueries(businessObject, globalStatus, boName);
        validateIndexes(businessObject, globalStatus, boName);
    }

    private void validateQueries(BusinessObject businessObject, MultiStatus globalStatus, Optional<String> boName) {
        boObservable.setValue(businessObject);
        businessObject.getQueries().forEach(query -> {
            validate(queryNameValidator, query, globalStatus, boName);
            validateQueryParameters(query, globalStatus, boName);
        });
    }

    private void validateQueryParameters(Query query, MultiStatus globalStatus, Optional<String> boName) {
        queryObservable.setValue(query);
        query.getQueryParameters()
                .forEach(parameter -> validate(queryParameterNameValidator, parameter, globalStatus, boName));
    }

    private void validateConstraints(BusinessObject businessObject, MultiStatus globalStatus, Optional<String> boName) {
        businessObject.getUniqueConstraints().forEach(constraint -> {
            validate(uniqueConstraintNameValidator, constraint, globalStatus, boName);
            validate(uniqueConstraintFieldsValidator, constraint, globalStatus, boName);
        });

    }

    private void validateIndexes(BusinessObject businessObject, MultiStatus globalStatus, Optional<String> boName) {
        businessObject.getIndexes().forEach(index -> {
            validate(indexNameValidator, index, globalStatus, boName);
            validate(indexFieldsValidator, index, globalStatus, boName);
        });
    }

    private void validateFields(BusinessObject businessObject, MultiStatus globalStatus, Optional<String> boName) {
        businessObject.getFields().forEach(field -> validate(fieldNameValidator, field, globalStatus, boName));
    }

    private void validate(IBDMValidator validator, Object object, MultiStatus globalStatus, Optional<String> boName) {
        IStatus status = boName.isPresent()
                ? validator.validateWithNameAndType(boName.get(), object)
                : validator.validateWithType(object);
        if (!status.isOK()) {
            if (status instanceof MultiStatus) {
                Arrays.asList(status.getChildren())
                        .stream()
                        .filter(s -> !s.isOK())
                        .forEach(globalStatus::add);
            } else {
                globalStatus.add(status);
            }
        }
    }
}
