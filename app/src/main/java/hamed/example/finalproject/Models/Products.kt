package hamed.example.finalproject.Models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "Product")
//data class Products(
//    @PrimaryKey(autoGenerate = true)
//    var id : Int,
//    val productName:String,
//    val productQnty:String,
//    val unitPrice:String,
//    val totalPrice:String
//)
@Entity(tableName = "Product")
class Products(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int ,
    var title: String?, var price: Int, var Quantity:Int,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(price)
        parcel.writeInt(Quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Products> {
        override fun createFromParcel(parcel: Parcel): Products {
            return Products(parcel)
        }

        override fun newArray(size: Int): Array<Products?> {
            return arrayOfNulls(size)
      }
    }
}