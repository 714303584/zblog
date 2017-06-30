angular.module('pexapp')

.factory('srvService', function($q, $localStorage, $timeout, applicationSettings) {
    'use strict';
    return {
        getVmrDetails: function(uri) {
            var uriComponents = uri.split('@');
            var domain = uriComponents[1];

            var deferred = $q.defer();
            if (!applicationSettings.serverAddress && $localStorage.serverAddress) {
                deferred.resolve([$localStorage.serverAddress]);
            } else if (applicationSettings.serverAddress) {
                deferred.resolve([applicationSettings.serverAddress]);
            } else {
                var promise = window.srv.resolveSrvAsync(domain);
                promise.then(
                    function(result) {
                        $timeout(function() {
                            deferred.resolve([result.content.replace(/https:\/\//, '')]);
                        });
                    },
                    function(error) {
                        $timeout(function() {
                            deferred.resolve([domain]);
                        });
                    }
                );
            }

            return deferred.promise;
        }
    };
});
