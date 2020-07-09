/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.pattern;

import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class GroovyExpressionPartitioner extends FastPartitioner {

    private static final String GROOVY_START_TAG = "${";
    private static final String GROOVY_END_TAG = "}";

    public GroovyExpressionPartitioner() {
        super(createDefaultScanner(), new String[] { PatternExpressionViewer.GROOVY_EXPRESSION_CONTENT_TYPE });
    }

    private static IPartitionTokenScanner createDefaultScanner() {
        final IToken string = new Token(PatternExpressionViewer.GROOVY_EXPRESSION_CONTENT_TYPE);
        final RuleBasedPartitionScanner scanner = new RuleBasedPartitionScanner();
        scanner.setPredicateRules(new IPredicateRule[] {
                new MultiLineRule(GROOVY_START_TAG, GROOVY_END_TAG, string)
        });
        return scanner;
    }

}
