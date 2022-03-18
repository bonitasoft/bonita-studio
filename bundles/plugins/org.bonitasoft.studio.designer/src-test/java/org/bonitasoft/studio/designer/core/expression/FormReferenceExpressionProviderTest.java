/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FormReferenceExpressionProviderTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @InjectMocks
    private FormReferenceExpressionProvider expressionProvider;

    @Mock
    private WebPageRepositoryStore store;

    @Test
    public void should_be_relevant_for_FormMapping_context() throws Exception {
        assertThat(expressionProvider.isRelevantFor(null)).isFalse();
        assertThat(expressionProvider.isRelevantFor(aFormMapping().build())).isTrue();
    }

    @Test
    public void should_have_FORM_REFERENCE_TYPE_expression_type() throws Exception {
        assertThat(expressionProvider.getExpressionType()).isEqualTo(ExpressionConstants.FORM_REFERENCE_TYPE);
    }

    @Test
    public void should_poposal_label_return_expression_name() throws Exception {
        assertThat(expressionProvider.getProposalLabel(anExpression().withName("hello").build())).isEqualTo("hello");
    }

    @Test
    public void should_getExpressions_returns_a_set_of_FormReferenceExpression() throws Exception {
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(store);
        doReturn(Arrays.asList(newWebPageFileStore("newPage", "newPageId"),
                newWebPageFileStore("form1", "form1-uuid"))).when(store).getChildren();

        final Set<Expression> expressions = expressionProvider.getExpressions(null);
        assertThat(expressions).extracting("name", "content", "type").contains(tuple("newPage", "newPageId", ExpressionConstants.FORM_REFERENCE_TYPE),
                tuple("form1", "form1-uuid", ExpressionConstants.FORM_REFERENCE_TYPE));
    }

    private WebPageFileStore newWebPageFileStore(final String name, final String id) {
        final WebPageFileStore webPageFileStore = mock(WebPageFileStore.class);
        when(webPageFileStore.getCustomPageName()).thenReturn(name);
        when(webPageFileStore.getUUID()).thenReturn(id);
        return webPageFileStore;
    }
}
