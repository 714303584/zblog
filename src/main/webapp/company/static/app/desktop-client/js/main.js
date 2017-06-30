'use strict';
/* global nw */

angular.module('pexapp')

.constant('path', require('path'))

.factory('globalService', function() {
    return global.globalService;
})

.run(function($rootScope, $localStorage, registrationService, mainWindowService, conferenceWindowService) {
    global.globalService = {};
    global.globalService.VERSION_STRING = global.VERSION_STRING;
    console.log('Version: ', global.VERSION_STRING);

    global.globalService.register = function(host, uri, userName, password, callback, storePassword) {
        if (uri) {
            var uriComponents = uri.split('@');
            if (!userName) {
                userName = $localStorage.registrationUsername = uriComponents[0];
            }
            if (!host && uriComponents[1]) {
                host = $localStorage.registrationHost = uriComponents[1];
            }
        }
        console.log('global.register', host, uri, userName, password, callback, storePassword);
        return registrationService.register.apply(this, [host, uri, userName, password, callback, storePassword]);
    };
    global.globalService.unregister = function() {
        console.log('global.unregister', arguments);
        return registrationService.unregister.apply(this, arguments);
    };
    global.globalService.callReject = function(message) {
        console.log('global.callReject', message);
        $rootScope.$emit('pex::callReject', message);
    };
    global.globalService.callAccept = function(message) {
        console.log('global.callAccept', message);
        $rootScope.$emit('pex::callAccept', message);
    };
    global.globalService.join = function(alias, displayName, pin, token, media, audioonly) {
        console.log('global.join', alias, displayName, pin, token, 'media:', media, 'audioonly:', audioonly);
        // mainWindowService.close();
        conferenceWindowService.openConferenceWindow(undefined, alias, displayName, token, undefined, media, audioonly);
    };
    global.globalService.reset = function() {
        mainWindowService.close().then(function() {
            global.globalService.unregister(function() {
                $localStorage.$reset({
                    callHistory: $localStorage.callHistory
                });
                mainWindowService.open();
            });
        });
    };

    // global.openMainWindow and quit is only used for selenium tests and should not be needed internally
    global.openMainWindow = function(callback) {
        $rootScope.$emit('pex::Open', callback);
    };
    global.quit = function() {
        $rootScope.$emit('pex::Quit');
    };
})

.run(function($rootScope, $log, $q, $localStorage, trayService, registrationService, mainWindowService, conferenceWindowService) {
    function getConferenceUri(argv) {
        var result;
        var re = /pexip:\/.(\S+)/i;
        argv.forEach(function(arg) {
            var match = re.exec(arg);
            if (match) {
                result = match[1];
            }
        });
        return result;
    }

    function main(argv) {
        $log.log('main: argv', argv);
        var conferenceUri = argv && getConferenceUri(argv);
        $log.log('main: conferenceUri', conferenceUri);
        if (conferenceUri) {
            global.globalService.join(conferenceUri);
        } else if (conferenceWindowService.getWindow()) {
            conferenceWindowService.getWindow().show();
            conferenceWindowService.getWindow().focus();
        } else if (!$localStorage.startMinimized) {
            mainWindowService.open();
        }
    }

    nw.App.on('open', function(cmdline) {
        $log.log('App.on("open")', cmdline);
        var argv = cmdline.split(/\s/);
        main(argv);
    });

    nw.App.on('reopen', function(argv) {
        $log.log('App.on("reopen")', argv);
        main(argv);
    });

    nw.Window.get().once('loaded', function() {
        main(nw.App.argv);
    });

    $rootScope.$on('pex::Quit', function() {
        $log.log('pex::Quit');
        trayService.remove();

        var unregisterDeferred = $q.defer();
        registrationService.unregister(unregisterDeferred.resolve);

        $q.all([
            unregisterDeferred.promise,
            conferenceWindowService.close(),
            mainWindowService.close()
        ]).then(function() {
            nw.App.quit();
        });
    });

    $rootScope.$on('pex::Open', function(event, callback) {
        $log.log('pex::Open');
        mainWindowService.open(callback);
    });

});
