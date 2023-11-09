package hamed.example.finalproject.Models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "ProductsDatabase"
//@Database(entities = [Products::class], version = 1)
//abstract class ProductDatabase:RoomDatabase() {
//abstract fun productDao():ProductsDAO
//companion object {
//    @Volatile
//    private var instance : ProductDatabase?= null
//    fun getInstance(context:Context):ProductDatabase{
//        return instance?: synchronized(Any()){
//            instance?:buildDataBase(context).also{ instance = it}
//        }
//    }
//
//    private fun buildDataBase(context: Context): ProductDatabase {
//        return Room.databaseBuilder(context,ProductDatabase::class.java, DATABASE_NAME).build()
//
//    }
//}
//}
@Database(entities = arrayOf(Products::class), version = 2)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun getProductDao():ProductsDAO
    companion object{
        @Volatile
        private var INSTANCE:ProductDatabase?=null
        fun getInstance(context: Context):ProductDatabase{
            val instance= Room.databaseBuilder(context.applicationContext,ProductDatabase::class.java,DATABASE_NAME)
                .build()
            INSTANCE=instance
            return instance
        }
    }
}