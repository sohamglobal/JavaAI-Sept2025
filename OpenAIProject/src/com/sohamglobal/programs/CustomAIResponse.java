package com.sohamglobal.programs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class CustomAIResponse {
	public static void main(String[] args) {
		try
		{
			String API_KEY="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";
			String endpoint="https://api.openai.com/v1/chat/completions";
			
			String requestBody="""
					{
					"model":"gpt-4o-mini",
					"messages":[{"role":"user","content":" tell me in 5 lines, which topics i must prepare to crack a Java Spring boot developer interview of a good package"}]
					}
					""";
			
			HttpRequest request=HttpRequest.newBuilder()
					.uri(URI.create(endpoint))
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+API_KEY)
					.POST(HttpRequest.BodyPublishers.ofString(requestBody))
					.build();
			
			HttpClient client=HttpClient.newHttpClient();
			HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Response from OpenAI : ");
			//System.out.println(response.body());
			
			JSONObject obj=new JSONObject(response.body());
			String AI_Answer=obj.getJSONArray("choices")
					.getJSONObject(0)
					.getJSONObject("message")
					.getString("content");
			
			System.out.println(AI_Answer);
			
		}
		catch(Exception e)
		{
			System.out.println("Error calling AI API...");
		}
	}

}
