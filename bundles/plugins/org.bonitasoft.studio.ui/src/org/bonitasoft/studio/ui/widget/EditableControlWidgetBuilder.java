/*******************************************************************************
 * Copyright (C) 2016 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

/**
 * @author Adrien lachambre
 */
public abstract class EditableControlWidgetBuilder<T, W extends EditableControlWidget>
        extends ControlWidgetBuilder<T, EditableControlWidget> {

    protected boolean readOnly = false;
    protected int labelWidth = SWT.DEFAULT;
    protected int horizontalLabelAlignment = SWT.RIGHT;
    protected int verticalLabelAlignment = SWT.CENTER;
    protected String message;
    protected boolean useNativeRender = false;

    /**
     * Set the control in read-only mode.
     */
    public T readOnly() {
        this.readOnly = true;
        return (T) this;
    }

    public T readOnly(boolean isReadOnly) {
        this.readOnly = isReadOnly;
        return (T) this;
    }

    /**
     * Adds a text message under the control. Should be use when additional user guidance is needed.
     */
    public T withMessage(String message) {
        this.message = message;
        return (T) this;
    }

    /**
     * Sets the {@link Label} with.
     * 
     * @see {@link org.bonitasoft.studio.ui.widget.ControlWidgetBuilder#withLabelWidth(int) withLabelWidth} to set a label
     */
    public T withLabelWidth(int labelWidth) {
        this.labelWidth = labelWidth;
        return (T) this;
    }

    /**
     * Sets the {@link Label} horizontal alignment to <em>SWT.LEFT</em>.
     * Default alignment is<em>SWT.RIGHT</em>.
     */
    public T alignLabelLeft() {
        this.horizontalLabelAlignment = SWT.LEFT;
        return (T) this;
    }

    /**
     * Sets the {@link Label} vertical alignment to <em>SWT.TOP</em>.
     * Default alignment is<em>SWT.CENTER</em>.
     */
    public T alignLabelTop() {
        this.verticalLabelAlignment = SWT.TOP;
        return (T) this;
    }

    public T useNativeRender() {
        this.useNativeRender = true;
        return (T) this;
    }
}
