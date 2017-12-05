mainApp.controller('manageAlertsController', function($scope, $location, $rootScope, $window, UserService) {
        // create a message to display in our view
		$scope.currentPage = 'Manage Alerts';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		
		$scope.currentUser = UserService.getCurrentUser();
		UserService.getAlerts().then(function(res){
    		console.log("alert res",res);
    		if(res.data){
    			if(res.data.text=='1'){
    				$scope.smsAlert = true
    			} else {
    				$scope.smsAlert = false
    			}
    			if(res.data.email=='1'){
    				$scope.emailAlert = true
    			} else {
    				$scope.emailAlert = false
    			}
    		}
    		
    	},function(err){
    		
    	});
        $scope.updateUserAlerts = function (smsAlert, emailAlert) {
        	$scope.updatingAlertParam = {
        			sms:smsAlert || false,
        			email:emailAlert || false
        	};
        	
        	console.log("Alerts",$scope.updatingAlertParam);
        	UserService.updateUserAlerts($scope.updatingAlertParam).then(function(res){
        		console.log("alert res",res);
        		$scope.displayNotification('show',res.data.msg,'info');
        	},function(err){
        		
        	});
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