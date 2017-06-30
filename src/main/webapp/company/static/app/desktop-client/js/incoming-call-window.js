'use strict';
/* global nw */

angular.module('pexapp')

.run(function($rootScope, $q, $log) {
    nw.Screen.Init();
    var notificationWindows = {};
    var width = 350;
    var height = process.platform === 'linux' ? 56 : 60;

    function openIncomingCallWindow(message) {
        $log.log('openIncomingCallWindow()');
        notificationWindows[message.token] = nw.Window.open(
            'desktop-client/index.notification.html', {
                title: process.platform === 'darwin' ? '' : message.conference_alias,
                show: false,
                // show_in_taskbar: process.platform === 'darwin',
                visible_on_all_workspaces: true,
                transparent: process.platform !== 'linux',
                // toolbar: false,
                frame: false,
                resizable: false,
                width: width,
                height: height,
                // min_width: width,
                // min_height: height,
                // max_width: width,
                // max_height: height,
                focus: true,
                icon: 'configuration/favicon.png',
                always_on_top: true
            }, function(win) {
                var notificationWindow = win;
                notificationWindow.once('loaded', function() {
                    notificationWindow.window.postMessage({
                        message: message
                    }, '*');

                    var workArea = nw.Screen.screens[0].work_area;
                    var x = workArea.x + workArea.width - width - 20;
                    var y = workArea.y + 20;
                    if (process.platform === 'win32') {
                        y = workArea.y + workArea.height - height - 20;
                    }

                    notificationWindow.moveTo(x, y);
                    notificationWindow.show();
                    notificationWindow.focus();
                    notificationWindow.setAlwaysOnTop(true);
                });

                notificationWindow.on('close', function() {
                    delete notificationWindows[message.token];
                    notificationWindow.close(true);
                    notificationWindow = null;
                });

                notificationWindows[message.token] = notificationWindow;
            });

    }

    $rootScope.$on('pex::onIncomingCall', function(event, message) {
        $log.log('pex::onIncomingCall');
        openIncomingCallWindow(message);
    });

    $rootScope.$on('pex::callAccept', function(event, message) {
        $log.log('pex::callAccept');
        var notificationWindow = notificationWindows[message.token];
        if (notificationWindow) {
            notificationWindow.close();
        }
    });

    $rootScope.$on('pex::onIncomingCallCancelled', function(event, message) {
        $log.log('pex::onIncomingCallCancelled');
        var notificationWindow = notificationWindows[message.token];
        if (notificationWindow) {
            notificationWindow.close();
        }
    });

    $rootScope.$on('pex::callReject', function(event, message) {
        $log.log('incomingCallWindow.on callReject', message);
        var notificationWindow = notificationWindows[message.token];
        if (notificationWindow) {
            notificationWindow.close();
        }
    });
});
