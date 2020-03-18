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
package org.bonitasoft.studio.common.jface;

import java.util.Set;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.preference.JFacePreferences;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

/**
 * @author Romain Bioteau
 *
 */
public class DataStyledTreeLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

	private final IMapChangeListener mapChangeListener = new IMapChangeListener() {
		@Override
		public void handleMapChange(MapChangeEvent event) {
			Set<?> affectedElements = event.diff.getChangedKeys();
			if (!affectedElements.isEmpty()) {
				LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(DataStyledTreeLabelProvider.this, affectedElements.toArray());
				fireLabelProviderChanged(newEvent);
			}
		}
	};
	private Styler italicGrey;
	private AdapterFactoryLabelProvider imageProvider;


	public DataStyledTreeLabelProvider() {
		super();
		initStyle();
	}



	public DataStyledTreeLabelProvider(int style) {
		super(style);
		initStyle();
	}

	public DataStyledTreeLabelProvider(IObservableMap... attributeMaps) {
		for (int i = 0; i < attributeMaps.length; i++) {
			attributeMaps[i].addMapChangeListener(mapChangeListener);
		}
		initStyle();
	}

	private void initStyle() {
		imageProvider = new AdapterFactoryLabelProvider(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)) ;
		italicGrey = new StyledString.Styler() {
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.font = BonitaStudioFontRegistry.getTransientDataFont();
				textStyle.foreground = JFaceResources.getColorRegistry().get(JFacePreferences.QUALIFIER_COLOR);
			}
		};
	}
	
	@Override
	public String getToolTipText(Object element) {
		return null;
	}

	@Override
	public void update(ViewerCell cell) {
		if (cell.getElement() instanceof Data) {
			Data d = (Data) cell.getElement();
			StyledString styledString = new StyledString();

			String decoration = " -- " + getTypeLabel(d);
			if (d.isTransient()) {
				styledString.append(d.getName(), italicGrey);
			} else {
				styledString.append(d.getName(), null);
			}
			styledString.append(decoration, StyledString.DECORATIONS_STYLER);
			if(d.getDefaultValue() != null
					&& d.getDefaultValue().getName() != null
					&& !d.getDefaultValue().getName().isEmpty()){
				String content = d.getDefaultValue().getName();
				content = Messages.defaultValue+": "  + content.replaceAll("\n", " ")  ;
				if(content.length() > 150) {
				    content = content.substring(0, 150) + "...";
				}
				styledString.append(" -- ",StyledString.DECORATIONS_STYLER) ;
				styledString.append(content, StyledString.QUALIFIER_STYLER);
			}
			cell.setText(styledString.getString());
			cell.setImage(getImage(d)) ;
			cell.setStyleRanges(styledString.getStyleRanges());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		return imageProvider.getImage(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		// only for filtered Tree
		return ((Data) element).getName();
	}

	private String getTypeLabel(Data element) {
		StringBuilder builder = new StringBuilder();
		if (element.isMultiple()) {
			builder.append("List<"); //$NON-NLS-1$
		}
		if (element instanceof JavaObjectData) {
			builder.append(((JavaObjectData) element).getClassName());
		}else if (element.getDataType() == null || element.getDataType().getName() == null) {
			builder.append("?");
		} else {
			builder.append(ModelHelper.getDataTypeNLLabel(element.getDataType().getName()));
		}
		if (element.isMultiple()) {
			builder.append(">"); //$NON-NLS-1$
		}
		return builder.toString();
	}
}