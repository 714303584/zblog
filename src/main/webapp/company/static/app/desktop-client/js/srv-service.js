/* global $*/

angular.module('pexapp')

.factory('srvService', function($q, $log, $localStorage, applicationSettings) {
    'use strict';
    var dns = require('dns');
    var ipRe = /^\d+\.\d+\.\d+\.\d+$/;

    function getPexappAddresses(domain, verbatimFallback) {
        var serversDeferred = $q.defer();
        if (domain && domain.match(ipRe)) {
            // If domain is an ip address, return it as the address
            serversDeferred.resolve([domain]);
        }
        else if (domain) {
            var record = '_pexapp._tcp.' + domain;
            dns.resolveSrv(record, function(error, addresses) {
                if (error) {
                    $log.debug('Unable to resolve', record);
                    if (verbatimFallback) {
                        $log.debug('Falling back to', domain);
                        serversDeferred.resolve([domain]);
                    } else {
                        serversDeferred.resolve([]);
                    }
                } else {
                    $log.debug('Resolved', record, addresses);
                    // Sort addresses by priority
                    addresses.sort(function(a, b) {
                        return a.priority > b.priority ? 1 : -1;
                    });
                    serversDeferred.resolve(
                        // Return only the addresses with their ports
                        addresses.reduce(function(domains, address) {
                            domains.push(address.name + ':' + address.port);
                            return domains;
                        }, [])
                    );
                }
            });
        } else {
            serversDeferred.resolve([]);
        }
        return serversDeferred.promise;
    }

    return {
        getVmrDetails: function(uri) {
            var uriComponents = uri.split('@');
            var servers = [];
            // allow uri SRV lookup to be disabled for test purposes
            if (!$localStorage.skipUriSrv) {
                servers.push(getPexappAddresses(uriComponents[1], false));
            }
            servers.push(
                getPexappAddresses(applicationSettings.serverAddress, true),
                getPexappAddresses($localStorage.serverAddress, true),
                getPexappAddresses($localStorage.registrationHost, true));
            return $q.all(servers).then(function(servers) {
                return [].concat.apply([], servers);
            });
        }
    };
});
