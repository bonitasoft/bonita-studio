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

import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.common.jface.databinding.WizardPageSupportWithoutMessages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractOrganizationWizardPage extends WizardPage implements ISelectionChangedListener{

    private StructuredViewer viewer;
    private Group infoGroup;
    private String searchQuery;
    private Organization organization;
    protected List<Membership> membershipList;
    protected List<org.bonitasoft.studio.actors.model.organization.Group> groupList;
    protected List<User> userList;
    protected List<Role> roleList;
    protected EMFDataBindingContext context;
    protected WizardPageSupportWithoutMessages pageSupport;

    protected AbstractOrganizationWizardPage(String pageName) {
        super(pageName);
        context = new EMFDataBindingContext() ;
    }

    @Override
    public void dispose() {
        super.dispose();
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        if(context != null){
            context.dispose() ;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent,SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).equalWidth(false).create()) ;

        Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).margins(0, 0).spacing(0, 2).create()) ;

        final Button removeButton = createButtons(buttonComposite);

        viewer = createViewer(mainComposite) ;

        viewer.addSelectionChangedListener(this) ;
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                removeButton.setEnabled(!event.getSelection().isEmpty()) ;
            }
        }) ;


        configureViewer(viewer) ;


        infoGroup = new Group(mainComposite, SWT.NONE) ;
        infoGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        infoGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create()) ;

        configureInfoGroup(infoGroup) ;

        pageSupport = WizardPageSupportWithoutMessages.create(this, context) ;
        setControl(mainComposite) ;
    }

    /**
     * Returns the remove button
     * @param buttonComposite
     * @return
     */
    protected Button createButtons(Composite buttonComposite) {
        Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        addButton.setText(Messages.add) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addButtonSelected() ;
            }
        }) ;

        final Button removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        removeButton.setText(Messages.remove) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removeButtonSelected();
            }
        }) ;
        removeButton.setEnabled(false) ;
        return removeButton;
    }



    protected StructuredViewer createViewer(Composite parent) {
        Composite viewerComposite = new Composite(parent, SWT.NONE) ;
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).margins(0, 0).spacing(0, 5).create()) ;


        final Text searchBox = new Text(viewerComposite, SWT.SEARCH | SWT.ICON_SEARCH | SWT.BORDER | SWT.CANCEL) ;
        searchBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        searchBox.setMessage(Messages.search) ;

        final TableViewer tableViewer = new TableViewer(viewerComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL );
        tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(SWT.DEFAULT,270).create()) ;
        tableViewer.getTable().setLinesVisible(true);
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.setContentProvider(new ArrayContentProvider()) ;
        tableViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return viewerSelect(element,searchQuery) ;
            }
        }) ;

        searchBox.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                searchQuery = searchBox.getText() ;
                tableViewer.refresh() ;
            }

        }) ;

        return tableViewer ;
    }

    protected abstract boolean viewerSelect(Object element, String searchQuery) ;

    protected abstract void addButtonSelected() ;

    protected abstract void removeButtonSelected() ;

    protected abstract void configureInfoGroup(Group group) ;

    protected abstract void configureViewer(StructuredViewer viewer) ;

    protected StructuredViewer getViewer(){
        return viewer;
    }

    protected Group getInfoGroup(){
        return infoGroup;
    }

    protected void setControlEnabled(Control control,boolean enabled){
        if(control instanceof Composite){
            for(Control c :  ((Composite) control).getChildren()){
                c.setEnabled(enabled) ;
                if(c instanceof Composite){
                    setControlEnabled(c, enabled) ;
                }
            }
        }else if(control instanceof TabFolder){
            for(Control c :  ((TabFolder) control).getChildren()){
                c.setEnabled(enabled) ;
                if(c instanceof Composite){
                    setControlEnabled(c, enabled) ;
                }
            }
        }else{
            control.setEnabled(enabled) ;
        }
    }

    public void setOrganization(Organization organization){
        this.organization = organization ;
        if(organization != null){
            membershipList = organization.getMemberships().getMembership() ;
            groupList = organization.getGroups().getGroup() ;
            roleList = organization.getRoles().getRole() ;
            userList = organization.getUsers().getUser() ;
        }
    }

    public Organization getOrganization(){
        return organization  ;
    }

}
