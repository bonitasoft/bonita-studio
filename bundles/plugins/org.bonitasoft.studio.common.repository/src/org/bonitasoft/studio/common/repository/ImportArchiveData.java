package org.bonitasoft.studio.common.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ImportArchiveData {

    private final ZipEntry entry;
    private final boolean shouldOverwrite;
    private final ZipFile archive;

    public ImportArchiveData(ZipFile archive, ZipEntry entry, boolean shouldOverwrite) {
        this.entry = entry;
        this.shouldOverwrite = shouldOverwrite;
        this.archive = archive;
    }

    public ZipEntry getEntry() {
        return entry;
    }

    public boolean shouldOverwrite() {
        return shouldOverwrite;
    }

    public InputStream getInputStream() throws IOException {
        return archive.getInputStream(entry);
    }
    
    public String getProjectRelativePath() {
        var segments = entry.getName().split("/");
        if(segments.length > 2 && segments[1].equals("app")) {
            return entry.getName().split("/", 4)[3];
        }
        return entry.getName().split("/", 3)[2];
    }

    public String getName() {
        var segments = entry.getName().split("/");
        return segments[segments.length -1];
    }
}
