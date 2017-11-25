mainApp.controller('signUpController', function($scope, $location, $http, UserService) {
        // create a message to display in our view
	$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		console.log("Root url",$scope.rootUrl);
		
		$scope.goBacktoLoginPage = function() {
			$location.path($scope.rootUrl);
		}
		
		$scope.registerNewUser = function() {
			if(!($scope.password==$scope.confirmpassword)){
				console.log("Passwords must match!");
				return;
			}
			$scope.userDetails = {
					firstname: $scope.firstname,
					lastname: $scope.lastname,
					email:$scope.useremail,
					password:$scope.password,
					phone:$scope.userPhone,
					type: $scope.userType		
			};
			
			var res = $http.post('registerUser', $scope.userDetails);
			res.success(function(data, status, headers, config) {
				console.log("Success");
				$scope.message = data;
			});
			res.error(function(data, status, headers, config) {
				console.log("Failure "+ JSON.stringify({data: data}));
				/*alert( "failure message: " + JSON.stringify({data: data}));*/
			});	
			
			console.log("User details ",$scope.userDetails);
		}
    });