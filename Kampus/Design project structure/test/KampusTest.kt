import org.hyperskill.hstest.testcase.CheckResult
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication

interface KampusTest {

    fun appTest(test: suspend ApplicationTestBuilder.() -> Unit): CheckResult {
        var result: CheckResult = CheckResult.correct()
        testApplication {
            try {
                test()
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }
}
