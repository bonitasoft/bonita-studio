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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author Baptiste Mesta
 * 
 */
public class XmlDocumentContentProvider implements ITreeContentProvider {

	
	
	/**
	 * @author Baptiste Mesta
	 *
	 */
	public class NodeEnd {
		private Node node;
		public NodeEnd(Node node) {
			this.node = node;
		}
		public Node getStartNode() {
			return node;
		}
	}

	public XmlDocumentContentProvider() {
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		if(!(inputElement instanceof Document)){
			throw new IllegalArgumentException();
		}
		Document document = (Document) inputElement;
		return getChildren(document);
	}

	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Node && ((Node) parentElement).getFirstChild() != null){
			Node nextNode = ((Node) parentElement).getFirstChild();
			if(nextNode instanceof Text){
				Text text = (Text) nextNode;
				return text.getTextContent().split("\n");
			}else{
				List<Object> nodeList = new ArrayList<Object>();
				do{
					nodeList.add(nextNode);
					nodeList.add(new NodeEnd(nextNode));
				}while((nextNode = nextNode.getNextSibling()) != null);
				return nodeList.toArray();
			}
		}
		return null;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return (element instanceof Node && ((Node)(element)).hasChildNodes());
	}

}
