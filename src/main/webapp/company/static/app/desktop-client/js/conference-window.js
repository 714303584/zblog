'use strict';
/* global nw */

angular.module('pexapp')

.factory('conferenceWindowService', function($rootScope, $log, $q) {
    var conferenceWindow;
    var messageDeferreds = {};

    window.addEventListener('message', function(event) {
        console.log('Got window message', event);
        var deferred = messageDeferreds[event.data.id];
        if (deferred) {
            if (event.data.result) {
                deferred.resolve();
            } else {
                deferred.reject();
            }
        }
    });

    function closeConferenceWindow() {
        var deferred = $q.defer();
        if (conferenceWindow) {
            $log.log('conferenceWindowService: closing window');
            conferenceWindow.once('closed', deferred.resolve);
            conferenceWindow.close();
        } else {
            deferred.resolve();
        }
        return deferred.promise;
    }

    function openConferenceWindow(host, alias, displayName, token, remoteAlias, media, audioonly, escalate) {
        var closePromise;
        if (conferenceWindow) {
            console.log('Sending window message');
            var id = Date.now();
            messageDeferreds[id] = $q.defer();
            conferenceWindow.window.postMessage({
                id: Date.now(),
                type: 'open-new',
            }, '*');
            closePromise = messageDeferreds[id].promise.then(closeConferenceWindow);
            conferenceWindow.show();
            conferenceWindow.focus();
        } else {
            closePromise = closeConferenceWindow();
        }
        closePromise.then(function() {
            var title = alias + ' - ' + nw.App.manifest.name;
            host = host ? '&host=' + encodeURIComponent(host) : '';
            alias = '&conference=' + encodeURIComponent(alias);
            displayName = displayName ? '&name=' + encodeURIComponent(displayName) : '';
            token = token ? '&token=' + encodeURIComponent(token) : '';
            remoteAlias = remoteAlias ? '&remote_alias=' + encodeURIComponent(remoteAlias) : '';
            media = media ? '&media=1' : '&media=';
            audioonly = audioonly ? '&audioonly=1' : '&audioonly=';
            escalate = escalate ? '&escalate=1' : '';
            var url = 'desktop-client/index.conference.html#/?join=1' + host + alias + displayName + token + remoteAlias + media + audioonly + escalate;
            $log.log('openConferenceWindow', url);
            nw.Window.open(
                url, {
                    id: 'conferenceWindow',
                    title: title,
                    show: false,
                    // toolbar: false,
                    // frame: true,
                    width: 900,
                    height: 650,
                    min_width: 450,
                    min_height: 253,
                    position: 'center',
                    icon: 'configuration/favicon.png',
                    focus: true
                }, function(win) {
                    conferenceWindow = win;
                    conferenceWindow.once('document-end', function() {
                        conferenceWindow.title = title;
                        conferenceWindow.window.onbeforeunload = null;
                        conferenceWindow.show();
                        conferenceWindow.focus();
                        global.isConferenceWindowOpen = true;
                    });
                    conferenceWindow.once('closed', function() {
                        $log.log('conferenceWindow closed');
                        conferenceWindow = null;
                        delete global.isConferenceWindowOpen;
                    });
                    conferenceWindow.on('new-win-policy', function(frame, url, policy) {
                        nw.Shell.openExternal(url);
                        policy.ignore();
                    });
                });
        });
    }

    $rootScope.$on('pex::callAccept', function(event, message) {
        $log.log('conferenceWindowService.on callAccept', message);
        openConferenceWindow(
            message.host,
            message.conference_alias,
            undefined,
            message.token,
            message.remote_alias,
            true,
            false,
            false);
    });

    return {
        openConferenceWindow: openConferenceWindow,
        close: closeConferenceWindow,
        getWindow: function() {
            return conferenceWindow;
        }
    };
});
