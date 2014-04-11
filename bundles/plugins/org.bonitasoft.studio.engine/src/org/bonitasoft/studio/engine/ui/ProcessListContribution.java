///**
// * Copyright (C) 2009 BonitaSoft S.A.
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
//
//package org.bonitasoft.studio.engine.ui;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.common.repository.RepositoryManager;
//import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
//import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
//import org.bonitasoft.studio.engine.command.RunProcessCommand;
//import org.bonitasoft.studio.engine.i18n.Messages;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.model.process.ProcessPackage;
//import org.eclipse.core.commands.Command;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.jface.action.ContributionItem;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.MenuEvent;
//import org.eclipse.swt.events.MenuListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.widgets.Menu;
//import org.eclipse.swt.widgets.MenuItem;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.commands.ICommandService;
//
///**
// * @author Romain Bioteau
// *
// */
//public class ProcessListContribution extends ContributionItem {
//
//    private ICommandService service ;
//    private DiagramRepositoryStore diagramStore;
//    private static String RUN_COMMAND_ID = "org.bonitasoft.studio.engine.runCommand";
//    private final MenuListener listener = new MenuListener() {
//        @Override
//        public void menuShown(MenuEvent e) {
//
//            for(MenuItem it : menu.getItems()){
//                it.dispose() ;
//            }
//            diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
//            for(final IRepositoryFileStore file : diagramStore.getChildren()){
//                final MenuItem item = new MenuItem(menu, SWT.CASCADE, index);
//                final Menu itemMenu = new Menu(menu);
//                item.setMenu(itemMenu) ;
//                final MainProcess diagram = (MainProcess) file.getContent() ;
//                //                StringBuilder stringBuilder = new StringBuilder();
//                //                stringBuilder.append(diagram.getName());
//                //                stringBuilder.append(" (");//$NON-NLS-1$
//                //                stringBuilder.append(diagram.getVersion());
//                //                stringBuilder.append(")");//$NON-NLS-1$
//                item.setText(file.getDisplayName());
//                for(final EObject p : ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.POOL)){
//                    final MenuItem poolItem = new MenuItem(itemMenu, SWT.NONE, index);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(((AbstractProcess) p).getName());
//                    stringBuilder.append(" (");//$NON-NLS-1$
//                    stringBuilder.append(((AbstractProcess) p).getVersion());
//                    stringBuilder.append(")");//$NON-NLS-1$
//                    poolItem.setText(stringBuilder.toString());
//                    poolItem.addSelectionListener(new SelectionAdapter() {
//                        @Override
//                        public void widgetSelected(SelectionEvent e) {
//                            try {
//                                Command cmd = service.getCommand(RUN_COMMAND_ID) ;
//                                Map<String, Object> parameters = new HashMap<String, Object>() ;
//                                parameters.put(RunProcessCommand.PROCESS, p) ;
//                                ExecutionEvent event = new ExecutionEvent(cmd,parameters,null,null) ;
//                                cmd.executeWithChecks(event) ;
//                            } catch (Exception e1) {
//                                BonitaStudioLog.error(e1);
//                            }
//                        }
//                    });
//                }
//
//            }
//
//            if(menu.getItemCount() == 0){
//                final MenuItem item = new MenuItem(menu, SWT.NONE);
//                item.setText(Messages.noProcessAvailable);
//                item.setEnabled(false);
//            }
//
//        }
//
//        @Override
//        public void menuHidden(MenuEvent e) { }
//    };
//    private Menu menu;
//    private int index;
//
//
//    public ProcessListContribution() {
//        service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
//    }
//
//    /**
//     * @param id
//     */
//    public ProcessListContribution(String id) {
//        super(id);
//    }
//
//    /* (non-Javadoc)
//     * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
//     */
//    @Override
//    public void fill(final Menu menu, final int index) {
//        if(!menu.equals(this.menu)){
//            if( this.menu != null){
//                this.menu.removeMenuListener(listener);
//            }
//            this.menu = menu;
//            this.index = index;
//        }
//        this.menu.addMenuListener(listener);
//    }
//
//
//
//}
