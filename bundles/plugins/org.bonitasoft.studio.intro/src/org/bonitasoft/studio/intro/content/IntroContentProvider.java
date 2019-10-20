/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.intro.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.content.actions.ExecuteCommandContentProvider;
import org.bonitasoft.studio.intro.i18n.Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.Element;

public class IntroContentProvider implements IIntroXHTMLContentProvider {

    protected final static List<DOMContentProvider> CONTENT_PROVIDERS = new ArrayList<>();
    private final static Map<String, String> VIDEO_CAMP_SERIES_ID_BY_LOCALE = new HashMap<>();
    private static final String LOCATION_HEADER = "Location";

    static {
        VIDEO_CAMP_SERIES_ID_BY_LOCALE.put("fr", "699");
        VIDEO_CAMP_SERIES_ID_BY_LOCALE.put("es", "700");

        CONTENT_PROVIDERS.add(new RecentFilesContentProvider());
        CONTENT_PROVIDERS.add(new LogoContentProvider());

        
  
        CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("new-diagram-action",
                "org.bonitasoft.studio.diagram.command.newDiagram", Messages.newProcess));
        CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("open-diagram-action",
                "org.bonitasoft.studio.diagram.command.openDiagram", Messages.openAProcess));
        CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("import-action",
                "org.bonitasoft.studio.importer.bos.command", Messages.importLabel));


        try {
            CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("procurement-example-link",
                    "org.bonitasoft.studio.importer.bos.command", Messages.procurementExample,
                    buildImportParameters("704", "procurement-example")));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        try {
            CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("expense-report-example-link",
                    "org.bonitasoft.studio.importer.bos.command", Messages.expenseReportExample,
                    buildImportParameters("707", "expense-report-example")));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        try {
            CONTENT_PROVIDERS.add(new ExecuteCommandContentProvider("user-application-link",
                    "org.bonitasoft.studio.importer.bos.command", Messages.importUserApplication,
                    buildImportParameters("713", null)));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }

        CONTENT_PROVIDERS.add(new SectionTitleContentProvider("design-title", Messages.design));
        CONTENT_PROVIDERS.add(new SectionTitleContentProvider("recent-title", Messages.recent));
        CONTENT_PROVIDERS.add(new SectionTitleContentProvider("resources-title", Messages.resources));
        CONTENT_PROVIDERS.add(new SectionTitleContentProvider("learn-title", Messages.learn));
        CONTENT_PROVIDERS.add(new SectionTitleContentProvider("help-title", Messages.help));

        CONTENT_PROVIDERS.add(new LinkContentProvider("release-notes-link",
                redirectUrl("696"),
                Messages.releaseNotes));
        CONTENT_PROVIDERS.add(new LinkContentProvider("getting-started-link",
                redirectUrl("697"),
                Messages.gettingStarted));
        CONTENT_PROVIDERS.add(new LinkContentProvider("camp-video-series-link",
                redirectUrl(VIDEO_CAMP_SERIES_ID_BY_LOCALE.getOrDefault(Locale.getDefault().getLanguage(), "698")),
                Messages.bonitaCampVideoSeries));
        CONTENT_PROVIDERS.add(new LinkContentProvider("training-link",
                redirectUrl("701"),
                Messages.officialTraining));
        CONTENT_PROVIDERS.add(new LinkContentProvider("forum-link",
                redirectUrl("77"),
                Messages.qaForum));
        CONTENT_PROVIDERS.add(new LinkContentProvider("feedback-link",
                redirectUrl("708"),
                Messages.productFeedback));
        CONTENT_PROVIDERS.add(new LinkContentProvider("report-issue-link",
                redirectUrl("709"),
                Messages.reportAnIssue));
        CONTENT_PROVIDERS.add(new LinkContentProvider("documentation-link",
                redirectUrl("695"),
                Messages.documentation));
        CONTENT_PROVIDERS.add(new LinkContentProvider("community-contribution-link",
                redirectUrl("81"),
                Messages.communityContributions));
        CONTENT_PROVIDERS.add(new LinkContentProvider("blog-link",
                redirectUrl("703"),
                Messages.blog));
        CONTENT_PROVIDERS.add(new LinkContentProvider("videos-podcasts-link",
                redirectUrl("702"),
                Messages.videosAndPodcasts));

        CONTENT_PROVIDERS.add(new SVGContentProvider("design-icon", "pencil-ruler.svg"));
        CONTENT_PROVIDERS.add(new SVGContentProvider("recent-icon", "clock.svg"));
        CONTENT_PROVIDERS.add(new SVGContentProvider("resources-icon", "toolbox.svg"));
        CONTENT_PROVIDERS.add(new SVGContentProvider("learn-icon", "graduation-cap.svg"));
        CONTENT_PROVIDERS.add(new SVGContentProvider("help-icon", "life-ring.svg"));
    }

    protected static Map<String, Object> buildImportParameters(String redirectId, String projectName)
            throws UnsupportedEncodingException, MalformedURLException, IOException {
        Map<String, Object> procurementParam = new HashMap<String, Object>();
        procurementParam.put("org.bonitasoft.studio.importer.bos.commandparameter.file",
                URLEncoder.encode(resolveRedirection(new URL(redirectUrl(redirectId))).toString(), "UTF-8"));
        procurementParam.put("org.bonitasoft.studio.importer.bos.commandparameter.targetProjectName", projectName);
        return procurementParam;
    }

    public static URL resolveRedirection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (isRedirect(connection.getResponseCode())) {
            String newUrl = connection.getHeaderField(LOCATION_HEADER);
            return new URL(newUrl);
        }
        return url;
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

    public static String redirectUrl(String redirectId) {
        return String.format(
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=%s&bos_redirect_product=bos&bos_redirect_major_version=%s",
                redirectId,
                ProductVersion.majorVersion());
    }

    @Override
    public void createContent(String id, Element parent) {
        CONTENT_PROVIDERS.stream()
                .filter(match(id))
                .forEach(contenProvider -> contenProvider.createContent(parent.getOwnerDocument(), parent));
    }

    @Override
    public void init(IIntroContentProviderSite site) {
    }

    @Override
    public void createContent(String id, PrintWriter out) {
    }

    @Override
    public void createContent(String id, Composite parent, FormToolkit toolkit) {
    }

    @Override
    public void dispose() {
    }

    private Predicate<? super DOMContentProvider> match(String id) {
        return provider -> provider.appliesTo(id);
    }

}
