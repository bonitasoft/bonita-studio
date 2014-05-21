/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.bonitasoft.studio.repository.themes.model.css;

import org.bonitasoft.studio.repository.themes.model.css.property.PropertyParser;

/**
 * Creates a new parser to analyze an input CSS2 file or a short-hand property
 * values.
 * 
 * @see org.bonitasoft.studio.repository.themes.model.css.StyleSheetLoader
 * @see CssParser
 */

public class ParserFactory
{


	static public PropertyParser createPropertyParser( String inputProperty )
	{
		return new PropertyParser( inputProperty );
	}



}