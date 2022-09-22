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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.OverviewContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class ElementComposite extends Composite {

    private static final String CLASS_EXECUTABLE_EXTENSION = "class";
    private static final String DASHBOARD_CONTRIBUTION_EXTENSION_POINT = "org.bonitasoft.studio.common.dashboardContribution";

    private ScrolledComposite scrolledComposite;
    private Composite cardComposite;

    public ElementComposite(Composite parent) {
        super(parent, SWT.NONE);

        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        setLayout(GridLayoutFactory.fillDefaults().margins(20, 20).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
        scrolledComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        scrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        cardComposite = createComposite(scrolledComposite, SWT.NONE);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createElementsTitleComposite(cardComposite);
        createCards(cardComposite);

        scrolledComposite.setContent(cardComposite);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
    }

    private void createElementsTitleComposite(Composite parent) {
        var titleComposite = createComposite(parent, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        var ElementsLabel = new Label(titleComposite, SWT.NONE);
        ElementsLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.END).create());
        ElementsLabel.setText(Messages.projectElements);
        ElementsLabel.setToolTipText(Messages.projectElementsTooltip);
        ElementsLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_8_FONT_ID));
        ElementsLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        ElementsLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        var separator = new Label(titleComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
    }

    private void createCards(Composite parent) {
        List<OverviewContribution> contributions = loadContributions();

        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().margins(20, 10).spacing(20, 20)
                        .numColumns(3)
                        .equalWidth(true)
                        .create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        contributions.forEach(contribution -> createCard(composite, contribution));
    }

    private void createCard(Composite parent, OverviewContribution contribution) {
        new ElementCard(parent, contribution);
        if (contribution instanceof Zoomable) {
            ((Zoomable) contribution).addZoomListener(new ZoomListener() {

                @Override
                public void zoom(Event e) {
                    Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
                    ((Zoomable) contribution).createZoomedControl(cardComposite);
                    cardComposite.layout();
                    scrolledComposite
                            .setMinHeight(cardComposite.computeSize(cardComposite.getClientArea().width, SWT.DEFAULT).y);
                }

                @Override
                public void deZoom(Event e) {
                    refreshContent();
                }

            });

            ((Zoomable) contribution).addComputeScrollListener(e -> {
                cardComposite.layout();
                scrolledComposite
                        .setMinHeight(cardComposite.computeSize(cardComposite.getClientArea().width, SWT.DEFAULT).y);
            });
        }
    }

    public void refreshContent() {
        Display.getDefault().asyncExec(() -> {
            Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
            createElementsTitleComposite(cardComposite);
            createCards(cardComposite);
            cardComposite.layout();
            scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        });
    }

    private List<OverviewContribution> loadContributions() {
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(DASHBOARD_CONTRIBUTION_EXTENSION_POINT);

        return List.of(elements).stream()
                .map(element -> {
                    try {
                        return element.createExecutableExtension(CLASS_EXECUTABLE_EXTENSION);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                })
                .filter(OverviewContribution.class::isInstance)
                .map(OverviewContribution.class::cast)
                .sorted()
                .collect(Collectors.toList());
    }

    private Composite createComposite(Composite parent, int style) {
        var composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

}
