package hamed.example.finalproject.UI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import hamed.example.finalproject.Models.LocalRepoImp
import hamed.example.finalproject.Models.OnProductListener
import hamed.example.finalproject.Models.ProductDatabase
import hamed.example.finalproject.Models.Products
import hamed.example.finalproject.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllItemsView : AppCompatActivity(),OnProductListener {
    var products:List<Products> = emptyList();
    lateinit var localRepoImp: LocalRepoImp;
    lateinit var recyclerView: RecyclerView;
    lateinit var listOfProducts: List<Products>;
    lateinit var userAdapter: Adapter;
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_items_view)
        recyclerView = findViewById(R.id.recyclerview)
        var products : List<Products> = emptyList();
        val db: ProductDatabase = ProductDatabase.getInstance(this);
        localRepoImp = LocalRepoImp(db)
        userAdapter = Adapter(products,this,this)
        recyclerView.adapter = userAdapter;
        lifecycleScope.launch(Dispatchers.IO) {
            listOfProducts = localRepoImp.getProducts();
            userAdapter.setValues(listOfProducts);
            userAdapter.notifyDataSetChanged();
        }
    }

    override fun onClick(product: Products) {
        lifecycleScope.launch(Dispatchers.IO) {
            localRepoImp.deleteProduct(product);
            listOfProducts = localRepoImp.getProducts();
            userAdapter.setValues(listOfProducts);
            userAdapter.notifyDataSetChanged();
        }
    }
}