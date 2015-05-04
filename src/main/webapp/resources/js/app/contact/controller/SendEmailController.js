'use strict';

angular.module("flowertyApplication.contactModule").controller("SendEmailController", ["$scope", "$http", "$location", "transportService",
                                    function($scope, $http, $location, transportService) {
	$scope.bundle = {
			actions: [],
			email:{
				to: [],
				subject: "you subject",
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
	
	$http({
		method: "get",
		url: "email/templates",
	}).success(function(data, status, headers, config) {
		$scope.bundle.templates = data;
		$scope.bundle.template = $scope.bundle.templates[0];
	}).error(function(data, status, headers, config) {
		console.log("error occured during fetch email templates: " + JSON.stringify(data));//LOG
	});
	
	$scope.bundle.email.to = transportService.getValue();//for test: studentbntu@mail.ru is valid
	
	$scope.$on("fileSelected", function (event, args) {
		$scope.$apply(function () {            
			$scope.bundle.files.push(args.file);
			console.log("add attchment: " + args.file.name + ", current quantity: " + $scope.bundle.files.length);
		});
	});

	// TODO: add service for this logic
	$scope.bundle.actions.send = function(){
		$scope.bundle.email.text = $scope.bundle.template.value;

		var formData = new FormData();
		formData.append("email", angular.toJson($scope.bundle.email));
		formData.append("template", angular.toJson($scope.bundle.template));
		for (var i = 0; i < $scope.bundle.files.length; i++) {
			formData.append("file", $scope.bundle.files[i]);
		}

		$http.post("email/send", formData, {
			headers: {'Content-Type': undefined },
			transformRequest: angular.identity
		}).success(function (data, status, headers, config) {
			console.log("send email success!");
			$scope.notification.type = "success";
			$scope.notification.message = "Send email success!";
			$scope.notification.status = "show";
		}).error(function (data, status, headers, config) {
			alert("send email failed!");
			$scope.notification.type = "danger";
			$scope.notification.message = "Send email failed!";
			$scope.notification.status = "show";
		});
		
		reset();
	};
	
	$scope.bundle.actions.removeAttachment = function(number){
		$scope.bundle.files.splice(number, 1);
		console.log("remove attachment with number: " + number + ", current quantity: " + $scope.bundle.files.length);
	};
	
	//TODO: remove in future
	$scope.bundle.actions.removeEmail = function(number){
		$scope.bundle.email.to.splice(number, 1);
		console.log("remove email to send with number: " + number + ", current quantity: " + $scope.bundle.email.to.length);
	};
	
	//TODO: remove in future
	$scope.bundle.actions.addNewEmail = function(event){
		if(event.which === 13) {//code of enter button
			$scope.bundle.email.to.push($scope.bundle.newEmail);
			$scope.bundle.newEmail = "";
			console.log("add new email: " + $scope.bundle.newEmail + ", current quantity: " + $scope.bundle.email.to.length);
		}
	};
	
	function reset(){
		$scope.bundle.email.subject = "";
		$scope.bundle.template = {};
		$scope.bundle.files = [];
	}
}])