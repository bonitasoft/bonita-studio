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
 * Array management plugin
 */

$(function() {
	/**
	 * Find the index of an element in an array
	 * 
	 * @param obj
	 *            (mixed) The element to look for
	 * @param fromIndex
	 *            (int) (optional) The index where to start the search.
	 * @return (int) This function return the index of obj or -1 if obj is not
	 *         in the array.
	 */
	if (!Array.prototype.indexOf) {
		Array.prototype.indexOf = function(obj, fromIndex) {

			if (typeof fromIndex == 'undefined') {
				// Default fromIndex
				fromIndex = 0;

			} else if (fromIndex < 0) {
				// Negative fromIndex
				fromIndex = Math.max(0, this.length + fromIndex);
			}

			// Search
			for ( var i = fromIndex, j = this.length; i < j; i++) {
				if (this[i] === obj) {
					return i;
				}
			}
			return -1;
		};
	}

	/**
	 * Transform an object into an array
	 * 
	 * @param obj
	 *            (Object) The object to transform
	 * @return (Array) This function transform an object into an indexed array.
	 */
	function toArray(obj) {
		if (!obj.length) {
			return [];
		} // length must be set on the object, or it is not iterable
		var a = [];

		try {
			a = Array.prototype.slice.call(obj, n);
		}
		// IE 6 and posssibly other browsers will throw an exception, so catch
		// it and use brute force
		catch (e) {
			a = [];
			for ( var i in obj) {
				a.push(obj[i]);
			}
			;
		}

		return a;
	}

	/**
	 * Extract a list of defined entries
	 * 
	 * @param src
	 *            (Object) The Object from which to Extract the entries
	 * @param names
	 *            (Array|String|Object) Array of index names to extract (or just
	 *            a single name)
	 * @return (Object) This function construct a new Object containing only the
	 *         entries from "src" defined in "names"
	 */
	$.extract = function(src, names) {
		if ($.isNull(src)) {
			return {};
		}

		// Liste the entries names to get
		var args = [ '' ];
		var j = 1;
		if ($.isArray(names)) {
			var length = names.length;
			for ( var i = 1; i < length; i++) {
				args[j++] = names[i];
			}
		} else if ($.isObject(names)) {
			for ( var i in names) {
				args[j++] = i;
			}
		} else {
			args[0] = names;
		}

		// Construct the output array
		var length = args.length;
		var r = {};
		for ( var i = 1; i < length; i++) {
			if (!$.isNull(src[args[i]])) {
				r[args[i]] = src[args[i]];
			}
		}
		return r;
	};

	/**
	 * Return indexes of an Object
	 * 
	 * @param obj
	 *            (Object) The Object from which to get indexes.
	 * @return (Array) This function returns an array of indexes
	 */
	$.keys = function(obj) {
		var k = [];
		for (i in o) {
			k.push(i);
		}
		return k;
	};
});
