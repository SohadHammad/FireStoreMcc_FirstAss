package com.example.firestoremcc

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

var name2: EditText? = null
var number2: EditText? = null
var address2: EditText? = null
var buttonSave: Button? = null
var buttonShow: Button? = null

var db: FirebaseFirestore = FirebaseFirestore.getInstance()
private var progressDialog: ProgressDialog?=null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name2 = findViewById(R.id.userNames)
        number2 = findViewById(R.id.userPhone)
        address2 = findViewById(R.id.userAddress)
        buttonSave = findViewById(R.id.btn_save)
        buttonShow = findViewById(R.id.btn_show_data)

        buttonSave!!.setOnClickListener {
            // show Progress Dialog :
            showDialog()
            val contactName: String = name2!!.text.toString()
            val contactNumber: String = number2!!.text.toString()
            val contactAddress: String = address2!!.text.toString()
             //
            UserData(contactName,contactNumber,contactAddress)
            name2!!.text.clear()
            number2!!.text.clear()
            address2!!.text.clear()
        }
        buttonShow!!.setOnClickListener {
            var i = Intent(this,ShowUsers::class.java)
            startActivity(i)
        }
    }

    fun UserData(name:String, number:String, address:String){
        val datas: MutableMap<String, Any> = HashMap()
        datas["contact_name"] = name
        datas["contact_number"] = number
        datas["contact_address"] = address

        db.collection("contacts")
                .add(datas)
                .addOnSuccessListener {
                    hiddenDialog()
                }
                .addOnFailureListener {
                    hiddenDialog()
                }
    }

    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Adding contact ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hiddenDialog(){
        if(progressDialog!!.isShowing){
            progressDialog!!.dismiss()
        }

    }


}