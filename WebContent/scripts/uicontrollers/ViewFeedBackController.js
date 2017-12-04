mainApp.controller('viewFeedBackController', function($scope, $location, $rootScope, $window, UserService, FoodTruckService) {
        // create a message to display in our view
		$scope.currentPage = 'Feedbacks';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		
		$scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
});