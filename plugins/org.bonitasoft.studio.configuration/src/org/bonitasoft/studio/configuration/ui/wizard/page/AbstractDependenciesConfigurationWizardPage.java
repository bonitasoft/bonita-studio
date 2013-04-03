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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.dependencies.ui.MissingDependenciesDecorator;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractDependenciesConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage, ICheckStateListener, ICheckStateProvider {

    /**
     * Label provider for the ListViewer.
     */
    class TabbedPropertySheetPageLabelProvider
    extends LabelProvider {

        @Override
        public String getText(Object element) {
            if (element instanceof ITabDescriptor) {
                return ((ITabDescriptor) element).getLabel();
            }
            return null;
        }
    }


    private CheckboxTreeViewer treeViewer;
    private Configuration configuration;
    private Button removeJarButton;
    private Button addJarButton;
    private TableViewer rawViewer;

    public AbstractDependenciesConfigurationWizardPage(String pageId) {
        super(pageId);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final Composite tabComposite = new Composite(parent, SWT.NONE);
        tabComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        tabComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

        final Label descriptionLabel = new Label(tabComposite,SWT.WRAP);
        descriptionLabel.setText(getDescription());
        descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        final TabFolder tabFolder = new TabFolder(tabComposite,SWT.TOP) ;
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        tabFolder.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(treeViewer != null){
                    treeViewer.refresh() ;
                }
                if(rawViewer != null){
                    rawViewer.refresh() ;
                }
            }
        }) ;

        TabItem treeClasspathItem = new TabItem(tabFolder, SWT.NONE) ;
        treeClasspathItem.setText(Messages.hiearachical) ;
        treeClasspathItem.setControl(createTreeClasspathControl(tabFolder)) ;

        TabItem rawClasspathItem = new TabItem(tabFolder, SWT.NONE) ;
        rawClasspathItem.setText(Messages.raw) ;
        rawClasspathItem.setControl(createRawClasspathControl(tabFolder)) ;

        setControl(tabComposite) ;
    }

    protected Control createRawClasspathControl(TabFolder parent) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 10).create()) ;

        final Label rawViewDesc = new Label(mainComposite, SWT.WRAP) ;
        rawViewDesc.setText(Messages.rawViewDesc) ;
        rawViewDesc.setLayoutData(GridDataFactory.fillDefaults().create()) ;

        final Text searchBox = new Text(mainComposite, SWT.SEARCH | SWT.CANCEL | SWT.ICON_SEARCH | SWT.ICON_CANCEL) ;
        searchBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        rawViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION) ;
        rawViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        rawViewer.getTable().setHeaderVisible(true) ;
        rawViewer.getTable().setLinesVisible(true) ;
        TableLayout tableLayout = new TableLayout() ;
        tableLayout.addColumnData(new ColumnWeightData(60)) ;
        tableLayout.addColumnData(new ColumnWeightData(40)) ;
        rawViewer.getTable().setLayout(tableLayout) ;
        rawViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                String searchQuery = searchBox.getText() ;
                if(searchQuery == null || searchQuery.isEmpty()
                        || (((Fragment)element).getValue() != null && ((Fragment)element).getValue().toLowerCase().contains(searchQuery.toLowerCase()))){
                    return true ;
                }
                return false ;
            }
        }) ;
        searchBox.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                rawViewer.refresh() ;
            }
        }) ;



        TableViewerColumn jarColumn = new TableViewerColumn(rawViewer,SWT.NONE) ;
        jarColumn.getColumn().setText(Messages.jarName) ;
        jarColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Fragment)element).getValue();
            }

            @Override
            public Image getImage(Object element) {
                return Pics.getImage("jar.gif",ConfigurationPlugin.getDefault());
            }
        }) ;

        new TableColumnSorter(rawViewer).setColumn(jarColumn.getColumn()) ;

        TableViewerColumn dependencyColumn = new TableViewerColumn(rawViewer,SWT.NONE) ;
        dependencyColumn.getColumn().setText(Messages.inculdedBy) ;
        dependencyColumn.setLabelProvider(new ColumnLabelProvider(){

            private final FragmentTypeLabelProvider fragmentTypeLabelProvider = new FragmentTypeLabelProvider() ;

            @Override
            public String getText(Object element) {
                String value = ((Fragment) element).getValue() ;
                List<FragmentContainer> fragmentContainers = (List<FragmentContainer>) getViewerInput(configuration) ;
                List<Fragment> fragment =  new ArrayList<Fragment>() ;
                for(FragmentContainer fc : fragmentContainers){
                    fragment.addAll((Collection<? extends Fragment>) ModelHelper.getAllItemsOfType(fc, ConfigurationPackage.Literals.FRAGMENT)) ;
                }
                Set<FragmentContainer> containers = new HashSet<FragmentContainer>() ;
                for(Fragment f : fragment){
                    if(f.getValue().equals(value)){
                        FragmentContainer container = (FragmentContainer) ((EObject) f).eContainer() ;
                        containers.add(container) ;
                    }
                }

                StringBuilder sb = new StringBuilder() ;
                for(FragmentContainer dep: containers){
                    sb.append(fragmentTypeLabelProvider.getText(dep)) ;
                    sb.append(", ") ;
                }
                sb.delete(sb.length() - 2, sb.length()) ;
                return sb.toString() ;
            }
        }) ;

        return mainComposite ;
    }

    protected Control createTreeClasspathControl(TabFolder parent) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10).create()) ;

        final Label hierarchicalViewDesc = new Label(mainComposite, SWT.WRAP) ;
        hierarchicalViewDesc.setText(Messages.hiearachicalViewDesc) ;
        hierarchicalViewDesc.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create()) ;

        treeViewer = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION) ;
        treeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        ILabelDecorator missingDespDecorator = new MissingDependenciesDecorator() ;
        treeViewer.getTree().setLinesVisible(true) ;
        treeViewer.setLabelProvider(new DecoratingLabelProvider(new DependenciesTreeLabelProvider(),missingDespDecorator)) ;
        treeViewer.setCheckStateProvider(this) ;
        treeViewer.addCheckStateListener(this) ;
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent arg0) {
                updateButtons() ;
            }
        }) ;

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false,true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0,0).spacing(0,3).create()) ;

        addJarButton = new Button(buttonComposite, SWT.FLAT) ;
        addJarButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        addJarButton.setText(Messages.add) ;
        addJarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final SelectJarsDialog dialog = new SelectJarsDialog(Display.getDefault().getActiveShell()) ;
                if(dialog.open() == Dialog.OK){
                    Object selection = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement() ;
                    FragmentContainer fc = null ;
                    if(selection instanceof FragmentContainer){
                        fc = (FragmentContainer) selection ;
                    }else if(selection instanceof Fragment){
                        fc = (FragmentContainer) ((Fragment) selection).eContainer() ;
                    }
                    for(IRepositoryFileStore file : dialog.getSelectedJars()){
                        String jarName = file.getName() ;
                        boolean exists = false ;
                        for(Fragment f : fc.getFragments()){
                            if(f.getValue().equals(jarName)){
                                exists = true ;
                                break ;
                            }
                        }
                        if(!exists){
                            Fragment f = ConfigurationFactory.eINSTANCE.createFragment() ;
                            f.setExported(true) ;
                            f.setValue(jarName) ;
                            f.setType(FragmentTypes.JAR) ;
                            f.setKey(jarName) ;
                            fc.getFragments().add(f) ;
                        }
                    }
                    treeViewer.refresh() ;
                }
            }
        }) ;

        removeJarButton = new Button(buttonComposite, SWT.FLAT) ;
        removeJarButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        removeJarButton.setText(Messages.remove) ;
        removeJarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Fragment selection = (Fragment) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement() ;
                FragmentContainer fc =  (FragmentContainer) selection.eContainer() ;
                fc.getFragments().remove(selection) ;
                treeViewer.refresh() ;
            }
        }) ;

        final Button manageJarButton = new Button(buttonComposite, SWT.FLAT) ;
        manageJarButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        manageJarButton.setText(Messages.manageJars) ;
        manageJarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final ICommandService commandService =  (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
                Command cmd =  commandService.getCommand("org.bonitasoft.studio.dependencies.manageJars") ;
                try {
                    cmd.executeWithChecks(new ExecutionEvent()) ;
                } catch (Exception e1){
                    BonitaStudioLog.error(e1) ;
                }
                treeViewer.refresh() ;
            }
        }) ;
        return mainComposite ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#updatePage(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public void updatePage(AbstractProcess process, Configuration configuration) {
        if(process != null && configuration != null && treeViewer != null && !treeViewer.getTree().isDisposed()){
            this.configuration = configuration ;
            treeViewer.setContentProvider(new TreeDependenciesContentProvider()) ;
            treeViewer.setInput(getViewerInput(configuration)) ;
            treeViewer.expandAll() ;

            rawViewer.setContentProvider(new RawDependenciesContentProvider()) ;
            rawViewer.setInput(getViewerInput(configuration)) ;

            addJarButton.setEnabled(false) ;
            removeJarButton.setEnabled(false) ;
        }
    }


    protected abstract Object getViewerInput(Configuration configuration) ;


    @Override
    public void checkStateChanged(CheckStateChangedEvent event) {
        Object element = event.getElement() ;
        if(element instanceof Fragment){
            ((Fragment) element).setExported(event.getChecked()) ;
        }
        if(element instanceof FragmentContainer){
            updateChildrenState((FragmentContainer) element,event.getChecked()) ;
        }
        updateParentSate(element) ;

        getContainer().updateMessage() ;
    }


    private void updateChildrenState(FragmentContainer element, boolean isChecked) {
        treeViewer.setGrayChecked(element,false);
        treeViewer.setChecked(element, isChecked) ;
        applyOnChildren(element, isChecked) ;
    }

    private void updateParentSate(Object element) {
        FragmentContainer parent = null ;
        if(element instanceof FragmentContainer){
            parent = ((FragmentContainer) element).getParent() ;
        }else if(element instanceof Fragment){
            parent = (FragmentContainer) ((Fragment) element).eContainer() ;
        }

        if( parent != null ){
            boolean isChecked = isChecked(parent) ;
            if(isChecked){
                treeViewer.setGrayChecked(parent,false);
                treeViewer.setChecked(parent,isChecked);
            }else{
                treeViewer.setGrayChecked(parent, isGrayed(parent));
            }
            updateParentSate(parent) ;
        }
    }

    protected void applyOnChildren(FragmentContainer element, boolean checked) {
        Set<Object> result = new HashSet<Object>() ;
        getAllChildren(element, result) ;
        Object[] children = result.toArray() ;
        if(children.length == 0){
            treeViewer.setChecked(element, false) ;
        }else{
            for(Object child :  children){
                if(child instanceof Fragment){
                    ((Fragment) child).setExported(checked) ;
                }
                treeViewer.setChecked(child, checked) ;
            }
        }

    }

    @Override
    public boolean isChecked(Object element) {
        if(element instanceof Fragment){
            return ((Fragment) element).isExported() ;
        }else if(element instanceof FragmentContainer){
            Set<Object> result = new HashSet<Object>() ;
            getAllChildren((FragmentContainer) element, result) ;
            for(Object child : result){
                if(!isChecked(child)){
                    return false;
                }
            }

            return !result.isEmpty() ;
        }

        return false ;
    }

    @Override
    public boolean isGrayed(Object element) {
        if(element instanceof Fragment){
            return false ;
        }else if(element instanceof FragmentContainer){
            Set<Object> result = new HashSet<Object>() ;
            getAllChildren((FragmentContainer) element,result) ;
            Object[] children = result.toArray() ;
            boolean isGrayed = false ;
            boolean isSelected = false ;
            if(children.length == 0){
                return false ;
            }
            isGrayed =  isChecked(children[0]) ;
            for(Object child :  children){
                isSelected = isChecked(child) ;
                if(isSelected != isGrayed){
                    treeViewer.setGrayChecked(element, true) ;
                    return true ;
                }
            }
            return false;
        }
        return false ;
    }


    private void getAllChildren(FragmentContainer element, Set<Object> result) {
        for(Fragment f : element.getFragments()){
            result.add(f) ;
        }
        for(FragmentContainer fc : element.getChildren()){
            result.add(fc) ;
            getAllChildren(fc, result) ;
        }
    }


    protected void updateButtons(){
        Object selection = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement() ;
        if(addJarButton != null && !addJarButton.isDisposed()){
            if(selection instanceof Fragment){
                FragmentContainer fc = (FragmentContainer) ((Fragment) selection).eContainer() ;
                addJarButton.setEnabled(fc.getId().equals(FragmentTypes.OTHER)) ;
            }else if(selection instanceof FragmentContainer){
                addJarButton.setEnabled(((FragmentContainer) selection).getId().equals(FragmentTypes.OTHER)) ;
            }else{
                addJarButton.setEnabled(false) ;
            }
        }

        if(removeJarButton != null && !removeJarButton.isDisposed()){
            if(selection instanceof Fragment){
                FragmentContainer fc = (FragmentContainer) ((Fragment) selection).eContainer() ;
                removeJarButton.setEnabled(fc.getId().equals(FragmentTypes.OTHER)) ;
            }else{
                removeJarButton.setEnabled(false) ;
            }
        }

    }

    protected EStructuralFeature getContainingFeature(EObject f) {
        if(f.eContainer() != null){
            if(f.eContainer().eContainer() != null){
                return ((Fragment) f).eContainer().eContainer().eContainingFeature();
            }
        }
        return null;
    }

}
