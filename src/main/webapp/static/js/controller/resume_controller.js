'use strict';

angular.module('myApp').controller('ResumeController', ['$scope', 'ResumeService', function ($scope, ResumeService) {

    var self = this;
    self.position_search = "";
    self.resumes = [];
    self.refreshDb = refreshDb;
    self.search = search;

    fetchAll();

    function fetchAll() {
        $scope.loading = true;
        ResumeService.fetchAll()
            .then(
                function (d) {
                    self.resumes = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Resumes');
                }
            ).finally(function () {
            $scope.loading = false;
        });
    }

    function refreshDb() {
        $scope.loading = true;
        ResumeService.refreshDb()
            .then(
                function (d) {
                    self.resumes = d;
                },
                function (errResponse) {
                    console.error('Error while refreshing Resumes');
                }
            ).finally(function () {
            $scope.loading = false;
        });
    }

    function search() {
        ResumeService.fetchSearch(self.position_search)
            .then(
                function (d) {
                    self.resumes = d;
                },
                function (errResponse) {
                    console.error('Error while searching Resumes');
                }
            );
    }

}]);