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
package org.bonitasoft.studio.model.edit.custom.process;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.edit.custom.EMFEditCustomPlugin;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.provider.JavaObjectDataItemProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class CustomJavaObjectDataItemProvider extends JavaObjectDataItemProvider {

	public CustomJavaObjectDataItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		Data d = (Data) object ;
		return "Data "+d.getName();
	}

	@Override
	public Object getImage(Object object) {
		Object icon = super.getImage(object);
		if(object instanceof Data){
			Image iconImage =  null;
			boolean formTransient = ((Data) object).getDatasourceId() != null && ((Data) object).getDatasourceId().equals("PAGEFLOW");
			if(object instanceof JavaObjectData){
				if(((JavaObjectData) object).getClassName() != null){
					try {
						IType t = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(((JavaObjectData) object).getClassName());
						if(t != null && t.isInterface()){
							iconImage = Pics.getImage("int_obj.gif");
							if(EMFEditCustomPlugin.getDefault().getImageRegistry().get("decoratedImageForInterfaceJavaObject") == null){
								EMFEditCustomPlugin.getDefault().getImageRegistry().put("decoratedImageForInterfaceJavaObject", new DecorationOverlayIcon(iconImage,Pics.getImageDescriptor("form_decorator.png",EMFEditCustomPlugin.getDefault()),IDecoration.BOTTOM_LEFT).createImage());
							}
							if(formTransient){
								return EMFEditCustomPlugin.getDefault().getImageRegistry().get("decoratedImageForInterfaceJavaObject") ;
							}
							return iconImage;
						}else{
							iconImage = Pics.getImage("class_obj.gif");
							if(EMFEditCustomPlugin.getDefault().getImageRegistry().get("decoratedImageForClassJavaObject") == null){
								EMFEditCustomPlugin.getDefault().getImageRegistry().put("decoratedImageForClassJavaObject", new DecorationOverlayIcon(iconImage,Pics.getImageDescriptor("form_decorator.png",EMFEditCustomPlugin.getDefault()),IDecoration.BOTTOM_LEFT).createImage());
							}
							if(formTransient){
								return EMFEditCustomPlugin.getDefault().getImageRegistry().get("decoratedImageForClassJavaObject") ;
							}
							return iconImage;
						}
					} catch (JavaModelException e) {

					}
				}
			}
		}
		return icon;
	}

}
