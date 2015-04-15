/**
 * @author Eugene Putsykovich(slesh) Apr 13, 2015
 *
 */

angular.module("flowertyApplication.errorModule", ["ngRoute"])

//TODO:maybe unnecessary...
.constant("ERROR_CONSTANTS", (function(){
	var ERROR_MODULE_PATH = "resources/js/app/error/";
	
	return {
		ERROR_PAGE: ERROR_MODULE_PATH + "partial/error-message.html"
	}
})())

//use for pass value between error handler and controllers.
//TODO:maybe unnecessary...
.service("transportShareErrorData", function() {
	var errorBundle = {};
	
	return {
		getErrorBundle: function(){
			return errorBundle;
		},
		setErrorBundle: function(bundle){
			errorBundle = bundle;
		}
	};
})

.config(["$routeProvider", "$provide", "$httpProvider", "ERROR_CONSTANTS", 
         function($routeProvider, $provide, $httpProvider, ERROR_CONSTANTS){
	$routeProvider
		.when("/error", {
			templateUrl: ERROR_CONSTANTS.ERROR_PAGE,
			controller: "ErrorHandler"
		});

//	TODO: add redirect to error page
	$provide.factory('ErrorInterceptor', function ($q) {
        return {
            responseError: function(rejection) {
//                alert(JSON.stringify(rejection));
                console.log(JSON.stringify(rejection));//TODO: remove comment
                return $q.reject(rejection);
            }
        };
    });
	
    $httpProvider.interceptors.push('ErrorInterceptor');
}])

//TODO:maybe unnecessary...
.controller("ErrorHandler", ["$scope", "transportShareErrorData", 
                           function($scope, transportShareErrorDataService){
	$scope.bundle = {
			error: transportShareErrorDataService.getErrorBundle()
	} 
}])

