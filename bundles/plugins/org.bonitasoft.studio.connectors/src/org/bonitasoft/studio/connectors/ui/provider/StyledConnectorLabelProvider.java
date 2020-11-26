/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.provider;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

/**
 * @author Romain Bioteau
 *
 */
public class StyledConnectorLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

	private final ConnectorDefRepositoryStore connectorDefStore;
	private final DefinitionResourceProvider resourceProvider;

	public StyledConnectorLabelProvider() {
		super();
		connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
		resourceProvider = DefinitionResourceProvider.getInstance(connectorDefStore, ConnectorPlugin.getDefault().getBundle()) ;
	}


	@Override
	public String getToolTipText(Object element) {
		return null;
	}

	@Override
	public void update(ViewerCell cell) {
		if (cell.getElement() instanceof Connector) {
			Connector connector = (Connector) cell.getElement();
			ConnectorDefinition def = connectorDefStore.getDefinition(connector.getDefinitionId(),connector.getDefinitionVersion()) ;
			if(def == null){
				def = connectorDefStore.getDefinition(connector.getDefinitionId(),connector.getDefinitionVersion()) ;
			}
			StyledString styledString = new StyledString();

			styledString.append(getText(connector), null);
			styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
			String connectorType = connector.getDefinitionId() +" ("+connector.getDefinitionVersion()+")";
			styledString.append(connectorType, StyledString.DECORATIONS_STYLER);
			EObject parent = connector.eContainer();
            if (!(parent instanceof Expression)) {
				if(connector.getEvent() != null && !connector.getEvent().isEmpty()){
					styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
					styledString.append(connector.getEvent(), StyledString.COUNTER_STYLER);
				}
			}
			if(def == null){
				styledString.setStyle(0, styledString.length(), new org.eclipse.jface.viewers.StyledString.Styler() {

					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.strikeout = true ;
					}
				}) ;
				styledString.append(" ");
				styledString.append(Messages.bind(Messages.connectorDefinitionNotFound,connector.getDefinitionId() + " ("+connector.getDefinitionVersion()+")")) ;
			}

			cell.setText(styledString.getString());
			cell.setImage(getImage(connector)) ;
			cell.setStyleRanges(styledString.getStyleRanges());
		}
	}



	@Override
	public Image getImage(Object element) {
		if(element instanceof Connector){
			ConnectorDefinition def = connectorDefStore.getDefinition(((Connector) element).getDefinitionId(),((Connector)element).getDefinitionVersion()) ;
			return resourceProvider.getDefinitionIcon(def) ;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return ((Connector) element).getName();
	}
}