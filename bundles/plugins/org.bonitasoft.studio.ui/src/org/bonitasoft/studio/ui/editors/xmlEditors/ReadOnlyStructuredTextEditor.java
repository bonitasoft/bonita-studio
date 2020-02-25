/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors.xmlEditors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;

public class ReadOnlyStructuredTextEditor extends NoPropertySheetStructuredTextEditor {

    @Override
    protected StructuredTextViewer createStructedTextViewer(Composite parent, IVerticalRuler verticalRuler, int styles) {
        return new ReadOnlyStructedTextViewer(parent, verticalRuler, getOverviewRuler(), isOverviewRulerVisible(), styles);
    }

    @Override
    protected void doSetInput(IEditorInput input) throws CoreException {
        super.doSetInput(input);
        configureInsertMode(SMART_INSERT, false);
    }

    @Override
    public void editorContextMenuAboutToShow(IMenuManager menu) {
    }

}

class ReadOnlyStructedTextViewer extends StructuredTextViewer {

    public ReadOnlyStructedTextViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(false);
    }

    @Override
    public boolean isEditable() {
        return false;
    }

}
