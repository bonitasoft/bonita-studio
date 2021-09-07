package org.bonitasoft.studio.antTasks;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MergeXmlFiles extends Task {

    private String destFile;

    private final Vector<FileSet> filesets = new Vector<FileSet>();

    @Override
    public void execute() throws BuildException {
        try {
            System.out.println("Merging xml files in " + destFile);
            DirectoryScanner directoryScanner = filesets.get(0).getDirectoryScanner();
            File basedir = directoryScanner.getBasedir();
            String[] includedFiles = directoryScanner.getIncludedFiles();
            Document merge = merge(basedir, includedFiles);
            print(merge, destFile);
            System.out.println("Merging done!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BuildException(e);
        }
    }

    public void add(final FileSet fileset) {
        if (!filesets.contains(fileset)) {
            filesets.add(fileset);
        }
    }

    /**
     * @param destFolder
     *            the destFolder to set
     */
    public void setDestFile(final String destFile) {
        this.destFile = destFile;
    }

    private static Document merge(final File basedir, final String[] files) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document rootNode = docBuilder.parse(new File(basedir,files[0]));
        System.out.println("Merging file:"+files[0]);
        Node beans = rootNode.getFirstChild();
        for (int i = 1; i < files.length; i++) {
            System.out.println("Merging file:"+files[i]);
            Document merge = docBuilder.parse(new File(basedir,files[i]));
            NodeList childNodes = merge.getFirstChild().getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node importNode = rootNode.importNode(childNodes.item(j), true);
                beans.appendChild(importNode);
            }
        }
        return rootNode;
    }
    private static void print(final Document doc, final String destFile) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        Result result = new StreamResult(new File(destFile));
        transformer.transform(source, result);
    }
}
