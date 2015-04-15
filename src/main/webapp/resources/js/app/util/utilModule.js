/**
 * @author Eugene Putsykovich(slesh) Apr 7, 2015
 * 
 * Contains filters, directions and other for util stuff
 */
angular.module("flowertyApplication.utilModule", [])

/*
 * Create array of numbers as objects to iterate with 'in' operator
 */
.filter('flowerRange', function() {
	return function(range, total) {
		if (range === undefined || total === undefined) {
			return '';
		}
		total = parseInt(total);
		for (var i = 0; i < total; ++i) {
			range.push(i);
		}
		
		return range;
	};
})

/*
 * make separating, paste together tokens. 
 * example: UPPER_CASE -> Upper case. I this case, seprator='_', connector: ' '
 */
.filter( "flowerSplit", function() {
	return function(value, separator, connector){
		if (value === undefined) {
			return '';
		}
	    var tokens = value.split(separator);
	    var result = '';
	    var l = tokens.length;
	    for(var i = 0; i < l; ++i){
	        result += tokens[i] + (i != l - 1 ? connector : '');
	    }
	    
	    return result;
	}
})
/*
 * filter to change case all character 'from' by 'length'.
 * 
 */
.filter("flowerCase", function() {
	return function(value, from, length, isUp){
		if (value === undefined) {
			return '';
		}
		var left = value.substr(0, from);
		var center = value.substr(from, length);
		center = isUp ? center.toUpperCase() : center.toLowerCase()
	    var right = value.substr(from + length, value.length);
	    
	    return left.concat(center).concat(right);
	}
})