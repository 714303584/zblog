'use strict';

angular.module('pexapp')

.factory('Call', function($log, $timeout, $q, $sce, $rootScope, $translate, $localStorage, applicationSettings) {
    return function Call(host, alias, displayName, token, registration_token) {
        $log.debug('Call', host, alias, displayName, token, registration_token);
        var _this = this;

        /* global PexRTC */
        var rtc = new PexRTC();

        this.getPexRTC = function() {
            // Used for plugins
            return rtc;
        };

        this.parseParticipant = function(data) {
            function getAvatarUrl(width, height) {
                return 'https://' + host +
                    '/api/client/v2/conferences/' + alias +
                    '/participants/' + data.uuid +
                    '/avatar.jpg?width=' + width +
                    '&height=' + height +
                    '&token=' + rtc.token;
            }

            function yesToBoolean(yes) {
                return yes === 'YES' ? true : false;
            }

            function allowToBoolean(allow) {
                return allow === 'ALLOW' ? true : false;
            }
            return {
                apiUrl: data.api_url,
                displayName: data.display_name || data.uri.replace('sip:', ''),
                hasMedia: data.has_media,
                overlayText: data.overlay_text,
                role: data.is_external ? 'external' : data.role,
                serviceType: data.service_type,
                spotlight: data.spotlight,
                startTime: data.start_time * 1000 || Date.now(),
                protocol: data.protocol,
                vendor: data.vendor,
                uri: data.uri,
                uuid: data.uuid,
                isChair: data.role === 'chair',
                isWaiting: data.service_type === 'waiting_room',
                isConnected: data.service_type === 'conference' || data.service_type === 'lecture',
                isConnecting: data.service_type === 'connecting',
                isStreaming: data.is_streaming_conference,
                isMuted: yesToBoolean(data.is_muted),
                isPresenting: yesToBoolean(data.is_presenting),
                isPresentationSupported: yesToBoolean(data.presentation_supported),
                isRxPresentation: allowToBoolean(data.rx_presentation_policy),
                avatarUrl: getAvatarUrl(40, 40),
                getAvatarUrl: getAvatarUrl,
                isExternal: data.is_external
            };
        };

        this.startCall = function(callType, bandwidth, videoSource, audioSource, flashElement) {
            rtc.call_type = callType;
            rtc.flash = flashElement;
            rtc.video_source = videoSource;
            rtc.audio_source = audioSource;
            if (bandwidth) {
                rtc.bandwidth_in = parseInt(bandwidth) - 64;
                rtc.bandwidth_out = rtc.bandwidth_in;
            }
            if (applicationSettings.h264_enabled !== undefined) {
                rtc.h264_enabled = applicationSettings.h264_enabled;
            }
            rtc.addCall(callType, flashElement);
        };

        this.getBandwidth = function() {
            return rtc.bandwidth_out + 64;
        };

        rtc.onSetup = function(stream, pinStatus, conferenceExtension) {
            $timeout(function() {
                $log.debug('PexRTC.onSetup', stream, pinStatus, conferenceExtension);

                if (stream) {
                    $rootScope.$broadcast('call::localMediaStream', stream, rtc.call_type);
                }

                if (!stream && conferenceExtension) {
                    $rootScope.$broadcast('call::extensionRequested', conferenceExtension);
                } else if (pinStatus !== 'none') {
                    $rootScope.$broadcast('call::pinRequested', pinStatus === 'required');
                } else {
                    _this.connect();
                }
            });
        };
        this.connect = function(pin, extension) {
            $log.debug('Call.connect', pin, extension);
            _this.microphoneMuted = rtc.muteAudio($localStorage.muteOnJoin);
            _this.cameraMuted = rtc.muteVideo($localStorage.muteCameraOnJoin);
            rtc.connect(pin, extension);
        };
        rtc.onConnect = function(stream) {
            $timeout(function() {
                $log.debug('PexRTC.onConnect', stream);
                if (rtc.uuid) {
                    $rootScope.$broadcast('call::connected', {
                        uuid: rtc.uuid,
                        displayName: rtc.display_name,
                        isChair: rtc.role === 'HOST',
                        alias: rtc.conference_extension || rtc.conference,
                        avatarUrl: 'https://' + host + '/api/client/' + (rtc.version.version_id < 12 ? 'v1' : 'v2') + '/conferences/' + alias + '/avatar.jpg',
                        version: rtc.getVersion(),
                        chatEnabled: rtc.chat_enabled,
                        feccEnabled: rtc.fecc_enabled,
                        analyticsEnabled: rtc.analytics_enabled,
                        serviceType: rtc.service_type,
                        isGateway: rtc.service_type === 'gateway',
                        remoteCallType: rtc.remote_call_type
                    });
                }
                _this.remoteMediaStream = $sce.trustAsResourceUrl(stream);
                $log.debug('remoteMediaStream', _this.remoteMediaStream);
            });
            $rootScope.$broadcast('call::remoteMediaStream', stream, rtc.call_type);
        };
        rtc.onError = function(reason) {
            $timeout(function() {
                $log.debug('PexRTC.onError', reason);
                // _this.disconnect();
                $rootScope.$broadcast('call::error', reason);
            });
        };
        rtc.onDisconnect = function(reason) {
            $timeout(function() {
                $rootScope.$broadcast('call::disconnected', reason);
            });
        };

        rtc.onConferenceUpdate = function(data) {
            $timeout(function() {
                $log.debug('onConferenceUpdate', data);
                if (angular.isDefined(_this.locked) && _this.locked !== data.locked) {
                    $rootScope.$broadcast('call::conferenceLocked', data.locked);
                }
                _this.locked = data.locked;
                _this.guestsMuted = data.guests_muted;
            });
        };

        rtc.onRoleUpdate = function(role) {
            $timeout(function() {
                $rootScope.$broadcast('call::roleUpdated', role === 'HOST');
            });
        };

        this.participantAdd = function(uri, protocol, role, userParams) {
            rtc.dialOut(uri, protocol, role || 'guest', function(response) {
                $rootScope.$broadcast(
                    'call::participantAdd', {
                        result: response.result,
                        waiting: response.result.slice(),
                        uri: uri,
                        protocol: protocol,
                        role: role
                    });
            }, userParams);
        };
        this.participantDisconnect = function(participant) {
            rtc.disconnectParticipant(participant.uuid);
        };
        this.participantUnlock = function(participant) {
            rtc.unlockParticipant(participant.uuid);
        };
        this.participantToggleMute = function(participant) {
            participant.isMuted = !participant.isMuted;
            rtc.setParticipantMute(participant.uuid, participant.isMuted);
        };
        this.participantSetMute = function(participant, value) {
            rtc.setParticipantMute(participant.uuid, value);
        };
        this.participantSetRxPresentation = function(participant, value) {
            rtc.setParticipantRxPresentation(participant.uuid, value);
        };
        rtc.onParticipantCreate = function(data) {
            $timeout(function() {
                $rootScope.$broadcast('call::participantUpdate', _this.parseParticipant(data));
            });
        };
        rtc.onParticipantUpdate = function(data) {
            $timeout(function() {
                $rootScope.$broadcast('call::participantUpdate', _this.parseParticipant(data));
            });
        };
        rtc.onParticipantDelete = function(data) {
            $timeout(function() {
                $rootScope.$broadcast('call::participantDeleted', data.uuid);
            });
        };

        rtc.onStageUpdate = function(stage) {
            $timeout(function() {
                $rootScope.$broadcast('call::stageUpdated', stage);
            });
        };

        this.startPresentationVideo = function() {
            $log.debug('Call.startPresentationVideo');
            rtc.getPresentation();
        };
        this.stopPresentationVideo = function() {
            $log.debug('Call.stopPresentationVideo');
            rtc.stopPresentation();
            $rootScope.$broadcast('call::presentationVideoUpdate', null);
            delete _this.presentationVideoSrc;
        };
        this.getPresentationUrl = function() {
            $rootScope.$broadcast('call::presentationUpdate', rtc.getPresentationURL());
        };
        rtc.onPresentation = function(isActive, presenter) {
            $timeout(function() {
                $log.debug('rtc.onPresentation', isActive, presenter);
                if (isActive) {
                    $rootScope.$broadcast('call::presentationStarted', presenter);
                    _this.presentationAcitve = true;
                } else {
                    delete _this.presentationAcitve;
                    if (typeof _this.screenShareMode === 'undefined') {
                        // suppress presentationStopped event when we have stolen presentation
                        $rootScope.$broadcast('call::presentationStopped');
                    }
                }
            });
        };
        rtc.onPresentationReload = function(src) {
            $timeout(function() {
                $log.debug('rtc.onPresentationReload', src);
                if (_this.presentationAcitve || _this.screenShareMode === 'screen') {
                    $rootScope.$broadcast('call::presentationUpdate', src);
                    _this.presentationImgSrc = $sce.trustAsResourceUrl(src);
                }
            });
        };
        rtc.onPresentationConnected = function(src) {
            $timeout(function() {
                $log.debug('rtc.onPresentationConnected', src);
                $rootScope.$broadcast('call::presentationVideoUpdate', src);
                _this.presentationVideoSrc = $sce.trustAsResourceUrl(src);
            });
        };
        rtc.onPresentationDisconnected = function(reason) {
            $timeout(function() {
                // Only called when we are receiving video presentation and remote side stop
                $log.debug('rtc.onPresentationDisconnected', reason);
                if (reason && reason.indexOf(': ') > 0) {
                    reason = reason.substr(reason.lastIndexOf(': ') + 2);
                }
                $rootScope.$broadcast('call::presentationVideoUpdate', null, reason);
                delete _this.presentationVideoSrc;
            });
        };

        this.startScreenShare = function() {
            $log.debug('Call.startScreenShare');
            this.screenShareMode = 'screen';
            rtc.present(this.screenShareMode);
        };
        this.stopScreenShare = function(src) {
            $log.debug('Call.stopScreenShare');
            delete _this.screenShareMode;
            delete _this.presentationImgSrc;
            delete _this.presentationVideoSrc;
            rtc.present(null);
        };
        var screenshareCallback = null;
        rtc.onScreenshareConnected = function(src) {
            $timeout(function() {
                $log.debug('rtc.onScreenshareConnected', src);
                // rtc.onPresentation(false, null) is not called when we steal presentation,
                // so make sure remote presentation is manually set to inactive.
                delete _this.presentationAcitve;
                $translate('IDS_PRESENTATION_NAME_SELF', {
                        displayName: displayName
                    })
                    .then(function(name) {
                        _this.presentationName = name;
                        _this.presentationMaximized = false;
                        if (!_this.remoteMediaStream || rtc.call_type === 'audioonly') {
                            _this.presentationMaximized = true;
                        }
                        // _this.presentationVideoSrc = $sce.trustAsResourceUrl(src);
                    });
                if (screenshareCallback) {
                    screenshareCallback();
                }
                screenshareCallback = null;
            });
        };
        rtc.onScreenshareStopped = function(reason) {
            $timeout(function() {
                $log.debug('rtc.onScreenshareStopped', reason);
                delete _this.screenShareMode;
                if (reason !== rtc.trans.ERROR_SCREENSHARE_CANCELLED) {
                    delete _this.presentationImgSrc;
                    delete _this.presentationVideoSrc;
                }
                $rootScope.$broadcast('call::screenshareStopped', reason);
            });
        };
        rtc.onScreenshareMissing = function() {
            $timeout(function() {
                $log.debug('rtc.onScreenshareMissing');
                rtc.onScreenshareStopped();
                $rootScope.$broadcast('call::screenShareMissing');
            });
        };
        this.imageShareStart = function(cb) {
            $log.debug('Call.imageShareStart');
            screenshareCallback = cb;
            this.screenShareMode = 'screen_http';
            rtc.present(this.screenShareMode);
            $translate('IDS_PRESENTATION_NAME_SELF', {
                    displayName: displayName
                })
                .then(function(name) {
                    _this.presentationName = name;
                    _this.presentationMaximized = false;
                    if (!_this.remoteMediaStream) {
                        _this.presentationMaximized = true;
                    }
                });
        };
        this.imageShareSetImage = function(dataURL) {
            $log.debug('Call.imageShareSetImage');
            rtc.sendPresentationImage({
                files: [dataURL]
            });
        };

        this.setRole = function(uuid, role) {
            return rtc.setRole(uuid, role);
        };

        this.getCallStatistics = function(callback) {
            if (rtc.call.getMediaStatistics) {
                return rtc.call.getMediaStatistics();
            } else {
                return rtc.getMediaStatistics();
            }
        };

        rtc.onCallTransfer = function(alias) {
            $timeout(function() {
                $log.log('rtc.onCallTransfer: ' + alias);
                $rootScope.$broadcast('call::transfer', alias);
            });
        };

        rtc.onMicActivity = function() {
            $timeout(function() {
                $rootScope.$broadcast('call::onMicActivity');
            });
        };

        rtc.onChatMessage = function(message) {
            $timeout(function() {
                message.timestamp = Date.now();
                $rootScope.$broadcast('call:chatMessageReceived', message);
            });
        };
        this.sendChatMessage = function(message) {
            rtc.sendChatMessage(message);
        };

        this.toggleLock = function() {
            this.locked = !this.locked;
            rtc.setConferenceLock(this.locked);
        };
        this.toggleMicrophone = function() {
            this.microphoneMuted = rtc.muteAudio();
        };
        this.toggleCamera = function() {
            this.cameraMuted = rtc.muteVideo();
            return this.cameraMuted;
        };
        this.toggleMuteAllGuests = function() {
            this.guestsMuted = !this.guestsMuted;
            rtc.setMuteAllGuests(this.guestsMuted);
        };
        this.sendDTMF = function(data, uuid) {
            rtc.sendDTMF(data, uuid);
        };
        this.fecc_timer = undefined;
        this.sendFECC = function(action, axis, direction, uuid) {
            rtc.sendFECC(action, axis, direction, uuid, 1000);
            if (action === "start" || (action === "continue" && _this.fecc_timer)) {
                _this.fecc_timer = setTimeout(function() {
                    _this.sendFECC("continue", axis, direction, uuid);
                }, 500);
            }
            if (action === "stop" && _this.fecc_timer) {
                clearTimeout(_this.fecc_timer);
                _this.fecc_timer = undefined;
            }
        };
        this.startConference = function() {
            rtc.startConference();
        };
        this.disconnectAll = function() {
            rtc.disconnectAll();
        };
        this.currentServiceType = function() {
            return rtc.current_service_type;
        };
        this.flashToggleSelfview = function() {
            return rtc.call.toggleSelfview();
        };
        this.disconnect = function() {
            $log.debug('Call.disconnect');
            delete _this.presentationVideoSrc;
            try {
                rtc.present(null);
                rtc.stopPresentation();
                rtc.disconnectCall();
                rtc.disconnect();
            } catch (e) {
                $log.error('Failed to disconnect pexrtc', e);
            }
        };

        if (token) {
            rtc.oneTimeToken = token;
        }

        if (registration_token) {
            rtc.registration_token = registration_token;
        }

        if (applicationSettings.turnServer) {
            rtc.turn_server = applicationSettings.turnServer;
        }

        if (applicationSettings.screenshareApi) {
            rtc.screenshare_api = applicationSettings.screenshareApi;
        }

        rtc.makeCall(host, alias, displayName, null, 'none');
    };
});
