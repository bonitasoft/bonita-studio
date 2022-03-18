/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Super class of bot used classes.
 *
 * @author Joachim Segala
 */
public class BotBase implements SWTBotConstants {

    protected final SWTGefBot bot;
    private final Keyboard keyboard;

    public BotBase(final SWTGefBot bot) {
        this.bot = bot;
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        keyboard = KeyboardFactory.getSWTKeyboard();
    }

    public void selectText() {
        List<KeyStroke> keystrokes = new ArrayList<>();
        keystrokes.add(Keystrokes.CTRL);
        keystrokes.addAll(Arrays.asList(Keystrokes.create('a')));
        keyboard.pressShortcut(keystrokes.toArray(new KeyStroke[0]));
    }

}
