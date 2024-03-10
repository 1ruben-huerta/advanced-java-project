package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
public class WelcomeLanguage {

    @Autowired
    private ThreadService threadService;
    @GetMapping("/welcomeFREN")
    public Collection<String> getWelcome() {
        Map<String, String> messages = threadService.getWelcomeMessages();
        return messages.values();
    }

}
