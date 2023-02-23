/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.widget;

import java.util.Optional;

import org.bonitasoft.studio.ui.databinding.UpdateValueStrategyFactory;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An abstract {@link ControlWidget} builder.
 */
public abstract class ControlWidgetBuilder<T, W extends ControlWidget> {

    protected final GridData gridData = new GridData();

    protected boolean labelAbove = false;

    protected String label;
    protected UpdateValueStrategy modelToTargetStrategy;
    protected UpdateValueStrategy targetToModelStrategy;
    protected Object layoutData;
    protected IObservableValue modelObservable;
    protected DataBindingContext ctx;
    protected String id;
    protected Optional<FormToolkit> toolkit = Optional.empty();
    protected Optional<IValidator> validator = Optional.empty();
    protected Optional<Integer> delay = Optional.empty();

    /**
     * Adds a text label next to the control.
     * 
     * @see {@link org.bonitasoft.studio.ui.widget.ControlWidgetBuilder#labelAbove() labelAbove} to change label position
     */
    public T withLabel(String label) {
        this.label = label;
        return (T) this;
    }

    public T withId(String id) {
        this.id = id;
        return (T) this;
    }

    /**
     * Set the position of the text label above the control.
     */
    public T labelAbove() {
        this.labelAbove = true;
        return (T) this;
    }

    public T adapt(FormToolkit toolkit) {
        this.toolkit = Optional.ofNullable(toolkit);
        return (T) this;
    }

    /**
     * Set a layout data for this control.
     * If set, this layout data override the default {@link GridData} of this control.
     * When placing this control in a {@link GridLayout} you may directly use helper methods of this {@link ControlWidgetBuilder}
     * 
     * @see {@link GridData},{@link RowData}, {@link FormData}
     */
    public T withLayoutData(Object layoutData) {
        this.layoutData = layoutData;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.FILL</em> alignment style.
     */
    public T fill() {
        this.gridData.horizontalAlignment = SWT.FILL;
        this.gridData.verticalAlignment = SWT.FILL;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.LEFT</em> horizontal alignment style.
     */
    public T alignLeft() {
        this.gridData.horizontalAlignment = SWT.LEFT;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.CENTER</em> horizontal alignment style.
     */
    public T center() {
        this.gridData.horizontalAlignment = SWT.CENTER;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.RIGHT</em> horizontal alignment style.
     */
    public T alignRight() {
        this.gridData.horizontalAlignment = SWT.RIGHT;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.TOP</em> vertical alignment style.
     */
    public T alignTop() {
        this.gridData.verticalAlignment = SWT.TOP;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.BOTTOM</em> vertical alignment style.
     */
    public T alignBottom() {
        this.gridData.verticalAlignment = SWT.BOTTOM;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} with a <em>SWT.CENTER</em> vertical alignment style.
     */
    public T alignMiddle() {
        this.gridData.verticalAlignment = SWT.CENTER;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} to grab excess horizontal space.
     */
    public T grabHorizontalSpace() {
        this.gridData.grabExcessHorizontalSpace = true;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} to grab excess vertical space.
     */
    public T grabVerticalSpace() {
        this.gridData.grabExcessVerticalSpace = true;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} horizontal span.
     * 
     * @param horizontalSpan specifies the number of column cells that the control will take up. The default value is 1.
     */
    public T horizontalSpan(int horizontalSpan) {
        this.gridData.horizontalSpan = horizontalSpan;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} vertical span.
     * 
     * @param verticalSpan specifies the number of row cells that the control will take up. The default value is 1.
     */
    public T verticalSpan(int verticalSpan) {
        this.gridData.verticalSpan = verticalSpan;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} height hint.
     * 
     * @param heightHint specifies the preferred height in pixels. This value is the hHint passed into Control.computeSize(int, int, boolean) to determine the
     *        preferred size of the control. The default value is SWT.DEFAULT.
     */
    public T heightHint(int heightHint) {
        this.gridData.heightHint = heightHint;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} width hint.
     * 
     * @param widthHint specifies the preferred width in pixels. This value is the wHint passed into Control.computeSize(int, int, boolean) to determine the
     *        preferred size of the control. The default value is SWT.DEFAULT.
     */
    public T widthHint(int widthHint) {
        this.gridData.widthHint = widthHint;
        return (T) this;
    }

    public T minimumWidth(int minimumWidth) {
        this.gridData.minimumWidth = minimumWidth;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} verticalIndent.
     * 
     * @param verticalIndent specifies the number of pixels of indentation that will be placed along the top side of the cell.
     *        The default value is 0.
     */
    public T verticalIndent(int verticalIndent) {
        this.gridData.verticalIndent = verticalIndent;
        return (T) this;
    }

    /**
     * Configure the {@link GridData} horizontalIndent.
     * 
     * @param horizontalIndent specifies the number of pixels of indentation that will be placed along the left side of the cell.
     *        The default value is 0.
     */
    public T horizontalIndent(int horizontalIndent) {
        this.gridData.horizontalIndent = horizontalIndent;
        return (T) this;
    }

    /**
     * Sets a target to model {@link UpdateValueStrategy} for the given binding.
     * 
     * @see {@link ControlWidgetBuilder#bindTo}
     */
    public T withTargetToModelStrategy(UpdateValueStrategy targetToModelStrategy) {
        this.targetToModelStrategy = targetToModelStrategy;
        return (T) this;
    }

    /**
     * Sets a model to target {@link UpdateValueStrategy} for the given binding.
     * 
     * @see {@link ControlWidgetBuilder#bindTo}
     */
    public T withModelToTargetStrategy(UpdateValueStrategy modelToTargetStrategy) {
        this.modelToTargetStrategy = modelToTargetStrategy;
        return (T) this;
    }

    /**
     * Sets a target to model {@link UpdateValueStrategy} for the given binding.
     * 
     * @see {@link ControlWidgetBuilder#bindTo}, {@link UpdateValueStrategyFactory}
     */
    public T withTargetToModelStrategy(UpdateValueStrategyFactory targetToModelStrategyFactory) {
        this.targetToModelStrategy = targetToModelStrategyFactory.create();
        return (T) this;
    }

    /**
     * Sets a model to target {@link UpdateValueStrategy} for the given binding.
     * 
     * @see {@link ControlWidgetBuilder#bindTo}, {@link UpdateValueStrategyFactory}
     */
    public T withModelToTargetStrategy(UpdateValueStrategyFactory modelToTargetStrategyFactory) {
        this.modelToTargetStrategy = modelToTargetStrategyFactory.create();
        return (T) this;
    }
    
    public T withDelay(int delay) {
        this.delay = Optional.of(delay);
        return (T) this;
    }

    /**
     * Sets the model {@link IObservableValue} to bind to this control
     */
    public T bindTo(IObservableValue modelObservable) {
        this.modelObservable = modelObservable;
        return (T) this;
    }

    public T withValidator(IValidator validator) {
        this.validator = Optional.ofNullable(validator);
        return (T) this;
    }

    /**
     * Sets the {@link DataBindingContext} to use when binding this control
     */
    public T inContext(DataBindingContext context) {
        this.ctx = context;
        return (T) this;
    }

    /**
     * Create an instance of the widget describe by this builder.
     * 
     * @param container is the parent composite of this widget
     */
    public abstract W createIn(Composite container);

}
