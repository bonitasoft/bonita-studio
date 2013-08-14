package org.bonitasoft.studio.connectors.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.SelectConnectorImplementationWizard;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.operation.TestConnectorOperation;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ui.TestConnectorResultDialog;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestConnectorUtil {

	public static boolean testConnectorWithConfiguration(
			final ConnectorConfiguration configuration,
			final String connectorDefId,
			final String connectorDefVersion,
			final Connector connector,
			final Shell shell,
			IWizardContainer wd ) {
		IImplementationRepositoryStore implStore = getImplementationStore();
		final List<ConnectorImplementation> implementations =  implStore.getImplementations(connectorDefId,connectorDefVersion);

		ConnectorImplementation impl = null ;
		if(implementations.isEmpty()){
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.noImplementationFoundTitle,Messages.bind(Messages.noImplementationFoundMsg,connectorDefId+"-"+connectorDefVersion)) ;
				}
			}) ;
			return false;
		}else if(implementations.size() == 1){
			impl = implementations.get(0);
		}else{
			impl = openImplementationSelection(connectorDefId, connectorDefVersion) ;
			if(impl == null){
				return false;
			}
		}
		
		final Set<String> jars = TestConnectorOperation.checkImplementationDependencies(impl, Repository.NULL_PROGRESS_MONITOR);
		final ManageConnectorJarDialog jd = new ManageConnectorJarDialog(shell,Messages.connectorAdditionalDependencyTitle,Messages.connectorAdditionalDependencyMessage) ;
		jd.setFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if(element instanceof IRepositoryFileStore){
					if(jars.contains(((IRepositoryFileStore) element).getName())){
						return false;
					}
				}
				return true;
			}
		});
		int retCode =jd.open();
		if(retCode == Window.OK){
			TestConnectorOperation operation = new TestConnectorOperation() ;
			operation.setImplementation(impl) ;
			operation.setConnectorConfiguration(configuration) ;
			operation.setConnectorOutput(connector);
			operation.setAdditionalJars(jd.getSelectedJars());
			Object result = null ;
			try {
				wd.run(true, false, operation) ;
				if(operation.getStatus().isOK()){
					result = operation.getResult() ;
				}else{
					if(operation.getStatus().getSeverity() == IStatus.WARNING){
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.testConnectorTitle, operation.getStatus().getMessage());
					}else{
						MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.testConnectorTitle, operation.getStatus().getMessage());
					}
					
				}
			} catch (InvocationTargetException e) {
				result = e ;
				BonitaStudioLog.error(e) ;
			} catch (InterruptedException e) {
				result = e ;
				BonitaStudioLog.error(e) ;
			}
			if(result != null){
				TestConnectorResultDialog dialog = new TestConnectorResultDialog(Display.getDefault().getActiveShell(), result) ;
				dialog.open() ;
			}
		}
		return false; //Keep wizard open on after test operation
	}

	
	protected static IImplementationRepositoryStore getImplementationStore() {
		return (IImplementationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
	}
	
	protected static ConnectorImplementation openImplementationSelection(String defId,String defVersion) {
        SelectConnectorImplementationWizard wizard = new SelectConnectorImplementationWizard(defId,defVersion) ;
        WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard ) ;
        if(dialog.open() == Dialog.OK){
            return  wizard.getConnectorImplementation() ;
        }
        return null;
    }
	
}
