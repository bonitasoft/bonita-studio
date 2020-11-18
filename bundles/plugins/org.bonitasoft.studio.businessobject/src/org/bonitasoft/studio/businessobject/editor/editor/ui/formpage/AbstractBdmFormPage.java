/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage;

import java.io.IOException;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.studio.businessobject.converter.BusinessDataModelConverter;
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.CleanDeployContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.DeployContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ExploreBDMContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ExportBDMContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ImportBDMContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ValidateContributionItem;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.refactor.BDMRefactorQueue;
import org.bonitasoft.studio.businessobject.refactor.DiffElement;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.xml.sax.SAXException;

public abstract class AbstractBdmFormPage extends AbstractFormPage<BusinessObjectModel> {

    private static final String REFACTOR_ACCESS_CTRL_COMMAND = "org.bonitasoft.studio.bdm.access.control.refactor.command";

    protected IObservableValue<BusinessObjectModel> workingCopyObservable;
    protected IObservableValue<BusinessObject> boSelectedObservable;
    protected BusinessDataModelEditorContribution editorContribution;
    private CommandExecutor commandExecutor;

    public AbstractBdmFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context);
        this.editorContribution = editorContribution;
        commandExecutor = new CommandExecutor();
    }

    public void init(IObservableValue<BusinessObjectModel> workingCopyObservable,
            IObservableValue<BusinessObject> boSelectedObservable, IDocument document) {
        super.init(workingCopyObservable.getValue(), document);
        this.workingCopyObservable = workingCopyObservable;
        this.boSelectedObservable = boSelectedObservable;
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        toolBarManager.add(new DeployContributionItem(this));
        toolBarManager.add(new CleanDeployContributionItem(this));
        toolBarManager.add(new ExportBDMContributionItem(this));
        toolBarManager.add(new ImportBDMContributionItem(this));
        toolBarManager.add(new ExploreBDMContributionItem());
        toolBarManager.add(new ValidateContributionItem(this));
        toolBarManager.update(true);
    }

    public abstract void makeDirty();

    public IObservableValue<BusinessObjectModel> observeWorkingCopy() {
        return workingCopyObservable;
    }

    public IObservableValue<BusinessObject> observeBusinessObjectSelected() {
        return boSelectedObservable;
    }

    public BusinessObjectModelConverter getParser() {
        return editorContribution.getParser();
    }

    public BusinessDataModelConverter getConverter() {
        return editorContribution.getConverter();
    }

    public BusinessDataModelEditorContribution getEditorContribution() {
        return editorContribution;
    }

    public void updateWorkingCopy() {
        try {
            BusinessObjectModel businessObjectModel = getConverter()
                    .toEmfModel(getParser().unmarshall((getDocument().get().getBytes())),
                            editorContribution.loadBdmArtifactDescriptor());
            if (isErrorState()) {
                setErrorState(false);
            }
            workingCopyObservable.getRealm().exec(() -> workingCopyObservable.setValue(businessObjectModel));
            // TODO refresh bdm artifact descriptor?? 
        } catch (IOException | SAXException | JAXBException e) {
            setErrorState(true);
        }
    }

    public abstract void refreshBusinessObjectList();

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (isDirty()) {
            super.doSave(monitor);
        }
    }

    public void addToAccessControlRefactorQueue(DiffElement diff) {
        if (commandExecutor.canExecute(REFACTOR_ACCESS_CTRL_COMMAND, null)) {
            BDMRefactorQueue.getInstance().add(diff);
        }
    }

    public void refactorAccessControl() {
        if (commandExecutor.canExecute(REFACTOR_ACCESS_CTRL_COMMAND, null)) {
            commandExecutor.executeCommand(REFACTOR_ACCESS_CTRL_COMMAND, null);
        }
    }

    public void refactorAccessControl(DiffElement diff) {
        addToAccessControlRefactorQueue(diff);
        refactorAccessControl();
    }

    public void updateDefaultQueries() {
        Display.getDefault().asyncExec(() -> {
            BusinessObject businessObject = observeBusinessObjectSelected().getValue();
            Stream<Query> newDefaultQueries = getConverter().createDefaultQueries(businessObject);
            businessObject.getDefaultQueries().clear();
            newDefaultQueries.forEach(businessObject.getDefaultQueries()::add);
        });
    }

}
