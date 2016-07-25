/**
 * Copyright (c) 2008 Borland Software Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.runtime.lite.svg;

import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.WeakHashMap;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.internal.runtime.lite.svg.Activator;
import org.eclipse.gmf.internal.runtime.lite.svg.InferringNamespaceContext;
import org.eclipse.gmf.internal.runtime.lite.svg.SimpleImageTranscoder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SVGFigure extends Figure {

    private String uri;
    private boolean failedToLoadDocument = true;
    protected boolean specifyCanvasWidth = true;
    protected boolean specifyCanvasHeight = true;
    protected SimpleImageTranscoder transcoder;

    private static WeakHashMap<String, Document> documentsMap = new WeakHashMap<String, Document>();

    public final String getURI() {
        return uri;
    }

    public final void setURI(final String uri) {
        setURI(uri, true);
    }

    public void setURI(final String uri, final boolean loadOnDemand) {
        this.uri = uri;
        transcoder = null;
        failedToLoadDocument = false;
        if (loadOnDemand) {
            loadDocument();
        }
    }

    private void loadDocument() {
        transcoder = null;
        failedToLoadDocument = true;
        if (uri == null) {
            return;
        }

        final SAXSVGDocumentFactory factory = new CustomSAXSVGDocumentFactory();
        try {
            Document document;
            if (documentsMap.containsKey(uri)) {
                document = documentsMap.get(uri);
            } else {
                document = factory.createDocument(uri);
                documentsMap.put(uri, document);
            }
            transcoder = new SimpleImageTranscoder(document);
            failedToLoadDocument = false;
        } catch (final IOException e) {
            Activator.logError("Error loading SVG file", e);
        }
    }

    protected final Document getDocument() {
        if (failedToLoadDocument) {
            return null;
        }
        if (transcoder == null) {
            loadDocument();
        }
        return transcoder == null ? null : transcoder.getDocument();
    }

    /**
     * Returns true if document was loaded without errors; tries to load document if needed.
     */
    public final boolean checkContentAvailable() {
        return getDocument() != null;
    }

    private XPath getXPath() {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new InferringNamespaceContext(getDocument().getDocumentElement()));
        return xpath;
    }

    /**
     * Executes XPath query over the SVG document.
     */
    protected final NodeList getNodes(final String query) {
        final Document document = getDocument();
        if (document != null) {
            try {
                return (NodeList) getXPath().evaluate(query, document, XPathConstants.NODESET);
            } catch (final XPathExpressionException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Reads color value from the document.
     */
    protected Color getColor(final Element element, final String attributeName) {
        if (getDocument() == null || getDocument() != element.getOwnerDocument()) {
            return null;
        }
        Color color = null;
        // Make sure that CSSEngine is available.
        final BridgeContext ctx = transcoder.initCSSEngine();
        try {
            color = SVGUtils.toSWTColor(element, attributeName);
        } finally {
            if (ctx != null) {
                ctx.dispose();
            }
        }
        return color;
    }

    @Override
    protected void paintFigure(final Graphics graphics) {
        super.paintFigure(graphics);
        Document document = getDocument();
        if (document == null) {
            return;
        }

        Image image = null;
        try {
            final Rectangle r = getClientArea();
            transcoder.setCanvasSize(specifyCanvasWidth ? r.width : -1, specifyCanvasHeight ? r.height : -1);
            updateRenderingHints(graphics);
            final BufferedImage awtImage = transcoder.getBufferedImage();
            if (awtImage != null) {
                image = toSWT(Display.getCurrent(), awtImage);
                graphics.drawImage(image, r.x, r.y);
            }
        } finally {
            if (image != null) {
                image.dispose();
            }

            document = null;
        }
    }

    protected void updateRenderingHints(final Graphics graphics) {
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getAntialias();
            } catch (final Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
            }
            if (transcoder.getRenderingHints().get(RenderingHints.KEY_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getTextAntialias();
            } catch (final Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
            }
            if (transcoder.getRenderingHints().get(RenderingHints.KEY_TEXT_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
    }

    /**
     * Converts an AWT based buffered image into an SWT <code>Image</code>. This will always return an <code>Image</code> that
     * has 24 bit depth regardless of the type of AWT buffered image that is passed into the method.
     * 
     * @param awtImage the {@link java.awt.image.BufferedImage} to be converted to an <code>Image</code>
     * @return an <code>Image</code> that represents the same image data as the AWT <code>BufferedImage</code> type.
     */
    protected static org.eclipse.swt.graphics.Image toSWT(final Device device, final BufferedImage awtImage) {
        // We can force bitdepth to be 24 bit because BufferedImage getRGB
        // allows us to always retrieve 24 bit data regardless of source color depth.
        final PaletteData palette = new PaletteData(0xFF0000, 0xFF00, 0xFF);
        final ImageData swtImageData = new ImageData(awtImage.getWidth(), awtImage.getHeight(), 24, palette);
        // Ensure scansize is aligned on 32 bit.
        final int scansize = (awtImage.getWidth() * 3 + 3) * 4 / 4;
        final WritableRaster alphaRaster = awtImage.getAlphaRaster();
        final byte[] alphaBytes = new byte[awtImage.getWidth()];
        for (int y = 0; y < awtImage.getHeight(); y++) {
            final int[] buff = awtImage.getRGB(0, y, awtImage.getWidth(), 1, null, 0, scansize);
            swtImageData.setPixels(0, y, awtImage.getWidth(), buff, 0);
            if (alphaRaster != null) {
                final int[] alpha = alphaRaster.getPixels(0, y, awtImage.getWidth(), 1, (int[]) null);
                for (int i = 0; i < awtImage.getWidth(); i++) {
                    alphaBytes[i] = (byte) alpha[i];
                }
                swtImageData.setAlphas(0, y, awtImage.getWidth(), alphaBytes, 0);
            }
        }
        return new org.eclipse.swt.graphics.Image(device, swtImageData);
    }

    public final Rectangle2D getAreaOfInterest() {
        getDocument();
        return transcoder == null ? null : transcoder.getCanvasAreaOfInterest();
    }

    public void setAreaOfInterest(final Rectangle2D value) {
        getDocument();
        if (transcoder != null) {
            transcoder.setCanvasAreaOfInterest(value);
        }
        repaint();
    }

    public final boolean isSpecifyCanvasWidth() {
        return specifyCanvasWidth;
    }

    public void setSpecifyCanvasWidth(final boolean specifyCanvasWidth) {
        this.specifyCanvasWidth = specifyCanvasWidth;
        contentChanged();
    }

    public final boolean isSpecifyCanvasHeight() {
        return specifyCanvasHeight;
    }

    public void setSpecifyCanvasHeight(final boolean specifyCanvasHeight) {
        this.specifyCanvasHeight = specifyCanvasHeight;
        contentChanged();
    }

    /**
     * Should be called when SVG document has been changed. It will be re-rendered and figure will be repainted.
     */
    public void contentChanged() {
        getDocument();
        if (transcoder != null) {
            transcoder.contentChanged();
        }
        repaint();
    }
}
