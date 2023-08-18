/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Executors;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Runs a HTTP query on the https://search.maven.org API to look for a match for a given jar file.
 * This is used when importing extension as file in a Bonita Project or updating older Bonita project.
 */
public class MavenCentralJarLookupOperation implements IRunnableWithProgress {

    private static final String DEFAULT_MAVEN_SEARCH_ENDPOINT = "https://search.maven.org/solrsearch/select?q=1:%s";
    static final String MAVEN_CENTRAL_REPOSITORY_URL = "https://search.maven.org";
    private static final String MAVEN_SEARCH_URI = System.getProperty("org.bonitasoft.studio.maven.search.endpoint",
            DEFAULT_MAVEN_SEARCH_ENDPOINT);
    private static final int REQUEST_TIMEOUT = 5; // seconds
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(REQUEST_TIMEOUT))
            .executor(Executors.newSingleThreadExecutor())
            .build();

    private DependencyLookup result;
    private InputStreamSupplier fileToLookup;
    private IStatus status = Status.OK_STATUS;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public MavenCentralJarLookupOperation(InputStreamSupplier fileToLookup) {
        this.fileToLookup = fileToLookup;
    }
 
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(String.format(Messages.lookupDependencyFor, fileToLookup.getName()),
                IProgressMonitor.UNKNOWN);
        try {
            result = lookup();
        } catch (CoreException e) {
            status = e.getStatus();
        }
    }

    private DependencyLookup lookup() throws CoreException {
        var file = fileToLookup.toTempFile();
        var defaultGav = new GAV(DependencyLookup.defaultGroupId(), file.getName().replace(".jar", ""), "1.0.0");
        try {
            var sha1 = createSha1(file);
            try {
                var request = HttpRequest.newBuilder()
                        .uri(new URI(String.format(MAVEN_SEARCH_URI, sha1)))
                        .timeout(Duration.ofSeconds(REQUEST_TIMEOUT))
                        .build();
                var response = HTTP_CLIENT.send(request, BodyHandlers.ofByteArray());
                BonitaStudioLog.debug(response.toString(), CommonRepositoryPlugin.PLUGIN_ID);
                
                if (response.statusCode() == 200) {
                    var searchResponse = objectMapper.readValue(response.body(), SearchResponse.class);
                    var status = searchResponse.getResponse().getNumFound() > 0 ? DependencyLookup.Status.FOUND
                            : DependencyLookup.Status.NOT_FOUND;
                    if (status == DependencyLookup.Status.FOUND) {
                        var fileName = file.getName();
                        var document = searchResponse.getResponse().getDocs().get(0);
                        var gav = new GAV(document.getG(), document.getA(), document.getV(), null, Objects.equals(document.getP(), "bundle") ? "jar" : document.getP(), Artifact.SCOPE_COMPILE);
                        for(var doc : searchResponse.getResponse().getDocs()) {
                            String classifier = findMatchingClassifier(fileName, doc);
                            if(classifier != null) {
                                gav = new GAV(doc.getG(), doc.getA(), doc.getV(), classifier, doc.getP(), Artifact.SCOPE_COMPILE);
                            }
                            // Handle relocated artifacts
                            try(var is = new URL(String.format("https://search.maven.org/remotecontent?filepath=%s/%s/%s/%s-%s.pom", doc.getG().replace(".", "/"), 
                                    doc.getA(),
                                    doc.getV(),
                                    doc.getA(),
                                    doc.getV())).openStream()){
                                var model = MavenPlugin.getMaven().readModel(is);
                                if(model.getDistributionManagement() != null && model.getDistributionManagement().getRelocation() != null) {
                                    var relocation = model.getDistributionManagement().getRelocation();
                                    gav = new GAV(Optional.ofNullable(relocation.getGroupId()).orElse(doc.getG()), 
                                            Optional.ofNullable(relocation.getArtifactId()).orElse(doc.getA()), 
                                            Optional.ofNullable(relocation.getVersion()).orElse(doc.getV()), 
                                            classifier, 
                                            document.getP(),
                                            Artifact.SCOPE_COMPILE);
                                }
                            }
                        }
                        var dl = new DependencyLookup(file.getAbsolutePath(),
                                sha1,
                                status,
                                gav,
                                MAVEN_CENTRAL_REPOSITORY_URL);
                        var pomProperties = DependencyLookup.readPomProperties(file);
                        if (pomProperties.isPresent()) {
                            return checkGAVConsistency(pomProperties.get(), dl);
                        }
                        return dl;
                    }
                }
                return new DependencyLookup(file.getAbsolutePath(),
                        sha1,
                        DependencyLookup.Status.NOT_FOUND,
                        defaultGav,
                        "");
            } catch (URISyntaxException e) {
                throw new CoreException(
                        Status.error(String.format("Invalid URI for maven search endpoint: %s", MAVEN_SEARCH_URI), e));
            } catch (HttpTimeoutException e) {
                BonitaStudioLog.warning(
                        String.format("Central search request timed-out after %s seconds for %s", REQUEST_TIMEOUT, file.getName()),
                        CommonRepositoryPlugin.PLUGIN_ID);
                return new DependencyLookup(file.getAbsolutePath(),
                        sha1,
                        DependencyLookup.Status.NOT_FOUND,
                        defaultGav,
                        "");
            } catch (IOException e) {
                BonitaStudioLog.warning(
                        String.format("Central search request failed for %s: %s", file.getName(), e.getClass()), CommonRepositoryPlugin.PLUGIN_ID);
                return new DependencyLookup(file.getAbsolutePath(),
                        sha1,
                        DependencyLookup.Status.NOT_FOUND,
                        defaultGav,
                        "");
            } catch (InterruptedException e) {
                throw new CoreException(Status.error("Failed to lookup dependency", e));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CoreException(Status.error(String.format("Failed to compute SHA-1 for %s", file.getName()), e));
        }
    }

    private String findMatchingClassifier(String fileName, Document doc) {
        for(var ec : doc.getEc()) {
            if(!ec.equals(".jar") && fileName.endsWith(ec)) {
                return ec.substring(1,ec.length()-4);
            }
        }
        return null;
    }

    private DependencyLookup checkGAVConsistency(Properties properties, DependencyLookup dl) {
        var groupId = properties.get("groupId");
        var artifactId = properties.get("artifactId");
        var version = properties.get("version");
        if (!Objects.equals(dl.getGroupId(), groupId)
                || !Objects.equals(dl.getArtifactId(), artifactId)
                || !Objects.equals(dl.getVersion(), version)) {
            dl.setStatus(DependencyLookup.Status.NOT_FOUND);
        }
        return dl;
    }

    public DependencyLookup getResult() {
        return result;
    }

    public IStatus getStatus() {
        return status;
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

    private static String createSha1(File file) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        try (var fis = new FileInputStream(file)) {
            int n = 0;
            byte[] buffer = new byte[8192];
            while (n != -1) {
                n = fis.read(buffer);
                if (n > 0) {
                    digest.update(buffer, 0, n);
                }
            }
            return new HexBinaryAdapter().marshal(digest.digest());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static class SearchResponse {

        private ResponseHeader responseHeader;
        private Response response;
        
        public ResponseHeader getResponseHeader() {
            return responseHeader;
        }
        
        public void setResponseHeader(ResponseHeader responseHeader) {
            this.responseHeader = responseHeader;
        }
        
        public Response getResponse() {
            return response;
        }
        
        public void setResponse(Response response) {
            this.response = response;
        }

    }

    static class ResponseHeader {

        private int status;

        @JsonProperty("QTime")
        private long qTime;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getqTime() {
            return qTime;
        }

        public void setqTime(long qTime) {
            this.qTime = qTime;
        }

    }

    static class Response {

        private int numFound;
        private int start;
        private List<Document> docs;

        public int getNumFound() {
            return numFound;
        }

        public void setNumFound(int numFound) {
            this.numFound = numFound;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public List<Document> getDocs() {
            return docs;
        }

        public void setDocs(List<Document> docs) {
            this.docs = docs;
        }

    }

    static class Document {

        private String id;
        private String g;
        private String a;
        private String v;
        private String p;
        private List<String> ec;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getG() {
            return g;
        }

        public void setG(String g) {
            this.g = g;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public List<String> getEc() {
            return ec;
        }

        public void setEc(List<String> ec) {
            this.ec = ec;
        }

    }
}
