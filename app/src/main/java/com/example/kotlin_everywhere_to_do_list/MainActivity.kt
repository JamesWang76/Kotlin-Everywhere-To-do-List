package com.example.kotlin_everywhere_to_do_list

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isAddMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {
            if (isAddMode) addEvent()
            else Toast.makeText(this, "已經新增過了，不能再新增囉。", Toast.LENGTH_SHORT).show()
        }

        btn_edit.setOnClickListener {
            if (!isAddMode) editEvent()
            else Toast.makeText(this, "請先新增後才能編輯喔！", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addEvent(){
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun editEvent(){
        // Intent means an activity by user, a change from the original to another new
        // Intent(from, to) -> Ex: From this(MainActivity) to EditActivity
        // intent is just a variable, it would be enabling while call startActivityForResult()
        val intent = Intent(this, EditActivity::class.java)
        val event = tv_display.text

        //putExtra is for carrying some data from the old one to the new one
        intent.putExtra("event", event)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            tv_display.text = data?.getStringExtra("newEvent")
            isAddMode = false
        }
    }
}
