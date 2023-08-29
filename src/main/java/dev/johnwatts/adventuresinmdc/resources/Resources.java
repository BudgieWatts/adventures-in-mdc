package dev.johnwatts.adventuresinmdc.resources;

import dev.johnwatts.adventuresinmdc.exceptions.ProcessingError;
import dev.johnwatts.adventuresinmdc.mdc.MdcUtility;
import dev.johnwatts.adventuresinmdc.validators.IdValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Resources {
    @GetMapping("/accounts/{id}")
    public String account(@PathVariable Long id) {
        MdcUtility.accounts(id);
        log.info("Doing some stuff");
        IdValidator.validate(id);
        MdcUtility.updateTask("returning response");
        return String.format("account-id: %s", id);
    }
    @GetMapping("/systems/{system}")
    public String systems(@PathVariable String system) {
        MdcUtility.system(system);
        if (system.equals("system-that-is-down")) {
            throw new ProcessingError("Oh no, the system is down");
        }
        MdcUtility.updateTask("returning response");
        return String.format("system: %s", system);
    }

    @GetMapping("/broken")
    public String broken() {
        MdcUtility.broken();
        throw new RuntimeException("We didn't see this coming");
    }
}
