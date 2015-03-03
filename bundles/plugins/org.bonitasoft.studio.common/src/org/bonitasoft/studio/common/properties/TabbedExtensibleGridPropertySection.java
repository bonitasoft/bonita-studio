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
package org.bonitasoft.studio.common.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Baptiste Mesta
 * 
 */
public abstract class TabbedExtensibleGridPropertySection extends ExtensibleGridPropertySection {

	private final Map<IExtensibleGridPropertySectionContribution, String> tabMap = new HashMap<IExtensibleGridPropertySectionContribution, String>();
	private final List<CTabItem> tabItems = new ArrayList<CTabItem>();
	private final List<String> tabNames = new ArrayList<String>();
	private final Map<String, Composite> composites = new HashMap<String, Composite>();
	private final Map<String, Integer> positions = new HashMap<String, Integer>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection
	 * #addContribution(org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution)
	 */
	protected void addContribution(final IExtensibleGridPropertySectionContribution contrib, final String tabName) {
		tabMap.put(contrib, tabName);
		addContribution(contrib);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection
	 * #createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();
		addContributions();
		mainComposite = widgetFactory.createTabFolder(parent, SWT.NONE);
		mainComposite.setLayout(new FillLayout());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection
	 * #refreshUI()
	 */
	@Override
	public void refreshUI() {
		for (final Entry<IExtensibleGridPropertySectionContribution, String> entry : tabMap.entrySet()) {
			final IExtensibleGridPropertySectionContribution contrib = entry.getKey();
			contrib.dispose();
		}
		for (final Control c : mainComposite.getChildren()) {
			c.dispose();
		}
		for (final CTabItem tabItem : tabItems) {
			if (!tabItem.isDisposed()) {
				tabItem.dispose();
			}
		}
		tabItems.clear();
		tabNames.clear();

		for (final Entry<IExtensibleGridPropertySectionContribution, String> entry : tabMap.entrySet()) {
			final IExtensibleGridPropertySectionContribution contrib = entry.getKey();
			if (contrib.isRelevantFor(getEObject())) {
				if (!tabNames.contains(entry.getValue())) {
					tabNames.add(entry.getValue());
				}
			}
		}
		final String[] array = tabNames.toArray(new String[tabNames.size()]);
		tabNames.clear();
		for (final String name : array) {
			if(positions.containsKey(name)){
				tabNames.add(Math.min(positions.get(name),tabNames.size()), name);
			}else{
				tabNames.add(name);
			}
		}
		for (final String name : tabNames) {
				final CTabItem tabItem = widgetFactory.createTabItem((CTabFolder) mainComposite, SWT.NONE);
				tabItem.setText(name);
				tabItems.add(tabItem);
				final Composite composite = widgetFactory.createComposite(mainComposite);
				composite.setLayout(getLayout());
				tabItem.setControl(composite);
				composites.put(name, composite);
		}
		for (final IExtensibleGridPropertySectionContribution contrib :getContributions()){
			if (contrib.isRelevantFor(getEObject())) {
				final String tabName = tabMap.get(contrib);
				final Composite parent = composites.get(tabName);
				if (contrib.getLabel().length() != 0) {
					widgetFactory.createCLabel(parent, contrib.getLabel());
				}
				final Composite composite = widgetFactory.createComposite(parent);
				contrib.createControl(composite, widgetFactory, this);
			}

		}
		if(((CTabFolder) mainComposite).getItems().length > 0){
			((CTabFolder)mainComposite).setSelection(((CTabFolder) mainComposite).getItem(0));
		}
		/* Force to layout even if there is the same Control than previously */
		mainComposite.getParent().getParent().layout(true, true);
	}

	/**
	 * @param tabName
	 * @param i
	 */
	protected void setTabPosition(final String tabName, final int i) {
		positions.put(tabName,i);
	}

}
