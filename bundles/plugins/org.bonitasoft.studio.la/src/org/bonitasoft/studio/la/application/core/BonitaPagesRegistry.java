/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BonitaPagesRegistry implements IRunnableWithProgress {

    public static final String ID_FIELD = "id";
    public static final String URL_TOKEN_FIELD = "urlToken";
    public static final String DESCRIPTION_FIELD = "description";
    private static final String DISPLAY_NAME_FIELD = "displayName";
    private static final String HIDDEN_FIELD = "isHidden";
    private static final String PROVIDED_FIELD = "isProvided";
    private static BonitaPagesRegistry INSTANCE;

    private List<EntryPage> pages = new ArrayList<>();
    private HttpClientFactory httpClientFactory = new HttpClientFactory();

    // Legacy ID, still supported for compatibility, but we do not want to display them for new users
    public List<String> pagesToExclude = new ArrayList<>();

    private BonitaPagesRegistry() {
        pagesToExclude.add("processlistinguser");
    }

    public static BonitaPagesRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BonitaPagesRegistry();
        }
        return INSTANCE;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (pages.isEmpty()) {
            APISession apiSession = null;
            try {
                apiSession = BOSEngineManager.getInstance().loginDefaultTenant(monitor);
            } catch (LoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
                throw new InvocationTargetException(e);
            } finally {
                if (apiSession != null) {
                    BOSEngineManager.getInstance().logoutDefaultTenant(apiSession);
                }
            }
            monitor.beginTask(Messages.retrieveBonitaPages, IProgressMonitor.UNKNOWN);
            login(httpClientFactory);
            addBonitaPages(requestBonitaPages(httpClientFactory));
            addCustomPages(requestCustomPages(httpClientFactory));
            monitor.done();
        }
    }

    private void addBonitaPages(String httpResponse) {
        try {
            JSONArray jsonArray = new JSONArray(httpResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject page = (JSONObject) jsonArray.get(i);
                if (!pagesToExclude.contains(page.get(ID_FIELD))) {
                    pages.add(new EntryPage((String) page.get(ID_FIELD),
                            (String) page.get(DISPLAY_NAME_FIELD),
                            (String) page.get(DESCRIPTION_FIELD),
                            true,
                            false));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException("Unable to parse bonitaPages http response", e);
        }
    }

    private void addCustomPages(String httpResponse) {
        try {
            JSONArray jsonArray = new JSONArray(httpResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject page = (JSONObject) jsonArray.get(i);
                if (page.getBoolean(PROVIDED_FIELD) && !pagesToExclude.contains(page.get(URL_TOKEN_FIELD))) {
                    pages.add(new EntryPage((String) page.get(URL_TOKEN_FIELD),
                            (String) page.get(DISPLAY_NAME_FIELD),
                            (String) page.get(DESCRIPTION_FIELD),
                            page.getBoolean(HIDDEN_FIELD),
                            false));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException("Unable to parse bonitaPages http response", e);
        }
    }

    private String requestBonitaPages(HttpClientFactory httpClientFactory) {
        try {
            return httpClientFactory.newBonitaPagesRequest().execute();
        } catch (IOException | HttpException e) {
            throw new RuntimeException("Unable to retrieve bonita pages", e);
        }
    }

    private String requestCustomPages(HttpClientFactory httpClientFactory) {
        try {
            return httpClientFactory.newCustomPagesRequest().execute();
        } catch (IOException | HttpException e) {
            throw new RuntimeException("Unable to retrieve bonita pages", e);
        }
    }

    private void login(HttpClientFactory httpClientFactory) {
        try {
            httpClientFactory.newLoginRequest().execute();
        } catch (IOException | HttpException e) {
            throw new RuntimeException("Unable to login", e);
        }
    }

    public List<EntryPage> getPages() {
        if (pages.isEmpty()) {
            Display.getDefault().syncExec(() -> {
                try {
                    PlatformUI.getWorkbench().getProgressService().run(true, false, this);
                } catch (InvocationTargetException | InterruptedException e) {
                    throw new RuntimeException("failed to retrieve default bonita pages", e);
                }
            });
        }
        pages.removeIf(Objects::isNull);
        return pages;
    }

    public boolean isDefaultPortalPage(String entryPage) {
        return getPages().stream()
                .filter(p -> Objects.equals(entryPage, p.getPageId()))
                .map(p -> !p.isCustom())
                .findFirst()
                .orElse(false);
    }

    public String getDisplayName(String pageName) {
        return getPages().stream()
                .filter(p -> Objects.equals(pageName, p.getPageId()))
                .findFirst()
                .map(EntryPage::getDisplayName)
                .orElse("");
    }

    public List<EntryPage> getCustomPages() {
        return getPages().stream()
                .filter(p -> !p.isHidden())
                .collect(Collectors.toList());
    }

    public Optional<EntryPage> getPage(String pageId) {
        return getPages().stream()
                .filter(p -> Objects.equals(pageId, p.getPageId()))
                .findFirst();
    }
}
