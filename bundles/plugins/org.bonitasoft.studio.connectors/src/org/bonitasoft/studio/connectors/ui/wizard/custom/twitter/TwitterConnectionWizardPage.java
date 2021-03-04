/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.custom.twitter;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;

import twitter4j.TwitterException;

public class TwitterConnectionWizardPage extends AbstractTwitterWizardPage {

    @Override
    public Control doCreateControl(Composite parent,
            EMFDataBindingContext context) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1)
                .margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());

        final Page page = getPage();
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(
                context, pageComposite);

        createTestValideInformationsGroup(pageComposite);

        for (Component component : page.getWidget()) {
            componentSwitch.doSwitch(component);
        }
        for (Section section : componentSwitch.getSectionsToExpand()) {
            section.setExpanded(true);
        }
        return mainComposite;
    }

    private void createTestValideInformationsGroup(Composite pageComposite) {
        final Group testValideInformationsGroup = new Group(pageComposite,
                SWT.NONE);
        testValideInformationsGroup.setLayoutData(GridDataFactory
                .fillDefaults().span(2, 1).grab(true, false).create());
        testValideInformationsGroup.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).margins(5, 5).create());
        testValideInformationsGroup
                .setText(Messages.testValideInformationTitle);

        final Label testValideInformationlabel = new Label(
                testValideInformationsGroup, SWT.NONE);
        testValideInformationlabel
                .setText(Messages.testValideInformationExplanation);
        testValideInformationlabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.BEGINNING, SWT.CENTER).grab(true, false).create());

        final Button testValideInformationButton = new Button(
                testValideInformationsGroup, SWT.FLAT);
        testValideInformationButton
                .setText(Messages.testValideInformationButtonLabel);
        testValideInformationButton.setLayoutData(GridDataFactory
                .fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
        testValideInformationButton
                .addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        super.widgetSelected(e);
                        testValideInformationButtonSelectionListener();
                    }
                });
    }

    private String getWizardField(String fieldName) {
        return ((Expression) getConnectorParameter(getInput(fieldName))
                .getExpression()).getContent();
    }

    private void createTwitterTool() {
        final String consumerKey = getWizardField("consumerKey");
        final String consumerSecret = getWizardField("consumerSecret");
        final String accessToken = getWizardField("accessToken");
        final String accessTokenSecret = getWizardField("accessTokenSecret");
        final String proxyHost = getWizardField("proxyHost");
        Integer proxyPort;
        try {
            proxyPort = Integer.parseInt(getWizardField("proxyPort"));
        } catch (Exception e) {
            proxyPort = null;
        }
        final String proxyUser = getWizardField("proxyUser");
        final String proxyPass = getWizardField("proxyPass");
        if (!proxyHost.isEmpty() && proxyPort != null) {
            if (!proxyUser.isEmpty() && !proxyPass.isEmpty()) {
                setTwitterTool(new TwitterTools(consumerKey, consumerSecret,
                        accessToken, accessTokenSecret, proxyHost, proxyPort,
                        proxyUser, proxyPass));
            } else {
                setTwitterTool(new TwitterTools(consumerKey, consumerSecret,
                        accessToken, accessTokenSecret, proxyHost, proxyPort));
            }
        } else {
            setTwitterTool(new TwitterTools(consumerKey, consumerSecret,
                    accessToken, accessTokenSecret));
        }
    }

    private void testValideInformationButtonSelectionListener() {
        createTwitterTool();
        final TwitterException error = getTwitterTool().testConnection();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                if (error != null) {
                    BonitaErrorDialog d = new BonitaErrorDialog(Display
                            .getDefault().getActiveShell(),
                            Messages.testValideInformationTitle,
                            Messages.testValideInformationError, error);
                    d.open();
                } else {
                    MessageDialog.openInformation(Display.getCurrent()
                            .getActiveShell(),
                            Messages.testValideInformationTitle,
                            Messages.testValideInformationSuccess);

                }
            }
        });
    }

    @Override
    public IWizardPage getNextPage() {
        createTwitterTool();
        final AbstractTwitterWizardPage newPage = (AbstractTwitterWizardPage) super.getNextPage();
        newPage.setTwitterTool(getTwitterTool());
        return newPage;
    }
}
