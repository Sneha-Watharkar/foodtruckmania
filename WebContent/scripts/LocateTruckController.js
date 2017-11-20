mainApp.controller('locateTruckController', function($scope, $location) {
        // create a message to display in our view
        $scope.currentPage = 'Truck Locations';
        $scope.rootUrl = $location.$$absUrl;
        console.log("Root url",$scope.rootUrl); 
});