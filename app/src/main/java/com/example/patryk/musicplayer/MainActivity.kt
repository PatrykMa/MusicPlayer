package com.example.patryk.musicplayer

import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.lang.Exception
import android.content.Intent



class MainActivity : AppCompatActivity(),IPlayView {

    override var author: String
        get() = findViewById<TextView>(R.id.textView_author).text.toString()
        set(value) {findViewById<TextView>(R.id.textView_author).text=value}

    override var tiltle: String
        get() = findViewById<TextView>(R.id.textView_title).text.toString()
        set(value) {findViewById<TextView>(R.id.textView_title).text=value}

    override var loop: Boolean
        get() = findViewById<Switch>(R.id.switch_loop).isChecked
        set(value) {findViewById<Switch>(R.id.switch_loop).isChecked=value}
    override var randomly: Boolean
        get() = findViewById<Switch>(R.id.switch_rand).isChecked
        set(value) {findViewById<Switch>(R.id.switch_rand).isChecked=value}
    override var context: Context
        get() = this
        set(value) {}
    override var currentTime: String
        get() = findViewById<TextView>(R.id.textView_curTime).text.toString()
        set(value) {findViewById<TextView>(R.id.textView_curTime).text=value}
    override var duration: String
        get() = findViewById<TextView>(R.id.textView_timeLeft).text.toString()
        set(value) {findViewById<TextView>(R.id.textView_timeLeft).text=value}

    lateinit var  songComander:SongComander




    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            songComander = SongComander(this)
            findViewById<Button>(R.id.button_play_pause).setOnClickListener {
                    songComander.onPlay()
            }
            findViewById<Switch>(R.id.switch_rand).setOnCheckedChangeListener{ buttonView, isChecked ->
                songComander.changeRandomly(isChecked)
            }
            findViewById<Switch>(R.id.switch_loop).setOnCheckedChangeListener{ buttonView, isChecked ->
                songComander.changeLoop(isChecked)
            }
            findViewById<Button>(R.id.button_next).setOnClickListener {
                songComander.onNext()
            }
            findViewById<Button>(R.id.button_prev).setOnClickListener {
                songComander.onPrewious()
            }
            findViewById<Button>(R.id.button_stop).setOnClickListener {
                stopService(Intent(baseContext, Services::class.java))
            }
        }
        catch (e:Exception){
            var x=0
        }
    }


}
