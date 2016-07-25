/**
 *
 */
package org.bonitasoft.studio.exporter.form;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.bonitasoft.forms.server.builder.IFormBuilder;
import org.bonitasoft.forms.server.exception.InvalidFormDefinitionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.builders.FileWidgetBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.builders.DocumentBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FormsExporterTest {

    @Mock
    private IFormBuilder formBuilder;

    @Spy
    private FormsExporter formExporter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldAddSubmitButtonActions_NotAddActionIfLeffOperand_IsEmpty() throws Exception {
        final SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
        formButton.getActions().add(createOperation("", "value", ExpressionConstants.ASSIGNMENT_OPERATOR));
        formExporter.addSubmitButtonActions(formBuilder, formButton);
        verifyZeroInteractions(formBuilder);
    }

    @Test
    public void shouldAddSubmitButtonActions_NotAddActionIfRightOperand_IsEmpty() throws Exception {
        final SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
        formButton.getActions().add(createOperation("data", "", ExpressionConstants.ASSIGNMENT_OPERATOR));
        formExporter.addSubmitButtonActions(formBuilder, formButton);
        verifyZeroInteractions(formBuilder);
    }

    @Test
    public void shouldAddSubmitButtonActions_AddActionIfRightAndLeftOperand_NotEmpty() throws Exception {
        final SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
        final Operation operation = createOperation("data", "value", ExpressionConstants.ASSIGNMENT_OPERATOR);
        formButton.getActions().add(operation);
        formExporter.addSubmitButtonActions(formBuilder, formButton);
        verify(formExporter).addAction(formBuilder, operation);
    }

    @Test
    public void should_addFileWidgetInitialValueExpression_whenTypeIsDocument() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget().withInputType(FileWidgetInputType.DOCUMENT).build();
        final Document document = DocumentBuilder.aDocument().withName("myBonitaDocument").multiple().build();
        final Expression documentExpr = ExpressionFactory.eINSTANCE.createExpression();
        documentExpr.setContent(document.getName());
        fileWidget.setInputExpression(documentExpr);
        formExporter.addDocumentInitialValue(fileWidget, formBuilder);
        verify(formExporter).addInitialValueExpression(formBuilder, documentExpr);
    }

    @Test
    public void should_addFileWidgetInitialValueExpression_whenTypeIsURL() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget().withInputType(FileWidgetInputType.URL).build();
        final Expression documentExpr = ExpressionFactory.eINSTANCE.createExpression();
        documentExpr.setContent("http://www.bonitasoft.com");
        fileWidget.setInputExpression(documentExpr);
        formExporter.addDocumentInitialValue(fileWidget, formBuilder);
        verify(formExporter).addInitialValueExpression(formBuilder, documentExpr);
    }

    private Operation createOperation(final String leftOpreand, final String rightOperand, final String operatorType) {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression leftExpression = ExpressionFactory.eINSTANCE.createExpression();
        leftExpression.setName(leftOpreand);
        leftExpression.setContent(leftOpreand);
        operation.setLeftOperand(leftExpression);

        final Expression rightExpression = ExpressionFactory.eINSTANCE.createExpression();
        rightExpression.setName(leftOpreand);
        rightExpression.setContent(rightOperand);
        operation.setRightOperand(rightExpression);

        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(operatorType);
        operation.setOperator(op);

        return operation;
    }

    @Test
    public void should_addFileWidgetInitialValueExpression_whenDocumentIsScript() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget().
                withInputType(FileWidgetInputType.DOCUMENT).
                havingInputExpression(ExpressionBuilder.anExpression().
                        withName("myDocumentScript").
                        withContent("myDocumentScript").
                        withExpressionType(ExpressionConstants.SCRIPT_TYPE).
                        withReturnType(String.class.getName())).build();
        formExporter.addDocumentInitialValue(fileWidget, formBuilder);
        verify(formExporter).addInitialValueExpression(formBuilder, fileWidget.getInputExpression());
    }

    @Test
    public void should_addDocumentInitialValue_WhenFileDocumentDownloadType_isURL() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget().
                withInputType(FileWidgetInputType.DOCUMENT)
                .notDuplicated().withUrlDownloadType().build();
        formExporter.addFileWidgetInputType(fileWidget, formBuilder);
        verify(formBuilder).addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.URL);
    }

    @Test
    public void should_addDocumentInitialValue_WhenFileDocumentDownloadType_isBrowse() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget().
                withInputType(FileWidgetInputType.DOCUMENT)
                .notDuplicated()
                .withBrowseDownloadType()
                .build();
        formExporter.addFileWidgetInputType(fileWidget, formBuilder);
        verify(formBuilder).addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.FILE);
    }

    @Test
    public void should_addDocumentInitialValue_WhenFileDocumentDownloadType_isBoth() throws InvalidFormDefinitionException {
        final FileWidget fileWidget = FileWidgetBuilder.aFileWidget()
                .withInputType(FileWidgetInputType.DOCUMENT)
                .notDuplicated()
                .withBothDownloadType()
                .build();
        formExporter.addFileWidgetInputType(fileWidget, formBuilder);
        verify(formBuilder).addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.ALL);
    }

}
