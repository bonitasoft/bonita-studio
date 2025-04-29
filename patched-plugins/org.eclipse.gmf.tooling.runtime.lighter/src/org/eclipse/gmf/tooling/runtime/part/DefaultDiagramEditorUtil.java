/**
 * Copyright (c) 2007, 2010, 2013 Borland Software Corporation and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 *    Svyatoslav Kovalsky (Montages) - #410477 "same-generated" code extracted to GMFT-runtime 
 */
package org.eclipse.gmf.tooling.runtime.part;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @since 3.1
 */
public class DefaultDiagramEditorUtil {

	public static String getUniqueFileName(IPath containerFullPath, String fileName, String extension) {
		return getUniqueFileName(containerFullPath, fileName, extension, EXISTS_IN_WORKSPACE);
	}

	public static String getUniqueFileName(IPath containerFullPath, String fileName, String extension, FileExistenceCheck check) {
		if (containerFullPath == null) {
			containerFullPath = new Path(""); //$NON-NLS-1$
		}
		if (fileName == null || fileName.trim().length() == 0) {
			fileName = "default"; //$NON-NLS-1$
		}
		IPath filePath = containerFullPath.append(fileName);
		if (extension != null && !extension.equals(filePath.getFileExtension())) {
			filePath = filePath.addFileExtension(extension);
		}
		extension = filePath.getFileExtension();
		fileName = filePath.removeFileExtension().lastSegment();
		int i = 1;
		while (check.fileExists(filePath)) {
			i++;
			filePath = containerFullPath.append(fileName + i);
			if (extension != null) {
				filePath = filePath.addFileExtension(extension);
			}
		}
		return filePath.lastSegment();
	}

	public static interface FileExistenceCheck {

		public boolean fileExists(IPath filePath);
	}

	public static final FileExistenceCheck EXISTS_IN_WORKSPACE = new FileExistenceCheck() {

		public boolean fileExists(IPath filePath) {
			return ResourcesPlugin.getWorkspace().getRoot().exists(filePath);
		}
	};

	public static final FileExistenceCheck EXISTS_AS_IO_FILE = new FileExistenceCheck() {

		public boolean fileExists(IPath filePath) {
			return filePath.toFile().exists();
		}
	};
}
