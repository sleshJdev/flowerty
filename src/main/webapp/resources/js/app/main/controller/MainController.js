'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module('flowertyApplication').controller('MainController', function ($scope, $http, $location, $filter, sessionService) {

    sessionService.setLoggedUser($scope);

    $scope.current = {
        isLogged: false,
        user: {},
        errorLogin: false,
        errorMessage : ""
    };

    $scope.current.basket = {
        info : {
            itemsCount : 0,
            fullCost: 0
        },
        goods : []
    };

    $scope.current.logOut = function () {
        sessionService.logout($scope, $location);
    };

    $scope.pagination = {
        getPagesCount : function(){},
        pageClass : function(page){},
        getPage : function(page){},
        getPreviousPage : function(){},
        getNextPage : function(){}
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
        offerContacts : function(model) {
            $scope.dynamicSearch.offeredContacts = $filter('bySurname')([], model.enteredSurname);
            model.selected = $scope.dynamicSearch.offeredContacts[0];
            model.show = $scope.dynamicSearch.showResults();
        },
        showResults : function(){
            return $scope.dynamicSearch.offeredContacts && $scope.dynamicSearch.offeredContacts.length > 0;
        },
        selectContact : function(model){
            //  Setting empty array hides select element
            $scope.dynamicSearch.offeredContacts = [];
            model.enteredSurname = model.selected.name + ' ' + model.selected.fathername + ' ' + model.selected.surname;
            model.show = false;
        }
    };
});
