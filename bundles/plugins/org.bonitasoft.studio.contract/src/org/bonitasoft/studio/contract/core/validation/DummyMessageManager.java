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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core.validation;

import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.IMessagePrefixProvider;


/**
 * @author Romain Bioteau
 *
 */
public class DummyMessageManager implements IMessageManager {

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#addMessage(java.lang.Object, java.lang.String, java.lang.Object, int)
     */
    @Override
    public void addMessage(final Object arg0, final String arg1, final Object arg2, final int arg3) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#addMessage(java.lang.Object, java.lang.String, java.lang.Object, int, org.eclipse.swt.widgets.Control)
     */
    @Override
    public void addMessage(final Object arg0, final String arg1, final Object arg2, final int arg3, final Control arg4) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#createSummary(org.eclipse.ui.forms.IMessage[])
     */
    @Override
    public String createSummary(final IMessage[] arg0) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#getDecorationPosition()
     */
    @Override
    public int getDecorationPosition() {
        return 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#getMessagePrefixProvider()
     */
    @Override
    public IMessagePrefixProvider getMessagePrefixProvider() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#isAutoUpdate()
     */
    @Override
    public boolean isAutoUpdate() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#removeAllMessages()
     */
    @Override
    public void removeAllMessages() {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#removeMessage(java.lang.Object)
     */
    @Override
    public void removeMessage(final Object arg0) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#removeMessage(java.lang.Object, org.eclipse.swt.widgets.Control)
     */
    @Override
    public void removeMessage(final Object arg0, final Control arg1) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#removeMessages()
     */
    @Override
    public void removeMessages() {

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#removeMessages(org.eclipse.swt.widgets.Control)
     */
    @Override
    public void removeMessages(final Control arg0) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#setAutoUpdate(boolean)
     */
    @Override
    public void setAutoUpdate(final boolean arg0) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#setDecorationPosition(int)
     */
    @Override
    public void setDecorationPosition(final int arg0) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#setMessagePrefixProvider(org.eclipse.ui.forms.IMessagePrefixProvider)
     */
    @Override
    public void setMessagePrefixProvider(final IMessagePrefixProvider arg0) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.IMessageManager#update()
     */
    @Override
    public void update() {
    }

}
