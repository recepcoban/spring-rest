var App = angular.module('nameApp', []);


App.controller('nameCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {

        $http.get('/book').success(function (data)
        {
            $scope.Items = data;
        });
        
        $scope.create = function () {

            $http({
                url: '/insert',
                method: "POST",
                data: JSON.stringify({"bookname": $scope.bookname, "author": $scope.author}),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                $window.alert(data.message);
                $window.location.reload();
            }).error(function (data, status, headers, config) {
                $window.alert("Server error " + status);
            });
        }

        

        $scope.bookdelete = function () {
            $scope.deletedbookname = $scope.editData.row.bookname;
            $http.get('/delete/' + $scope.deletedbookname)
                    .success(function (data)
                    {
                  $window.alert(data.message);
                  $window.location.reload();
                    }).error(function (data, status, headers, config) {
                $scope.showModal = !$scope.showModal;
                $window.alert("Server error " + status);
            });
        }
        
            $scope.editData={};
            
            $scope.getrowdata=function(variable)
           {    
            	$scope.editData.row=variable;
            	$scope.updatebookname=variable.bookname;
            	$scope.updateauthor=variable.author;
                
           }
            
        
        $scope.update = function () {
            
            $scope.updatename = $scope.editData.row.bookname;
            
            
            $http({
                url: '/update/' + $scope.updatename,
                method: "POST",
                data: JSON.stringify({"bookname": $scope.updatebookname, "author": $scope.updateauthor}),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                $window.alert(data.message);
                $window.location.reload();
            }).error(function (data, status, headers, config) {
                $window.alert("Server error " + status);
            });
        }

    }]);

