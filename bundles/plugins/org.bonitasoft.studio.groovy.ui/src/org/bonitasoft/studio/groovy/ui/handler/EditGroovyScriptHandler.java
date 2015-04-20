/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.handler;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.GroovyScriptFileDialog;
import org.bonitasoft.studio.groovy.ui.wizard.OpenScriptWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class EditGroovyScriptHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        final OpenScriptWizard openScriptWizard = new OpenScriptWizard(true, null);
        final WizardDialog wizardDialog = new CustomWizardDialog(shell, openScriptWizard, Messages.openScriptLabel);
        if (wizardDialog.open() == Dialog.OK) {
            final GroovyFileStore groovyScriptArtifact = (GroovyFileStore) openScriptWizard.getFile();
            String script = "";
            try {
                script = groovyScriptArtifact.getContent();
            } catch (final Exception e) {
                throw new ExecutionException("Failed to retrieve script content", e);
            }
            final GroovyScriptFileDialog bonitaGroovyEditorDialog = new GroovyScriptFileDialog(
                    shell,
                    ExpressionHelper.createGroovyScriptExpression(script, Object.class.getName())
                    ,
                    null,
                    null,
                    null);
            BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

                @Override
                public void run() {
                    if (bonitaGroovyEditorDialog.open() == Dialog.OK) {
                        final boolean disablePopup = FileActionDialog.getDisablePopup();
                        try {
                            final String expression = bonitaGroovyEditorDialog.getExpression().getContent();
                            FileActionDialog.setDisablePopup(true);
                            groovyScriptArtifact.save(expression);
                            GroovyDocumentUtil.refreshUserLibrary();
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        } finally {
                            FileActionDialog.setDisablePopup(disablePopup);
                        }
                    }
                    try {
                        new EditGroovyScriptHandler().execute(null);
                    } catch (final ExecutionException e) {
                        BonitaStudioLog.error(e);
                    }
                }

            });
        }
        return null;
    }
}
