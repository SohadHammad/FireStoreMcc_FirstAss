package com.example.firestoremcc

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firestoremcc.Adapter.UserAdapter
import com.example.firestoremcc.modle.User

var rv_data_User: RecyclerView? = null
private var progressDialog: ProgressDialog?=null

class ShowUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_users)




        rv_data_User = findViewById(R.id.rv_User)
        showDialog()
        get_Data()
    }


    fun get_Data(){
        val contactList= mutableListOf<User>()
        db.collection("contacts")
            .get()


            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val contactName = data["contact_name"] as String?
                        val contactNumber = data["contact_number"] as String?
                        val contactAddress = data["contact_address"] as String?
                        contactList.add(User(id, contactName, contactNumber,contactAddress))
                    }


                    rv_data_User!!.layoutManager =
                        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    rv_data_User!!.setHasFixedSize(true)


                    val contactsAdapter = UserAdapter(this, contactList)
                    rv_data_User!!.adapter = contactsAdapter
                }

                hideDialog()
            }
    }



    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("View contacts ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }




    private fun hideDialog(){
        if(progressDialog!!.isShowing){
            progressDialog!!.dismiss()
        }
    }


}