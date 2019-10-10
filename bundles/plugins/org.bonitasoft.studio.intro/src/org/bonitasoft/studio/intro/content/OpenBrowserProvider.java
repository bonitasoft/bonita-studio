/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.intro.content;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OpenBrowserProvider implements IIntroXHTMLContentProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#init(org.eclipse.ui.intro.config.IIntroContentProviderSite)
     */
    @Override
    public void init(IIntroContentProviderSite site) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, java.io.PrintWriter)
     */
    @Override
    public void createContent(String id, PrintWriter out) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    public void createContent(String id, Composite parent, FormToolkit toolkit) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#dispose()
     */
    @Override
    public void dispose() {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroXHTMLContentProvider#createContent(java.lang.String, org.w3c.dom.Element)
     */
    @Override
    public void createContent(String id, Element parent) {
        String[] args = id.split(",");
        Document dom = parent.getOwnerDocument();
        Element href = dom.createElement("a");
        try {
            href.setAttribute("href", redirectUrl(args[0], ProductVersion.majorVersion()));
        } catch (DOMException | UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        href.appendChild(dom.createTextNode(args[1]));
        parent.appendChild(href);

    }

    private String redirectUrl(String id, String currentVersion) throws UnsupportedEncodingException {
        return String.format(
                "http://org.eclipse.ui.intro/runAction?pluginId=org.bonitasoft.studio.intro&class=org.bonitasoft.studio.intro.actions.OpenInExternalBrowserIntroAction&url=%s",
                URLEncoder.encode(String.format(
                        "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=%s&&bos_redirect_product=bos&bos_redirect_major_version=%s&currentVersion&bos_redirect_minor_version=0",
                        id, currentVersion), "UTF-8"));
    }

}
