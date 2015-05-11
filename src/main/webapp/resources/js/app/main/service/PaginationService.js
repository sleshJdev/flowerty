'use strict';
/**
 * Created by Катерина on 10.05.2015.
 *
 * Service for paging
 */

angular.module("flowertyApplication.orderModule").service('paginationService', ['$location', 'MAIN_MODULE_CONSTANTS',
    function($location, MAIN_MODULE_CONSTANTS) {

        var service = this;

        var listBundle = {
            pages: [],
            pagesCount: 1,
            currentPage: 1,
            list: [],
            limit: MAIN_MODULE_CONSTANTS.LIMITS[1]
        };

        var changeLimit = function(limit){
            listBundle.limit = limit;
            getPage(listBundle.currentPage);
        };

        service.getListBundle = function () {
            return listBundle;
        };

        var pageClass = function (pageNumber) {
            return pageNumber == listBundle.currentPage ? 'active' : '';
        };

        var getPage = function (pageNumber) {
            listBundle.currentPage = pageNumber;

            if(!listBundle.limit){
                listBundle.limit = MAIN_MODULE_CONSTANTS.LIMITS[1];
            }

            service.getPageFromServer(
                pageNumber,
                listBundle.limit,
                function (data) {
                    if (!data.content) {
                        $location.path("login");
                    } else {
                        listBundle.list = data.content;
                        listBundle.pagesCount = data.totalPages;
                    }
                },
                //TODO: error processing
                function (data) {
                }
            );
        };

        service.getPageFromServer = function () {};

        var getPreviousPage = function () {
            if (listBundle.currentPage !== 1) {
                listBundle.currentPage--;
            }
            getPage(listBundle.currentPage);
        };

        var getNextPage = function () {
            if (listBundle.currentPage !== listBundle.pagesCount) {
                listBundle.currentPage++;
            }
            getPage(listBundle.currentPage);
        };

        var getPagesCount = function () {
            return listBundle.pagesCount;
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
                limit: listBundle.limit,
                limits: MAIN_MODULE_CONSTANTS.LIMITS
            }
        };
    }]);