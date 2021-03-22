/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.custom.webservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.extensions.soap12.SOAP12Address;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.wsdl.extensions.soap12.SOAP12Operation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.wst.wsdl.BindingOutput;
import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Output;
import org.eclipse.wst.wsdl.Part;
import org.eclipse.wst.wsdl.util.WSDLResourceImpl;
import org.eclipse.xsd.XSDElementDeclaration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.sun.xml.messaging.saaj.soap.ver1_1.Message1_1Impl;
import com.sun.xml.messaging.saaj.soap.ver1_2.Message1_2Impl;

public class WSDLParsingTool {

    /**
     * A constant representing the identity of the SOAP 1.1 over HTTP binding.
     */
    public static final String SOAP11HTTP_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http";

    /**
     * A constant representing the identity of the SOAP 1.2 over HTTP binding.
     */
    public static final String SOAP12HTTP_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/";
    
	private final Definition definition;
	private org.eclipse.wst.wsdl.Definition eclipseDefinition;

	public static enum SOAP_PROTOCOL {
		SOAP_PROTOCOL_1_1, SOAP_PROTOCOL_1_2
	}

	public WSDLParsingTool(final String wsdlURL, final String authUserName, final String authPassword) throws Exception, IOException {
		if (authUserName != null) {
			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(authUserName, authPassword.toCharArray());
				}
			});
		}

		final WSDLFactory wsdlFactory = WSDLFactory.newInstance();
		final WSDLReader wsdlReader = wsdlFactory.newWSDLReader();
		
		try{
			this.definition = wsdlReader.readWSDL(wsdlURL);
		} catch(WSDLException ex){
			throw new Exception(Messages.errorIntrospectMessage, ex);
		}

		// create the eclipse wsdl object

		final ResourceSet set = new ResourceSetImpl();
		// set.get
		final File tmpFile = File.createTempFile("temp", ".wsdl", ProjectUtil.getBonitaStudioWorkFolder());
		tmpFile.deleteOnExit();
		final FileOutputStream fos = new FileOutputStream(tmpFile);
		final InputStream is = new URL(wsdlURL).openStream();
		FileUtil.copy(is, fos);
		is.close();
		fos.close();

		final WSDLResourceImpl resource = (WSDLResourceImpl) set.createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put(WSDLResourceImpl.CONTINUE_ON_LOAD_ERROR, false);
		resource.load(param);
		eclipseDefinition = resource.getDefinition();
		eclipseDefinition.getNamespaces() ;

	}

	public Definition getDefinition() {
		return definition;
	}

	public Set<QName> getAvailableServices() {
		final Map<QName, Service> wsdlServices = definition.getAllServices();
		return wsdlServices.keySet();
	}

	public Set<String> getAvailablePorts(final QName serviceQName) {
		final Map<String, Port> wsdlServices = definition.getService(serviceQName).getPorts();
		return wsdlServices.keySet();
	}

	private Port getPort(QName serviceQName, String portName) {
		final Service service = definition.getService(serviceQName);
		if (service == null) {
			return null;
		}
		return service.getPort(portName);
	}

	public Set<String> getAvailableEndpoints(final String wsdlLocation, final QName serviceQName, final String portName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		final Set<String> endpoints = new HashSet<String>();
		for (Object ext : wsdlPort.getExtensibilityElements()) {
			if (ext instanceof SOAPAddress) {
				endpoints.add(((SOAPAddress) ext).getLocationURI());
			} else if (ext instanceof SOAP12Address) {
				endpoints.add(((SOAP12Address) ext).getLocationURI());
			}
		}
		if (!endpoints.isEmpty()) {
			return endpoints;
		}

		if (wsdlLocation.endsWith("?wsdl")) {
			endpoints.add(wsdlLocation.substring(0, wsdlLocation.length() - "?wsdl".length()));
		} else {
			endpoints.add(wsdlLocation);
		}
		return endpoints;
	}

	public String getSoapAction(final String operationName, final QName serviceQName, final String portName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		final Binding wsdlBinding = wsdlPort.getBinding();
		final List<BindingOperation> bindingOperations = wsdlBinding.getBindingOperations();

		if (bindingOperations != null) {
			for (BindingOperation op : bindingOperations) {
				if (op.getName().equals(operationName)) {
					for (Object item : op.getExtensibilityElements()) {
						if (item instanceof SOAPOperation) {
							return ((SOAPOperation) item).getSoapActionURI();
						} else if (item instanceof SOAP12Operation) {
							return ((SOAP12Operation) item).getSoapActionURI();
						}
					}
				}
			}
		}
		return null;
	}

	public String getBindingId(final QName serviceQName, final String portName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		for (final Object item : wsdlPort.getBinding().getExtensibilityElements()) {
			if (item instanceof SOAP12Binding) {
				return SOAP12HTTP_BINDING;
			} else if (item instanceof SOAPBinding) {
				return SOAP11HTTP_BINDING;
			}
		}
		return null;
	}

	public SOAP_PROTOCOL getSoapProtocol(final QName serviceQName, final String portName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		for (final Object item : wsdlPort.getBinding().getExtensibilityElements()) {
			if (item instanceof SOAP12Binding) {
				return SOAP_PROTOCOL.SOAP_PROTOCOL_1_2;
			} else if (item instanceof SOAPBinding) {
				return SOAP_PROTOCOL.SOAP_PROTOCOL_1_1;
			}
		}
		return null;
	}

	public Set<String> getAvailableOperations(final QName serviceQName, final String portName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		final Binding wsdlBinding = wsdlPort.getBinding();
		final List<BindingOperation> bindingOperations = wsdlBinding.getBindingOperations();

		final Set<String> operations = new HashSet<String>();

		if (bindingOperations != null) {
			for (BindingOperation op : bindingOperations) {
				operations.add(op.getName());
			}
		}
		return operations;
	}

	public String getRequestMessageNamespace(final QName serviceQName, final String portName, final String operationName) {
		final Port wsdlPort = getPort(serviceQName, portName);
		if (wsdlPort != null) {
			final Binding wsdlBinding = wsdlPort.getBinding();
			final List<BindingOperation> bindingOperations = wsdlBinding.getBindingOperations();
			if (bindingOperations != null) {
				for (BindingOperation op : bindingOperations) {
					if (op.getName().equals(operationName)) {
//						TreeIterator<EObject> it  = eclipseDefinition.eAllContents() ;
//						while(it.hasNext()){
//							EObject elem = it.next() ;
//							if(elem instanceof XSDSchema){
//								Map<String, String> result = ((XSDSchema) elem).getQNamePrefixToNamespaceMap() ;
//								String ns = result.get(serviceQName.getPrefix());
//							}
//						}
						Message message = op.getOperation().getInput().getMessage();
						if(message != null){
							return message.getQName().getNamespaceURI();
						}
					}
				}
			}
		}
		return null;
	}

	public Document createRequest(final Map<String,String> prefixMap, final String namespaceURI, final Map<String, Expression> parameters) throws Exception {
		final Document res = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		XPath xpath = XPathFactory.newInstance().newXPath();
		for (Entry<String, Expression> entry : parameters.entrySet()) {
			Expression expression = entry.getValue();
			if(expression != null && expression.getContent()!= null && !expression.getContent().isEmpty()){
				String[] segments = entry.getKey().split("/");
				StringBuilder builder = new StringBuilder();
				Node parent = res;
				for (String segment : segments) {
					if (segment.length() > 0 && !segment.equals("text()")) {

						builder.append("/" + segment);
						xpath.setNamespaceContext(new CustomNamespaceContext(prefixMap));
						Node node = (Node) xpath.compile(builder.toString()).evaluate(res, XPathConstants.NODE);

						if (node == null) {
							if (segment.startsWith("@")) {
								node = res.createAttribute(segment.substring(1));
							} else {
								if (segment.lastIndexOf(':') > 0) {
									// We have a qualified element
									node = res.createElementNS(namespaceURI, segment);
								} else {
									// Unqualified element
									node = res.createElement(segment);
								}

							}

							parent.appendChild(node);

						}
						parent = node;
					}
				}
				if(ExpressionConstants.CONSTANT_TYPE.equals(expression.getType())){
					parent.setTextContent(expression.getContent());
				}else{
					parent.setTextContent("${"+expression.getContent()+"}");
				}
			}
		}
		return res;
	}

	public String getEnvelope(final Document request, final SOAP_PROTOCOL soapProtocol) throws SOAPException {
		SOAPMessage message = new Message1_2Impl();
		if (soapProtocol.equals(SOAP_PROTOCOL.SOAP_PROTOCOL_1_1)) {
		    message = new Message1_1Impl();
		}
		message.getSOAPHeader().detachNode();
		message.getSOAPBody().addDocument(request);
		
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		String envelope = "";
		try{
			message.writeTo(out);
			envelope = new String(out.toByteArray());
		}catch (Exception e) {
			BonitaStudioLog.error(e);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}
		return envelope;
	}

	public XSDElementDeclaration getOperationPart(QName serviceQName, String portName, Object operationName) {
		EList<?> eServices = eclipseDefinition.getEServices();
		org.eclipse.wst.wsdl.Service service = null;
		for (Object object : eServices) {
			if (((org.eclipse.wst.wsdl.Service) object).getQName().equals(serviceQName)) {
				service = (org.eclipse.wst.wsdl.Service) object;
				break;
			}
		}
		if (service == null) {
			return null;
		}
		org.eclipse.wst.wsdl.Port port = null;
		EList<?> ePorts = service.getEPorts();
		for (Object object : ePorts) {
			if (((org.eclipse.wst.wsdl.Port) object).getName().equals(portName)) {
				port = (org.eclipse.wst.wsdl.Port) object;
			}
		}
		org.eclipse.wst.wsdl.BindingOperation operation = null;
		EList<?> operations = port.getEBinding().getEBindingOperations();
		for (Object object : operations) {
			if (operationName.equals(((org.eclipse.wst.wsdl.BindingOperation) object).getName())) {
				operation = (org.eclipse.wst.wsdl.BindingOperation) object;
				break;
			}
		}
		if (operation == null) {
			return null;
		}
		org.eclipse.wst.wsdl.BindingInput eBindingInput = operation.getEBindingInput();
		XSDElementDeclaration elementDeclaration = null;
		Input eInput = null;
		if (eBindingInput != null) {
			eInput = eBindingInput.getEInput();
			if (eInput != null) {
				final List<?> parts = eInput.getEMessage().getEParts();
				if(!parts.isEmpty()){
					elementDeclaration = ((Part) parts.get(0)).getElementDeclaration();
				}
			}
		}
		return elementDeclaration;
	}

	public XSDElementDeclaration getOperationOutPut(QName serviceQName,
			String portName, String operationName) {
		EList<?> eServices = eclipseDefinition.getEServices();
		org.eclipse.wst.wsdl.Service service = null;
		for (Object object : eServices) {
			if (((org.eclipse.wst.wsdl.Service) object).getQName().equals(serviceQName)) {
				service = (org.eclipse.wst.wsdl.Service) object;
				break;
			}
		}
		if (service == null) {
			return null;
		}
		org.eclipse.wst.wsdl.Port port = null;
		EList<?> ePorts = service.getEPorts();
		for (Object object : ePorts) {
			if (((org.eclipse.wst.wsdl.Port) object).getName().equals(portName)) {
				port = (org.eclipse.wst.wsdl.Port) object;
			}
		}
		org.eclipse.wst.wsdl.BindingOperation operation = null;
		EList<?> operations = port.getEBinding().getEBindingOperations();
		for (Object object : operations) {
			if (operationName.equals(((org.eclipse.wst.wsdl.BindingOperation) object).getName())) {
				operation = (org.eclipse.wst.wsdl.BindingOperation) object;
				break;
			}
		}
		if (operation == null) {
			return null;
		}
		BindingOutput eBindingOutput = operation.getEBindingOutput();
		XSDElementDeclaration elementDeclaration = null;
		if (eBindingOutput != null) {
			final Output eOutput = eBindingOutput.getEOutput();
			if (eOutput != null) {
				final List<?> outParts = eOutput.getEMessage().getEParts();
				elementDeclaration = ((Part) outParts.get(0)).getElementDeclaration();
			}
		}
		return elementDeclaration;
	}

	public String getRequestMessagePrefix(String requestMessageNamespace) {
		return this.definition.getPrefix(requestMessageNamespace) ;
	}

	public String getRequestMessagePrefix(Map<String, String> concreteParams,Map<String, String> prefixMap,String requestMessageNamespace ) {
		for(String entry : concreteParams.keySet()){
			for(String ns  : prefixMap.keySet()){
				if(entry.equals(ns)){
					return ns ;
				}
			}

		}
		return null;
	}
}
