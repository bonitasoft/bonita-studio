/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.bonitasoft.studio.migration.migrator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edapt.common.MetamodelExtent;
import org.eclipse.emf.edapt.common.MetamodelUtils;
import org.eclipse.emf.edapt.common.ResourceSetFactoryImpl;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.declaration.OperationImplementation;
import org.eclipse.emf.edapt.history.reconstruction.EcoreReconstructorSwitchBase;
import org.eclipse.emf.edapt.history.reconstruction.FinishedException;
import org.eclipse.emf.edapt.history.reconstruction.Mapping;
import org.eclipse.emf.edapt.history.reconstruction.ReconstructorBase;
import org.eclipse.emf.edapt.history.reconstruction.ResolverBase;
import org.eclipse.emf.edapt.internal.migration.Persistency;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.OperationInstanceConverter;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.internal.migration.execution.WrappedMigrationException;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.history.Add;
import org.eclipse.emf.edapt.spi.history.Change;
import org.eclipse.emf.edapt.spi.history.Create;
import org.eclipse.emf.edapt.spi.history.Delete;
import org.eclipse.emf.edapt.spi.history.MigrationChange;
import org.eclipse.emf.edapt.spi.history.Move;
import org.eclipse.emf.edapt.spi.history.OperationChange;
import org.eclipse.emf.edapt.spi.history.OperationInstance;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edapt.spi.history.Remove;
import org.eclipse.emf.edapt.spi.history.Set;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.Repository;

/**
 * A reconstructor that perform the migration of models from a source release to
 * a target release.
 *
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 306 $
 * @levd.rating RED Rev:
 */
public class MigrationReconstructor extends ReconstructorBase {

	/** Source release. */
	protected final Release sourceRelease;

	/** Target release. */
	protected final Release targetRelease;

	/** URIs of the models that need to be migrated. */
	protected final List<URI> modelURIs;

	/** Extent of the reconstructed metamodel. */
	protected MetamodelExtent extent;

	/** Internal representation of the model during migration. */
	protected Repository repository;

	/** Whether migration is active. */
	protected boolean enabled = false;

	/** Whether generation is started. */
	protected boolean started = false;

	/** Trigger to restart migration. */
	protected Change trigger = null;

	/** Mapping to the reconstructed metamodel. */
	protected Mapping mapping;

	/** Monitor to show progress. */
	protected final IProgressMonitor monitor;

	/** Switch to perform migration depending on change. */
	protected MigrationReconstructorSwitch migrationSwitch;

	/** Custom migration that is currently active. */
	protected CustomMigration customMigration;

	/** Classloader to load {@link CustomMigration}s. */
    protected IClassLoader classLoader;

	/** Validation level. */
    protected ValidationLevel level;

	/** Constructor. */
	public MigrationReconstructor(final List<URI> modelURIs, final Release sourceRelease,
			final Release targetRelease, final IProgressMonitor monitor,
			final IClassLoader classLoader, final ValidationLevel level) {
        this.modelURIs = modelURIs;
        this.sourceRelease = sourceRelease;
        this.targetRelease = targetRelease;
        this.monitor = monitor;
        this.classLoader = classLoader;
        this.level = level;
	}

	/** {@inheritDoc} */
	@Override
	public void init(Mapping mapping, MetamodelExtent extent) {
		migrationSwitch = new MigrationReconstructorSwitch();
		extent = extent;
		mapping = mapping;
	}

	/** {@inheritDoc} */
	@Override
	public void startRelease(final Release originalRelease) {
		if (isEnabled()) {
			monitor.subTask("");
		}
	}

	/** {@inheritDoc} */
	@Override
	public void endRelease(final Release originalRelease) {
		if (originalRelease == targetRelease) {
			disable();
			checkConformanceIfMoreThan(ValidationLevel.HISTORY);
			throw new FinishedException();
		} else if (originalRelease == sourceRelease) {
			enable();
			started = true;
			loadRepository();
		} else if (isEnabled()) {
			checkConformanceIfMoreThan(ValidationLevel.RELEASE);
		}
	}

	/**
	 * Check the conformance of the model to the metamodel if the validation
	 * level is greater or equal to a certain level.
	 */
	protected void checkConformanceIfMoreThan(final ValidationLevel level) {
		if (level.compareTo(level) >= 0) {
			checkConformance();
		}
	}

	/** Helper method to check the conformance of the model. */
	protected void checkConformance() {
		try {
			repository.getModel().checkConformance();
		} catch (final MigrationException e) {
			throwWrappedMigrationException(e);
		}
	}

	/** Load the model before migration. */
	protected void loadRepository() {
		final Metamodel metamodel = loadMetamodel();
		metamodel.refreshCaches();
		try {
            final Model model = Persistency.loadModel(modelURIs, metamodel, new ResourceSetFactoryImpl());
			repository = MigrationFactory.eINSTANCE.createRepository();
			repository.setMetamodel(metamodel);
			repository.setModel(model);
			checkConformanceIfMoreThan(ValidationLevel.HISTORY);
		} catch (final IOException e) {
			throwWrappedMigrationException("Model could not be loaded", e);
		}
	}

	/** Load the metamodel. */
	protected Metamodel loadMetamodel() {
		final URI metamodelURI = URI.createFileURI(new File("metamodel."
				+ ResourceUtils.ECORE_FILE_EXTENSION).getAbsolutePath());
		final Collection<EPackage> rootPackages = extent.getRootPackages();
		final ResourceSet resourceSet = MetamodelUtils
				.createIndependentMetamodelCopy(rootPackages, metamodelURI);
		return Persistency.loadMetamodel(resourceSet);
	}

	/** {@inheritDoc} */
	@Override
	public void startChange(final Change change) {
		if (isEnabled()) {
			if (isStarted()) {
				migrationSwitch.doSwitch(change);
			}
			checkPause(change);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void endChange(final Change change) {
		if (isEnabled()) {
			checkResume(change);
			if (isStarted()) {
				if (change instanceof MigrationChange
						&& customMigration != null) {
					try {
						customMigration.migrateAfter(repository.getModel(),
								repository.getMetamodel());
						monitor.worked(1);
						checkConformanceIfMoreThan(ValidationLevel.CUSTOM_MIGRATION);
					} catch (final MigrationException e) {
						throwWrappedMigrationException(e);
					} finally {
						customMigration = null;
					}
				} else if (change.eContainer() instanceof Release) {
					monitor.worked(1);
					checkConformanceIfMoreThan(ValidationLevel.CHANGE);
				}
			}
		}
	}

	/** Check whether to pause migration generator. */
	protected void checkResume(final Change originalChange) {
		if (trigger == originalChange) {
			started = true;
			trigger = null;
		}
	}

	/** Check whether to resume migration generator. */
	protected void checkPause(final Change originalChange) {
		if (trigger != null) {
			return;
		}
		if (originalChange instanceof OperationChange
				|| originalChange instanceof Delete) {
			started = false;
			trigger = originalChange;
		}
	}

	/** Check whether migration is started. */
	protected boolean isStarted() {
		return started;
	}

	/** Check whether migration is active. */
	protected boolean isEnabled() {
		return enabled;
	}

	/** De-activate migration. */
	protected void disable() {
		enabled = false;
	}

	/** Activate migration. */
	protected void enable() {
		enabled = true;
	}

	/** Wrap and throw a {@link MigrationException}. */
	protected void throwWrappedMigrationException(final String message, final Throwable e) {
		final MigrationException me = new MigrationException(message, e);
		throwWrappedMigrationException(me);
	}

	/** Wrap and throw a {@link MigrationException}. */
	protected void throwWrappedMigrationException(final MigrationException me) {
		throw new WrappedMigrationException(me);
	}

	/** Get the model. */
	public Model getModel() {
		if (repository == null) {
			return null;
		}
		return repository.getModel();
	}

	/** Switch that performs the migration attached to a change. */
	protected class MigrationReconstructorSwitch extends
			EcoreReconstructorSwitchBase<Object> {

		/**
		 * Resolver to resolve the metamodel on which the migration is performed
		 * from the original metamodel.
		 */
		protected final ResolverBase resolver = new ResolverBase() {

			@Override
			protected EObject doResolve(EObject element) {
				element = mapping.resolveTarget(element);
				element = find(element);
				return element;
			}
		};

		/** {@inheritDoc} */
		@Override
		public Object caseSet(final Set set) {
			final EObject element = set.getElement();
			final EStructuralFeature feature = set.getFeature();
			final Object value = set.getValue();
			if (feature == EcorePackage.eINSTANCE.getEReference_EOpposite()) {
				repository.getMetamodel().setEOpposite(
						(EReference) resolve(element),
						(EReference) resolve((EObject) value));
			} else if (feature instanceof EReference) {
				final EObject resolve = resolve((EObject) value);
				if (resolve instanceof EClass) {
					if ("EGenericType".equals(((EClass) resolve).getName())) {
						System.out.println();
						resolve((EObject) value);
					}
				}
				set(resolve(element), feature, resolve);
			} else {
				set(resolve(element), feature, value);
			}

			return set;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseAdd(final Add add) {
			final EObject element = add.getElement();
			final EStructuralFeature feature = add.getFeature();
			final Object value = add.getValue();
			if (feature instanceof EReference) {
				add(resolve(element), feature, resolve((EObject) value));
			} else {
				add(resolve(element), feature, value);
			}

			return add;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseRemove(final Remove remove) {
			final EObject element = remove.getElement();
			final EStructuralFeature feature = remove.getFeature();
			final Object value = remove.getValue();
			if (feature instanceof EReference) {
				remove(resolve(element), feature, resolve((EObject) value));
			} else {
				remove(resolve(element), feature, value);
			}

			return remove;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseCreate(final Create create) {
			final EObject element = create.getTarget();
			final EReference reference = create.getReference();
			create(resolve(element), reference, create.getElement().eClass());

			return create;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseDelete(final Delete delete) {
			final EObject element = delete.getElement();
			delete(resolve(element));

			return delete;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseMove(final Move move) {
			move(resolve(move.getElement()), resolve(move.getTarget()),
					move.getReference());

			return move;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseMigrationChange(final MigrationChange change) {
			try {
				final String migration = change.getMigration();
				final Class<?> c = classLoader.load(migration);
				customMigration = (CustomMigration) c.newInstance();
				customMigration.migrateBefore(repository.getModel(),
						repository.getMetamodel());
			} catch (final ClassNotFoundException e) {
				throwWrappedMigrationException(
						"Custom migration could not be loaded", e);
			} catch (final InstantiationException e) {
				throwWrappedMigrationException(
						"Custom migration could not be instantiated", e);
			} catch (final IllegalAccessException e) {
				throwWrappedMigrationException(
						"Custom migration could not be accessed", e);
			} catch (final MigrationException e) {
				throwWrappedMigrationException(e);
			}

			return change;
		}

		/** {@inheritDoc} */
		@Override
		public Object caseOperationChange(final OperationChange change) {
			final OperationInstance operationInstance = (OperationInstance) resolver
					.copyResolve(change.getOperation(), true);

			final Metamodel metamodel = repository.getMetamodel();
            final OperationImplementation operation = OperationInstanceConverter.convert(operationInstance, metamodel);
			if (operation == null) {
				throwWrappedMigrationException("Operation could not be found: "
						+ operationInstance.getName(), null);
			} else {
				try {
					operation.checkAndExecute(repository.getMetamodel(),
							repository.getModel());
				} catch (final MigrationException e) {
					throwWrappedMigrationException(e);
				}
			}

			return change;
		}

		/** Resolve an element using the resolver. */
		protected EObject resolve(final EObject element) {
			return resolver.resolve(element);
		}

		/** Find an element in the metamodel created for migration. */
		@SuppressWarnings("unchecked")
		protected EObject find(final EObject sourceElement) {
			if (sourceElement == EcorePackage.eINSTANCE) {
				return sourceElement;
			}
			final EObject sourceParent = sourceElement.eContainer();
			if (sourceParent == null) {
				final EPackage sourcePackage = (EPackage) sourceElement;
				for (final EPackage targetPackage : repository.getMetamodel()
						.getEPackages()) {
					if (targetPackage.getNsURI().equals(
							sourcePackage.getNsURI())) {
						return targetPackage;
					}
				}
				return sourcePackage;
			}
			final EObject targetParent = find(sourceParent);
			if (targetParent == sourceParent) {
				return sourceElement;
			}
			final EReference reference = sourceElement.eContainmentFeature();
			if (reference.isMany()) {
				final List<EObject> targetChildren = (List<EObject>) targetParent
						.eGet(reference);
				final List<EObject> sourceChildren = (List<EObject>) sourceParent
						.eGet(reference);
				final int index = sourceChildren.indexOf(sourceElement);
				final EObject targetElement = targetChildren.get(index);
				return targetElement;
			}
			final EObject targetElement = (EObject) targetParent.eGet(reference);
			return targetElement;
		}
	}
}
