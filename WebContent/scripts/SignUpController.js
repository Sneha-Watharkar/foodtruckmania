mainApp.controller('signUpController', function($scope, $location) {
        // create a message to display in our view
		$scope.rootUrl = $location.$$absUrl;
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
			
			console.log("User details ",$scope.userDetails);
		}
    });