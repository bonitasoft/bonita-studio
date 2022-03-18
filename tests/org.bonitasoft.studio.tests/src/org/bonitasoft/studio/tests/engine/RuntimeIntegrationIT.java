package org.bonitasoft.studio.tests.engine;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.junit.Before;
import org.junit.Test;

public class RuntimeIntegrationIT {
    
    @Before
    public void setUp() throws Exception {
       BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_find_runtime_logs() throws Exception {
        File bonitaLogFile = BOSWebServerManager.getInstance().getBonitaLogFile();
  
        assertThat(bonitaLogFile).isNotNull().exists();
    }
    
}
