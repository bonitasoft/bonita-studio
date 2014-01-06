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
package org.bonitasoft.studio.application.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.application.contribution.IPostImportContribution;
import org.bonitasoft.studio.application.contribution.IPreImportContribution;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;
import org.eclipse.ui.progress.IProgressService;
import static org.bonitasoft.studio.common.Messages.bonitaBPMCommunity;
/**
 * @author Mickael Istria
 *
 */
public class ImportFromEXchangeAction implements IIntroAction {

	/**
	 * @author Mickael Istria
	 *
	 */
	private final class InstallRunnable implements IRunnableWithProgress {
		/**
		 * 
		 */
		private final String contributionFile;
		/**
		 * 
		 */
		private final String contributionURL;
		/**
		 * 
		 */
		private final IIntroSite site;
		String message;
		private String pemAuthCookie;
		private String forumCookie;

		/**
		 * @param contributionFile
		 * @param contributionURL
		 * @param site
		 */
		private InstallRunnable(String contributionFile, String contributionURL, String forumCookie, String pemAuthCookie, IIntroSite site) {
			this.contributionFile = contributionFile;
			this.contributionURL = contributionURL;
			this.site = site;
			this.pemAuthCookie = pemAuthCookie;
			this.forumCookie = forumCookie;
		}

		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			try {
				message = installContribution(site, contributionURL, contributionFile, forumCookie, pemAuthCookie, monitor);
			} catch (Exception ex) {
				message = ex.getMessage();
			}
		}
	}

	protected Control control;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
	 */
	public void run(final IIntroSite site, Properties params) {
		final String contributionURL = params.getProperty("contributionURL");
		final String contributionName = params.getProperty("contributionName");
		final String contributionFile = params.getProperty("fileName");
		final String forumCookie = params.getProperty("d1");
		final String pemAuthCookie = params.getProperty("d2");
		
		if (MessageDialog.openConfirm(site.getShell(), Messages.confirmContributionInstall_title, Messages.bind(Messages.confirmContributionInstall_message, contributionName))) {
			IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
			InstallRunnable runnable = new InstallRunnable(contributionFile, contributionURL, forumCookie, pemAuthCookie, site);
			
			try {
				progressManager.run(false, false, runnable);
				if (runnable.message != null) {
					MessageDialog.openError(site.getShell(), Messages.error, runnable.message);	
				} else {
					MessageDialog.openInformation(site.getShell(), Messages.success, Messages.contributionSuccessfullyInstalled);
				}
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				MessageDialog.openError(site.getShell(), "Error", "An error occured while importing, see log for details");
			}
			
		}
	}

	/**
	 * @param site
	 * @param contributionURL
	 * @param contributionFile
	 * @return the error that occured, or null
	 */
	private String installContribution(final IIntroSite site, final String contributionURL, final String contributionFile, String forumCookie, String pemAuthCookie, IProgressMonitor monitor) throws Exception {
			InputStream contrib = null;
			File testFile = null;
			
			monitor.beginTask(Messages.installingFromContribution, 5);
			monitor.subTask(Messages.downloading);
			try {
				URL url = new URL(contributionURL);
				URLConnection connection = url.openConnection();
				connection.setRequestProperty("Cookie", "forum_cookie_5eca4a=" + forumCookie + "; pem_auth_cookie=" + URLEncoder.encode(pemAuthCookie, "UTF-8"));
				connection.connect();
				contrib = connection.getInputStream();
				testFile = File.createTempFile("testContrib", ".zip", ProjectUtil.getBonitaStudioWorkFolder());
				FileOutputStream fos = new FileOutputStream(testFile);
				FileUtil.copy(contrib, fos);
				fos.close();
				contrib.close();
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				return Messages.errorWhileDownloadingContrib;
			}
			monitor.worked(3);
			monitor.subTask(Messages.installingContribution);
			try {
				new ZipFile(testFile);
			} catch (Exception ex) {
				return Messages.bind(Messages.notLoggedIn, new Object[]{bonitaBPMCommunity});
			}
			
			try {
				
				IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.application.preImport"); //$NON-NLS-1$
				IPreImportContribution preImportcontrib = null;
				for (IConfigurationElement elem : elems){
					try {
						preImportcontrib = (IPreImportContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					} 
					preImportcontrib.execute();
				}
				
				//TODO Re make import
//				IProgressMonitor progressMonitor = new NullProgressMonitor();
//				if (contributionFile.endsWith(".jar")) {
//					contrib = new FileInputStream(testFile);
//					JarRepository.getInstance().importArtifact(contributionFile, contrib, progressMonitor);
//					contrib.close();
//				} else if (contributionFile.endsWith(".zip")) {
//					RepositoryPlugin.getPlugin().importFromZip(testFile, monitor, progressMonitor);
//				} else if (contributionFile.endsWith(".bar")) {
//					contrib = new FileInputStream(testFile);
//					final File tempFile = File.createTempFile("temp", ".bar");
//					FileOutputStream out = new FileOutputStream(tempFile);
//					FileUtil.copy(contrib, out);
//					out.close();
//					Display.getDefault().syncExec(new Runnable() {						
//						public void run() {
//							Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//							try {
//								ProcessRepository.ImportBARModule.importProcOrBar(shell, tempFile, new NullProgressMonitor());
//							} catch (Exception e) {
//								BonitaStudioLog.log(e);
//							}
//						}
//					});
//										
//					tempFile.delete();
//					contrib.close();
//				}
//				monitor.subTask(Messages.refresh);
//				ExtensionProjectUtil.refresh(progressMonitor);
				
				elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.application.postImport"); //$NON-NLS-1$
				IPostImportContribution postImportcontrib = null;
				for (IConfigurationElement elem : elems){
					try {
						postImportcontrib = (IPostImportContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					} 
					postImportcontrib.execute();
				}
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				return "An error occured while importing, see log for details";
			}
			monitor.done();
			return null;
	}


}
