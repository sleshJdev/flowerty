/**
 * @author Eugene Putsykovich(slesh) Apr 13, 2015
 * 
 * global error handler
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
	.service('transport', function() {
		this.f = function(){
			alert("1");
		}
	})
.config(["$routeProvider", "$provide", "$httpProvider", "ERROR_CONSTANTS",
         function($routeProvider, $provide, $httpProvider, ERROR_CONSTANTS){
	$routeProvider
		.when("/error", {
			templateUrl: ERROR_CONSTANTS.ERROR_PAGE,
			controller: "ErrorHandler"
		});

//	TODO: add redirect to error page
	$provide.factory('ErrorInterceptor', function ($q, transport, $location) {
        return {
            responseError: function(rejection) {
				//transport.f();
				//$location.path("error");
				$routeProvider.
                console.log(JSON.stringify(rejection));//TODO: remove comment
                return $q.reject(rejection);
            }
        };
    });

    $httpProvider.interceptors.push('ErrorInterceptor');
}]);