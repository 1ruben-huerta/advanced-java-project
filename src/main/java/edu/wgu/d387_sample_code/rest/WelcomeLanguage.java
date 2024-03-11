package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeLanguage {

    @Autowired
    private ThreadService threadService;
    @GetMapping("/welcomeFREN")
    public List<String> getWelcome() {
        List<String> messages = threadService.getWelcomeMessages();
        return messages;
    }

}
