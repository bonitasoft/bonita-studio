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

$(function() {

	/**
	 * Get the list of all options stored in an element
	 * 
	 * @param reload
	 *            (boolean) Optional - If TRUE, forces the reload from the
	 *            attribute, else it will use a cached version.
	 * @return (Array) This function returns the options in an associative
	 *         array.
	 */
	$.fn.getOptions = function(reload) {
		if ((reload == undefined || !reload) && this.data('__o')) {
			return this.data('__o');
		}

		var r = this.attr('rel');
		this.removeAttr('rel');
		var o = r ? o = $.parseJSON(r) : {};
		this.data('__o', o);

		return o;
	};

	/**
	 * Read a single option entry. If the option doesn't exist, the function
	 * will return [d].
	 * 
	 * @param n
	 *            (String) The name of the option.
	 * @param d
	 *            mixed (optional) The default value to return if the option
	 *            doesn't exist.
	 * @return (mixed) This function returns the value of the option [n] if it
	 *         exists, else it returns [d]
	 */
	$.fn.getOption = function(n, d) {
		if (!this.hasOption(n)) {
			return d;
		}
		return this.getOptions()[n];
	};

	/**
	 * Get a text in the reserved sub-option '__texts'.
	 * 
	 * @param k
	 *            (String) The key of the string to retrieve
	 * @return (String) This functions return the string corresponding to [k].
	 */
	$.fn.getText = function(k) {
		if (!this.hasOption('__texts') || $.isNull(this.getOption('texts')[k])) {
			return k;
		}
		return this.getOption('texts')[k];
	};

	/**
	 * Add or update an option
	 * 
	 * @param n
	 *            (String) The name of the option
	 * @param v
	 *            mixed The value of the option
	 * @return (jQuery) This function returns [this]
	 */
	$.fn.setOption = function(n, v) {
		var o = this.getOptions();
		o[n] = v;
		this.data('__o', o);
		return this;
	};

	/**
	 * Add or update a set of options
	 * 
	 * @param v
	 *            (Array) The options to add as an associative array
	 * @return (jQuery) This function returns [this]
	 */
	$.fn.setOptions = function(v) {
		var o = this.getOptions();
		o = $.extend(o, v);
		this.data('__o', o);
		return this;
	};

	/**
	 * Check if an option exists
	 * 
	 * @param n
	 *            (String) The name of the option
	 * @param v
	 *            (mixed) (optional) The value of the option. If sets, the
	 *            function will also check that the value of the existing option
	 *            is equal to [v].
	 * @return (Boolean) This function returns TRUE if the function exists, else
	 *         it returns FALSE.
	 */
	$.fn.hasOption = function(n, v) {
		var o = this.getOptions();
		return (!$.isUndefined(o[n]) && ($.isNull(v) || o[n] == v));
	};

	/**
	 * Define the default value if an option doesn't exist
	 * 
	 * @param n
	 *            (String) The name of the option
	 * @param v
	 *            (mixed) (optional) The value of the option.
	 * @return (jQuery) This function returns [this]
	 */
	$.fn.setOptionDefault = function(n, v) {
		if (!this.hasOption(n)) {
			this.setOption(n, v);
		}
		return this;
	};

	/**
	 * Define the default value if an option doesn't exist
	 * 
	 * @param v
	 *            (Array) The options to set as an associative array
	 * @return (jQuery) This function returns [this]
	 */
	$.fn.setOptionDefault = function(v) {
		for (var n in v) {
			this.setOptionDefault(n,v[n]);
		}
		return this;
	};

});