
var app = angular.module('app',['ngRoute','ui.bootstrap','mobile-angular-ui','mobile-angular-ui.gestures']);

app.config(function($routeProvider) {
	  $routeProvider.when('/',              {templateUrl: 'order.html', 
          controller  : 'OrderCtrl',
          reloadOnSearch: false});
	});


//
// For this trivial demo we have just a unique MainController 
// for everything
//
app.controller('MainController', function($rootScope, $scope){

});

app.controller('OrderCtrl', function($scope,$http){
	
	  this.myInterval = 5000;
	  this.noWrapSlides = false;
	  this.active = 0;

	  $http.get("../json/get.do?pageModel=com.runtech.onlineshop.json.CommodityOrderJson&formId=1000000209")
		.then(function(response) {
		    $scope.json = response.data;
		    $scope.order = response.data.result;
		});

});
  
app.controller('TabController', function(){
  this.tab = "Description";

  this.setTab = function(newValue){
    this.tab = newValue;
  };

  this.isSet = function(tabName){
    return this.tab === tabName;
  };
});