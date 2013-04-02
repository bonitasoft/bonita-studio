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
package org.bonitasoft.studio.properties.sections.resources;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.diagram.custom.resources.ResourceTreeContentProvider;
import org.bonitasoft.studio.diagram.custom.resources.ResourceTreeLabelProvider;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Aurelien Pupier
 */
public class ResourcePropertySection extends AbstractBonitaDescriptionSection implements SWTBotConstants{



    private TreeViewer tv;
    private final SelectionListener radioListener = new SelectionListener() {

        @Override
        public void widgetSelected(SelectionEvent e) {
            if (e.getSource().equals(internal)) {
                changeWelcome.setEnabled(true);
            } else {
                changeWelcome.setEnabled(false);
            }
            getEditingDomain().getCommandStack().execute(
                    new SetCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL, internal
                            .getSelection()));
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
        }
    };
    private Text loginPath;
    private Button changeLogin;
    private Button editLogin;
    private Button internal;
    private Text welcomePath;
    private Button changeWelcome;
    private Button editWelcome;
    private ResourceContainer resourceContainer;
    private final Listener browseButtonListener = new Listener() {

        @Override
        public void handleEvent(Event event) {
            Text textField = null;
            if(event.widget.equals(changeWelcome)) {
                textField = welcomePath;
            } else {
                textField = loginPath;
            }
            FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
            fd.setFilterExtensions(event.widget.equals(changeLogin) ? new String[] { "*.jsp" } : new String[] { "*.html", "*.htm", "*.*" });
            if (textField.getText() != null) {
                File temp = new File(textField.getText());
                if (temp.exists()) {
                    fd.setFilterPath(temp.getAbsolutePath());
                }
            }

            String res = fd.open();
            if (res != null) {
                final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                if (artifact == null) {
                    artifact = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(processUUID) ;
                }
                if (event.widget.equals(changeWelcome)) {
                    res = artifact.setWelcomePage(res);
                    textField.setText(res);

                    AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
                    af.setPath(res);
                    getEditingDomain().getCommandStack().execute(
                            new SetCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE, af));
                } else {
                    res = artifact.setLoginPage(res);
                    textField.setText(res);

                    AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
                    af.setPath(res);
                    getEditingDomain().getCommandStack().execute(
                            new SetCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE, af));
                }
            }
        }
    };

    private final ModifyListener modifyListener = new ModifyListener() {

        @Override
        public void modifyText(ModifyEvent e) {
            if (((AbstractProcess) resourceContainer).getWelcomePage() == null) {

                AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
                af.setPath(welcomePath.getText() != null ? welcomePath.getText() : "");
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE, af));

            } else {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), ((AbstractProcess) resourceContainer).getWelcomePage(), ProcessPackage.Literals.ASSOCIATED_FILE__PATH,
                                welcomePath.getText() != null ? welcomePath.getText() : ""));

            }

        }
    };
    private final SelectionAdapter editButtonListener = new SelectionAdapter() {
        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
         * .swt.events.SelectionEvent)
         */
        @Override
        public void widgetSelected(SelectionEvent e) {
            String path;
            Text text = null;
            if (e.widget.equals(editWelcome)) {
                text = welcomePath;
            } else if (e.widget.equals(editLogin)) {
                text = loginPath;
            }
            if (text != null) {
                path = text.getText();
                File file = WebTemplatesUtil.getFile(path);
                if (file != null && file.exists() && !file.isDirectory()) {
                    URI uri = file.toURI();
                    try {
                        URL url = uri.toURL();
                        url.toString();
                        url.toURI();
                        try {//workaround for 2353
                            if(file.getName().endsWith(".htm")||file.getName().endsWith(".html") ){
                                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), url.toURI(), IDE.getEditorDescriptor("test.txt")////$NON-NLS-1$
                                        .getId(), true);
                            }else{
                                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), url.toURI(), IDE.getEditorDescriptor(file.getName())
                                        .getId(), true);
                            }
                        } catch (PartInitException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    } catch (MalformedURLException e2) {
                        BonitaStudioLog.error(e2);
                    } catch (URISyntaxException e4) {
                        BonitaStudioLog.error(e4);
                    }
                }
            }
        }
    };
    private ResourceTreeContentProvider resourceTreeContentProvider;
    private Button removeFolder;
    private Button editFile;

    @Override
    public void createControls(Composite parent,
            TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite mainComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        mainComposite.setLayout(layout);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
        createTree(mainComposite);
        createButtons(mainComposite);
        updateButtons() ;
        Composite templates = createRightPanel(mainComposite);
        //createWelcomePage(templates);
        createLoginPage(templates);
    }

    private void updateButtons() {
        if(tv != null && tv.getSelection() != null && !tv.getSelection().isEmpty()){
            editFile.setEnabled(true) ;
            removeFolder.setEnabled(true) ;
        }else{
            editFile.setEnabled(false) ;
            removeFolder.setEnabled(false) ;
        }

    }


    private Composite createRightPanel(Composite parent) {
        Composite templates = getWidgetFactory().createComposite(parent);
        templates.setLayout(new GridLayout(5, false));
        GridData gridD = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 3);
        templates.setLayoutData(gridD);
        gridD.widthHint = 200;
        return templates;

    }

    private void createTree(Composite parent) {

        // Create the tree viewer to display the file tree
        tv = new TreeViewer(parent);
        tv.getTree().setData(SWTBOT_WIDGET_ID_KEY, APPLICATION_RESOURCES_TREE_ID);
        GridData treeGridData = new GridData(GridData.FILL, GridData.FILL, false, false, 1, 5);
        treeGridData.heightHint = 127;
        treeGridData.widthHint = 227;

        tv.getTree().setLayoutData(treeGridData);
        resourceTreeContentProvider = new ResourceTreeContentProvider();
        tv.setAutoExpandLevel(2);
        tv.setContentProvider(resourceTreeContentProvider);
        final ResourceTreeLabelProvider fileTreeLabelProvider = new ResourceTreeLabelProvider();
        tv.setLabelProvider(fileTreeLabelProvider);

        tv.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                updateButtons();
                if (!event.getSelection().isEmpty() && event.getSelection() instanceof IStructuredSelection) {
                    boolean disable = false;
                    boolean enableEdit = false;
                    for (Object el : ((IStructuredSelection) event.getSelection()).toArray()) {
                        if (!(el instanceof ResourceFolder || el instanceof IResource)) {
                            if(el instanceof File || el instanceof ResourceFile){
                                if(!WebTemplatesUtil.isInUserTemplate(el)){
                                    disable = true;
                                }else{

                                    enableEdit = !(el instanceof File) || !((File) el).isDirectory() ;
                                }
                            }else{
                                disable = true;
                            }
                        }
                        if(disable){
                            break;
                        }
                    }
                
                    editFile.setEnabled(enableEdit) ;
                }
            }
        });
        tv.getTree().setFocus();
    }

    private void createButtons(Composite parent) {
        Composite buttonsComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
        buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
        buttonsComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 2));

        // add a resource folder in the tree
        Button addFolder = getWidgetFactory().createButton(buttonsComposite, Messages.Folder, SWT.FLAT);
        addFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                // FileFolderSelectionDialog ffd;

                DirectoryDialog fd = new DirectoryDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
                String res = fd.open();
                if (res != null && !containsPath(resourceContainer, res)) {
                    Object element = ((IStructuredSelection) tv.getSelection()).getFirstElement();
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
                    Object af = WebTemplatesUtil.putResourcesInProcessTemplate(res, parentFolder, getEditingDomain(), (AbstractProcess) resourceContainer);
                    if(parentFolder == null){
                        final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                        String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                        ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                        if (artifact != null && af !=null) {
                            tv.add(ResourceTreeContentProvider.RESOURCES_CATEGORY, af);
                        }
                    }else{
                        tv.refresh();
                    }
                }
            }

        });

        // add files at the root
        Button addFiles = getWidgetFactory().createButton(buttonsComposite, Messages.File, SWT.FLAT);
        addFiles.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN | SWT.MULTI);
                String res = fd.open();
                if (res != null) {
                    Object element = ((IStructuredSelection) tv.getSelection()).getFirstElement();
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
                            Object af = WebTemplatesUtil.putResourcesInProcessTemplate(res, parentFolder, getEditingDomain(), (AbstractProcess) resourceContainer);
                            if(parentFolder == null){
                                final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                                String processUUID = ModelHelper.getEObjectID(resourceContainer) ;
                                ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                                if (artifact != null) {
                                    tv.add(ResourceTreeContentProvider.RESOURCES_CATEGORY, af);
                                }
                            }else{
                                tv.refresh();
                            }
                        }
                    }

                }
            }
        });
        // EditFile
        editFile = getWidgetFactory().createButton(buttonsComposite, Messages.Edit, SWT.FLAT);
        editFile.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Iterator<?> it = ((ITreeSelection) tv.getSelection()).iterator();
                Object temp;
                while (it.hasNext()) {
                    temp = it.next();
                    File file = null;
                    if (temp instanceof ResourceFile) {
                        String path = ((ResourceFile) temp).getPath();
                        if(WebTemplatesUtil.isInUserTemplate(path)){
                            file = WebTemplatesUtil.getFile(path);
                        }

                    } else if (temp instanceof IResource) {
                        IProject project = ((IResource) temp).getProject();
                        final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                        if(project.equals(resourceStore.getResource().getProject())){
                            String path = ((IResource)temp).getProjectRelativePath().toString();
                            if(WebTemplatesUtil.isInUserTemplate(path)){
                                file = WebTemplatesUtil.getFile(path);

                            }
                        }
                    }else if(temp instanceof File){
                        if(WebTemplatesUtil.isInUserTemplate(temp)){
                            file = (File) temp;
                        }
                    }
                    if(file != null && file.exists() && !file.isDirectory()){
                        URI uri = file.toURI();
                        try {
                            if(file.getName().endsWith(".htm")||file.getName().endsWith(".html") ||file.getName().endsWith(".js") ){//FIXME use IFile to determine content type
                                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), uri, IDE.getEditorDescriptor("test.txt")////$NON-NLS-1$
                                        .getId(), true);
                            }else{
                                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), uri, IDE.getEditorDescriptor(file.getName())
                                        .getId(), true);
                            }
                        } catch (PartInitException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                }
            }
        });

        // remove the resource Folder
        removeFolder = getWidgetFactory().createButton(buttonsComposite, Messages.Remove, SWT.FLAT);
        removeFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!MessageDialog.openConfirm(removeFolder.getShell(), Messages.confirmDeleteFile_title, Messages.confirmDeleteFile_msg)){
                    return;
                }
                Iterator<?> it = ((ITreeSelection) tv.getSelection()).iterator();
                Object temp;
                Object toRemove = null;
                final ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                final String processUUID = ModelHelper.getEObjectID(getAbstractProcess()) ;
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
                        getEditingDomain().getCommandStack().execute(
                                new RemoveCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, toRemove));

                    } else if (temp instanceof ResourceFile) {
                        toRemove = temp;
                        if (WebTemplatesUtil.isInUserTemplate(toRemove)) {
                            ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                            file.removeResource(((ResourceFile) toRemove).getPath());
                        }
                        getEditingDomain().getCommandStack().execute(
                                new RemoveCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES, toRemove));

                    } else if (temp instanceof IResource) {
                        ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
                        file.removeResource((IResource) temp);
                        tv.remove(temp);
                    } else if (temp instanceof File) {
                        if(WebTemplatesUtil.isInUserTemplate(temp)){
                            FileUtil.deleteDir((File) temp);
                        }
                        tv.remove(temp);
                    }
                    if (toRemove != null) {
                        tv.refresh();
                    }

                }

            }
        });

    }

    /**
     * @param templates
     */
    private void createLoginPage(Composite templates) {
        Button download;
        // ---------------- login page

        CLabel loginLabel = getWidgetFactory().createCLabel(templates, Messages.ResourceSection_LoginPage, SWT.CENTER);
        loginLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, true, false, 4, 1));

        Composite pathComposite = getWidgetFactory().createComposite(templates, SWT.NONE);
        pathComposite.setLayoutData(GridDataFactory.fillDefaults().span(5, 1).grab(true, false).create());
        GridLayout layout2 = new GridLayout(5,false);
        layout2.marginWidth = 2;
        layout2.marginHeight = 0;
        pathComposite.setLayout(layout2);
        // the path to the html template
        loginPath = getWidgetFactory().createText(pathComposite, "");
        loginPath.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
        loginPath.setEditable(false);

        Button clear = getWidgetFactory().createButton(pathComposite,"", SWT.FLAT);//$NON-NLS-1$
        clear.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));
        clear.setImage(Pics.getImage(PicsConstants.remove));
        clear.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                loginPath.setText("");
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE, null));
            }
        });
        // browse for login page
        changeLogin = getWidgetFactory().createButton(pathComposite, Messages.Browse, SWT.FLAT);
        changeLogin.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));
        changeLogin.addListener(SWT.Selection, browseButtonListener);

        editLogin = getWidgetFactory().createButton(pathComposite, Messages.Edit, SWT.FLAT);
        editLogin.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        editLogin.addSelectionListener(editButtonListener);
        loginPath.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                String path = loginPath.getText();
                editLogin.setEnabled(isEditable(path));
            }
        });
        // download template
        download = getWidgetFactory().createButton(pathComposite, Messages.Download, SWT.FLAT);
        download.setLayoutData(new GridData(GridData.END, GridData.FILL, false, false, 1, 1));
        download.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                downloadDefaultTemplate("login.jsp", "application/");
            }
        });


    }

    /**
     * @param path
     * @return
     */
    protected boolean isEditable(String path) {
        if(path != null && path.length()>0){
            File file = WebTemplatesUtil.getFile(path);
            if (file != null && file.exists() && !file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void setEObject(EObject object) {
        super.setEObject(object);


        if (getEObject() != null) {
            // if it's a lane take the eContainer (the pool)
            if (getEObject() instanceof Lane) {
                resourceContainer = (ResourceContainer) getEObject().eContainer();
            } else if (getEObject() instanceof ResourceContainer) {
                resourceContainer = (ResourceContainer) getEObject();
            }

            // reload login.jsp
            AssociatedFile loginJsp = ((AbstractProcess) resourceContainer).getLogInPage();
            if (loginJsp != null) {
                loginPath.setText(loginJsp.getPath());
            } else {
                loginPath.setText("");
            }
            // reload resources folder+files
            ArrayList<AssociatedFile> toRemoveFolders = new ArrayList<AssociatedFile>();
            ArrayList<AssociatedFile> toRemoveFiles = new ArrayList<AssociatedFile>();
            CompoundCommand cc = new CompoundCommand();
            if (toRemoveFolders.size() > 0) {
                cc.append(new RemoveCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, toRemoveFolders));
            }
            if (toRemoveFiles.size() > 0) {
                cc.append(new RemoveCommand(getEditingDomain(), resourceContainer, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES, toRemoveFiles));
            }
            if (!cc.isEmpty()) {
                getEditingDomain().getCommandStack().execute(cc);
            }
            tv.setInput(resourceContainer);
            tv.expandToLevel(1);
        }

        tv.refresh();
    }


    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    /**
     * download login page from bonita-app.war
     * 
     * @param name
     *            resource name
     * @param path
     *            path in bonita-app.war
     */
    public static void downloadDefaultTemplate(String name, String path) {
        FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        fd.setFileName(name);
        String outFile = fd.open();
        if(outFile != null){
            File template = new File(outFile);
            template.delete();
            try {
                template.createNewFile();
                // should not be copied here
                PlatformUtil.copyResource(new File(TMP_DIR), ProjectUtil.getConsoleLibsBundle(), "tomcat/webapps/bonita.war", new NullProgressMonitor());
                FileUtil.getFileFromZip(new File(TMP_DIR + File.separatorChar + "bonita.war"), path + name, template);
                // edit it in the java/html editor?
                // IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
                // ifile,BonitaJavaEditor.ID);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected AbstractProcess getAbstractProcess() {
        EObject eo = getEObject();
        if (eo instanceof AbstractProcess) {
            return (AbstractProcess) eo;
        } else {
            return ModelHelper.getParentProcess(eo);
        }
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

    @Override
    public String getSectionDescription() {
        return Messages.resourcePropertySectionDescription;
    }



}
