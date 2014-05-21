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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.diagram.custom.resources.ResourceTreeContentProvider;
import org.bonitasoft.studio.diagram.custom.resources.ResourceTreeLabelProvider;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 *
 */
public class SelectFileStoreWizardPage extends WizardPage {

    private Object selectedElement = null;
    private final TransactionalEditingDomain editingDomain;
    private final ResourceContainer resourceContainer;
    private TreeViewer fileStoreViewer;
    private final String initialValue;

    protected SelectFileStoreWizardPage(TransactionalEditingDomain editingDomain, ResourceContainer resourceContainer, String initialValue) {
        super(SelectFileStoreWizardPage.class.getName());
        this.editingDomain = editingDomain;
        this.resourceContainer = resourceContainer;
        this.initialValue = initialValue;
    }

    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        createTree(mainComposite);
        createButtons(mainComposite);
        setControl(parent);
    }

    private void createTree(Composite mainComposite) {
        fileStoreViewer = new TreeViewer(mainComposite, SWT.BORDER | SWT.SINGLE);
        fileStoreViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final ResourceTreeContentProvider provider = new ResourceTreeContentProvider();
        fileStoreViewer.setContentProvider(provider);
        fileStoreViewer.setLabelProvider(new ResourceTreeLabelProvider());
        fileStoreViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                selectedElement = ((IStructuredSelection) event.getSelection()).getFirstElement();
                setPageComplete(true);
            }
        });
        fileStoreViewer.setInput(resourceContainer);
        Object[] initial  = provider.getElements(resourceContainer);
        Object[] currentChildren = provider.getChildren(initial[0]);
        if(initialValue != null){
            StringTokenizer tokenizer = new StringTokenizer(initialValue, "/");
            Object temp = null;
            while(tokenizer.hasMoreElements()){
                String item = tokenizer.nextToken();

                for (int i = 0; i < currentChildren.length; i++) {
                    temp = currentChildren[i];
                    if(temp instanceof File){
                        if(((File) temp).getName().equals(item)){
                            if(((File) temp).isDirectory()){
                                currentChildren = provider.getChildren(temp);
                                break;
                            } else {
                                break;
                            }
                        }
                    } else if(temp instanceof ResourceFile){
                        if(((ResourceFile) temp).getPath().endsWith("/"+item)){
                            break;
                        }
                    } else if( temp instanceof ResourceFolder){
                        if(((ResourceFolder) temp).getPath().endsWith("/"+item)){
                            currentChildren = provider.getChildren(temp);
                            break;
                        }
                    }
                }
            }
            if(temp != null){
                fileStoreViewer.setSelection(new StructuredSelection(temp));
                fileStoreViewer.reveal(temp);
            }
        }
    }

    private void createButtons(Composite mainComposite) {
        Composite buttonsComposite = new Composite(mainComposite, NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.fill = true;
        buttonsComposite.setLayout(rowLayout);

        createAddFolderButton(buttonsComposite);
        createAddFileButton(buttonsComposite);
        createRemoveButton(buttonsComposite);
    }

    private void createAddFolderButton(Composite buttonsComposite) {
        Button addFolder = new Button(buttonsComposite, SWT.FLAT);
        addFolder.setText(Messages.Folder);
        addFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // FileFolderSelectionDialog ffd;

                DirectoryDialog fd = new DirectoryDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
                String res = fd.open();
                if (res != null && !containsPath(resourceContainer, res)) {
                    Object element = ((IStructuredSelection) fileStoreViewer.getSelection()).getFirstElement();
                    Object parentFolder = null;
                    if(element instanceof ResourceFolder){
                        if(WebTemplatesUtil.isInUserTemplate(element)){
                            parentFolder = element;
                        }
                    }else if(element instanceof File){
                        if(((File)element).isDirectory() && WebTemplatesUtil.isInUserTemplate(element)){
                            parentFolder = element;
                        }
                    }
                    Object af = WebTemplatesUtil.putResourcesInProcessTemplate(res, parentFolder, editingDomain, (AbstractProcess) resourceContainer);
                    if(parentFolder == null){
                        final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                        String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                        ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                        if (artifact != null && af !=null) {
                            fileStoreViewer.add(ResourceTreeContentProvider.RESOURCES_CATEGORY, af);
                        }
                    }else{
                        fileStoreViewer.refresh();
                    }
                }
            }

        });

    }

    private void createAddFileButton(Composite buttonsComposite) {
        Button addFiles = new Button(buttonsComposite, SWT.FLAT);
        addFiles.setText(Messages.File);
        addFiles.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN | SWT.MULTI);
                String res = fd.open();
                if (res != null) {
                    Object element = ((IStructuredSelection) fileStoreViewer.getSelection()).getFirstElement();
                    Object parentFolder = null;
                    if(element instanceof ResourceFolder){
                        if(WebTemplatesUtil.isInUserTemplate(element)){
                            parentFolder = element;
                        }
                    }else if(element instanceof File){
                        if(((File)element).isDirectory() && WebTemplatesUtil.isInUserTemplate(element)){
                            parentFolder = element;
                        }
                    }
                    // ((FileTreeContentProvider)tv.getContentProvider()).setRootPath(res);
                    File temp = null;
                    String[] fileNames = fd.getFileNames();
                    for (int i = 0; i < fileNames.length; i++) {
                        temp = new File(fd.getFilterPath() + File.separatorChar + fileNames[i]);
                        if (!containsPath(resourceContainer, temp.getAbsolutePath())) {

                            // copy it in the process template directory
                            Object af = WebTemplatesUtil.putResourcesInProcessTemplate(res, parentFolder, editingDomain, (AbstractProcess) resourceContainer);
                            if(parentFolder == null){
                                final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                                String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                                ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                                if (artifact != null) {
                                    fileStoreViewer.add(ResourceTreeContentProvider.RESOURCES_CATEGORY, af);
                                }
                            }else{
                                fileStoreViewer.refresh();
                            }
                        }
                    }

                }
            }
        });

    }

    private void createRemoveButton(Composite buttonsComposite){
        final Button removeFolder = new Button(buttonsComposite, SWT.FLAT);
        removeFolder.setText(Messages.Remove);
        removeFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!MessageDialog.openConfirm(removeFolder.getShell(), Messages.confirmDeleteFile_title, Messages.confirmDeleteFile_msg)){
                    return;
                }
                Iterator<?> it = ((ITreeSelection) fileStoreViewer.getSelection()).iterator();
                Object temp;
                Object toRemove = null;
                final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                final String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                while (it.hasNext()) {
                    temp = it.next();
                    if (temp instanceof String && !containsPath(resourceContainer, (String) temp)) {
                        toRemove = temp;
                        if (WebTemplatesUtil.isInUserTemplate(toRemove)) {
                            ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                            file.removeResource((String) toRemove);
                        }
                    } else if (temp instanceof ResourceFolder) {
                        toRemove = temp;
                        if (WebTemplatesUtil.isInUserTemplate(toRemove)) {
                            ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                            file.removeResource(((ResourceFolder) toRemove).getPath());
                        }
                        editingDomain.getCommandStack().execute(
                                new RemoveCommand(editingDomain, resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, toRemove));

                    } else if (temp instanceof ResourceFile) {
                        toRemove = temp;
                        if (WebTemplatesUtil.isInUserTemplate(toRemove)) {
                            ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                            file.removeResource(((ResourceFile) toRemove).getPath());
                        }
                        editingDomain.getCommandStack().execute(
                                new RemoveCommand(editingDomain, resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES, toRemove));

                    } else if (temp instanceof IResource) {
                        ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                        file.removeResource((IResource) temp);
                        fileStoreViewer.remove(temp);
                    } else if (temp instanceof File) {
                        if(WebTemplatesUtil.isInUserTemplate(temp)){
                            FileUtil.deleteDir((File) temp);
                        }
                        fileStoreViewer.remove(temp);
                    }
                    if (toRemove != null) {
                        fileStoreViewer.refresh();
                    }

                }

            }
        });
    }

    @Override
    public String getDescription() {
        return Messages.selectApplicationResourceFileWizardDescription;
    }

    @Override
    public String getTitle() {
        return Messages.selectApplicationResourceFileWizardTitle;
    }

    @Override
    public boolean canFlipToNextPage() {
        return super.canFlipToNextPage() && selectedElement != null  && isFile(selectedElement);
    }

    private boolean isFile(Object selectedElement2) {
        return selectedElement2 instanceof File || selectedElement2 instanceof ResourceFile;
    }

    /**
     * @param resourceContainer2
     * @param res
     * @return
     */
    protected boolean containsPath(ResourceContainer resourceContainer2, String res) {
        for (ResourceFolder folder : resourceContainer2.getResourceFolders()) {
            if (folder.getPath() != null && folder.getPath().equals(res)) {
                return true;
            }
        }
        for (ResourceFile file : resourceContainer2.getResourceFiles()) {
            if (file.getPath() != null && file.getPath().equals(res)) {
                return true;
            }
        }
        return false;
    }

    public String getSelectedFilePath() {
        final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
        if(selectedElement instanceof ResourceFile){
            final String res = ((ResourceFile)selectedElement).getPath().replaceFirst(".*/", "");
            return res;
        } else {
            String filePath = ((File)selectedElement).getPath();
            String rootFolderPath = resourceStore.getResource().getLocation().toOSString().replaceAll("\\\\", "/");
            String withoutStart = filePath.replaceAll("\\\\", "/").replaceFirst(rootFolderPath, "");
            //withoutStart.replaceFirst("/", "");
            return withoutStart.replaceFirst(
                    "/.*/" //UUID
                    +"application/"
                    , "");
        }
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() && selectedElement != null  && isFile(selectedElement);
    }

}
