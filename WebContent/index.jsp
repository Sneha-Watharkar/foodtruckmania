<!DOCTYPE html>
<html ng-app="mainApp">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Food truck mania</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
<!-- <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/jquery-3.2.1.min.js"></script> -->
<link href="dashboard.css" rel="stylesheet">
<link href="styles.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>
<script src="scripts/mainApp.js"></script>


<!-- Including all js files -->
<script src="scripts/LoginController.js"></script>
<script src="scripts/SignUpController.js"></script>
<script src="scripts/HomePageController.js"></script>
<script src="scripts/LocateTruckController.js"></script>
</head>
<body ng-controller="mainController">
	<div ng-view></div>
	<form action="loginUser?action=login" method="post" class="form-group ">
		<input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
		<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
		<input type="submit" value="Log On" class="btn btn-def btn-block">
	</form>
	<div class="form-group">
            <h1 align="center">${requestScope.msg}</h1>
        </div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>