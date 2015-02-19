/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.adapters.FormRemovedAdapter;
import org.bonitasoft.studio.properties.sections.forms.adapters.WidgetAddedOrRemoved;
import org.bonitasoft.studio.properties.sections.forms.commands.DuplicateFormCommand;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 *
 * @author Charles Souillard
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 */
public class FormsUtils {

    public static enum WidgetEnum {
        TEXT, TEXT_AREA, COMBO, CHECKBOX,CHECKBOX_LIST, DATE, LIST, PASSWORD, RADIO, SELECT, FILE
    };


    public static void duplicateForm(final Element pageFlow, final TransactionalEditingDomain editingDomain, final EStructuralFeature feature, final Form baseForm, final String id, final String formDesc) {
        try {
            OperationHistoryFactory.getOperationHistory().execute(new DuplicateFormCommand(pageFlow, feature, baseForm, id, formDesc, editingDomain),
                    Repository.NULL_PROGRESS_MONITOR, null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * create the diagram of the form and put it in the same resource file
     *
     * @param form
     *            create the diagram corresponding to this form
     * @return created diagram
     */
    public static void createFormDiagram(final Form form, final TransactionalEditingDomain editingDomain) {
        final AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "", null) { //$NON-NLS-1$

            @Override
            protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                final Diagram diagram = ViewService.createDiagram(form, FormEditPart.MODEL_ID, FormDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                final Resource resource =  getEditingDomain().getResourceSet().getResource(form.eResource().getURI(), false);
                resource.getContents().add(diagram);
                diagram.persist();
                diagram.setElement(form);
                return CommandResult.newOKCommandResult();
            }

        };

        try {
            OperationHistoryFactory.getOperationHistory().execute(command, null, null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e); //$NON-NLS-1$
        }
    }


    /**
     * open the diagram corresponding to the form
     *
     * @param form
     *            the form to open
     */
    public static DiagramEditor openDiagram(final Form form,final EditingDomain domain) {

        /* get the Diagram element related to the form in the resource */
        final Diagram diag = ModelHelper.getDiagramFor(form,domain);

        /*
         * need to get the URI after save because the name can change as it is
         * synchronized with the MainProcess name
         */
        final URI uri = EcoreUtil.getURI(diag);

        /* open the form editor */
        final FormDiagramEditor formEditor = (FormDiagramEditor) EditorService.getInstance().openEditor(new URIEditorInput(uri, form.getName()));
        formEditor.getDiagramGraphicalViewer().select(formEditor.getDiagramEditPart());
        if (form != null && formEditor != null) {
            final MainProcess diagram = ModelHelper.getMainProcess(form);
            handleReadOnlyModeOnEditor(formEditor, diagram);
        }
        ensureAdaptersForDeletionOrRenamingWellSetted(form, formEditor);
        final MainProcess mainProcess = ModelHelper.getMainProcess(form);

        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final IRepositoryFileStore file = diagramStore.getChild(NamingUtils.toDiagramFilename(mainProcess));
        if (file.isReadOnly()) {
            formEditor.getDiagramEditPart().disableEditMode();
        }
        return formEditor;
    }

    protected static void handleReadOnlyModeOnEditor(final FormDiagramEditor formEditor, final MainProcess diagram) {
        if (diagram != null) {
            final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getCurrentRepository()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            final DiagramFileStore diagramFile = diagramStore.getDiagram(diagram.getName(), diagram.getVersion());

            if (diagramFile != null && diagramFile.isReadOnly()) {
                formEditor.getDiagramEditPart().disableEditMode();
                if (formEditor instanceof FormDiagramEditor) {
                    formEditor.setReadOnly(true);
                }
            }
        }
    }

    protected static void ensureAdaptersForDeletionOrRenamingWellSetted(final Form form, final FormDiagramEditor formEditor) {
        final EObject parent = form.eContainer();
        if (parent instanceof PageFlow) {
            parent.eAdapters().add(new FormRemovedAdapter(form, formEditor));
        }
        final EList<Adapter> eAdapters = form.eAdapters();
        boolean alreadyHere = false;
        for (final Adapter adapter : eAdapters) {
            if (adapter instanceof WidgetAddedOrRemoved) {
                alreadyHere = true;
                break;
            }
        }
        if (!alreadyHere) {
            final WidgetAddedOrRemoved adapterImpl = new WidgetAddedOrRemoved(form);
            eAdapters.add(adapterImpl);
        }
    }

    /**
     * @param form
     * @param srcName
     * @param name
     */
    public static void changeIdInTemplate(final Form form, final String oldId, final String newId) {
        final HtmlTemplateGenerator generator = (HtmlTemplateGenerator) ExporterService.getInstance().getExporterService(SERVICE_TYPE.HtmlTemplateGenerator);
        final File file = WebTemplatesUtil.getFile(form.getHtmlTemplate().getPath());
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            final File tempFile = File.createTempFile("tempForm", ".html");
            final FileWriter fileWriter = new FileWriter(tempFile);
            generator.changeDivId(oldId, newId, fis, fileWriter);
            fis.close();
            fileWriter.close();
            FileUtil.copy(tempFile, file);
            WebTemplatesUtil.refreshFile(form.getHtmlTemplate().getPath());
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    new BonitaErrorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.Error, "Unexpected error", e).open();
                }
            });
        }
    }



}
