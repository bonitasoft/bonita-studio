/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors;

import org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart;

public class FilteredXMLEditor extends XMLMultiPageEditorPart {

    public static final String ID = "org.bonitasoft.studio.rest.api.extension.XMLMultiPageEditorPart";

    /*
     * (non-Javadoc)
     * @see org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart#createPages()
     */
    @Override
    protected void createPages() {
        super.createPages();
        new MenuManagerFilter().filter(getSite());
    }

}
