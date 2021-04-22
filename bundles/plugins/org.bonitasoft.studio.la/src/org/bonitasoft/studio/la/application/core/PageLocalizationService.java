/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpException;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageLocalizationService {

    private static PageLocalizationService INSTANCE;
    private Map<String, Map<String, String>> locales = new HashMap<>();

    private HttpClientFactory httpClientFactory = new HttpClientFactory();

    private PageLocalizationService() {

    }

    public static PageLocalizationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageLocalizationService();
        }
        return INSTANCE;
    }

    public String translate(String key, String locale) {
        Map<String, String> l10n = locales.get(locale);
        if (l10n == null) {
            l10n = new HashMap<>();
            if (!Objects.equals("en", locale)) {
                try {
                    httpClientFactory.newLoginRequest().execute();
                    HttpRequest<String> httpRequest = httpClientFactory.newLocalizationRequest(locale);
                    String httpResponse = httpRequest.execute();
                    JSONArray jsonArray = new JSONArray(httpResponse);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = (JSONObject) jsonArray.get(i);
                        String value = item.getString("value");
                        if(value != null && !value.isEmpty()) {
                            String l10nKey = item.getString("key");
                            if(l10nKey != null && !l10nKey.isEmpty()) {
                                l10n.put(l10nKey, value);
                            }
                        }
                    }
                } catch (IOException | HttpException | JSONException e) {
                    BonitaStudioLog.error(e);
                }
            }
            locales.put(locale, l10n);
        }
        return l10n.getOrDefault(key, key);
    }

}
