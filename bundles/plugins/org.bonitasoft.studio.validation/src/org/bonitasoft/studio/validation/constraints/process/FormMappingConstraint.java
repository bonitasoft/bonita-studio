/**
 * Copyright (C) 2010-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class FormMappingConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.formMapping";

    @Override
    protected String getConstraintId() {
        return ID;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof FormMapping);
        final FormMapping formMapping = (FormMapping) eObj;
        if (formMapping.getType() == FormMappingType.INTERNAL) {
            return doValidateInternalMapping(ctx, formMapping);
        }
        return ctx.createSuccessStatus();
    }

    private IStatus doValidateInternalMapping(final IValidationContext ctx, final FormMapping formMapping) {
        final Expression targetForm = formMapping.getTargetForm();
        if (targetForm.hasContent()) {
            final RepositoryAccessor repositoryAccessor = getRepositoryAccessor();
            final WebPageRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
            if (repositoryStore.getChild(targetForm.getContent(), true) == null) {
                return ctx.createFailureStatus(Messages.bind(Messages.invalidInternalFormMapping, mappingKind(formMapping), targetForm.getContent()));
            }
            final List<FormMapping> duplicatedMappings = findDuplicatedNameMappings(formMapping, repositoryStore);
            if (!duplicatedMappings.isEmpty()) {
                return ctx
                        .createFailureStatus(Messages.bind(Messages.duplicatedFormName, mappingKind(formMapping), listDuplicatedMappings(duplicatedMappings)));
            }
        }
        return ctx.createSuccessStatus();
    }

    private String listDuplicatedMappings(final List<FormMapping> duplicatedMappings) {
        final StringBuilder sb = new StringBuilder();
        for (final FormMapping form : duplicatedMappings) {
            sb.append(((Element) form.eContainer()).getName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    private List<FormMapping> findDuplicatedNameMappings(final FormMapping formMapping, final WebPageRepositoryStore repositoryStore) {
        final List<FormMapping> allInternalFormMapping = newArrayList(filter(
                ModelHelper.getAllElementOfTypeIn(ModelHelper.getParentPool(formMapping), FormMapping.class),
                and(withType(FormMappingType.INTERNAL), not(equalTo(formMapping)))));
        return newArrayList(filter(allInternalFormMapping, and(withSameName(formMapping), not(withSameId(formMapping)))));
    }

    private Predicate<FormMapping> withType(final FormMappingType type) {
        return new Predicate<FormMapping>() {

            @Override
            public boolean apply(final FormMapping input) {
                return input.getType() == type;
            }
        };
    }

    private Predicate<FormMapping> withSameName(final FormMapping formMapping) {
        return new Predicate<FormMapping>() {

            @Override
            public boolean apply(final FormMapping input) {
                return Objects.equals(formMapping.getTargetForm().getName(), input.getTargetForm().getName());
            }
        };
    }

    private Predicate<FormMapping> withSameId(final FormMapping formMapping) {
        return new Predicate<FormMapping>() {

            @Override
            public boolean apply(final FormMapping input) {
                return Objects.equals(formMapping.getTargetForm().getContent(), input.getTargetForm().getContent());
            }
        };
    }

    private String mappingKind(final EObject eObj) {
        return eObj.eContainingFeature().equals(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING) ? Messages.entryFormMapping : Messages.overviewFormMapping;
    }

    protected RepositoryAccessor getRepositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }
}
