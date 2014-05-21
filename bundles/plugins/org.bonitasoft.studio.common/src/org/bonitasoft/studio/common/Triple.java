/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

/**
 * @author Baptiste Mesta
 *
 */
public class Triple<A, B, C> {
    private A first;
    private B second;
    private C third;


	public Triple(A first, B second, C third) {
        super();
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;
        int hashThird = third != null ? third.hashCode() : 0;
        return (hashFirst + hashSecond + hashThird) * hashSecond + hashFirst + hashThird;
    }

    public boolean equals(Object other) {
        if (other instanceof Triple<?,?,?>) {
        		Triple<?,?,?> otherTriple = (Triple<?,?,?>) other;
                return 
                (( this.first == otherTriple.first ||
                        ( this.first != null && otherTriple.first != null &&
                          this.first.equals(otherTriple.first))) &&
                 ( this.second == otherTriple.second ||
                        ( this.second != null && otherTriple.second != null &&
                          this.second.equals(otherTriple.second))) &&
                  (this.third == otherTriple.third ||
                          ( this.third != null && otherTriple.third != null &&
                            this.third.equals(otherTriple.third))) );
        }

        return false;
    }

    public String toString()
    { 
           return "(" + first + ", " + second +", "+third+")"; 
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
    
    public C getThird() {
		return third;
	}

	public void setThird(C third) {
		this.third = third;
	}
}
