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

import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SectionTitleContentProvider implements DOMContentProvider {
    
    
    private String id;
    private String title;

    public SectionTitleContentProvider(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public void createContent(Document dom, Element parent) {
        Element titleDiv = dom.createElement("div");
        titleDiv.setAttribute("class", "inline font-sans font-bold text-2xl mb-2 ml-1");
        titleDiv.setTextContent(title);
        parent.appendChild(titleDiv);
        parent.setAttribute("class", "inline");
    }

    @Override
    public boolean appliesTo(String id) {
        return Objects.equals(id, this.id);
    }

}
