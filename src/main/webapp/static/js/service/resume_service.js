'use strict';

angular.module('myApp').factory('ResumeService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8080/rest/resume/';

    var factory = {
        refreshDb: refreshDb,
        fetchAll: fetchAll,
        fetchSearch: fetchSearch
    };

    return factory;

    function refreshDb() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "refresh")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Resumes');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAll() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "all")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Resumes');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchSearch(position) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "search", {params: {position: position}})
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Resumes');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);