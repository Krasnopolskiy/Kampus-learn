import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.hyperskill.hstest.testcase.CheckResult
import org.junit.Assert.assertEquals
import org.junit.Test

class Test: StageTest<Any>() {
    private val client = HttpClient()

    @DynamicTest
    fun `Student service is responding`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("http://localhost:8000")
                println(response)
                if (response.status != HttpStatusCode.OK)
                    result = CheckResult.wrong("The student service is not responding")
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }


    @DynamicTest
    fun `Student service returns correct string`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("http://localhost:8000")
                println(response)
                if (response.bodyAsText() != "Hello from StudentService!")
                    result = CheckResult.wrong("The student service is not responding")
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }


    @DynamicTest
    fun `Schedule service is responding`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("http://localhost:8001")
                println(response)
                if (response.status != HttpStatusCode.OK)
                    result = CheckResult.wrong("The student service is not responding")
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Schedule service returns correct string`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("http://localhost:8001")
                println(response)
                if (response.bodyAsText() != "Hello from ScheduleService!")
                    result = CheckResult.wrong("The schedule service is not responding")
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }
}
