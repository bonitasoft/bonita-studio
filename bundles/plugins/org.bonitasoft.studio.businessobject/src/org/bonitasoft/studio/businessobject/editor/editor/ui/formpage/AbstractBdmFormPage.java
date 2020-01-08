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

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.studio.businessobject.editor.converter.BusinessDataModelConverter;
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.CleanDeployContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.DeployContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ExportBDMContributionItem;
import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.ImportBDMContributionItem;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.ToolBar;
import org.xml.sax.SAXException;

public abstract class AbstractBdmFormPage extends AbstractFormPage<BusinessObjectModel> {

    protected IObservableValue<BusinessObjectModel> workingCopyObservable;
    protected IObservableValue<BusinessObject> boSelectedObservable;
    protected BusinessDataModelEditorContribution editorContribution;

    public AbstractBdmFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context);
        this.editorContribution = editorContribution;
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

}
