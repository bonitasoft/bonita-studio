/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.dependencies.handler;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;

import org.bonitasoft.studio.common.ZipInputStreamIFileFriendly;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class AddJarsHandler extends AbstractHandler {

	private String[] filenames = null;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		final FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN | SWT.MULTI);
		fd.setFilterExtensions(new String[] { "*.jar;*.zip" });
		if(filenames != null){
			fd.setFilterNames(filenames);
		}
		if (fd.open() != null) {
			final DependencyRepositoryStore libStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
			final String[] jars = fd.getFileNames();
			IProgressService progressManager = PlatformUI.getWorkbench().getProgressService() ;
			IRunnableWithProgress runnable = new IRunnableWithProgress(){
				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.beginToAddJars, jars.length);
					Map<String, InputStream> jarsToImportMap = new HashMap<String, InputStream>();
					for (String jar : jars) {
						try {
							if (monitor.isCanceled()) {
								return;
							}
							File file = null ;
							if(jar.contains(fd.getFilterPath())){
								file = new File(jar);
							}else{
								file = new File(fd.getFilterPath() + File.separator + jar);
							}

							FileInputStream fis = new FileInputStream(file);
							if (file.getName().endsWith(".jar")) { //$NON-NLS-1$
								monitor.setTaskName(Messages.addingJar + " " + file.getName());
							jarsToImportMap.put(file.getName(), fis);
							} else if (file.getName().endsWith(".zip")) { //$NON-NLS-1$
								ZipInputStreamIFileFriendly zip = new ZipInputStreamIFileFriendly(fis);
							ZipEntry entry = zip.getNextEntry();
							if(entry == null){
								throw new InvocationTargetException(new Exception(org.bonitasoft.studio.dependencies.i18n.Messages.zipFileIsCorrupted),org.bonitasoft.studio.dependencies.i18n.Messages.zipFileIsCorrupted);
							}
							while (entry != null) {
								if (entry.getName().endsWith(".jar")) { //$NON-NLS-1$
									monitor.setTaskName(Messages.addingJar + " " + entry.getName()); //$NON-NLS-1$
									libStore.importInputStream(entry.getName(), zip);
								}
								entry = zip.getNextEntry();
							}
							zip.forceClose();
							fis.close();
							}

						} catch (Exception ex) {
							BonitaStudioLog.error(ex);
							throw new InvocationTargetException(ex);
						}
					}

					for(final Entry<String, InputStream> entry : jarsToImportMap.entrySet()){
						Display.getDefault().syncExec(new Runnable() {

							@Override
							public void run() {
								InputStream is = null ;
								try{
									is = entry.getValue()  ;
									libStore.importInputStream(entry.getKey(), is) ;
									is.close() ;
								}catch (Exception e) {
									BonitaStudioLog.error(e) ;
								}
							}
						}) ;
					}
				}

			};

			try {

				progressManager.run(true,false,runnable);
				progressManager.run(true, false, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
					InterruptedException {
						RepositoryManager.getInstance().getCurrentRepository().refresh(monitor) ;
					}
				}) ;
			} catch (InvocationTargetException e1) {
				BonitaStudioLog.error(e1);
				if(e1.getCause() != null && e1.getCause().getMessage() != null){
					MessageDialog.openError(Display.getDefault().getActiveShell(), org.bonitasoft.studio.dependencies.i18n.Messages.importJar,e1.getCause().getMessage());
				}
			} catch (InterruptedException e2) {
				BonitaStudioLog.error(e2);
			}

		}
		return fd.getFileNames();
	}
	
	public void setFileNameFilter(String[] fileNames){
		this.filenames  = fileNames;
	}

}
