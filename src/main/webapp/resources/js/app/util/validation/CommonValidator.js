/**
 * @author Eugene Putsykovich(slesh) May 9, 2015
 *
 */
angular.module("flowertyApplication.utilModule")

.constant("VALIDATE_DATE", (function(){
	var format = "YYYY-MM-DD";
	return{
		validate : function(dateString, isCheckOnPast){
			var now = moment().format(format);
	        var dateToCheck = moment(dateString).format(format);
	        
	        return isCheckOnPast ? moment(dateToCheck).isBefore(now) : moment(dateToCheck).isAfter(now);
		}
	};
})())

.directive("flowertyValidate", ["$compile", "VALIDATE_MESSAGES", "VALIDATE_DATE", 
                            	function($compile, VALIDATE_MESSAGES, VALIDATE_DATE) {
	var template = "<span class='glyphicon form-control-feedback' aria-hidden='true' data-ng-class='info.icon'></span>" +
				   "<small class='text-nowrap' data-ng-class='info.type'>{{info.message}}</small>";

	return {
		require: "ngModel",
		restrict: "A",
		scope:{},
		link: function(scope, element, attributes, ngModelCtrl){
			scope.info = {
					type: "",
					state: "",
					icon: "",
					message: ""
			};
			
			var link = $compile(template);
            var content = link(scope);

            var levelUp = 1;
            if(attributes.levelUp){
            	levelUp = attributes.levelUp;
            };
            
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
			
			function setState(type, state, icon, message){
				scope.info.type = type,
				scope.info.state = state,
				scope.info.icon = icon,
				scope.info.message = message;
			};
			
			function validate(currentValue){
				var message = "";
				if(ngModelCtrl.$error.minlength){
					message = VALIDATE_MESSAGES["minlength"](attributes.name, attributes.minlength);
					
				}else if(ngModelCtrl.$error.maxlength){
					message = VALIDATE_MESSAGES["maxlength"](attributes.name, attributes.maxlength);
				
				}else if(ngModelCtrl.$error.required){
					message = VALIDATE_MESSAGES["required"](attributes.name);
				
				}else if(ngModelCtrl.$error.pattern){
					message = VALIDATE_MESSAGES["pattern"](attributes.name);
				
				}else if(ngModelCtrl.$error.email){
					message = VALIDATE_MESSAGES["email"]();
				
				}else if(ngModelCtrl.$error.number){
					message = VALIDATE_MESSAGES["number"](attributes.name);
				
				}else if(ngModelCtrl.$error.min){
					if(attributes.min === "0"){
						message = VALIDATE_MESSAGES["positive-only"](attributes.name);
						
					} else {
						message = VALIDATE_MESSAGES["number-min"](attributes.name, attributes.min);
					
					};
				}else if(ngModelCtrl.$error.max){
					message = VALIDATE_MESSAGES["number-max"](attributes.name, attributes.max);
				
				}else if(ngModelCtrl.$error.password){
					message = VALIDATE_MESSAGES["password"]();
					
				}else if(ngModelCtrl.$error.isFutureDate){
					message = VALIDATE_MESSAGES["date"](attributes.name, "more");
					
				}else if(ngModelCtrl.$error.isPastDate){
					message = VALIDATE_MESSAGES["date"](attributes.name, "less");
					
				};
				
				var isInvalid = ngModelCtrl.$dirty && ngModelCtrl.$invalid;
				
				if(isInvalid){
					setState("text-danger", "has-error", "glyphicon-remove", message);
				} else if(!!currentValue){
					setState("text-danger", "has-success", "glyphicon-ok", "");
				};
				
				if(ngModelCtrl.$error.required){
					setState("text-warning", "has-warning", "glyphicon-warning-sign", message);
				};
				
				parent.removeClass("has-error");
				parent.removeClass("has-success");
				parent.removeClass("has-warning");
				parent.addClass(scope.info.state);
			};
			
			function validateDate(dateString){
				var dateValidateType = attributes.dateValidate;
				if(!!dateValidateType && dateValidateType === "past"){
					ngModelCtrl.$setValidity("isPastDate", VALIDATE_DATE.validate(dateString, true));
				}else if(!!dateValidateType && dateValidateType === "future"){
					ngModelCtrl.$setValidity("isFutureDate", VALIDATE_DATE.validate(dateString, false));
				};
			};
			
			scope.$watch(function(){
				return ngModelCtrl.$viewValue;
			}, function(value){
				validateDate(value);
				validate(value);
			});
		}
	};
}]);