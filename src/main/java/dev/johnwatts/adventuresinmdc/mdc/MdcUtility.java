package dev.johnwatts.adventuresinmdc.mdc;

import org.slf4j.MDC;

public class MdcUtility {
    public static void accounts(Long id) {
        MDC.clear();
        MDC.put("method", "GET");
        MDC.put("request", "account");
        MDC.put("arg", id.toString());
        updateTask("receiving request");
    }
    public static void system(String system) {
        MDC.clear();
        MDC.put("method", "GET");
        MDC.put("request", "system");
        MDC.put("arg", system);
        updateTask("receiving request");
    }

    public static void broken() {
        MDC.clear();
        MDC.put("method", "GET");
        MDC.put("request", "broken");
        updateTask("receiving request");
    }

    public static void updateTask(String currentTask) {
        MDC.put("task", currentTask);
    }
}
