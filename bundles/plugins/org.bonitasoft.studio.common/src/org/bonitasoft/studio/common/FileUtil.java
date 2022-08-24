/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

import com.thebuzzmedia.imgscalr.Scalr;

/**
 * This class contains a set of util methods to manipulate {@link File}, {@link InputStream} and so on
 * 
 * @author Romain Bioteau (initial implementation)
 * @author Mickael Istria (Refactoring from ProjectUtil to FileUtil)
 */
public class FileUtil {

    public static final int BUFFER_SIZE = 2 * 8192;

    public static void replaceStringInFile(File file, String match, String replacingString) {
        try {
            Files.writeString(file.toPath(),
                    Files.readString(file.toPath(), StandardCharsets.UTF_8).replace(match, replacingString), 
                    StandardCharsets.UTF_8);
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }

    }

    public static File findDirectory(File root, String dirName) {
        File founded = null;
        if (root.getName().equals(dirName)) {
            return root;
        } else if (root.isDirectory()) {
            for (final File f : root.listFiles()) {
                if (founded == null) {
                    founded = findDirectory(f, dirName);
                } else {
                    break;
                }
            }
        }
        return founded;

    }

    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns false.
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            final String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                final boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    public static boolean compareStream(InputStream stream1, InputStream stream2) throws IOException {
        final byte[] bs1 = loadBytes(stream1);
        final byte[] bs2 = loadBytes(stream2);
        final String sum1 = computeDigest(bs1);
        final String sum2 = computeDigest(bs2);
        return sum1.equals(sum2);
    }

    public static byte[] loadBytes(InputStream in) throws IOException {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            buffer.write(ch);
        }
        return buffer.toByteArray();
    }

    public static String computeDigest(byte[] b) {

        try {
            final MessageDigest algo = MessageDigest.getInstance("md5");
            algo.reset();
            algo.update(b);
            final byte[] hash = algo.digest();
            final StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < hash.length; i++) {
                final int v = hash[i] & 0xFF;
                if (v < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toString(v, 16).toUpperCase());
                sb.append(' ');
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            BonitaStudioLog.error(e);
        }
        return "";
    }

    /**
     * Create a structured zip archive recursively.
     * The string must be OS specific String to represent path.
     * 
     * @param dir2zip
     * @param zos
     * @param root
     * @param filenameFilter
     */
    public static void zipDir(String dir2zip, ZipOutputStream zos, String root, FilenameFilter filenameFilter) {
        try {
            //create a new File object based on the directory we
            //have to zip File
            final File zipDir = new File(dir2zip);
            //get a listing of the directory content
            String[] dirList;
            if (filenameFilter != null) {
                dirList = zipDir.list(filenameFilter);
            } else {
                dirList = zipDir.list();
            }
            final byte[] readBuffer = new byte[2156];
            int bytesIn = 0;

            //loop through dirList, and zip the files

            for (int i = 0; i < dirList.length; i++) {
                final File f = new File(zipDir, dirList[i]);
                final String path = f.getPath();
                if (f.isDirectory()) {
                    //if the File object is a directory, call this
                    //function again to add its content recursively
                    final String filePath = path;
                    zipDir(filePath, zos, root, filenameFilter);
                    //loop again
                    continue;
                }
                //if we reached here, the File object f was not
                //a directory
                //create a FileInputStream on top of f
                try (final FileInputStream fis = new FileInputStream(f);) {
                    //create a new zip entry
                    final ZipEntry anEntry = new ZipEntry(
                            path.substring(path.indexOf(root) + root.length() + 1, path.length())
                                    .replace(String.valueOf(File.separatorChar), "/")); //$NON-NLS-1$
                    //place the zip entry in the ZipOutputStream object
                    zos.putNextEntry(anEntry);
                    //now write the content of the file to the ZipOutputStream
                    while ((bytesIn = fis.read(readBuffer)) != -1) {
                        zos.write(readBuffer, 0, bytesIn);
                    }
                    //close the Stream
                }
                zos.flush();
                zos.closeEntry();
            }
            zos.close();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Create a structured zip archive recursively.
     * The string must be OS specific String to represent path.
     * 
     * @param dir2zip
     * @param zos
     * @param root
     * @param filenameFilter
     */
    public static void zipDir(String dir2zip, ZipOutputStream zos, String root) {
        zipDir(dir2zip, zos, root, null);
    }

    /**
     * get files and files in sub-directories form the zip file and put it in
     * the output folder
     * 
     * @param zipFile
     *        the source zip
     * @param entry
     *        get files from this entry
     * @param outFolder
     *        where to put files
     */
    public static File getFilesFromZip(File zipFile, String entry, File outFolder) {
        try (final FileInputStream fis = new FileInputStream(zipFile);
                final ZipInputStream zin = new ZipInputStream(fis);) {

            ZipEntry zipEntry = zin.getNextEntry();
            File tempFile;
            while (zipEntry != null) {
                // the entry is in the list
                if (zipEntry.getName().startsWith(entry)) {
                    if (!zipEntry.isDirectory()) {
                        tempFile = new File(outFolder.getAbsolutePath() + File.separatorChar
                                + zipEntry.getName().substring(entry.length()));

                        tempFile.getParentFile().mkdirs();
                        tempFile.delete();
                        tempFile.createNewFile();
                        final byte[] buf = new byte[1024];
                        int len;
                        try (final FileOutputStream out = new FileOutputStream(tempFile)) {
                            while ((len = zin.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                        }
                    }
                }
                zipEntry = zin.getNextEntry();
            }
            return outFolder;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /**
     * get a file from a zip file and put it in a specified one
     * 
     * @param zipFile
     *        where to get the file
     * @param entry
     *        the location of the file in the zipFile
     * @param outputFile
     *        where to put the file
     * @return a file in temp directory
     */
    public static File getFileFromZip(File zipFile, String entry, File outputFile) {
        try (final FileInputStream fis = new FileInputStream(zipFile);
                final ZipInputStream zin = new ZipInputStream(fis);) {
            ZipEntry zipEntry = zin.getNextEntry();
            while (zipEntry != null && !entry.equals(zipEntry.getName())) {
                zipEntry = zin.getNextEntry();
            }
            if (zipEntry == null) {
                zin.close();
                throw new FileNotFoundException("can't find entry " + entry + " in " + zipFile.getName()); //$NON-NLS-1$ //$NON-NLS-2$
            }

            final byte[] buf = new byte[1024];
            outputFile.delete();
            outputFile.createNewFile();
            int len;
            try (final FileOutputStream out = new FileOutputStream(outputFile);) {
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            return outputFile;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /**
     * get a file from a zip file
     * 
     * @param zipFile
     *        where to get the file
     * @param entry
     *        the location of the file in the zipFile
     * @return a file in temp directory
     */
    public static File getFileFromZip(File zipFile, String entry) {
        try (final ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));) {
            ZipEntry zipEntry = zin.getNextEntry();
            while (zipEntry != null && !entry.equals(zipEntry.getName())) {
                zipEntry = zin.getNextEntry();
            }
            if (zipEntry == null) {
                throw new FileNotFoundException("can't find entry " + entry + " in " + zipFile.getName()); //$NON-NLS-1$ //$NON-NLS-2$
            }
            final File tempFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), entry.substring(entry.lastIndexOf("/"))); //.createTempFile(entry.substring(entry.lastIndexOf("/")), ".tmp"); //$NON-NLS-1$
            final byte[] buf = new byte[1024];
            tempFile.delete();
            int len;
            try (final FileOutputStream out = new FileOutputStream(tempFile);) {
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            return tempFile;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static InputStream replaceStringInFile(InputStream is, String match, String replacement) {
        try {
            final Writer writer = new StringWriter();
            final char[] buffer = new char[1024];
            try {
                Reader reader;
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                reader.close();
            } finally {
                is.close();
            }
            String content = writer.toString();
            writer.close();
            content = content.replaceAll(match, replacement);
            return new ByteArrayInputStream(content.getBytes());
        } catch (final IOException ioe) {
            BonitaStudioLog.error(ioe);
        }
        return null;
    }

    public static void removeLineInFile(File file, String toRemove) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file));
                final FileWriter writer = new FileWriter(file.getAbsolutePath());) {
            final StringBuilder sb = new StringBuilder();
            String line = ""; //$NON-NLS-1$
            while ((line = reader.readLine()) != null) {
                if (!line.contains(toRemove)) {
                    sb.append(line);
                    sb.append("\r\n");//$NON-NLS-1$
                }
            }
            writer.write(sb.toString());
        } catch (final IOException ioe) {
            BonitaStudioLog.error(ioe);
        }
    }

    public static BufferedImage resizeImage(BufferedImage image, int maxSize) {
        return Scalr.resize(image, maxSize);
    }

    public static BufferedImage convertToAWT(ImageData data) {
        ColorModel colorModel = null;
        final PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel = new DirectColorModel(data.depth, palette.redMask,
                    palette.greenMask, palette.blueMask);
            final BufferedImage bufferedImage = new BufferedImage(colorModel,
                    colorModel.createCompatibleWritableRaster(data.width,
                            data.height),
                    false, null);
            final WritableRaster raster = bufferedImage.getRaster();
            final int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    final int pixel = data.getPixel(x, y);
                    final RGB rgb = palette.getRGB(pixel);
                    pixelArray[0] = rgb.red;
                    pixelArray[1] = rgb.green;
                    pixelArray[2] = rgb.blue;
                    raster.setPixels(x, y, 1, 1, pixelArray);
                }
            }
            return bufferedImage;
        } else {
            final RGB[] rgbs = palette.getRGBs();
            final byte[] red = new byte[rgbs.length];
            final byte[] green = new byte[rgbs.length];
            final byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                final RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue, data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue);
            }
            final BufferedImage bufferedImage = new BufferedImage(colorModel,
                    colorModel.createCompatibleWritableRaster(data.width,
                            data.height),
                    false, null);
            final WritableRaster raster = bufferedImage.getRaster();
            final int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    final int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            return bufferedImage;
        }
    }

    public static ImageData convertToSWT(BufferedImage bufferedImage) {
        if (bufferedImage.getColorModel() instanceof DirectColorModel) {
            final DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
            final PaletteData palette = new PaletteData(colorModel.getRedMask(),
                    colorModel.getGreenMask(), colorModel.getBlueMask());
            final ImageData data = new ImageData(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), colorModel.getPixelSize(),
                    palette);
            final WritableRaster raster = bufferedImage.getRaster();
            final int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    final int pixel = palette.getPixel(new RGB(pixelArray[0],
                            pixelArray[1], pixelArray[2]));
                    data.setPixel(x, y, pixel);
                }
            }
            return data;
        } else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
            final IndexColorModel colorModel = (IndexColorModel) bufferedImage.getColorModel();
            final int size = colorModel.getMapSize();
            final byte[] reds = new byte[size];
            final byte[] greens = new byte[size];
            final byte[] blues = new byte[size];
            colorModel.getReds(reds);
            colorModel.getGreens(greens);
            colorModel.getBlues(blues);
            final RGB[] rgbs = new RGB[size];
            for (int i = 0; i < rgbs.length; i++) {
                rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF,
                        blues[i] & 0xFF);
            }
            final PaletteData palette = new PaletteData(rgbs);
            final ImageData data = new ImageData(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), colorModel.getPixelSize(),
                    palette);
            data.transparentPixel = colorModel.getTransparentPixel();
            final WritableRaster raster = bufferedImage.getRaster();
            final int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    data.setPixel(x, y, pixelArray[0]);
                }
            }
            return data;
        }
        return null;
    }

    public static boolean isValidName(String text) {
        return new URLEncodableInputValidator("").validate(text).isOK();
    }
}
