/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.dialog;

import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

/**
 * @author aurelie
 */
public class GroovyHelpLinkFactory {

    public static final String GROOVY_DOC_LINK = "<a href=\"http://groovy-lang.org/single-page-documentation.html\">" + Messages.groovyDocumentationLink + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$
    protected static final String GROOVY_BROWSER_ID = "org.bonitasoft.studio.groovy.browser"; //$NON-NLS-1$

    public void createGroovyHelpLink(final Composite parent) {
        final Link docLinkText = new Link(parent, SWT.NONE);
        docLinkText.setText(GROOVY_DOC_LINK);
        docLinkText.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {

                try {
                    final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(GROOVY_BROWSER_ID);
                    browser.openURL(new URL(event.text));
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }

            }
        });

    }

}
