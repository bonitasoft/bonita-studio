/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.userguidance.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.engine.command.OpenBrowserCommand;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.profiles.repository.ProfileRepositoryStore;
import org.bonitasoft.studio.userguidance.UserGuidancePlugin;
import org.bonitasoft.studio.userguidance.i18n.Messages;
import org.bonitasoft.studio.userguidance.model.Task;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 *
 */
public class UserGuideWizardPage extends WizardPage {

    public static final String BOS_REDIRECT_ID = "bos_redirect_id";
    public static final String BOS_REDIRECT_PRODUCT = "bos_redirect_product";
    public static final String BOS_REDIRECT_MAJOR_VERSION = "bos_redirect_major_version";
    public static final String BOS_REDIRECT_MINOR_VERSION = "bos_redirect_minor_version";
    public static final String MAJOR = ProductVersion.CURRENT_VERSION.substring(0, ProductVersion.CURRENT_VERSION.lastIndexOf("."));
    public static final String MINOR = ProductVersion.CURRENT_VERSION.replaceFirst(".*\\.(\\d+)(\\-.*)*", "$1");
    
    
    private static final String DEFAULT_LOCALE = "en";
    private TreeViewer taskListViewer;
    private Button skipButton;
    private Button doneUndoButton;
    private final Bundle bundle;

    protected UserGuideWizardPage() {
        super(UserGuideWizardPage.class.getName());
        setTitle(Messages.userGuidanceTitle) ;
        setDescription(Messages.userGuidanceDesc) ;
        setImageDescriptor(Pics.getWizban()) ;
        bundle = Platform.getBundle("org.bonitasoft.studio.userguidance") ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayout(new GridLayout(2, false)) ;

        Label taskTitle = new Label(mainComposite, SWT.NONE) ;
        IRepositoryStore<?> profileStore = RepositoryManager.getInstance().getRepositoryStore(ProfileRepositoryStore.class) ;
        String activeProfile = profileStore.getChild(BonitaProfilesManager.getInstance().getActiveProfile()+".profile").getName() ;
        taskTitle.setText(Messages.bind(Messages.tasksList,activeProfile)) ;

        new Label(mainComposite, SWT.NONE) ;

        taskListViewer = new TreeViewer(mainComposite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL) ;
        taskListViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(false,true).hint(SWT.DEFAULT, 500).create()) ;
        taskListViewer.setContentProvider(new TaskContentProvider()) ;
        taskListViewer.setLabelProvider(new TaskLabelProvider()) ;

        Composite leftComposite = new Composite(mainComposite,SWT.NONE) ;
        leftComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(700, 400).create()) ;
        GridLayout gl = new GridLayout(2, false) ;
        gl.verticalSpacing = 0 ;
        gl.marginWidth = 0 ;
        gl.marginHeight = 0 ;
        gl.marginBottom = 0 ;
        leftComposite.setLayout(gl) ;


        Link accessOnlineDocLink = new Link(leftComposite, SWT.NONE) ;
        accessOnlineDocLink.setText("<A>"+"Access online documentation"+"</A>") ;
        accessOnlineDocLink.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).span(2, 1).create()) ;



        final Group helpContentGroup = new Group(leftComposite, SWT.NONE) ;
        helpContentGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).span(2, 1).create()) ;
        helpContentGroup.setLayout(new FillLayout()) ;

        final Browser webBrowser = new Browser(helpContentGroup, SWT.NONE) ;

        accessOnlineDocLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                URL docUrl;
                try {
                    docUrl = new URL("http://www.bonitasoft.com/bos_redirect.php?"+BOS_REDIRECT_ID+"="+getSelectedTask().getRedirectId()+"&"+BOS_REDIRECT_PRODUCT+"=bos&"+BOS_REDIRECT_MAJOR_VERSION+"="+MAJOR+"&"+BOS_REDIRECT_MINOR_VERSION+"="+MINOR);
                    new OpenBrowserCommand(docUrl, "user.guidance.browser", "Online documentation").execute(null) ;
                } catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        });
        
        Label filler = new Label(leftComposite, SWT.NONE);
        
//        Link synchroniezLink = new Link(leftComposite, SWT.NONE) ;
//        synchroniezLink.setText("<A>"+Messages.synchronize+"</A>") ;
//        synchroniezLink.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create()) ;
//        synchroniezLink.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent event) {
//                IProgressService progress = PlatformUI.getWorkbench().getProgressService() ;
//                try {
//                    progress.run(false, false, new DocSynchronizer()) ;
//                } catch (InvocationTargetException e) {
//                    BonitaStudioLog.error(e) ;
//                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.synchronizationErrorTitle, Messages.synchronizationErrorMessage, new Status(IStatus.ERROR, UserGuidancePlugin.PLUGIN_ID, e.getMessage(), e), Status.ERROR).open() ;
//                } catch (InterruptedException e) {
//                    BonitaStudioLog.error(e) ;
//                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.synchronizationErrorTitle, Messages.synchronizationErrorMessage, new Status(IStatus.ERROR, UserGuidancePlugin.PLUGIN_ID, e.getMessage(), e), Status.ERROR).open() ;
//                }
//                String url = webBrowser.getUrl() ;
//                if(url != null){
//                    webBrowser.setUrl(url) ;
//                }
//            }
//        });


        Composite buttonContainer = new Composite(leftComposite, SWT.NONE) ;
        buttonContainer.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END,SWT.TOP).grab(false, false).create()) ;
        GridLayout gl2 = new GridLayout(2, false) ;
        gl2.marginTop = 0 ;
        gl2.marginBottom = 0 ;
        gl2.verticalSpacing = 0 ;
        gl2.horizontalSpacing = 0 ;
        gl2.marginHeight = 0 ;
        gl2.marginWidth = 0 ;
        buttonContainer.setLayout(gl2) ;

        skipButton = new Button(buttonContainer, SWT.PUSH) ;
        skipButton.setText(Messages.skip) ;
        skipButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!taskListViewer.getSelection().isEmpty()){
                    Task t = (Task) ((StructuredSelection) taskListViewer.getSelection()).getFirstElement() ;
                    t.setSkip(true) ;
                    t.setDone(false) ;
                    for(Task subtask : t.getSubtasks()){
                        subtask.setSkip(true) ;
                        subtask.setDone(false) ;
                        taskListViewer.update(subtask, null) ;
                    }


                    taskListViewer.update(t,null) ;
                    Map<Integer, Task> tasksByRank = Task.getTaskByRank() ;
                    Task next = null ;
                    for(int i = t.getRank() +1 ; i <  tasksByRank.size() ; i++){
                        if(tasksByRank.get(i) != null){
                            if(tasksByRank.get(i).getSubtasks().isEmpty() && !tasksByRank.get(i).isSkip() && !tasksByRank.get(i).isDone()){
                                next = tasksByRank.get(i) ;
                                break ;
                            }
                        }
                    }


                    if(next != null){
                        taskListViewer.setSelection(new StructuredSelection(next)) ;
                        updateButtons(next);
                    }else{
                        updateButtons(t);
                    }

                }
            }
        }) ;

        doneUndoButton = new Button(buttonContainer, SWT.PUSH) ;
        doneUndoButton.setText(Messages.done) ;

        doneUndoButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Task t = getSelectedTask() ;
                if(t.isSkip()){
                    t.setDone(false) ;
                }else{
                    t.setDone(!t.isDone()) ;
                }
                t.setSkip(false) ;
                taskListViewer.update(t,null) ;

                for(Task subtask : t.getSubtasks()){
                    if(subtask.isSkip()){
                        subtask.setDone(false) ;
                    }else{
                        subtask.setDone(t.isDone()) ;
                    }
                    subtask.setSkip(false) ;
                    taskListViewer.update(subtask, null) ;
                }


                if(t.getParentTaskId() != null){
                    Task parent = getTask(t.getParentTaskId()) ;
                    boolean allDone = true ;
                    boolean allSkip = true ;
                    for(Task c : parent.getSubtasks()){
                        if(!c.isDone()){
                            allDone = false ;
                        }
                        if(!c.isSkip()){
                            allSkip = false ;
                        }
                    }
                    parent.setDone(allDone) ;
                    parent.setSkip(allSkip) ;
                    taskListViewer.update(parent, null) ;
                }

                if(t.isDone()){
                    Map<Integer, Task> tasksByRank = Task.getTaskByRank() ;
                    Task next = null ;
                    for(int i = t.getRank() +1 ; i <  tasksByRank.size() ; i++){
                        if(tasksByRank.get(i) != null){
                            if(tasksByRank.get(i).getSubtasks().isEmpty() && !tasksByRank.get(i).isSkip() && !tasksByRank.get(i).isDone()){
                                next = tasksByRank.get(i) ;
                                break ;
                            }
                        }
                    }


                    if(next != null){
                        taskListViewer.setSelection(new StructuredSelection(next)) ;
                    }else{
                        updateButtons(t);
                    }
                }else{
                    updateButtons(t);
                }

            }
        }) ;
        taskListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                Task t = getSelectedTask() ;
                if(t != null && t.getContentURL() != null){
                    if(DocSynchronizer.getLatestDocRoot().isEmpty()){
                        URL url = bundle.getResource(toLocale(t.getContentURL())) ;
                        try {
                            webBrowser.setUrl(FileLocator.toFileURL(url).toString()) ;
                        } catch (IOException e) {
                            BonitaStudioLog.error(e) ;
                        }
                    }else{
                        webBrowser.setUrl(DocSynchronizer.getLatestDocRoot()+File.separatorChar+ toLocale(t.getContentURL())) ;
                    }

                }
                updateButtons(t) ;

            }
        }) ;

        try {
            taskListViewer.setInput(Task.buildTaskTree()) ;
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }

        taskListViewer.expandAll() ;
        Task selection = getFirstTaskTodo(Task.getTaskByRank().values()) ;
        if(selection == null){
            selection = Task.getTaskByRank().get(0) ;
        }
        taskListViewer.setSelection(new StructuredSelection(selection)) ;

        setControl(parent) ;
    }

    protected String toLocale(String defaultUrl) {
        if(isLocaleAvailable()){
            if(defaultUrl.startsWith("html/"+DEFAULT_LOCALE)){
                defaultUrl = defaultUrl.replaceFirst("html/"+DEFAULT_LOCALE, "html/"+Platform.getNL()) ;
            }
        }
        return defaultUrl;
    }

    private boolean isLocaleAvailable() {
        String nl = Platform.getNL() ;
        if(DocSynchronizer.getLatestDocRoot().isEmpty()){
            if(bundle.getResource("html/"+nl) != null){
                return true ;
            }else{
                return false ;
            }
        }else{
            if(new File(DocSynchronizer.getLatestDocRoot()+File.separatorChar+nl).exists()){
                return true ;
            }else{
                return false ;
            }
        }
    }

    protected Task getTask(String id) {
        for(Task t : Task.getTaskByRank().values()){
            if(t.getId().equals(id)){
                return t ;
            }
        }
        return null;
    }

    private Task getFirstTaskTodo(Collection<Task> tasks) {
        for(Task t : tasks){
            if(!t.isDone() && t.getSubtasks().isEmpty() && !t.isSkip()){
                return t ;
            }
            if(!t.getSubtasks().isEmpty()){
                Task result = getFirstTaskTodo(t.getSubtasks()) ;
                if(result != null){
                    return result ;
                }
            }
        }
        return null;
    }

    protected void updateButtons(Task selectedTask) {

        if(skipButton != null){
            skipButton.setEnabled(selectedTask != null && !selectedTask.isDone() && !selectedTask.isSkip() ) ;
        }

        if(doneUndoButton != null){

            doneUndoButton.setEnabled(true);//selectedTask != null && selectedTask.getSubtasks().isEmpty()) ;

            if(selectedTask != null && (selectedTask.isDone() || selectedTask.isSkip())){
                doneUndoButton.setText(Messages.undo) ;
            }else{
                doneUndoButton.setText(Messages.done) ;
            }
        }

    }

    private Task getSelectedTask(){
        if(!taskListViewer.getSelection().isEmpty()){
            Task t = (Task) ((StructuredSelection) taskListViewer.getSelection()).getFirstElement() ;
            return t ;
        }
        return null ;
    }

}
