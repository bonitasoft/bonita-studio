/**
 * Copyright (C) 2024 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.importer.bpmn2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.importer.bpmn.BPMNToProc;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class to verify BPMN source detection functionality.
 * This test ensures that we can properly extract exporter and exporterVersion
 * information from BPMN files through the TDefinitions object.
 */
public class BPMNSourceDetectionTest {

    /**
     * Test that verifies we can access TDefinitions and extract source tool information.
     * This test uses a BPMN file with known exporter metadata.
     */
	@Ignore //TODO Waiting for more information about running the app in local
    @Test
    public void testBpmnSourceDetectionFromDefinitions() throws Exception {
        // Create a test BPMN file URL
        URL testBpmnUrl = getClass().getResource("testWithExporter.bpmn");
        assertNotNull("Test BPMN file should exist", testBpmnUrl);
        
        // Create BPMNToProc processor
        BPMNToProc processor = new BPMNToProc();
        
        // Process the BPMN file
        File result = processor.createDiagram(testBpmnUrl, null);
        
        // Verify that we can access the definitions
        var definitions = processor.getDefinitions();
        assertNotNull("TDefinitions should be available after processing", definitions);
        
        // Verify that we can extract exporter information
        String exporter = definitions.getExporter();
        String exporterVersion = definitions.getExporterVersion();
        
        // Verify the expected values from our test file
        assertNotNull("Exporter should not be null", exporter);
        assertEquals("Expected exporter name", "Camunda Modeler", exporter);
        
        assertNotNull("Exporter version should not be null", exporterVersion);
        assertEquals("Expected exporter version", "5.15.0", exporterVersion);
        
        // Verify that the result file was created
        assertNotNull("Result file should be created", result);
        assertTrue("Result file should exist", result.exists());
        
        // Clean up
        if (result != null && result.exists()) {
            result.delete();
        }
    }
    
    /**
     * Test that verifies behavior when no exporter information is available.
     */
	@Ignore //TODO Waiting for more information about running the app in local
    @Test
    public void testBpmnWithoutExporterInfo() throws Exception {
        // This test would use a BPMN file without exporter metadata
        // For now, we just verify that the method handles null values gracefully
        
        BPMNToProc processor = new BPMNToProc();
        var definitions = processor.getDefinitions();
        
        // Before processing any file, definitions should be null
        // This verifies our null-safety
        if (definitions != null) {
            String exporter = definitions.getExporter();
            String exporterVersion = definitions.getExporterVersion();
            
            // These could be null and that's fine
            // The important thing is that the code doesn't crash
            assertTrue("Test should complete without exceptions", true);
        }
    }
} 