/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.TextFormField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Florine Boudin
 *
 */
public class InitialValueContributionTest {

	private InitialValueContribution initialValueContribution;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		initialValueContribution = new InitialValueContribution(); 
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_not_group_widget_in_group_getExpressionViewerFilter_return_a_filter_containing_groupIteratorType() throws Exception {
		TextFormField textFormField = FormFactory.eINSTANCE.createTextFormField();
		Group group = FormFactory.eINSTANCE.createGroup();
		group.getWidgets().add(textFormField);
		
		initialValueContribution.setEObject(textFormField);
		
		AvailableExpressionTypeFilter expressionViewerFilter = initialValueContribution.getExpressionViewerFilter();
		assertThat(expressionViewerFilter).isNotNull();
		assertThat(expressionViewerFilter.getContentTypes()).contains(ExpressionConstants.GROUP_ITERATOR_TYPE);
	}
	
	

	@Test
	public void should_group_widget_in_group_getExpressionViewerFilter_return_a_filter_containing_groupIteratorType() throws Exception {
		Group groupBottom = FormFactory.eINSTANCE.createGroup();
		Group groupTop = FormFactory.eINSTANCE.createGroup();
		groupTop.getWidgets().add(groupBottom);
		
		initialValueContribution.setEObject(groupBottom);
		
		AvailableExpressionTypeFilter expressionViewerFilter = initialValueContribution.getExpressionViewerFilter();
		assertThat(expressionViewerFilter).isNotNull();
		assertThat(expressionViewerFilter.getContentTypes()).contains(ExpressionConstants.GROUP_ITERATOR_TYPE);
		
	}
	
	@Test
	public void should_not_group_widget_in_form_getExpressionViewerFilter_return_a_filter_without_groupIteratorType() throws Exception {
		Form form = FormFactory.eINSTANCE.createForm();
		TextFormField textFormField = FormFactory.eINSTANCE.createTextFormField();
		form.getWidgets().add(textFormField);
		
		initialValueContribution.setEObject(textFormField);
		
		AvailableExpressionTypeFilter expressionViewerFilter = initialValueContribution.getExpressionViewerFilter();
		assertThat(expressionViewerFilter).isNotNull();
		assertThat(expressionViewerFilter.getContentTypes()).doesNotContain(ExpressionConstants.GROUP_ITERATOR_TYPE);
		
	}
	
	@Test
	public void should_group_widget_in_form_getExpressionViewerFilter_return_a_filter_without_groupIteratorType() throws Exception {
		Form form = FormFactory.eINSTANCE.createForm();
		Group group = FormFactory.eINSTANCE.createGroup();
		form.getWidgets().add(group);
		
		initialValueContribution.setEObject(group);
		
		AvailableExpressionTypeFilter expressionViewerFilter = initialValueContribution.getExpressionViewerFilter();
		assertThat(expressionViewerFilter).isNotNull();
		
		assertThat(expressionViewerFilter.getContentTypes()).doesNotContain(ExpressionConstants.GROUP_ITERATOR_TYPE);
		
	}
	
}
