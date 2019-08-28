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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.ClassGenerator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ImportDefinitionDepedenciesOperation;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractImplementationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.ConnectorDefinitionContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.UniqueConnectorDefinitionContentProvider;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorImplementationWizard extends ExtensibleWizard {


	private boolean editMode = false;
	protected IRepositoryFileStore fileStore;
	protected final ConnectorImplementation implWorkingCopy;
	protected ConnectorImplementation originalImpl;
	private IFile fileToOpen;
	protected IRepositoryStore<? extends IRepositoryFileStore> implStore;
	protected SourceRepositoryStore<AbstractFileStore> sourceStore;
	protected IRepositoryStore<? extends IRepositoryFileStore> defStore;
	protected DefinitionResourceProvider messageProvider;

	public ConnectorImplementationWizard(){
		setWindowTitle(Messages.newConnectorImplementation) ;
		setNeedsProgressMonitor(true) ;
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		implWorkingCopy = ConnectorImplementationFactory.eINSTANCE.createConnectorImplementation() ;
		implWorkingCopy.setImplementationVersion("1.0.0") ;
		implWorkingCopy.setJarDependencies(ConnectorImplementationFactory.eINSTANCE.createJarDependencies()) ;
		initialize() ;
	}



	public ConnectorImplementationWizard(ConnectorImplementation implementation){
		setNeedsProgressMonitor(true) ;
		setWindowTitle(Messages.editConnectorImplementation) ;
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		editMode  = true ;
		originalImpl = implementation ;
		implWorkingCopy = EcoreUtil.copy(implementation) ;
		if(implWorkingCopy.getJarDependencies() == null){
			implWorkingCopy.setJarDependencies(ConnectorImplementationFactory.eINSTANCE.createJarDependencies()) ;
		}
		initialize() ;
	}

	@SuppressWarnings("unchecked")
	protected void initialize() {
		implStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class) ;
		if(originalImpl != null){
			fileStore = implStore.getChild(NamingUtils.getEResourceFileName(originalImpl,true)) ;
		}
		defStore =  RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
		sourceStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class) ;
		messageProvider = DefinitionResourceProvider.getInstance(defStore, ConnectorPlugin.getDefault().getBundle()) ;
	}

	@Override
	public void addPages() {
		List<ConnectorImplementation> existingImplementation = ((IImplementationRepositoryStore)implStore).getImplementations() ;
		if(originalImpl != null){
			existingImplementation.clear() ;
			for(ConnectorImplementation impl : ((IImplementationRepositoryStore)implStore).getImplementations()){
				if(!(impl.getImplementationId().equals(originalImpl.getImplementationId()) && impl.getImplementationVersion().equals(originalImpl.getImplementationVersion()))){
					existingImplementation.add(impl) ;
				}
			}
		}



		addPage(getDefinitionSelectionWizardPage(existingImplementation)) ;
		addPage(getImplementationPage(existingImplementation));
	}



	protected IWizardPage getDefinitionSelectionWizardPage(
			List<ConnectorImplementation> existingImplementation) {
		return new AbstractDefinitionSelectionImpementationWizardPage(implWorkingCopy,existingImplementation,((IDefinitionRepositoryStore) defStore).getDefinitions(),getSelectionPageTitle(),getSelectionPageDescription(),messageProvider){

			@Override
			protected ITreeContentProvider getContentProvider() {
				return new UniqueConnectorDefinitionContentProvider();
			}

			@Override
			protected ITreeContentProvider getCustomContentProvider() {
				return new UniqueConnectorDefinitionContentProvider(true);
			}

			@Override
			protected void bindValue() {
				final IViewerObservableValue observeSingleSelection = ViewersObservables.observeSingleSelection(explorer.getRightTableViewer());
				context.bindValue(observeSingleSelection, EMFObservables.observeValue(implementation, ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_ID),defIdStrategy,defModelStrategy) ;
				context.bindValue(ViewersObservables.observeSingleSelection(versionCombo), EMFObservables.observeValue(implementation, ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION));
			}

		};
	}





	protected String getSelectionPageDescription() {
		return Messages.selectConnectorDefinitionForImplDesc;
	}

	protected String getSelectionPageTitle() {
		return Messages.selectConnectorDefinitionTitle;
	}

	protected IWizardPage getImplementationPage(
			List<ConnectorImplementation> existingImplementation) {
		return new AbstractImplementationWizardPage(implWorkingCopy,existingImplementation,((IDefinitionRepositoryStore) defStore).getDefinitions(),sourceStore,getPageTitle(),getPageDescription(),messageProvider){

			@Override
			protected ITreeContentProvider getContentProvider() {
				return new ConnectorDefinitionContentProvider();
			}

		};
	}

	protected String getPageDescription() {
		return Messages.connectorImplementationDesc;
	}

	protected String getPageTitle() {
		return Messages.connectorImplementationTitle;
	}

	@Override
	public boolean canFinish() {
		if(getContainer().getCurrentPage() instanceof AbstractDefinitionSelectionImpementationWizardPage){
			return false;
		}
		return super.canFinish();
	}

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					String implId =  NamingUtils.toConnectorImplementationFilename(implWorkingCopy.getImplementationId(),implWorkingCopy.getImplementationVersion(),false);
					String implFileName = implId+"."+ConnectorImplRepositoryStore.CONNECTOR_IMPL_EXT;

					if(editMode){
						final String qualifiedClassname = implWorkingCopy.getImplementationClassname() ;
						final IRepositoryFileStore file = sourceStore.getChild(ClassGenerator.getAbstractClassName(originalImpl.getImplementationClassname())) ;
						if(file != null){
							file.delete() ;
						}
						if(!originalImpl.getImplementationClassname().equals(implWorkingCopy.getImplementationClassname())){
							SourceFileStore sourceFile = (SourceFileStore) sourceStore.getChild(originalImpl.getImplementationClassname()) ;
							if(sourceFile != null){
								sourceFile.renameLegacy(qualifiedClassname) ;
								try {
									ClassGenerator.updateConnectorImplementationAbstractClassName(implWorkingCopy, ClassGenerator.getAbstractClassName(originalImpl.getImplementationClassname()), sourceFile, monitor);
								} catch (Exception e) {
									BonitaStudioLog.error(e);
								}
							}
						}

						if(!fileStore.getName().equals(implFileName)){
							fileStore.delete() ;
						}
					}

					fileStore = implStore.createRepositoryFileStore(implFileName) ;
					fileStore.save(implWorkingCopy) ;

					try {
						ConnectorDefinition definition = ((IDefinitionRepositoryStore) defStore).getDefinition(implWorkingCopy.getDefinitionId(),implWorkingCopy.getDefinitionVersion()) ;
                        new ImportDefinitionDepedenciesOperation(definition, messageProvider,
                                RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class))
                                        .run(monitor);
						ClassGenerator.generateConnectorImplementationAbstractClass(implWorkingCopy,definition,getAbstractClassName(),sourceStore, monitor) ;
						fileToOpen = ClassGenerator.generateConnectorImplementationClass(implWorkingCopy,definition,sourceStore, monitor) ;
					} catch (Exception e) {
						BonitaStudioLog.error(e) ;
					}

					monitor.done() ;
				}
			});
		} catch (Exception e){
			BonitaStudioLog.error(e) ;
		}

		if(fileToOpen != null){
			BusyIndicator.showWhile(Display.getDefault(),new Runnable() {
				@Override
				public void run() {
					//need to get the acive page from the UI shell
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IEditorPart part = IDE.openEditor(page, new FileEditorInput(fileToOpen), "org.eclipse.jdt.ui.CompilationUnitEditor");
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveEditor(part, false);
					} catch (PartInitException e) {
					}
				}
			});
		}

		return true;
	}

	protected String getAbstractClassName() {
		return AbstractConnector.class.getName();
	}

	public ConnectorImplementation getOriginalImplementation() {
		return originalImpl;
	}


}
