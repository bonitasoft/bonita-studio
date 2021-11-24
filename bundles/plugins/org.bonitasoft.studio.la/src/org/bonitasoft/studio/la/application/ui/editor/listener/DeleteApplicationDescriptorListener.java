/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.editor.listener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.la.application.core.DeleteApplicationRunnable;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.xml.sax.SAXException;

public class DeleteApplicationDescriptorListener implements Listener {

    private final ApplicationNode application;
    private final ApplicationFormPage applicationFormPage;
    private final ApplicationNodeContainerConverter applicationNodeContainerConverter;

    public DeleteApplicationDescriptorListener(ApplicationNode application, ApplicationFormPage applicationFormPage) {
        this.application = application;
        this.applicationFormPage = applicationFormPage;
        this.applicationNodeContainerConverter = RepositoryManager.getInstance()
                .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();
    }

    @Override
    public void handleEvent(Event event) {
        if (MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
                org.bonitasoft.studio.ui.i18n.Messages.deleteConfirmation,
                String.format(Messages.deleteDescriptor, application.getDisplayName(),
                        applicationFormPage.getEditorInput().getName()))) {
            final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            final GetApiSessionOperation getApiSessionOperation = new GetApiSessionOperation();
            final APISession session = getApiSessionOperation.execute();
            try {
                final ApplicationAPI applicationAPI = BOSEngineManager.getInstance()
                        .getApplicationAPI(session);
                progressService.run(true, false, new DeleteApplicationRunnable(applicationAPI, application).ignoreErrors());
            } catch (InvocationTargetException | InterruptedException | BonitaHomeNotSetException | ServerAPIException
                    | UnknownAPITypeException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                        String.format(Messages.deleteFailed, application.getDisplayName()),
                        e);
            } finally {
                getApiSessionOperation.logout();
            }
            applicationFormPage.saveExpendedApplications();
            ApplicationNodeContainer workingCopy = applicationFormPage.getWorkingCopy();
            workingCopy.getApplications().remove(application);
            applicationFormPage.removeApplicationFromForm(application);
            try {
                applicationFormPage.getDocument()
                        .set(new String(applicationNodeContainerConverter.marshallToXML(workingCopy)));
            } catch (JAXBException | IOException | SAXException e) {
                throw new RuntimeException("Fail to update the document", e);
            }
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                    org.bonitasoft.studio.ui.i18n.Messages.deleteDoneTitle,
                    String.format(Messages.deleteDescriptorDone, application.getDisplayName()));
        }
    }

}
