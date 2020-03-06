package io.steamreviewbot.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import okhttp3.*;

import java.io.IOException;


@Component
@EnableScheduling
public class ReviveScheduler {

	private final OkHttpClient httpClient = new OkHttpClient();
	
	@Scheduled(fixedDelay = 1500000)
	 public void monsterReborn() throws IOException {
		

		Request request = new Request.Builder()
                .url("https://forbidden-bot.herokuapp.com/revive")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

		
   }
}
