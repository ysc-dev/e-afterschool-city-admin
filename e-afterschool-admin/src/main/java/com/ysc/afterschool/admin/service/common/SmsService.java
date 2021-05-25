package com.ysc.afterschool.admin.service.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.RealResponseBody;
import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.InvitationService;
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
	
	@Autowired
	private InvitationService invitationService;
	
	private String token;
	
	private String tokenType;
	
	private final String message = "대기 중인 강좌의 수강 승인이 완료되었습니다. 사이트에서 확인해주세요.";

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
	 * 과목별 메시지 전송
	 * 
	 * @param subjectId
	 * @param message
	 * @throws IOException
	 */
	public ResponseEntity<?> sendBySubject(int subjectId, String message) {
		
		try {
			Subject subject = subjectService.get(subjectId);
			if (subject != null) {
				String callback = subject.getInvitation().getCity().getSms();
				String sendType = message.getBytes("euc-kr").length > 90 ? "lms" : "sms";
				
				List<Apply> applies = applyService.getListFromSubject(subjectId);
				if (applies.size() == 0) {
					return new ResponseEntity<>("수강신청이 없습니다.", HttpStatus.BAD_REQUEST);
				}
				
				for (Apply apply : applies) {
					if (init()) {
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
						
//						log.debug("send response : " + response.toString());
						
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("SMS 발송을 실패하였습니다.", HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("SMS 발송을 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("SMS 발송이 완료 되었습니다.", HttpStatus.OK);
	}
	
	/**
	 * 안내장별 메시지 전송
	 * 
	 * @param subjectId
	 * @param message
	 * @throws IOException
	 */
	public ResponseEntity<?> sendByInvitation(int invitationId, String message) {
		
		try {
			Invitation invitation = invitationService.get(invitationId);
			if (invitation != null) {
				String callback = invitation.getCity().getSms();
				String sendType = message.getBytes("euc-kr").length > 90 ? "lms" : "sms";
				
				List<Apply> applies = applyService.getListFromInvitation(invitationId);
				if (applies.size() == 0) {
					return new ResponseEntity<>("수강신청이 없습니다.", HttpStatus.BAD_REQUEST);
				}
				
				for (Apply apply : applies) {
					if (init()) {
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
						
//						log.debug("send response : " + response.toString());
						
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("SMS 발송을 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>("SMS 발송이 완료 되었습니다.", HttpStatus.OK);
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

	/**
	 * 문자 발송
	 * @param phone
	 * @param invitationId
	 * @throws IOException
	 */
	public ResponseEntity<?> send(String phone, int invitationId) {
		
		try {
			Invitation invitation = invitationService.get(invitationId);
			if (invitation != null) {
				if (init()) {
					String callback = invitation.getCity().getSms();
					phone = phone.replaceAll("-", "");
					
					String sendType = message.getBytes("euc-kr").length > 90 ? "lms" : "sms";
					
					MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
					RequestBody body = RequestBody.create(mediaType, "phone=" + phone 
							+ "&callback=" + callback + "&message=" + message + "&refkey=12132214");
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
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("SMS 발송을 실패하였습니다.", HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("SMS 발송을 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("SMS 발송이 완료 되었습니다.", HttpStatus.OK);
	}		
}
