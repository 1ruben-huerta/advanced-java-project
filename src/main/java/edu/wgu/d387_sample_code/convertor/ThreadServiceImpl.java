package edu.wgu.d387_sample_code.convertor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;


@Component
public class ThreadServiceImpl implements ThreadService {

    static ExecutorService messageExecutor = newFixedThreadPool(5);
    private final String[] languages = { "fr_CA", "en_US"};
    private final Map<String, String> welcomeMessages = new HashMap<>();
    @PostConstruct
    public void createThreads() {
        for (String language : languages) {
            messageExecutor.submit(() -> {
                try (InputStream stream = new ClassPathResource("welcome_" + language + ".properties").getInputStream()) {
                    Properties properties = new Properties();
                    properties.load(stream);
                    welcomeMessages.put(language, properties.getProperty("welcome"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public Map<String, String> getWelcomeMessages() {
        return this.welcomeMessages;
    }

    public String[] getLanguages() {
        return this.languages;
    }
}
