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
package org.bonitasoft.studio.refactoring.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.aParameter;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.SearchIndexBuilder.aSearchIndex;

import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DependencyFeatureNameResolverTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_return_ELEMENT_NAME_feature_for_EObject_with_element_super_class() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        final EStructuralFeature nameFeature = dependencyNameResolver.resolveNameDependencyFeatureFor(aData().build());

        assertThat(nameFeature).isEqualTo(ProcessPackage.Literals.ELEMENT__NAME);
    }

    @Test
    public void should_return_CONTRACT_INPUT_NAME_feature_for_ContractInput() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        final EStructuralFeature nameFeature = dependencyNameResolver.resolveNameDependencyFeatureFor(aContractInput().build());

        assertThat(nameFeature).isEqualTo(ProcessPackage.Literals.CONTRACT_INPUT__NAME);
    }

    @Test
    public void should_return_PARAMETER_NAME_feature_for_Parameter() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        final EStructuralFeature nameFeature = dependencyNameResolver.resolveNameDependencyFeatureFor(aParameter().build());

        assertThat(nameFeature).isEqualTo(ParameterPackage.Literals.PARAMETER__NAME);
    }

    @Test
    public void should_return_ELEMENT__NAME_feature_for_SearchIndex() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        final EStructuralFeature nameFeature = dependencyNameResolver.resolveNameDependencyFeatureFor(aSearchIndex().build());

        assertThat(nameFeature).isEqualTo(ProcessPackage.Literals.ELEMENT__NAME);
    }

    @Test
    public void should_throw_a_IllegalStateException_for_unsupported_EObject() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        thrown.expect(IllegalStateException.class);

        dependencyNameResolver.resolveNameDependencyFeatureFor(ParameterFactory.eINSTANCE.createParameterContext());
    }

    @Test
    public void should_throw_a_IllegalArgumentException_for_null_EObject() throws Exception {
        final DependencyFeatureNameResolver dependencyNameResolver = new DependencyFeatureNameResolver();

        thrown.expect(IllegalArgumentException.class);

        dependencyNameResolver.resolveNameDependencyFeatureFor(null);
    }

}
