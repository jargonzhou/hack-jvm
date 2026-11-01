import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import kotlin.test.assertTrue

// 12.1.1

const val TEST_TIMEOUT = 10L // compile-time constant

class MyTest {

    @Test
    @Timeout(TEST_TIMEOUT)
    fun test() {
        remove(1);
        assertTrue(1 + 1 == 2)
    }

    @Deprecated("Use removeAt(index) instead", ReplaceWith("removeAt(index)"))
    fun remove(index: Int) {
        println("removing $index")
    }

    fun removeAt(index: Int) {
        println("removing $index")
    }
}