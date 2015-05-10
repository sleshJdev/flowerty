'use strict';
/**
 * Created by Катерина on 10.05.2015.
 *
 * Service for paging
 */

angular.module("flowertyApplication.orderModule").service('orderPaginationService', ['$location',
    function($location) {

        var service = this;

        var orders = {
            pages: [],
            pagesCount: 1,
            currentPage: 1,
            ordersList: [],
            limit: 10
        };

        service.setLimit = function(limit){
            orders.limit = limit;
        };

        service.getOrdersListBundle = function () {
            return orders;
        };

        var pageClass = function (pageNumber) {
            return pageNumber == orders.currentPage ? 'active' : '';
        };

        var getPage = function (pageNumber) {
            orders.currentPage = pageNumber;

            //TODO: add limit
            service.getPageFromServer(
                pageNumber,
                orders.limit,
                function (data) {
                    if (!data.content) {
                        $location.path("login");
                    } else {
                        orders.ordersList = data.content;
                        orders.pagesCount = data.totalPages;
                    }
                },
                //TODO: error processing
                function (data) {
                }
            );
        };

        service.getPageFromServer = function () {};

        var getPreviousPage = function () {
            if (orders.currentPage !== 1) {
                orders.currentPage--;
            }
            getPage(orders.currentPage);
        };

        var getNextPage = function () {
            if (orders.currentPage !== orders.pagesCount) {
                orders.currentPage++;
            }
            getPage(orders.currentPage);
        };

        var getPagesCount = function () {
            return orders.pagesCount;
        };

        service.getPagination = function (getPageFromServerFunction) {
            service.getPageFromServer = getPageFromServerFunction;
            return {
                getNextPage: getNextPage,
                getPreviousPage: getPreviousPage,
                getPage: getPage,
                pageClass: pageClass,
                getPagesCount: getPagesCount
            }
        };
    }]);