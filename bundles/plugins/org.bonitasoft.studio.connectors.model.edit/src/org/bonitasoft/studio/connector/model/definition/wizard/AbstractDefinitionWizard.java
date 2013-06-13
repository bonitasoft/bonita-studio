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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractDefinitionWizard extends ExtensibleWizard {

    private static final String DEF_EXT = "def";
    private boolean editMode = false;
    private IRepositoryFileStore fileStore;
    private final ConnectorDefinition definitionWorkingCopy;
    private DefinitionInformationWizardPage infoPage;
    private final Properties messages ;
    private ConnectorDefinition originalDefinition;
    private DefinitionI18NWizardPage i18nPage;
    private final DefinitionResourceProvider messageProvider;
    private final IRepositoryStore defStore;

    public AbstractDefinitionWizard(String windowTitle,IRepositoryStore defStore,DefinitionResourceProvider messageProvider){
        Assert.isTrue(defStore instanceof IDefinitionRepositoryStore) ;
        setWindowTitle(windowTitle) ;
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
        definitionWorkingCopy = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition() ;
        definitionWorkingCopy.setVersion("1.0.0") ;
        this.defStore = defStore ;
        this.messageProvider = messageProvider ;
        messages = new Properties() ;
    }

    public AbstractDefinitionWizard(String windowTitle,ConnectorDefinition definition,IRepositoryStore defStore,DefinitionResourceProvider messageProvider){
        Assert.isTrue(defStore instanceof IDefinitionRepositoryStore) ;
        setWindowTitle(windowTitle) ;
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
        editMode  = true ;
        originalDefinition = definition ;
        this.defStore = defStore ;
        fileStore = defStore.getChild(NamingUtils.toConnectorDefinitionFilename(definition.getId(),definition.getVersion(),true)) ;
        definitionWorkingCopy = EcoreUtil.copy(definition) ;
        this.messageProvider = messageProvider ;
        messages = messageProvider.getDefaultMessageProperties(definition) ;
    }

    @Override
    public void addPages() {
        final List<ConnectorDefinition> existinfDefinitions = new ArrayList<ConnectorDefinition>() ;
        for(ConnectorDefinition def : ((IDefinitionRepositoryStore) defStore).getDefinitions()){
            if(originalDefinition == null || (!(def.getId().equals(originalDefinition.getId()) && def.getVersion().equals(originalDefinition.getVersion())))){
                existinfDefinitions.add(def) ;
            }
        }

        infoPage = new DefinitionInformationWizardPage(definitionWorkingCopy,messages,existinfDefinitions,Pics.getImage("connector.png"),messageProvider) ;
        if(originalDefinition != null){
            infoPage.setDisplayName(messageProvider.getConnectorDefinitionLabel(originalDefinition)) ;
            infoPage.setDefinitionDescription(messageProvider.getConnectorDefinitionDescription(originalDefinition)) ;
        }
        addPage(infoPage) ;
        addPage(new InputsWizardPage(definitionWorkingCopy)) ;
        addPage(new DefinitionPageWizardPage(definitionWorkingCopy,messages,messageProvider)) ;

        addOutputPage() ;

        i18nPage = new DefinitionI18NWizardPage(definitionWorkingCopy,originalDefinition, messageProvider.getExistingLocale(originalDefinition)) ;
        addPage(i18nPage) ;
    }

    protected void addOutputPage(){
        addPage(new OutputsWizardPage(definitionWorkingCopy)) ;
    }



    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        String defId = NamingUtils.toConnectorDefinitionFilename(definitionWorkingCopy.getId(), definitionWorkingCopy.getVersion(), false) ;
        String defFileName = defId+"."+ DEF_EXT;

        File imageFile = infoPage.getIconImageFile() ;
        if(imageFile != null){
            IFolder targetFoler = defStore.getResource() ;
            IFile iconFile = targetFoler.getFile(definitionWorkingCopy.getIcon()) ;
            try{
                if(iconFile.exists() && !iconFile.getLocation().toFile().getAbsolutePath().equals(imageFile.getAbsolutePath())){
                    iconFile.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
                }
                BufferedImage image = ImageIO.read(imageFile) ;
                image = FileUtil.resizeImage(image,16) ;
                ImageIO.write(image, "PNG", iconFile.getLocation().toFile()) ;
            }catch (Exception ex) {
                BonitaStudioLog.error(ex) ;
                try {
                    iconFile.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
                } catch (CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
        if(editMode){
            String oldDefId =  NamingUtils.toConnectorDefinitionFilename(originalDefinition.getId(),originalDefinition.getVersion(),false) ;
            if(!oldDefId.equals(defId)){
                String oldId = oldDefId+".properties" ;
                try {
                    defStore.getResource().getFile(oldId).delete(true, Repository.NULL_PROGRESS_MONITOR) ;
                } catch (CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
            Set<Locale> existingLocales = messageProvider.getExistingLocale(originalDefinition) ;
            for(Locale l : existingLocales){
                try {
                    for(IResource r : defStore.getResource().members()){
                        if(r.getFileExtension() != null && r.getFileExtension().equals("properties")){
                            String resourceName = r.getName() ;

                            if(!oldDefId.equals(defId)){
                                String oldLocaleFile = oldDefId+"_"+l.toString()+".properties" ;
                                if(resourceName.equals(oldLocaleFile)){
                                    String newLocaleFile = defId+"_"+l.toString()+".properties" ;
                                    IPath tarhetPath =  r.getFullPath().removeLastSegments(1) ;
                                    tarhetPath = tarhetPath.append(newLocaleFile) ;
                                    r.move(tarhetPath, true, Repository.NULL_PROGRESS_MONITOR) ;
                                }
                            }
                        }
                    }
                } catch (CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }

            if(!fileStore.getName().equals(defFileName)){
                fileStore.delete() ;
            }

        }
        messageProvider.setConnectorDefinitionLabel(messages, infoPage.getDisplayName());
        messageProvider.setConnectorDefinitionDescription(messages, infoPage.getDefinitionDescription()) ;
        messageProvider.saveMessagesProperties(definitionWorkingCopy,messages) ;


        fileStore = defStore.createRepositoryFileStore(defFileName) ;
        fileStore.save(definitionWorkingCopy) ;
        reloadCategories();
        final List<IFile> filesToOpen = openPropertiesEditor(i18nPage.getSelectedLocales()) ;

        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                for(IFile toOpen : filesToOpen){
                    try {
                        IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), toOpen) ;
                    } catch (PartInitException e) {
                        BonitaStudioLog.error(e) ;
                    }
                }
            }
        }) ;

        return true;
    }

    private void reloadCategories() {
        boolean reloadCategories = false ;
        for(Category c : definitionWorkingCopy.getCategory()){
            if(!messageProvider.getAllCategories().contains(c)){
                reloadCategories = true ;
                break;
            }
        }
        if(reloadCategories){
            messageProvider.loadDefinitionsCategories(null);
        }
    }

    protected List<IFile> openPropertiesEditor(Set<Locale> selectedLocales) {
        String defId = NamingUtils.toConnectorDefinitionFilename(definitionWorkingCopy.getId(),definitionWorkingCopy.getVersion(),false);
        List<IFile> filesToOpen = new ArrayList<IFile>() ;
        for(Locale l : selectedLocales){
            try {
                for(final IResource r : defStore.getResource().members()){
                    if(r.getFileExtension() != null && r.getFileExtension().equals("properties")){
                        String resourceName = r.getName() ;
                        String localFile = defId+"_"+l.toString()+".properties" ;
                        if(resourceName.equals(localFile)){
                            Properties oldProperties = new Properties() ;
                            try {
                                oldProperties.load(((IFile)r).getContents()) ;
                                for(Object key : messages.keySet()){
                                    if(oldProperties.get(key) == null){
                                        oldProperties.put(key, messages.get(key)) ;
                                    }
                                }
                                FileOutputStream fos = new FileOutputStream(r.getLocation().toFile()) ;
                                oldProperties.store(fos, null) ;
                                fos.close() ;
                                r.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR) ;
                                filesToOpen.add((IFile) r) ;
                            } catch (IOException e) {
                                BonitaStudioLog.error(e) ;
                            }

                        }
                    }
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e) ;
            }
        }

        Set<Locale> existingLocales = messageProvider.getExistingLocale(definitionWorkingCopy) ;
        selectedLocales.removeAll(existingLocales) ;
        for(Locale l : selectedLocales){
            try {
                String localFile = defId+"_"+l.toString()+".properties" ;
                final IFile file = defStore.getResource().getFile(localFile) ;
                FileOutputStream fos = new FileOutputStream(file.getLocation().toFile()) ;
                messages.store(fos, null) ;
                fos.close() ;
                file.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR) ;
                filesToOpen.add(file) ;
            } catch (Exception e) {
                BonitaStudioLog.error(e) ;
            }
        }
        return filesToOpen ;
    }

    public ConnectorDefinition getOriginalDefinition() {
        return originalDefinition;
    }


}
