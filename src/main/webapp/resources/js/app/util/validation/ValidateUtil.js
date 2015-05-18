/**
 * @author Eugene Putsykovich(slesh) May 18, 2015
 *
 *	util for validate date
 */
angular.module("flowertyApplication.utilModule")

.constant("VALIDATE_DATE", (function(){
	var format = "YYYY-MM-DD";
	return{
		validate : function(dateString, isCheckOnPast){
			var now = moment().format(format);
			var dateToCheck = moment(dateString).format(format);

			return isCheckOnPast ? moment(dateToCheck).isBefore(now) : moment(dateToCheck).isAfter(now);
		},
		correctFormat : function correctForma(dateString){
			if(/^\d+$/.test(dateString)){
				// need division on 1000 to convert to seconds
				dateString = moment.unix(parseInt(dateString, 10) / 1000).format(format);
			};

			return dateString;
		}
	};
})())