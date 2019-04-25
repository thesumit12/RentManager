package com.example.common

import com.example.common.eventBus.RxBus
import com.example.common.utils.Event
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @brief: Test class to test events
 * @author: Sumit Lakra
 * @date: 04/27/2019
 */
class EventTest {

    @Test
    fun event_test() {
        val ev = Event<String>()
        var out = ""

        val del: Event<String>.(String) -> (Unit) = {r -> out = r}

        ev += del

        ev.invoke("TEST HELLO")
        assertTrue(out == "TEST HELLO")

        ev -= del

        ev.invoke("TEST_HELLO_2")
        assertTrue(out == "TEST HELLO")

        ev += del
        ev.invoke("TEST_HELLO_3")
        assertTrue(out == "TEST_HELLO_3")

        ev.removeAllhandlers()

        ev.invoke("TEST_HELLO_4")
        assertTrue(out == "TEST_HELLO_3")
    }

    @Test
    fun rxBusTest() {
        val instance = RxBus::class.java
        Assert.assertSame(instance, RxBus::class.java)

        val obj = RxBus.listen(String::class.java)
        RxBus.publish("TEST EVENT")
        Assert.assertNotNull(obj)
    }
}