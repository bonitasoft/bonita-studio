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
package org.bonitasoft.studio.exporter.extension;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressService;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 *
 */
public class ExporterFactoryContributions extends ContributionItem {


	protected static final String PRIORITY = "priority";
	private List<Image> images;
	private List<MenuItem> items;

	public ExporterFactoryContributions() {
		images = new ArrayList<Image>() ;
		items = new ArrayList<MenuItem>() ;
	}

	/**
	 * @param id
	 */
	public ExporterFactoryContributions(String id) {
		super(id);
	}

	@Override
	public void fill(final Menu menu, int index) {
		menu.addMenuListener(new MenuAdapter() {
			
			public void menuShown(MenuEvent e) {
				boolean enable = isEnabled() ;
				recreateIfDisposed(menu);			
				for(MenuItem item : items){
					if(!item.isDisposed()){
						item.setEnabled(enable) ;
					}
				}
			}

			/**
			 * It seem sthat MenuManager can handle only one dynamic menu contribution in update. And so it disposes others MenuItem.
			 * So in this case we have to recreate the menus... Yes that is ugly but it works.
			 * @param menu
			 */
			private void recreateIfDisposed(final Menu menu) {
				boolean needToRecreateAll = false;
				for(MenuItem item : items){
					if(item.isDisposed()){
						needToRecreateAll = true;
						break;
					}
				}
				if(needToRecreateAll){
					for(MenuItem item : items){
						if(!item.isDisposed()){
							item.dispose();
						}
					}
					items.clear();
					addExportAsImage(menu);
					addContributions(menu);
				}
			}
	
		})  ;
		
		
		addExportAsImage(menu);
		
		addContributions(menu);
	}

	private void addExportAsImage(Menu menu) {
		MenuItem exportAsImage = new MenuItem(menu, SWT.NONE) ;
		exportAsImage.setText(Messages.exportAsImage) ;
		exportAsImage.setImage(Pics.getImage("menuIcons/save-as-image.png")) ;
		items.add(exportAsImage) ;
		
		exportAsImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
				Command c = service.getCommand("org.bonitasoft.studio.diagram.custom.saveasimage") ;
				if(c != null && c.isEnabled()){
					try {
						c.executeWithChecks(new ExecutionEvent()) ;
					} catch (Exception e1) {
						BonitaStudioLog.error(e1) ;
					}
				}
			}
		}) ;
	}

	protected void addContributions(Menu menu) {
		IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.exporter.exporterFactory") ;
		List<IConfigurationElement> sortedElements = new ArrayList<IConfigurationElement>();
		for(IConfigurationElement elem : elems){
			sortedElements.add(elem) ;
		}
		Collections.sort(sortedElements, new Comparator<IConfigurationElement>() {

			public int compare(IConfigurationElement e1, IConfigurationElement e2) {
				int	p1 = 0; 
				int p2 = 0 ;
				try{
					p1 = Integer.parseInt(e1.getAttribute(PRIORITY)); 
				}catch (NumberFormatException e) {
					p1 = 0 ;
				}
				try{
					p2 = Integer.parseInt(e2.getAttribute(PRIORITY)); 
				}catch (NumberFormatException e) {
					p2 = 0 ;
				}
				return  p1 -p2; //Lowest Priority first
			}

		}) ;


		for(IConfigurationElement e : sortedElements ){
			try{	
				final IBonitaTransformer transformer = (IBonitaTransformer) e.createExecutableExtension("class") ;

				String menuLabel = e.getAttribute("menuLabel") ;
				String menuIcon = e.getAttribute("menuIcon") ;
				Image icon = null ;
				if(menuIcon != null){
					Bundle b = Platform.getBundle(e.getContributor().getName()) ;
					URL iconURL = b.getResource(menuIcon) ;
					File imageFile = new File(FileLocator.toFileURL(iconURL).getFile())  ;
					FileInputStream fis =	new FileInputStream(imageFile) ;
					icon = new Image(Display.getDefault(), fis) ;
					images.add(icon) ;
					fis.close() ;

				}
				MenuItem item = new MenuItem(menu, SWT.NONE);
				items.add(item) ;
				item.setText(menuLabel+"...") ;
				if(icon != null){
					item.setImage(icon) ;
				}
				final String ext = e.getAttribute("targetExtension") ;
		
				item.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
						
						if (editor == null || !(editor instanceof ProcessDiagramEditor)) {
							return ;
						}
						final MainProcessEditPart part = (MainProcessEditPart) ((ProcessDiagramEditor)editor).getDiagramEditPart() ;
						FileDialog dialog =  new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE) ;
						dialog.setFilterExtensions(new String[]{"*."+ext}) ;
						dialog.setFileName(((Element)part.resolveSemanticElement()).getName()) ;
						String target = null;
						if((target = dialog.open()) != null){
							final File destFile = new File(target) ;
							boolean overwrite = false ;
							if(destFile.exists()){
								if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.overwriteBPMNFile_title, Messages.bind(Messages.overwriteBPMNFile_message,destFile.getName()))){
									overwrite = true ;
								}
							}

							if(!destFile.exists() || overwrite){
								IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
								try {
									service.run(false, false, new IRunnableWithProgress() {

										public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
											monitor.beginTask(Messages.exportingTo+" "+destFile.getName()+"...", IProgressMonitor.UNKNOWN) ;
											if(transformer.transform(new BonitaModelExporterImpl(part), destFile,monitor)){
												MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportSuccessfulTitle, Messages.exportSuccessfulMessage +SWT.CR+transformer.getErrorMessage()) ;
											}else{
												MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailedTitle,  Messages.exportFailedMessage+SWT.CR+transformer.getErrorMessage()) ;
											}
										}
									});
								} catch (Exception e1) {
									BonitaStudioLog.error(e1) ;
									new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.exportFailedTitle, Messages.exportFailedMessage,e1).open() ;
								} 
							}
						}
					}


				});
			}catch (Exception ex) {
				BonitaStudioLog.error(ex) ;
			}
		}
	}

	@Override
	public boolean isEnabled() {
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return editor != null && editor instanceof ProcessDiagramEditor ;
	}

	
	@Override
	public void dispose() {
		super.dispose();
		for(Image i : images){
			i.dispose() ;
		}
		for(MenuItem i : items){
			i.dispose() ;
		}
	}

}
