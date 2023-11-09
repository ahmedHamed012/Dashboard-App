package hamed.example.finalproject.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import hamed.example.finalproject.Models.LocalRepoImp
import hamed.example.finalproject.Models.ProductDatabase
import hamed.example.finalproject.Models.Products
import hamed.example.finalproject.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddProduct : AppCompatActivity() {
    lateinit var welcome:TextView;
    lateinit var signout:TextView;
    lateinit var productName:TextView;
    lateinit var productQuantity:TextView;
    lateinit var productPrice:TextView;
    lateinit var addBtn : Button;
    lateinit var viewBtn:Button;
    lateinit var addresult : TextView;
    lateinit var localRepoImp: LocalRepoImp;
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        val db:ProductDatabase=ProductDatabase.getInstance(this);
        localRepoImp = LocalRepoImp(db);
        welcome = findViewById(R.id.welcometxt);
        signout = findViewById(R.id.signouttxt)
        var username = intent.extras!!.get("Username");
        welcome.text = "Welcome, $username";
        signout.setOnClickListener {
            var intent:Intent = Intent(this,MainActivity::class.java);
            startActivity(intent);
        }
        productName = findViewById(R.id.productname);
        productQuantity = findViewById(R.id.productqnty);
        productPrice = findViewById(R.id.productprice);
//        var productTotalPrice = productQuantity.text.toString().toInt() * productPrice.text.toString().toInt();
        addBtn = findViewById(R.id.addproduct);
        viewBtn = findViewById(R.id.viewproducts);
        addresult = findViewById(R.id.addresult);
        viewBtn.setOnClickListener {
            var intent:Intent = Intent(this,AllItemsView::class.java);
            startActivity(intent);
        }
        addBtn.setOnClickListener {
            if(productName.text.toString() == ""||productQuantity.text.toString()== ""||productPrice.text.toString()== ""){
                addresult.visibility = View.VISIBLE
                addresult.text = "There are some fields is empty"
            }
            else {
                var title = productName.text.toString()
                var priceInt = productPrice.text.toString().toInt()
                var quantityInt = productQuantity.text.toString().toInt()
                var myProduct = Products(0,title,priceInt,quantityInt);
                lifecycleScope.launch(Dispatchers.IO){
                    localRepoImp.addProduct(myProduct);
                }
                Toast.makeText(this, "Product inserted successfully", Toast.LENGTH_SHORT).show();
            }

        }

    }
}