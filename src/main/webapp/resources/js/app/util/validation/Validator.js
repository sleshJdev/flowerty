/**
 * @author Eugene Putsykovich(slesh) May 9, 2015
 *
 */

angular.module("flowertyApplication.utilModule")

.directive("flowertyValidate", ["$compile", "VALIDATE_MESSAGES", 
                            	function($compile, VALIDATE_MESSAGES) {
	var template = "<span class='glyphicon form-control-feedback' aria-hidden='true' data-ng-class='info.icon'></span>" +
				   "<small class='btn-danger'>{{info.message}}</small>";
	
	return {
		require: "ngModel",
		restrict: "A",
		scope:{},
		link: function(scope, element, attributes, ngModelCtrl){
			
			scope.info = {
					state: "",
					icon: "",
					message: ""
			};
			
			var link = $compile(template);
            var content = link(scope);

            var levelUp = 1;
            if(attributes.levelUp){
            	levelUp = attributes.levelUp;
            }
            
            var parent = null;
            for(var i = 0; i < levelUp; ++i){
            	if(parent === null){
            		parent = element.parent();
            		continue;
            	};
            	parent = parent.parent();
            };
            
            parent.append(content);
            parent.addClass("has-feedback");
			
			
			function setState(state, icon, message){
				scope.info.state = state,
				scope.info.icon = icon,
				scope.info.message = message;
			}
			
			function validate(currentValue){
				var message = "";
				if(ngModelCtrl.$error.minlength){
					message = VALIDATE_MESSAGES["minlength"](attributes.name, attributes.minlength);
				};
				if(ngModelCtrl.$error.maxlength){
					message = VALIDATE_MESSAGES["maxlength"](attributes.name, attributes.maxlength);
				};
				if(ngModelCtrl.$error.required){
					message = VALIDATE_MESSAGES["required"](attributes.name);
				};
				if(ngModelCtrl.$error.pattern){
					message = VALIDATE_MESSAGES["pattern"](attributes.name);
				};
				if(ngModelCtrl.$error.email){
					message = VALIDATE_MESSAGES["email"]();
				};
				if(ngModelCtrl.$error.number){
					message = VALIDATE_MESSAGES["number"](attributes.name);
				};
				
				var isInvalid = ngModelCtrl.$dirty && ngModelCtrl.$invalid;
				
				if(isInvalid){
					setState("has-error", "glyphicon-remove", message)
				} else {
					setState("has-success", "glyphicon-ok", "")
				};
				
				if(!currentValue){
					setState("", "", "")
				}
				
				parent.removeClass("has-error");
				parent.removeClass("has-success");
				parent.addClass(scope.info.state);
			}
			
			ngModelCtrl.$parsers.unshift(function (viewValue) {
				validate(viewValue);
					
				return viewValue;
            });

            ngModelCtrl.$formatters.unshift(function (modelValue) {
            	validate(modelValue);
            	
            	return modelValue;
            });
		}
	};
}]);