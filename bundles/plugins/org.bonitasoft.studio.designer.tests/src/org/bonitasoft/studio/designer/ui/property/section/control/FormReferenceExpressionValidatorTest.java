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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FormReferenceExpressionValidatorTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Test
    public void is_relevant_for_FORM_REFERENCE_TYPE() throws Exception {
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);

        final boolean isRelevant = formReferenceExpressionValidator.isRelevantForExpressionType(ExpressionConstants.FORM_REFERENCE_TYPE);

        assertThat(isRelevant).isTrue();
    }

    @Test
    public void is_not_relevant_for_other_expression_type_than_FORM_REFERENCE_TYPE() throws Exception {
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);

        final boolean isRelevant = formReferenceExpressionValidator.isRelevantForExpressionType(ExpressionConstants.CONSTANT_TYPE);

        assertThat(isRelevant).isFalse();
    }

    @Test
    public void return_an_error_status_if_page_id_is_not_in_the_repository() throws Exception {
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
        formReferenceExpressionValidator.setInputExpression(ExpressionHelper.createFormReferenceExpression("Step1", "step1-id"));
        doReturn(aWebPageStoreMockContaining()).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);

        final IStatus status = formReferenceExpressionValidator.validate("Step1");

        assertThat(status).isNotOK();
    }

    @Test
    public void return_an_error_status_if_page_id_is_not_in_the_repository_for_overviewPageFlow() throws Exception {
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING);
        formReferenceExpressionValidator.setInputExpression(ExpressionHelper.createFormReferenceExpression("Step1", "step1-id"));
        doReturn(aWebPageStoreMockContaining()).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);

        final IStatus status = formReferenceExpressionValidator.validate("Step1");

        assertThat(status).isNotOK();
    }

    @Test
    public void return_a_valid_status_if_page_id_is_present_in_the_repository() throws Exception {
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
        formReferenceExpressionValidator.setInputExpression(ExpressionHelper.createFormReferenceExpression("Step1", "step1-id"));
        doReturn(aWebPageStoreMockContaining("step1-id", "step2-id")).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);

        final IStatus status = formReferenceExpressionValidator.validate("Step1");

        assertThat(status).isOK();
    }

    private WebPageRepositoryStore aWebPageStoreMockContaining(final String... fileStoreIds) {
        final WebPageRepositoryStore pageRepositoryStore = mock(WebPageRepositoryStore.class);
        if (fileStoreIds != null) {
            for (final String id : fileStoreIds) {
                doReturn(mock(WebPageFileStore.class)).when(pageRepositoryStore).getChild(id, true);
            }
        }
        return pageRepositoryStore;
    }

}
