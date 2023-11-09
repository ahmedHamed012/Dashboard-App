package hamed.example.finalproject.Models

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepoImp(private val db:ProductDatabase):LocalRepo {
    override suspend fun addProduct(product: Products) {
        withContext(Dispatchers.IO) {
            db.getProductDao().addProduct(product)
        }
    }

    override suspend fun getProducts(): List<Products> =
        withContext(Dispatchers.IO){ db.getProductDao().getProducts()
    }

    override suspend fun deleteProduct(product: Products) {
        withContext(Dispatchers.IO) {
            db.getProductDao().deleteProduct(product);
        }
    }
}