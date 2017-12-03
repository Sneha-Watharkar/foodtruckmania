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
        
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
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