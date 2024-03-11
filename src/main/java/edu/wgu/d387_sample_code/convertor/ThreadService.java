package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ThreadService {
    void createThreads();

    String[] getLanguages();

    List<String> getWelcomeMessages();
}
