mainApp.controller('signUpController', function($scope, $location, $http, UserService) {
        // create a message to display in our view
	$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		console.log("Root url",$scope.rootUrl);
		
		$scope.goBacktoLoginPage = function() {
			$location.path($scope.rootUrl);
		}
		
		$scope.registerNewUser = function(userType) {
			if(!($scope.password==$scope.confirmpassword)){
				console.log("Passwords must match!");
				return;
			}
			
			$scope.dataTosend = {};
			if(userType == 'vendor') {
				var vendor = {
						firstname: $scope.vendorfirstname,
						lastname: $scope.vendorlastname,
						email:$scope.vendoremail,
						password:$scope.vendorpassword,
						phone:$scope.vendorPhone,
						type: userType	
				};
				$scope.dataTosend.userDetails = vendor;
				var truckDetails = {};
				truckDetails = {
						name: $scope.truckName
				};
				$scope.dataTosend.truckDetails = truckDetails;
			} else if (userType == 'customer') {
				var customer = {
						firstname: $scope.firstname,
						lastname: $scope.lastname,
						email:$scope.useremail,
						password:$scope.password,
						phone:$scope.userPhone,
						type: userType	
				};
				$scope.dataTosend.userDetails = customer;
			}
			
			console.log("Details to send ",$scope.dataTosend);
			/*var fd = new FormData();
	        fd.append('file', $scope.file);

	        var info = {
	            "text":"additional info"
	        };
	        fd.append('data', $scope.dataTosend);*/

	       /* $http.post('registerUser', fd, {
	             transformRequest: angular.identity,
	             headers: {'Content-Type': undefined}
	        })
	       */
			$http({
	            method: 'POST',
	            url: 'registerUser',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            data:	JSON.stringify({data: $scope.dataTosend, action:'registerUser'}),
	            /*transformRequest: function (data, headersGetter) {
	                var formData = new FormData();
	                angular.forEach(data, function (value, key) {
	                    formData.append(key, value);
	                });

	                var headers = headersGetter();
	                delete headers['Content-Type'];
	                return formData;
	            }*/
	        })
	        .success(function (data) {
	        	console.log("Success", data);
				$scope.message = data;
				$scope.displayNotification('show', 'Registered Successfully', 'success');
				$scope.goBacktoLoginPage();
				
	        })
	        .error(function (data, status) {
	        	console.log("Failure "+ JSON.stringify({data: data}));
	        });
		}
		
		$scope.displayNotification = function(status, message, type) {
        	$scope.notification = {};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
		
		/* $scope.loadLoginPage = function() {
	        	console.log("Load Home page",$scope.rootUrl+'home');
	        	$location.path($scope.rootUrl);
	        };  */
    });