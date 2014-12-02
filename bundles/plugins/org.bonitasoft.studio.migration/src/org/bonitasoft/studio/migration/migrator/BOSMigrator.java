/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.migrator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Report;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edapt.common.MetamodelUtils;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.declaration.LibraryImplementation;
import org.eclipse.emf.edapt.declaration.OperationImplementation;
import org.eclipse.emf.edapt.history.reconstruction.EcoreForwardReconstructor;
import org.eclipse.emf.edapt.history.util.HistoryUtils;
import org.eclipse.emf.edapt.internal.declaration.OperationRegistry;
import org.eclipse.emf.edapt.internal.migration.BackupUtils;
import org.eclipse.emf.edapt.internal.migration.MaterializingBackwardConverter;
import org.eclipse.emf.edapt.internal.migration.Persistency;
import org.eclipse.emf.edapt.internal.migration.PrintStreamProgressMonitor;
import org.eclipse.emf.edapt.internal.migration.execution.ClassLoaderFacade;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.MigratorCommandLine;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.internal.migration.execution.WrappedMigrationException;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Delete;
import org.eclipse.emf.edapt.spi.history.History;
import org.eclipse.emf.edapt.spi.history.HistoryPackage;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class BOSMigrator {

	/** Metamodel history no which this migrator is based. */
	private History history;

	/** Mapping of namespace URIs to releases. */
	private HashMap<String, Set<Release>> releaseMap;

	/** Classloader to load {@link CustomMigration}s. */
	private final IClassLoader classLoader;

	/** Validation level. */
    private ValidationLevel level = ValidationLevel.CUSTOM_MIGRATION;

	private BOSReportReconstructor reportReconstructor;

	/** Constructor. */
	public BOSMigrator(final URI historyURI, final IClassLoader classLoader)
			throws MigrationException {
		HistoryPackage.eINSTANCE.getHistory();
		try {
			history = ResourceUtils.loadElement(historyURI);
		} catch (final IOException e) {
			throw new MigrationException("History could not be loaded", e);
		}
        this.classLoader = classLoader;
		init();
	}

	/** Constructor. */
	public BOSMigrator(final History history, final IClassLoader classLoader) {
        this.history = history;
        this.classLoader = classLoader;
		init();
	}

	/** Initialize release map for the migrator. */
	private void init() {
		releaseMap = new HashMap<String, Set<Release>>();
		final Map<EPackage, String> packageMap = new HashMap<EPackage, String>();

		for (final Release release : history.getReleases()) {
			if (!release.isLastRelease()) {
				updatePackages(release, packageMap);
				registerRelease(release, packageMap);
			}
		}
	}

	/** Register a package for a certain release. */
	private void registerRelease(final Release release,
			final Map<EPackage, String> packageMap) {
		for (final Entry<EPackage, String> entry : packageMap.entrySet()) {
			final String nsURI = entry.getValue();
			Set<Release> releases = releaseMap.get(nsURI);
			if (releases == null) {
				releases = new HashSet<Release>();
				releaseMap.put(nsURI, releases);
			}
			releases.add(release);
		}
	}

	/** Update the namespace URIs based on the changes during a release. */
	private void updatePackages(final Release release,
			final Map<EPackage, String> packageMap) {
		for (final Iterator<EObject> i = release.eAllContents(); i.hasNext();) {
			final EObject element = i.next();
            if (element instanceof org.eclipse.emf.edapt.spi.history.Set) {
                final org.eclipse.emf.edapt.spi.history.Set set = (org.eclipse.emf.edapt.spi.history.Set) element;
				if (set.getFeature() == EcorePackage.eINSTANCE
						.getEPackage_NsURI()) {
					final EPackage ePackage = (EPackage) set.getElement();
					final String nsURI = (String) set.getValue();
					packageMap.put(ePackage, nsURI);
				}
			} else if (element instanceof Delete) {
				final Delete delete = (Delete) element;
				packageMap.remove(delete.getElement());
			}
		}
	}

	/**
	 * Migrate a model based on a set of {@link URI}.
	 *
	 * @param modelURIs
	 * @param sourceRelease
	 *            Release to which the model conforms
	 * @param targetRelease
	 *            Release to which the model should be migrated (use null for
	 *            the newest release)
	 * @param monitor
	 *            Progress monitor
	 */
	public void migrateAndSave(final List<URI> modelURIs,
			final Release sourceRelease, final Release targetRelease,
			final IProgressMonitor monitor) throws MigrationException {
		final Model model = migrate(modelURIs, sourceRelease, targetRelease,
				monitor);
		if (model == null) {
			throw new MigrationException("Model is up-to-date", null);
		}
		try {

			Persistency.saveModel(model);
		} catch (final IOException e) {
			throw new MigrationException("Model could not be saved", e);
		}
	}

	/**
	 * Migrate a model based on a set of {@link URI}s and load it afterwards.
	 *
	 * @param modelURIs
	 *            The set of {@link URI}
	 * @param sourceRelease
	 *            Release to which the model conforms
	 * @param targetRelease
	 *            Release to which the model should be migrated (use null for
	 *            the newest release)
	 * @param monitor
	 *            Progress monitor
	 * @return The model in a {@link ResourceSet}
	 */
	public ResourceSet migrateAndLoad(final List<URI> modelURIs,
			final Release sourceRelease, final Release targetRelease,
			final IProgressMonitor monitor) throws MigrationException {
		final Model model = migrate(modelURIs, sourceRelease, targetRelease,
				monitor);
		if (model == null) {
			return null;
		}
		final MaterializingBackwardConverter converter = new MaterializingBackwardConverter();
		return converter.convert(model);
	}

	/** Get the latest release. */
	public Release getLatestRelease() {
		final List<Release> releases = history.getReleases();
		return releases.get(releases.size() - 2);
	}

	/**
	 * Migrate a model based on a set of {@link URI}s.
	 *
	 * @param modelURIs
	 *            The set of {@link URI}
	 * @param sourceRelease
	 *            Release to which the model conforms
	 * @param targetRelease
	 *            Release to which the model should be migrated (use null for
	 *            the newest release)
	 * @param monitor
	 *            Progress monitor
	 * @return The model in the generic structure
	 */
	private Model migrate(final List<URI> modelURIs,
			final Release sourceRelease, Release targetRelease,
			final IProgressMonitor monitor) throws MigrationException {
		try {
			if (targetRelease == null) {
				targetRelease = getLatestRelease();
			}
			if (sourceRelease == targetRelease) {
				return null;
			}

			monitor.beginTask(Messages.migrating,
					numberOfSteps(sourceRelease, targetRelease));
			final EcoreForwardReconstructor reconstructor = new EcoreForwardReconstructor(
					URI.createFileURI("test"));
			reportReconstructor = new BOSReportReconstructor(modelURIs,
					sourceRelease, targetRelease, monitor, classLoader, level);
			reconstructor.addReconstructor(reportReconstructor);
			reconstructor.reconstruct(targetRelease, false);

			final Model model = reportReconstructor.getModel();
			return model;
		} catch (final WrappedMigrationException e) {
			throw e.getCause();
		} finally {
			monitor.done();
		}
	}

	/** Returns the length of the migration in terms of the steps. */
	private int numberOfSteps(final Release sourceRelease,
			final Release targetRelease) {
		int size = 0;
		boolean inRelease = false;
		for (final Release release : history.getReleases()) {
			if (inRelease) {
				size += release.getChanges().size();
			}
			if (release == sourceRelease) {
				inRelease = true;
			}
			if (release == targetRelease) {
				break;
			}
		}
		return size;
	}

	/** Get the release of a model based on {@link URI}. */
	public Set<Release> getRelease(final URI modelURI) {
		final String nsURI = ReleaseUtils.getNamespaceURI(modelURI);
		return releaseMap.containsKey(nsURI) ? releaseMap.get(nsURI)
				: Collections.<Release> emptySet();
	}

	/** Get the release with a certain number. */
	public Release getRelease(final int number) {
		if (number < 0 || number >= history.getReleases().size()) {
			return null;
		}
		return history.getReleases().get(number);
	}

	/** Get all releases. */
	public List<Release> getReleases() {
		final List<Release> releases = new ArrayList<Release>();
		releases.addAll(history.getReleases());
		releases.remove(history.getLastRelease());
		return releases;
	}

	/** Get set of namespace URIs. */
	public Set<String> getNsURIs() {
		return releaseMap.keySet();
	}

	/** Returns the metamodel for a release. */
	public Metamodel getMetamodel(final Release release) {
		final EcoreForwardReconstructor reconstructor = new EcoreForwardReconstructor(
				URI.createFileURI("test"));
		reconstructor.reconstruct(release, false);
		final URI metamodelURI = URI.createFileURI(new File("metamodel."
				+ ResourceUtils.ECORE_FILE_EXTENSION).getAbsolutePath());
		final List<EPackage> rootPackages = ResourceUtils.getRootElements(
				reconstructor.getResourceSet(), EPackage.class);
		final ResourceSet resourceSet = MetamodelUtils
				.createIndependentMetamodelCopy(rootPackages, metamodelURI);
		return Persistency.loadMetamodel(resourceSet);
	}

	/** Set the validation level. */
	public void setLevel(final ValidationLevel level) {
        this.level = level;
	}

	/** Main method to perform migrations. */
	public static void main(final String[] args) {

		final MigratorCommandLine commandLine = new MigratorCommandLine(args);
		final List<URI> modelURIs = commandLine.getModelURIs();
		final int sourceReleaseNumber = commandLine.getSourceReleaseNumber();
		final int targetReleaseNumber = commandLine.getTargetReleaseNumber();

		try {
			for (final Class<? extends LibraryImplementation> library : commandLine
					.getLibraries()) {
				OperationRegistry.getInstance().registerLibrary(library);
			}
			for (final Class<? extends OperationImplementation> operation : commandLine
					.getOperations()) {
				OperationRegistry.getInstance().registerOperation(operation);
			}

			final Migrator migrator = new Migrator(commandLine.getHistoryURI(),
					new ClassLoaderFacade(Thread.currentThread()
							.getContextClassLoader()));
			migrator.setLevel(commandLine.getLevel());

			final Set<Release> releases = migrator.getRelease(modelURIs.get(0));
			Release sourceRelease = null;
			if (sourceReleaseNumber != -1) {
				sourceRelease = HistoryUtils.getRelease(releases,
						sourceReleaseNumber);
			} else {
				sourceRelease = releases.iterator().next();
			}

			if (commandLine.isBackup()) {
				final Metamodel metamodel = migrator
						.getMetamodel(sourceRelease);
				try {
					BackupUtils.backup(modelURIs, metamodel);
				} catch (final IOException e) {
					System.err.println(e.getMessage());
				}
			}

			final Release targetRelease = migrator
					.getRelease(targetReleaseNumber);

			migrator.migrateAndSave(modelURIs, sourceRelease, targetRelease,
					new PrintStreamProgressMonitor(System.out));
		} catch (final MigrationException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getCause().getMessage());
		}
	}

	public Report getReport() {
		return reportReconstructor.getReport();
	}
}
