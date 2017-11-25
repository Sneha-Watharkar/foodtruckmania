mainApp.controller('locateTruckController', function($scope, $location) {
        // create a message to display in our view
        $scope.currentPage = 'Truck Locations';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);;
        console.log("Root url",$scope.rootUrl); 
});