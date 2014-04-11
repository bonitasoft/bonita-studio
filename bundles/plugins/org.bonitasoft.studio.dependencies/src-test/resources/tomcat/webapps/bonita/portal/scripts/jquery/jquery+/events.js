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
 * Gestion des évenements
 * * Listing
 * * Copie
 * Ajout de nouveaux évenements
 * * domChange
 * * attrChange 
 */

/*
 * Fonction de clonage
 * @author Keith Devens
 * @see http://keithdevens.com/weblog/archive/2007/Jun/07/javascript.clone
 */
function clone(srcInstance)
{
	/*Si l'instance source n'est pas un objet ou qu'elle ne vaut rien c'est une feuille donc on la retourne*/
	if(typeof(srcInstance) != 'object' || srcInstance == null)
	{
		return srcInstance;
	}
	/*On appel le constructeur de l'instance source pour crée une nouvelle instance de la même classe*/
	var newInstance = srcInstance.constructor();
	/*On parcourt les propriétés de l'objet et on les recopies dans la nouvelle instance*/
	for(var i in srcInstance)
	{
		newInstance[i] = clone(srcInstance[i]);
	}
	/*On retourne la nouvelle instance*/
	return newInstance;
}

$(function(){
	
	/**
	 * DEBUG - Trace une chaine des évenements d'un ou plusieurs élément
	 * @param string events (facultatif) Liste des évenements à rechercher (séparé  par une virgule)
	 * @param function outputFunction (facultatif) Fonction de log. Si non définie, la fonction retournera la chaîne générée.
	 * @return string|$ si {outputFunction} n'est pas définie, cette fonction retourne la trace, sinon {this} 
	 */
	$.fn.listHandlers = function(events, outputFunction) {
		if ($.isFunction(events)) {
			outputFunction = events;
			events = '*';
		}
		if ($.isNull(events)) {
			events = '*';
		}
		var eventsBufferString = false;
		if ($.isNull(outputFunction)) {
			eventsBufferString = '';
			outputFunction = function(e, txt){
				eventsBufferString = eventsBufferString + txt;
			};
		}
		
	    this.each(function(i){
	        var elem = this;
	        var dEvents = $(this).data('events');
	        
	        if (!dEvents) {return;}
	        $.each(dEvents, function(name, handler){
	            if((new RegExp('^(' + (events === '*' ? '.+' : events.replace(',','|').replace(/^on/i,'')) + ')$' ,'i')).test(name)) {
	               $.each(handler, function(i,handler){
	                   outputFunction(elem, '\n' + i + ': [' + name + '] : ' + handler );
	               });
	           }
	        });
	    });
	    outputFunction = undefined;
	    events = undefined;
	    
	    if (eventsBufferString !== false) {
	    	return eventsBufferString;
	    } else {
	    	return this;
	    }
	};

	$.fn.lockBinds = function(){
		return this.each(function(i){
	    	var e = $(this);
	        var dEvents = e.data('events');
	        
	        if (!dEvents) {return;}
	        
	        e.data('lockBinds', clone(dEvents)).unbind();
	        
	    });
	};
	
	$.fn.unlockBinds = function(){
		return this.each(function(i){
	    	var e = $(this);

	    	var dEvents = e.data('lockBinds');
	        if (!dEvents) {return;}

			$.each(dEvents, function(name, handler){
				$.each(handler, function(i,handler){
					e.bind(name, handler);
				});
	        });
	    });
		
		
	};
	
	/**
	 * Copie les évenements d'un ou plusieurs éléments sur un ou plusieurs autres
	 * @param $ src Elements dans lesquels allez chercher les évenments à copier sur {this}
	 * @return $ Cette fonction retourne {this} après y avoir lié les évenements de {src} 
	 */
	$.fn.rebindFrom = function(src) {
		return this.each(function(i){
	    	var e = $(this);
	        dEvents = src.data('events');
	        
	        if (!dEvents) {return;}
	        
	        $.each(dEvents, function(name, handler){
				$.each(handler, function(i,handler){
					e.bind(name, handler);
				});
	        });
	    });
	};
	
	/**
	 * Options de fonctionnement de l'évenement
	 */
	$.domChange = {
		isEnabled:true,
		/**
		 * Désactive l'envoie des évenements domChange.
		 * Cette fonction est a utiliser lors des modifications massives de l'arbre DOM ne nécessitant pas la remontée d'évenements (updateUI par exemple)
		 */
		disable:function(){
			$.domChange.isEnabled=false
		},
		/**
		 * Active l'envoie des évenements domChange.
		 */
		enable:function(){
			$.domChange.isEnabled=true
		},
		// Liste des fonctions jQuery devant lancer domChange
		events:{ 
			domManip: $.fn.domManip,
			text: $.fn.text,
			html: $.fn.html
		}
	};

	/*
	 * Boucle générant les surcharges des fonctions jQuery de $.domChange.events
	 */
	for (var n in $.domChange.events) {
		eval(
		'$.fn.' + n + ' = function(){\
			var r = $.domChange.events[\'' + n + '\'].apply(this, arguments);\
			if ($.domChange.isEnabled){this.trigger(\'domChange\', [\'' + n + '\'])}\
			return r;\
		};');
	}

	/**
	 * BIND et TRIGGER de DomChange
	 * * TRIGGER - Si fn n'est pas défini
	 * * BIND
	 * @param function fn Callback sur l'évenment 'domchange'  
	 * */
	$.fn.domChange = function(fn){
		if ($.isBoolean(fn)&&!fn){fn=function(){return false}}
		return $.isFunction(fn) ? this.bind('domChange', fn) : this.trigger('domChange');
	}

	/*
	 * Surcharge de la fonction attr() de jQuery 
	 */
	var old_attr_fn = $.fn.attr;
	$.fn.attr = function() {
		var r = old_attr_fn.apply(this, arguments);
		if ($.domChange.isEnabled && arguments.length > 1){
			this.trigger('attrChange', arguments)
		}
		return r;
	}
	
	/*
	 * Surcharge de la fonction removeAttr() de jQuery 
	 */
	var old_removeAttr_fn = $.fn.removeAttr;
	$.fn.removeAttr = function() {
		var r = old_removeAttr_fn.apply(this, arguments);
		if ($.domChange.isEnabled){
			this.trigger('attrChange', arguments)
		}
		return r;
	}
	
	
	/**
	 * BIND et TRIGGER de attrChange
	 * * TRIGGER - Si fn n'est pas défini
	 * @param string name (facultatif) Nom de l'attribut devant déclencher un évenement si il est modifié
	 * @param string value (facultatif) Valeur que doit prendre l'attribut pour déclencher l'évenement
	 * * BIND
	 * @param function fn Callback sur l'évenment 'attrChange'  
	 * */
	$.fn.attrChange = function(name, value, fn){
		if ($.isUndefined(fn)) {
			fn = value;
			value = undefined;
		}
		if ($.isUndefined(fn)) {
			fn = name;
			name = undefined;
		}
		
		if ($.isBoolean(fn)&&!fn){fn = function(){return false}}
		
		if ($.isFunction(fn)) {
			this.bind('attrChange', function(ev, n, v) {
				// Cas du passage d'un tableau
				if (!$.isNull(name) && $.isArray(n) && !$.isNull(n[name])) {
					v = n[name];
					if (!$.isNull(value) && $.isArray(v) && v.indexOf(value) > -1) {
						v = value;
					}
					n = name;
				}
				
				if ((!name || name == n) && (!value || value == v)) {
					fn.apply(this, arguments);
				}
			})
		} else {
			return this.trigger('attrChange', [name, value]);
		}
	}
})