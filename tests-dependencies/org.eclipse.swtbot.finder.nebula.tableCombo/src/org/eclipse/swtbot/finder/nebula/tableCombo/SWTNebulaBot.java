/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Aurelien Pupier <aurelien.pupier@bonitasoft.com> - initial API and implementation
 */
package org.eclipse.swtbot.finder.nebula.tableCombo;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withId;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withLabel;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;

import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.hamcrest.Matcher;

/**
 * @author Aurelien Pupier
 */
public class SWTNebulaBot extends SWTWorkbenchBot {

    public SWTBotTableCombo tableCombo() {
        return tableCombo(0);
    }

    public SWTBotTableCombo tableCombo(int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    public SWTBotTableCombo tableCombo(String mnemonicText) {
        return tableCombo(mnemonicText, 0);
    }

    public SWTBotTableCombo tableCombo(String mnemonicText, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), withMnemonic(mnemonicText));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    public SWTBotTableCombo tableComboInGroup(String inGroup) {
        return tableComboInGroup(inGroup, 0);
    }

    public SWTBotTableCombo tableComboInGroup(String inGroup, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), inGroup(inGroup));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    public SWTBotTableCombo tableComboWithId(String value) {
        return tableComboWithId(value, 0);
    }

    public SWTBotTableCombo tableComboWithId(String value, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), withId(value));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    /**
     * @param key the key set on the widget.
     * @param value the value for the key.
     * @return a {@link SWTBotTableCombo} with the specified <code>key/value</code>.
     */
    public SWTBotTableCombo tableComboWithId(String key, String value) {
        return tableComboWithId(key, value, 0);
    }

    /**
     * @param key the key set on the widget.
     * @param value the value for the key.
     * @param index the index of the widget.
     * @return a {@link SWTBotTableCombo} with the specified <code>key/value</code>.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SWTBotTableCombo tableComboWithId(String key, String value, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), withId(key, value));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    /**
     * @param label the label on the widget.
     * @return a {@link SWTBotTableCombo} with the specified <code>label</code>.
     */
    public SWTBotTableCombo tableComboWithLabel(String label) {
        return tableComboWithLabel(label, 0);
    }

    /**
     * @param label the label on the widget.
     * @param index the index of the widget.
     * @return a {@link SWTBotTableCombo} with the specified <code>label</code>.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SWTBotTableCombo tableComboWithLabel(String label, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), withLabel(label));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

    /**
     * @param label the label on the widget.
     * @param inGroup the inGroup on the widget.
     * @return a {@link SWTBotTableCombo} with the specified <code>label</code> with the specified <code>inGroup</code>.
     */
    public SWTBotTableCombo tableComboWithLabelInGroup(String label, String inGroup) {
        return tableComboWithLabelInGroup(label, inGroup, 0);
    }

    /**
     * @param label the label on the widget.
     * @param inGroup the inGroup on the widget.
     * @param index the index of the widget.
     * @return a {@link SWTBotTableCombo} with the specified <code>label</code> with the specified <code>inGroup</code>.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SWTBotTableCombo tableComboWithLabelInGroup(String label, String inGroup, int index) {
        Matcher matcher = allOf(widgetOfType(TableCombo.class), withLabel(label), inGroup(inGroup));
        return new SWTBotTableCombo((TableCombo) widget(matcher, index), matcher);
    }

}
