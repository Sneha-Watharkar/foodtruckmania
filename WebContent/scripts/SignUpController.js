mainApp.controller('signUpController', function($scope, $location) {
        // create a message to display in our view
		$scope.rootUrl = $location.$$absUrl;
		console.log("Root url",$scope.rootUrl);
		
		$scope.goBacktoLoginPage = function() {
			$location.path($scope.rootUrl);
		}
    });