package edu.wgu.d387_sample_code.add_ons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeLanguage {

    @Autowired
    private ThreadService threadService;
    @Autowired
    private TimeKeeper timeKeeper;

    @GetMapping("/welcomeFREN")
    public List<String> getWelcome() {
        return threadService.getWelcomeMessages();
    }

    @GetMapping("/time")
    public Map<String, String> getTime() {
        return timeKeeper.timeZones();
    }

}
