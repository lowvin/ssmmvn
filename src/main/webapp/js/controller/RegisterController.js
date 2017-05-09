app.controller('registerController', function($scope,$http) {
    $scope.userRegister = function () {
        $scope.user = {
            "userName":$scope.registerUserName,
            "password":$scope.registerPassword,
            "email":$scope.registerEmail
        };
        console.log($scope.user);
        $http({
            method:"post",
            url:"/ssmmvn/register",
            data:$scope.user
        }).success(function (data){
            $scope.loginInfo = data;
        })
    };
});