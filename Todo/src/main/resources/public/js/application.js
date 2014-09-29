var module = angular.module('myApp', ['ui.router','ui.bootstrap']);
module.config(function($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/index');
  $stateProvider.state('index', {
    url : '/index:id',
    templateUrl : 'pages/todos.html',
    controller : 'TodoCtrl'
  })
});
module.controller('TodoCtrl',['$scope','$http','$log','$stateParams',function($scope, $http, $log,$stateParams) {
  $scope.todos = [];
  $scope.recentTodo = 0;
  $scope.pages = 0;
  $scope.currentPage = 0;
  $scope.newTodo = {title: '', priority:'LOW'};
  $scope.priorities = ['HIGH','MEDIUM','LOW'];
  $scope.getNumber = function(num) {
    var array = [];
    for (var i = 0; i < num; i++) {
      array[i] = i;
    }
    return array;
  };
  $scope.inital = function() {
    $scope.todos = [];
    $http.get('/rest/todo?size=10&page='+$stateParams.id).success(function(todos) {
      $scope.pages = todos["page"]["totalPages"];
      $scope.currentPage = todos["page"]["number"];
      angular.forEach(todos["_embedded"]["todo"],function(value, key) {
        var todo = {
            link : value["_links"]["self"]["href"],
            title: value["title"],
            description: value["description"],
            priority: value["priority"]
        };
        $scope.todos.push(todo);
      });
     });
  };
  $scope.setPriority = function(priority){
    $scope.newTodo.priority = priority;
  };
  $scope.saveTodo = function(){
    $log.info($scope.newTodo);
    $http.post('/rest/todo', $scope.newTodo).success(function() {
      $log.info('Post successfull');
      $scope.todos.push($scope.newTodo);
      $scope.newTodo = {title: '', priority:'LOW'};
    });
  };
  $scope.inital();
}]);

module.controller('NewCtrl', [ '$scope', '$log',function($scope,$log) {
  $scope.inital = function() {};
  $scope.status = {
      isopen: false
  };
  $scope.inital();
}]);

module.controller('IndexCtrl', [ '$scope', '$log',function($scope,$log) {
  $scope.inital = function() {};
  $scope.inital();
}]);