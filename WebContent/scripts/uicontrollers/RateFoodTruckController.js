mainApp.controller('rateFoodTruckController', function($scope, $location, $rootScope, $window, UserService, FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Rate Food Truck';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		$scope.userRating = 0;
		$scope.currentRating = 0;
		$scope.currentUser = UserService.getCurrentUser();
		FoodTruckService.getAllFoodtrucks().then(function(res){
        	console.log("All food trucks in res loc",  res);
        	var truckString = res.data.trucks;
        	$scope.allFoodTrucks = JSON.parse(truckString);
        }, function(err){
        	
        });
		
		$scope.getUserRating = function (rating) {
			console.log("Rating ccaptured",rating);
			$scope.currentRating = rating;
		}
		
		$scope.addFoodTruckRating = function () {
			console.log(JSON.parse($scope.selectedTruckName));
			if($scope.selectedTruckName) {
				var ratingObject = {
					rating: $scope.currentRating,
					comments: $scope.truckComments,
					truck: JSON.parse($scope.selectedTruckName)
				};
				
				console.log("Rating object is", ratingObject);
				
				FoodTruckService.rateFoodTruck(ratingObject).then(function(res){
					console.log("Result of rating", res);
					$scope.displayNotification('show', res.data.msg, 'success');	
				},function(err){
					console.log("Error",err);
				});
				
			}
		};
		
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
		
		$scope.displayNotification = function(status, message, type) {
        	$scope.notification = {};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
});