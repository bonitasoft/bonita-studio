package org.bonitasoft.studio.application.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.junit.Before;
import org.junit.Test;

public class MavenSettingsValidatorTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String URL = "url";

    private MavenSettingsValidator validator;
    private MultiStatus multiStatus;

    @Before
    public void init() {
        validator = new MavenSettingsValidator();
        multiStatus = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, "");
    }

    @Test
    public void should_validate_repository_without_id() {
        var repo = new Repository();
        validator.validateRepository(repo, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(Messages.repositoryIdMandatory);
    }

    @Test
    public void should_validate_repository_without_url() {
        var repo = new Repository();
        repo.setId(ID);
        repo.setName(NAME);
        validator.validateRepository(repo, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(String.format(Messages.repositoryUrlMandatory, ID));
    }

    @Test
    public void should_validate_valid_repository() {
        var repo = new Repository();
        repo.setId(ID);
        repo.setName(NAME);
        repo.setUrl(URL);
        validator.validateRepository(repo, multiStatus);
        StatusAssert.assertThat(multiStatus).isOK();
    }

    @Test
    public void should_validate_proxy_without_id() {
        var proxy = new Proxy();
        proxy.setId("");
        validator.validateProxy(proxy, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(Messages.proxyIdMandatory);
    }

    @Test
    public void should_validate_proxy_without_host() {
        var proxy = new Proxy();
        proxy.setId(ID);
        validator.validateProxy(proxy, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(Messages.proxyHostMandatory);
    }

    @Test
    public void should_validate_valid_proxy() {
        var proxy = new Proxy();
        proxy.setId(ID);
        proxy.setHost(URL);
        validator.validateProxy(proxy, multiStatus);
        StatusAssert.assertThat(multiStatus).isOK();
    }

    @Test
    public void should_validate_mirror_without_id() {
        var mirror = new Mirror();
        mirror.setId("");
        validator.validateMirror(mirror, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(Messages.mirrorIdMandatory);
    }

    @Test
    public void should_validate_mirror_without_url() {
        var mirror = new Mirror();
        mirror.setId(ID);
        validator.validateMirror(mirror, multiStatus);
        StatusAssert.assertThat(multiStatus).isError();
        List<IStatus> status = List.of(multiStatus.getChildren());
        assertThat(status).extracting(IStatus::getMessage).contains(String.format(Messages.mirrorUrlMandatory, ID));
    }

    @Test
    public void should_validate_valid_mirror() {
        var mirror = new Mirror();
        mirror.setId(ID);
        mirror.setUrl(URL);
        validator.validateMirror(mirror, multiStatus);
        StatusAssert.assertThat(multiStatus).isOK();
    }

}
