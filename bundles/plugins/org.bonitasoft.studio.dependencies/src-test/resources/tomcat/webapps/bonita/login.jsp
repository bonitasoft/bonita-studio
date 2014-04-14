<%-- Copyright (C) 2009 BonitaSoft S.A.
 BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 2.0 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>. --%>
<%@page import="java.net.URLDecoder"%>
<%@page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.bonitasoft.web.toolkit.client.common.texttemplate.Arg"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.bonitasoft.console.common.server.i18n.I18n"%>
<%@page import="static org.bonitasoft.console.common.server.i18n.I18n._"%>
<%@page import="org.bonitasoft.console.common.server.jsp.JSPUtils"%>
<%
    JSPUtils JSP = new JSPUtils(request, session);

    // Define Locale
    String defaultLocale = JSP.getParameter("_l") != null ? JSP.getParameter("_l") : JSP.getSessionOrCookie("BOS_Locale", "en_US");
    defaultLocale = "default".equals(defaultLocale) ? "en_US" : defaultLocale;

    I18n.getInstance().loadLocale(I18n.stringToLocale(defaultLocale));
    I18n.setDefaultLocale(I18n.stringToLocale(defaultLocale));

    // Build Action URL
    final String tenantId = JSP.getParameter("tenant");
    String redirectUrl = JSP.getParameter("redirectUrl");

    StringBuffer actionUrl = new StringBuffer("loginservice?");
	StringBuffer styleUrl = new StringBuffer("portal/themeResource?theme=portal&location=bonita.css");
    if (tenantId != null) {
        actionUrl.append("tenant=").append(tenantId).append("&");
		styleUrl.append("&tenant=").append(tenantId);
    }
    
    if (redirectUrl != null) {
    	if (tenantId != null) {
    		redirectUrl = redirectUrl.replaceAll("[\\?&]tenant=\\d+$", "").replaceAll("tenant=\\d+&", "");
    		if (redirectUrl.contains("?")) {
    			redirectUrl += "&";
    		} else {
    			redirectUrl += "?";
    		}
    		redirectUrl += "tenant=" + tenantId;
    	}
        actionUrl.append("redirectUrl=" + URLEncoder.encode(redirectUrl, "UTF-8"));
    }

    // Error messages
    String errorMessage = "";
    boolean disableLogin = false;
    String noBonitaHomeMessage = request.getAttribute("noBonitaHomeMessage") + "";
	String noBonitaClientFileMessage = request.getAttribute("noBonitaClientFileMessage") + "";
	String loginFailMessage = request.getAttribute("loginFailMessage") + "";

    // Technical problems
    if (
        !JSP.getParameter("isPlatformCreated", true) ||
		!JSP.getParameter("isTenantCreated", true) ||
		"tenantNotActivated".equals(loginFailMessage) ||
		"noBonitaHomeMessage".equals(noBonitaHomeMessage) ||
		"noBonitaClientFileMessage".equals(noBonitaClientFileMessage)
	) {
        errorMessage = _("The server is not available") + "<br />" + _("Please, contact your administrator.");
        disableLogin = true;
    }
    // No profile for this user
    else if ("noProfileForUser".equals(loginFailMessage)) {
        errorMessage = _("Login failed. No profile has been set up for this user. Contact your administrator.");
    }
 	// Login or password error
    else if ("loginFailMessage".equals(loginFailMessage)) {
        errorMessage = _("Unable to log in. Please check your username and password.");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Bonita BPM Portal</title>
<link rel="icon" type="image/png" href="images/favicon2.ico" />
<!-- Load LESS CSS -->
<script type="text/javascript" src="portal/scripts/includes/array.prototype.js"></script>
<link rel="stylesheet" type="text/css" href="<%= styleUrl %>"/>


<script type="text/javascript" src="portal/scripts/jquery/jquery-1.6.4.js"></script>
<script>
	/* Add url hash to form action url */
	$(document).ready(function() {
		var form = $('#LoginForm');
		form.attr('action', form.attr('action') + encodeURI(window.location.hash));
	});
</script>

</head>
<body id="LoginPage">
	<div id="LoginHeader"><h1><span><%= _("Welcome to") %></span> <%= _("Bonita BPM Portal") %></h1></div>
	<div id="floater"></div>
	<div id="LoginFormContainer" >
		<div id="logo">
		</div>
		<div class="body">
			<form id="LoginForm" action="<%=actionUrl%>" method="post">
				<div class="header">
					<h2><%=_("Login form")%></h2>
				</div>
				<p class="error"><%=errorMessage.length() > 0 ? errorMessage  : ""%></p>
				<div class="formentries">
					<div class="formentry" title="<%=_("Enter your login (username)")%>">
						<div class="label">
							<label for="username"><%=_("User")%></label>
						</div>
						<div class="input">
							<input title="<%=_("Login")%>" id="username" name="username" value="<%=JSP.getSessionOrCookie("username", "")%>" placeholder="<%=_("User")%>" type="text" tabindex="1" maxlength="50" <%=disableLogin ? "disabled=\"disabled\" " : ""%> />
						</div>
					</div>
					<div class="formentry" title="<%=_("Enter your password")%>">
						<div class="label">
							<label for="password"><%=_("Password")%></label>
						</div>
						<div class="input">
							<input title="<%=_("Password")%>" id="password" name="password" type="password" tabindex="2" maxlength="50" placeholder="<%=_("Password")%>" <%=disableLogin ? "disabled=\"disabled\" " : ""%> />
						</div>
						<input name="_l" type="hidden" value="<%=defaultLocale%>" />
					</div>
				</div>
				<div class="formactions">
					<input type="submit" value="Login" <%=disableLogin ? "disabled=\"disabled\" " : ""%> />
				</div>
			</form>
		</div>
	</div>
	<div class="footer" id="footer">
		Bonitasoft © 2013 All rights reserved.
	</div>
</body>
</html>
