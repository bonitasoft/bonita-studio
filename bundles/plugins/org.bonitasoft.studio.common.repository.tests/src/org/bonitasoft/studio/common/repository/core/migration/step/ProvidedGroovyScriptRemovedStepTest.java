package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.Test;

class ProvidedGroovyScriptRemovedStepTest {
	
	@Test
	void haveMigrationReport() throws Exception {
		var step = new ProvidedGroovyScriptRemovedStep();
		
		var report = step.run(null, new NullProgressMonitor());
		
		assertThat(report.removals()).contains("Deprecated provided groovy classes `BonitaUsers`, `BonitaSql`, `BonitaXML` and `BonitaTypes` have been removed.");
	}
	
	@Test
	void appliesToBelow9Version() throws Exception {
		var step = new ProvidedGroovyScriptRemovedStep();
		
		assertThat(step.appliesToVersion("8.0.0")).isTrue();
		assertThat(step.appliesToVersion("9.0.0")).isFalse();
	}
}
