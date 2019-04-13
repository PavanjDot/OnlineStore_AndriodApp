package pavanjdot.com.onlinestorekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.activity_signup_.*

class Signup_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_)

       Signup_Layout_btnLogin.setOnClickListener {

            finish()
        }

        Signup_Layout_btnSignup.setOnClickListener {

            if(Signup_Layout_edtPassword.text.toString().
                    equals(Signup_Layout_edtConfirmPassword.text.toString())){


            //Registration



                val email = Signup_Layout_edtEmail.text.toString()
                val username = Signup_Layout_edtUsername.text.toString()
                val password = Signup_Layout_edtPassword.text.toString()
                val confirm_password = Signup_Layout_edtConfirmPassword.text.toString()

                val signupURL = "http://192.168.1.100/onlineStoreApp/join_new_user.php?"+"email="+email+"&username="+username+"&password="+password

                val RequestQ = Volley.newRequestQueue(this@Signup_Activity)

                val stringRequest = StringRequest(Request.Method.GET, signupURL, Response.Listener {

                    response->

                    if(response.equals("A User with the same Email is Already exsits") ){

                        alertBuilder(response)
                    }else{
                        alertBuilder(response)

                        person.email = Signup_Layout_edtEmail.text.toString()

                        val intent = Intent(this@Signup_Activity, HomeScreen::class.java)
                        startActivity(intent)
                    }


                }, Response.ErrorListener {

                    error->


                    alertBuilder(error.message)
                })

               RequestQ.add(stringRequest)

            }else{
                alertBuilder("Password Mismatch")

            }

        }
    }

   public fun alertBuilder(usermessage: String?){

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(usermessage)
        dialogBuilder.create().show()

    }
}
