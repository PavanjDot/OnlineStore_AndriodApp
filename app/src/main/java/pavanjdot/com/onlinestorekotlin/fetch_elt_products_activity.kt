package pavanjdot.com.onlinestorekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class fetch_elt_products_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_elt_products_activity)

        val selectedBrand = intent.getStringExtra("BRAND")

        var ProductsList = ArrayList<EProduct>()

        var productsURL = "http://192.168.1.100/onlineStoreApp/fetch_electronics_product.php?brands=$selectedBrand"

        val requestQ = Volley.newRequestQueue(this@fetch_elt_products_activity)

        val jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null, Response.Listener
        {
                response ->

            for(productJOIndex in 0.until(response.length())){

                ProductsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"), response.getJSONObject(productJOIndex).getString("name"), response.getJSONObject(productJOIndex).getInt("price"), response.getJSONObject(productJOIndex).getString("picture")))

            }




        }, Response.ErrorListener { 
            
            error ->

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })




    }
}
