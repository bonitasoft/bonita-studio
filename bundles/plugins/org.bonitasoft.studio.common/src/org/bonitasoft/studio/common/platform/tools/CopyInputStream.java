/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.platform.tools;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 *Utility object to copy an InputStream
 */
public class CopyInputStream implements AutoCloseable{

    private final InputStream _is;
	private Path file;


    public CopyInputStream(InputStream is){
        _is = is;
        OutputStream _copy = null;
        try {
            file = Files.createTempFile("copy","");
			_copy = Files.newOutputStream(file);
		} catch (final IOException e) {
			BonitaStudioLog.error(e);
		}
        try{
            copy(_copy);
        }catch(final IOException ex){
            BonitaStudioLog.error(ex);
        } finally {
            if (_copy != null) {
                try {
                    _copy.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    private int copy(OutputStream outputStream) throws IOException {
        int read = 0;
        int chunk = 0;
        final byte[] buffer = new byte[256];
        while (-1 != (chunk = _is.read(buffer))) {
            outputStream.write(buffer, 0, chunk);
        	read += chunk;
        }
        return read;
    }

    public InputStream getCopy() {
        try {
			return Files.newInputStream(file);
		} catch (final IOException e) {
			BonitaStudioLog.error(e);
		}
		return _is;
    }
    
    public void close(){
    	if(_is != null){
    		try {
				_is.close();
			} catch (final IOException e) {
				BonitaStudioLog.error(e);
			}
    	}
    	if(file != null && file.toFile().exists()){
    		try {
                Files.delete(file);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
    	}
    }

    public File getFile() {
        return file.toFile();
    }
}