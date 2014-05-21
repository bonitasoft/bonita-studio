/*
 * Ajoute des fonctions de manipulation de larbre DOM
 */
$(function(){
	
	/**
	 * Retourne ou attribut le outerHTML (replaceWith)
	 * 
	 * * SET - Si {v} est définit
	 * Remplace un élément par le code HTML ou l'élément définit
	 * @param string|$ v Nouveau code HTML ou élément
	 * @return $ Cette fonction retourn le nouvel élément;
	 * 
	 * * GET - Si {v} n'est pas définit
	 * Récupère le code outer HTML
	 * @return string Cette fonction retourne le code HTML  
	 */
	$.fn.outerHTML = function(v){
		// Replaces the content
		if($.isString(v) || $.isJQueryObject(v)){
			v = $(v);
			
			$(this).replaceWith(v);
			
			return v;
						
		// Returns the value
		}else{
			return $('<div>').append(this.clone()).html();
		}
	}
	
	/**
	 * Remove the parents of the set of matched elements from the DOM, leaving the matched elements in their place.
	 * -- Récupération de code de Jquery 1.4
	 * @TODO Supprimer lors du passage à JQuery > 1.4.0
	 */
	if (!$.fn.unwrap) {
		$.fn.unwrap = function() {
		  return this.parent().each(function( n, elem ){
		    $.nodeName( elem, 'body' ) || $( elem ).replaceWith( elem.childNodes );
		  }).end();
		};
	}
	
	/**
	 * Récupère ou remplace le code HTML de l'élément principal mais pas des enfants.
	 * 
	 * * SET - Si {v} est définit
	 * Remplace un élément par le code HTML ou l'élément définit
	 * @param string|$ v Nouveau code HTML ou élément
	 * @return $ Cette fonction retourn le nouvel élément;
	 * 
	 * * GET - Si {v} n'est pas définit
	 * Récupère le code HTML de l'élément sans ses enfants
	 * @return string Cette fonction retourne le code HTML  
	 */
	$.fn.rootHTML = function(v){
		if ($.isString(v)) {
			v = $(v);
		}
		if(v !== undefined){
			var p = this.parent();
			var t = this;
			v.empty().append(t.children());
			
			t.data('rootHTMLMarker', new Date().getTime());
			
			var p2 = $('<div>');
			p.children().each(function(){
				if ($(this).data('rootHTMLMarker') && $(this).data('rootHTMLMarker') == t.data('rootHTMLMarker')) {
					t.removeData('rootHTMLMarker');
					p2.append(v);
				} else {
					p2.append($(this))
				}
			})
			
			p.empty().append(p2.children());
				
			return v;
			
		// Returns the value
		}else{
			return this.clone().empty().outerHTML();
		}
	}

	/**
	 * Récupère ou set plusieurs attributs
	 * 
	 * * SET - Si {v} est définit
	 * Set les attributs passés
	 * @param array v Tableau associatif des attributs à setter
	 * @return $ Cette fonction retourne {this}
	 * 
	 * * GET - Si {v} n'est pas définit
	 * Récupère tout les attributs
	 * @return array Cette fonction retourne tout les attributs sous forme d'un tableau associatif  
	 */
	$.fn.attrs = function(v) {
		if (!$.isNull(v)) {
			return this.attr(v);
		}
		var l = this[0].attributes.length;
		var a = {};
		for (var i=0;i<l;i++){
			if (this[0].attributes[i].specified) {
				a[this[0].attributes[i].nodeName] = this[0].attributes[i].nodeValue;
			}
		}
		return a;
	}
});