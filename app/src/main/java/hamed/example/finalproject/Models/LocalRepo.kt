package hamed.example.finalproject.Models
import hamed.example.finalproject.Models.Products

interface LocalRepo {
   suspend fun addProduct(product: Products)
   suspend fun getProducts():List<Products>
    suspend fun deleteProduct(product: Products)
}