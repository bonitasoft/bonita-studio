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
package org.bonitasoft.studio.la.application.ui.editor.contribution;

import org.bonitasoft.studio.la.application.handler.ExportApplicationFileAction;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.ui.editors.contribution.AbstractExportContributionItem;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationContributionItem extends AbstractExportContributionItem<ApplicationFormPage> {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.export";
    private final ExportApplicationFileAction exportApplicationFileAction = new ExportApplicationFileAction();

    public ExportApplicationContributionItem(ApplicationFormPage formPage) {
        super(ID, formPage);
    }

    @Override
    protected void exportAction(Shell shell) {
        exportApplicationFileAction.export(shell,
                formPage.getRepositoryAccessor().getRepositoryStore(ApplicationRepositoryStore.class)
                        .getChild((formPage.getEditor().getEditorInput().getName()), true));
    }
}
