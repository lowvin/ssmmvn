var var app = angular.module('logReg', []);
// function  loginController($scope,$http) {
app.controller('loginController', function($scope,$http) {
    $scope.userLogin = function () {
        $http({
            method:"post",
            url:"/ssmmvn/login",
            params:{
                "userName":$scope.loginUserName,
                "password":$scope.loginPassword
            }
        }).success(function (data){
            console.log(data);
            $scope.loginInfo = data;
        })
    };
});
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
            alert("注册成功，返回登录！");
            var login = document.getElementById("logining");
            login.style.display = "block";
            var register = document.getElementById("registering");
            register.style.display = "none";
            var text = document.getElementById("label_text");
            text.innerText = "欢迎登陆";
        })
    };
});