/**
* 
* This is script it is used to display/hide the form footer according to mode 
* value specified in the URL.
* 
*/
 
window.onload = function(e){
	var hash = window.location.hash;
	var re = new RegExp("mode=([a-zA-Z0-9]*)");
	var m = re.exec(hash);
	if (m == null) {
		alert("No match");
	} else {	
		var mode = m[1];
		if (mode == "form") {
			document.getElementById("footer").style.display="none";
		} else {
			document.getElementById("footer").style.display="block";
		}
	}
}