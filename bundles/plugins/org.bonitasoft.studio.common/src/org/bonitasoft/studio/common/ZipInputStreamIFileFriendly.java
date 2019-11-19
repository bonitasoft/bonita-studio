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
package org.bonitasoft.studio.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IFile;

/**
 * @author Mickael Istria
 * A {@link ZipInputStream} that ignores the close to be used to
 * initialize {@link IFile}s.
 * /!\ DON'T FORGET TO CALL {@link IFileFriendlyZipInputStream}{@link #forceClose()} on it /!\
 */
public class ZipInputStreamIFileFriendly extends ZipInputStream {
	
	public ZipInputStreamIFileFriendly(InputStream in) {
		super(in);
	}

	@Override
	public void close() {
		// Skipped because of this very annoying IFile.create
		// which will try to close the stream for use
	}

	public void forceClose() throws IOException {
		super.close();
	}
	
}
