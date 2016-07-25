/*
 * Copyright (c) 2011, Séverin MOUSSEL
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of jQuery+ nor the names of its contributors may be
 *       used to endorse or promote products derived from this software without
 *       specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Variables types tests
 */

$(function() {

	/**
	 * Create a set of types testing options :
	 * <ul>
	 * <li>$.isObject</li>
	 * <li>$.isNumber</li>
	 * <li>$.isString</li>
	 * <li>$.isBoolean</li>
	 * <li>$.isUndefined</li>
	 * </ul>
	 * 
	 * @param e
	 *            (all) The var to check
	 * @return (boolean) This function return TRUE if 'e' is a ???, else it
	 *         returns FALSE.
	 */
	$.each([ 'Object', 'Number', 'String', 'Boolean', 'Undefined' ], function(i, n) {
		$['is' + n] = function(e) {
			return typeof e == n.toLowerCase();
		};
	});

	/**
	 * Check if the parameter is a jQuery Object
	 * 
	 * @param e
	 *            (all) The var to check
	 * @return (boolean) This function return TRUE if 'e' is a jQuery Object,
	 *         else it returns FALSE.
	 */
	$.isJQueryObject = $.isJQ = function(e) {
		return e && !$.isNull(e.jquery);
	};

	/**
	 * Check if an element is undefined
	 * 
	 * @param e
	 *            (all) The var to check
	 * @return (boolean) This function return TRUE if 'e' is not defined, else
	 *         it returns FALSE.
	 */
	$.isNull = $.isUndefined = function(e) {
		return typeof e == 'undefined' || e === null || e === undefined
	};

	$.notNull = function(v, d) {
		return $.isNull(v) ? d : v
	}

	/**
	 * Check if an element is defined
	 * 
	 * @param e
	 *            (all) The var to check
	 * @return (bool) This function return TRUE if 'e' is defined, else it
	 *         returns FALSE.
	 */
	$.isSet = $.isDefined = function(e) {
		return !$.isNull(e);
	};

	/**
	 * Check if an element is an integer
	 * 
	 * @param e
	 *            (all) The var to check
	 * @return (boolean) This function return TRUE if 'e' is an integer, else it
	 *         returns FALSE.
	 */
	$.isInteger = $.isInt = function(e) {
		return $.isNumber(e) && Math.floor(e) == v;
	};
});