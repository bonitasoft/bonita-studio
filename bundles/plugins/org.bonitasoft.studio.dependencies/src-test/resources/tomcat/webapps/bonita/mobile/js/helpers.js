//### HELPERS ###//

// Ajoute une méthode .serializeJSON() à jQuery
(function($) {
	$.fn.serializeJSON = function() {
		var json = {};
		jQuery.map($(this).serializeArray(), function(n, i) {
			json[n['name']] = n['value'];
		});
		return json;
	};
})(jQuery);

function _(string) {
	for (var i = 0; translations && i < translations.length; i++) {
		var translation = translations[i];
		
		if (translation.key == string) {
			return translation.value;
		}
	}
	
	return string;
}

Twig.extendFunction('_', function(value){
    return _(value);
});

function renderView(templateName, data){
	return twig({ ref: templateName }).render(data);
}

function timeAutoFormat(timeElement){
	//add firefox compatibility by replacing by T https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Date/parse
	var date = new Date(timeElement.attr('datetime').replace(new RegExp(" "), "T")+"Z");
	var today = new Date();
	
	if(dateDiff.inDays(today, date) < 0){ // date passée
		timeElement.timeago();
	} else { // date futur
		timeElement.text(date.toDateString());
	}
}

function parseDate(date) {
	var re = date.match("([0-9]+)-([0-9]+)-([0-9]+) ([0-9]+):([0-9]+):.*");
	return (re[2] + '/' + re[3] + '/' + re[1] + ' ' + re[4] + ':' + re[5]);	
}

function getUrlParameters(key, defaultValue) {
    var vars = [], hash;
    var href = window.location.href;
    var queryUrl = href.slice(href.lastIndexOf('?') + 1);
    var hashes = queryUrl.split('&');
    for(var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
	
	if(vars[key] == null)
		return defaultValue;
	else
		return vars[key];
}

function reloadPage() {
	$.mobile.changePage(
		window.location.href,
		{
			allowSamePageTransition : true,
			transition              : 'none',
			showLoadMsg             : false,
			reloadPage              : true
		}
	);
}

function getThumbnailColor(processId) {
	 var mod = sumDigits(processId) % 10;
	 
	 if (mod == 0) return 'red'
	 if (mod == 1) return 'black'
	 if (mod == 2) return 'orange'
	 if (mod == 3) return 'green'
	 if (mod == 4) return 'blue'
	 if (mod == 5) return 'red'
	 if (mod == 6) return 'black'
	 if (mod == 7) return 'orange'
	 if (mod == 8) return 'green'
	 if (mod == 9) return 'blue'
	 return false
	}

function sumDigits(number) {
	var str = number.toString();
	var sum = 0;

	for (var i = 0; i < str.length; i++) {
		sum += parseInt(str.charAt(i), 10);
	}

	return sum;
}

function toTitleCase(str)
{
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

/**
 * << PLUS UTILE, A SUPPRIMER >>
 * Convertis les dates retourné par le serveur en date exploitable par le plugin timeAgo
 * param dateBonita = 2012-10-24 10:24:52.604
 * return = 2012-10-24T10:24:52Z
 *
function dateMicroformat(dateBonita){
	dateBonita = dateBonita.split(" ");
	return dateBonita[0] + 'T' + dateBonita[1].slice(0,8) + 'Z';
}
*/

var dateDiff = {
    inDays: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
        return parseInt((t2-t1)/(24*3600*1000));
    },
    inWeeks: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
        return parseInt((t2-t1)/(24*3600*1000*7));
    },
    inMonths: function(d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();
        return (d2M+12*d2Y)-(d1M+12*d1Y);
    },
    inYears: function(d1, d2) {
        return d2.getFullYear()-d1.getFullYear();
    }
}