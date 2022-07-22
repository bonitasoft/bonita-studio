/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface.databinding.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @author Baptiste Mesta
 *
 */
public class WrappingValidator implements IValidator {



    private IValidator validator;
    private final ControlDecoration controlDecoration;
    private final boolean onlyChangeDecoration;
    private final Image error;
    private final Image info;
    private final Image warn;
    private final boolean updateMessage;

    /**
     * @param controlDecoration
     * @param validator
     */
    public WrappingValidator(ControlDecoration controlDecoration, IValidator validator) {
        this(controlDecoration,validator,false);
    }
    /**
     * @param controlDecoration
     * @param validator
     */
    public WrappingValidator(ControlDecoration controlDecoration, IValidator validator, boolean onlyChangeDecoration) {
        this(controlDecoration, validator, onlyChangeDecoration, false);
    }

    /**
     * @param controlDecoration
     * @param validator
     */
    public WrappingValidator(ControlDecoration controlDecoration, IValidator validator, boolean onlyChangeDecoration, boolean updateMessage) {
        this.controlDecoration = controlDecoration;
        this.validator = validator;
        this.onlyChangeDecoration = onlyChangeDecoration;
        this.updateMessage = updateMessage;

        FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
        error = fieldDecoration.getImage();
        fieldDecoration = FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_WARNING);
        warn = fieldDecoration.getImage();
        fieldDecoration = FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION);
        info = fieldDecoration.getImage();

    }


    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        IStatus validate = validator.validate(value);
        int severity = validate.getSeverity();
        if(severity>IStatus.OK){
            controlDecoration.show();
            if(severity>=IStatus.ERROR){
                controlDecoration.setImage(error);
            }else if(severity==IStatus.WARNING){
                controlDecoration.setImage(warn);
            }else if(severity==IStatus.INFO){
                controlDecoration.setImage(info);
            }
            if(updateMessage){
                controlDecoration.setDescriptionText(validate.getMessage());
            }
        }else{
            controlDecoration.hide();
        }
        if(onlyChangeDecoration){
            return Status.OK_STATUS;
        }else{
            return validate;
        }
    }
    public void setValidator(IValidator iValidator) {
        validator = iValidator;

    }

}
