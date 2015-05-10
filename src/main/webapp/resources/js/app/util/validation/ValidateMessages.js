/**
 * @author Eugene Putsykovich(slesh) May 10, 2015
 * 
 */

angular.module("flowertyApplication.contactModule")

.constant("VALIDATE_MESSAGES", (function() {
		if (!String.prototype.format) {
			String.prototype.format = function() {
				var formatted = this;
				for (var i = 0; i < arguments.length; i++) {
					var regexp = new RegExp('\\{' + i + '\\}', 'gi');
					formatted = formatted.replace(regexp, arguments[i]);
				}
				return formatted;
			};
		};
		
		return {
			"required" : function(fieldName) {
				return "Please input {0}".format(fieldName.toUpperCase());
			},
			"minlength" : function(fieldName, length) {
				return "Please enter {0} a min length of {1}".format( fieldName.toUpperCase(), length);
			},
			"maxlength" : function(fieldName, length) {
				return "Please enter {0} a max length of {1}".format( fieldName.toUpperCase(), length);
			},
			"pattern" : function(fieldName, length) {
				return "{0} has incorrect format".format(fieldName .toUpperCase());
			},
			"email" : function() {
				return "Email has incorrect format";
			},
			"number" : function(fieldName){
				return "{0} should contain only the numerals".format(fieldName.toUpperCase());
			}
		}
	})());
