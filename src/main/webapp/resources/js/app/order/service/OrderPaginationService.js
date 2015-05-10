'use strict';
/**
 * Created by Катерина on 10.05.2015.
 *
 * Service for paging
 */

angular.module("flowertyApplication.orderModule").service('orderPaginationService', ['$location', 'MAIN_MODULE_CONSTANTS',
    function($location, MAIN_MODULE_CONSTANTS) {

        var service = this;

        var orders = {
            pages: [],
            pagesCount: 1,
            currentPage: 1,
            ordersList: [],
            limit: MAIN_MODULE_CONSTANTS.LIMITS[1]
        };

        var changeLimit = function(limit){
            orders.limit = limit;
            getPage(orders.currentPage);
        };

        service.getOrdersListBundle = function () {
            return orders;
        };

        var pageClass = function (pageNumber) {
            return pageNumber == orders.currentPage ? 'active' : '';
        };

        var getPage = function (pageNumber) {
            orders.currentPage = pageNumber;
            //TODO: ????
            if(!orders.limit){
                orders.limit = MAIN_MODULE_CONSTANTS.LIMITS[1];
            }

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
                getPagesCount: getPagesCount,
                changeLimit: changeLimit,
                limit: orders.limit,
                limits: MAIN_MODULE_CONSTANTS.LIMITS
            }
        };
    }]);