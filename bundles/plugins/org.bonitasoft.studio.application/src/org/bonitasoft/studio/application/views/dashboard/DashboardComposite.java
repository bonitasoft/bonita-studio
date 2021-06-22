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
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.DashboardContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DashboardComposite extends Composite {

    private static final String CLASS_EXECUTABLE_EXTENSION = "class";
    private static final String DASHBOARD_CONTRIBUTION_EXTENSION_POINT = "org.bonitasoft.studio.common.dashboardContribution";

    private RepositoryAccessor repositoryAccessor;
    private ScrolledComposite scrolledComposite;

    public DashboardComposite(Composite parent, RepositoryAccessor repositoryAccessor) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;

        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
        scrolledComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        scrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var cardComposite = createComposite(scrolledComposite, SWT.NONE);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createCards(cardComposite);

        scrolledComposite.setContent(cardComposite);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
    }

    private void createCards(Composite parent) {
        List<DashboardContribution> contributions = loadContributions();
        createCards(parent, contributions, DashboardContribution.Category.PROCESS_DATA, Messages.processAndData);
        createCards(parent, contributions, DashboardContribution.Category.USERS, Messages.users);
        createCards(parent, contributions, DashboardContribution.Category.PROJECT,
                Messages.project);
    }

    private void createCards(Composite parent,
            List<DashboardContribution> contributions,
            DashboardContribution.Category category,
            String sectionTitle) {
        List<DashboardContribution> contributionsToCreate = contributions.stream()
                .filter(contribution -> Objects.equals(contribution.getCategory(), category))
                .sorted()
                .collect(Collectors.toList());

        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().margins(40, 20).spacing(20, 20)
                        .numColumns(3)
                        .equalWidth(true)
                        .create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        var titleLabel = new Label(composite, SWT.WRAP);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        titleLabel.setText(sectionTitle);
        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        contributionsToCreate.forEach(contribution -> new DashboardCard(composite, contribution));
    }

    private List<DashboardContribution> loadContributions() {
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
                .filter(DashboardContribution.class::isInstance)
                .map(DashboardContribution.class::cast)
                .collect(Collectors.toList());
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

}
