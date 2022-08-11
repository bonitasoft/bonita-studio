/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.debug.internal.ui.views.console.ProcessConsole;
import org.eclipse.debug.internal.ui.views.console.ShowWhenContentChangesAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.m2e.core.ui.internal.console.MavenShowConsoleAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.internal.console.ConsoleView;

public class MavenConsoleView extends ConsoleView {

    public static final String VIEW_ID = "org.bonitasoft.studio.rest.api.extension.mavenConsole";

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.internal.console.ConsoleView#display(org.eclipse.ui.console.IConsole)
     */
    @Override
    public void display(IConsole console) {
        if (console instanceof ProcessConsole) {
            super.display(console);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.internal.console.ConsoleView#consolesAdded(org.eclipse.ui.console.IConsole[])
     */
    @Override
    public void consolesAdded(IConsole[] consoles) {
        final List<IConsole> filteredConsoles = new ArrayList<>();
        for (final IConsole c : consoles) {
            final String name = c.getName();
            if (name != null
                    && !name.contains("Tomcat")
                    && !name.contains("UI Designer")
                    && c instanceof ProcessConsole) {
                filteredConsoles.add(c);
            }
        }
        super.consolesAdded(filteredConsoles.toArray(new IConsole[filteredConsoles.size()]));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.internal.console.ConsoleView#configureToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    @Override
    protected void configureToolBar(IToolBarManager mgr) {
        mgr.add(new Separator(IConsoleConstants.LAUNCH_GROUP));
        mgr.add(new Separator(IConsoleConstants.OUTPUT_GROUP));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.internal.console.ConsoleView#doCreatePage(org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    protected PageRec doCreatePage(IWorkbenchPart dummyPart) {
        final PageRec page = super.doCreatePage(dummyPart);
        filter(getViewSite().getActionBars().getToolBarManager());
        return page;
    }

    private void filter(IToolBarManager mgr) {
        //filter toolbar items
        for (final IContributionItem item : mgr.getItems()) {
            item.setVisible(Objects.equals(IConsoleConstants.OUTPUT_GROUP, item.getId()));
            if (item instanceof SubContributionItem) {
                final IContributionItem innerItem = ((SubContributionItem) item).getInnerItem();
                if (innerItem instanceof ActionContributionItem) {
                    final IAction action = ((ActionContributionItem) innerItem).getAction();
                    innerItem.setVisible(!(action instanceof MavenShowConsoleAction)
                            && !(action instanceof ShowWhenContentChangesAction));
                }
            }

        }
    }

}
