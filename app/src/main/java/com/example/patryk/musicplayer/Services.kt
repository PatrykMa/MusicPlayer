package com.example.patryk.musicplayer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast



class Services(): Service() {
    //var view:IPlayView
    var songComander:SongComander?=null
    // Binder given to clients
    private val mBinder = LocalBinder()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        return Service.START_STICKY
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): Services = this@Services
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}