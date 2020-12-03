/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ImportBdmContentValidator implements IValidator<String> {

    private BusinessObjectModelRepositoryStore<?> store;

    public ImportBdmContentValidator(BusinessObjectModelRepositoryStore<?> store) {
        this.store = store;
    }

    @Override
    public IStatus validate(String value) {
        if (value != null) {
            File file = new File(value);
            try (ZipFile zipFile = new ZipFile(file)) {
                if (!Objects.equals(zipFile.entries().nextElement().getName(), BusinessObjectModelFileStore.BOM_FILENAME)) {
                    return ValidationStatus.error(String.format(Messages.bdmZipInvalid, file.getName()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                return checkBDMVersion(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ValidationStatus.ok();
    }
    
    private IStatus checkBDMVersion(File value) throws FileNotFoundException, IOException {
        try(InputStream is = new FileInputStream(value)){
            return store.validate(BusinessObjectModelFileStore. ZIP_FILENAME, is);
        }
    }

}
