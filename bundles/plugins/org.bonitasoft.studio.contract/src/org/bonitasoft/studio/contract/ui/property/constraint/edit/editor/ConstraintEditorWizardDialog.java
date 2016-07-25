/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.util.Objects;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;


/**
 * @author Romain Bioteau
 *
 */
public class ConstraintEditorWizardDialog extends WizardDialog {

    public ConstraintEditorWizardDialog(final Shell parentShell, final IWizard newWizard) {
        super(parentShell, newWizard);
        setHelpAvailable(true);
    }

    @Override
    protected Control createHelpControl(final Composite parent) {
        return createHelpImageButton(parent, getHelpImage());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        configureContext();
        return super.createContents(parent);
    }

    private void configureContext() {
        final IEclipseContext e4Context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        while (!Objects.equals(e4Context.getActiveLeaf(), e4Context)) {
            e4Context.getActiveLeaf().deactivate();
        }
        final IEclipseContext expressionDialogContext = e4Context.createChild("expressionDialogContext");
        expressionDialogContext.activate();
        getShell().setData("org.eclipse.e4.ui.shellContext", expressionDialogContext);
    }

    protected Image getHelpImage() {
        return JFaceResources.getImage(DLG_IMG_HELP);
    }

    private ToolBar createHelpImageButton(final Composite parent, final Image image) {
        final ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.NO_FOCUS);
        ((GridLayout) parent.getLayout()).numColumns++;
        toolBar.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        final Cursor cursor = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);
        toolBar.setCursor(cursor);
        toolBar.addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(final DisposeEvent e) {
                cursor.dispose();
            }
        });
        final ToolItem fHelpButton = new ToolItem(toolBar, SWT.CHECK);
        fHelpButton.setImage(image);
        fHelpButton.setToolTipText(JFaceResources.getString("helpToolTip")); //$NON-NLS-1$
        fHelpButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                helpPressed();
            }
        });
        return toolBar;
    }

}
