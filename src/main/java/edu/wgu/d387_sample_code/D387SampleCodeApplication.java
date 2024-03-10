package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@SpringBootApplication
public class D387SampleCodeApplication {
	//static ExecutorService messageExecutor = newFixedThreadPool(5);
	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);
		/*Thread[] threadArr = new Thread[2];
		int threadIndex = 0;
		for (String language : Arrays.asList("fr_CA", "en_US")) {
			Thread thread = new Thread(() -> {
				try {
					Properties properties = new Properties();
					InputStream stream = new ClassPathResource("welcome_" + language + ".properties").getInputStream();
					properties.load(stream);
					System.out.println(properties.getProperty("welcome"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threadArr[threadIndex] = thread;
			thread.start();
		}
			threadIndex++;
		}
		 */
/*
		Properties properties = new Properties();
		messageExecutor.execute(()-> {
			try {
				InputStream stream = new ClassPathResource("welcome_fr_CA.properties").getInputStream();
				properties.load(stream);
				System.out.println(properties.getProperty("welcome"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		messageExecutor.execute(()-> {
			try {
				InputStream stream = new ClassPathResource("welcome_en_US.properties").getInputStream();
				properties.load(stream);
				System.out.println(properties.getProperty("welcome"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
*/
	}
}