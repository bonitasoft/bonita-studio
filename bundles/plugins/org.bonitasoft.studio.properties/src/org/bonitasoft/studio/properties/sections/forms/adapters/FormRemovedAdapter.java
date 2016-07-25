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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.forms.adapters;

import java.util.Collection;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

public class FormRemovedAdapter extends AdapterImpl {
    
    private final DiagramEditor formEditor;
    private final Form form;

    public FormRemovedAdapter(Form form,DiagramEditor formEditor) {
        this.formEditor = formEditor;
        this.form = form;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // Listen for changes to features.
        switch (notification.getFeatureID(PageFlow.class)) {
            case ProcessPackage.RECAP_FLOW__RECAP_FORMS:
            case ProcessPackage.PAGE_FLOW__FORM:
                if (notification.getEventType() == Notification.REMOVE ) {
                    final Form formRemoved = (Form) notification.getOldValue();
                    if(form.equals(formRemoved)){
                        formEditor.getEditorSite().getPage().closeEditor(formEditor, false);
                    }
                }else if(notification.getEventType() == Notification.REMOVE_MANY ) {
                    final Collection<?> formsRemoved = (Collection<?>) notification.getOldValue();
                    if(formsRemoved.contains(form)){
                        formEditor.getEditorSite().getPage().closeEditor(formEditor, false);
                    }
                }
                break;
          
        }
    }
}