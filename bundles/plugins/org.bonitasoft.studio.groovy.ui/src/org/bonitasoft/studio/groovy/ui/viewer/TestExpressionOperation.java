/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.viewer;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.process.IllegalProcessStateException;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class TestExpressionOperation implements IRunnableWithProgress {

    private static final String TEST_EXPRESSION_POOL = "TEST_EXPRESSION_POOL";
    private Serializable result;
    private Map<String, Serializable> inputValues = new HashMap<String, Serializable>();
    private org.bonitasoft.studio.model.expression.Expression expression;
    private Set<IRepositoryFileStore> additionalJars;

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(expression);

        APISession session = null;
        ProcessAPI processApi = null;
        long procId = -1;
        try {
            session = BOSEngineManager.getInstance().loginDefaultTenant(monitor);
            processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            Assert.isNotNull(processApi);
            final AbstractProcess proc = createAbstractProcess();

            final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
            configuration.setName("TestExpressionConfiguration");
            new ConfigurationSynchronizer(proc, configuration).synchronize();
            for (final FragmentContainer fc : configuration.getProcessDependencies()) {
                if (additionalJars != null && FragmentTypes.OTHER.equals(fc.getId())) {
                    final IFolder libFolder = RepositoryManager.getInstance().getCurrentRepository().getProject().getFolder("lib");
                    if (libFolder.exists()) {
                        for (final IResource f : libFolder.members()) {
                            if (f instanceof IFile && ((IFile) f).getFileExtension() != null && ((IFile) f).getFileExtension().equalsIgnoreCase("jar")
                                    && isSelectedJar(((IFile) f).getName())) {
                                final Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
                                fragment.setExported(true);
                                fragment.setKey(f.getName());
                                fragment.setValue(f.getName());
                                fragment.setType(FragmentTypes.JAR);
                                fc.getFragments().add(fragment);
                            }
                        }
                    }
                }
                if (FragmentTypes.GROOVY_SCRIPT.equals(fc.getId())) {
                    final GroovyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class);
                    final List<GroovyFileStore> fileStores = store.getChildren();
                    for (final IRepositoryFileStore fileStore : fileStores) {
                        final String name = fileStore.getName();
                        final Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment();
                        newFragment.setType(FragmentTypes.GROOVY_SCRIPT);
                        newFragment.setKey(name);
                        newFragment.setValue(name);
                        newFragment.setExported(true);
                        fc.getFragments().add(newFragment);
                    }
                }
            }

            final BusinessArchive businessArchive = BarExporter.getInstance().createBusinessArchive(proc, configuration);

            undeployProcess(proc, processApi);
            final ProcessDefinition def = processApi.deploy(businessArchive);
            procId = def.getId();
            processApi.enableProcess(procId);
            expression.setReturnType(Object.class.getName());
            result = processApi.evaluateExpressionOnProcessDefinition(EngineExpressionUtil.createExpression(expression), inputValues, procId);
        } catch (final Exception e) {
            result = e;
        } finally {
            if (processApi != null && procId != -1) {
                try {
                    processApi.disableProcess(procId);
                    processApi.deleteProcessDefinition(procId);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
    }

    private boolean isSelectedJar(final String fileName) {
        for (final IRepositoryFileStore fileStore : additionalJars) {
            if (fileStore.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private AbstractProcess createAbstractProcess() {
        final AbstractProcess proc = ProcessFactory.eINSTANCE.createPool();
        proc.setName(TEST_EXPRESSION_POOL);
        proc.setVersion("1.0");
        return proc;
    }

    public Serializable getResult() {
        return result;
    }

    public void setExpression(final org.bonitasoft.studio.model.expression.Expression expression) {
        this.expression = expression;
    }

    protected void undeployProcess(final AbstractProcess process, final ProcessAPI processApi) throws InvalidSessionException,
            ProcessDefinitionNotFoundException, IllegalProcessStateException, DeletionException {
        final long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos();
        if (nbDeployedProcesses > 0) {
            final List<ProcessDeploymentInfo> processes = processApi.getProcessDeploymentInfos(0, (int) nbDeployedProcesses,
                    ProcessDeploymentInfoCriterion.DEFAULT);
            for (final ProcessDeploymentInfo info : processes) {
                if (info.getName().equals(process.getName()) && info.getVersion().equals(process.getVersion())) {
                    try {
                        processApi.disableProcess(info.getProcessId());
                    } catch (final ProcessActivationException e) {

                    }
                    processApi.deleteProcessDefinition(info.getProcessId());
                }
            }
        }
    }

    public void setContextMap(final Map<String, Serializable> variableMap) {
        inputValues = variableMap;
    }

    public Set<IRepositoryFileStore> getAdditionalJars() {
        return additionalJars;
    }

    public void setAdditionalJars(final Set<IRepositoryFileStore> additionalJars) {
        this.additionalJars = additionalJars;
    }

}
