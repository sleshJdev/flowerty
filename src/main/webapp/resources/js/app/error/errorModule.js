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
.config(["$routeProvider", "$provide", "$httpProvider", "ERROR_CONSTANTS",
         function($routeProvider, $provide, $httpProvider, ERROR_CONSTANTS){
	$routeProvider
		.when("/error", {
			templateUrl: ERROR_CONSTANTS.ERROR_PAGE,
			controller: "ErrorHandler"
		});

//	TODO: add redirect to error page
	$provide.factory('ErrorInterceptor', function ($q, errorDataTransportService, $location) {
        return {
            responseError: function(rejection) {

				if (401 !== rejection.status) {
					errorDataTransportService.setErrorBundle(rejection);
					$location.path("error");
				}

				$routeProvider.
                console.log(JSON.stringify(rejection));//TODO: remove comment
                return $q.reject(rejection);
            }
        };
    });

    $httpProvider.interceptors.push('ErrorInterceptor');
}]);