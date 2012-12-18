/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 *
 */
public class BatchValidationHandler extends AbstractHandler {

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Map parameters = event.getParameters();
        Set<Diagram> toValidate = new HashSet<Diagram>();
        if(parameters != null && !parameters.isEmpty() && parameters.get("diagrams") != null){
            toValidate = (Set<Diagram>) parameters.get("diagrams");
        }
        if(toValidate.isEmpty()){
            IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
            if(part instanceof DiagramEditor){
                toValidate.add( ((DiagramEditor) part).getDiagram());
            }
        }

        final IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
        final BatchValidationOperation validateOperation = new BatchValidationOperation();
        validateOperation.setDiagramToValidate(toValidate);
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                try {
                    service.run(true, false, validateOperation );
                } catch (InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
        });

        Object showReport = parameters.get("showReport");
        if(showReport == null){
            showReport = Boolean.TRUE;
        }
        if(showReport instanceof Boolean){
            if(((Boolean)showReport).booleanValue()){
                showReport(validateOperation.getResult());
            }
        }
        return validateOperation.getResult();
    }

    private void showReport(IStatus status) {
        if(status == null || !status.isOK()){
            StringBuilder report = new StringBuilder("");
            for(IStatus s : status.getChildren()){
                report.append(s.getMessage());
                report.append("\n");
            }
            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,Messages.bind(Messages.validationFailedMessage,report.toString()));
        }
    }

}
