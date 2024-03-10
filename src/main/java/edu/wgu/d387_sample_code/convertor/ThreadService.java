package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ThreadService {
    void createThreads();

    String[] getLanguages();

    Map<String, String> getWelcomeMessages();
}
