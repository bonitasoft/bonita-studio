/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.ui.databinding;

import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.UpdateSetStrategy;
import org.eclipse.core.databinding.UpdateValueStrategy;

public class UpdateStrategyFactory {

    private UpdateStrategyFactory() {

    }

    public static UpdateValueStrategyFactory updateValueStrategy() {
        return new UpdateValueStrategyFactory(UpdateValueStrategy.POLICY_UPDATE);
    }

    public static UpdateSetStrategyFactory updateSetStrategy() {
        return new UpdateSetStrategyFactory(UpdateSetStrategy.POLICY_UPDATE);
    }

    public static UpdateValueStrategyFactory neverUpdateValueStrategy() {
        return new UpdateValueStrategyFactory(UpdateValueStrategy.POLICY_NEVER);
    }

    public static UpdateValueStrategyFactory onRequestUpdateValueStrategy() {
        return new UpdateValueStrategyFactory(UpdateValueStrategy.POLICY_ON_REQUEST);
    }

    public static UpdateValueStrategyFactory convertUpdateValueStrategy() {
        return new UpdateValueStrategyFactory(UpdateValueStrategy.POLICY_CONVERT);
    }

    public static UpdateListStrategyFactory updateListStrategyFactory() {
        return new UpdateListStrategyFactory(UpdateListStrategy.POLICY_UPDATE);
    }

    public static UpdateListStrategyFactory neverUpdateListStrategyFactory() {
        return new UpdateListStrategyFactory(UpdateListStrategy.POLICY_NEVER);
    }
}
