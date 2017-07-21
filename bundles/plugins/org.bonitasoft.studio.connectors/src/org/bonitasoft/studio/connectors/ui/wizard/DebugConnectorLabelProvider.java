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
package org.bonitasoft.studio.connectors.ui.wizard;

import org.bonitasoft.studio.connectors.ui.provider.StyledConnectorLabelProvider;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DebugConnectorLabelProvider extends StyledConnectorLabelProvider {


    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider labelProvider;

    public DebugConnectorLabelProvider() {
        super();
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        labelProvider = new AdapterFactoryLabelProvider(adapterFactory) ;
    }

    @Override
    public String getText(Object object) {
        if(object instanceof Connector){
            return super.getText(object) ;
        }
        return labelProvider.getText(object) ;
    }


    @Override
    public Image getImage(Object object) {
        if(object instanceof Connector){
            return super.getImage(object) ;
        }
        return labelProvider.getImage(object) ;
    }


    @Override
    public void update(ViewerCell cell) {
        Object object = cell.getElement()  ;
        if(object instanceof Connector){
            super.update(cell) ;
        }else{
            cell.setText(getText(object));
            cell.setImage(getImage(object)) ;
        }
    }
}
