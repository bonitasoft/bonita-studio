/**
 * Copyright (C) 2013-2014 BonitaSoft S.A.
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.GroovyOnlyExpressionViewer;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Florine Boudin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentWizardPageTest extends AbstractSWTTestCase{


    private DocumentWizardPage documentWizardPageUnderTest;

    @Mock
    private ExpressionViewer expressionViewerMock;

    @Mock
    private GroovyOnlyExpressionViewer groovyOnlyExpressionViewerMock;

    private Composite composite;

    private Document document;

    private Image hintImage;

    private DataBindingContext dbc;

    @Before
    public void setUp() {
        composite = createDisplayAndRealm();
        dbc = new EMFDataBindingContext();
        document = ProcessFactory.eINSTANCE.createDocument();
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        documentWizardPageUnderTest = spy(new DocumentWizardPage(pool, document));
        hintImage = createImage();
        doReturn(hintImage).when(documentWizardPageUnderTest).getHintImage();

        final Control control = mock(Control.class);
        when(expressionViewerMock.getControl()).thenReturn(control);
        when(expressionViewerMock.getTextControl()).thenReturn(mock(Text.class));
        doReturn(expressionViewerMock).when(documentWizardPageUnderTest).createExpressionViewer(Mockito.any(Composite.class), Mockito.any(EReference.class));


        when(groovyOnlyExpressionViewerMock.getControl()).thenReturn(control);
        doReturn(groovyOnlyExpressionViewerMock).when(documentWizardPageUnderTest).createExpressionViewerWithGroovyScriptOnly(Mockito.any(Composite.class));
        when(control.getDisplay()).thenReturn(display);

    }

    @After
    public void tearDown() {
        if (dbc != null) {
            dbc.dispose();
        }
        if (hintImage != null) {
            hintImage.dispose();
        }
        dispose();
    }

    @Test
    public void should_getConstantTypeOnlyExpressionViewerFilter_returns_Filter_accepting_only_CONSTANT_TYPE() throws Exception {
        final AvailableExpressionTypeFilter filterType = documentWizardPageUnderTest.getConstantTypeOnlyExpressionViewerFilter();
        assertThat(filterType).isNotNull();
        assertThat(filterType.getContentTypes()).containsOnly(ExpressionConstants.CONSTANT_TYPE);
    }

    @Test
    public void should_createDocumentMIMETypeExpressionViewer_returns_ExpressionViewer_with_Filter_accepting_only_CONSTANT_TYPE() throws Exception {
        documentWizardPageUnderTest.createDocumentMIMETypeExpressionViewer(mock(Composite.class));
        verify(expressionViewerMock).addFilter(documentWizardPageUnderTest.getConstantTypeOnlyExpressionViewerFilter());
    }

    @Test
    public void should_createDocumentURLExpressionViewer_returns_ExpressionViewer_with_Filter_accepting_only_CONSTANT_TYPE() throws Exception {
        documentWizardPageUnderTest.createDocumentURL(composite, dbc);
        verify(expressionViewerMock).addFilter(documentWizardPageUnderTest.getConstantTypeOnlyExpressionViewerFilter());
    }

    @Test
    public void should_createDocumentInitialMultipleContent_should_create_ExpressionViewer_with_filter_accepting_only_Groovy_script() throws Exception {
        documentWizardPageUnderTest.createDocumentInitialMultipleContent(composite, dbc);
        verify(documentWizardPageUnderTest).createExpressionViewerWithGroovyScriptOnly(composite);
    }

    @Test
    public void should_bindDocumentName_bind_documentText_and_document_name() throws Exception {
        final Text text = new Text(composite, SWT.NONE);

        documentWizardPageUnderTest.bindDocumentName(text, dbc);

        assertThat(document.getName()).isEmpty();

        text.setText("myDocumentName");

        assertThat(document.getName()).isEqualTo("myDocumentName");

        text.setText("my Document Name");

        assertThat(document.getName()).isEqualTo("myDocumentName");

        assertThat(dbc.getValidationStatusProviders()).hasSize(1);

        final ValidationStatusProvider validationStatus = (ValidationStatusProvider) dbc.getValidationStatusProviders().get(0);
        final IObservableValue iObservableValue = validationStatus.getValidationStatus();
        final IStatus status = (IStatus) iObservableValue.getValue();

        assertThat(status.isOK()).isFalse();
        assertThat(status.getMessage()).isEqualTo(Messages.bind(Messages.nameCantHaveAWhitespace, "my Document Name"));
    }

}
