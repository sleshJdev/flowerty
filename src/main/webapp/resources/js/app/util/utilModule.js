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
		total = parseInt(total);
		for (var i = 0; i < total; ++i) {
			range.push(i);
		}
		return range;
	};
})

/*
 * Make separating, paste together tokens and make capitalize first character of
 * first token. Example: UPPER_CASE -> Upper case. I this case, seprator='_'.
 */
.filter(
		"flowerSplit",
		function() {
			return function(value, separator) {
				if (value === undefined) {
					return '';
				}
				var tokens = value.toLowerCase().split(separator);
				var result = "";
				for (var i = 0; i < tokens.length; ++i) {
					if (i == 0) {
						result = tokens[i].charAt(0).toUpperCase() + tokens[i].substring(1);
						continue;
					}
					result += " " + tokens[i];
				}
				return result;
			}
		});