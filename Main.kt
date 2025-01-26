import kotlinx.coroutines.*
import kotlin.random.Random

data class Person(val name: String, val age: Int)

data class Weather(val city: String, val description: String, val temp: Int)

val personList = listOf(
    Person("Alice", 30),
    Person("Bob", 25),
    Person("Charlie", 35)
)

val weatherList = listOf(
    Weather("Moscow", "Sunny", 20),
    Weather("Saint Petersburg", "Cloudy", 15),
    Weather("Novosibirsk", "Rainy", 10)
)

val randoms = List(10) { Random.nextInt(1, 101) }

suspend fun <T> downloadData(data: List<T>): List<T> {
    delay(1000L)
    println("Скачанные данные: $data")
    return data
}

fun main() = runBlocking {
    val personDeferred = async { downloadData(personList) }
    val weatherDeferred = async { downloadData(weatherList) }
    val randomDeferred = async { downloadData(randoms) }

    personDeferred.await()
    weatherDeferred.await()
    randomDeferred.await()

    println("Данные загружены")
    println("Список людей: $personList")
    println("Список погоды: $weatherList")
    println("Список случайных чисел: $randoms")
    println("Программа завершена")
}