/**
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.internal.runtime.lite.svg;

import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.gvt.renderer.StaticRenderer;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.w3c.dom.Document;

public class SimpleImageTranscoder extends SVGAbstractTranscoder {

	private BufferedImage image;
	private Document document;
	private int canvasWidth = -1, canvasHeight = -1;
	private Rectangle2D canvasAOI;
	private RenderingHints renderingHints;

	public SimpleImageTranscoder(Document document) {
		this.document = document;
		renderingHints = new RenderingHints(null);
	}

	public final Document getDocument() {
		return document;
	}

	public final RenderingHints getRenderingHints() {
		return renderingHints;
	}

	public final int getCanvasWidth() {
		return canvasWidth;
	}

	public final int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasSize(int width, int height) {
		if (this.canvasWidth == width && this.canvasHeight == height) {
			return;
		}
		this.canvasWidth = width;
		this.canvasHeight = height;
		contentChanged();
	}

	public final Rectangle2D getCanvasAreaOfInterest() {
		if (canvasAOI == null) {
			return null;
		}
		Rectangle2D result = new Rectangle2D.Float();
		result.setRect(canvasAOI);
		return result;
	}

	public void setCanvasAreaOfInterest(Rectangle2D value) {
		if (value == null) {
			if (canvasAOI == null) {
				return;
			}
			canvasAOI = null;
			contentChanged();
			return;
		}
		if (value.equals(canvasAOI)) {
			return;
		}
		canvasAOI = new Rectangle2D.Float();
		canvasAOI.setRect(value);
		contentChanged();
	}

	/**
	 * Call before querying for CSS properties. If document has CSS engine installed returns null. Client is responsible to
	 * dispose bridge context if it was returned by this method.
	 */
	public BridgeContext initCSSEngine() {
		if (this.document == null) {
			return null;
		}
		SVGOMDocument sd = (SVGOMDocument) this.document;
		if (sd.getCSSEngine() != null) {
			return null;
		}
		class BridgeContextEx extends BridgeContext {

			public BridgeContextEx() {
				super(SimpleImageTranscoder.this.userAgent);
				BridgeContextEx.this.setDocument(SimpleImageTranscoder.this.document);
				BridgeContextEx.this.initializeDocument(SimpleImageTranscoder.this.document);
			}
		}
		return new BridgeContextEx();
	}

	public void contentChanged() {
		//bufferedImage = null;
	}

	private BufferedImage createImage() {
		if (document == null) {
			return null;
		}
		try {
			if (canvasWidth >= 0) {
				addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(canvasWidth));
			} else {
				removeTranscodingHint(ImageTranscoder.KEY_WIDTH);
			}
			if (canvasHeight >= 0) {
				addTranscodingHint(ImageTranscoder.KEY_HEIGHT, new Float(canvasHeight));
			} else {
				removeTranscodingHint(ImageTranscoder.KEY_HEIGHT);
			}
			if (canvasAOI != null) {
				addTranscodingHint(ImageTranscoder.KEY_AOI, canvasAOI);
			} else {
				removeTranscodingHint(ImageTranscoder.KEY_AOI);
			}
			BufferedImage imageToReturn;
			transcode(new TranscoderInput(document), new TranscoderOutput());
			imageToReturn = this.image;
			this.image = null;
			return imageToReturn;
		} catch (TranscoderException e) {
			Activator.logError("Error transcoding SVG image", e);
		}
		return null;
	}

	protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
		super.transcode(document, uri, output);
		int w = (int) (width + 0.5);
		int h = (int) (height + 0.5);
		ImageRenderer renderer = createImageRenderer();
		renderer.updateOffScreen(w, h);
		// curTxf.translate(0.5, 0.5);
		renderer.setTransform(curTxf);
		renderer.setTree(this.root);
		this.root = null; // We're done with it...
		try {
			Shape raoi = new Rectangle2D.Float(0, 0, width, height);
			// Warning: the renderer's AOI must be in user space
			renderer.repaint(curTxf.createInverse().createTransformedShape(raoi));
			image = renderer.getOffScreen();
		} catch (Exception ex) {
			throw new TranscoderException(ex);
		}
	}

	protected ImageRenderer createImageRenderer() {
		StaticRenderer renderer = new StaticRenderer();
		renderer.getRenderingHints().add(renderingHints);
		return renderer;
	}

	public final BufferedImage getBufferedImage() {
		return createImage();
		
	}
}
