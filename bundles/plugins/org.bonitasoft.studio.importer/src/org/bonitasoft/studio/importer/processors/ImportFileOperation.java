/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ImportFileOperation implements IRunnableWithProgress {

	private ImporterFactory importerFactory;
	private File fileToImport;
	private List<DiagramFileStore> fileStoresToOpen;

	public List<DiagramFileStore> getFileStoresToOpen() {
		return fileStoresToOpen;
	}

	public ImportFileOperation(ImporterFactory importerFactory,
			File fileToImport) {
		this.importerFactory = importerFactory;
		this.fileToImport = fileToImport;
		this.fileStoresToOpen = new ArrayList<DiagramFileStore>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {
		monitor.beginTask(Messages.importProcessProgressDialog,IProgressMonitor.UNKNOWN);

		final ToProcProcessor processor = importerFactory.createProcessor(fileToImport.getName()) ;
		try {
			processor.createDiagram(fileToImport.toURI().toURL(), monitor)  ;
		} catch (MalformedURLException e) {
			throw new InvocationTargetException(e);
		} catch (Exception e) {
			throw new InvocationTargetException(e);
		}
		handleErrors(processor);
		addFileStoresToOpen(processor);
	}

	protected void handleErrors(final ToProcProcessor processor) {
		if(processor.getErrors().size()>0) {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					String message = Messages.errorWhileImporting_message;
					StringBuilder stringBuilder = new StringBuilder(message);
					for (Object error : processor.getErrors()) {
						stringBuilder.append('\n');
						stringBuilder.append(error.toString());
					}
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title, stringBuilder.toString());
				}
			});
		}
	}

	protected void addFileStoresToOpen(final ToProcProcessor processor)
			throws InvocationTargetException {
		for(IRepositoryFileStore fStore : processor.getDiagramFileStoresToOpen()){
			if(fStore instanceof DiagramFileStore){
				fileStoresToOpen.add((DiagramFileStore) fStore);
			}
		}
		if(processor.getResources() != null){
			for(File f : processor.getResources()){
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(f);
					DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
					DiagramFileStore fileStore = diagramStore.importInputStream(f.getName(), fis);
					if(fileStore instanceof DiagramFileStore){
					    fileStoresToOpen.add(fileStore) ;
					}
					f.delete();
				} catch (FileNotFoundException e) {
					throw new InvocationTargetException(e);
				}finally{
					if(fis != null){
						try {
							fis.close();
						} catch (IOException e) {
							throw new InvocationTargetException(e);
						}
					}
				}
			}
		}
	}



}
