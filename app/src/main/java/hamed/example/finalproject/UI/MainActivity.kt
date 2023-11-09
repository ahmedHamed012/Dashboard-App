package hamed.example.finalproject.UI
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hamed.example.finalproject.Models.Users
import hamed.example.finalproject.R
import java.lang.reflect.Type
class MainActivity : AppCompatActivity() {
  lateinit var nameText:TextView
  lateinit var emailText :TextView
  lateinit  var phoneNumber:TextView
  lateinit  var password :TextView
  lateinit  var result : TextView
    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameText = findViewById(R.id.nameText)
        emailText  = findViewById(R.id.emailText)
        phoneNumber = findViewById(R.id.phone)
        password = findViewById(R.id.passText)
        var btn = findViewById<Button>(R.id.btnReg)
        result = findViewById(R.id.result)
        val listOfUsers = ArrayList<Users>()
        btn.setOnClickListener {
            if(nameText.text.toString() == ""||emailText.text.toString()== ""||phoneNumber.text.toString()== ""||password.text.toString()== ""){
                result.visibility = View.VISIBLE
                result.text = "There are some fields is empty"
            }
            else {
                val dataList:Users = Users(
                    nameText.text.toString(),
                    emailText.text.toString(),
                    phoneNumber.text.toString(),
                    password.text.toString()
                );
                SaveUsersList(listOfUsers,dataList)
                ReadNumberOfUsersList();
                var intent:Intent = Intent(this,AddProduct::class.java);
                intent.putExtra("Username",nameText.text.toString());
                startActivity(intent);
            }
        }
        ReadNumberOfUsersList()
    }
    private fun SaveUsersList(listOfUsers: ArrayList<Users>,data:Users) {
        listOfUsers+=data
        val sharedPrefrence: SharedPreferences = getSharedPreferences(
            "sharedPrefrence",
            MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = sharedPrefrence.edit();
        val gson = Gson()
        val json = gson.toJson(listOfUsers);
        editor.putString("UsersList", json);
        editor.apply();
    }
    private fun ReadNumberOfUsersList() {
        val sharedPrefrence: SharedPreferences = getSharedPreferences(
            "sharedPrefrence",
            MODE_PRIVATE
        );
        val gson = Gson()
        val json = sharedPrefrence.getString("UsersList", null)
        val type: Type = object : TypeToken<ArrayList<Users>>() {}.type
        val arrayList: List<Users> = gson.fromJson<List<Users>>(json, type)
//        result.visibility = View.VISIBLE
//        var finalNames : String = "";
//        for (user in arrayList) {
//            finalNames += "${user.name}, "
//        }
//        result.text = finalNames;
    }
}