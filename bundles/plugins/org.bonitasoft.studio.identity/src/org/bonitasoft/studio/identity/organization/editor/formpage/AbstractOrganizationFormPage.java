/**
  * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.formpage;

import java.io.IOException;
import java.util.Optional;

import org.bonitasoft.studio.identity.organization.editor.OrganizationEditor;
import org.bonitasoft.studio.identity.organization.editor.contribution.DeployContributionItem;
import org.bonitasoft.studio.identity.organization.editor.contribution.ExportContributionItem;
import org.bonitasoft.studio.identity.organization.editor.contribution.ImportContributionItem;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Memberships;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.wst.sse.core.internal.text.JobSafeStructuredDocument;

public abstract class AbstractOrganizationFormPage extends AbstractFormPage<Organization> implements IDocumentListener {

    protected IObservableValue<Organization> workingCopyObservable;
    private OrganizationXMLProcessor xmlProcessor;
    private OrganizationEditor editor;

    public AbstractOrganizationFormPage(String id, String title, IEclipseContext context, OrganizationEditor editor) {
        super(id, title, context);
        this.editor = editor;
    }

    public void init(IObservableValue<Organization> workingCopyObservable, IDocument document,
            OrganizationXMLProcessor xmlProcessor) {
        this.xmlProcessor = xmlProcessor;
        super.init(workingCopyObservable.getValue(), document);
        this.workingCopyObservable = workingCopyObservable;
    }

    @Override
    public void update() {
        // We do not recreate the form, since we use an observable working copy
        retrieveModelFromDocument().ifPresent(model -> {
            updateWorkingCopy(model);
            setErrorState(false);
            Display.getDefault().asyncExec(() -> editor.refreshGroupList()); // Fix issues related to the tree viewer selection cache...
        });
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        toolBarManager.add(new DeployContributionItem(this));
        toolBarManager.add(new ExportContributionItem(this));
        toolBarManager.add(new ImportContributionItem(this));
        toolBarManager.update(true);
    }

    @Override
    protected void createForm() {
        getDocument().addDocumentListener(this);
    }

    public IObservableValue<Organization> observeWorkingCopy() {
        return workingCopyObservable;
    }

    public void updateWorkingCopy() {
        Optional<Organization> orga = xmlToModel(getDocument().get().getBytes());
        if (orga.isPresent()) {
            if (isErrorState()) {
                setErrorState(false);
            }
            workingCopyObservable.getRealm().exec(() -> workingCopyObservable.setValue(orga.get()));
        } else {
            setErrorState(true);
        }
    }

    public abstract AbstractFormPart getFormPart();

    public void makeDirty() {
        AbstractFormPart formPart = getFormPart();
        if (formPart != null && !formPart.isDirty()) {
            formPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (isDirty()) {
            super.doSave(monitor);
        }
    }

    public OrganizationXMLProcessor getXmlProcessor() {
        return xmlProcessor;
    }

    public abstract void makeStale();

    public void commit() {
        Organization workingCopy = observeWorkingCopy().getValue();
        JobSafeStructuredDocument document = (JobSafeStructuredDocument) getDocument();
        DocumentRewriteSession session = null;
        try {
            session = document.startRewriteSession(DocumentRewriteSessionType.STRICTLY_SEQUENTIAL);
            document.set(getXmlProcessor().saveToString(workingCopy.eResource(), null));
        } catch (IOException e) {
            throw new RuntimeException("Fail to update the document", e);
        } finally {
            if (session != null) {
                document.stopRewriteSession(session);
            }
        }
    }

    public abstract void refreshList();

    public void refreshMembershipTable() {
        editor.refreshMembershipTable();
    }

    public void refreshUserList() {
        editor.refreshUserList();
    }

    public void updateDefaultUserViewerInput() {
        editor.updateDefaultUserViewerInput();
    }

    public String toUserDisplayName(User user) {
        if (user.getFirstName() != null && user.getLastName() != null) {
            return String.format("%s %s", user.getFirstName(), user.getLastName());
        }
        return user.getUserName();
    }

    public IObservableList<User> observeUsers() {
        IObservableValue<Users> groupsObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__USERS);
        return EMFObservables.observeDetailList(Realm.getDefault(), groupsObservable,
                OrganizationPackage.Literals.USERS__USER);
    }

    public IObservableList<Membership> observeMemberships() {
        IObservableValue<Memberships> memberships = EMFObservables.observeDetailValue(Realm.getDefault(),
                observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__MEMBERSHIPS);
        return EMFObservables.observeDetailList(Realm.getDefault(),
                memberships, OrganizationPackage.Literals.MEMBERSHIPS__MEMBERSHIP);
    }

    public void refreshOverviewGroupList() {
        editor.refreshOverviewGroupList();
    }

}
