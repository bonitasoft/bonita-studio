/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
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

import java.io.PrintWriter;

import org.bonitasoft.studio.intro.Messages;
import org.eclipse.core.commands.Command;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author Romain
 *
 */
public class OpenActionContentProvider implements IIntroXHTMLContentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroContentProvider#init(org.eclipse.ui.intro.config.IIntroContentProviderSite)
	 */
	public void init(IIntroContentProviderSite site) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, java.io.PrintWriter)
	 */
	public void createContent(String id, PrintWriter out) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroContentProvider#createContent(java.lang.String, org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	public void createContent(String id, Composite parent, FormToolkit toolkit) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroContentProvider#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroXHTMLContentProvider#createContent(java.lang.String, org.w3c.dom.Element)
	 */
	public void createContent(String id, Element parent) {
		if(PlatformUI.isWorkbenchRunning()){
			final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if(activeWorkbenchWindow != null){
				final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
				if(activePage != null ){
					if(PlatformUI.getWorkbench().getService(ICommandService.class) != null){
						// Do not replace by static link since this command does not resolve to the same between BOS and SP
						Command open = ((ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class)).getCommand("org.bonitasoft.studio.diagram.command.openDiagram");//$NON-NLS-1$
						Document dom = parent.getOwnerDocument();
						Element ul = (Element) dom.getElementsByTagName("ul").item(0);
						NodeList liList = ul.getElementsByTagName("li");
						if(liList.getLength() == 2){
							Element li = dom.createElement("li");
							ul.insertBefore(li, liList.item(1));
							Element link = dom.createElement("a");
							li.appendChild(link);
							if(open.isEnabled()){
								link.setAttribute("href", "http://org.eclipse.ui.intro/runAction?pluginId=org.bonitasoft.studio.intro&class=org.bonitasoft.studio.intro.actions.OpenProcess");
							}		
							Text t = dom.createTextNode(Messages.getMessage("openAProcess"));
							link.appendChild(t);
							if(open.isEnabled()){
								li.setAttribute("id", id);
							} else {
								li.setAttribute("id", id+"-disabled");
							}
						}
					}
				}
			}
		}
	}

}
