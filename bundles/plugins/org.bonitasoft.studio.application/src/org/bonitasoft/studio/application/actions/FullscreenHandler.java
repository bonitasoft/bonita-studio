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
package org.bonitasoft.studio.application.actions;

import java.lang.reflect.Method;

import org.bonitasoft.studio.application.actions.coolbar.NormalCoolBarHandler;
import org.bonitasoft.studio.application.actions.coolbar.SmallCoolBarHandler;
import org.bonitasoft.studio.common.jface.BonitaSashForm;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class FullscreenHandler extends AbstractHandler implements IHandler {
	
	private static int coolbarSize = 0 ;

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;

		if(part != null && part.getEditorSite().getPage().isPageZoomed()){
			PlatformUtil.restoreWindow(part.getEditorSite().getPage()) ;
			if(coolbarSize > 0 && coolbarSize < 60){
				new SmallCoolBarHandler().execute(null) ;
			}else if(coolbarSize >= 60){
				new NormalCoolBarHandler().execute(null) ;
			}
		}else{
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow() ;
			for(Control c : window.getShell().getChildren()){
				if(((Composite)c).getChildren().length > 0){
					if( (((Composite)c).getChildren())[0] instanceof BonitaSashForm){
						BonitaSashForm sash = (BonitaSashForm) (((Composite)c).getChildren())[0] ;
						coolbarSize = sash.getWeights()[0] ;
					}
				}
			}
			new SmallCoolBarHandler().execute(null) ;
//			if(part instanceof DiagramEditor){
////				FlyoutPaletteComposite paletteComposite = (FlyoutPaletteComposite) ((FigureCanvas) ((DiagramEditor)part).getDiagramGraphicalViewer().getControl()).getParent().getParent() ;
////
////				for(Method m : FlyoutPaletteComposite.class.getDeclaredMethods()){
////					if(m.getName().equals("setState")){
////						m.setAccessible(true) ;
////						try {
////							m.invoke(paletteComposite, new Integer(PaletteViewerPreferences.COLLAPSE_ALWAYS)) ;
////						} catch (Exception e) {
////							BonitaStudioLog.error(e) ;
////						}
////					}
////				}
//			}
			if(part != null && part.getSite() != null){
				PlatformUtil.maximizeWindow(part.getEditorSite().getPage(),part.getEditorSite().getPage().getReference(part)) ;
			}
		}
		return null;
	}


}
