/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.intro.content;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.content.actions.OpenInExternalBrowserIntroAction;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LogoContentProvider implements DOMContentProvider {

    private static final String LOGO = "community-logo";

    @Override
    public void createContent(Document dom, Element parent) {
        Element a = dom.createElement("a");
        try {
            a.setAttribute("href", redirectUrl("99", ProductVersion.majorVersion()));
        } catch (DOMException | UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        Element img = dom.createElement("img");
        img.setAttribute("src", "images/logo-community.png");
        img.setAttribute("class", "mx-auto");
        a.appendChild(img);
        parent.appendChild(a);
    }

    @Override
    public boolean appliesTo(String id) {
        return LOGO.equals(id);
    }

    private String redirectUrl(String id, String currentVersion) throws UnsupportedEncodingException {
        return String.format(
                "http://org.eclipse.ui.intro/runAction?pluginId=org.bonitasoft.studio.intro&class=%s&url=%s",
                OpenInExternalBrowserIntroAction.class.getName(),
                URLEncoder.encode(String.format(
                        "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=%s&&bos_redirect_product=bos&bos_redirect_major_version=%s&currentVersion&bos_redirect_minor_version=0",
                        id, currentVersion), "UTF-8"));
    }

}
