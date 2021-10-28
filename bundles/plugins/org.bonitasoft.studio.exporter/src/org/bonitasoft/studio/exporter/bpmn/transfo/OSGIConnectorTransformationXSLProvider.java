package org.bonitasoft.studio.exporter.bpmn.transfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.exporter.Activator;
import org.eclipse.core.runtime.FileLocator;

public class OSGIConnectorTransformationXSLProvider implements ConnectorTransformationXSLProvider{

    @Override
    public File getConnectorXSLFile() throws IOException {
        final URL xsltUrl = Activator.getDefault().getBundle().getEntry(ConnectorTransformationXSLProvider.CONNECTOR_XSL_FILE_PATH);
        return new File(FileLocator.toFileURL(xsltUrl).getFile());
    }

}
