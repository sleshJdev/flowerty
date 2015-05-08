/**
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * 	save selected contacts on page
 */
angular.module("flowertyApplication.contactModule").service("stateSaverService", function(){
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
	};
});