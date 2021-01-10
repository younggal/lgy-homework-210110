package kr.backpac.idus.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto<T> {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";
	
	@ApiModelProperty(
			  value = "Result Status(SUCCESS or FAIL)",
			  name = "result",
			  dataType = "String",
			  example = "SUCCESS")
	private String result;
	private T body;

	public ResultDto() {
	}

	public ResultDto(String result) {
		this.result = result;
	}

	public ResultDto(String result, T body) {
		this.result = result;
		this.body = body;
	}

	public ResultDto(T body) {
		this.result = SUCCESS;
		this.body = body;
	}
}
