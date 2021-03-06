package pavanjdot.com.onlinestorekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val brandsURL = "http://192.168.1.100/onlineStoreApp/fetch_brands.php"

        var brandsList = ArrayList<String>()

       val  RequestQ = Volley.newRequestQueue(this@HomeScreen)

        val jsonAR = JsonArrayRequest(Request.Method.GET, brandsURL, null, Response.Listener
        {
                response ->

            for(jsonObject in 0.until(response.length())){
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }
            var brandsListAdapter = ArrayAdapter(this@HomeScreen, R.layout.brandslinearlayout, brandsList)
            brandsListView.adapter = brandsListAdapter


        }, Response.ErrorListener
        {
                error ->

            alertBuilder(error.message)


        })


        RequestQ.add(jsonAR)

       brandsListView.setOnItemClickListener { parent, view, position, id ->


           val tappedBrand = brandsList.get(position)

           var intent =   Intent(this@HomeScreen, fetch_elt_products_activity::class.java)

           intent.putExtra("BRAND", tappedBrand)

           startActivity(intent)

       }

        var brandsListAdapter = ArrayAdapter(this@HomeScreen, android.R.layout.simple_list_item_1, brandsList)
        brandsListView.adapter = brandsListAdapter

    }

    public fun alertBuilder(usermessage: String?){

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(usermessage)
        dialogBuilder.create().show()

    }
}
