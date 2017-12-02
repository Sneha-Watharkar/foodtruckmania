mainApp.controller('rateFoodTruckController', function($scope, $location, $rootScope, $window, UserService, FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Rate Food Truck';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		$scope.userRating = 0;
		$scope.currentRating = 0;
		$scope.allFoodTrucks = [{
        	truckId: 1,
        	name: "Asian Eatery"
        },{
        	truckId: 2,
        	name: "Maki of Japan"
        },
        {
        	truckId: 3,
        	name: "Dominos"
        },
        {
        	truckId: 4,
        	name: "Chinese Gourmet"
        },
        {
        	truckId: 5,
        	name: "Subz"
        }];
		
		$scope.getUserRating = function (rating) {
			console.log("Rating ccaptured",rating);
			$scope.currentRating = rating;
		}
		
		$scope.addFoodTruckRating = function () {
			if($scope.selectedTruckName) {
				var ratingObject = {
					truckName: $scope.selectedTruckName,
					rating: $scope.currentRating,
					comments: $scope.truckComments
				};
				
				console.log("Rating object is", ratingObject);
				
				var result = FoodTruckService.rateFoodTruck(ratingObject);
				console.log("Result of rating", result);
				
			}
		};
		
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
});