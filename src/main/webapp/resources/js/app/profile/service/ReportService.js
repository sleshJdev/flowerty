/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 */
angular.module("flowertyApplication.profileModule")

.service("financialService", ['$http',
                             function ($http) {

        var self = this;

        self.getFinancialReport = function (successCallback, errorCallback) {

            $http({
                method: "get",
                url: "reporst/finances"
            })
                .success(successCallback)
                .error(function (data) {
                    errorCallback(data);
                });
        };
    }]);