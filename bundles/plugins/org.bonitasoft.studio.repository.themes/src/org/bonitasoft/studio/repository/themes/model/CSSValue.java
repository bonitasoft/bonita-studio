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
package org.bonitasoft.studio.repository.themes.model;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.repository.themes.helper.CSSUtil;

/**
 * @author Romain Bioteau
 *
 */
public class CSSValue  implements CSSPropertyConstants,Cloneable{

    private final String rawValue ;

    public CSSValue(final String value){
        rawValue = value ;
    }

    public String getRawValue(){
        return rawValue ;
    }

    /**
     * It seems that we can't use this, it doesn't handle the case: 45 px (see BorderCSSValue to have a working sample)
     * @param value
     * @param attribute
     * @return
     */
    @Deprecated
    protected boolean isWidth(final String value,final String attribute) {
        if(value.endsWith(PX_UNIT)
                || value.endsWith(EM_UNIT)
                || value.endsWith(EX_UNIT)
                || value.endsWith(PC_UNIT)
                || value.endsWith(IN_UNIT)
                || value.endsWith(CM_UNIT)
                || value.endsWith(MM_UNIT)
                || value.endsWith(PT_UNIT)
                || value.endsWith(PERCENT_UNIT)){
            return true ;
        }
        final List<String> values = Arrays.asList(CSSUtil.getAvailableValuesFor(attribute)) ;
        if(values.contains(value)){
            return true ;
        }
        return false;
    }

    protected boolean isBorderStyle(final String value,final String attribute) {
        final List<String> values = Arrays.asList(CSSUtil.getAvailableValuesFor(attribute)) ;
        if(values.contains(value)){
            return true ;
        }
        return false;
    }

    /**
     * It seems that we can't use this, it doesn't handle the case: 45 px (see BorderCSSValue to have a working sample)
     * @param value
     * @param attribute
     * @return
     */
    @Deprecated
    protected boolean isColor(final String value) {
        if(CSSUtil.getCSSColors().keySet().contains(value)){
            return true ;
        }
        if(value.startsWith("#")
                || value.startsWith("rgb(")){
            return true ;
        }
        return false;
    }

    @Override
    public String toString() {
        return getRawValue();
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (rawValue == null ? 0 : rawValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof CSSValue) {
            return toString().equals(obj.toString());
        }
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CSSValue(getRawValue());
    }
}
