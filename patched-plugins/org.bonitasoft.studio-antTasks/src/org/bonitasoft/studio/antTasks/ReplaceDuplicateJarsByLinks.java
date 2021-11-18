/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.antTasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Mickael Istria
 *
 */
public class ReplaceDuplicateJarsByLinks extends Task {
	
	private File folder;
	private HashMap<FileKey, String> knownFiles = new HashMap<FileKey, String>();
	
	private static class FileKey {
		
		private String fileName;
		private String fileDigest;
 				
		private FileKey() {}
		
		public static FileKey createInstance(File file) throws Exception {
			FileKey res = new FileKey();
			res.fileName = file.getName();
			MessageDigest digester = MessageDigest.getInstance("SHA-1");
			byte[] bytes = new byte[(int)file.length()];
			InputStream stream = new FileInputStream(file);
			stream.read(bytes);
			stream.close();
			digester.update(bytes);
			res.fileDigest = new String(digester.digest());
			return res;
		}

		public boolean equals(Object o) {
			if (o instanceof FileKey) {
				FileKey other = (FileKey)o;
				return other.fileName.equals(this.fileName) && other.fileDigest.equals(this.fileDigest);
			} else {
				return false;
			}
		}
		
		public int hashCode() {
			return (fileName + fileDigest).hashCode();
		}
	}
	
	@Override
	public void execute() throws BuildException {
		try {
			processFile(folder);
		} catch (Exception ex) {
			throw new BuildException(ex);
		}
    }
	
	private void processFile(File file) throws Exception {
		if (!file.exists() || file.getName().equals(".svn")) {
			return;
		}
		
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				processFile(child);
			}
		} else if (file.length() > 50) { // Avoid complexity for small files
			FileKey key = FileKey.createInstance(file);
			if (knownFiles.containsKey(key)) {
				replaceFileByLinkTo(file, knownFiles.get(key));
			} else {
				knownFiles.put(key, file.getAbsolutePath());
			}
		}
		
	}

	/**
	 * @param file
	 * @param string
	 */
	private void replaceFileByLinkTo(File file, String linkedFilePath) throws Exception {
		File linkFile = new File(file.getAbsolutePath() + ".lnk");
		if (linkFile.exists()) {
			throw new Exception("File [" + linkFile + "] already exists");
		}
		linkFile.createNewFile();
		String linkedFileRelativePath = linkedFilePath.substring(folder.getAbsolutePath().length(), linkedFilePath.length());
		FileWriter out = new FileWriter(linkFile);
		out.append(linkedFileRelativePath);
		out.flush();
		out.close();
		file.getAbsoluteFile().delete();
	}

	public void setFolder(String folder) {
		this.folder = new File(folder);
	}
}
