package org.bonitasoft.studio.common.model.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLModelCompatibilityValidator implements IValidator<InputStream>{
    
    private ModelNamespaceValidator namespaceValidator;

    public XMLModelCompatibilityValidator(ModelNamespaceValidator namespaceValidator) {
        this.namespaceValidator = namespaceValidator;
    }

    @Override
    public IStatus validate(InputStream inputStream) {
        Path tmpFile = null;
        try {
            tmpFile = Files.createTempFile("model", ".xml");
            Files.copy(inputStream, tmpFile, StandardCopyOption.REPLACE_EXISTING);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document document = dbf.newDocumentBuilder().parse(tmpFile.toFile());
            Element documentElement = document.getDocumentElement();
            return namespaceValidator.validate(documentElement.getNamespaceURI());
        } catch (IOException | SAXException | ParserConfigurationException  e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error("Failed to read xml model namespace", e);
        } finally {
            if (tmpFile != null) {
                try {
                    Files.deleteIfExists(tmpFile);
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

}
