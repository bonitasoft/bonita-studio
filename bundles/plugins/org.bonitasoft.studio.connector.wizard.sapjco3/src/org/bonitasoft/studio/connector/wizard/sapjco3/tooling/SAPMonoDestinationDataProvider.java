/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.tooling;

import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

/*
 *
 * @author Charles Souillard
 * 
 */
public class SAPMonoDestinationDataProvider implements DestinationDataProvider {

	private DestinationDataEventListener listener;
	private Properties properties;
	private final String destinationName;

	public SAPMonoDestinationDataProvider(final String destinationName) {
		super();
		this.destinationName = destinationName;
	}

	// @Override
	public Properties getDestinationProperties(final String destinationName) {
		if (destinationName.equals(this.destinationName)
				&& this.properties != null) {
			return this.properties;
		}
		throw new RuntimeException("Destination " + destinationName
				+ " is not available");
	}

	// @Override
	public void setDestinationDataEventListener(
			final DestinationDataEventListener eventListener) {
		this.listener = eventListener;
	}

	// @Override
	public boolean supportsEvents() {
		return true;
	}

	public void changeProperties(final Properties properties) {
		if (properties == null) {
			this.listener.deleted(this.destinationName);
			this.properties = null;
		} else {
			if (!properties.equals(this.properties)) {
				this.listener.updated(this.destinationName);
			}
			this.properties = properties;
		}
	}

	public String getDestinationName() {
		return this.destinationName;
	}

}
