package org.bonitasoft.studio.common.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.repository.ui.wizard.ExportRepositoryWizard;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class CommonRepositoryPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.common.repository"; //$NON-NLS-1$

    // The shared instance
    private static CommonRepositoryPlugin plugin;

    /**
     * The constructor
     */
    public CommonRepositoryPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static CommonRepositoryPlugin getDefault() {
        return plugin;
    }

    /**
     * open a wizard in which you can select a destination + which artifact you want to export
     * 
     * @param stores
     *   repositories or artifacts you want to put in the tree
     * @param showImages
     * 	Whether to show icons on repositories/artifacts or not
     * @param selectAllByDefault
     */
    public static void exportArtifactsToFile(List<IRepositoryStore<? extends IRepositoryFileStore>> stores, Set<Object> selectFilesByDefault, String dialogTitle) {
        if(dialogTitle == null || dialogTitle.isEmpty()){
            dialogTitle = Messages.exportRepositoryFileTitle ;
        }
        ExportRepositoryWizard wizard = new ExportRepositoryWizard(stores,false,selectFilesByDefault,null,dialogTitle);
        WizardDialog dialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard){
        	protected void initializeBounds() {
        		super.initializeBounds();
        		getShell().setSize(600, 500); 
        	}
        };
        dialog.setTitle(dialogTitle);
        dialog.open() ;
    }

    public static CommonRepositoryPlugin getPlugin(){
        return plugin ;
    }

    public static String getCurrentRepository(){
        if(CommonRepositoryPlugin.getDefault() != null){
            String defaultRepo = CommonRepositoryPlugin.getDefault().getPreferenceStore()
                    .getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
            return defaultRepo == null || defaultRepo.isEmpty() ? Messages.defaultRepositoryName : defaultRepo;
        }
        return Messages.defaultRepositoryName;
    }


    public static void setCurrentRepository(String repositoryName) {
        CommonRepositoryPlugin.getDefault().getPreferenceStore().setValue(RepositoryPreferenceConstant.CURRENT_REPOSITORY, repositoryName);
    }

    public void openErrorDialog(Shell shell, String errorMessage, Throwable t) {
        final Status status = createErrorStatus(t);
        Platform.getLog(Platform.getBundle(PLUGIN_ID)).log(status);
        new ErrorDialog(shell, Messages.errorTitle, errorMessage,
                status, IStatus.ERROR).open();
    }

    private Status createErrorStatus(Throwable t) {
        Throwable exception = InvocationTargetException.class.isInstance(t)
                ? ((InvocationTargetException) t).getTargetException() : t;
        return new Status(IStatus.ERROR, PLUGIN_ID, exception.getMessage(), exception);
    }


}
