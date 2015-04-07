/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */

angular.module("flowertyApplication.flowerFilters", [])

.filter('flowerRange', function() {
	return function(range, total) {
		total = parseInt(total);
		for (var i = 0; i < total; ++i){
			range.push(i);
		}			
		return range;
	};
});