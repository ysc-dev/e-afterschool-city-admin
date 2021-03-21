package com.ysc.afterschool.admin.service.common;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.RealResponseBody;
import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

/**
 * 문자 서비스
 * 
 * @author hgko
 *
 */
@Slf4j
@Service
public class SmsService {
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private SubjectService subjectService;
	
	private String token;
	
	private String tokenType;

	/**
	 * 초기화
	 * 
	 * @throws IOException
	 */
	public boolean init() throws IOException {
		String text = "ysc2019:505e4c6d61dbe9dbf93e0f8861dc2c5d";
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder()
			.url("https://sms.gabia.com/oauth/token")
			.post(body)
			.addHeader("Content-Type", "application/x-www-form-urlencoded")
			.addHeader("Authorization", "Basic " + new String(encodedBytes))
			.addHeader("cache-control", "no-cache")
			.build();
		
		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		log.debug("init response : " + response.toString());
		
		if (response.isSuccessful()) {
			RealResponseBody result = (RealResponseBody) response.body();
			
			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(result.string());
				
				String accessToken = (String) jsonObject.get("access_token");
				tokenType = (String) jsonObject.get("token_type");
				token = "ysc2019:" + accessToken;
				
				result.close();
				
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}

	/**
	 * 메시지 전송
	 * 
	 * @param subjectId
	 * @param message
	 * @throws IOException
	 */
	public void send(int subjectId, String message) throws IOException {
		
		Subject subject = subjectService.get(subjectId);
		if (subject != null) {
			if (init()) {
				String callback = subject.getInvitation().getCity().getSms();
				String sendType = message.getBytes("euc-kr").length > 90 ? "lms" : "sms";
				
				for (Apply apply : applyService.getListFromSubject(subjectId)) {
					String phone = apply.getStudent().getTel().replaceAll("-", "");
					
					MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
					RequestBody body = RequestBody.create(mediaType, "phone=" + phone + "&callback=" 
								+ callback + "&message=" + message + "&refkey=12132214");
					Request request = new Request.Builder()
							.url("https://sms.gabia.com/api/send/" + sendType)
							.post(body)
							.addHeader("Content-Type", "application/x-www-form-urlencoded")
							.addHeader("Authorization", tokenType + " " + new String(Base64.encodeBase64(token.getBytes())))
							.addHeader("cache-control", "no-cache")
							.build();
					
					OkHttpClient client = new OkHttpClient();
					Response response = client.newCall(request).execute();
					response.body().close();
					
					log.debug("send response : " + response.toString());
				}
			}
		}
	}
	
	/**
	 * 테스트 전송
	 * 
	 * @param subjectId
	 * @param message
	 * @throws IOException
	 */
	public void test(int subjectId, String message) throws IOException {
		
		Subject subject = subjectService.get(subjectId);
		if (subject != null) {
			if (init()) {
				String callback = subject.getInvitation().getCity().getSms();
				String phone = "01046131202";
				
				String sendType = message.getBytes("euc-kr").length > 90 ? "lms" : "sms";
				
				MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
				RequestBody body = RequestBody.create(mediaType, "phone=" + phone + "&callback=" 
							+ callback + "&message=" + message + "&refkey=12132214");
				Request request = new Request.Builder()
						.url("https://sms.gabia.com/api/send/" + sendType)
						.post(body)
						.addHeader("Content-Type", "application/x-www-form-urlencoded")
						.addHeader("Authorization", tokenType + " " + new String(Base64.encodeBase64(token.getBytes())))
						.addHeader("cache-control", "no-cache")
						.build();
				
				OkHttpClient client = new OkHttpClient();
				Response response = client.newCall(request).execute();
				response.body().close();
				
				log.debug("send response : " + response.toString());
			}
		}
	}		
}
