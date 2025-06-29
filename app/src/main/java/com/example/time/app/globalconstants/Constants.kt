package com.example.time.app.globalconstants

object Constants {

    // Time
    const val ONE_SECOND = 1000L
    const val ONE_MINUTE = 60_000L

    // Error code
    const val NETWORK_ERROR = -2
    const val NO_INTERNET = -1
    const val SUCCESS = 200
    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val REQUEST_TIMEOUT = 408
    const val TOO_MANY_REQUESTS = 429
    const val INTERNAL_SERVER_ERROR = 500

    // Alarm
    const val ALARM_CHANEL_ID = "ALARM_CHANEL_ID"

    // Intent
    const val EXTRA_ALARM_ID = "EXTRA_ALARM_ID"
    const val EXTRA_SOUND_URI = "EXTRA_SOUND_URI"
    const val EXTRA_ALARM_NAME = "EXTRA_ALARM_NAME"

}