package org.bonitasoft.studio.registration;

import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.eclipse.swt.widgets.Display;

public class RegistrationContribution implements IPostStartupContribution {

    public RegistrationContribution() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.application.contribution.IPostStartupContribution#execute()
     */
    public void execute() {
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                new BonitaRegistrationDialog( Display.getDefault().getActiveShell(), new BonitaRegisterWizard()).open();
            }
        });

    }


}
