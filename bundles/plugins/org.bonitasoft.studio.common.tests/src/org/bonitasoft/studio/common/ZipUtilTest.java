package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class ZipUtilTest {

    @Test
    public void should_normalize_path() throws Exception {
        Path windowsPath = Paths.get("a\\windows\\path");
        String[] segmentsFromWindowsPath = ZipUtil.normalizePath(windowsPath).split("/");
        assertThat(segmentsFromWindowsPath).hasSize(3);

        Path unixPath = Paths.get("a/unix/path");
        String[] segmentsFromUnixPath = ZipUtil.normalizePath(unixPath).split("/");
        assertThat(segmentsFromUnixPath).hasSize(3);
    }

}
