///**
// * Copyright (C) 2011 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.repository.themes;
//
//import java.io.InputStream;
//
//import org.bonitasoft.studio.common.exporter.ExporterTools.TemplateType;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.repository.themes.exceptions.ThemeCreationException;
//import org.eclipse.core.resources.IContainer;
//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.resources.IFolder;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.Status;
//
///**
// * @author Baptiste Mesta
// *
// */
//public class ApplicationThemeArtifact extends ThemeArtifact{
//
//	/**
//	 * 
//	 */
//	private static final String HTML_FOLDER = "html";
//	private static final String APPLICATION_FOLDER = "application";
//	private static final String PROCESS_TEMPLATE = "process.html";
//	private static final String GLOBAL_PAGE_TEMPLATE = "page.html";
//	private static final String CONFIRMATION_TEMPLATE = "confirmation.html";
//	private static final String ERROR_TEMPLATE = "error.html";
//	private static final String WELCOME = "welcome.html";
//	private static final String LOGIN_PAGE = "login.jsp";
//	private static final String CONSULTATION_TEMPLATE = "consultation.html";
//	private static final String HOST_PAGE = "BonitaApplication.html";
//
//	/**
//	 * @param id
//	 * @throws ThemeCreationException
//	 */
//	ApplicationThemeArtifact(String id) throws ThemeCreationException {
//		super(id);
//	}
//	
//	/**
//	 * @param id 
//	 * @param newDescription 
//	 * @param duplicateFrom 
//	 * @throws ThemeCreationException 
//	 * 
//	 */
//	ApplicationThemeArtifact(String id, String newDescription, ApplicationThemeArtifact duplicateFrom) throws ThemeCreationException {
//		super(id, newDescription, duplicateFrom);
//	}
//
//	public IContainer getResourcesApplicationFolder() {
//		return getResource().getFolder(APPLICATION_FOLDER);
//	}
//	public IFile getProcessTemplate() {
//		return getTemplate(TemplateType.PROCESS);
//	}
//	private IFolder getTemplateFolder() {
//		return getResource().getFolder(HTML_FOLDER);
//	}
//	public IResource getConsultationTemplate() {
//		return getTemplate(TemplateType.GLOBAL_CONSULTATION);
//	}
//	public IFile getErrorTemplate() {
//		return getTemplate(TemplateType.ERROR);
//	}
//	public IFile getGlobalPageTemplate() {
//		return getTemplate(TemplateType.GLOBAL_PAGE);
//	}
//	public IFile getLoginPage() {
//		return getTemplate(TemplateType.LOGIN_PAGE);
//	}
//	public IFile getHostPage() {
//		return getTemplate(TemplateType.HOST_PAGE);
//	}
//
//	public IFile getConfirmationTemplate(){
//		return getTemplate(TemplateType.CONFIRMATION);
//	}
//	public IFile getWelcomePage() {
//		return getTemplate(TemplateType.WELCOME);
//	}
//	
//	public IFile getTemplate(TemplateType type){
//		String templateFileName = getTemplateFileName(type);
//		if(templateFileName != null){
//			return getTemplateFolder().getFile(templateFileName);
//		} else{
//			return null;
//		}
//	}
//	
//	/**
//	 * @param type
//	 * @return
//	 */
//	private String getTemplateFileName(TemplateType type) {
//		switch (type) {
//		case GLOBAL_CONSULTATION:
//			return CONSULTATION_TEMPLATE;
//		case GLOBAL_PAGE:
//			return GLOBAL_PAGE_TEMPLATE;
//		case ERROR:
//			return ERROR_TEMPLATE;
//		case PROCESS:
//			return PROCESS_TEMPLATE;
//		case HOST_PAGE:
//			return HOST_PAGE;
//		case WELCOME:
//			return WELCOME;
//		case CONFIRMATION:
//			return CONFIRMATION_TEMPLATE;
//		case LOGIN_PAGE:
//			return LOGIN_PAGE;
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @param type
//	 * @return
//	 * 		true if the template was deleted
//	 */
//	public boolean deleteTemplate(TemplateType type){
//		IFile template = getTemplate(type);
//		if(template!= null && template.exists()){
//			try {
//				template.refreshLocal(IResource.DEPTH_ONE, ThemeRepository.NULL_PROGRESS_MONITOR) ;
//				template.delete(false, ThemeRepository.NULL_PROGRESS_MONITOR);
//				return true;
//			} catch (CoreException e) {
//				BonitaStudioLog.log(e);
//			}
//		}
//		return false;
//	}
//	
//	public void setTemplate(TemplateType type, InputStream inputStream) throws CoreException{
//		IFile template = getTemplate(type);
//		if (template != null) {
//			if (!template.exists()) {
//				template.create(inputStream, false, ThemeRepository.NULL_PROGRESS_MONITOR);
//			}else{
//				template.setContents(inputStream, false, false, ThemeRepository.NULL_PROGRESS_MONITOR);
//			}
//		} else {
//			throw new CoreException(Status.CANCEL_STATUS);
//		}
//	}
//
//}
