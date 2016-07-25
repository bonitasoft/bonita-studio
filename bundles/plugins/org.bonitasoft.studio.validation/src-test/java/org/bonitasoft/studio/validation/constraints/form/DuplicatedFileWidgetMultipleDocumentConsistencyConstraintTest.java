package org.bonitasoft.studio.validation.constraints.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DuplicatedFileWidgetMultipleDocumentConsistencyConstraintTest {

    @Mock
    private IValidationContext context;

    @Spy
    private DuplicatedFileWidgetMultipleDocumentConsistencyConstraint constraint;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(anyObject())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shoudFailedWhenFileWidgetInitialValue_isMultipleDocument_and_duplicatedIsNotSet() {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(List.class.getName());
        widget.setInputExpression(expr);
        widget.setDuplicate(false);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void shouldNotFailedWhenFileWidgetInitialValue_isMultipleDocument_and_duplicatedIsSet() {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(List.class.getName());
        widget.setInputExpression(expr);
        widget.setDuplicate(true);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();

    }

    @Test
    public void shouldNotFailedWhenFileWidgetInitialValue_isNotMultipleDocument_and_duplicatedIsSet() {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(String.class.getName());
        widget.setInputExpression(expr);
        widget.setDuplicate(true);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void shouldNotFailedWhenImageWidgetInitialValue_isNotMultipleDocument_and_duplicatedIsSet() {
        final ImageWidget widget = FormFactory.eINSTANCE.createImageWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(String.class.getName());
        widget.setImgPath(expr);
        widget.setDuplicate(true);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void shouldNotFailedWhenImageWidgetInitialValue_isMultipleDocument_and_duplicatedIsSet() {
        final ImageWidget widget = FormFactory.eINSTANCE.createImageWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(List.class.getName());
        widget.setImgPath(expr);
        widget.setDuplicate(true);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();

    }

    @Test
    public void shoudFailedWhenImageWidgetInitialValue_isMultipleDocument_and_duplicatedIsNotSet() {
        final ImageWidget widget = FormFactory.eINSTANCE.createImageWidget();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setReturnType(List.class.getName());
        widget.setImgPath(expr);
        widget.setDuplicate(false);
        when(context.getTarget()).thenReturn(widget);
        final IStatus status = constraint.performBatchValidation(context);
        assertThat(status.isOK()).isFalse();
    }


}
