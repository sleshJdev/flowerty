/**
 * @author Eugene Putsykovich(slesh) May 10, 2015
 * 
 * 	it makes checking the input field, in accordance with the limits of its field (maxLength, type and etc.)
 */
angular.module("flowertyApplication.utilModule")

.constant("VALIDATE_MESSAGES", (function() {
	if (!String.prototype.format) {
		String.prototype.format = function() {
			var formatted = this;
			for (var i = 0; i < arguments.length; i++) {
				var regexp = new RegExp('\\{' + i + '\\}', 'gi');
				formatted = formatted.replace(regexp, arguments[i]);
			};
			
			return formatted;
		};
	};
	
	return {
		"required" : function(fieldName) {
			return "Please input {0}".format(fieldName.toUpperCase());
		},
		"minlength" : function(fieldName, length) {
			return "Please enter {0} a min length of {1}".format(fieldName.toUpperCase(), length);
		},
		"maxlength" : function(fieldName, length) {
			return "Please enter {0} a max length of {1}".format(fieldName.toUpperCase(), length);
		},
		"pattern" : function(fieldName) {
			return "The {0} has incorrect format".format(fieldName .toUpperCase());
		},
		"email" : function() {
			return "Email has incorrect format";
		},
		"number" : function(fieldName){
			return "The {0} should contain only the numbers".format(fieldName.toUpperCase());
		},
		"positive-only" : function(fieldName){
			return "The {0} can only be a positive number".format(fieldName.toUpperCase());
		},
		"number-min" : function(fieldName, threshold){
			return "The {0} can't be less than {1}".format(fieldName.toUpperCase(), threshold);
		},
		"number-max" : function(fieldName, threshold){
			return "The {0} can't be more than {1}".format(fieldName.toUpperCase(), threshold);
		},
		"password" : function() {
			return "Password has incorrect format";
		},
		"date" : function(fieldName, which){
			return "The {0} should be {1}, than current date".format(fieldName.toUpperCase(), which);
		}
	}
})());
