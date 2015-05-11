'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	send email for selected contact
 */
angular.module("flowertyApplication.contactModule")

.controller("SendEmailController", ["$scope", "$http", "$location", "emailService",
                                    function($scope, $http, $location, emailService) {
	$scope.bundle = {
			actions: [],
			email:{
				to: [],
				subject: "",
				text: ""
			},
			templates: [],
			template: {},
			files: []
	};
	
	$scope.notification = {
        status: "hide",
        message: "",
        type: ""
    };
	
	emailService.getTemplates(function(data) {
		$scope.bundle.templates = data;
		$scope.bundle.template = data[0];
	});
	$scope.bundle.email.to = emailService.getValue();//for test: studentbntu@mail.ru is valid
	
	$scope.$on("fileSelected", function (event, args) {
		$scope.$apply(function () {            
			$scope.bundle.files.push(args.file);
			console.log("add attchment: " + args.file.name + ", current quantity: " + $scope.bundle.files.length);
		});
	});

	$scope.bundle.actions.send = function(){
		$scope.bundle.email.text = $scope.bundle.template.value;
		emailService.send(
				$scope.bundle.email, 
				$scope.bundle.files, 
				$scope.bundle.template,
				function(data){
					console.log("send email success!");
					$scope.notification.type = "success";
					$scope.notification.message = "Send email success!";
					$scope.notification.status = "show";
					reset();
				},
				function(data){
					console.log("send email failed!");
					$scope.notification.type = "danger";
					$scope.notification.message = "Send email failed!";
					$scope.notification.status = "show";
				});
	}
	
	$scope.bundle.actions.removeAttachment = function(number){
		$scope.bundle.files.splice(number, 1);
		console.log("remove attachment with number: " + number + ", current quantity: " + $scope.bundle.files.length);
	};
	
	function reset(){
		$scope.bundle.email.subject = "";
		$scope.bundle.template = {};
		$scope.bundle.files = [];
	}
}]);