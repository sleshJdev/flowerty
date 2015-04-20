'use strict';

/*
 * remove item from collection, if his id < 0
 */
angular.module("flowertyApplication.contactModule").service("deleteService", function(){
    this.deleteById = function(collection){
        var isBreak = true;
        do{
            isBreak = true;
            for(var i = 0; i < collection.length; ++i){
                if(collection[i].id < 0){
                    collection.splice(i, 1);
                    isBreak = false;
                }
            }
        }while(!isBreak);
    }
})
