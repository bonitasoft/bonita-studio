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
package org.bonitasoft.studio.businessobject.overview;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.extension.OverviewContribution;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class BdmOverviewContribution implements OverviewContribution {

    private static final String DEFINE_BDM_COMMAND = "org.bonitasoft.studio.businessobject.define";
    private BusinessObjectModelRepositoryStore repositoryStore;

    public BdmOverviewContribution() {
        repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Override
    public String getName() {
        return Messages.businessDataModel;
    }

    @Override
    public String getDescription() {
        return Messages.bdmOverviewContribution;
    }

    @Override
    public String getDocumentationLink() {
        return "https://documentation.bonitasoft.com/bonita/latest/define-and-deploy-the-bdm";
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.bdm32);
    }

    @Override
    public void contributeActions(Composite parent) {
        var toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createToolbarButtons(toolbarComposite);
    }

    private void createToolbarButtons(Composite parent) {
        if (bdmIsPresent()) {
            new DynamicButtonWidget.Builder()
                    .withText(org.bonitasoft.studio.common.Messages.open)
                    .withImage(Pics.getImage(PicsConstants.open))
                    .withHotImage(Pics.getImage(PicsConstants.openHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .withId(SWTBotConstants.openArtifactButtonId(BusinessObjectModelFileStore.BOM_FILENAME))
                    .onClick(e -> commandExecutor.executeCommand(DEFINE_BDM_COMMAND, null))
                    .createIn(parent);

        } else {
            new DynamicButtonWidget.Builder()
                    .withText(org.bonitasoft.studio.common.Messages.create)
                    .withImage(Pics.getImage(PicsConstants.add_simple))
                    .withHotImage(Pics.getImage(PicsConstants.add_simple_hot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .withId(SWTBotConstants.createArtifactButtonId(Messages.businessDataModel))
                    .onClick(e -> {
                        commandExecutor.executeCommand(DEFINE_BDM_COMMAND, null);
                        refreshToolbar(parent);
                    })
                    .createIn(parent);
        }
    }

    private void refreshToolbar(Composite toolbarComposite) {
        Display.getDefault().asyncExec(() -> {
            List.of(toolbarComposite.getChildren()).forEach(Control::dispose);
            createToolbarButtons(toolbarComposite);
            toolbarComposite.getParent().layout();
        });
    }

    private boolean bdmIsPresent() {
        return repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, false) != null;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
