/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.palette.DefaultElementNameProvider;
import org.bonitasoft.studio.common.palette.ProcessPaletteLabelProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class NamingUtils {

    private static final String VERSION_SEPARATOR = "-";
    private static final String UTF8 = "UTF-8";
    private MainProcess process;

    private NamingUtils(final MainProcess process) {
        this.process = process;
    }

    private NamingUtils() {

    }

    public static NamingUtils getInstance(final Element element) {
        final MainProcess process = ModelHelper.getMainProcess(element);
        if (process != null) {
            return new NamingUtils(process);
        }
        return new NamingUtils();
    }

    public static Expression generateConstantExpression(final String name) {
        return generateConstantExpression(name, false);
    }

    public static Expression generateConstantExpression(final String name, final boolean returnTypeFixed) {
        return generateConstantExpression(name, String.class.getName(), returnTypeFixed);
    }

    public static Expression generateConstantExpression(final String name, final String typeName,
            final boolean returnTypeFixed) {
        final Expression constantExpression = ExpressionHelper.createConstantExpression(name, name, typeName);
        constantExpression.setReturnTypeFixed(returnTypeFixed);
        return constantExpression;
    }

    public String generateName(final Element newItem, final Element existingItem) {
        final DefaultElementNameProvider elementNameProvider = new DefaultElementNameProvider();
        final Adapter adapter = new ProcessItemProviderAdapterFactory().createAdapter(newItem);
        final ItemProviderAdapter itemProvider = (ItemProviderAdapter) adapter;
        String defaultName = elementNameProvider.getNameFor(newItem);
        // the container of the newItem (where we search for the number max)
        EObject mainContainer;
        if (defaultName == null) {
            defaultName = itemProvider.getText(newItem);
        }

        mainContainer = process;

        if (mainContainer != null) {
            int number = getMaxElements((Element) mainContainer, defaultName);
            number++;
            defaultName += number;
        }
        newItem.setName(defaultName);

        return defaultName;
    }

    /**
     * get the max number of elements prefixed if label
     *
     * @param element
     * @param label
     * @return
     */
    public static int getMaxElements(final Element element, final String label) {
        int max = 0;
        if (element != null) {
            for (final Iterator<?> iterator = element.eAllContents(); iterator.hasNext();) {
                final EObject eObject = (EObject) iterator.next();
                if (eObject instanceof Element) {
                    final Element child = (Element) eObject;
                    String name = child.getName();
                    if (name != null && name.startsWith(label)) {
                        final int index = name.indexOf(label) + label.length();
                        name = name.substring(index);
                        try {
                            max = Math.max(Integer.valueOf(name), max);
                        } catch (final NumberFormatException n) {
                        }
                    }
                }
            }
        }
        return max;
    }

    public static String convertToId(final Element item) {
        return convertToId(item.getName(), item);
    }

    public static String convertToId(final String label, final Element item) {

        if (label != null) {
            if (item instanceof MainProcess) {
                final AbstractProcess p = (AbstractProcess) item;
                return convertToId(p.getName());
            } else {
                return convertToId(label);
            }
        }
        return ""; //$NON-NLS-1$

    }

    public static String convertToId(final String label, final boolean toLowerCaseFirstChar) {
        final String result = convertToId(label);
        if (toLowerCaseFirstChar) {
            if (result.trim().length() > 0) {
                return result.substring(0, 1).toLowerCase() + result.substring(1);
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    public static String convertToId(final String label) {
        final StringBuffer tmp = new StringBuffer();
        char car;
        char toAppendChar;

        int i = 0;
        if (label != null) {
            while (i < label.length()) {
                car = label.charAt(i);
                car = StringOperation.sansAccent(car);
                toAppendChar = Character.isJavaIdentifierPart(car) ? car : '_';

                if (i == 0) {

                    if (Character.UnicodeBlock.of(car) == Character.UnicodeBlock.BASIC_LATIN
                            && !('$' == car)) {
                        tmp.append(car);
                    } else {
                        tmp.append('_');
                        if (toAppendChar != '_') {
                            tmp.append(toAppendChar);
                        }
                    }
                } else {
                    if (Character.isJavaIdentifierPart(car)
                            && Character.UnicodeBlock.of(car) == Character.UnicodeBlock.BASIC_LATIN
                            && !('$' == car)) {
                        tmp.append(car);
                    } else {
                        tmp.append('_');
                    }
                }

                i++;
            }
            return tmp.toString();
        }
        return ""; //$NON-NLS-1$

    }

    /**
     * Think to use JavaConventions.validateXXX() instead of this one when possible
     *
     * @param text
     * @param uppercaseFirst
     * @return
     */
    public static String toJavaIdentifier(final String text, final Boolean uppercaseFirst) {
        boolean isStart = true;
        boolean nextIsUpperCase = true;
        final StringBuilder res = new StringBuilder();
        for (final char c : text.toCharArray()) {
            boolean isValid = false;
            if (isStart) {
                isValid = Character.isJavaIdentifierStart(c);
            } else {
                isValid = Character.isJavaIdentifierPart(c);
            }
            if (isValid) {
                if (isStart) {
                    res.append(uppercaseFirst ? Character.toUpperCase(c) : Character.toLowerCase(c));
                } else {
                    res.append(nextIsUpperCase ? Character.toUpperCase(c) : c);
                }
                nextIsUpperCase = false;
                isStart = false;
            } else {
                nextIsUpperCase = true;
            }
        }

        return res.toString();
    }

    public static String getEventDefaultId(final MessageFlow self) {
        if (self.getTarget() != null && self.getTarget().getEvent() != null) {
            final Message event = ModelHelper.findEvent(self.getSource(), self.getTarget().getEvent());
            if (event != null) {
                self.setName(event.getName());
                return event.getName();
            } else {
                self.setName("");
                return "";
            }
        } else if (self.getSource() != null && self.getSource().getEvents().size() > 0) {
            self.setName(self.getSource().getEvents().get(0).getName());
            self.getTarget().setEvent(self.getName());
            return self.getSource().getEvents().get(0).getName();
        }
        return "";
    }

    public static String convertToPackage(final String name, final String version) {
        return name + "_" + version.replace(".", "_");
    }

    public static String generateNewName(final Set<String> existingNames, final String defaultName, int startIndex) {
        if (startIndex == 0 && existingNames.contains(defaultName) || startIndex > 0) {
            if (startIndex == 0) {
                startIndex++;
            }
            while (existingNames.contains(defaultName + startIndex)) {
                startIndex++;
            }
            if (startIndex > 0) {
                return defaultName + startIndex;
            }
        }
        return defaultName;
    }

    /**
     * @param existingNames must ba a list in lowerCase
     * @param defaultName
     * @return
     */
    public static String generateNewNameCaseInsensitive(final Set<String> existingNames, final String defaultName) {
        int cpt = 1;
        while (existingNames.contains((defaultName + cpt).toLowerCase())) {
            cpt++;
        }
        return defaultName + cpt;
    }

    public static String toConnectorDefinitionFilename(final String definitionId, final String defVersion,
            final boolean inculdeExtension) {
        if (!inculdeExtension) {
            return definitionId + VERSION_SEPARATOR + defVersion;
        } else {
            return definitionId + VERSION_SEPARATOR + defVersion + ".def";
        }
    }

    public static String toConnectorImplementationFilename(final String implementationId, final String implementationVersion,
            final boolean inculdeExtension) {
        if (!inculdeExtension) {
            return implementationId + VERSION_SEPARATOR + implementationVersion;
        } else {
            return implementationId + VERSION_SEPARATOR + implementationVersion + ".impl";
        }
    }

    public static boolean isUTF8String(final String inputString) throws UnsupportedEncodingException {
        final String encoded = URLEncoder.encode(inputString, UTF8);
        final String decoded = URLDecoder.decode(encoded, UTF8);
        return inputString.equals(decoded);
    }

    public static String toDiagramFilename(final MainProcess diagram) {
        return toDiagramFilename(diagram.getName(), diagram.getVersion());
    }

    public static String toDiagramFilename(final String processName, final String baseVersion) {
        return toDiagramFilenameWithoutFileExtension(processName, baseVersion) + ".proc";
    }

    public static String toDiagramFilenameWithoutFileExtension(final String name, final String version) {
        return String.format("%s%s%s", convertToValidURI(name), VERSION_SEPARATOR, convertToValidURI(version));
    }

    public static String convertToValidURI(final String input) {
        String result = new String(input);
        for (final String invalidChar : URLEncodableInputValidator.reservedChars) {
            if (input.contains(invalidChar)) {
                result = result.replace(invalidChar, "_");
            }
        }
        return result;
    }

    public static String getEResourceFileName(final EObject eObject, final boolean includeExtension) {
        final Resource resource = eObject.eResource();
        if (resource != null) {
            URI uri = resource.getURI();
            if (!includeExtension) {
                uri = uri.trimFileExtension();
            }
            return URI.decode(uri.lastSegment());
        }
        return null;
    }

    public static String getSimpleName(final String qualifiedName) {
        Assert.isNotNull(qualifiedName);
        String simpleName = qualifiedName;
        if (simpleName.contains(".")) {
            final String[] split = simpleName.split("\\.");
            simpleName = split[split.length - 1];
        }
        return simpleName;
    }

    public static String getPackageName(final String qualifiedName) {
        Assert.isNotNull(qualifiedName);
        String packageName = "";
        if (qualifiedName.contains(".")) {
            packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
        }
        return packageName;
    }

    public static String getPaletteTitle(final List<IElementType> relationshipTypes) {
        if (!relationshipTypes.isEmpty()) {
            return new ProcessPaletteLabelProvider().getProcessPaletteText(relationshipTypes.get(0).getEClass());
        }
        return null;
    }

    public static String getPaletteDescription(final List<IElementType> relationshipTypes) {
        if (!relationshipTypes.isEmpty()) {
            return new ProcessPaletteLabelProvider().getProcessPaletteDescription(relationshipTypes.get(0).getEClass());
        }
        return null;
    }

    public static String toConnectorImplementationJarName(final ConnectorImplementation implementation) {
        return toConnectorImplementationFilename(implementation.getImplementationId(),
                implementation.getImplementationVersion(), false) + ".jar";
    }

}
