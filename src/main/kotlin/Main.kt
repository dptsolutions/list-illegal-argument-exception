import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun main(args: Array<String>) {
    val myModelList: List<MyModel> = listOf(
            MyModel("ListModel1"),
            MyModel("ListModel2")
    )

    val myModel = MyModel("NotListModel")

    serialize(myModel)
    serialize(myModelList)
}

fun serialize(source: Any) {
    try {
        println(Deps.moshi.toJson(source))
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

object Deps {
    val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}

inline fun <reified T: Any> Moshi.toJson(obj: T): String {
    val adapter = this.adapter<T>(obj::class.java)
    return adapter.toJson(obj)
}

@JsonClass(generateAdapter = true)
data class MyModel(val str : String)