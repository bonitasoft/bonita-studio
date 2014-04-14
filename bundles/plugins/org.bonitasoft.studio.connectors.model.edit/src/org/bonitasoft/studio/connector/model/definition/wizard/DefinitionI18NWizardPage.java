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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.Locale;
import java.util.Set;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class DefinitionI18NWizardPage extends WizardPage {

    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private Set<Locale> selectedLocales;
    private ViewerFilter searchFilter;
    public DefinitionI18NWizardPage(ConnectorDefinition definition,ConnectorDefinition orginalDef,Set<Locale> selectedLocales) {
        super(DefinitionI18NWizardPage.class.getName());
        setTitle(Messages.connectorI18NTitle) ;
        setDescription(Messages.connectorI18NDesc) ;
        this.selectedLocales = selectedLocales ;

    }

    @Override
    public void dispose() {
        super.dispose();
        if(context != null){
            context.dispose() ;
        }
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
    }



    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext() ;

        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create()) ;

        final Text searchText = new Text(mainComposite,SWT.SEARCH | SWT.ICON_SEARCH | SWT.BORDER | SWT.CANCEL) ;
        searchText.setMessage(Messages.search) ;
        searchText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;


        final CheckboxTableViewer languageViewer = CheckboxTableViewer.newCheckList(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL) ;
        languageViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 190).create()) ;
        languageViewer.getTable().setLinesVisible(true) ;
        languageViewer.getTable().setHeaderVisible(true) ;
        final TableLayout layout = new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(65)) ;
        layout.addColumnData(new ColumnWeightData(35)) ;
        languageViewer.getTable().setLayout(layout) ;
        languageViewer.setContentProvider(new ArrayContentProvider()) ;
        languageViewer.setLabelProvider(new LocaleLabelProvider()) ;


        searchFilter = new ViewerFilter() {

            @Override
            public boolean select(Viewer arg0, Object arg1, Object element) {
                if(!searchText.getText().isEmpty()){
                    String searchQuery = searchText.getText().toLowerCase() ;
                    Locale locale = (Locale) element ;
                    return locale.toString().toLowerCase().contains(searchQuery)
                            || locale.getDisplayName().toLowerCase().contains(searchQuery) ;
                }
                return true;
            }
        };
        languageViewer.addFilter(searchFilter) ;
        languageViewer.setInput(Locale.getAvailableLocales()) ;
        languageViewer.getTable().setFocus() ;

        TableViewerColumn countryNameColumn = new TableViewerColumn(languageViewer, SWT.FILL);
        countryNameColumn.setLabelProvider(new LocaleLabelProvider()) ;
        countryNameColumn.getColumn().setText(Messages.countryName) ;


        TableViewerColumn localeCodeNameColumn = new TableViewerColumn(languageViewer, SWT.FILL);
        localeCodeNameColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Locale)element).toString();
            }
        }) ;

        localeCodeNameColumn.getColumn().setText(Messages.locale) ;

        new TableColumnSorter(languageViewer).setColumn(countryNameColumn.getColumn()) ;

        context.bindSet(ViewersObservables.observeCheckedElements(languageViewer,Locale.class),PojoObservables.observeSet(this, "selectedLocales")) ;

        searchText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                languageViewer.refresh() ;
            }
        }) ;

        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(mainComposite) ;
    }


    public Set<Locale> getSelectedLocales() {
        return selectedLocales;
    }


    public void setSelectedLocales(Set<Locale> selectedLocales) {
        this.selectedLocales = selectedLocales;
    }
}
