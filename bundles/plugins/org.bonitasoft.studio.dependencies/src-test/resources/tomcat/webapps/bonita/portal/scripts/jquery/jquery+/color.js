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
 * Calcul de couleurs
 */

$(function(){
	
	/**
	 * Calcul la valeur d'une couleur désopacifiée
	 * @param string c Couleur d'origine
	 * @param float opacity Opacité à appliquer
	 * @param string background Couleur de fond
	 * @return string Cette fonction retourne un code couleur sous forme de chaîne de caractère au format rgb(...,...,...)
	 */
	$.calcColorOpacity = function(c,opacity, background) {
		var rgb = getRGB(c);
		var rgbb = getRGB(background);
	    for(var i = 0; i < rgb.length; i++){
	    	rgb[i] = Math.round((rgbb[i] * (1- opacity)) + (rgb[i] * opacity));
	    }
	    return 'rgb(' + Math.round(rgb[0]) + ',' + Math.round(rgb[1]) + ',' + Math.round(rgb[2]) + ')';
	}

	/**
	 * Effectue une modification de couleur
	 * @param string c La couleur à modifier
	 * @param function|string|number ch Modificateur à appliquer
	 * * Si {ch} est une fonction : on applique la fonction aux 3 composantes (rouge, vert et bleu) de la couleur.
	 * * Si {ch} est une chaine : il s'agit d'un opérateur suivi d'un nombre '+12', '-7.5', '/2' ou '*10'. Le calcul est alors appliqué à chaque composante
	 * * Si {ch} est un nombre : on effectue une addition sur chaque composante
	 * @return string Cette fonction retourne un code couleur sous forme de chaîne de caractère au format rgb(...,...,...)
	 */
	$.calcColor = function(c,ch) {
		var op;
		if ($.isNumber(ch)) {
			op = '+';
		} else if ($.isFunction(ch)) {
			op = 'func';
		} else {
			op = ch.charAt(0);
			ch = parseFloat(ch.substring(1, ch.length-1));
		}

		switch(op){
			default:
				return c;
			case '+':
			case '-':
			case '/':
			case '*':
				var chRef = ch;
				ch = function(v){return eval('v' + op + 'chRef');}
				break;
		}

		var rgb = getRGB(c);
	    for(var i = 0; i < rgb.length; i++){
	        rgb[i] = Math.min(255, Math.max(0, ch.call(null, rgb[i])));
	    }

	    return 'rgb(' + Math.round(rgb[0]) + ',' + Math.round(rgb[1]) + ',' + Math.round(rgb[2]) + ')';
	}
});