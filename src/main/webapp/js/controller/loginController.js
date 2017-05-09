/**
 * Created by 罗文 on 2017/1/5.
 */

var app = angular.module('logReg', []);
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

