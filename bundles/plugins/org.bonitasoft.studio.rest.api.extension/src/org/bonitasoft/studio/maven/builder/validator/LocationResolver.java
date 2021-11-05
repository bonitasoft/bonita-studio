/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IRegion;

public class LocationResolver {

    private final Document document;
    private final FindReplaceDocumentAdapter findReplaceDocumentAdapter;

    public LocationResolver(final Document document) {
        this.document = document;
        findReplaceDocumentAdapter = new FindReplaceDocumentAdapter(document);
    }

    public Location getLocation(final String value) {
        try {
            final IRegion region = findReplaceDocumentAdapter.find(0, value, true, true, true, false);
            return new Location(region.getOffset(), region.getLength(), lineNumber(region));
        } catch (final BadLocationException e) {
            return new Location(0, 0, 0);
        }
    }

    private int lineNumber(final IRegion region) throws BadLocationException {
        return document.getLineOfOffset(region.getOffset());
    }

    public Location getLineLocation(final String value) {
        try {
            IRegion region = findReplaceDocumentAdapter.find(0, value, true, true, true, false);
            region = document.getLineInformationOfOffset(region.getOffset());
            return new Location(region.getOffset(), region.getLength(), lineNumber(region));
        } catch (final BadLocationException e) {
            return new Location(0, 0, 0);
        }
    }

    public List<Location> getLocations(final String value) {
        final List<Location> result = new ArrayList<>();
        try {
            IRegion region = null;
            int offset = 0;
            while ((region = findReplaceDocumentAdapter.find(offset, value, true, true, true, false)) != null) {
                result.add(new Location(region.getOffset(), region.getLength(), lineNumber(region)));
                offset = offset + region.getOffset() + region.getLength();
            }
            return result;
        } catch (final BadLocationException e) {
            return result;
        }
    }

    public List<Location> findLocationsMatching(final String regexp, boolean completeLine) {
        final List<Location> result = new ArrayList<>();
        try {
            IRegion region = null;
            int offset = 0;
            while ((region = findReplaceDocumentAdapter.find(offset, regexp, true, true, false, true)) != null) {
                final IRegion lineRegion = document.getLineInformationOfOffset(region.getOffset());
                if (completeLine) {
                    result.add(new Location(lineRegion.getOffset(), lineRegion.getLength(), lineNumber(lineRegion)));
                    offset = lineRegion.getOffset() + lineRegion.getLength();
                } else {
                    result.add(new Location(region.getOffset(), region.getLength(), lineNumber(lineRegion)));
                    offset = region.getOffset() + region.getLength();
                }
            }
            return result;
        } catch (final BadLocationException e) {
            return result;
        }
    }

}
