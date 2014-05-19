/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.dialog;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OutlineFilterTest {

	private Expression expressionWithReferencies;
	private OutlineFilter filter;

	@Before
	public void setUp() throws Exception {
		
		expressionWithReferencies = ExpressionFactory.eINSTANCE.createExpression();
		filter = new OutlineFilter();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void should_isReferencedInExpression_find_a_element_with_other_element_referenced() throws Exception {
		// given
		Data referencedData = ProcessFactory.eINSTANCE.createData();
		referencedData.setName("myReferencedData");
		
		Data otherData = ProcessFactory.eINSTANCE.createData();
		otherData.setName("myOtherData");
		
		expressionWithReferencies.getReferencedElements().add(otherData);
		expressionWithReferencies.getReferencedElements().add(referencedData);
		
		filter.setElementToDisplay(referencedData);
		
		// when
		boolean isElementReferenceFound = ModelHelper.isObjectIsReferencedInExpression(expressionWithReferencies, filter.getElementToDisplay());
		
		// then
		assertThat(isElementReferenceFound).isTrue();
	}	
	
	
	@Test
	public void should_isReferencedInExpression_find_a_parameter_with_other_parameter_referenced() throws Exception {
		// given
		
		Parameter parameterReferenced = ParameterFactory.eINSTANCE.createParameter();
		parameterReferenced.setName("myParameterReferenced");
		
		Parameter otherParameterReferenced = ParameterFactory.eINSTANCE.createParameter();
		otherParameterReferenced.setName("myOtherParameterReferenced");
		
		filter.setElementToDisplay(parameterReferenced);
		
		expressionWithReferencies.getReferencedElements().add(otherParameterReferenced);
		expressionWithReferencies.getReferencedElements().add(parameterReferenced);
		
		// when
		boolean isParameterReferenceFound = ModelHelper.isObjectIsReferencedInExpression(expressionWithReferencies, filter.getElementToDisplay());
		
		// then
		assertThat(isParameterReferenceFound).isTrue();
		
	}
	
	@Test
	public void should_isReferencedInExpression_find_a_searchIndex_with_other_searchIndex_referenced() throws Exception {
		// given
		
		Expression searchIndexReferencedName = ExpressionFactory.eINSTANCE.createExpression();
		searchIndexReferencedName.setName("mySearchIndexReferenced");
		searchIndexReferencedName.setContent(searchIndexReferencedName.getName());
		
		SearchIndex searchIndexReferenced = ProcessFactory.eINSTANCE.createSearchIndex();
		searchIndexReferenced.setName(searchIndexReferencedName);
		
		Expression otherSearchIndexReferencedName = ExpressionFactory.eINSTANCE.createExpression();
		otherSearchIndexReferencedName.setName("myOtherSearchIndexReferenced");
		otherSearchIndexReferencedName.setContent(otherSearchIndexReferencedName.getName());
		
		SearchIndex otherSearchIndexReferenced = ProcessFactory.eINSTANCE.createSearchIndex();
		otherSearchIndexReferenced.setName(otherSearchIndexReferencedName);
		
		filter.setElementToDisplay(searchIndexReferenced);
		
		expressionWithReferencies.getReferencedElements().add(otherSearchIndexReferenced);
		expressionWithReferencies.getReferencedElements().add(searchIndexReferenced);
		
		// when
		boolean isSearchIndexReferenceFound = ModelHelper.isObjectIsReferencedInExpression(expressionWithReferencies, filter.getElementToDisplay());
		
		// then
		assertThat(isSearchIndexReferenceFound).isTrue();
		
	}
	
	
}
