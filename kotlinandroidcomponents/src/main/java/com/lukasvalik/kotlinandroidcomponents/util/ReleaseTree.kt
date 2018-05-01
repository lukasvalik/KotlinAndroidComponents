package com.lukasvalik.kotlinandroidcomponents.util

import android.util.Log
import timber.log.Timber

/**
 * helper class for timber library to configure release version logs
 */

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return when (priority) {
            Log.VERBOSE, Log.DEBUG, Log.INFO -> false
            else -> true
        }
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {

            // message is short enough, does not need to be broken down into chunks
            if (message.length < MAX_LOG_LENGTH) {

                /*// log errors to crash logging tool
                    if (priority == Log.ERROR && t != null) {
                        crashlytics.log(e)
                    }*/

                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }

            // split by line, then ensure each line can fit into Log maximum length
            val length = message.length
            var i = 0
            while (i < length) {
                var newLine = message.indexOf("\n", i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = Math.min(newLine, i + MAX_LOG_LENGTH)
                    val part = message.substring(newLine, i + MAX_LOG_LENGTH)
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < length)
            }
        }
    }

    companion object {
        private const val MAX_LOG_LENGTH = 4000
    }
}