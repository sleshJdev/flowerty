/**
 * Created by Rostislav on 17-May-15
 */

angular.module("flowertyApplication.userModule").service("stateSaverUserService", function() {

    var self = this;

    self.state = {
        checkeds: [],
        reset: function () {
            self.state.checkeds = [];
        },
        checked: function (entityWithId) {
            self.state.checkeds.push(entityWithId);
        },
        unchecked: function (entityWithId) {
            self.state.checkeds.splice(findById(entityWithId.id).index, 1);
        },
        ischecked: function (entityWithId) {
            return findById(entityWithId.id).index !== -1;
        },
        isempty: function () {
            return self.state.checkeds.length === 0;
        }
    };

    function findById(id) {
        for (var i = 0; i < self.state.checkeds.length; ++i) {
            if (self.state.checkeds[i].id === id) {
                return {
                    index: i,
                    value: self.state.checkeds[i]
                };
            }
        }

        return {
            index: -1,
            value: {}
        }
    }
});