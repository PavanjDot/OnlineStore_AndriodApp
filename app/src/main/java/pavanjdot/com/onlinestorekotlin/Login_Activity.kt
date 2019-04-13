package pavanjdot.com.onlinestorekotlin

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login_.*

class Login_Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)


        Login_Layout_btnSignup.setOnClickListener {

            var intent = Intent(this@Login_Activity, Signup_Activity::class.java)
            startActivity(intent)

        }

        Login_Layout_btnLogin.setOnClickListener {

            val LoginURL = "http://192.168.1.100/onlineStoreApp/login_app_user.php?email="+
                    Login_Layout_edtEmail.text.toString()+"&password="+
                    Login_Layout_edtPassword.text.toString()

            var RequestQ = Volley.newRequestQueue(this@Login_Activity)

            var stringRequest = StringRequest(Request.Method.GET, LoginURL, Response.Listener
            {
                    response ->

                if(response.equals("The user does Exist")){


                    Toast.makeText(this@Login_Activity, response,Toast.LENGTH_SHORT).show()

                    person.email = Login_Layout_edtEmail.text.toString()

                    val intent = Intent(this@Login_Activity, HomeScreen::class.java)
                    startActivity(intent)
                }else{

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()

                }



            }, Response.ErrorListener
            {
                    error ->



                alertBuilder(error.message)




            })

            RequestQ.add(stringRequest)


        }
    }

    public fun alertBuilder(usermessage: String?){

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(usermessage)
        dialogBuilder.create().show()

    }
}
