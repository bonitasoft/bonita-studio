/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.wizard.uipath.page;

import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.eclipse.swt.widgets.Composite;

public class UIPathGetJobAuthenticationWizardPage extends UiPathAuthenticationWizardPage {

    private static final String JOB_ID_INPUT = "jobId";
    private static final String JOB_ID_ID = "jobIdWidget";

    public UIPathGetJobAuthenticationWizardPage(String pageName) {
        super(pageName);
    }

    public UIPathGetJobAuthenticationWizardPage() {
        super();
    }

    @Override
    protected Composite createCloudComposite(Composite parent, PageComponentSwitchBuilder builder) {
        Composite composite = super.createCloudComposite(parent, builder);
        createText(builder, composite, JOB_ID_INPUT, JOB_ID_ID);
        return composite;
    }

    @Override
    protected Composite createOnPremiseComposite(Composite parent, PageComponentSwitchBuilder builder) {
        Composite composite = super.createOnPremiseComposite(parent, builder);
        createText(builder, composite, JOB_ID_INPUT, JOB_ID_ID);
        return composite;
    }

}
