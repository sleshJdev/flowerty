'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module('flowertyApplication').controller('ViewController', ['$scope', function ($scope) {
    $scope.templates =
        [
            {
                name: 'header.html',
                url: 'resources/template/header.html'
            },
            {
                name: 'footer.html',
                url: 'resources/template/footer.html'
            },
            {
                name: 'pagination.html',
                url: 'resources/template/pagination.html'
            }
        ];
    $scope.templates.header = $scope.templates[0];
    $scope.templates.footer = $scope.templates[1];
    $scope.templates.pagination = $scope.templates[2];
}]);