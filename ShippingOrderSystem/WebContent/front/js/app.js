var ORDER_OBJECT_TYPE = "com.runtech.onlineshop.json.CommodityOrderJson";
var COMMODITY_OBJECT_TYPE = "com.runtech.onlineshop.json.CommodityJson";
var orderId;

var app = angular.module('app',['ngRoute','ngSanitize','ui.bootstrap','mobile-angular-ui','mobile-angular-ui.gestures','runtech-data-object']);

app.factory('Data', function(){
    var data =
    {
        orderId: '',
        setOrderId: function(name) {
        	data.orderId = angular.copy(name); 
        }
    };
	return data;
});

app.config(function($routeProvider) {
	  $routeProvider.when('/order/:orderId',              {templateUrl: 'order/order.html', 
	          controller  : 'OrderCtrl',
	          reloadOnSearch: false})
          .when('/commodity/:commodityId',              {templateUrl: 'commodity/commodity.html', 
	          controller  : 'CommodityCtrl',
	          reloadOnSearch: false})
          .otherwise({redirectTo: '/order'});
	});


//
// For this trivial demo we have just a unique MainController 
// for everything
//
app.controller('MainController', function($rootScope, $scope){

});

app.controller('OrderCtrl', ['$scope','$http','$rootScope','$routeParams','RuntechDataObject','Data',
                             function($scope,$http,$rootScope,$routeParams,RuntechDataObject,Data){
	$scope.success = false;
	var tempOrderId = $routeParams.orderId;
	RuntechDataObject.get(ORDER_OBJECT_TYPE,tempOrderId)
		.then(function(response) {
		    $scope.order = response.data.result;
			$scope.success = response.data.success;
		});
	Data.setOrderId(tempOrderId);
	$rootScope.$broadcast('dataChanged');
}]);

app.controller('CommodityCtrl', ['$scope','$http','$routeParams','RuntechDataObject',
                                 function($scope,$http,$routeParams,RuntechDataObject){
	$scope.queryFailed = false;
	RuntechDataObject.get(COMMODITY_OBJECT_TYPE,$routeParams.commodityId)
		.then(function(response) {
		    $scope.commodity = response.data.result;
			$scope.queryFailed = !response.data.success;
		});
}]);

app.controller('SidebarController',['$scope','$rootScope','Data',function($scope,$rootScope,Data){
	$rootScope.$on('dataChanged',function(){$scope.orderId = Data.orderId;});
}]);

app.controller('BottomNavController',['$scope','$rootScope','Data',function($scope,$rootScope,Data){
	$rootScope.$on('dataChanged',function(){$scope.orderId = Data.orderId;});
}]);

app.controller('TabController', function(){
  this.tab = '1';

  this.setTab = function(newValue){
    this.tab = newValue;
  };

  this.isSet = function(tabName){
    return this.tab === tabName;
  };
});


app.controller('AccordionController', function(){
  this.visiable = true;

  this.click = function(){
    this.visiable = !this.visiable;
  };

  this.isVisiable = function(){
    return this.visiable;
  };
});