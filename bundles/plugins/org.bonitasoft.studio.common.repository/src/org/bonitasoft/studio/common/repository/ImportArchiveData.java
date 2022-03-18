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

    public String getName() {
        return entry.getName();
    }
}
