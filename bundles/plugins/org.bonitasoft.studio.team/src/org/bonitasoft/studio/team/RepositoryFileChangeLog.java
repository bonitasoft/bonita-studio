/*******************************************************************************
 * Copyright (C) 2009, 2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team;

import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.resource.ILocalResource;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Romain Bioteau
 *
 */
public class RepositoryFileChangeLog {

	private final static String COMMIT_COMMENT_GENRATOR = "org.bonitasoft.studio.team.comment";
	private final String comment;

	public RepositoryFileChangeLog(final IRepositoryFileStore file){
		comment = createCommitComment(file) ;
	}

	public String getComment() {
		return comment;
	}

	protected String createCommitComment(final IRepositoryFileStore file) {
		final String comment = generateDefaultCommitComment(file);
		return generateCustomCommentIfExtensionProvided(comment);
	}

	/**
	 * if the extension point org.bonitasoft.studio.team.commitCommentGenerator is provided,
	 * it will use a custom generator
	 *
	 * @param comment
	 * @return
	 */
	private String generateCustomCommentIfExtensionProvided(String comment) {
		try {
			final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(COMMIT_COMMENT_GENRATOR);
			if(extensions.length != 0){
				try {
					final CommitCommentGenerator ccg = (CommitCommentGenerator) extensions[0].createExecutableExtension("providerClass");
					return ccg.generateComment(comment);
				} catch (final CoreException e) {
					BonitaStudioLog.error(e);
				}
			}
		} catch (final Throwable t) {// we don't want the comment generation failed.
			comment += "Error during comment generation.";
			BonitaStudioLog.error(t);
		}
		return comment;
	}

	/**
	 * Generates a default commit comment based on EMF diff
	 * @param file
	 * @return
	 */
	private String generateDefaultCommitComment(final IRepositoryFileStore file) {
		String comment = "";
		try {
			if(file.getResource() != null && file.getResource().exists()){
				comment += "[" + file.getParentStore().getDisplayName() + "] " + file.getName() + " has been updated.";
				if (file instanceof EMFFileStore) {
					comment += "\n" + createCommitCommentForModelArtifact(file);
				}
			}
		} catch (final Throwable t) {// we don't want the comment generation failed.
			comment += "Error during comment generation.";
			BonitaStudioLog.error(t);
		}
		return comment;
	}

	protected String createCommitCommentForModelArtifact(final IRepositoryFileStore file) throws IOException, InterruptedException {
        final IResource resource = file.getResource();
        final ILocalResource local = SVNRemoteStorage.instance().asLocalResourceAccessible(resource);
        final IRepositoryResource remote = local.isCopied() ? SVNUtility.getCopiedFrom(resource) : SVNRemoteStorage.instance().asRepositoryResource(resource);

        if (resource != null && resource.exists() && local.getResource() != null && local.getResource().exists()) {
            final EObject model1 = EMFCompareModelUtils.load(((IFile) (local.getResource())).getLocation().toFile(), new ResourceSetImpl());
            final GetFileContentOperation gfco = new GetFileContentOperation(remote);
            gfco.run(new NullProgressMonitor());
            final InputStream stream = gfco.getContent();
            if (stream.available() > 0) {
                final EObject model2 = EMFCompareModelUtils.load(stream, resource.getName(), new ResourceSetImpl());
                final IComparisonScope scope = new DefaultComparisonScope(model1, model2, null);
                final EMFCompare comparator = createComparator();
                final Comparison comparison = comparator.compare(scope);
                final String generatedComment = BonitaEMFComparePrettyPrinter.getDifferences(comparison);

                stream.close();

                return generatedComment;
            } else {
                return "";
            }
        } else {
            return "";
        }
	}

    protected EMFCompare createComparator() {
    	return EMFCompare.builder().build();
    }

}
