'use strict';
/* global nw */

angular.module('pexapp')

.factory('mainWindowService', function($q, $log) {
    var mainWindow;
    var width = 400;
    var height = 650;

    return {
        open: function(callback) {
            callback = callback || angular.noop;
            if (!mainWindow) {
                $log.log('mainWindowService.open(): creating window');
                nw.Window.open(
                    'desktop-client/index.main.html', {
                        id: 'mainWindow',
                        title: nw.App.manifest.name,
                        show: false,
                        // toolbar: false,
                        // frame: true,
                        width: width,
                        height: height,
                        min_width: width,
                        min_height: 300,
                        position: 'center',
                        icon: 'configuration/favicon.png',
                        focus: true,
                    }, function(win) {
                        mainWindow = win;
                        mainWindow.once('document-end', function() {
                            $log.log('mainWindowService: document-end');
                            mainWindow.title = nw.App.manifest.name;
                            mainWindow.window.onbeforeunload = null;
                            mainWindow.show();
                            mainWindow.focus();
                            callback();
                        });

                        mainWindow.once('closed', function() {
                            $log.log('mainWindow closed');
                            mainWindow = null;
                        });

                        mainWindow.on('new-win-policy', function(frame, url, policy) {
                            $log.log('mainWindowService: new-win-policy', frame, url, policy);
                            nw.Shell.openExternal(url);
                            policy.ignore();
                        });
                    });
            } else {
                $log.log('mainWindowService.open(): showing window');
                mainWindow.show();
                mainWindow.focus();
                callback();
            }
        },

        close: function() {
            var deferred = $q.defer();
            if (mainWindow) {
                $log.log('mainWindowService: closing window');
                mainWindow.once('closed', deferred.resolve);
                mainWindow.close();
            } else {
                deferred.resolve();
            }
            return deferred.promise;
        }
    };
});
