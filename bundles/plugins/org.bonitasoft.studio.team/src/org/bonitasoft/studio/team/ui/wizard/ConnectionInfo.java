/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.wizard;

import org.eclipse.team.svn.ui.utility.UserInputHistory;

public class ConnectionInfo {

    private final UserInputHistory urlHistory = new UserInputHistory("repositoryURL");

    private final UserInputHistory usernameHistory = new UserInputHistory("repositoryUser");

    private String url;

    private String username;

    private String password;

    private boolean savePassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isSavePassword() {
        return savePassword;
    }

    public void setSavePassword(final boolean savePassword) {
        this.savePassword = savePassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String[] getURLHistory() {
        return urlHistory.getHistory();
    }

    public String[] getUsernameHistory() {
        return usernameHistory.getHistory();
    }

    public void saveHistory() {
        urlHistory.addLine(url);
        usernameHistory.addLine(username);
    }

}
