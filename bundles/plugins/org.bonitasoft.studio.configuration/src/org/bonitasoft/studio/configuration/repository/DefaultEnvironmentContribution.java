/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.repository;

import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.i18n.Messages;

/**
 * @author Romain Bioteau
 *
 */
public class DefaultEnvironmentContribution implements IFileStoreContribution {

	private static final String QA_ENVIRONMENT_ID = "Qualification";
	private static final String PROD_ENVIRONMENT_ID = "Production";
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#appliesTo(org.bonitasoft.studio.common.repository.model.IRepositoryFileStore)
	 */
	@Override
	public boolean appliesTo(IRepositoryStore<?> repository) {
		return repository instanceof EnvironmentRepositoryStore;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#execute()
	 */
	@Override
	public void execute(IRepositoryStore<?> repository) {
		IRepositoryFileStore file = repository.createRepositoryFileStore(QA_ENVIRONMENT_ID+".xml") ;
		Environment env = EnvironmentFactory.eINSTANCE.createEnvironment() ;
		env.setName(QA_ENVIRONMENT_ID) ;
		env.setDescription(Messages.qualificationEnvironmentDescription) ;
		file.save(env) ;
		
		IRepositoryFileStore prodFile = repository.createRepositoryFileStore(PROD_ENVIRONMENT_ID+".xml") ;
		Environment prodEnv = EnvironmentFactory.eINSTANCE.createEnvironment() ;
		prodEnv.setName(PROD_ENVIRONMENT_ID) ;
		prodEnv.setDescription(Messages.prodEnvironmentDescription) ;
		prodFile.save(prodEnv) ;
	}

}
