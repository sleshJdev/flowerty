'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module('flowertyApplication').controller('MainController',
    function ($scope, $http, $location, $filter, AuthServerProvider, $localStorage, notificationService) {

        AuthServerProvider.setLoggedUser($scope);

        $scope.current = {
            isLogged: false,
            user: {},
            errorLogin: false
        };

        $scope.current.resetBasket = function () {
            $scope.current.basket.info.itemsCount = 0;
            $scope.current.basket.info.fullCost = 0;
            $scope.current.basket.items = {};
            delete $localStorage.cart;
        };

        $scope.current.basket = {
            info: {
                itemsCount: 0,
                fullCost: 0
            },
            items: {}
        };

        $scope.current.logOut = function () {
            AuthServerProvider.logout($scope, $location);
            $scope.current.resetBasket();
        };

        $scope.pagination = {
            getPagesCount: function () {
            },
            pageClass: function (page) {
            },
            getPage: function (page) {
            },
            getPreviousPage: function () {
            },
            getNextPage: function () {
            },
            changeLimit: function (limit) {
            },
            limit: 10,
            limits: [],
            canChangeLimit : true
        };

        $scope.dynamicSearch = {
            /**
            *
            * @param model - entity you want to search dynamically
            * has the next format: {
            *                          selected : {},      //  contact that you choose
            *                          show : false,       // show or not results
            *                          enteredSurname : '' // string for filtering bySurname
            *                      }
            */
            offerContacts: function (model) {
                $filter('bySurname')([], model, $scope.dynamicSearch.filterCallback);
            },
            filterCallback: function (model, data) {
                $scope.dynamicSearch.offeredContacts = data.content;
                model.selected = $scope.dynamicSearch.offeredContacts[0];
                model.show = $scope.dynamicSearch.showResults();
            },
            showResults: function () {
                return $scope.dynamicSearch.offeredContacts && $scope.dynamicSearch.offeredContacts.length > 0;
            },
            selectContact: function (model) {
                //  Setting empty array hides select element
                $scope.dynamicSearch.offeredContacts = [];
                model.enteredSurname = model.selected.name + ' ' + model.selected.fathername + ' ' + model.selected.surname;
                model.show = false;
            }
        };

        $scope.notification = notificationService.getNotificationBundle();
    });
