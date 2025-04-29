/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views.overview;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.application.views.extension.ExtensionComposite;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectChangedListener;
import org.eclipse.m2e.core.project.MavenProjectChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.progress.IProgressService;
import org.osgi.service.event.EventHandler;

public class ProjectOverviewEditorPart extends EditorPart implements EventHandler, IMavenProjectChangedListener {

	public static final String ID = "org.bonitasoft.studio.application.dashboard.editor";

	public static final String BOLD_20_FONT_ID = "bold20_bonita";
	public static final String BOLD_12_FONT_ID = "bold12_bonita";
	public static final String BOLD_8_FONT_ID = "bold8_bonita";
	public static final String BOLD_4_FONT_ID = "bold4_bonita";
	public static final String BOLD_0_FONT_ID = "bold0_bonita";
	public static final String ITALIC_2_FONT_ID = "italic2_bonita";
	public static final String ITALIC_0_FONT_ID = "italic0_bonita";
	public static final String NORMAL_10_FONT_ID = "normal10_bonita";
	public static final String NORMAL_4_FONT_ID = "normal4_bonita";

	public static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";
	public static final String IMPORT_EXTENSION_COMMAND = "org.bonitasoft.studio.application.import.extension.command";
	public static final String UPDATE_GAV_COMMAND = "org.bonitasoft.studio.application.update.gav.command";
	public static final String EDIT_PROJECT_COMMAND = "org.bonitasoft.studio.application.edit.project.command";
	public static final String IMPORT_PROJECT_EXTENSION_COMMAND = "org.bonitasoft.studio.maven.import.project.extension.command";

	private RepositoryAccessor repositoryAccessor;
	private LocalResourceManager localResourceManager;
	private ExceptionDialogHandler errorHandler;
	private CommandExecutor commandExecutor;

	private Composite mainComposite;
	private Composite titleComposite;
	private Composite toolbarComposite;
	private ExtensionComposite extensionComposite;
	private StyledText title;
	private Label description;

	private Cursor cursorHand;
	private Cursor cursorArrow;

	private ElementComposite elementComposite;
	private DynamicButtonWidget toElementViewButton;
	private DynamicButtonWidget toExtensionViewButton;

	private IProgressService progressService;

	private static ReentrantLock lock = new ReentrantLock();

	public ProjectOverviewEditorPart() {
		repositoryAccessor = RepositoryManager.getInstance().getAccessor();
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		var eclipseContext = EclipseContextFactory.create();
		errorHandler = ContextInjectionFactory.make(ExceptionDialogHandler.class, eclipseContext);
		commandExecutor = ContextInjectionFactory.make(CommandExecutor.class, eclipseContext);
		progressService = PlatformUI.getWorkbench().getProgressService();
		MavenPlugin.getMavenProjectRegistry().addMavenProjectChangedListener(this);
		PlatformUI.getWorkbench().getService(IEventBroker.class)
				.subscribe(MavenProjectDependenciesStore.PROJECT_DEPENDENCIES_ANALYZED_TOPIC, this);

		if (!(input instanceof ProjectOverviewEditorInput)) {
			throw new PartInitException("Invalid Input: Must be ProjectExtensionEditorInput");
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public void createPartControl(Composite parent) {

		try {
			PlatformUI.getWorkbench().getProgressService().busyCursorWhile(monitor -> {
				monitor.beginTask(Messages.loadingProjectOverview, IProgressMonitor.UNKNOWN);
				try {
					waitUntil(() -> isCurrentRepositoryLoaded(), 5000, 50);
				} catch (TimeoutException e) {
					BonitaStudioLog.error(e);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), "Failed to open project overview.", e);
		}

		initVariables(parent);
		if (parent.isDisposed()) {
			return;
		}
		parent.setLayout(GridLayoutFactory.fillDefaults().create());

		mainComposite = createComposite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 0).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		initFonts(mainComposite.getFont());

		createTitleAndToolbar(mainComposite);

		elementComposite = new ElementComposite(mainComposite);
	}

	private boolean isCurrentRepositoryLoaded() {
		return repositoryAccessor.getCurrentRepository().isPresent()
				&& !repositoryAccessor.getCurrentRepository().filter(IRepository::isLoaded).isEmpty();
	}

	private static void waitUntil(BooleanSupplier condition, int timeout, int interval)
			throws TimeoutException, InterruptedException {
		var countDownLatch = new CountDownLatch(1);
		var shouldExitThread = new AtomicBoolean();
		shouldExitThread.set(false);
		new Thread(() -> {
			var result = false;
			while (!result && !shouldExitThread.get()) {
				result = condition.getAsBoolean();
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			if (result) {
				countDownLatch.countDown();
			}
		}, "Wait condition thread").start();

		if (!countDownLatch.await(timeout, TimeUnit.MILLISECONDS)) {
			shouldExitThread.set(true);
			throw new TimeoutException(String.format("Failed to evaluate condition after %sms", timeout));
		}

	}

	private void toElementsView() {
		if (extensionComposite != null && !extensionComposite.isDisposed()) {
			extensionComposite.dispose();
			elementComposite = new ElementComposite(mainComposite);
			toElementViewButton.updateImage(Pics.getImage(PicsConstants.openOverview32_hot));
			toExtensionViewButton.updateImage(Pics.getImage(PicsConstants.extensions32));
			mainComposite.layout();
		}
	}

	private void toExtensionsView() {
		if (elementComposite != null && !elementComposite.isDisposed()) {
			elementComposite.dispose();
			extensionComposite = new ExtensionComposite(mainComposite, repositoryAccessor);
			toElementViewButton.updateImage(Pics.getImage(PicsConstants.openOverview32));
			toExtensionViewButton.updateImage(Pics.getImage(PicsConstants.extensions32_hot));
			mainComposite.layout();
		}
	}

	private void createTitleAndToolbar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 20, 20).create());
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);

		titleComposite = new Composite(composite, SWT.NONE);
		titleComposite.setLayout(
				GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 3).create());
		titleComposite.setLayoutData(
				GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());
		titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);

		Composite labelComposite = new Composite(titleComposite, SWT.NONE);
		labelComposite.setLayout(
				GridLayoutFactory.fillDefaults().numColumns(2).spacing(2, LayoutConstants.getSpacing().y).create());
		labelComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
		labelComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);

		title = new StyledText(labelComposite, SWT.NONE);
		title.setEditable(false);
		title.setEnabled(false);
		title.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.END).create());

		title.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_PROJECT_DETAILS_TITLE);
		title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.HEADER_TEXT_COLOR);
		title.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);

		createEditButton(titleComposite);

		refreshContent();

		toolbarComposite = new Composite(composite, SWT.NONE);
		toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());
		toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BOTTOM).create());
		toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);

		toElementViewButton = createSwitchButton(toolbarComposite, Messages.elementView, Messages.elementViewTooltip,
				Pics.getImage(PicsConstants.openOverview32_hot), Pics.getImage(PicsConstants.openOverview32_hot),
				SWTBotConstants.SWTBOT_ID_OPEN_ELEMENT_VIEW, e -> Display.getDefault().asyncExec(this::toElementsView));

		toExtensionViewButton = createSwitchButton(toolbarComposite, Messages.extensionView,
				Messages.extensionViewTooltip, Pics.getImage(PicsConstants.extensions32),
				Pics.getImage(PicsConstants.extensions32_hot), SWTBotConstants.SWTBOT_ID_OPEN_EXTENSIONS_VIEW,
				e -> Display.getDefault().asyncExec(this::toExtensionsView));

		var separator = new Label(toolbarComposite, SWT.SEPARATOR | SWT.VERTICAL);
		separator.setLayoutData(GridDataFactory.swtDefaults().hint(SWT.DEFAULT, 32).create());
		new DynamicButtonWidget.Builder().withLabel(Messages.refresh).withTooltipText(Messages.refreshTooltip)
				.withId(SWTBotConstants.SWTBOT_ID_REFRESH_PROJECT).withImage(Pics.getImage(PicsConstants.refresh))
				.withHotImage(Pics.getImage(PicsConstants.refresh_hot))
				.withCssclass(BonitaThemeConstants.HEADER_BACKGROUND).onClick(e -> {
					try {
						progressService.run(true, false, monitor -> {
							var currentRepository = RepositoryManager.getInstance().getCurrentRepository()
									.orElseThrow();
							try {
								MavenPlugin.getProjectConfigurationManager()
										.updateProjectConfiguration(currentRepository.getProject(), monitor);
							} catch (CoreException ex) {
								BonitaStudioLog.error(ex);
							}
							currentRepository.getProjectDependenciesStore().analyze(monitor);
							currentRepository.build(monitor);
						});
					} catch (InvocationTargetException | InterruptedException ex) {
						BonitaStudioLog.error(ex);
					}
				}).createIn(toolbarComposite);

	}

	public void openExtensionsView() {
		Display.getDefault().asyncExec(() -> {
			toExtensionsView();
		});
	}

	private DynamicButtonWidget createSwitchButton(Composite parent, String text, String tooltip, Image image,
			Image hotImage, String swtbotId, Consumer<Event> onClickListener) {
		return new DynamicButtonWidget.Builder().withLabel(text).withTooltipText(tooltip).withId(swtbotId)
				.withImage(image).withHotImage(hotImage).withCssclass(BonitaThemeConstants.HEADER_BACKGROUND)
				.onClick(onClickListener::accept).createIn(parent);
	}

	private void createEditButton(Composite parent) {
		Label editLabel = new Label(parent, SWT.NONE);
		editLabel.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_EDIT_PROJECT_METADATA);
		editLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
		editLabel.setImage(Pics.getImage(PicsConstants.editProject));
		editLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_20_FONT_ID));
		editLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);
		editLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (editLabel.equals(e.widget)) {
					commandExecutor.executeCommand(EDIT_PROJECT_COMMAND, null);
				}
			}
		});

		editLabel.addMouseTrackListener(new MouseTrackAdapter() {

			@Override
			public void mouseExit(MouseEvent e) {
				editLabel.setImage(Pics.getImage(PicsConstants.editProject));
				editLabel.setCursor(cursorArrow);
			}

			@Override
			public void mouseEnter(MouseEvent e) {
				editLabel.setImage(Pics.getImage(PicsConstants.editProjectHot));
				editLabel.setCursor(cursorHand);
			}
		});
	}

	private void initVariables(Composite parent) {
		if (!parent.isDisposed() && !parent.getDisplay().isDisposed()) {
			cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
			cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		}
	}

	@Override
	public void dispose() {
		MavenPlugin.getMavenProjectRegistry().removeMavenProjectChangedListener(this);
		super.dispose();
	}

	private Composite createComposite(Composite parent, int style) {
		Composite composite = new Composite(parent, style);
		composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
		return composite;
	}

	private void refreshContent() {
		lock.lock();
		try {
			repositoryAccessor.getCurrentProject().ifPresent(bonitaProject -> {
				Display.getDefault().syncExec(() -> {
					ProjectMetadata metadata = null;
					try {
						metadata = bonitaProject.getProjectMetadata(new NullProgressMonitor());
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					}
					if (metadata == null) {
						return;
					}
					String name = metadata.getName();
					name = name == null ? "" : name;
					String version = metadata.getVersion();
					String descriptionContent = metadata.getDescription();
					if (title == null || title.isDisposed()) {
						return;
					}
					title.setText(String.format("%s %s", name, version));

					StyleRange titleStyle = new StyleRange(0, name.length(), title.getForeground(),
							title.getBackground());
					titleStyle.font = JFaceResources.getFontRegistry().get(ProjectOverviewEditorPart.BOLD_20_FONT_ID);
					StyleRange versionStyle = new StyleRange(name.length() + 1, version.length(), title.getForeground(),
							title.getBackground());
					versionStyle.font = JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID);
					title.setStyleRanges(new StyleRange[] { titleStyle, versionStyle });

					refreshDescription(descriptionContent);
					mainComposite.layout();

					if (extensionComposite != null && !extensionComposite.isDisposed()) {
						extensionComposite.refreshContent();
					}
				});
			});
		} finally {
			lock.unlock();
		}
	}

	private boolean refreshDescription(String descriptionContent) {
		if (descriptionContent != null && !descriptionContent.isBlank()
				&& (description == null || !Objects.equals(description.getText(), descriptionContent))) {
			if (description == null || description.isDisposed()) {
				createDescriptionLabel(titleComposite);
			}
			description.setText(descriptionContent);
			return true;
		} else if (description != null && (descriptionContent == null || descriptionContent.isBlank())) {
			description.dispose();
			description = null;
			return true;
		} else {
			titleComposite.getParent().layout();
			return false;
		}
	}

	public void createDescriptionLabel(Composite titleComposite) {
		description = new Label(titleComposite, SWT.WRAP);
		description.setLayoutData(
				GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BEGINNING).span(2, 1).create());
		description.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
				SWTBotConstants.SWTBOT_ID_PROJECT_DETAILS_DESCRIPTION);
		description.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.HEADER_TEXT_COLOR);
		description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.HEADER_BACKGROUND);
	}

	private void initFonts(Font defaultFont) {
		createFont(BOLD_20_FONT_ID, defaultFont, 20, SWT.BOLD);
		createFont(BOLD_12_FONT_ID, defaultFont, 12, SWT.BOLD);
		createFont(BOLD_8_FONT_ID, defaultFont, 8, SWT.BOLD);
		createFont(BOLD_4_FONT_ID, defaultFont, 4, SWT.BOLD);
		createFont(BOLD_0_FONT_ID, defaultFont, 0, SWT.BOLD);
		createFont(ITALIC_0_FONT_ID, defaultFont, 0, SWT.ITALIC);
		createFont(ITALIC_2_FONT_ID, defaultFont, 2, SWT.ITALIC);
		createFont(NORMAL_10_FONT_ID, defaultFont, 10, SWT.NORMAL);
		createFont(NORMAL_4_FONT_ID, defaultFont, 4, SWT.NORMAL);
	}

	private void createFont(String id, Font initialFont, int increaseHeight, int style) {
		if (!JFaceResources.getFontRegistry().hasValueFor(id)
				|| JFaceResources.getFontRegistry().get(id).isDisposed()) {
			FontDescriptor descriptor = FontDescriptor.createFrom(initialFont).setStyle(style)
					.increaseHeight(increaseHeight);
			JFaceResources.getFontRegistry().put(id, localResourceManager.createFont(descriptor).getFontData());
		}
	}

	@Override
	public void setFocus() {
		IWorkbenchPage page = getSite().getPage();
		var explorerView = page.findView(BonitaProjectExplorer.ID);
		if (explorerView != null && page.getActivePart() != explorerView) {
			page.activate(explorerView);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// do nothing
	}

	@Override
	public void doSaveAs() {
		// do nothing
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public Image getTitleImage() {
		return Pics.getImage(PicsConstants.openOverview);
	}

	@Override
	public void handleEvent(org.osgi.service.event.Event event) {
		refreshContent();
	}

	@Override
	public void mavenProjectChanged(List<MavenProjectChangedEvent> events, IProgressMonitor monitor) {
		repositoryAccessor.getCurrentRepository().ifPresent(current -> {
			events.stream()
			.filter(e -> e.getMavenProject() != null)
			.filter(e -> Objects.equals(e.getMavenProject().getPom(),
					current.getProject().getFile(IMavenConstants.POM_FILE_NAME))).findFirst().ifPresent(e -> {
						refreshContent();
					});
		});

	}

}
