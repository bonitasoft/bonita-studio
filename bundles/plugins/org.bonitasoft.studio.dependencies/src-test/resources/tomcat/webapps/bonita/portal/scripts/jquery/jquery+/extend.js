// Extend String
	String.prototype.startsWith = function(needle){ 
        return this.toString().indexOf(needle) === 0;
    }
    String.prototype.endsWith = function(needle){ 
        return this.toString().substr(this.toString().length - needle.length) === needle;
    }
    String.prototype.contains = function(needle){
        return this.toString().indexOf(needle) !== -1;
    }
