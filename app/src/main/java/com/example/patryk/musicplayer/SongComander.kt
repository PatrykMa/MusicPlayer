package com.example.patryk.musicplayer

import com.example.patryk.musicplayer.player_component.Player
import com.example.patryk.musicplayer.player_component.SongManager
import java.lang.Exception
import android.R.string.cancel
import android.support.annotation.MainThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class SongComander(var playView:IPlayView) {
    var songManager:SongManager
    lateinit var player:Player


    init {
        songManager= SongManager(playView.context)
        player=Player(playView.context)
        player.songList=songManager.songs
        player.playedSong=player.songList[0]
        player.onStart={
            playView.author=player.playedSong!!.author
            playView.tiltle=player.playedSong!!.title
            playView.duration=convertTimeFromMiliToString(player.duration)
        }

            doAsync {
                while (true){
            try {
                if (player.isPlaing) {

                    uiThread {
                        playView.currentTime = convertTimeFromMiliToString(player.currentPosition)
                    }
                }
                Thread.sleep(1000)
            }catch (e:Exception)
            {
                var x=0
            }}}

    }

    fun onStop()
    {
        player.stop()
    }
    fun onPlay()
    {
        if(player.isPlaing)
            player.pause()
        else player.play()
    }
    fun onPause()
    {
        if(player.isPlaing)
            player.pause()
        else player.play()
    }
    fun changeLoop(to:Boolean)
    {
        try{
            player.loop=to
            playView.loop=player.loop
        }
        catch (e:Exception){}
    }

    fun changeRandomly(to:Boolean)
    {
        try{
            player.randomly=to
            playView.randomly=player.randomly
        }
        catch (e:Exception){}
    }
    private fun onStart()
    {
        playView.author=player.playedSong!!.author
        playView.tiltle=player.playedSong!!.title
        playView.duration=player.duration.toString()
    }
    fun onPrewious()
    {
        player.previous()
    }
    fun onNext()
    {
        player.next()
    }

    private fun convertTimeFromMiliToString(time:Int):String
    {
        var w=""
        w+= if(time/360000>0)(time/3600_000).toInt().toString() + ":" else ""
        w+= ((time%3600_000)/60_000).toString() + ":"
        w+= (((time%3600_000)%60_000)/1000).toString()
        return w

    }

}