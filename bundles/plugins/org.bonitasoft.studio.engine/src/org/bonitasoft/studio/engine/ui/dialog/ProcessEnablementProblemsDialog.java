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
package org.bonitasoft.studio.engine.ui.dialog;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.process.Problem;
import org.bonitasoft.engine.bpm.process.Problem.Level;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.handler.ConfigureHandler;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.ui.dialog.ProblemsDialog;
import org.bonitasoft.studio.ui.provider.TypedLabelProvider;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 * 
 */
public class ProcessEnablementProblemsDialog extends ProblemsDialog<Problem> {

    private AbstractProcess process;

    private List<Problem> processResolutionProblems;

    public ProcessEnablementProblemsDialog(Shell parentShell, String dialogMessage, AbstractProcess process, List<Problem> processResolutionProblems) {
        super(parentShell, Messages.processEnableFailedTitle,  dialogMessage, INFORMATION, new String[] { Messages.configure,
                IDialogConstants.CANCEL_LABEL });
        this.process = process;
        this.processResolutionProblems = processResolutionProblems;
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            try {
                close();
                setReturnCode(openConfigureDialog().isOK() ? IDialogConstants.OK_ID : IDialogConstants.CANCEL_ID);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            super.buttonPressed(buttonId);
        }
    }

    protected IStatus openConfigureDialog() throws ExecutionException {
        ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        Command cmd = service.getCommand("org.bonitasoft.studio.configuration.configure");
        Map<String, Object> parameters = new HashMap<String, Object>();
        String configuration = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        parameters.put("configuration", configuration);
        parameters.put("process", process);
        return (IStatus) new ConfigureHandler().execute(new ExecutionEvent(cmd, parameters, null, null));
    }


    @Override
    protected Collection<Problem> getInput() {
        return processResolutionProblems;
    }

    @Override
    protected TypedLabelProvider<Problem> getTypedLabelProvider() {
        return new TypedLabelProvider<Problem>() {

            @Override
            public String getText(Problem element) {
                return element.getDescription();
            }

            @Override
            public Image getImage(Problem element) {
                return  element.getLevel() == Level.ERROR ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR) : JFaceResources
                        .getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
            }
        };
    }

}
