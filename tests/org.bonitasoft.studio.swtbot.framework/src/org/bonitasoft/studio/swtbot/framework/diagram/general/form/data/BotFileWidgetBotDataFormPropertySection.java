/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.diagram.general.form.data;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author aurelie
 *
 */
public class BotFileWidgetBotDataFormPropertySection extends BotDataFormPropertySection {

    /**
     * @param bot
     */
    public BotFileWidgetBotDataFormPropertySection(final SWTGefBot bot) {
        super(bot);

    }

    public boolean isDocumentPickerEnabled() {
        return bot.radio(Messages.both).isEnabled();
    }

    public void checkDownloadOnly() {
        bot.checkBox(Messages.downloadOnly).select();;
    }

    public void uncheckDownloadOnly() {
        bot.checkBox(Messages.downloadOnly).deselect();
    }

    public void selectDocumentPickerBoth() {
        bot.radio(Messages.both).click();
    }

    public boolean isDocumentPickerBothSelected() {
        return bot.radio(Messages.both).isSelected();
    }

    public void selectDocumentPickerURL() {
        bot.radio(Messages.urlRadio).click();
    }

    public boolean isDocumentPickerUrlSelected() {
        return bot.radio(Messages.urlRadio).isSelected();
    }

    public void selectDocumentPickerBrowse() {
        bot.radio(Messages.BrowseRadio).click();
    }

    public boolean isDocumentPickerBrowseSelected() {
        return bot.radio(Messages.BrowseRadio).isSelected();
    }

    public void checkResource() {
        bot.radio(Messages.useResource).click();
    }

    public void checkDocument() {
        bot.radio(Messages.useDocument).click();
    }


    public boolean isUrlRadioIsEnabled() {
        return bot.radio(Messages.urlRadio).isEnabled();
    }

}
