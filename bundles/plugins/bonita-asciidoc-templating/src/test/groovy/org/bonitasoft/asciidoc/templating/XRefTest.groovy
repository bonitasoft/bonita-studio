/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.asciidoc.templating;

import spock.lang.Specification;
import spock.lang.Unroll

@Unroll
class XRefTest extends Specification {

    def "should normalize cross reference '#id' into '#normalizedForm'"(String id, String normalizedForm){
	given:
	def xref = new XRef() {

	    @Override
	    public String getId() {
		return crossRefId(id);
	    }

	    @Override
	    public String getLabel() {
		return null;
	    }
	    
	}
	
	expect:
	xref.getId() == normalizedForm
	
	where:
	id                        | normalizedForm
	'mon id avec des espaces' | 'mon-id-avec-des-espaces'
	'mon id avec dès accents' | 'mon-id-avec-des-accents'
	'$d; \\id* special"char?' | 'd-id-specialchar'
    }
}
