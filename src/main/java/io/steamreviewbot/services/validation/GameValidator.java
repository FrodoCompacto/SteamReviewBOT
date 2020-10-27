package io.steamreviewbot.services.validation;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;

import io.steamreviewbot.services.exceptions.ForbiddenException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class GameValidator {
	
	OkHttpClient client = new OkHttpClient();
	
	public boolean validateGame(Integer appId) {
		
		Request request = new Request.Builder()
			      .url("https://store.steampowered.com/api/appdetails?appids=" + appId)
			      .build();

			  try (Response response = client.newCall(request).execute()) {
				String str = response.body().string();
				
//				int lastOc = 0;
//				
//				int firstOc = str.indexOf(34);
//				if (firstOc == -1) throw new ForbiddenException("Invalid game list(Irrecognizable Json)");
//				
//				for(int i = firstOc+1; str.charAt(i) != 34; i++) {
//					lastOc = i;
//				}
//				
//				String lastString = str.substring(lastOc+1);
//				
//				String finalString = "{\"game_number"+ lastString;
//				
//				System.out.println(finalString);
//				
//			    Gson g = new Gson();
//			    SteamGameResponse p = g.fromJson(finalString, SteamGameResponse.class);
//			    
//			    return p.gameNumber.success;
				
				int index = str.indexOf("success");
				if (index == -1) throw new ForbiddenException("Invalid game list(Irrecognizable Json)");
				index = index + 9;
				
				
				return str.substring(index, index+4).equalsIgnoreCase("true");
				
			    
			  } catch (JsonSyntaxException e) {
				e.printStackTrace();
				throw new ForbiddenException("Invalid game list(JsonSyntaxException)");
			} catch (IOException e) {
				e.printStackTrace();
				throw new ForbiddenException("Invalid game list(IOException)");
			}
	}
}
