/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.control;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.control.StringListViewer;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Rule;
import org.junit.Test;

public class StringListViewerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void testListViewerDisplayed() throws Exception {
        final Display display = Display.getDefault();
        final Shell shell = new Shell(display);
        shell.setSize(300, 300);
        shell.setLayout(GridLayoutFactory.fillDefaults().create());
        final Composite parent = new Composite(shell, SWT.NONE);
        parent.setLayout(GridLayoutFactory.fillDefaults().create());

        final StringListViewer slv = new StringListViewer(parent, "Header test", "viewerId", new WidgetFactory());

        final List<String> toWrap = new ArrayList<String>();
        toWrap.add("first string");
        final WritableList input = new WritableList(toWrap, String.class);
        slv.setInput(input);
        assertThat(slv.getViewer().getTable().getItem(0).getText()).contains("first string");
        assertThat(slv.getControl().getData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY)).isEqualTo("viewerId");
    }

}
