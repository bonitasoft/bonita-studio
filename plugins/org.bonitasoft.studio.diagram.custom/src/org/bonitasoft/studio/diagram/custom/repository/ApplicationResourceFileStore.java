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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.common.EcoreUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class ApplicationResourceFileStore extends AbstractFileStore implements IRepositoryFileStore {

	private final class FileNameFilterWithResults implements FilenameFilter {
		private List<String> unmatchedFiles = new ArrayList<String>();

		public boolean accept(File dir, String name) {
			if (name.startsWith(".")) {
				if(!name.equals(".svn")){
					unmatchedFiles.add(name);
				}
				return false;
			} else {
				return true;
			}
		}

		/**
		 * @return
		 */
		public List<String> getInvalidFiles() {
			return unmatchedFiles;
		}
	}


	public enum ResourceType {
		RESOURCE,
		PROCESS_TEMPLATE,
		PAGE_TEMPLATE,
		ERROR_TEMPLATE,
		CONFIRMATION_TEMPLATE,
		GLOBAL_PAGE_TEMPLATE,
		WELCOME,
		LOGIN_PAGE,
		GLOBAL_CONSULTATION_TEMPLATE,
		HOST_PAGE
	};

	private static final String PREVIEW_JPG = "preview.jpg";
	private static final String PREVIEW_PNG = "preview.png";
	private static final String File_EXTENSION = ".html";
	private static final String HTML_FOLDER = "html";
	private static final String RESOURCE_FOLDER = "application";
	private static final String PROCESS_TEMPLATE = "process.html";
	private static final String GLOBAL_PAGE_TEMPLATE = "page.html";
	private static final String CONFIRMATION_TEMPLATE = "confirmation.html";
	private static final String ERROR_TEMPLATE = "error.html";
	private static final String WELCOME = "welcome.html";
	private static final String LOGIN_PAGE = "login.jsp";
	private static final String CONSULTATION_TEMPLATE = "consultation.html";
	private static final String HOST_PAGE = "BonitaApplication.html";


	public ApplicationResourceFileStore(String processUUID,IRepositoryStore store){
		super(processUUID, store) ;
		if(!getResource().exists()){
			try {
				getResource().create(true, true, Repository.NULL_PROGRESS_MONITOR) ;
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}


	public Image getIcon() {
		return Pics.getImage("resources.gif",Activator.getDefault());
	}


	public Object getContent() {
		return null;
	}


	public void rename(String newName) {

	}

	@Override
	protected void doDelete() {
		try {
			getResource().delete(true, Repository.NULL_PROGRESS_MONITOR) ;
		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}
	}

	@Override
	protected void doSave(Object content) {

	}


	@Override
	protected IWorkbenchPart doOpen() {
		return null ;
	}


	@Override
	protected void doClose() {

	}

	@Override
	public String getDisplayName() {
		String name = getName() ;
		DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class) ;
		for(AbstractProcess proc : store.getAllProcesses()){
			String id = ModelHelper.getEObjectID(proc) ;
			if(name.equals(id)){
				return proc.getName() +" ("+proc.getVersion()+")" ;
			}
		}
		return super.getDisplayName();
	}


	public List<IFile> getHTMLFiles() {
		FindFilesResourceVisitor visitor = new FindFilesResourceVisitor(".*\\.html");
		try {
			getParentStore().getResource().getFolder(HTML_FOLDER).accept(visitor);
		} catch (Exception ex) {
			BonitaStudioLog.error(ex);
		}
		return visitor.getFiles();
	}


	public String getResourceProjectRelativePath() {
		return  getName() + "/" + RESOURCE_FOLDER;
	}


	public String getProcessTemplateProjectRelativePath() {
		return  getName() + "/" + HTML_FOLDER + "/" + PROCESS_TEMPLATE;
	}

	/**
	 * only for provided template
	 * 
	 * @return
	 */
	public String getErrorTemplateProjectRelativePath() {
		return  getName() + "/" + HTML_FOLDER + "/" + ERROR_TEMPLATE;
	}

	/**
	 * 
	 * only for provided template
	 * 
	 * @return
	 */
	public String getConfirmationTemplateRelativePath() {
		return  getName() + "/" + HTML_FOLDER + "/" + CONFIRMATION_TEMPLATE;
	}

	/**
	 * 
	 * only for provided template
	 * 
	 * @return
	 */
	public String getGlobalPageTemplateRelativePath() {
		return  getName() + "/" + HTML_FOLDER + "/" + GLOBAL_PAGE_TEMPLATE;
	}

	/**
	 * only for provided template
	 * 
	 * @return
	 */
	public String getGlobalConsultationTemplateRelativePath() {
		return  getName() + "/" + HTML_FOLDER + "/" + CONSULTATION_TEMPLATE;
	}

	public String getHostPageRelativePath() {
		return getName() + "/" + getResourcesApplicationFolder().getName() + "/application/" + HOST_PAGE;
	}

	/**
	 * 
	 */
	public String setGlobalPageTemplate(String path) {
		return addResource(new File(path), ResourceType.GLOBAL_PAGE_TEMPLATE,null, Repository.NULL_PROGRESS_MONITOR);
	}

	/**
	 * 
	 */
	public String setErrorTemplate(String path) {
		return addResource(new File(path),ResourceType.ERROR_TEMPLATE,null,Repository.NULL_PROGRESS_MONITOR );
	}

	public String setProcessTemplate(String path){
		return addResource(new File(path), ResourceType.PROCESS_TEMPLATE,null, Repository.NULL_PROGRESS_MONITOR);
	}
	public IFile getProcessTemplate(){
		return getWebTemplateFolder().getFile(PROCESS_TEMPLATE);
	}
	public IFile getErrorTemplate(){
		return getWebTemplateFolder().getFile(ERROR_TEMPLATE);
	}
	public IFile getGlobalPageTemplate(){
		return getWebTemplateFolder().getFile(GLOBAL_PAGE_TEMPLATE);
	}
	public IFile getConfirmationTemplate(){
		return getWebTemplateFolder().getFile(CONFIRMATION_TEMPLATE);
	}
	public IFile getConsultationTemplate(){
		return getWebTemplateFolder().getFile(CONSULTATION_TEMPLATE);
	}
	public IFile getWelcomePage(){
		return getWebTemplateFolder().getFile(WELCOME);
	}

	public String getWelcomePageRelativePath() {
		return getName() +  "/" + HTML_FOLDER + "/" + WELCOME;
	}


	public IFile getLoginPage(){
		return getWebTemplateFolder().getFile(LOGIN_PAGE);
	}

	public String getLoginPageRelativePath() {
		return  getName() +  "/" + HTML_FOLDER + "/" + LOGIN_PAGE;
	}

	public IFile getHostPage() {
		return getResourcesApplicationFolder().getFolder("application").getFile(HOST_PAGE);
	}

	public String setWelcomePage(String path){
		return addResource(new File(path), ResourceType.WELCOME,null, Repository.NULL_PROGRESS_MONITOR);
	}

	public String setLoginPage(String path){
		return addResource(new File(path), ResourceType.LOGIN_PAGE,null, Repository.NULL_PROGRESS_MONITOR);
	}

	public String setConfirmationTemplate(String path){
		return setConfirmationTemplate(path, null);
	}

	public String setConfirmationTemplate(String path, EObject element){
		return addResource(new File(path), ResourceType.CONFIRMATION_TEMPLATE,element, Repository.NULL_PROGRESS_MONITOR);
	}

	public String setPageTemplate(String path,Element element){
		return addResource(new File(path), ResourceType.PAGE_TEMPLATE,element, Repository.NULL_PROGRESS_MONITOR);
	}

	public IFolder getWebTemplateFolder() {
		IFolder folder = getResource().getFolder(HTML_FOLDER);
		if (!folder.exists()) {
			try {
				folder.create(true, true, Repository.NULL_PROGRESS_MONITOR);
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}
		return folder;
	}

	public IFolder getResource() {
		return getParentStore().getResource().getFolder(getName());
	}


	public String setGlobalConsultationPage(String path) {
		return addResource(new File(path), ResourceType.GLOBAL_CONSULTATION_TEMPLATE,null, Repository.NULL_PROGRESS_MONITOR);
	}

	public String setHostPage(String path) {
		return addResource(new File(path), ResourceType.HOST_PAGE,null,Repository.NULL_PROGRESS_MONITOR);
	}

	public String setPreviewFile(File file){
		File destFile = getResource().getFile(PREVIEW_JPG).getLocation().toFile();
		PlatformUtil.copyResource(destFile.getParentFile(), file, Repository.NULL_PROGRESS_MONITOR);
		new File(destFile.getParent()+File.separator+file.getName()).renameTo(destFile);

		return destFile.getAbsolutePath();
	}

	public String addResource(File file, IProgressMonitor monitor) {
		return addResource(file, ResourceType.RESOURCE,null, monitor);
	}

	public String createResourceFolder(String name){
		File destFile = null;
		destFile = getResourcesApplicationFolder().getFile(name).getLocation().toFile();
		destFile.mkdirs();
		try {
			getResource().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
		}

		String templatePath = getParentStore().getResource().getLocation().toOSString();
		if (destFile.getAbsolutePath().startsWith(templatePath)) {
			String substring = destFile.getAbsolutePath().substring(templatePath.length() + 1);
			return Path.fromOSString(substring).toString();
		} else {
			return destFile.getAbsolutePath();
		}
	}

	protected String addResource(final File file, ResourceType type, Object element, IProgressMonitor monitor) {
		if (file.getName().startsWith(".")) {
			List<String> files = new ArrayList<String>();
			files.add(file.getName());
			notifyInvalidFiles(files);
			return null;
		}
		if (file.exists()) {
			File destFile = null;
			switch (type) {
			case RESOURCE:
				if(element instanceof ResourceFolder){
					File file2 = WebTemplatesUtil.getFile(((AssociatedFile) element).getPath());
					destFile = new File(file2,file.getName());
				}else if(element instanceof File){
					destFile = new File((File) element,file.getName());
				}else{
					destFile = getResourcesApplicationFolder().getFile(file.getName()).getLocation().toFile();
				}
				break;
			case PROCESS_TEMPLATE:
				destFile = getWebTemplateFolder().getFile(PROCESS_TEMPLATE).getLocation().toFile();
				break;
			case GLOBAL_PAGE_TEMPLATE:
				destFile = getWebTemplateFolder().getFile(GLOBAL_PAGE_TEMPLATE).getLocation().toFile();
				break;
			case GLOBAL_CONSULTATION_TEMPLATE:
				destFile = getWebTemplateFolder().getFile(CONSULTATION_TEMPLATE).getLocation().toFile();
				break;
			case ERROR_TEMPLATE:
				destFile = getWebTemplateFolder().getFile(ERROR_TEMPLATE).getLocation().toFile();
				break;
			case HOST_PAGE:
				destFile = getResourcesApplicationFolder().getFolder("application").getFile(HOST_PAGE).getLocation().toFile();
				break;
			case PAGE_TEMPLATE:
				destFile = getWebTemplateFolder().getFile(ModelHelper.getEObjectID((EObject) element)+File_EXTENSION).getLocation().toFile();
				break;
			case CONFIRMATION_TEMPLATE:
				if(element == null){
					destFile = getWebTemplateFolder().getFile(CONFIRMATION_TEMPLATE).getLocation().toFile();
				}else{
					destFile = getWebTemplateFolder().getFile(ModelHelper.getEObjectID((EObject) element)+CONFIRMATION_TEMPLATE).getLocation().toFile();
				}
				break;
			case WELCOME:
				destFile = getWebTemplateFolder().getFile(WELCOME).getLocation().toFile();
				break;
			case LOGIN_PAGE:
				destFile = getWebTemplateFolder().getFile(LOGIN_PAGE).getLocation().toFile();
				break;
			default:
				destFile = getResourcesApplicationFolder().getFile(file.getName()).getLocation().toFile();
				break;
			}
			final FileNameFilterWithResults filterWithResult = new FileNameFilterWithResults();
			PlatformUtil.copyResource(destFile, file, filterWithResult, monitor);
			if (filterWithResult.getInvalidFiles().size() > 0) {
				notifyInvalidFiles(filterWithResult.getInvalidFiles());
			}
			try {
				getResource().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}

			String templatePath = getParentStore().getResource().getLocation().toFile().getAbsolutePath();
			if (destFile.getAbsolutePath().startsWith(templatePath)) {
				String substring = destFile.getAbsolutePath().substring(templatePath.length() + 1);
				return Path.fromOSString(substring).toString();
			} else {
				return destFile.getAbsolutePath();
			}
		}
		return null;
	}

	public IFolder getResourcesApplicationFolder() {
		IFolder folder = getResource().getFolder(RESOURCE_FOLDER);
		if (!folder.exists()) {
			try {
				folder.create(true, true, Repository.NULL_PROGRESS_MONITOR);
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}
		return folder;
	}

	private void notifyInvalidFiles(final List<String> files) {
		BonitaStudioLog.log(".svn POP-UP !!!");
		for (StackTraceElement item : Thread.currentThread().getStackTrace()) {
			BonitaStudioLog.log(item.toString());
		}

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				StringBuilder builder = new StringBuilder(Messages.invalidResourceFileName_message);
				builder.append("\n");
				for (String name : files) {
					builder.append("* ");
					builder.append(name);
					builder.append("\n");
				}
				MessageDialog.openWarning(shell, Messages.invalidResourceFileName_title, builder.toString());
			}
		});
	}

	public void removeResource(String resourceName) {
		String path = getParentStore().getResource().getLocation().toOSString() + File.separator + resourceName;
		File file = new File(path);
		if(file.exists()){
			if(file.isDirectory()){
				FileUtil.deleteDir(file);
			}else{
				PlatformUtil.delete(file, Repository.NULL_PROGRESS_MONITOR) ;
			}
		}


		try {
			getResource().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
		}
	}

	/**
	 * @param temp
	 */
	public void removeResource(IResource temp) {
		try {
			temp.delete(true, Repository.NULL_PROGRESS_MONITOR);
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
		}
	}

	public void clear() {
		try{
			for (IResource toDelete : getResourcesApplicationFolder().members()) {
				toDelete.delete(true, Repository.NULL_PROGRESS_MONITOR);
			}
			deleteIfExists(getConfirmationTemplate());
			deleteIfExists(getConsultationTemplate());
			deleteIfExists(getErrorTemplate());
			deleteIfExists(getGlobalPageTemplate());
			deleteIfExists(getHostPage());
			deleteIfExists(getLoginPage());
			deleteIfExists(getProcessTemplate());
			deleteIfExists(getWelcomePage());
		}catch(Exception e){
			BonitaStudioLog.error(e) ;
		}
	}

	private void deleteIfExists(IFile file) throws CoreException {
		if(file.exists()){
			file.delete(true, Repository.NULL_PROGRESS_MONITOR);
		}
	}

	public String setApplicationResource(ResourceType resource, String path) {
		switch (resource) {
		case CONFIRMATION_TEMPLATE: return setConfirmationTemplate(path);
		case GLOBAL_CONSULTATION_TEMPLATE: return setGlobalConsultationPage(path);
		case ERROR_TEMPLATE: return setErrorTemplate(path);
		case GLOBAL_PAGE_TEMPLATE: return setGlobalPageTemplate(path);
		case HOST_PAGE: return setHostPage(path);
		case LOGIN_PAGE: return setLoginPage(path);
		case PROCESS_TEMPLATE: return setProcessTemplate(path);
		case WELCOME: return setWelcomePage(path);
		}
		return null;
	}

}
