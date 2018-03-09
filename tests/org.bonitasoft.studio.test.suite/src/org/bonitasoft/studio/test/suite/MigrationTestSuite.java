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
package org.bonitasoft.studio.test.suite;

import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.importer.TestBarImporterInput;
import org.bonitasoft.studio.tests.importer.TestSimpleMigrationUseCase;
import org.bonitasoft.studio.tests.importer.attachmentDataImport.AttachmentDataImportTest;
import org.bonitasoft.studio.tests.importer.connector.TestConnectorMigrationUseCase;
import org.bonitasoft.studio.tests.importer.connector.TestCustomConnectorMigrationUseCase;
import org.bonitasoft.studio.tests.importer.examples.Test59ExampleImport;
import org.bonitasoft.studio.tests.importer.messagesImport.CorrelationMigrationTest;
import org.bonitasoft.studio.tests.migration.EdaptHistoryIT;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        EdaptHistoryIT.class,
        TestBarImporterInput.class,
        TestSimpleMigrationUseCase.class,
        TestConnectorMigrationUseCase.class,
        TestCustomConnectorMigrationUseCase.class,
        Test59ExampleImport.class,
        AttachmentDataImportTest.class,
        CorrelationMigrationTest.class,
        CloseAllEditors.class
})
public class MigrationTestSuite {

}
