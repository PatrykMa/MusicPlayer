package com.example.patryk.musicplayer.player_component

class Song() {
    constructor(id:Long,title:String,author:String):this()
    {
        this.title=title
        this.ID=id
        this.author=author
    }
    var ID:Long=0
    var title=""
    var author=""
}