mainApp.controller('viewFeedBackController', function($scope, $location, $rootScope, $window, UserService, FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Feedbacks';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		
		
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
		
		$scope.currentUser = UserService.getCurrentUser();
		
		FoodTruckService.getFeedBacks().then(function(res) {
			console.log("Res of Feedbacks",res);
			if(res.data.ratingLists) {
				var ratingList = res.data.ratingLists;
				if(JSON.parse(ratingList).length){
					$scope.userRatings = JSON.parse(ratingList);
				} else {
					$scope.errorMsg = "No ratings yet!"
				}
			}
		}, function (){
			
		});
});