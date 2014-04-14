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

/*
 * Ajoute des sélecteurs à Sizzler
 */

$(function(){
	/**
	 * Parsing des filtres avec attribut de comparaison :xxx(name*=value)
	 */
	var parseSelectorParam = function(p) {
		//:xxx(__) accepts 'dataKey', 'dataKey=Value', 'dataKey.InnerdataKey', 'dataKey.InnerdataKey=Value'
		//Also instead of = we accept: != (does not equal Value), ^= (starts with Value), 
		//		$= (ends with Value), *=Value (contains Value);
		//$(e).data(dataKey) or $(e).data(dataKey)[innerDataKey] (optional more innerDataKeys)
		//When no value is speciefied we return all eents that have the dataKey specified, similar to [attribute]
		var p = p[3].split(',')
		
		var operators = ['=', '<', '>'];
		var prefix = ['!', '<', '>', '^', '$', '*'];

		var r = [];
		for (var i=0; i<p.length; i++) {
			// On découpe d'après l'opérateur
			r[i] = {
				name:false,
				operator:false,
				value:p[i]
			};
			for (var j=0; j<operators.length;j++) {
				var q = p[i].split(operators[j]);
				if (q.length > 1) {
					p[i] = q;
					r[i].name = p[i][0];
					r[i].operator = operators[j];
					r[i].value = p[i][1];
					break;
				}
			}
			
			if (!r[i].name.length) {
				r[i].name = r[i].value;
				r[i].value = undefined;
			} else if(r[i].operator == '=') {
				// On vérifit si l'opérateur à un préfix (!, <, >, ^, $, *)
				var pf = r[i].name.charAt(r[i].name.length - 1);
				
				for (var j=0; j<prefix.length; j++) {
					if(pf == prefix[j]){
						r[i].name = r[i].name.substring(0, r[i].name.length-1);
						r[i].operator = prefix[j] + r[i].operator; 
					}
				}
			}
		}
		
		return r;
	}
	
	/**
	 * Test des filtres avec attribut de comparaison :xxx(name*=value)
	 */
	var testSelectorParam = function(v1,v2,o) {
		if(!v2) {
			return !$.isNull(v1);
		}
		
		// Conversion de types pour comparaison
		if (typeof v1 != typeof v2) {
			switch (typeof v1) {
				case 'string':
					v2 = '' + v2;
					break;
				case 'boolean':
					if ($.isString(v2)) {
						if (parseInt(v2)) {
							v2 = true; 
						} else {
							v2 = v2.toLowerCase();
							v2 = (v2 == 'true' || v2 == 'yes' || v2 == '1');
						}
					} else {
						v2 = !!v2;
					}
					break;
				case 'number':
					v2 = parseInt(v2);
					break;
			} 
		}			

		switch(o){
			case '=': //equals
				return v1 == v2; 
			case '>=': //equals
				return v1 >= v2; 
			case '<=': //equals
				return v1 <= v2; 
			case '!': //does not equeal
			case '!=': //does not equeal
				return v1 != v2;
			case '^': //starts with
			case '^=': //starts with
				return v1.startsWith(v2);
			case '$': //ends with
			case '$=': //ends with
				return v1.endsWith(v2);
			case '*': //contains
			case '*=': //contains
				return v1.contains(v2);

		}
		return false;
	}


	// Mémorise les filtres originaux pour les appeler en surcharge
	var old_filters = {};
	
	/**
	 * Ajout du filtre :FOCUS
	 * Sélectionne l'élément qui détient le focus
	 */
	$.expr[':'].focus = function(a){
		return (a==document.activeElement);
	}
	
	/**
	 * Ajoute des filtres :SELECT et :OPTION 
	 * Sélectionne les éléments par {tagname} ou avec le data '__x_{tagname}' à TRUE 
	 */ 
	$.each(['select', 'option'], function(i,name){
		$.expr[':'][name] = function(elem){
			return elem.tagName == name || $(elem).data('__x_' + name);
		}
	});

	/**
	 * Surcharge des filtres :CHECKBOX, :RADIO
	 */ 
	/*$.each(['radio', 'checkbox'], function(i,name){
		old_filters[name] = $.expr[':'][name];
		$.expr[':'][name] = function(elem){
			return (old_filters[name] && old_filters[name]) || $(elem).data('__x_' + name);
		}
	});*/
	
	/**
	 * Ajout du filtre :DATA(name*=value)
	 * Filtre par data JQuery
	 */
	$.expr[':'].data = function(e, c, p){
		p = parseSelectorParam(p);
		var r = true; 
		for (var i=0;i<p.length;i++) {
			r = r && testSelectorParam($(e).data(p[i].name), p[i].value, p[i].operator);
		}
		return r;
	}

	/**
	 * Ajout du filtre :CSS(name*=value)
	 * Filtre par style CSS
	 */
	$.expr[':'].css = function(e, c, p){
		p = parseSelectorParam(p);
		var r = true; 
		for (var i=0;i<p.length;i++) {
			r = r && testSelectorParam($(e).css(p[i].name), p[i].value, p[i].operator);
		}
		return r;
	}
	
	/**
	 * Ajout du filtre :OPT(name*=value)
	 * Filtre par Option JOptions
	 */
	$.expr[':'].opt = function(e, c, p){
		e = $(e);
		if (!e.attr('rel') && !e.data('__o')){
			return false;
		}

		p = parseSelectorParam(p);
		var r = true;
		for (var i=0;i<p.length;i++) {
			r = r && testSelectorParam(e.getOption(p[i].name), p[i].value, p[i].operator);
		}
		return r;
	}
	
	/**
	 * Ajout du filtre :VIEWABLE(full/top/bottom/left/right/percent)
	 * 
	 * $('toto:viewable(top>=50%)') // On voit au moins 50% du haut de l'élément
	 * $('toto:viewable(left<5%)')
	 * $('toto:viewable(50%)')
	 * 
	 * Filtre qui valide qu'un élément est déja visible dans la fenêtre
	 */
	$.expr[':'].viewable = function(e, c, p){
		var v = $(window).dimensions()
		var e = $(e).dimensions()

		if (e.top >= v.bottom || e.bottom <= v.top || e.left >= v.right || e.right <= v.left)
			return false
		
		p = parseSelectorParam(p)
		if ($.isNull(p.name)) {
			p.name = 'partial'
		}
		if ($.isNull(p.value)) {
			p.value = '50%';
		}

		p.percent = 0;
		if ($.isString(p.value)) {
			if (p.value.indexOf('px') > -1) {
				p.value = parseFloat(p.value.substring(0, p.value.indexOf('px') > -1))
			} else if (p.value.indexOf('%') > -1) {
				p.percent = parseFloat(p.value.substring(0, p.value.indexOf('%') > -1)) / 100
				p.value = 0
			} else {
				p.value = parseFloat(p.value);
			}
		}

		switch (p.name.toLowerCase()) {
			case 'left' :
				e.right = e.left + (p.value + (e.width * p.percent))
				if (e.right < v.left || e.right > v.right)
					return false
				break;
			case 'right' :
				e.left = e.right - (p.value + (e.width * p.percent))
				if (e.left < v.left || e.left > v.right)
					return false
				break;
			case 'top' :
				e.bottom = e.top + (p.value + (e.height * p.percent))
				if (e.bottom < v.top || e.bottom > v.bottom)
					return false
				break;
			case 'bottom' :
				e.top = e.bottom - (p.value + (e.height * p.percent))
				if (e.top < v.top || e.top > v.bottom)
					return false
				break;
			case 'full' :
				if (e.top < v.top || e.top > v.bottom || e.bottom < v.top || e.bottom > v.bottom || e.left < v.left || e.left > v.right || e.right < v.left || e.right > v.right)
					return false
				break;
			case 'partial' :
				if (percent = 0) {
					if (e.top >= v.bottom || e.bottom <= v.top || e.left >= v.right || e.right <= v.left)
						return false
				} else {
					var visible = {
						width : Math.min(e.right, v.right) - Math.max(e.left, v.left),
						bottom : Math.min(e.bottom, v.bottom) - Math.max(e.top, v.top)
					}
					
					if ((visible.width * visible.height) / (e.width * e.height) < p.percent)
						return false;
				}
				break;
		}
		
		return true;
	}
	
});