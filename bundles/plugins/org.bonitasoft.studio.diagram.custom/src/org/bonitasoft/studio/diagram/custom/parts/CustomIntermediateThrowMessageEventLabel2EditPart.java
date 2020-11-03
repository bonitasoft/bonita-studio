/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.diagram.custom.providers.DiagramColorProvider;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventLabel2EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

public class CustomIntermediateThrowMessageEventLabel2EditPart extends IntermediateThrowMessageEventLabel2EditPart {

    public CustomIntermediateThrowMessageEventLabel2EditPart(View view) {
        super(view);
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
