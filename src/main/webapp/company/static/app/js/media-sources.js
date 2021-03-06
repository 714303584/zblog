'use strict';
/* global $ */

if (window.AudioContext) {
    var audioContext = new window.AudioContext();
    var analyser = audioContext.createAnalyser();
    analyser.fftSize = 1024;
    analyser.smoothingTimeConstant = 0.5;
}

angular.module('pexapp')

.controller('UserMediaPreviewController', function($scope, $rootScope, $localStorage, $sce, $timeout, $interval, $log, flashVideo, platformSettings) {
    navigator.getUserMedia = (navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia);

    var mediaStreamSource;
    var audioInterval;

    var flashMicrophoneTimeout;
    var flashMicrophoneInterval;
    var flashElement;

    function getFlashUserMedia() {
        flashElement.setSelfviewRatio(1);
        flashElement.initCamera($localStorage.cameraSourceId);
        flashElement.initMicrophone($localStorage.microphoneSourceId);
        if (!$scope.params.audioonly) {
            flashElement.showSelfview();
        }
    }

    $log.debug('Creating handler for flash::ready');
    var offFlashReady = $rootScope.$on('flash::ready', function() {
        /* global swfobject */
        $log.debug('flash::ready');
        flashElement = swfobject.getObjectById($scope.flashPreviewId);
        $log.debug('Flash Preview Element', $scope.flashPreviewId, flashElement);

        getFlashUserMedia();

        $scope.mediaSources = {
            audio: [],
            video: []
        };
        angular.forEach(flashElement.enumerateVideoSources(), function(label, id) {
            $scope.mediaSources.video.push({
                id: id,
                label: label,
                kind: 'video'
            });
        });
        angular.forEach(flashElement.enumerateAudioSources(), function(label, id) {
            if (label !== 'Default') {
                $scope.mediaSources.audio.push({
                    id: id,
                    label: label,
                    kind: 'audio'
                });
            }
        });

        flashMicrophoneInterval = $interval(function() {
            try {
                var mediaStats = flashElement.getMediaStatistics();
                $scope.microphoneVolume = mediaStats.Outgoing.Audio.Level;
            } catch (e) {
                $log.debug(e);
                $interval.cancel(flashMicrophoneInterval);
            }
        }, 500);

        offFlashReady();
    });

    function releaseUserMedia() {
        $log.debug('calling releaseUserMedia()');
        delete $scope.getUserMediaError;
        $scope.mediaSources = {
            audio: [],
            video: []
        };

        $log.debug('Stopping WebRTC audio interval');
        $interval.cancel(audioInterval);
        $scope.microphoneVolume = 0;

        if (mediaStreamSource) {
            mediaStreamSource.disconnect();
            mediaStreamSource = null;
        }

        if (analyser) {
            analyser.disconnect();
        }

        if ($scope.localMediaStream) {
            $log.debug('Stopping localMediaStream');
            var tracks = $scope.localMediaStream.getTracks();
            for (var i = 0; i < tracks.length; i++) {
                tracks[i].stop();
            }
        }

        if (flashElement) {
            try {
                flashElement.hideSelfview();
            } catch (e) {
                console.log('Error hiding selfview in flash element');
            }
        }
    }

    function gotSources(sources) {
        $timeout(function() {
            $scope.mediaSources.audio = [];
            $scope.mediaSources.video = [];
            $scope.mediaSources.output = [];

            angular.forEach(sources, function(source) {
                if (source.label !== 'Default') {
                    switch (source.kind) {
                        case 'audio':
                        case 'audioinput':
                            $scope.mediaSources.audio.push({
                                id: source.id || source.deviceId,
                                kind: 'audio',
                                label: source.label || 'Microphone ' + ($scope.mediaSources.audio.length + 1)
                            });
                            break;
                        case 'video':
                        case 'videoinput':
                            $scope.mediaSources.video.push({
                                id: source.id || source.deviceId,
                                kind: 'video',
                                label: source.label || 'Camera ' + ($scope.mediaSources.video.length + 1)
                            });
                            break;
                        case 'audiooutput':
                            if ($scope.edge_ver == 0) {
                                $scope.mediaSources.output.push({
                                    id: source.id || source.deviceId,
                                    kind: 'output',
                                    label: source.label || 'Output ' + ($scope.mediaSources.output.length + 1)
                                });
                            }
                            break;
                    }
                }
            });

            $scope.mediaSources.video.push({
                id: false,
                kind: 'video',
                label: 'IDS_SETTINGS_CAMERA_NONE'
            });

            $scope.mediaSources.audio.push({
                id: false,
                kind: 'audio',
                label: 'IDS_SETTINGS_MICROPHONE_NONE'
            });

            $log.debug('mediaSources', $scope.mediaSources);
        });
    }

    function getMediaSources() {
        $log.log('getMediaSources');

        function enumerateDevicesError(error) {
            $timeout(function() {
                $log.error('enumerateDevices failed:', error);
                $scope.getUserMediaError = 'IDS_ESCALATE_ACCESS_ERROR';
            });
            gotSources([]);
        }

        if (($scope.chrome_ver > 49 || $scope.edge_ver > 10527) && navigator.mediaDevices.enumerateDevices) {
            try {
                navigator.mediaDevices.enumerateDevices()
                    .then(gotSources)
                    .catch(enumerateDevicesError);
            } catch (e) {
                enumerateDevicesError(e);
            }
        } else {
            var getSources = window.MediaStreamTrack.getSources || function(callback) {
                // callback(localMediaStream.getVideoTracks().concat(localMediaStream.getAudioTracks()));
                callback([]);
            };
            getSources(gotSources);
        }
    }

    function getWebRtcUserMedia() {
        function getUserMediaError(err) {
            $log.error('getUserMedia failed:', err.name, err.toString());
            gotSources([]);
            $timeout(function() {
                // $scope.getUserMediaError = err.name || err.toString();
                $scope.getUserMediaError = 'IDS_ESCALATE_ACCESS_ERROR';
                $localStorage.microphoneSourceId = false;
                $localStorage.cameraSourceId = false;
            });
        }

        var constraints = {};

        if (audioContext && angular.isFunction(audioContext.resume)) {
            audioContext.resume();
        }

        if ($localStorage.cameraSourceId !== false) {
            if ($scope.chrome_ver > 49 && $localStorage.cameraSourceId) {
                constraints.video = {
                    mandatory: {
                        sourceId: $localStorage.cameraSourceId
                    },
                    optional: [{
                        minWidth: 320
                    }, {
                        minWidth: 640
                    }, {
                        minWidth: 1024
                    }, {
                        minWidth: 1280
                    }]
                };
            } else if ($scope.firefox_ver > 43 || $scope.edge_ver > 10527) {
                constraints.video = {
                    deviceId: $localStorage.cameraSourceId,
                    width: {
                        min: 320,
                        ideal: 1024,
                        max: 1280
                    }
                };
            } else {
                constraints.video = {
                    optional: [{
                        sourceId: $localStorage.cameraSourceId
                    }, {
                        minWidth: 320
                    }, {
                        minWidth: 640
                    }, {
                        minWidth: 1024
                    }, {
                        minWidth: 1280
                    }]
                };
            }
        }
        if ($localStorage.microphoneSourceId !== false) {
            if ($scope.chrome_ver > 49 && $localStorage.microphoneSourceId) {
                constraints.audio = {
                    mandatory: {
                        sourceId: $localStorage.microphoneSourceId
                    }
                };
            } else if ($scope.firefox_ver > 43 || $scope.edge_ver > 10527) {
                constraints.audio = {
                    deviceId: $localStorage.microphoneSourceId
                };
            } else {
                constraints.audio = {
                    optional: [{
                        sourceId: $localStorage.microphoneSourceId
                    }]
                };
            }
        }

        $log.debug('getUserMedia', constraints);
        if (!constraints.video && !constraints.audio) {
            getMediaSources();
            $scope.videoStreamName = 'IDS_SETTINGS_CAMERA_NONE';
            $scope.audioStreamName = 'IDS_SETTINGS_MICROPHONE_NONE';
        } else {
            try {
                navigator.getUserMedia(constraints,
                    function(localMediaStream) {
                        $timeout(function() {
                            var url = window.URL || window.webkitURL || window.mozURL;
                            $scope.localMediaStream = localMediaStream;
                            $scope.localMediaStreamURL = $sce.trustAsResourceUrl(url.createObjectURL($scope.localMediaStream));
                            $log.debug('gotUserMedia', $scope);
                        });

                        getMediaSources();

                        var videoTracks = localMediaStream.getVideoTracks();
                        $scope.videoStreamName = videoTracks.length ? videoTracks[0].label.replace(/\(.*\)/, '') || 'IDS_SETTINGS_CAMERA_DEFAULT' : 'IDS_SETTINGS_CAMERA_NONE';
                        var audioTracks = localMediaStream.getAudioTracks();
                        $scope.audioStreamName = audioTracks.length ? audioTracks[0].label || 'IDS_SETTINGS_MICROPHONE_DEFAULT' : 'IDS_SETTINGS_MICROPHONE_NONE';

                        if (window.AudioContext) {
                            try {
                                mediaStreamSource = audioContext.createMediaStreamSource(localMediaStream);
                                mediaStreamSource.connect(analyser);

                                $log.debug('Creating WebRTC audio interval');

                                audioInterval = $interval(function() {
                                    if (audioContext.state === 'suspended') {
                                        audioContext.resume();
                                    }
                                    var array = new Uint8Array(analyser.frequencyBinCount);
                                    analyser.getByteFrequencyData(array);
                                    var values = 0;

                                    var length = array.length;
                                    for (var i = 0; i < length; i++) {
                                        values += array[i];
                                    }
                                    $timeout(function() {
                                        $scope.microphoneVolume = (values / length);
                                    });
                                }, 100);

                                analyser.disconnect();
                            } catch (e) {
                                $log.debug('Failed to create media stream source for audio context', e);
                            }
                        }
                    },
                    getUserMediaError
                );
            } catch (e) {
                getUserMediaError(e);
            }
        }
    }

    $scope.previewUserMedia = function() {
        if (platformSettings.hasWebRTC) {
            releaseUserMedia();
            $timeout(getWebRtcUserMedia, 500);
        } else if (flashElement) {
            getFlashUserMedia();
        }
    };

    $scope.previewUserMedia();

    $scope.$on('$destroy', function(event) {
        $interval.cancel(flashMicrophoneInterval);
        offFlashReady();
        releaseUserMedia();
        if (audioContext && angular.isFunction(audioContext.suspend)) {
            audioContext.suspend();
        }
    });
})

.directive('muted', function() {
    return {
        link: function(scope, element, attrs) {
            element[0].muted = true;
        }
    };
});
