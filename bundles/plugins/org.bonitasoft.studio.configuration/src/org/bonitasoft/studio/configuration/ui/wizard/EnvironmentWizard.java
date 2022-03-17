/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.ui.wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.configuration.repository.LocalEnvironmentFileStore;
import org.bonitasoft.studio.configuration.ui.wizard.page.EnvironmentWizardPage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 */
public class EnvironmentWizard extends Wizard {

    private final List<Environment> environmentsWorkingCopy;
    private final ArrayList<Environment> environments;
    private final EnvironmentRepositoryStore environmentStore;
    private Optional<ISelection> selection = Optional.empty();

    public EnvironmentWizard() {
        setWindowTitle(Messages.manageEnvironmentTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        environments = new ArrayList<>();
        environmentsWorkingCopy = new ArrayList<>();
        environmentStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(EnvironmentRepositoryStore.class);

        environmentStore.getChildren().stream()
                .filter(env -> !(env instanceof LocalEnvironmentFileStore))
                .map(t -> {
                    try {
                        return t.getContent();
                    } catch (ReadFileStoreException e) {
                       return null;
                    }
                })
                .peek(environments::add)
                .map(EcoreUtil::copy)
                .forEach(environmentsWorkingCopy::add);
    }

    @Override
    public void addPages() {
        addPage(new EnvironmentWizardPage(environmentsWorkingCopy, selection));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        try {
            getContainer().run(true, false, arg0 -> {
                try {
                    for (final Environment env1 : environmentsWorkingCopy) {
                        if (!env1.getName().equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) {
                            IRepositoryFileStore file = environmentStore.getChild(env1.getName() + ".xml", true);
                            if (file == null) {
                                file = environmentStore.createRepositoryFileStore(env1.getName() + ".xml");
                            }
                            file.save(env1);
                        }
                    }
                    for (final Environment e1 : environments) {
                        boolean exists = false;
                        for (final Environment env2 : environmentsWorkingCopy) {
                            if (env2.getName().equals(e1.getName())) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            final IRepositoryFileStore f = environmentStore.getChild(e1.getName() + ".xml", true);
                            f.delete();
                        }
                    }

                } catch (final Exception e2) {
                    BonitaStudioLog.error(e2);
                }

            });
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            return false;
        }

        return true;
    }

    public void setSelection(ISelection selection) {
        this.selection = Optional.ofNullable(selection);
    }

}
