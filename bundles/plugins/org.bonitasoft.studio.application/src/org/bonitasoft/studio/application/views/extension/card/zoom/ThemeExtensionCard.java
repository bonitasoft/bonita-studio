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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCard;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget.Builder;
import org.eclipse.swt.widgets.Composite;

public class ThemeExtensionCard extends ExtensionCard {

    public static final String DEPLOY_COMMAND = "org.bonitasoft.studio.theme.deploy.from.dependency.command";

    private CommandExecutor commandExecutor;

    public ThemeExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, dep, bonitaDep);
        commandExecutor = new CommandExecutor();
    }

    @Override
    protected int getToolbarMazSize() {
        return super.getToolbarMazSize() + 1;
    }

    @Override
    protected List<Builder> getToolbarContributions() {
        var builder = new DynamicButtonWidget.Builder()
                .withLabel(Messages.deploy)
                .withTooltipText(Messages.deploy)
                .withImage(Pics.getImage(PicsConstants.deploy))
                .withHotImage(Pics.getImage(PicsConstants.deploy_hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("groupId", dep.getGroupId());
                    parameters.put("artifactId", dep.getArtifactId());
                    parameters.put("version", dep.getVersion());
                    if (!Strings.isNullOrEmpty(dep.getClassifier())) {
                        parameters.put("classifier", dep.getClassifier());
                    }

                    commandExecutor.executeCommand(DEPLOY_COMMAND, parameters);
                });

        return List.of(builder);
    }

}
