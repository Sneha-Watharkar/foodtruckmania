mainApp.controller('loginController', function($scope, $location, $rootScope, $window, UserService) {
        // create a message to display in our view
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        
        $scope.loginUser = function(username,password) {
        	var user = {
        			name:$scope.username,
        			password:$scope.password
        	};
        	UserService.checkForUserExistence(user).then(function(results){
        		console.log("Login Success", results);
        	     if(!results.data.error) {
        	    	 $scope.currentUser = JSON.parse(results.data.user);
        	    	 console.log("Current user is ",$scope.currentUser);
        	    	 UserService.setCurrentUser($scope.currentUser);
        	    	 if($scope.currentUser.userType == 'customer') {
        	    		 $scope.loadHomePage();
        	    	 } else if ($scope.currentUser.userType == 'vendor') {
        	    		 $scope.loadReserveLocationPage();
        	    	 } else if($scope.currentUser.userType == 'admin') {
        	    		 $scope.loadAdminPage();
        	    	 }
        	     } else {
        	    	 $scope.displayNotification('show', results.data.msg + "! " + results.data.error, 'danger');
        	     }
        	  },function(err){
        		  console.log("Errorblock");
        	  });
        };
        
        $scope.loadHomePage = function() {
        	console.log("Load Home page",$scope.rootUrl+'home');
        	$window.location.assign($scope.rootUrl+'home');
        };
        
        $scope.loadReserveLocationPage = function() {
        	$window.location.assign($scope.rootUrl+'reserveLocation');
        }
        
        $scope.loadAdminPage = function() {
        	$window.location.assign($scope.rootUrl+'manageTrucks');
        }
        
        $scope.displayNotification = function(status, message, type) {
        	$scope.notification = {};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
});