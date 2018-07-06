import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun main(args: Array<String>) {
    val myModelList: List<MyModel> = listOf(
            MyModel(1.0, 1.0),
            MyModel(2.0, 2.0)
    )

    try {
        println(Deps.moshi.toJson(myModelList))
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

object Deps {
    val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}

fun <T: Any> Moshi.toJson(obj: T): String {
    val adapter = this.adapter<T>(obj::class.java)
    return adapter.toJson(obj)
}

@JsonClass(generateAdapter = true)
data class MyModel(val a : Double, val b : Double)