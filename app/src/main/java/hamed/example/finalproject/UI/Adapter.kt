package hamed.example.finalproject.UI

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hamed.example.finalproject.Models.LocalRepoImp
import hamed.example.finalproject.Models.OnProductListener
import hamed.example.finalproject.Models.ProductDatabase
import hamed.example.finalproject.Models.Products
import hamed.example.finalproject.R

class Adapter(var productsList:List<Products>, var context:Activity, var mylistener: OnProductListener) :RecyclerView.Adapter<Adapter.productsViewHolder>() {
    val db: ProductDatabase = ProductDatabase.getInstance(context);
    var localRepoImp: LocalRepoImp = LocalRepoImp(db);
    class productsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pdctName:TextView = itemView.findViewById(R.id.product_name)
        var quantity : TextView = itemView.findViewById(R.id.quantity)
        var unitPrice : TextView = itemView.findViewById(R.id.unit_price)
        var totalPrice : TextView = itemView.findViewById(R.id.total_price)
        var deleteBtn : ImageView = itemView.findViewById(R.id.deletebtn);
    }
    fun setValues(values:List<Products?>) {
        productsList = values as List<Products>;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productsViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.product_layout,parent,false)
        return productsViewHolder(view);
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: productsViewHolder, position: Int) {
        var product :Products = productsList.get(position)
        holder.pdctName.text = product.title
        holder.quantity.text = product.Quantity.toString()
        holder.unitPrice.text = product.price.toString()
        var total = product.Quantity.toString().toInt() * product.price.toString().toInt();
        holder.totalPrice.text = total.toString()
        holder.deleteBtn.setOnClickListener {
            mylistener.onClick(product);
        }
    }
}