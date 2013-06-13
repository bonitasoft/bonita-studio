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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 *Utility object to copy an InputStream
 */
public class CopyInputStream{

    private final InputStream _is;
    private final ByteArrayOutputStream _copy = new ByteArrayOutputStream();


    public CopyInputStream(InputStream is){
        _is = is;
        try{
            copy();
        }catch(IOException ex){
            BonitaStudioLog.error(ex);
        }
    }

    private int copy() throws IOException {
        int read = 0;
        int chunk = 0;
        byte[] data = new byte[256];

        while(-1 != (chunk = _is.read(data))){
            read += data.length;
            _copy.write(data, 0, chunk);
        }
        return read;
    }

    public InputStream getCopy() {
        return new ByteArrayInputStream(_copy.toByteArray());
    }
    
    public void close(){
    	if(_is != null){
    		try {
				_is.close();
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
    	}
    }
}