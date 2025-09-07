package com.airesearch.springopenai.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airesearch.springopenai.responses.AIResult;

@RestController
public class SohamController {
	
	@Value("${api_key}")
	private String API_KEY;
	
	@GetMapping("/test")
	public String test()
	{
		return "spring rest tested ok";
	}
	
	@PostMapping("/callai")
	public AIResult aiResponse(@RequestBody AIResult result)
	{
		try
		{
		String endpoint="https://api.openai.com/v1/chat/completions";
		JSONObject message=new JSONObject()
				.put("role", "user")
				.put("content", result.getPrompt());
		
		JSONObject requestJson=new JSONObject()
				.put("model", "gpt-5")
				.append("messages", message);
		
		String requestBody=requestJson.toString();
		
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(endpoint))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer "+API_KEY)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		
		HttpClient client=HttpClient.newHttpClient();
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject obj=new JSONObject(response.body());
		String AI_Answer=obj.getJSONArray("choices")
				.getJSONObject(0)
				.getJSONObject("message")
				.getString("content");
		
		result.setResponse(AI_Answer);
		
		}
		catch(Exception e)
		{
			result.setResponse(e.getMessage());
		}
		return result;
	}

}
