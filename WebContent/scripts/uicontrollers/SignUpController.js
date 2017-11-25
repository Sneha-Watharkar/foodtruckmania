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
						name: $scope.truckName,
						location:$scope.truckLocation
				};
				$scope.dataTosend.truckDetails = truckDetails;
				$scope.dataTosend.menu = $scope.file;
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
	        	console.log("Success");
				$scope.message = data;
	        })
	        .error(function (data, status) {
	        	console.log("Failure "+ JSON.stringify({data: data}));
	        });
	        
			/*var res = $http.post('registerUser', $scope.userDetails);
			res.success(function(data, status, headers, config) {
				
			});
			res.error(function(data, status, headers, config) {
				
				alert( "failure message: " + JSON.stringify({data: data}));
			});	
			*/
		}
    });