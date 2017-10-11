/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.dialog;

import java.util.function.Function;

import org.bonitasoft.studio.ui.UIPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class EngineStatusMapper implements Function<org.bonitasoft.engine.api.result.Status, Status> {

    private Function<org.bonitasoft.engine.api.result.Status, String> l10nFunction;

    private EngineStatusMapper(Function<org.bonitasoft.engine.api.result.Status, String> l10nFunction) {
        this.l10nFunction = l10nFunction;
    }

    /*
     * (non-Javadoc)
     * @see java.util.function.Function#apply(java.lang.Object)
     */
    @Override
    public Status apply(org.bonitasoft.engine.api.result.Status engineStatus) {
        return new Status(levelToSeverity(engineStatus), UIPlugin.PLUGIN_ID, l10n(engineStatus));
    }

    private String l10n(org.bonitasoft.engine.api.result.Status engineStatus) {
        return l10nFunction.apply(engineStatus);
    }

    private int levelToSeverity(org.bonitasoft.engine.api.result.Status engineStatus) {
        switch (engineStatus.getLevel()) {
            case ERROR:
                return IStatus.ERROR;
            case WARNING:
                return IStatus.WARNING;
            case INFO:
                return IStatus.INFO;
            default:
                return IStatus.OK;
        }
    }

    public static Function<org.bonitasoft.engine.api.result.Status, Status> instance(
            Function<org.bonitasoft.engine.api.result.Status, String> l10nFunction) {
        return new EngineStatusMapper(l10nFunction);
    }

}
