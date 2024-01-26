
package org.bonitasoft.studio.maven.ui.handler;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ImportProjectExtensionHandler {

	private MavenProjectHelper mavenProjectHelper;
	private RepositoryAccessor repositoryAccessor;
	private ExceptionDialogHandler errorDialogHandler;

	@Inject
	public ImportProjectExtensionHandler(RepositoryAccessor repositoryAccessor,
			MavenRepositoryRegistry mavenRepositoryRegistry,
			MavenProjectHelper mavenProjectHelper,
			ExceptionDialogHandler errorDialogHandler) {
		this.mavenProjectHelper = mavenProjectHelper;
		this.repositoryAccessor = repositoryAccessor;
		this.errorDialogHandler = errorDialogHandler;
	}

	@CanExecute
	public boolean canExecute() {
		return repositoryAccessor.getCurrentRepository()
				.filter(org.bonitasoft.studio.common.repository.model.IRepository::isLoaded).isPresent();
	}

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell) {
		var project = repositoryAccessor.getCurrentProject().orElseThrow();
		try {
			var mavenModel = MavenProjectHelper.getMavenModel(project.getAppProject());
			var selectedProjects = new WritableList<ExtensionProjectFileStore<?>>();

			WizardBuilder.<Boolean>newWizard().withTitle(Messages.importProjectExtensionTitle).needProgress()
					.havingPage(newPage().withTitle(Messages.importProjectExtensionTitle)
							.withDescription(Messages.importProjectExtensionDesc)
							.withControl(projectExtensionControl(project, mavenModel, selectedProjects)))
					.onFinish(container -> performFinish(container, selectedProjects))
					.open(activeShell, org.bonitasoft.studio.ui.i18n.Messages.importLabel);
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
			errorDialogHandler.openErrorDialog(activeShell, e);
		}
	}

	private ControlSupplier projectExtensionControl(BonitaProject project, Model mavenModel,
			IObservableList<ExtensionProjectFileStore<?>> selectedProjects) {
		return new ControlSupplier() {

			@Override
			public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
				var container = new Composite(parent, SWT.NONE);
				container.setLayout(
						GridLayoutFactory.fillDefaults().margins(10, 10).extendedMargins(0, 0, 0, 30).create());
				container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

				var selectLabel = new Label(container, SWT.WRAP);
				selectLabel.setText(Messages.selectProjectExtensionsFromList);
				selectLabel.setLayoutData(GridDataFactory.fillDefaults().hint(500, SWT.DEFAULT).create());

				var projectViewer = new TableViewer(container);
				projectViewer.getControl()
						.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, 300).create());

				projectViewer.setContentProvider(ArrayContentProvider.getInstance());
				projectViewer.setLabelProvider(new LabelProviderBuilder<ExtensionProjectFileStore<?>>()
						.withStyledStringProvider(project -> IDisplayable.adapt(project).map(IDisplayable::getStyledString).orElse(null))
						.withImageProvider(
								project -> IDisplayable.adapt(project).map(IDisplayable::getIcon).orElse(null))
						.createStyledCellLabelProvider());
				projectViewer.setFilters(new ViewerFilter() {

					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						var extensionProject = (ExtensionProjectFileStore<?>) element;
						try {
							var dependencyToAdd = createProjectExtensionDependency(extensionProject.getContent().getArtifactId());
							return mavenProjectHelper.findDependency(mavenModel, dependencyToAdd).isEmpty();
						} catch (ReadFileStoreException e) {
							errorDialogHandler.openErrorDialog(parent.getShell(), "Failed to read artifactId" ,e);
							return false;
						}
					}
				});
				projectViewer.setInput(repositoryAccessor.getRepositoryStore(ExtensionRepositoryStore.class).getChildren().stream()
						.filter(store -> !store.isReadOnly())
						.collect(Collectors.toList()));
				ctx.bindList(ViewerProperties.multipleSelection(IProject.class).observe(projectViewer),
						selectedProjects);
				ctx.addValidationStatusProvider(new MultiValidator() {
					
					@Override
					protected IStatus validate() {
						return selectedProjects.isEmpty() ? Status.error("At least one project must be selected.") : Status.OK_STATUS;
					}
				});
				return container;
			}
		};
	}

	private Optional<Boolean> performFinish(IWizardContainer container, IObservableList<ExtensionProjectFileStore<?>> selectedProjects) {
		try {
			var dependenciesToAdd = selectedProjects.stream()
					.map(this::createProjectExtensionDependency)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			container.run(true, false, monitor -> {
				try {
					new AddDependencyOperation(dependenciesToAdd).run(monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			if (e.getCause() instanceof CoreException) {
				errorDialogHandler.openErrorDialog(container.getShell(), (CoreException) e.getCause());
			} else {
				errorDialogHandler.openErrorDialog(container.getShell(), "Failed to add project extension.", e);
			}
			BonitaStudioLog.error(e);
			return Optional.of(false);
		}
		return Optional.of(true);
	}

	private Dependency createProjectExtensionDependency(ExtensionProjectFileStore<?> project) {
		try {
			var model = MavenProjectHelper.getMavenModel(project.getProject());
			return createProjectExtensionDependency(model.getArtifactId());
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
			return null;
		}

	}

	private Dependency createProjectExtensionDependency(String artifactId) {
		var dependencyToAdd = new Dependency();
		dependencyToAdd.setGroupId("${project.groupId}");
		dependencyToAdd.setArtifactId(artifactId);
		dependencyToAdd.setVersion("${project.version}");
		dependencyToAdd.setType("zip");
		return dependencyToAdd;
	}

}