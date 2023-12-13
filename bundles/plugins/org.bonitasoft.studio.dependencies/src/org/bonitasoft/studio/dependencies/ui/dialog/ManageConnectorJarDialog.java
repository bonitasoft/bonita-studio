/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.dependencies.ui.dialog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Florine Boudin
 *
 */
public class ManageConnectorJarDialog extends Dialog {

	protected TreeViewer treeViewer;
	private final DependencyRepositoryStore dependencyStore;

	protected DataBindingContext context;
	private ViewerFilter searchFilter;
	protected CheckboxTableViewer viewer;

	protected Set<DependencyFileStore> selectedJars = new HashSet<>();
	private ViewerFilter filter;
	private String title;
	private String infoLabel;
    private List<DependencyFileStore> dependencies;


	public ManageConnectorJarDialog(Shell parentShell) {
		super(parentShell);
		dependencyStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
		dependencies = dependencyStore.findJarDependencies();
	}

	public ManageConnectorJarDialog(Shell parentShell, String label, String infoLabel) {
		this(parentShell);
		this.title = label;
		this.infoLabel = infoLabel;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if(title == null){
			newShell.setText(Messages.selectMissingJarTitle) ;
		}else{
			newShell.setText(title) ;
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		context = new DataBindingContext() ;
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create());
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(400, 400).create());
		if(infoLabel != null){
			addLabel(composite);
		}
		
		createTree(composite);

		var selectionStartegy = new UpdateValueStrategy() ;
		selectionStartegy.setAfterGetValidator(value -> {
        	if( value == null){
        		return ValidationStatus.error("Selection is empty") ;
        	}
        	return Status.OK_STATUS;
        }) ;

		context.bindSet(ViewerProperties.checkedElements(IRepositoryFileStore.class).observe((Viewer) viewer), PojoProperties.set(ManageConnectorJarDialog.class,"selectedJars").observe(this)) ;


		return composite;
	}

	protected void createTree(Composite composite) {
		final Text searchText = new Text(composite,SWT.SEARCH | SWT.ICON_SEARCH | SWT.BORDER | SWT.CANCEL) ;
		searchText.setMessage(Messages.search) ;
		searchText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

		searchFilter = new ViewerFilter() {

			@Override
			public boolean select(Viewer arg0, Object arg1, Object element) {
				if(!searchText.getText().isEmpty()){
					String searchQuery = searchText.getText().toLowerCase() ;
					IRepositoryFileStore file = (IRepositoryFileStore) element ;
					return file.getName().toLowerCase().contains(searchQuery) ;
				}
				return true;
			}
		};

		viewer = CheckboxTableViewer.newCheckList(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL) ;
		viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 190).create()) ;
		viewer.getTable().setLinesVisible(true) ;
		viewer.getTable().setHeaderVisible(false) ;
		final TableLayout layout = new TableLayout() ;
		layout.addColumnData(new ColumnWeightData(65)) ;
		viewer.getTable().setLayout(layout) ;
		viewer.setContentProvider(new ArrayContentProvider()) ;
		viewer.setLabelProvider(new FileStoreLabelProvider()) ;
		viewer.addFilter(searchFilter) ;
		if(filter != null){
			viewer.addFilter(filter);
		}
		
		viewer.setInput(dependencies);
		viewer.getTable().setFocus() ;

		searchText.addModifyListener(e -> viewer.refresh()) ;
	}
	
    public boolean hasDependencies() {
        return !dependencies.isEmpty();
    }

	protected void addLabel(Composite composite){
		final Label label = new Label(composite, SWT.WRAP);
		if(infoLabel == null){
			label.setText(Messages.popUpMessage);
		}else{
			label.setText(infoLabel);
		}
		
	}


	@Override
	protected void okPressed() {
		super.okPressed();
	}

	/**
	 * @return the selectedJars
	 */
	public Set<DependencyFileStore> getSelectedJars() {
		return selectedJars;
	}

	/**
	 * @param selectedJars the selectedJars to set
	 */
	public void setSelectedJars(Set<DependencyFileStore> selectedJars) {
		this.selectedJars = selectedJars;
	}

	public void setFilter(ViewerFilter viewerFilter) {
		this.filter = viewerFilter;
	}



}
