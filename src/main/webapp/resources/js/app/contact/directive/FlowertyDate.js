'use strict';

angular.module("flowertyApplication.contactModule").directive('flowertyDate', function ($filter) {
    return {
        restrict: "A",
        require: "?ngModel",
        link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
                format : attrs.format.length > 4 ? attrs.format : (" " + attrs.format + " "), //extra space for fetch only year, month or day
                viewMode : !attrs.viewMode ? "days" : attrs.viewMode,
                minViewMode : !attrs.minViewMode ? "days" : attrs.minViewMode
            }).on('changeDate', function( e ){
                switch(attrs.format.toLowerCase().trim()){
                    case "yyyy":
                        ngModelCtrl.$setViewValue(e.date.getFullYear());
                        break;
                    case "mm":
                        ngModelCtrl.$setViewValue(e.date.getMonth() + 1);
                        break;
                    case "dd":
                        ngModelCtrl.$setViewValue(e.date.getDay() + 1);
                        break;
                    default:
                        ngModelCtrl.$setViewValue($filter('date')(e.date,'yyyy-MM-dd'));
                        break;
                }
                element.datepicker('hide');
            });

            //TODO: wtf?? this code thrown exception: data[option] is not a function
//            scope.$on('$destroy', function () {
//                element.datepicker('destroy');
//            });
        }
    };
});