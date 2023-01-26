package org.bonitasoft.studio.importer.bos.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_BONITA_DEPENDENCY_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE;

import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.junit.Test;

public class BonitaStatusCodeAggregatorTest {

    private static final String PROCESS_STATUS_1 = "a process status";
    private static final String PROCESS_STATUS_2 = "an other process status";
    private static final String GROOVY_COMPILER_STATUS = "update groovy compiler dependency pls";
    private static final String GROOVY_ALL_STATUS = "update groovy all dependency pls";
    private static final String BONITA_DEPENDENCY_STATUS = "update bonita dependency pls";

    @Test
    public void should_factorize_redundant_rest_api_statuses() {
        BosArchiveImportStatus initialBosArchiveImportStatus = new BosArchiveImportStatus();

        initialBosArchiveImportStatus.add(ValidationStatus.warning(PROCESS_STATUS_1));
        initialBosArchiveImportStatus.add(ValidationStatus.warning(PROCESS_STATUS_2));

        // one groovy compiler to update status
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE, IStatus.WARNING,
                GROOVY_COMPILER_STATUS));

        // two groovy all to update status
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE, IStatus.WARNING, GROOVY_ALL_STATUS));
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE, IStatus.WARNING, GROOVY_ALL_STATUS));

        // three bonita dependency status
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_BONITA_DEPENDENCY_STATUS_CODE, IStatus.INFO,
                BONITA_DEPENDENCY_STATUS));
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_BONITA_DEPENDENCY_STATUS_CODE, IStatus.INFO,
                BONITA_DEPENDENCY_STATUS));
        initialBosArchiveImportStatus.add(createStatusWithCode(
                REST_API_BONITA_DEPENDENCY_STATUS_CODE, IStatus.INFO,
                BONITA_DEPENDENCY_STATUS));

        assertThat(initialBosArchiveImportStatus.getChildren()).hasSize(8);

        BosArchiveImportStatus factorizedBosArchiveImportStatus = new BonitaStatusCodeAggregator()
                .aggregateStatus(initialBosArchiveImportStatus);

        String expectedGroovyAllMessage = String.format("%s (%s %s)", GROOVY_ALL_STATUS, 2, Messages.occurrences);
        String expectedBonitaDependencyMessage = String.format("%s (%s %s)", BONITA_DEPENDENCY_STATUS, 3,
                Messages.occurrences);
        assertThat(factorizedBosArchiveImportStatus.getChildren()).hasSize(5);
        assertThat(factorizedBosArchiveImportStatus.getChildren())
                .extracting(IStatus::getMessage)
                .containsExactlyInAnyOrder(
                        PROCESS_STATUS_1,
                        PROCESS_STATUS_2,
                        GROOVY_COMPILER_STATUS,
                        expectedGroovyAllMessage,
                        expectedBonitaDependencyMessage);
    }

    private IStatus createStatusWithCode(int code, int severity, String message) {
        return new Status(severity, "0", code, message, null);
    }

}
