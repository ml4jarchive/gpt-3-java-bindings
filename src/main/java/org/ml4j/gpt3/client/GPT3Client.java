/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ml4j.gpt3.client;

import org.ml4j.gpt3.GPT3Api;
import org.ml4j.gpt3.GPT3Request;
import org.ml4j.gpt3.GPT3Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * This is the beginnings of a client for the GPT-3 API. This has only been used to hit
 * the mock API so far as I don't have access to the real thing.
 * 
 *( https://github.com/ml4j/gpt-3-mock-api )
 * 
 * Still to do, exception handling and testing.
 * 
 * 
 * @author Michael Lavelle
 *
 */
public class GPT3Client implements GPT3Api {

	private RestTemplate restTemplate;
	private String apiKey;
	private String urlProtocolAndHostPrefix;
	private String modelName;
	
	public GPT3Client(RestTemplate restTemplate, String urlProtocolAndHostPrefix, String modelName, String apiKey) {
		this.restTemplate = restTemplate;
		this.apiKey = apiKey;
		this.urlProtocolAndHostPrefix = urlProtocolAndHostPrefix;
		this.modelName = modelName;
	}
	
	@Override
	public GPT3Response getResponse(GPT3Request request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ apiKey);
		String url = urlProtocolAndHostPrefix + "/v1/engines/" + modelName + "completions";
		HttpEntity<GPT3Request> entity = new HttpEntity<GPT3Request>(request,headers);
		return restTemplate.postForObject(url, entity, GPT3Response.class);
	}

}
