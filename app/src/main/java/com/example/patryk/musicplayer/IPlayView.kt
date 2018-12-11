package com.example.patryk.musicplayer

import android.content.Context

interface IPlayView {
    var tiltle:String
    var author:String
    var context:Context
    var duration:String
    var currentTime:String
    var randomly:Boolean
    var loop:Boolean

}