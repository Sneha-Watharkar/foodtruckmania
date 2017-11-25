mainApp.controller('loginController', function($scope, $location, $rootScope, $window, UserService) {
        // create a message to display in our view
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        var authenticatedUser = null;
        
        $scope.loginUser = function(username,password) {
        	authenticatedUser = UserService.checkForUserExistence($scope.username);
        	if(authenticatedUser) {
        		if(authenticatedUser.password == password){
        			console.log("Login Success");
        			$scope.loadHomePage();
        		} else {
        			console.log("Password might be incorrect!");
        		}
        	} else {
        		console.log("Credentials are wrong.Please enter correct details");
        	}
        };
        
        $scope.loadHomePage = function() {
        	console.log("Load Home page",$scope.rootUrl+'home');
        	$window.location.assign($scope.rootUrl+'home');
        };  
});