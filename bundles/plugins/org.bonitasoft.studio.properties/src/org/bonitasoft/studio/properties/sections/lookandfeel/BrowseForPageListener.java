package org.bonitasoft.studio.properties.sections.lookandfeel;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore.ResourceType;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
final class BrowseForPageListener extends LookAndFeelPropertySectionListener implements Listener {
	
	private ResourceType type;
	private Label isSetLabel;
	private EStructuralFeature feature;
	private IRepositoryStore resourceStore;

	public BrowseForPageListener(ResourceType type, EStructuralFeature eStructuralFeature, Label isSetLabel) {
		this.type = type;
		this.isSetLabel = isSetLabel;
		this.feature = eStructuralFeature;
		this.resourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
	}
	
	public void handleEvent(Event event) {
		FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
		fd.setFilterExtensions(new String[] { "*.html", "*.htm", "*.*" });
		String res = fd.open();
		if (res != null) {
			String processUUID = ModelHelper.getEObjectID(getProcess()) ;
			ApplicationResourceFileStore artifact =(ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
			if (artifact == null) {
				artifact = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(processUUID);
			}
			res = artifact.setApplicationResource(type, res);
			LookAndFeelPropertySection.setPathIsFilled(isSetLabel, true);
			AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
			af.setPath(res);
			getEditDomain().getCommandStack().execute(new SetCommand(getEditDomain(), getProcess(), feature, af));
		}
	}
}