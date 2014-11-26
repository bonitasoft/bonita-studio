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

import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author aurelie Zara
 * @author Romain Bioteau
 */
public abstract class AbstractBonitaDescriptionSection extends AbstractModelerPropertySection {

    private Section section;
    protected Form form;
    private TabbedPropertySheetPage tabbedPropertySheetPage;


    @Override
    public void refresh(){
        super.refresh();
        if (form != null) {
            form.setText(getSectionTitle());
        }
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        tabbedPropertySheetPage=aTabbedPropertySheetPage;
        final TabbedPropertySheetWidgetFactory widgetFactory = tabbedPropertySheetPage.getWidgetFactory();

        form = widgetFactory.createForm(parent);
        form.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        form.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Composite formBodyComposite = form.getBody();
        formBodyComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        formBodyComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        form.setToolBarVerticalAlignment(SWT.CENTER);
        final TogglePropertyHelpContributionItem togglePropertyHelpContributionItem = new TogglePropertyHelpContributionItem(widgetFactory, form,
                getSectionDescription());
        form.getToolBarManager().add(togglePropertyHelpContributionItem);
        form.getToolBarManager().update(true);
        form.setText(getSectionTitle());
        form.getMenuManager().add(togglePropertyHelpContributionItem);

        createContent(formBodyComposite);
        form.update();
        form.setFocus();
    }

    protected abstract void createContent(final Composite parent);

    @Override
    public void dispose(){
        super.dispose();
        if (section!=null){
            section.dispose();
        }
    }

    protected IMessageManager getMessageManager() {
        if (form != null) {
            return form.getMessageManager();
        }
        return null;
    }


    public TabbedPropertySheetPage getTabbedPropertySheetPage() {
        return tabbedPropertySheetPage;
    }

    public String getSectionTitle() {
        final ITabDescriptor tab = tabbedPropertySheetPage.getSelectedTab();
        if (tab != null) {
            return tab.getLabel();
        }
        return "";
    }

    public abstract String getSectionDescription();


}
