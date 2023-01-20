/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.maven.dialog;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager.Result;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestMavenConnectionMessageDialog extends MessageDialogWithPrompt {

    public static final int CONFIGURE_ID = 42;

    private Result testResult;

    public TestMavenConnectionMessageDialog(Shell parentShell, Result testResult) {
        super(parentShell, Messages.connectionFailedTitle, null, null, null,
                testResult.canReachMavenCentral() ? MessageDialog.WARNING : MessageDialog.ERROR,
                buttonsLabelToId(), 0,
                Messages.ignoreConnectionTestAtStartup,
                ApplicationPlugin.getDefault().getPreferenceStore()
                        .getBoolean(BonitaMavenConfigurationManager.SKIP_MAVEN_CONFIGURATION_CKECK_PREFERENCE));
        this.testResult = testResult;
        setButtonLabels(getButtonLabels());
        setPrefStore(ApplicationPlugin.getDefault().getPreferenceStore());
        setPrefKey(BonitaMavenConfigurationManager.SKIP_MAVEN_CONFIGURATION_CKECK_PREFERENCE);
        setDetails(buildDetailsMessage(testResult));
        this.linkSelectionListener = linkListener();
    }

    @Override
    protected String getDetailsSectionTitle() {
        return Messages.possibleReasons;
    }

    private Optional<Listener> linkListener() {
        return Optional.of(e -> openBrowser(e.text));
    }

    private void openBrowser(String text) {
        try {
            Desktop.getDesktop().browse(RedirectURLBuilder.createURI(getRedirectId(text)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String getRedirectId(String text) {
        if (text.toLowerCase().contains("maven")) { // Maven central status
            return "728";
        }
        return "755"; // JFrog Cloud status
    }

    private String buildDetailsMessage(Result testResult) {
        var details = "";
        if (!testResult.canReachMavenCentral()) {
            details += Messages.cannotReachMavenCentralRepositoryMessage;
            details += System.lineSeparator();
        }
        if (!testResult.canReachBonitaArtifactRepository()) {
            details += Messages.cannotReachBonitaArtifactRepositoryMessage;
            details += System.lineSeparator();
        }
        return details;
    }

    @Override
    protected Control createMessageArea(Composite composite) {
        // create image
        final Image image = getImage();
        if (image != null) {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            GridDataFactory.fillDefaults()
                    .align(SWT.CENTER, SWT.BEGINNING)
                    .applyTo(imageLabel);
        }
        var messageArea = new Composite(composite, SWT.NONE);
        messageArea.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 0).create());
        GridDataFactory
                .fillDefaults()
                .align(SWT.FILL, SWT.BEGINNING)
                .grab(true, false)
                .hint(
                        convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                        SWT.DEFAULT)
                .applyTo(messageArea);
        var header = new Label(messageArea, SWT.WRAP);
        header.setText(Messages.connectionFailedMessage);
        GridDataFactory
                .fillDefaults()
                .align(SWT.FILL, SWT.BEGINNING)
                .grab(true, false)
                .hint(
                        convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                        SWT.DEFAULT)
                .applyTo(header);

        var centralStatus = new CLabel(messageArea, SWT.WRAP);
        centralStatus.setImage(testResult.canReachMavenCentral() ? Pics.getImage(PicsConstants.checkmark)
                : Pics.getImage(PicsConstants.error));
        centralStatus.setText(Messages.mavenCentralConnection);
        GridDataFactory
                .fillDefaults()
                .align(SWT.FILL, SWT.BEGINNING)
                .grab(true, false)
                .indent(0, 10)
                .hint(
                        convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                        SWT.DEFAULT)
                .applyTo(centralStatus);

        if (!PlatformUtil.isACommunityBonitaProduct()) {
            var barStatus = new CLabel(messageArea, SWT.WRAP);
            barStatus.setImage(testResult.canReachBonitaArtifactRepository()
                    ? Pics.getImage(PicsConstants.checkmark) : Pics.getImage(PicsConstants.warning));
            barStatus.setText(Messages.bonitaArtifactRepositoryConnection);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(barStatus);
            if(testResult.isMissingBarCredentials() 
                    || testResult.hasInvalidBarCredentials()) {
                var extraInfo = new Label(messageArea, SWT.WRAP);
                extraInfo.setLayoutData(GridDataFactory.fillDefaults().indent(25, 0).grab(true, false).create());
                String extraMessage = null;
                if(testResult.isMissingBarCredentials()) {
                    extraMessage = Messages.noAuthenticationForBar;
                }else if(testResult.hasInvalidBarCredentials()) {
                    extraMessage = Messages.invalidBarCredentials;
                }
                extraInfo.setText(extraMessage);
            }
        }
        return composite;
    }

    private static LinkedHashMap<String, Integer> buttonsLabelToId() {
        var labelToId = new LinkedHashMap<String, Integer>();
        labelToId.put(Messages.configure, CONFIGURE_ID);
        labelToId.put(IDialogConstants.IGNORE_LABEL, IDialogConstants.IGNORE_ID);
        labelToId.put(IDialogConstants.RETRY_LABEL, IDialogConstants.RETRY_ID);
        return labelToId;
    }

    @Override
    protected void buttonPressed(int buttonId) {
        setReturnCode(buttonId);
        close();

        var prefStore = getPrefStore();
        prefStore.setValue(getPrefKey(), getToggleState());
    }

}
