/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.document.ui.dialog.DocumentWizardDialog;
import org.bonitasoft.studio.document.ui.wizard.DocumentWizard;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author aurelie Zara
 */

@RunWith(MockitoJUnitRunner.class)
public class DocumentProposalListenerTest extends DocumentProposalListener {

    @Mock
    private DocumentWizard mockedDocumentWizard;

    @Mock
    private DocumentWizardDialog mockedDocumentWizardDialog;

    private Document documentToReturn;

    private EObject context;

    @Spy
    private DocumentProposalListener documentProposalListener;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        documentToReturn = ProcessFactory.eINSTANCE.createDocument();
        documentToReturn.setName("myBonitaDocument");

        context = ProcessFactory.eINSTANCE.createPool();
        doReturn(mockedDocumentWizard).when(documentProposalListener).createDocumentWizard(context);
        doReturn(mockedDocumentWizardDialog).when(documentProposalListener).createDocumentWizardDialog(mockedDocumentWizard);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_HandleEvent_Returns_DocumentName() {
        when(mockedDocumentWizardDialog.open()).thenReturn(Dialog.OK);
        when(mockedDocumentWizard.getDocumentWorkingCopy()).thenReturn(documentToReturn);

        final String result = documentProposalListener.handleEvent(context, null);
        assertThat(result).isEqualTo(documentToReturn.getName());

    }

    @Test
    public void test_HandleEvent_Returns_Null() {

        when(mockedDocumentWizardDialog.open()).thenReturn(Dialog.OK);
        when(mockedDocumentWizard.getDocumentWorkingCopy()).thenReturn(null);
        String result = documentProposalListener.handleEvent(context, null);
        assertThat(result).isNull();

        when(mockedDocumentWizardDialog.open()).thenReturn(Dialog.CANCEL);
        result = documentProposalListener.handleEvent(context, null);
        assertThat(result).isNull();
    }

    @Test
    public void test_is_PageFLowContext() {
        documentProposalListener.setIsPageFlowContext(true);
        assertTrue(documentProposalListener.isPageFlowContext());
    }

    @Test
    public void test_is_not_PageFLowContext() {
        documentProposalListener.setIsPageFlowContext(false);
        assertFalse(documentProposalListener.isPageFlowContext());
    }

}
