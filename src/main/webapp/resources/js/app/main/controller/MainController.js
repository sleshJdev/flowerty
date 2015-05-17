'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module('flowertyApplication').controller('MainController',
    function ($scope, $http, $location, $filter, AuthServerProvider, $localStorage, notificationService, xlatService) {

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
            offerContacts : function (model) {

                //  start spinner
                model.loading = true;
                $filter('bySurname')([], model, $scope.dynamicSearch.filterCallback,  $scope.dynamicSearch.errorCallback);
            },
            filterCallback : function (model, data) {
                model.offeredContacts = data.content ? data.content : data;
                model.selected = model.offeredContacts[0];
                model.show = $scope.dynamicSearch.showResults(model);
                //  stop spinner
                model.loading = false;
            },
            showResults : function (model) {
                return model.offeredContacts && model.offeredContacts.length > 0;
            },
            selectContact : function (model) {
                //  Setting empty array hides select element
                model.offeredContacts = [];
                model.enteredSurname = model.selected.name + ' ' + model.selected.fathername + ' ' + model.selected.surname;
                model.show = false;
                //  stop spinner
                //  на всякий случай!
                model.loading = false;
            }
        };

        $scope.setCurrentLanguage = function(language) {
            alert(language);
            xlatService.setCurrentLanguage(language);
            $localStorage.language = language;
            $location.reload();
        };

        $scope.notification = notificationService.getNotificationBundle();

        $scope.localization = {
            language : 'en'
        };

        if ($localStorage.language) {
            $scope.localization.language = $localStorage.language;
            alert('localization.language');
            alert($scope.localization.language);
            xlatService.setCurrentLanguage($localStorage.language);
        }

    });
