/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.intro.content;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author Mickael Istria
 * 
 */
public class RssContentProvider implements IIntroXHTMLContentProvider {

    private int nbNews;

    protected String xmlName;

    private static DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#init(org.eclipse.ui.intro.config.IIntroContentProviderSite)
     */
    public void init(IIntroContentProviderSite site) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, java.io.PrintWriter)
     */
    public void createContent(String id, PrintWriter out) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.forms.widgets.FormToolkit)
     */
    public void createContent(String id, Composite parent, FormToolkit toolkit) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroContentProvider#dispose()
     */
    public void dispose() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.intro.config.IIntroXHTMLContentProvider#createContent(java.lang.String, org.w3c.dom.Element)
     */
    public void createContent(String id, Element parent) {
        try {
            read(id);
            URL fileUrl = getRSS();
            if (fileUrl != null) {
                Document dom = parent.getOwnerDocument();

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = null;
                try {
                    feed = input.build(new XmlReader(fileUrl));
                } catch (Exception e) {
                    BonitaStudioLog.error("RSS parsing exception for " + fileUrl, "org.bonitasoft.studio.intro");
                    BonitaStudioLog.error(e, "org.bonitasoft.studio.intro");
                }
                Node ul = parent.appendChild(dom.createElement("ul"));
                int i = 0;
                for (Object entry : feed.getEntries()) {
                    SyndEntry syndEntry = (SyndEntry) entry;
                    Element li = createLiRssEntryNode(dom, syndEntry);
                    ul.appendChild(li);
                    i++;
                    if (i >= nbNews) {
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            BonitaStudioLog.error(ex, "org.bonitasoft.studio.intro");
        }

    }

    /**
     * @param id
     */
    private void read(String id) {
        String[] segments = id.split(",");
        nbNews = Integer.parseInt(segments[0].trim());
        xmlName = segments[1].trim();
    }

    private Element createLiRssEntryNode(Document dom, SyndEntry syndEntry) {
        Element li = dom.createElement("li");
        Element a = dom.createElement("a");
        a.setAttribute("href", syndEntry.getLink());
        a.setAttribute("class", "rssLink");
        li.appendChild(a);
        {
        	final Date publishedDate = syndEntry.getPublishedDate();
        	if(publishedDate != null){
        		Element dateSpan = dom.createElement("span");
        		dateSpan.setAttribute("class", "rssPubDate");
        		dateSpan.appendChild(dom.createTextNode(dateInstance.format(publishedDate) + " - "));
        		a.appendChild(dateSpan);
        	}
        }
        {
            Element titleSpan = dom.createElement("span");
            titleSpan.setAttribute("class", "rssTitle");
            titleSpan.appendChild(dom.createTextNode(syndEntry.getTitle()));
            a.appendChild(titleSpan);
        }
        {
            Element descriptionSpan = dom.createElement("span");
            descriptionSpan.setAttribute("class", "rssDesc");
            descriptionSpan.appendChild(dom.createElement("br"));
            descriptionSpan.appendChild(dom.createTextNode(getDesc(syndEntry)));
            li.appendChild(descriptionSpan);
        }
        return li;
    }

    /**
     * @param syndEntry
     * @return
     */
    private String getDesc(SyndEntry syndEntry) {
        String res = syndEntry.getDescription().getValue();
        res = res.replace("<br/>", " ");
        res = res.replace("<br>", " ");
        res = res.replace("<BR>", " ");
        res = res.replace("<BR/>", " ");
        return res;
    }

    protected URL getRSS() throws Exception {
        File xmlFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), xmlName + ".xml");
        return xmlFile.toURI().toURL();
    }
}
