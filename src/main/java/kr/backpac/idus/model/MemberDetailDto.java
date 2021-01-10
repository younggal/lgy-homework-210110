package kr.backpac.idus.model;

import io.swagger.annotations.ApiModelProperty;
import kr.backpac.idus.entity.Member.Gender;
import lombok.Data;

@Data
public class MemberDetailDto {
	@ApiModelProperty(
			  value = "Member Name(회원 이름)",
			  name = "name",
			  dataType = "String",
			  example = "이가영")
	private String name;
	
	@ApiModelProperty(
			  value = "Member Nickname(회원 별명)",
			  name = "nickname",
			  dataType = "String",
			  example = "younggal")
	private String nickname;
	
	@ApiModelProperty(
			  value = "Phone Number(전화번호)",
			  name = "phone",
			  dataType = "String",
			  example = "01012345678")
	private String phone;
	
	@ApiModelProperty(
			  value = "Email(이메일)",
			  name = "email",
			  dataType = "String",
			  example = "younggal@aaa.com")
	private String email;
	
	@ApiModelProperty(
			  value = "Genger(성별)",
			  name = "gender",
			  dataType = "Gender",
			  example = "F")
	private Gender gender;
}
