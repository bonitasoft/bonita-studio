/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.provider;

public interface LocalizedConnectorDefinition {

    public static final String PAGE_TITLE = "pageTitle";
    public static final String PAGE_FIELD = "label";
    public static final String PAGE_DESCRIPTION = "pageDescription";
    public static final String CATEGORY = "category";
    public static final String CONNECTOR_DEFINITION = "connectorDefinitionLabel";
    public static final String CONNECTOR_DEFINITION_DESCRIPTION = "connectorDefinitionDescription";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_EXAMPLE = "example";
    public static final String OUTPUTS_DESC = "outputsDescription";
    public static final String OUTPUT_DESC = "output.description";
    
    String getConnectorDefinitionLabel();
    
    String getConnectorDefinitionDescription();
    
    String getPageTitle(String pageId);

    String getPageDescription(String pageId);

    String getFieldLabel(String fieldId);

    String getFieldDescription(String fieldId);

    String getFieldExample(String fieldId);

    String getOutputsDescription();

    String getOutputDescription(String outputName);

}
