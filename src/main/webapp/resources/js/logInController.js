
	app.controller('logInController', function($scope, $http) {
		
		$scope.login = '';
		$scope.password = '';
		$scope.isLogged = false;
		
		$scope.logIn = function() {	
			
			var logged = {
					'login' : $scope.login,
					'password' : $scope.password						
			};			
			$scope.isLogged = true;

            var request = $http({
                method: "post",
                url: "/",
                data: {
                    loggedInUser: logged
                }
            });
            request.success(function(data, status, headers, config) {
				alert( "User logged in: " + JSON.stringify(logged));
			});
            request.error(function(data, status, headers, config) {
				alert( "Exception details: " + JSON.stringify({data: data}));
			});			
		};
	});