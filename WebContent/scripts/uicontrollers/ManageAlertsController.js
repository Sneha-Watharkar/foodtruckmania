mainApp.controller('manageAlertsController', function($scope, $location, $rootScope, $window, UserService) {
        // create a message to display in our view
		$scope.currentPage = 'Manage Alerts';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        
        $scope.updateUserAlerts = function (smsAlert, emailAlert) {
        	$scope.updatingAlertParam = {
        			sms:smsAlert,
        			email:emailAlert
        	};
        	
        	console.log("Alerts",$scope.updatingAlertParam);
        	
        	//var res = UserService.updateUserAlerts($scope.updatingAlertParam);
        }
});