package kr.backpac.idus.util;

import java.util.regex.Pattern;

public class ValidationUtil {
	// 숫자 체크
	public boolean isNumeric(String str) {
	    return Pattern.matches("^[0-9]*$", str);
	}
	
	// 한글 혹은 영문 체크
	public boolean isAlphaNumeric(String str) {
	    return Pattern.matches("[a-zA-Z가-힣]*$", str);
	}

	// 영문 소문자 체크
	public boolean isDowner(String str) {
	    return Pattern.matches("^[a-z]*$", str);
	}
	
	// 비밀번호 체크
	public boolean isPassword(String str) {
	    return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}$", str);
	}

	// 이메일 체크
	public boolean isEmail(String str) {
	    return Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", str);
	}
}
