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
package org.bonitasoft.studio.application.views.extension.card.zoom;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorPart;
import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;
import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class RestApiZoomControl extends AbstractZoomControl {

    private Label sectionTitle;

    public RestApiZoomControl(Composite parent,
            ZoomListener zoomListener,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, zoomListener, dep, bonitaDep);
    }

    @Override
    protected void createDetailsSection(Composite parent) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 20, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var titleComposite = createComposite(composite, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        sectionTitle = new Label(titleComposite, SWT.WRAP);
        sectionTitle.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        sectionTitle.setText(Messages.restApiExtensions);
        sectionTitle.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        sectionTitle.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        sectionTitle.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_4_FONT_ID));

        var iconLabel = new Label(titleComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.FILL)
                .span(1, 2)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());

        var hint = new Label(titleComposite, SWT.WRAP);
        hint.setLayoutData(GridDataFactory.fillDefaults().indent(10, 0).create());
        hint.setText(String.format(Messages.restApiDetailsHint, bonitaDep.getName()));
        hint.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var definitionsComposite = createComposite(composite, SWT.NONE);
        definitionsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 10).create());
        definitionsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Optional<RestAPIExtension> restApiExtension = getRestApiExtension();
        if (restApiExtension.isPresent()) {
            try {
                Properties properties = ExtensionPagePropertiesReader
                        .getPageProperties(new File(restApiExtension.get().getArtifact().getFile()))
                        .orElseThrow();
                ExtensionPagePropertiesReader.getProperty(properties, PagePropertyConstants.API_EXTENSIONS)
                        .ifPresent(extensions -> {
                            for (String extension : extensions.split(",")) {
                                createRestAPIExtensionDetails(definitionsComposite, properties, extension);
                            }
                        });
            } catch (IOException e) {
                new ExceptionDialogHandler().openErrorDialog(getShell(), e.getMessage(), e);
            }
        }
    }

    private void createRestAPIExtensionDetails(Composite parent, Properties properties, String extension) {
        String method = getProperty(properties, extension, PagePropertyConstants.METHOD).orElse(Messages.httpMethodMissing);
        String path = getProperty(properties, extension, PagePropertyConstants.PATH_TEMAPLTE).orElse(Messages.pathMissing);
        String permissions = getProperty(properties, extension, PagePropertyConstants.PERMISSIONS)
                .orElse(Messages.permissionsMissing);

        var separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createStyledText(composite, extension, method, JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_0_FONT_ID));

        var detailsComposite = createComposite(composite, SWT.NONE);
        // num column is voluntary higher than the real num column (2), so we can have alignement and we don't have a big white space between path and permissions
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(3).equalWidth(true)
                .spacing(LayoutConstants.getSpacing().x, 20).create());
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var pathComposite = createComposite(detailsComposite, SWT.NONE);
        pathComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).create());
        pathComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var pathLabel = new Label(pathComposite, SWT.WRAP);
        pathLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        pathLabel.setText(Messages.path);
        pathLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_0_FONT_ID));
        pathLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var pathValueLabel = new Label(pathComposite, SWT.WRAP);
        pathValueLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        pathValueLabel.setText(String.format("../API/extension/%s", path));
        pathValueLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
        pathValueLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withImage(Pics.getImage(PicsConstants.copyToClipboard))
                .withTooltipText(Messages.copyToClipboard)
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create())
                .onClick(e -> copyToClipBoard(pathValueLabel.getText()))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .createIn(pathComposite);

        var permissionsComposite = createComposite(detailsComposite, SWT.NONE);
        permissionsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        permissionsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var permissionsLabel = new Label(permissionsComposite, SWT.WRAP);
        permissionsLabel.setLayoutData(GridDataFactory.fillDefaults().indent(30, 0).create());
        permissionsLabel.setText(Messages.permissions);
        permissionsLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_0_FONT_ID));
        permissionsLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var permissionListComposite = createComposite(permissionsComposite, SWT.NONE);
        permissionListComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 0).create());
        permissionListComposite
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());

        Arrays.asList(permissions.split(",")).forEach(permission -> {
            var permissionLabel = new Label(permissionListComposite, SWT.WRAP);
            permissionLabel.setLayoutData(GridDataFactory.fillDefaults().create());
            permissionLabel.setText(permission);
            permissionLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
            permissionLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        });
    }

    private void copyToClipBoard(String value) {
        Clipboard cb = new Clipboard(Display.getDefault());
        TextTransfer textTransfer = TextTransfer.getInstance();
        cb.setContents(new Object[] { value },
                new Transfer[] { textTransfer });
        cb.dispose();
    }

    private Optional<String> getProperty(Properties properties, String extension, String property) {
        return ExtensionPagePropertiesReader.getProperty(properties,
                String.format("%s.%s", extension, property));
    }

    private void createStyledText(Composite parent, String extensionName, String extensionMethod, Font font) {
        var styledText = new StyledText(parent, SWT.WRAP);
        styledText.setEditable(false);
        styledText.setEnabled(false);
        styledText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        styledText.setText(String.format("%s (%s)", extensionName, extensionMethod));
        styledText.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TYPE_TEXT_COLOR);
        styledText.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Display.getDefault().asyncExec(() -> {
            StyleRange nameStyle = new StyleRange(0, extensionName.length(), sectionTitle.getForeground(),
                    styledText.getBackground());
            nameStyle.font = font;
            StyleRange methodStyle = new StyleRange(extensionName.length() + 1,
                    extensionMethod.length() + 2,
                    styledText.getForeground(), styledText.getBackground());
            methodStyle.font = JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID);
            styledText.setStyleRanges(new StyleRange[] { nameStyle, methodStyle });
            parent.layout();
        });
    }

    private Optional<RestAPIExtension> getRestApiExtension() {
        return projectDependenciesStore.getRestAPIExtensions().stream()
                .filter(extension -> Objects.equals(extension.getArtifact().getGroupId(), dep.getGroupId()))
                .filter(extension -> Objects.equals(extension.getArtifact().getArtifactId(), dep.getArtifactId()))
                .filter(extension -> Objects.equals(extension.getArtifact().getVersion(), dep.getVersion()))
                .findFirst();
    }

}
