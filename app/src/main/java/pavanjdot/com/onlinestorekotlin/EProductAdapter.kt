package pavanjdot.com.onlinestorekotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class EProductAdapter(var context: Context, var arrayList: ArrayList<EProduct>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


    }

    override fun getItemCount(): Int {

        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
     }


}