package dev.johnwatts.adventuresinmdc.resources;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @Test
    void mdcIsWorkingForAccount() {
        Resources resources = new Resources();
        resources.account(1L);
        assertEquals(MDC.get("method"), "GET");
        assertEquals(MDC.get("request"), "account");
        assertEquals(MDC.get("arg"), "1");
        assertEquals(MDC.get("task"), "returning response");
    }

    @Test
    void outputIsLoggedCorrectly() {
        // https://www.baeldung.com/junit-asserting-logs
        assertEquals("See above", "for how we could");
    }
}