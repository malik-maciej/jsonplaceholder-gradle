package com.malik.jsonplaceholdergradle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MainRunner implements CommandLineRunner {

    private static final String API_URL = "https://jsonplaceholder.typicode.com";
    private static final Path OUTPUT_DIRECTORY_PATH = Paths.get("./output");

    @Override
    public void run(String... args) {
        if (!Files.exists(OUTPUT_DIRECTORY_PATH)) {
            createDirectory();
        }

        Post[] posts = getResponseEntity().getBody();
        if (posts != null) {
            for (Post post : posts) {
                createJsonFile(post);
            }
        }
    }

    private void createDirectory() {
        try {
            Files.createDirectory(OUTPUT_DIRECTORY_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ResponseEntity<Post[]> getResponseEntity() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(API_URL + "/posts",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Post[].class);
    }

    private void createJsonFile(Post post) {
        try {
            Path outputFilePath = Paths.get(OUTPUT_DIRECTORY_PATH + "/" + post.getId() + ".json");
            Files.write(outputFilePath, post.toJson().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}