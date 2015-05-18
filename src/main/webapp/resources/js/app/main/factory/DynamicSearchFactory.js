'use strict';
/**
 * Created by Катерина on 17.05.2015.
 *
 * Factory that provides dynamic search for partial searching by surname
 */

angular.module("flowertyApplication").factory('dynamicSearchFactory', ['$filter',
    function($filter){

        var dynamicSearch = {
            /**
            *
            * @param model - entity you want to search dynamically
            * has the next format: {
            *                          selected : {},      //  contact that you choose
            *                          show : false,       // show or not results
            *                          enteredSurname : '' // string for filtering bySurname
            *                      }
            */
            offerContacts: function (model) {

                //  start spinner
                model.loading = true;
                $filter('bySurname')([], model, dynamicSearch.filterCallback);
            },
            filterCallback: function (model, data) {
                model.offeredContacts = data.content ? data.content : data;
                model.selected = model.offeredContacts[0];
                model.show = dynamicSearch.showResults(model);
                //  stop spinner
                model.loading = false;
            },
            showResults: function (model) {
                return model.offeredContacts && model.offeredContacts.length > 0;
            },
            selectContact: function (model) {
                //  Setting empty array hides select element
                model.offeredContacts = [];
                model.enteredSurname = model.selected.name + ' ' + model.selected.fathername + ' ' + model.selected.surname;
                model.show = false;
                //  stop spinner
                //  на всякий случай!
                model.loading = false;
            }
        };

        return {
            dynamicSearch: dynamicSearch
        }
    }]);