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
package org.bonitasoft.studio.actors.ui.section;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
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
public class StyledFilterLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private final ActorFilterDefRepositoryStore defStore;
    private final DefinitionResourceProvider messageProvider;

    public StyledFilterLabelProvider() {
        super();
        defStore = (ActorFilterDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
        messageProvider = DefinitionResourceProvider.getInstance(defStore, ActorsPlugin.getDefault().getBundle()) ;
    }


    @Override
    public String getToolTipText(Object element) {
        return null;
    }

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof ActorFilter) {
            ActorFilter filter = (ActorFilter) cell.getElement();
            ConnectorDefinition def = defStore.getDefinition(filter.getDefinitionId(),filter.getDefinitionVersion()) ;
            StyledString styledString = new StyledString();

            styledString.append(getText(filter), null);
            styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
            String connectorType = filter.getDefinitionId() +" ("+filter.getDefinitionVersion()+")";
            styledString.append(connectorType, StyledString.DECORATIONS_STYLER);
            if(filter.getEvent() != null && !filter.getEvent().isEmpty()){
                styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
                styledString.append(filter.getEvent(), StyledString.COUNTER_STYLER);
            }
            if(def == null){
                styledString.setStyle(0, styledString.length(), new org.eclipse.jface.viewers.StyledString.Styler() {

                    @Override
                    public void applyStyles(TextStyle textStyle) {
                        textStyle.strikeout = true ;
                    }
                }) ;
                styledString.append(" ");
                styledString.append(Messages.bind(Messages.filterDefinitionNotFound,filter.getDefinitionId() + " ("+filter.getDefinitionVersion()+")")) ;
            }

            cell.setText(styledString.getString());
            cell.setImage(getImage(filter)) ;
            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }



    @Override
    public Image getImage(Object element) {
        if(element instanceof Connector){
            ConnectorDefinition def = defStore.getDefinition(((ActorFilter) element).getDefinitionId(),((ActorFilter)element).getDefinitionVersion()) ;
            return messageProvider.getDefinitionIcon(def) ;
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
        ActorFilter filter = (ActorFilter) element;
        ConnectorDefinition def = defStore.getDefinition(filter.getDefinitionId(),filter.getDefinitionVersion()) ;
        StyledString styledString = new StyledString();

        styledString.append(filter.getName(), null);
        styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
        String connectorType = messageProvider.getConnectorDefinitionLabel(def) ;
        if(connectorType==null && def != null) {
            connectorType = def.getId();
        }
        if(connectorType!=null){
        	 styledString.append(connectorType, StyledString.DECORATIONS_STYLER);
        }
       
        if(filter.getEvent() != null && !filter.getEvent().isEmpty()){
            styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
            styledString.append(filter.getEvent(), StyledString.COUNTER_STYLER);
        }
        if(def == null){
            styledString.setStyle(0, styledString.length(), new org.eclipse.jface.viewers.StyledString.Styler() {

                @Override
                public void applyStyles(TextStyle textStyle) {
                    textStyle.strikeout = true ;
                }
            }) ;
            styledString.append(" ");
            styledString.append(Messages.bind(Messages.filterDefinitionNotFound,filter.getDefinitionId() + " ("+filter.getDefinitionVersion()+")")) ;
        }
        return styledString.toString() ;
    }
}