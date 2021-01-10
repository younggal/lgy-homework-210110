package kr.backpac.idus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.backpac.idus.model.MemberDetailDto;
import kr.backpac.idus.model.MemberJoinDto;
import kr.backpac.idus.model.MemberListDto;
import kr.backpac.idus.model.MemberSearchDto;
import kr.backpac.idus.model.ResultDto;
import kr.backpac.idus.service.MemberService;
import kr.backpac.idus.util.ValidationUtil;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@ApiOperation(value="회원가입 (Member Join)")
	@PostMapping
	public ResultDto<?> memberJoin(MemberJoinDto memberJoinDto) {
		// 유효성 검사
		ValidationUtil validCheck = new ValidationUtil();
		
		if (!validCheck.isAlphaNumeric(memberJoinDto.getName())) {
			return new ResultDto<>(ResultDto.FAIL, "이름은 한글, 영문 대소문자만 허용됩니다.");
		} else if (!validCheck.isDowner(memberJoinDto.getNickname())) {
			return new ResultDto<>(ResultDto.FAIL, "별명은 영문 소문자만 허용됩니다.");
		} else if (!validCheck.isNumeric(memberJoinDto.getPhone())) {
			return new ResultDto<>(ResultDto.FAIL, "전화번호는 숫자만 허용됩니다.");
		} else if (!validCheck.isEmail(memberJoinDto.getEmail())) {
			return new ResultDto<>(ResultDto.FAIL, "이메일 형식에 맞지않습니다.");
		} else if (!validCheck.isPassword(memberJoinDto.getPassword())) {
			return new ResultDto<>(ResultDto.FAIL, "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여야 합니다. (최소 10자)");
		}
		
		return memberService.insertMember(memberJoinDto);
	}
	
	@ApiOperation(value="단일 회원 상세 정보 조회 (Member Detail)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberIdx", value = "멤버 IDX", required = true, dataType = "int", paramType = "path")
    })
	@GetMapping("/{memberIdx}")
	public ResultDto<MemberDetailDto> memberDetail(@PathVariable(name = "memberIdx") int memberIdx) {
		return memberService.getMemberDetail(memberIdx);
	}
	
	@ApiOperation(value="여러 회원 목록 조회 (Member List)")
	@GetMapping("/list")
	public ResultDto<Page<MemberListDto>> memberList(MemberSearchDto memberSearchDto, Pageable page) {
		return memberService.getMemberList(memberSearchDto, page);
	}
}
