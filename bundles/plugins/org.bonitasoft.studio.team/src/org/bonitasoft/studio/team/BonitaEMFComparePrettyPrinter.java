/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.team;

import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.DifferenceSource;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;

/**
 * Implementation based on EMFComparePrettyPrinter
 *
 * @author Aurelien Pupier
 */
public class BonitaEMFComparePrettyPrinter {

    public static String getDifferences(final Comparison comparison) {
        final StringBuilder sb = new StringBuilder();

        sb.append("REFERENCE CHANGES\n");
        for (final Diff diff : comparison.getDifferences()) {
            if(diff instanceof ReferenceChange){
                appendDiff((ReferenceChange) diff, sb);
            }
        }

        sb.append("ATTRIBUTE CHANGES\n");
        for (final Diff diff : comparison.getDifferences()) {
            if(diff instanceof AttributeChange){
                appendDiff((AttributeChange) diff, sb);
            }
        }
        return sb.toString();
    }

    private static void appendDiff(final ReferenceChange diff, final StringBuilder sb) {
        final ReferenceChange refChange = diff;
        final String valueName;
        if (refChange.getValue() instanceof Element) {
            valueName = ((Element) refChange.getValue()).getName();
            final String change = computeReadbaleType(diff);
            final String objectName = computeReadableObjectName(refChange);
            sb.append("value " + valueName + " has been " + change + " reference "
                    + refChange.getReference().getName() + " of object " + objectName + "\n");
        }
    }

    private static void appendDiff(final AttributeChange diff, final StringBuilder sb) {
        final AttributeChange attChange = diff;
        String valueName = "null";
        if (attChange.getValue() != null) {
            valueName = attChange.getValue().toString();
        }
        final String change = computeReadbaleType(diff);
        final String objectName = computeReadableObjectName(attChange);
        sb.append("value " + valueName + " has been " + change + " attribute "
                + attChange.getAttribute().getName() + " of object " + objectName + "\n");
    }

    protected static String computeReadableObjectName(final AttributeChange attChange) {
        final String objectName;
        final Match match = attChange.getMatch();
        objectName = computeReadableObjectName(match);
        return objectName;
    }

    protected static String computeReadableObjectName(final ReferenceChange refChange) {
        final String objectName;
        final Match match = refChange.getMatch();
        objectName = computeReadableObjectName(match);
        return objectName;
    }

    protected static String computeReadableObjectName(final Match match) {
        final String objectName;
        if (match.getLeft() instanceof Element) {
            objectName = ((Element) match.getLeft()).getName();
        } else if (match.getRight() instanceof Element) {
            objectName = ((Element) match.getRight()).getName();
        } else if (match.getOrigin() instanceof Element) {
            objectName = ((Element) match.getOrigin()).getName();
        } else {
            objectName = "";
        }
        return objectName;
    }

    protected static String computeReadbaleType(final Diff diff) {
        String change = "";
        if (diff.getSource() == DifferenceSource.RIGHT) {
            change = "remotely ";
        }
        if (diff.getKind() == DifferenceKind.ADD) {
            change += "added to";
        } else if (diff.getKind() == DifferenceKind.DELETE) {
            change += "deleted from";
        } else if (diff.getKind() == DifferenceKind.CHANGE) {
            change += "changed from";
        } else {
            change += "moved from";
        }
        return change;
    }
}
