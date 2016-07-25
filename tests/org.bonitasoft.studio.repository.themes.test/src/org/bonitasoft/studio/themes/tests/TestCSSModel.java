/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.themes.tests;

import junit.framework.TestCase;

import org.bonitasoft.studio.repository.themes.model.BackgroundCSSValue;
import org.bonitasoft.studio.repository.themes.model.BorderCSSValue;
import org.bonitasoft.studio.repository.themes.model.FontCSSValue;
import org.bonitasoft.studio.repository.themes.model.MarginCSSValue;
import org.bonitasoft.studio.repository.themes.model.PaddingCSSValue;

/**
 * @author Romain Bioteau
 *
 */
public class TestCSSModel extends TestCase {

	public void testBorderCSSValue() throws Exception {
		BorderCSSValue value = new BorderCSSValue("1px solid #FFFFFF") ;

		assertEquals(value.getWidth(),"1px") ;
		assertEquals(value.getStyle(),"solid") ;
		assertEquals(value.getColor(),"#FFFFFF") ;
		assertEquals(value.getRawValue(),"1px solid #FFFFFF") ;
		
		value = new BorderCSSValue("solid #FFFFFF") ;

		assertEquals(value.getStyle(),"solid") ;
		assertEquals(value.getColor(),"#FFFFFF") ;
		assertEquals(value.getRawValue(),"solid #FFFFFF") ;
		
		value = new BorderCSSValue("20% #FFFFFF") ;

		assertEquals(value.getWidth(),"20%") ;
		assertEquals(value.getColor(),"#FFFFFF") ;
		assertEquals(value.getRawValue(),"20% #FFFFFF") ;
		
		value = new BorderCSSValue("100% double") ;

		assertEquals(value.getWidth(),"100%") ;
		assertEquals(value.getStyle(),"double") ;
		
		value = new BorderCSSValue("100% gros") ;

		assertEquals(value.getWidth(),"100%") ;
		assertEquals(value.getStyle(),null) ;
		
		value = new BorderCSSValue("nawak gros") ;
		
		assertEquals(value.getWidth(),null) ;
		assertEquals(value.getStyle(),null) ;
		assertEquals(value.getColor(),null) ;
		
		value = new BorderCSSValue("solid 100%") ;
		
		assertEquals(value.getStyle(),"solid") ;
		assertEquals(value.getWidth(),"100%") ;
		assertEquals(value.getColor(),null) ;
 	}
	
	public void testFontCSSValue() throws Exception {
		FontCSSValue value = new FontCSSValue("normal small-caps 120%/120% fantasy") ;

		assertEquals(value.getFontFamily(),"fantasy") ;
		assertEquals(value.getFontWeight(),"normal") ;
		assertEquals(value.getFontStyle(),"normal") ;
		assertEquals(value.getFontVariant(),"small-caps") ;
		assertEquals(value.getFontSize(),"120%") ;
		assertEquals(value.getLineHeight(),"120%") ;
		
		value = new FontCSSValue("bold italic large Palatino, serif") ;
		
		assertEquals(value.getFontFamily(),"Palatino,serif") ;
		assertEquals(value.getFontWeight(),"bold") ;
		assertEquals(value.getFontStyle(),"italic") ;
		assertEquals(value.getFontVariant(),null) ;
		assertEquals(value.getFontSize(),"large") ;
		assertEquals(value.getLineHeight(),null) ;
	
 	}
	
	public void testMarginCSSValue() throws Exception {
		MarginCSSValue value = new MarginCSSValue("0%") ;

		assertEquals("0%",value.getTop()) ;
		
		value = new MarginCSSValue("0% .5em") ;

		assertEquals("0%",value.getTop()) ;
		assertEquals(".5em",value.getRight()) ;
	
 	}
	
	public void testPaddingCSSValue() throws Exception {
		PaddingCSSValue value = new PaddingCSSValue("0%") ;

		assertEquals("0%",value.getTop()) ;
		
		value = new PaddingCSSValue("0% .5em") ;

		assertEquals("0%",value.getTop()) ;
		assertEquals(".5em",value.getRight()) ;
	
 	}
	
	public void testBackgroundCSSValue() throws Exception {
		BackgroundCSSValue value = new BackgroundCSSValue("url(\"chess.png\") gray 50% repeat fixed") ;

		assertEquals("url(\"chess.png\")",value.getImage()) ;
		assertEquals("gray",value.getColor()) ;
		assertEquals("fixed",value.getAttachment()) ;
		assertEquals("repeat",value.getRepeat()) ;
		assertEquals("50%",value.getPosition()) ;
	
 	}
	
}
