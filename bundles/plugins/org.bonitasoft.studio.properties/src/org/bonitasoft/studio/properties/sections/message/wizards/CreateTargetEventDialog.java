/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.properties.sections.message.wizards;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class CreateTargetEventDialog extends Dialog {

    private ComboViewer comboProcess;
    private ComboViewer comboEvent;
    private String targetProcessName;

    private String targetEventName;
    private GridData gd;
    private final Element element;

    protected CreateTargetEventDialog(final Shell parentShell, final Element element) {
        super(parentShell);
        this.element = element;
    }

    @Override
    protected Button createButton(final Composite parent, final int id, final String label,
            final boolean defaultButton) {

        final Button b = super.createButton(parent, id, label, defaultButton);
        updateButtons();
        return b;
    }

    @Override
    protected Control createContents(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        final Label processNameLabel = new Label(composite, SWT.NONE);
        processNameLabel.setText(Messages.processNameLabel);

        comboProcess = new ComboViewer(composite, SWT.NONE);
        gd = new GridData();
        gd.widthHint = 120;

        comboProcess.getControl().setLayoutData(gd);
        comboProcess.setContentProvider(ArrayContentProvider.getInstance());
        comboProcess.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        comboProcess.setInput(ModelHelper.getAllProcesses(element));
        comboProcess.getCombo().addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                setTargetProcessName(comboProcess.getCombo().getText());
                refresh();
                updateButtons();
            }

        });

        final Label eventNameLabel = new Label(composite, SWT.NONE);
        eventNameLabel.setText(Messages.eventNameLabel);

        comboEvent = new ComboViewer(composite, SWT.NONE);
        comboEvent.getControl().setLayoutData(gd);
        comboEvent.setContentProvider(ArrayContentProvider.getInstance());
        comboEvent.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        comboEvent.getCombo().addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                setTargetEventName(comboEvent.getCombo().getText());
                updateButtons();
            }
        });
        updateButtons();
        return super.createContents(parent);
    }

    private void refresh() {
        if (comboProcess.getCombo().getText() != null && comboProcess.getCombo().getText().length() > 0) {
            final AbstractProcess proc = ModelHelper.findProcess(element, comboProcess.getCombo().getText());
            comboEvent.setInput(ModelHelper.getAllCatchEvent(proc));
        }

    }

    private void updateButtons() {
        final Button okButton = getButton(Dialog.OK);
        if (okButton != null) {
            if (getTargetEventName() != null && getTargetEventName().length() > 0
                    && getTargetProcessName() != null && getTargetProcessName().length() > 0) {
                okButton.setEnabled(true);
            } else {
                okButton.setEnabled(false);
            }
        }
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.addTargetEventDialogTitle);
    }

    /**
     * @return the targetEventName
     */
    public String getTargetEventName() {
        return targetEventName;
    }

    /**
     * @param targetEventName the targetEventName to set
     */
    public void setTargetEventName(final String targetEventName) {
        this.targetEventName = targetEventName;
    }

    /**
     * @return the targetProcessName
     */
    public String getTargetProcessName() {
        return targetProcessName;
    }

    /**
     * @param targetProcessName the targetProcessName to set
     */
    public void setTargetProcessName(final String targetProcessName) {
        this.targetProcessName = targetProcessName;
    }

    /**
     * Prevent the wizard to close accidentally by pressing escape (or the red cross)
     *
     * @see org.eclipse.jface.window.Window#canHandleShellCloseEvent()
     */
    @Override
    protected boolean canHandleShellCloseEvent() {
        final Boolean close = MessageDialog.openQuestion(getShell(), org.bonitasoft.studio.common.Messages.handleShellCloseEventTitle,
                org.bonitasoft.studio.common.Messages.handleShellCloseEventMessage);
        if (close) {
            return super.canHandleShellCloseEvent();
        } else {
            return false;
        }
    }

}
