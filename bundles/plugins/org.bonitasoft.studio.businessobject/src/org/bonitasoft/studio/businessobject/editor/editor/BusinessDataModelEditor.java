/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractMultiSourceFormEditor;
import org.eclipse.swt.graphics.Image;

public class BusinessDataModelEditor extends AbstractMultiSourceFormEditor {

    public static final String EDITOR_ID = "org.bonitasoft.studio.bdm.editor";

    @Override
    public String getEditorId() {
        return EDITOR_ID;
    }

    @Override
    public Image getTitleImage() {
        return Pics.getImage(PicsConstants.bdm);
    }

}
