package kr.backpac.idus.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberSearchDto {
	@ApiModelProperty(
			  value = "Member Name(회원 이름)",
			  name = "name",
			  dataType = "String",
			  example = "이가영")
	private String name;
	
	@ApiModelProperty(
			  value = "Email(이메일)",
			  name = "email",
			  dataType = "String",
			  example = "younggal@aaa.com")
	private String email;
}
