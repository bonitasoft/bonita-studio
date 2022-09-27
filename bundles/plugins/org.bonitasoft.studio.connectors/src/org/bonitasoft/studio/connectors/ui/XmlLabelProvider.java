/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui;

import org.bonitasoft.studio.connectors.ui.XmlDocumentContentProvider.NodeEnd;
import org.eclipse.jface.viewers.LabelProvider;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author Baptiste Mesta
 *
 */
public class XmlLabelProvider extends LabelProvider {

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if(element instanceof Node){
            Node node = (Node) element;
            NamedNodeMap attributes = node.getAttributes();
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(node.getNodeName());
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                sb.append(' ');
                sb.append(item.getNodeName());
                sb.append("=\"");
                sb.append(item.getNodeValue());
                sb.append("\"");
            }
            sb.append(">");
            return "<"+node.getNodeName()+">";
        }else if (element instanceof NodeEnd) {
            return "</"+((NodeEnd) element).getStartNode().getNodeName()+">";
        }
        return super.getText(element);
    }


}
