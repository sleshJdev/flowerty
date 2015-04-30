'use strict';

/*
 * contains the list of contacts found by search. if the list is null, than we're not searching but
 * browsing all contacts
 */
angular.module("flowertyApplication.contactModule").service("contactListService", function() {
    var list = null;
    return {
        getList: function(){
            return list;
        },
        setList: function(newList){
            list = newList;
        }
    };
})