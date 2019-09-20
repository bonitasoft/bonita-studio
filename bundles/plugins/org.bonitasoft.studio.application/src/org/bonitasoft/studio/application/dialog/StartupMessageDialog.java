/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.dialog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;

public class StartupMessageDialog extends MessageDialogWithPrompt {

    private Link startMessage;
    private Label title;

    public StartupMessageDialog(Shell parentShell) {
        super(parentShell,
                "",
                null,
                "",
                MessageDialog.INFORMATION,
                getButtonLabelsFor(MessageDialog.INFORMATION),
                0,
                Messages.doNotShowMeAgain,
                false);
    }

    public static int open(Shell parent, int style, IPreferenceStore store, String key) {
        final StartupMessageDialog dialog = new StartupMessageDialog(parent);
        dialog.setShellStyle(dialog.getShellStyle() | style | SWT.SHEET);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        return dialog.open();
    }

    @Override
    protected Control createMessageArea(Composite composite) {
        if (message != null) {


            title = new Label(composite, SWT.CENTER);
            title.setText(String.format(Messages.startDialogTitle, ProductVersion.CURRENT_VERSION));
            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .grab(true, false)
                    .applyTo(title);

            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .grab(true, false)
                    .applyTo(new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL));

            startMessage = new Link(composite, SWT.WRAP | SWT.NO_FOCUS);
            startMessage
                    .setText(String.format(Messages.startDialogMsg, Messages.releaseNote, Messages._6xFormsDontWorkAnymore));
            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(startMessage);
            startMessage.addListener(SWT.Selection, event -> openBrowser(event.text));

            createDetailsSection(composite);
        }
        return composite;
    }

    private void openBrowser(String text) {
        try {
            new OpenBrowserOperation(new URL(getLinkFor(text))).execute();
        } catch (MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected String getLinkFor(String text) {
        if (Objects.equals(Messages.releaseNote, text)) {
            return String.format(
                    "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=676&bos_redirect_product=bos&bos_redirect_major_version=%s",
                    ProductVersion.majorVersion());
        }
        if (Objects.equals(Messages._6xFormsDontWorkAnymore, text)) {
            return String.format(
                    "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=677&bos_redirect_product=bos&bos_redirect_major_version=%s",
                    ProductVersion.majorVersion());
        }
        throw new IllegalArgumentException("Unsupported link");
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        getButton(IDialogConstants.OK_ID).setText(Messages.letsStart);
    }

    private void createDetailsSection(Composite parent) {

        Section detailsSection = new Section(parent, Section.TWISTIE | Section.NO_TITLE_FOCUS_BOX);
        detailsSection.setLayout(GridLayoutFactory.fillDefaults().create());
        detailsSection.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        detailsSection.setText(org.bonitasoft.studio.common.Messages.moreDetails);

        Composite detailsComposite = new Composite(detailsSection, SWT.NONE);
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label details = new Label(detailsComposite, SWT.NONE);
        details.setLayoutData(GridDataFactory.fillDefaults().create());
        details.setText(Messages.startDialogDetails);

        detailsSection.setClient(detailsComposite);

        detailsSection.addExpansionListener(new ExpansionAdapter() {

            @Override
            public void expansionStateChanged(ExpansionEvent e) {
                parent.getShell().pack();
            }
        });
    }

}
