'use strict';

/*
 * provides a method for crete/edit/remove contact and phones
 */
angular.module("flowertyApplication.contactModule").service("processContactService", ["$http", "$location", "deleteService", "CONSTANTS",
    function($http, $location, deleteService, CONSTANTS) {
        var me = this;

        me.bundle = {
            phoneTemplate: CONSTANTS.EDIT_PHONE,
            phoneListTemplate: CONSTANTS.PHONES,
            datePickerTemplate: CONSTANTS.DATE_PICKER,
            types: CONSTANTS.PHONE_TYPES,
            contact: {},
            actions: []
        };

        /*
         * save/update contact after editing/creating
         */
        me.bundle.actions.saveContact = function(contact){
            $http({
                method: "post",
                url: "contact/save",
                data: contact
            }).success(function(data, status, headers, config) {
                console.log("save contact success!")
                $location.path("contacts");
            }).error(function(data, status, headers, config) {
                console.log("save contact error: " + JSON.stringify(data))//REMOVE_COMMENT
            });
        }

        /*
         * delete phone. remove all phone at which id < 0
         */
        me.bundle.actions.deletePhone = function(){
            console.log("delete phone");
            deleteService.deleteById(me.bundle.contact.phones);
        };

        /*
         * add new phone
         */
        me.bundle.actions.addPhone = function(){
            console.log("show pop-up to create new phone");
            me.bundle.phone = {};
            me.bundle.processType.titlePhone = "Add phone";
        };

        /*
         * edit phone. Show pop-up to editing specific phone.
         */
        me.bundle.actions.editPhone = function(editablePhone){
            console.log("edit phone");
            me.bundle.phone = angular.copy(editablePhone);
            me.bundle.originPhone = editablePhone;
            me.bundle.processType.titlePhone = "Edit phone";
        };

        /*
         * save/update phone after creating/editing
         */
        me.bundle.actions.savePhone = function(newPhone){
            if(!newPhone.id){
                me.bundle.contact.phones.push(newPhone)
                console.log("add new phone");
            }else{
                angular.copy(newPhone, me.bundle.originPhone);
                console.log("update phone");
            }
        };
    }]);