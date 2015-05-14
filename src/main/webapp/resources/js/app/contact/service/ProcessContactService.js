'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * provides a method for create/edit/remove contact and phones
 */
angular.module("flowertyApplication.contactModule")

.service("processContactService", ["$http", "$location", "deleteService", "stateSaverService", "CONSTANTS", "notificationService",
        function($http, $location, deleteService, stateSaverService, CONSTANTS, notificationService) {
            var self = this;

            self.bundle = {
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
            self.bundle.actions.saveContact = function (contact) {
                for (var i = 0; i < contact.phones.length; ++i) {
                    if (!!contact.phones[i].id) {
                        delete contact.phones[i].id;
                    }
                }

                $http({
                    method: "post",
                    url: "contact/save",
                    data: contact
                }).success(function (data) {
                    console.log("save contact success!");
                    notificationService.notify("success", "A new contact created successfully!");
                    $location.path("contact-list");
                }).error(function (data) {
                    console.log("save contact error: " + JSON.stringify(data));
                    notificationService.notify("danger", "Error occured during saving the contact!");
                });
            };

            /*
             * delete phone. remove all phone at which id < 0
             */
            self.bundle.actions.deletePhone = function () {
                console.log("delete phone");
                if (stateSaverService.state.isempty()) {
                    notificationService.notify("danger", "Please, select phones to be deleted!");
                } else {
                    deleteService.deleteIsChecked(stateSaverService.state.ischecked, self.bundle.contact.phones);
                    stateSaverService.state.reset();
                }
            };

            /*
             * add new phone
             */
            self.bundle.actions.addPhone = function () {
                console.log("show pop-up to create new phone");
                self.bundle.phone = {};
                self.bundle.processType.titlePhone = "Add phone";
            };

            /*
             * edit phone. Show pop-up to editing specific phone.
             */
            self.bundle.actions.editPhone = function (editablePhone) {
                console.log("edit phone");
                self.bundle.phone = angular.copy(editablePhone);
                self.bundle.originPhone = editablePhone;
                self.bundle.processType.titlePhone = "Edit phone";
            };

            /*
             * save/update phone after creating/editing
             */
            self.bundle.actions.savePhone = function (newPhone) {
                if (!newPhone.id) {
                    newPhone.id = -(self.bundle.contact.phones.length + 1);//unique in contact scope
                    self.bundle.contact.phones.push(newPhone);
                    console.log("add new phone");
                } else {
                    angular.copy(newPhone, self.bundle.originPhone);
                    console.log("update phone");
                }
            };

            self.bundle.actions.getContact = function (id, successCallback, errorCallback) {
                $http({
                    method: "get",
                    url: "contact/details/" + id
                })
                    .success(successCallback)
                    .error(function (data) {
                        console.log("error contact details: " + JSON.stringify(data));
                        errorCallback(data);
                    });
            };
        }]);
