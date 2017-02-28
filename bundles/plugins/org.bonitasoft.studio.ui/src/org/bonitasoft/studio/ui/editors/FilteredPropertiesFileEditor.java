/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors;

import org.eclipse.jdt.internal.ui.propertiesfileeditor.PropertiesFileEditor;
import org.eclipse.swt.widgets.Composite;

public class FilteredPropertiesFileEditor extends PropertiesFileEditor {

    public static final String ID = "org.bonitasoft.studio.rest.api.extension.PropertiesFileEditor";

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.editor.GroovyEditor#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        new MenuManagerFilter().filter(getSite());
    }

}
