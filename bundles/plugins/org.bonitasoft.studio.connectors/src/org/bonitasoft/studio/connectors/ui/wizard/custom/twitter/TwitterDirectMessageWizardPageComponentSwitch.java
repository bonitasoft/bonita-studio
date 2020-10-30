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

import java.io.InputStream;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import twitter4j.User;

public class TwitterDirectMessageWizardPageComponentSwitch extends
        PageComponentSwitch {

    private TwitterTools twitterTools;
    private Label imageLabel;
    private Label screenNameLabel;
    private Label descriptionLabel;
    private Label lastTweetsLabel;
    private List lastTweetsList;
    private List recipientList;
    private org.eclipse.swt.widgets.Text searchText;
    private ExpressionViewer userScreenNameViewer;

    public TwitterDirectMessageWizardPageComponentSwitch(
            IWizardContainer iWizardContainer, Composite parent,
            EObject container, ConnectorDefinition definition,
            ConnectorConfiguration connectorConfiguration,
            EMFDataBindingContext context,
            DefinitionResourceProvider messageProvider,
            AvailableExpressionTypeFilter connectorExpressionContentTypeFilter,
            TwitterTools twitterTools) {
        super(iWizardContainer, parent, container, definition,
                connectorConfiguration, context, messageProvider,
                connectorExpressionContentTypeFilter);
        this.twitterTools = twitterTools;
    }

    @Override
    protected ExpressionViewer createTextControl(Composite composite,
            Text object) {
        if ("recipientId".equals(object.getInputName())) {
            userScreenNameViewer = super.createTextControl(composite, object);
            final AutoCompleteExpressionProvider provider = new AutoCompleteExpressionProvider(
                    twitterTools.getFollowersAndFriendsName());
            userScreenNameViewer.setExpressionNatureProvider(provider);
            userScreenNameViewer.updateAutocompletionProposals();

            createSearchAndMoreInformationsGroup(composite);

            return userScreenNameViewer;
        }
        return super.createTextControl(composite, object);
    }

    private void createSearchAndMoreInformationsGroup(Composite composite) {
        final Group searchAndMoreInformationsGroup = new Group(composite,
                SWT.NONE);
        searchAndMoreInformationsGroup.setLayout(GridLayoutFactory
                .fillDefaults().numColumns(2).margins(5, 5).create());
        searchAndMoreInformationsGroup.setLayoutData(GridDataFactory
                .fillDefaults().grab(true, true).span(2, 1).create());
        searchAndMoreInformationsGroup.setText(Messages.searchUserTitle);

        createSearchComposite(searchAndMoreInformationsGroup);

        createMoreInformationsComposite(searchAndMoreInformationsGroup);
    }

    private void createSearchComposite(Group group) {
        final Composite searchComposite = new Composite(group, SWT.NONE);
        searchComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).margins(5, 5).create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(200, 100).grab(true, false).create());
        searchText = new org.eclipse.swt.widgets.Text(searchComposite,
                SWT.BORDER);
        searchText.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        final Button seachButton = new Button(searchComposite, SWT.FLAT);
        seachButton.setText(Messages.searchUserButton);
        seachButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                final String query = searchText.getText();
                java.util.List<String> users = twitterTools.searchUser(query);
                recipientList.removeAll();
                recipientList.setItems(users.toArray(new String[users.size()]));
            }
        });
        recipientList = new List(searchComposite, SWT.SINGLE | SWT.BORDER
                | SWT.V_SCROLL);
        recipientList.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).span(2, 1).create());
        recipientList.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                if (recipientList.getSelectionIndex() != -1) {
                    final String name = recipientList.getItem(recipientList
                            .getSelectionIndex());
                    userScreenNameViewer.getTextControl().setText(name);
                    final User user = twitterTools.getUser(name);
                    try {
                        final URL url = new URL(user.getBiggerProfileImageURL());
                        final InputStream is = url.openStream();
                        final Image avatar = new Image(Display.getCurrent(), is);
                        imageLabel.setImage(avatar);
                        is.close();
                    } catch (Exception exception) {
                        BonitaStudioLog.log(exception.getMessage());
                    }
                    screenNameLabel.setText(user.getScreenName());
                    descriptionLabel.setText(user.getDescription());
                    final java.util.List<String> tweets = twitterTools
                            .getLastweet(user.getScreenName());
                    lastTweetsList.removeAll();
                    lastTweetsList.setItems(tweets.toArray(new String[tweets
                            .size()]));
                }
            }
        });
    }

    private void createMoreInformationsComposite(Group group) {
        final Composite moreInformationsComposite = new Composite(group,
                SWT.NONE);
        moreInformationsComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).margins(5, 5).create());
        moreInformationsComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        imageLabel = new Label(moreInformationsComposite, SWT.NONE | SWT.BORDER);
        imageLabel.setSize(73, 73);
        imageLabel.setLayoutData(GridDataFactory.fillDefaults().span(1, 2)
                .grab(false, false).hint(73, 73).create());
        screenNameLabel = new Label(moreInformationsComposite, SWT.NONE);
        screenNameLabel.setText(Messages.searchUserEmptyField);
        screenNameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        descriptionLabel = new Label(moreInformationsComposite, SWT.NONE
                | SWT.WRAP);
        descriptionLabel.setText(Messages.searchUserEmptyField);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        lastTweetsLabel = new Label(moreInformationsComposite, SWT.NONE);
        lastTweetsLabel.setText(Messages.searchUserLastTweetsTitle);
        lastTweetsLabel.setLayoutData(GridDataFactory.fillDefaults().span(2, 1)
                .create());
        lastTweetsList = new List(moreInformationsComposite, SWT.SINGLE
                | SWT.BORDER);
        lastTweetsList.setLayoutData(GridDataFactory.fillDefaults().span(2, 1)
                .grab(true, true).create());
    }

}
