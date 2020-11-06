/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.ui.wizardPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;

import org.bonitasoft.studio.document.ui.wizardPage.DocumentWizardPage;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.bonitasoft.studio.model.process.builders.DocumentBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentWizardPageTest {

    @Mock
    private DocumentWizardPage docWizardPage;

    @Before
    public void setup(){
        doCallRealMethod().when(docWizardPage).isMimetypeEditable(anyBoolean(), any(Document.class));
    }

    @Test
    public void testIsMimetypeEditable_false_forMultiple() throws Exception {
        assertThat(docWizardPage.isMimetypeEditable(true, null)).isFalse();
    }

    @Test
    public void testIsMimetypeEditable_false_forNone() throws Exception {
        final Document document = DocumentBuilder.aDocument().single().withDocumentType(DocumentType.NONE).build();
        assertThat(docWizardPage.isMimetypeEditable(false, document)).isFalse();
    }

    @Test
    public void testIsMimetypeEditable_false_forContractInput() throws Exception {
        final Document document = DocumentBuilder.aDocument().single().withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(ContractInputBuilder.aContractInput()).build();
        assertThat(docWizardPage.isMimetypeEditable(false, document)).isFalse();
    }

    @Test
    public void testIsMimetypeEditable_true_forExternal() throws Exception {
        final Document document = DocumentBuilder.aDocument().single().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(ExpressionBuilder.aConstantExpression()).build();
        assertThat(docWizardPage.isMimetypeEditable(false, document)).isTrue();
    }

    @Test
    public void testIsMimetypeEditable_true_forInternal() throws Exception {
        final Document document = DocumentBuilder.aDocument().single().withDocumentType(DocumentType.INTERNAL).withDefaultValueIdOfDocumentStore("test")
                .build();
        assertThat(docWizardPage.isMimetypeEditable(false, document)).isTrue();
    }
}
