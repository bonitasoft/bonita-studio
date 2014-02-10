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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 *Utility object to copy an InputStream
 */
public class CopyInputStream{

    private final InputStream _is;
    private FileOutputStream _copy ;
	private File file;


    public CopyInputStream(InputStream is){
        _is = is;
        try {
			file = new File(ProjectUtil.getBonitaStudioWorkFolder(),"importTmp"+System.currentTimeMillis());
			file.delete();
			file.createNewFile();
			_copy = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			BonitaStudioLog.error(e);
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		}
        try{
            copy();
        }catch(IOException ex){
            BonitaStudioLog.error(ex);
        }
    }

    private int copy() throws IOException {
        int read = 0;
        int chunk = 0;
        byte[] buffer = new byte[256];
        while (-1 != (chunk = _is.read(buffer))) {
        	_copy.write(buffer, 0, chunk);
        	read += chunk;
        }
        return read;
    }

    public InputStream getCopy() {
        try {
			return new FileInputStream(file);
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		}
		return _is;
    }
    
    public void close(){
    	if(_is != null){
    		try {
				_is.close();
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
    	}
    	if(file != null && file.exists()){
    		file.delete();
    	}
    }
}