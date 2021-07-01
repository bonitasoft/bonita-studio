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
package org.bonitasoft.studio.application.views.dashboard;

import java.util.List;
import java.util.function.Consumer;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.extension.DashboardContribution;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractDashboardZoomControl<T extends AbstractFileStore> extends Composite {

    private ZoomListener zoomListener;
    protected DashboardContribution contribution;
    private CommandExecutor commandExecutor;
    protected ExceptionDialogHandler errorHandler;

    private IThemeEngine engine;
    private Cursor cursorHand;
    private Cursor cursorArrow;

    protected class Element {

        private String name;
        private String description;

        public Element(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    protected AbstractDashboardZoomControl(Composite parent, ZoomListener zoomListener, DashboardContribution contribution) {
        super(parent, SWT.BORDER);
        this.zoomListener = zoomListener;
        this.contribution = contribution;
        this.commandExecutor = new CommandExecutor();
        this.errorHandler = new ExceptionDialogHandler();
        this.engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        this.cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        this.cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);

        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var typeColorComposite = new Composite(this, SWT.NONE);
        typeColorComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        typeColorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(50, SWT.DEFAULT).create());
        typeColorComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, contribution.getColorCssClass());

        var contentComposite = new Composite(this, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        contentComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createZoomedTitleComposite(contentComposite);
        createDescription(contentComposite);
        createDetailsSection(contentComposite);
    }

    protected abstract String getHint();

    private void createDetailsSection(Composite parent) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        List<T> fileStores = getFileStores();

        if (fileStores.isEmpty()) {
            createEmptyComposite(composite,
                    String.format(Messages.projectDoesntContainsElement, getElementName()),
                    String.format(Messages.newElementTitle, getElementName()),
                    e -> commandExecutor.executeCommand(getNewCommand(), null));
        } else {
            createfileStoreListComposite(composite, fileStores);
        }
    }

    protected abstract String getElementName();

    protected abstract String getNewCommand();

    protected abstract List<T> getFileStores();

    private void createDescription(Composite parent) {
        var description = new Link(parent, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 10).create());
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        description.setText(getHint());
        description.addListener(SWT.Selection, new OpenSystemBrowserListener(contribution.getDocumentationLink()));
    }

    private void createZoomedTitleComposite(Composite parent) {
        var titleComposite = createComposite(parent, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(contribution.getName());

        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        titleLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        titleLabel.addListener(SWT.MouseUp, e -> {
            if (zoomListener != null) {
                Rectangle bounds = titleLabel.getBounds();
                if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                    zoomListener.deZoom(e);
                }
            }
        });

        titleLabel.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        BonitaThemeConstants.CARD_HIGHLIGHT_TITLE_COLOR);
                engine.applyStyles(titleLabel, false);
                titleLabel.setCursor(cursorHand);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                engine.applyStyles(titleLabel, false);
                titleLabel.setCursor(cursorArrow);
            }
        });

        new DynamicButtonWidget.Builder()
                .withText(Messages.back)
                .withId(SWTBotConstants.SWTBOT_ID_MINIMIZE_CARD_BUTTON)
                .withImage(Pics.getImage(PicsConstants.back))
                .withHotImage(Pics.getImage(PicsConstants.backHot))
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.END).create())
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> {
                    if (zoomListener != null) {
                        zoomListener.deZoom(e);
                    }
                })
                .createIn(titleComposite);
    }

    protected void createEmptyComposite(Composite parent, String message, String buttonMessage,
            Consumer<Event> onClickListener) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());

        var emptyLabel = new Label(composite, SWT.NONE);
        emptyLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        emptyLabel.setText(message);
        emptyLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.NORMAL_4_FONT_ID));

        new DynamicButtonWidget.Builder()
                .withText(buttonMessage)
                .withImage(Pics.getImage(PicsConstants.add_item_large))
                .withHotImage(Pics.getImage(PicsConstants.add_item_large))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .withFont(JFaceResources.getFont(ProjectDashboardEditorPart.NORMAL_4_FONT_ID))
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.FILL).create())
                .onClick(onClickListener)
                .createIn(composite);
    }

    protected void createfileStoreListComposite(Composite parent, List<T> fileStores) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 40, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, true).create());

        createSeparator(composite);
        fileStores.forEach(fileStore -> {
            createFileStoreComposite(composite, fileStore);
            createSeparator(composite);
        });
    }

    private void createFileStoreComposite(Composite parent, T fileStore) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(4).equalWidth(true).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var titleComposite = createComposite(composite, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(4, 1).create());

        createFileStoreTitleLabel(fileStore, titleComposite);

        new DynamicButtonWidget.Builder()
                .withText(org.bonitasoft.studio.common.Messages.open)
                .withImage(Pics.getImage(PicsConstants.open32))
                .withHotImage(Pics.getImage(PicsConstants.open32Hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create())
                .withId(SWTBotConstants.openArtifactButtonId(fileStore.getName()))
                .onClick(e -> fileStore.open())
                .createIn(titleComposite);

        retrieveFileStoreContent(fileStore).forEach(element -> createElementComposite(composite, element));
    }

    protected void createFileStoreTitleLabel(T fileStore, Composite titleComposite) {
        var titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(fileStore.getName());
        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_4_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
    }

    protected abstract List<Element> retrieveFileStoreContent(T fileStore);

    protected void createElementComposite(Composite parent, Element element) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var titleLabel = new CLabel(composite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        titleLabel.setText(element.getName());
        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_0_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        String description = Strings.hasText(element.getDescription()) ? element.getDescription() : Messages.noDescription;
        var descrptionLabel = new Label(composite, SWT.WRAP);
        descrptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
        descrptionLabel.setText(description);
        descrptionLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.ITALIC_0_FONT_ID));
        descrptionLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);
    }

    private void createSeparator(Composite parent) {
        var separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);
    }

    protected Composite createComposite(Composite parent, int style) {
        var composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        return composite;
    }

}
