var app = angular.module('orderApp',['ui.bootstrap','mobile-angular-ui']);

app.controller('Order', function($scope){
	  this.myInterval = 5000;
	  this.noWrapSlides = false;
	  this.active = 0;
	  
    this.orderItems = data;

  });
  
app.controller('OrderItem', function(){
  });
    
app.controller('OrderItemTabController', function(){
    this.tab = "Description";

    this.setTab = function(newValue){
      this.tab = newValue;
    };

    this.isSet = function(tabName){
      return this.tab === tabName;
    };
});


var data = [
  {
	  id: 0,
    name: 'Azurite',
    description: "Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.",
    shine: 8,
    price: 110.50,
    rarity: 7,
    color: '#CCC',
    faces: 14,
    image: 'images/300(1).jpg'
  },
  {
	  id: 1,
    name: 'Bloodstone',
    description: "Origin of the Bloodstone is unknown, hence its low value. It has a very high shine and 12 sides, however.",
    shine: 9,
    price: 22.90,
    rarity: 6,
    color: '#EEE',
    faces: 12,
    image: 'images/300(2).jpg'
  },
  {
	  id: 2,
    name: 'Zircon',
    description: "Zircon is our most coveted and sought after gem. You will pay much to be the proud owner of this gorgeous and high shine gem.",
    shine: 70,
    price: 1100,
    rarity: 2,
    color: '#000',
    faces: 6,
    image: 'images/300(3).jpg'
  }
];
