mainApp.service('UserService', function($http) {
    this.checkForUserExistence = function () {
        
    	
    	
    	return x.toString(16);
    }
    
    this.updateUserAlerts = function (userAlerts) {
    		var res = $http.post('updateUserAlerts',userAlerts);
    		return res;
    }
});