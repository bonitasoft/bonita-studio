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
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 *
 */
public class SelectGroupMappingWizardPage extends SelectOrganizationWizardPage {

    private final ActorMapping mapping;
    private final SortedSet<String> availableGroups = new TreeSet<String>() ;
    private final SortedSet<String> selectedGroups ;
    private List<Group> groups;
	private CheckboxTableViewer availableGroupViewer;
    
    // validation group selection
	private DataBindingContext context;
	
    public SelectGroupMappingWizardPage(ActorMapping mapping) {
        super();
        setTitle(Messages.selectGroupTitle) ;
        setDescription(Messages.selectGroupDescription) ;
        this.mapping = mapping ;
        selectedGroups = new TreeSet<String>(this.mapping.getGroups().getGroup()) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        super.createControl(parent) ;

        final Composite mainComposite = (Composite) getControl();
        final Composite viewersComposite = new Composite(mainComposite, SWT.NONE) ;
        viewersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT,250).create()) ;
        viewersComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(1).margins(0, 0).extendedMargins(0, 0, 10, 0).equalWidth(false).create()) ;

        availableGroupViewer = CheckboxTableViewer.newCheckList(viewersComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL) ;
        availableGroupViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        availableGroupViewer.getTable().setHeaderVisible(true) ;
        availableGroupViewer.setContentProvider(new ArrayContentProvider()) ;
        final TableLayout layout = new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(100));
        availableGroupViewer.getTable().setLayout(layout) ;

        final TableViewerColumn columnViewer = new TableViewerColumn(availableGroupViewer, SWT.NONE) ;
        final TableColumn usernameColumn = columnViewer.getColumn() ;
        usernameColumn.setText(Messages.groupName);
        columnViewer.setLabelProvider(new ColumnLabelProvider());
        final TableColumnSorter sorter = new TableColumnSorter(availableGroupViewer) ;
        sorter.setColumn(usernameColumn) ;

        availableGroupViewer.setInput(availableGroups) ;
        availableGroupViewer.setCheckedElements(selectedGroups.toArray());
        
        context = new DataBindingContext();
		
        final IObservableSet checkedElementsObservable =  ViewersObservables.observeCheckedElements(availableGroupViewer, String.class) ;
        final MultiValidator notEmptyValidator = new MultiValidator() {

        	@Override
            protected IStatus validate() {
				if(groupSelectionIsValid(checkedElementsObservable)){
					return ValidationStatus.ok();
				}
				return ValidationStatus.error(Messages.errorSelectionGroups);
			}
        }  ;

        context.addValidationStatusProvider(notEmptyValidator);
		context.bindSet(checkedElementsObservable, PojoObservables.observeSet(this, "selectedGroups"));
		
		WizardPageSupport.create(this, context);
		setControl(mainComposite);
    }
    
    /** Return false if a group and one of its child is selected. Else return true.
     * @param checkedElementsObservable 
     * 
     * @return
     */
	private boolean groupSelectionIsValid(IObservableSet checkedElementsObservable) {
        final List<Path> list1 = new ArrayList<Path>();
        final List<Path> list2 = new ArrayList<Path>();
		final Iterator<?> it = checkedElementsObservable.iterator();
		while(it.hasNext()){
            String groupName = (String) it.next();
            if (groupName.startsWith("/")) {
                groupName = groupName.substring(1);
            }
            final String[] splitted = groupName.split("/");
            Path groupPath = null;
            if (splitted.length > 1) {
                groupPath = Paths.get(splitted[0], Arrays.copyOfRange(splitted, 1, splitted.length));
            } else {
                groupPath = Paths.get(splitted[0]);
            }
            list1.add(groupPath);
            list2.add(groupPath);
		}
	
        for (final Path o1 : list1) {
            final Path s1 = o1;
            for (final Path o2 : list2) {
                final Path s2 = o2;
				if(!s1.equals(s2) && (s2.startsWith(s1) || s1.startsWith(s2)) ){
					return false;
				}
			}
		}
		return true;
	}
	
	
    protected void removedGroup(List<String> removedGroups) {
        selectedGroups.removeAll(removedGroups) ;
        for(final Group g : groups){
            if(removedGroups.contains(GroupContentProvider.getGroupPath(g))
                    && !availableGroups.contains(GroupContentProvider.getGroupPath(g))){
                availableGroups.add(GroupContentProvider.getGroupPath(g)) ;
            }
        }
        mapping.getGroups().getGroup().clear() ;
        mapping.getGroups().getGroup().addAll(selectedGroups) ;
    }

    protected void selectedGroup(List<String> selectedGroup) {
        availableGroups.removeAll(selectedGroup) ;
        selectedGroups.addAll(selectedGroup) ;
        mapping.getGroups().getGroup().clear() ;
        mapping.getGroups().getGroup().addAll(selectedGroups) ;
    }

    @Override
    protected void refreshOrganization(Organization organization) {
        if(organization != null){
            if(organization.getGroups() == null){
                organization.setGroups(OrganizationFactory.eINSTANCE.createGroups()) ;
            }

            groups = organization.getGroups().getGroup() ;

            availableGroups.clear() ;
            for(final Group g : groups){
                final String groupPath = GroupContentProvider.getGroupPath(g) ;
                if(!selectedGroups.contains(groupPath)){
                    availableGroups.add(groupPath) ;
                }
            }
            availableGroups.addAll(selectedGroups);
            if(availableGroupViewer != null){
                availableGroupViewer.setInput(availableGroups) ;
                availableGroupViewer.setCheckedElements(selectedGroups.toArray());
            }


        }
    }

    public Object[] getSelectedGroups(){
        return availableGroupViewer.getCheckedElements();
    }

}
