/**
 * 
 */
package org.bonitasoft.studio.designer.core.repository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public interface WebResource {

    URI toURI() throws MalformedURLException, URISyntaxException;
    
}
