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
package org.bonitasoft.studio.swtbot.framework;

import java.util.function.Supplier;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

public class ConditionBuilder {

    private Supplier<Boolean> test;
    private Supplier<String> failureMessageSupplier;

    public ConditionBuilder withTest(Supplier<Boolean> test) {
        this.test = test;
        return this;
    }

    public ConditionBuilder withFailureMessage(Supplier<String> failureMessageSupplier) {
        this.failureMessageSupplier = failureMessageSupplier;
        return this;
    }

    public ICondition create() {
        return new ICondition() {

            @Override
            public boolean test() throws Exception {
                return test.get();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return failureMessageSupplier.get();
            }
        };
    }

}
