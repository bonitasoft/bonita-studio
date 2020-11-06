/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil extends SimpleFileVisitor<Path> implements java.lang.AutoCloseable {

    public static final int BUFFER_SIZE = 4096;
    private Path source;
    private FileOutputStream fos;
    private ZipOutputStream zos;

    public static void zip(Path source, Path target) throws IOException {
        try (ZipUtil zippingVisitor = new ZipUtil(source, target)) {
            Files.walkFileTree(source, zippingVisitor);
        }
    }

    public ZipUtil(Path source, Path target) throws FileNotFoundException {
        this.source = source;
        fos = new FileOutputStream(target.toFile());
        zos = new ZipOutputStream(fos);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toFile().exists()) {
            throw new IOException("File " + file.toString() + " not found.");
        }
        Path zipEntryPath = source.relativize(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        try (FileInputStream fis = new FileInputStream(file.toFile())) {
            zos.putNextEntry(new ZipEntry(normalizePath(zipEntryPath)));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
        return CONTINUE;
    }

    /**
     * Ensure that the zip entry separator is '/', which is not the case by default on windows ('\\'),
     * So the client doesn't have to manage this.
     */
    public static String normalizePath(Path path) {
        return path.toString().replaceAll("\\\\", "/");
    }

    @Override
    public void close() throws IOException {
        zos.close();
        fos.close();
    }

    public static Path unzip(File file) throws IOException {
        Path targetDir = Files.createTempDirectory(file.getName());
        try (FileInputStream fis = new FileInputStream(file);
                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                int count;
                byte[] data = new byte[BUFFER_SIZE];
                File target = targetDir.toFile().toPath().resolve(entry.getName()).toFile();
                if (!target.exists()) {
                    target.getParentFile().mkdirs();
                    if (!target.createNewFile()) {
                        throw new IOException("Failed to create file " + target.getAbsolutePath());
                    }
                }
                FileOutputStream fos = new FileOutputStream(target);
                try (BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE);) {
                    while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                }
            }
        }
        return targetDir;
    }

}
