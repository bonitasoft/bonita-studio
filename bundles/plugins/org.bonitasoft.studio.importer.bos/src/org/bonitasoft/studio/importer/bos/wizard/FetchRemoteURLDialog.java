/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bos.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class FetchRemoteURLDialog extends Dialog {

    private static final String FILENAME_PARAM = "filename=";
    private static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    private static final String BOS_FILE_EXTENSION = ".bos";
    private static final String APPLICATION_CONTENT_TYPE = "application";
    private static final String LOCATION_HEADER = "Location";
    private static final int MARGIN_BOTTOM = 50;
    private static final String URL_HISTORY = "url_history";
    private String url;
    private DataBindingContext dbc;

    public FetchRemoteURLDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.fetchRemoteDialogTitle);
    }

    @Override
    protected Point getInitialSize() {
        Point initialSize = super.getInitialSize();
        initialSize.y = initialSize.y + MARGIN_BOTTOM;
        return initialSize;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        dbc = new DataBindingContext();

        IObservableValue<String> urlObservable = PojoProperties.value("url").observe(this);

        new ComboWidget.Builder()
                .withLabel(Messages.url)
                .labelAbove()
                .widthHint(600)
                .withDelay(500)
                .withTargetToModelStrategy(updateValueStrategy().withValidator(urlValidator()))
                .withItems(loadFromHistory())
                .bindTo(urlObservable)
                .inContext(dbc)
                .createIn(composite);

        return composite;
    }

    private String[] loadFromHistory() {
        String urls = ImporterPlugin.getDefault().getDialogSettings().get(URL_HISTORY);
        if (urls != null && !urls.isEmpty()) {
            return Stream.of(urls.split(",")).map(String::trim).toArray(String[]::new);
        }
        return new String[0];
    }

    @Override
    protected void okPressed() {
        saveUrlInHistory();
        super.okPressed();
    }

    private void saveUrlInHistory() {
        String urls = ImporterPlugin.getDefault().getDialogSettings().get(URL_HISTORY);
        if (urls == null || urls.isEmpty()) {
            urls = getUrl();
        } else {
            List<String> history = Stream.of(urls.split(",")).map(String::trim).collect(Collectors.toList()); 
            history.add(getUrl());
            urls = history.stream().distinct().collect(Collectors.joining(","));
        }
        ImporterPlugin.getDefault().getDialogSettings().put(URL_HISTORY, urls);
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        DialogSupport.create(this, dbc);
        return control;
    }

    private IValidator<String> urlValidator() {
        return value -> {
            if (value == null || value.trim().isEmpty()) {
                return ValidationStatus.error(Messages.enterAValidURL);
            }
            return validateHTTPUrl(value);
        };
    }

    protected IStatus validateHTTPUrl(String urlString) {
        URLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = url.openConnection();
            if (connection instanceof HttpURLConnection) {
                String contentType = getContentType((HttpURLConnection) connection);
                if (contentType == null || !contentType.contains(APPLICATION_CONTENT_TYPE)) {
                    return ValidationStatus.error(contentType != null
                            ? String.format(Messages.invalidContentType, contentType) : Messages.cannotRetrieveContentType);
                }
                String headerField = ((HttpURLConnection)connection).getHeaderField(CONTENT_DISPOSITION_HEADER);
                String filename = new File(url.getFile()).getName();
                if( headerField != null && !headerField.isEmpty() && headerField.contains(FILENAME_PARAM)) {
                    filename = headerField.split("=")[1];
                }
                if(!filename.toLowerCase().endsWith(BOS_FILE_EXTENSION)) {
                    return ValidationStatus.error(Messages.invalidBosArchiveType);
                }
            } else {
                return ValidationStatus.error(Messages.onlyHTTPUrlSupported);
            }
            return ValidationStatus.ok();
        } catch (IOException e) {
            return ValidationStatus.error(String.format(Messages.invalidURL, e.getLocalizedMessage()));
        } finally {
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
        }
    }

    private String getContentType(HttpURLConnection connection) throws IOException {
        if (isRedirect(connection.getResponseCode())) {
            String newUrl = connection.getHeaderField(LOCATION_HEADER);
            setUrl(newUrl);
            URL url = new URL(newUrl);
            connection = (HttpURLConnection) url.openConnection();
            return getContentType(connection);
        }
        return connection.getContentType();
    }

    private static boolean isRedirect(int statusCode) {
        if (statusCode != HttpURLConnection.HTTP_OK) {
            if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                    || statusCode == HttpURLConnection.HTTP_SEE_OTHER) {
                return true;
            }
        }
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
