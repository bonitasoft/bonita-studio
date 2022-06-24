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
package org.bonitasoft.studio.properties.sections.index;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexSectionTest {

	@Rule
	public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

	@Test
	public void search_index_value_expression_should_contain_contract_input() {
		final IndexSection section = new IndexSection();
		final AvailableExpressionTypeFilter filter = section
				.createAvailableExpressionTypeFilter();

		assertThat(filter.getContentTypes()).contains(
				ExpressionConstants.CONTRACT_INPUT_TYPE);
	}
}
