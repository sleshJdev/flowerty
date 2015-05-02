'use strict';
/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */


/*
 * TODO: need think about that the do separate file for filters, services and
 * etc, because over-head code obtained
 */

angular.module("flowertyApplication.contactModule", ["ngRoute"])

.constant("CONSTANTS", (function(){
    var CONTACT_MODULE_PATH = "resources/js/app/contact/";

    return {
        CONTACTS 		: CONTACT_MODULE_PATH + "partial/contact-list-form.html",
        EDIT_CONTACT	: CONTACT_MODULE_PATH + "partial/contact-form.html",
        ADD_CONTACT		: CONTACT_MODULE_PATH + "partial/contact-form.html",
        SEARCH_CONTACT	: CONTACT_MODULE_PATH + "partial/contact-form.html",
        EDIT_PHONE		: CONTACT_MODULE_PATH + "partial/phone-form.html",
        PHONES			: CONTACT_MODULE_PATH + "partial/phone-list-form.html",
        SHOW_CONTACTS   : CONTACT_MODULE_PATH + "partial/contact-list-form.html",

        DATA_PICKER_BOX : CONTACT_MODULE_PATH + "partial/date-picker-box.html",
        DATE_PICKER		: CONTACT_MODULE_PATH + "partial/date-picker.html",

        SEND_EMAIL		: CONTACT_MODULE_PATH + "partial/send-email-form.html",

        PHONE_TYPES: [{name: "CELL"}, {name: "HOME"}],

        PROCESS_TYPES : {
            ADD:{
                name: "Add new contact",
                titleContact: "Add Contact",
                titlePhone: "Add phone",//init below
                isShowPhones: true,
                widthClass: "col-md-6",
                action: []//main action
            },
            EDIT:{
                name: "Save contact",
                titleContact: "Edit Contact",
                titlePhone: "Add phone",//init below
                isShowPhones: true,
                widthClass: "col-md-6",
                action: []//main action
            },
            SEARCH:{
                name: "Search contact",
                titleContact: "Search Contact",
                titlePhone: "",//not use
                isShowPhones: false,
                widthClass: "col-md-10",
                action: []//main action
            }
        }
    }
})())

.service("stateSaverService", function(){
	var me = this;
	me.state = {
			checkeds: [],
			reset: function(){
				me.state.checkeds = [];
			},
			checked: function(entityWithId) {
				me.state.checkeds.push(entityWithId);
			},
			unchecked: function(entityWithId) {
				me.state.checkeds.splice(findById(entityWithId.id).index, 1);
			},
			ischecked: function(entityWithId) {
				return findById(entityWithId.id).index !== -1 ? true : false; 
			},
			isempty: function(){
				return me.state.checkeds.length === 0;
			}
	};
	function findById(id){
		for(var i = 0; i < me.state.checkeds.length; ++i){
			if(me.state.checkeds[i].id === id){
				return {
					index: i,
					value: me.state.checkeds[i]
				};
			}
		}
		
		return {
			index: -1,
			value: {}			
		}
	}
})

.config(["$routeProvider", "$locationProvider", "CONSTANTS", function($routeProvider, $locationProvider, CONSTANTS) {
    $routeProvider
        .when("/contact-list", {
        templateUrl: CONSTANTS.CONTACTS,
        controller: "ContactListController"
    })
        .when("/contact-edit/:id", {
            templateUrl: CONSTANTS.EDIT_CONTACT,
            controller: "EditContactController"
        })
        .when("/contact-add", {
            templateUrl: CONSTANTS.ADD_CONTACT,
            controller: "AddContactController"
        })
        .when("/contact-search", {
            templateUrl: CONSTANTS.SEARCH_CONTACT,
            controller: "SearchContactController"
        })
        .when("/show-contacts", {
            templateUrl: CONSTANTS.SHOW_CONTACTS,
            controller: "ShowContactsController"
        })
        .when("/send-email", {
            templateUrl: CONSTANTS.SEND_EMAIL,
            controller: "SendEmailController"
        });
}])

.filter("flowerFullContactName", function() {
	return function(contact){
		return (!contact.name ? "" : contact.name) + " " + 
		(!contact.surname ? "" : contact.surname) + " " + 
		(!contact.fathername ? "" : contact.fathername);
	}
});
