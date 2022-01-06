/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class DirtyStateAdapter extends EContentAdapter {

    private List<AbstractBdmFormPage> formPage;
    private boolean ignore;

    public DirtyStateAdapter(AbstractBdmFormPage... formPage) {
        this.formPage = Arrays.asList(formPage);
    }

    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        if (!ignore) {
            formPage.forEach(AbstractBdmFormPage::makeDirty);
            formPage.forEach(AbstractBdmFormPage::refreshBusinessObjectList);
            formPage.stream().findFirst() // Access ctrl available content is based on this working cpy -> when the working copy is updated access ctrl model must be reloaded. 
                    .map(AbstractBdmFormPage::getEditorContribution)
                    .ifPresent(BusinessDataModelEditorContribution::makeAccessControlStale);
        }
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

}
