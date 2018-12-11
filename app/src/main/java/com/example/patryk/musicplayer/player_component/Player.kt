package com.example.patryk.musicplayer.player_component

import android.content.ContentUris
import android.content.Context
import android.media.MediaPlayer
import java.lang.Exception
import java.util.*
import android.support.v4.os.HandlerCompat.postDelayed




class Player(var context: Context) {
    var songList:Array<Song> = arrayOf()
    var playedSong:Song?=null
    var loop:Boolean=false
    var currentPosition:Int
        get() = try{mediaplayer!!.currentPosition}catch (e:Exception){0}
        private set(value) {}
    var duration:Int
        get() = try{mediaplayer!!.duration}catch (e:Exception){0}
        private set(value) {}
    var randomly:Boolean=false
    var isPlaing:Boolean
            get()=try{mediaplayer!!.isPlaying}catch (e:Exception){false}
            set(value) {}
    private var actualSong:Int=0
    private var mediaplayer:MediaPlayer?=null
    private var stopedAt:Int=0
    var onEnd: () -> Unit={}
    var onStart : () -> Unit={}


    fun play()
    {

        if (mediaplayer==null)
        {

            mediaplayer= MediaPlayer.create(context, ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                playedSong!!.ID)).apply { loop=false }
            mediaplayer?.setOnCompletionListener {
                onEnd()
                nextSong()
                mediaplayer?.reset()
                mediaplayer?.setDataSource(context,ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    playedSong!!.ID))
                mediaplayer?.prepare()
                onStart()
                mediaplayer?.start()

            }
            onStart()
            mediaplayer?.start()
            mediaplayer?.currentPosition
        }
        else if (mediaplayer?.isPlaying !=true)
        {
            mediaplayer?.seekTo(stopedAt)
            mediaplayer?.start()
        }
    }

    fun stop()
    {
        mediaplayer?.stop()
        mediaplayer=null
    }
    private fun nextSong()
    {
        //toDo loop nalezy sprawdzić czy ta piosenka była juz grana
        if(randomly){
            actualSong=Random().nextInt(songList.size)
        }
        else {
            actualSong++
            if (actualSong >= songList.size)
                actualSong = 0
        }
        playedSong=songList[actualSong]
    }

    fun pause(){
        stopedAt=currentPosition
        mediaplayer?.pause()
    }

    fun next()
    {
        onEnd()
        mediaplayer?.stop()
        nextSong()
        mediaplayer?.reset()
        mediaplayer?.setDataSource(context,ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            playedSong!!.ID))
        mediaplayer?.prepare()
        onStart()
        mediaplayer?.start()

    }
    fun previous()
    {

        onEnd()
        mediaplayer?.stop()
        previousSong()
        mediaplayer?.reset()
        mediaplayer?.setDataSource(context,ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            playedSong!!.ID))
        mediaplayer?.prepare()
        onStart()
        mediaplayer?.start()

    }
    private fun previousSong()
    {
        //toDo loop nalezy sprawdzić czy ta piosenka była juz grana
        if(randomly){
            actualSong=Random().nextInt(songList.size)
        }
        else {
            actualSong--
            if (actualSong <= -1)
                actualSong = songList.size-1
        }
        playedSong=songList[actualSong]
    }



}