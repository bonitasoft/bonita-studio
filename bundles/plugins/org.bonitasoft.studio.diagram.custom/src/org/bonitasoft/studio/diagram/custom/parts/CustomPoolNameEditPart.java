/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.diagram.tools.CustomTextDirectEditManager;
import org.bonitasoft.studio.diagram.custom.providers.DiagramColorProvider;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ProcessEditPartFactory;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

/**
 * @author Romain Bioteau
 */
public class CustomPoolNameEditPart extends PoolNameEditPart {

    private DirectEditManager manager;

    public CustomPoolNameEditPart(final View view) {
        super(view);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart#getManager()
     */
    @Override
    protected DirectEditManager getManager() {
        if (manager == null) {
            /* Create custom text director which use wrap label editor */
            setManager(new CustomTextDirectEditManager(this, CustomTextDirectEditManager
                    .getTextCellEditorClass(this),
                    ProcessEditPartFactory
                            .getTextCellEditorLocator(this)));
        }
        return manager;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart#setManager(org.eclipse.gef.tools.
     * DirectEditManager)
     */
    @Override
    protected void setManager(final DirectEditManager manager) {
        this.manager = manager;
    }

    @Override
    public void setSelected(final int value) {
        getParent().setSelected(value);
    }

    @Override
    protected void setFontColor(Color color) {
        Object preferenceStore = getDiagramPreferencesHint().getPreferenceStore();
        if (preferenceStore instanceof IPreferenceStore) {
            super.setFontColor(DiagramColorProvider.getFontColor((IPreferenceStore) preferenceStore, color));
        } else {
            super.setFontColor(color);
        }
    }

}
