/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.wizard;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Mickael Istria
 *
 */
public class OpenDiagramWizardPage extends WizardPage implements IWizardPage {

    protected FilteredTree ifileTree;
    private Text processLocationText;
    private Button removeProcessButton;
    private final ProcessConfigurationRepositoryStore processConfStore;
    private final ApplicationResourceRepositoryStore applicationResourceStore;

    /**
     * @param openProcessWizard
     * @param example
     * 
     */
    public OpenDiagramWizardPage() {
        super(Messages.openProcessWizardPage_title);
        setTitle(Messages.openProcessWizardPage_title);
        setDescription(Messages.openProcessWizardPage_desc);
        setImageDescriptor(Pics.getWizban());
        processConfStore =  (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class) ;
        applicationResourceStore =  (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, true));
        //Composite repositoryComposite = new Composite(composite, SWT.NONE);
        ifileTree = new FilteredTree(composite, SWT.MULTI | SWT.BORDER, new PatternFilter(), false);
        ifileTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 250).create());
        ifileTree.getViewer().setContentProvider(new DiagramTreeContentProvider());
        
        
        final LabelProvider fileStoreLabelProvider = new FileStoreLabelProvider();
        ifileTree.getViewer().setLabelProvider(new DiagramLabelProvider(fileStoreLabelProvider));
        ifileTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                updateRemoveButton();
                setPageComplete(isPageComplete());
            }

        });
        ifileTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent arg0) {
                if (getWizard().canFinish()) {
                    if(getWizard().performFinish()){
                        ((WizardDialog)getContainer()).close();
                    }
                }

            }
        }) ;


        removeProcessButton = new Button(composite,SWT.FLAT);
        GridData gd = new GridData();
        gd.widthHint = 90 ;
        removeProcessButton.setLayoutData(gd);
        removeProcessButton.setText(Messages.removeProcessLabel);
        removeProcessButton.addSelectionListener(new SelectionListener() {

        	public void widgetSelected(SelectionEvent e) {
        		if (ifileTree.isEnabled() && !ifileTree.getViewer().getSelection().isEmpty()) {
        			try {
        				List<DiagramFileStore> files = getDiagrams();
        				StringBuilder stringBuilder = new StringBuilder(files.size()==1?"":"\n");
        				for (DiagramFileStore file : files) {
        					stringBuilder.append(file.getName());
        					stringBuilder.append("\n");
        				}
        				if (MessageDialog.openQuestion(composite.getShell(), Messages.confirmProcessDeleteTitle,
        						NLS.bind(Messages.confirmProcessDeleteMessage,stringBuilder.toString()))) {
        					for (DiagramFileStore file : files) {
        						for(AbstractProcess process : file.getProcesses()){
        							String uuid = ModelHelper.getEObjectID(process) ;
        							IRepositoryFileStore resourceFolder = applicationResourceStore.getChild(uuid) ;
        							if(resourceFolder != null){
        								resourceFolder.delete() ;
        							}
        							IRepositoryFileStore confFile =  processConfStore.getChild(uuid+"."+ProcessConfigurationRepositoryStore.CONF_EXT) ;
        							if(confFile != null){
        								confFile.delete() ;
        							}
        						}

        						for(IEditorPart editor : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getDirtyEditors() ){
        							if(editor.getEditorInput().getName().equals(file.getName()) ){
        								file.save(editor);
        								break;
        							}
        						}
        						file.close();
        						file.delete() ;
        					}

        					ifileTree.getViewer().setInput(new Object());
        					if(PlatformUtil.isIntroOpen()){
        						PlatformUtil.closeIntro();
        						PlatformUtil.openIntro();
        					}
        				}
        			} catch (Exception e1) {
        				BonitaStudioLog.error(e1);
        			}
        		}
        	}

            public void widgetDefaultSelected(SelectionEvent e) {

            }
        });

        updateRemoveButton();

        // Separator
        Composite blank = new Composite(composite, SWT.NONE);
        blank.setLayoutData(new GridData(SWT.DEFAULT, 40));

        ifileTree.getViewer().setInput(new Object());


        setWorkspaceThingsEnabled(true);
        setControl(composite);
    }


    protected void updateRemoveButton() {
        if (ifileTree.isEnabled() && !ifileTree.getViewer().getSelection().isEmpty()) {
            removeProcessButton.setEnabled(true);
        }else{
            removeProcessButton.setEnabled(false);
        }
    }

    private void setWorkspaceThingsEnabled(boolean enabled) {
        ifileTree.getFilterControl().setEnabled(enabled);
        ifileTree.getViewer().getTree().setEnabled(enabled);
    }


    @Override
    public boolean isPageComplete() {
        return getDiagrams() != null;
    }

    /**
     * @return
     */
    public boolean useIFile() {
        return !processLocationText.isEnabled();
    }

    public List<DiagramFileStore> getDiagrams() {
        if (!ifileTree.getViewer().getSelection().isEmpty()) {
            return ((ITreeSelection)ifileTree.getViewer().getSelection()).toList();
        }
        return null;
    }

}
