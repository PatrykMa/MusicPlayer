package com.example.patryk.musicplayer.player_component

import android.content.Context
import android.content.ContentResolver



class SongManager(var context: Context) {
    var songs:Array<Song> = arrayOf()
    private set
    init {
        getSongs()
    }

    private fun getSongs()
    {
        val musicResolver = context.contentResolver
        val musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor = musicResolver.query(musicUri, null, null, null, null)
        var songList= mutableListOf<Song>()
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            val titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
            val idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
            val artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST)
            //add songs to list
            do {
                val thisId = musicCursor.getLong(idColumn)
                val thisTitle = musicCursor.getString(titleColumn)
                val thisArtist = musicCursor.getString(artistColumn)
                songList.add(Song(thisId, thisTitle, thisArtist))
            } while (musicCursor.moveToNext())
        }
        songs=songList.toTypedArray()
    }
    fun refresh()
    {
        getSongs()
    }


}