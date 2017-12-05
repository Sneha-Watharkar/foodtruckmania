mainApp.controller('reserveLocationController', function($scope, $location, $rootScope, $window, UserService,
		FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Reserve Location';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		$scope.currentUser = UserService.getCurrentUser();
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
		 $scope.currentUser = UserService.getCurrentUser();
	        console.log("In Reserve Location", $scope.currentUser);
		$scope.reserveLocation = function() {
			var locationObject = {
					location: $scope.selectedLocation,
					timeSlot:$scope.timeSlot,
					days:$scope.selectedDays,
					userId: $scope.currentUser.userId
			};
			console.log("Location", locationObject);
			
			FoodTruckService.reserveLocation(locationObject).then(function(res){
				console.log("Reserve Loc",res);
				$scope.displayNotification('show',res.data.msg,'info');
			},function(err){
				
			});
		};
		
		$scope.displayNotification = function(status, message, type) {
        	$scope.notification = {
					/*status: 'hide',
					type: '',
					message: ''*/
			};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
        
        $scope.sendMail = function() {
        	FoodTruckService.sendMail().then(function(res){
        		console.log("Res of email",res);
        	},function(err){
        		
        	});
        }
});