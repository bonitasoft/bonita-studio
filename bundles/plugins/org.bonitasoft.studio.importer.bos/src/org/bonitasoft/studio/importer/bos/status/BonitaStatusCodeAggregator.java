/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class BonitaStatusCodeAggregator {

    /**
     * A status code is considered as a Bonita status code if it is greater or equals than 1000.
     */
    public static boolean isBonitaStatusCode(int code) {
        return code >= 1000;
    }

    public static final int REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE = 1000;
    public static final int REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE = 1001;
    public static final int REST_API_JAVA_11_GROOVY_BATCH_STATUS_CODE = 1002;
    public static final int REST_API_BONITA_DEPENDENCY_STATUS_CODE = 1003;

    public BosArchiveImportStatus aggregateStatus(BosArchiveImportStatus bosArchiveImportStatus) {
        BosArchiveImportStatus bosArchiveStatusAggregated = new BosArchiveImportStatus();
        List<RedundantStatus> statusToAggregate = new ArrayList<>();

        for (IStatus status : bosArchiveImportStatus.getChildren()) {
            if (isBonitaStatusCode(status.getCode())) {
                statusToAggregate.stream()
                        .filter(redundantStatus -> redundantStatus.match(status))
                        .findFirst()
                        .orElseGet(() -> createRedundantStatus(status, statusToAggregate))
                        .incrementOccurence();
            } else {
                bosArchiveStatusAggregated.add(status);
            }
        }

        statusToAggregate.stream()
                .map(RedundantStatus::createAggregatedStatus)
                .forEach(bosArchiveStatusAggregated::add);

        return bosArchiveStatusAggregated;
    }

    private RedundantStatus createRedundantStatus(IStatus status, List<RedundantStatus> statusToAggregate) {
        RedundantStatus redundantStatus = new RedundantStatus(status);
        statusToAggregate.add(redundantStatus);
        return redundantStatus;
    }

}

class RedundantStatus {

    private IStatus status;
    private int occurrence = 0;

    public RedundantStatus(IStatus status) {
        this.status = status;
    }

    public boolean match(IStatus aStatus) {
        return Objects.equals(status.getCode(), aStatus.getCode());
    }

    public void incrementOccurence() {
        occurrence += 1;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public IStatus createAggregatedStatus() {
        String message = occurrence > 1
                ? String.format("%s (%s %s)", status.getMessage(), occurrence, Messages.occurrences)
                : status.getMessage();
        switch (status.getSeverity()) {
            case IStatus.OK:
            case IStatus.INFO:
                return ValidationStatus.info(message);
            case IStatus.WARNING:
                return ValidationStatus.warning(message);
            default:
                return ValidationStatus.error(message);
        }
    }
}
