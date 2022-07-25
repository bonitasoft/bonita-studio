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
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.Activator;
import org.bonitasoft.studio.intro.content.actions.OpenInExternalBrowserIntroAction;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LinkContentProvider implements DOMContentProvider {

    private String id;
    private String url;
    private String text;

    public LinkContentProvider(String id, String url, String text) {
        this.id = id;
        this.url = url;
        this.text = text;
    }

    @Override
    public void createContent(Document dom, Element parent) {
        Element link = dom.createElement("a");
        link.setAttribute("class", "hover:text-red-600");
        try {
            link.setAttribute("href",
                    String.format("http://org.eclipse.ui.intro/runAction?pluginId=%s&class=%s&url=%s",
                            Activator.PLUGIN_ID,
                            OpenInExternalBrowserIntroAction.class.getName(),
                            URLEncoder.encode(url, "UTF-8")));
        } catch (DOMException | UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        link.setTextContent(text);
        parent.appendChild(link);
    }

    @Override
    public boolean appliesTo(String id) {
        return Objects.equals(this.id, id);
    }

}
