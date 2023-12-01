package schedule.config

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import schedule.service.StudentService

object RetrofitManager {
    private val studentHost = System.getenv("STUDENT_HOST") ?: "localhost"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://$studentHost:8000")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    private val service = retrofit.create(StudentService::class.java)

    fun getStudentService(): StudentService = service
        ?: throw Exception("The student service is not available")
}
