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
 * Checkboxes helpers
 */

$(function(){
	
	/**
	 * Indique si un élément ou une liste d'éléments sont cochés
	 * @return boolean Cette fonction retourne TRUE si tout les éléments sont cochés, sinon FALSE. 
	 */
	$.fn.checked = function(){
		var r = true;
		this.each(function(){
			r = r && this.checked; 
		});
		return r;
	};
	
	/**
	 * Coche d'une ou plusieur case à cocher. Cette fonction peut servir de bind ou d'action
	 * 
	 * * ACTION - Si pas d'argument ou si {c} est un booléen
	 * Coche ou décoche la case
	 * @param boolean c (facultatif) Etat à donner à la case. TRUE ou UNDEFINED pour cochée, FALSE pour décocher
	 * @return $ Cette fonction renvoie {this}
	 * 
	 * * BIND - Si {c} est une fonction
	 * Attribut des callbacks aux evenements 'check' et 'uncheck'
	 * @param function c Callback à appeler si la case devient cochée
	 * @param function u (facultatif) Callback à appeler si la case devient décochée
	 * @return $ Cette fonction renvoie {this}
	 */
	$.fn.check = function(c,u) {
		if ($.isNull(c))
			c = true;

		if ($.isBoolean(c))
			return this.each(function(){
				this.checked = c;
			});
		
		this.bind('check',c);
		if (!$.isNull(u))
			this.uncheck(u);
		return this;
	};

	/**
	 * Décoche d'une ou plusieur case à cocher. Cette fonction peut servir de bind, d'action ou de trigger
	 * 
	 * * ACTION - Si pas d'argument
	 * Décoche la case
	 * 
	 * * BIND - Si {f} est une fonction
	 * @param f function Callback à appeler si la case devient décochée

	 * @return $ Cette fonction renvoie {this}
	 */
	$.fn.uncheck = function(f) {
		if ($.isNull(f))
			return this.check(false);

		return this.bind('uncheck',f);
	};
	
	/**
	 * Inverse l'état d'une ou plusieurs cases à cocher
	 * @return $ Cette fonction renvoie {this}
	 */
	$.fn.checkToggle=function() {
		return this.each(function(){
			this.checked = !this.checked;
		});
	};
	
	/*
	 * Déclencheur des évenments 'check' et 'uncheck'
	 */
	$(':checkbox, :radio').live('change.x', function(){
		var t = $(this),c=t.checked();
		if (c) {
			t.closest('form').find('input[type='+t.attr('type')+'][name='+t.attr('name')+']').not(t).trigger('uncheck');
		}
		t.trigger(c ? 'check' : 'uncheck');
	});

});