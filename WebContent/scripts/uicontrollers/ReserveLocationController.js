mainApp.controller('reserveLocationController', function($scope, $location, $rootScope, $window, UserService,
		FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Reserve Location';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
		
		$scope.reserveLocation = function() {
			var locationObject = {
					location: $scope.selectedLocation,
					timeSlot:$scope.timeSlot,
					days:$scope.selectedDays
			};
			console.log("Location", locationObject);
			
			var result = FoodTruckService.reserveLocation(locationObject);
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
});