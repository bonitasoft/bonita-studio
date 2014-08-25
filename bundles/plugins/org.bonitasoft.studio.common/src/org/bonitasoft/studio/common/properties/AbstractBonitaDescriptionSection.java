/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author aurelie Zara
 *
 */
public abstract class AbstractBonitaDescriptionSection extends
AbstractModelerPropertySection {

    private Section section;
    protected Composite composite;
    private TabbedPropertySheetPage tabbedPropertySheetPage;
    private TabbedPropertySheetWidgetFactory widgetFactory;


    @Override
    public void refresh(){
        super.refresh();

        final String description = getSectionDescription();
        if(tabbedPropertySheetPage != null){
            final ITabDescriptor tab = tabbedPropertySheetPage.getSelectedTab();
            if(tab != null && section != null){
                section.setText(tab.getLabel() + " "+Messages.descriptionTitle);
            }
        }
        if (description !=null  && section != null){
            section.setDescription(description);
        }
    }



    @Override
    public void createControls(final Composite parent,final TabbedPropertySheetPage aTabbedPropertySheetPage){
        super.createControls(parent, aTabbedPropertySheetPage);
        parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        tabbedPropertySheetPage=aTabbedPropertySheetPage;
        composite = aTabbedPropertySheetPage.getWidgetFactory().createPlainComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5,5).extendedMargins(0, 20, 0, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory = tabbedPropertySheetPage.getWidgetFactory();
        section =widgetFactory.createSection(composite, Section.DESCRIPTION | Section.TITLE_BAR |Section.TWISTIE | Section.NO_TITLE_FOCUS_BOX);
        section.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).create());
        final Composite client = widgetFactory.createComposite(section, SWT.NONE);
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0,0).create());
        client.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).create());
        final Label c = new Label(client, SWT.SEPARATOR | SWT.HORIZONTAL);
        c.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0,0).create());
        c.setVisible(false);
        c.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
        section.setClient(client);

        final String description = getSectionDescription();
        final ITabDescriptor tab = tabbedPropertySheetPage.getSelectedTab();
        if(tab != null){
            section.setText(tab.getLabel() + " "+Messages.descriptionTitle);
        }
        if (description !=null){
            section.setDescription(description);
        }
    }

    @Override
    public void dispose(){
        super.dispose();
        if (section!=null){
            section.dispose();
        }
    }



    public abstract String getSectionDescription();


}
