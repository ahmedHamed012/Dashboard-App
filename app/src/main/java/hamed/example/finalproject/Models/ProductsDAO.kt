package hamed.example.finalproject.Models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product:Products)
    @Query("select * from Product")
    fun getProducts():List<Products>
    @Delete
    suspend fun deleteProduct(product: Products)

}