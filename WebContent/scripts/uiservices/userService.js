mainApp.service('UserService', function($http) {
    this.checkForUserExistence = function (userName) {
    	$http({
            method: 'POST',
            url: 'loginUser',
            headers: {
                'Content-Type': 'application/json'
            },
            data:	JSON.stringify({data: userName, action:'login'})
        })
        .success(function (data) {
        	console.log("Success", data);
        	return data;
        })
        .error(function (data, status) {
        	console.log("Failure "+ JSON.stringify({data: data}));
        	return data;
        });
    	
    	
    }
    
    this.updateUserAlerts = function (userAlerts) {
    		var res = $http.post('updateUserAlerts',userAlerts);
    		return res;
    }
});