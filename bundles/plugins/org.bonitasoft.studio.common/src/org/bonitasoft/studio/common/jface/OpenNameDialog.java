/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.jface;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 */
public class OpenNameDialog extends Dialog {

    private final String srcName;
    private String newName;
    private String message;
    private IObservableValue observable;
    private UpdateValueStrategy targetToModel;
    private final EMFDataBindingContext context;
    private Binding binding;
    private UpdateValueStrategy modelToTarget;
    private String title;

    public OpenNameDialog(final Shell parentShell, final String title, final String name) {
        this(parentShell, name);
        this.title = title;
    }

    /**
     * @param name
     * @param version
     */
    public OpenNameDialog(final Shell parentShell, final String name) {
        super(parentShell);
        srcName = name;
        newName = name;
        context = new EMFDataBindingContext();
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        if (title == null) {
            newShell.setText(Messages.openNameAndVersionDialogTitle);
        } else {
            newShell.setText(title);
        }
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(0, 20).create());
        if (message != null) {
            final Label messageLabel = new Label(res, SWT.WRAP);
            messageLabel.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
            messageLabel.setText(message);

        }
        final Label nameLabel = new Label(res, SWT.NONE);
        nameLabel.setText(Messages.name);
        final Text nameText = new Text(res, SWT.BORDER);
        nameText.setText(srcName);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
        if (observable != null) {
            binding = context.bindValue(SWTObservables.observeDelayedValue(400, SWTObservables.observeText(nameText, SWT.Modify)), observable, targetToModel,
                    modelToTarget);
            ControlDecorationSupport.create(binding, SWT.LEFT);
        }
        nameText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                newName = nameText.getText();
                updateOkButton();
            }
        });
        return res;
    }

    protected void updateOkButton() {
        final Button button = getButton(OK);
        boolean isValid = newName != null && newName.length() > 0 &&
                !(NamingUtils.convertToId(newName).equalsIgnoreCase(NamingUtils.convertToId(srcName)));
        if (isValid == true) {
            if (binding != null) {
                if (targetToModel != null) {
                    binding.validateTargetToModel();
                }
                if (modelToTarget != null) {
                    binding.validateModelToTarget();
                }
                final IStatus status = (IStatus) binding.getValidationStatus().getValue();
                if (!status.isOK()) {
                    isValid = false;
                }
            }
        }

        button.setEnabled(isValid);

    }

    @Override
    public void create() {
        super.create();
        updateOkButton();
    }

    public String getNewName() {
        return newName;
    }

    /**
     * @return the srcName
     */
    public String getSrcName() {
        return srcName;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setBinding(final IObservableValue observable, final UpdateValueStrategy targetToModel, final UpdateValueStrategy modelToTarget) {
        this.observable = observable;
        this.targetToModel = targetToModel;
        this.modelToTarget = modelToTarget;
    }

}
