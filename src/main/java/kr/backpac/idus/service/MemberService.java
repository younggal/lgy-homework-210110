package kr.backpac.idus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.backpac.idus.entity.Member;
import kr.backpac.idus.model.MemberDetailDto;
import kr.backpac.idus.model.MemberJoinDto;
import kr.backpac.idus.model.MemberListDto;
import kr.backpac.idus.model.MemberSearchDto;
import kr.backpac.idus.model.ResultDto;
import kr.backpac.idus.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	// Member Join
	public ResultDto<String> insertMember(MemberJoinDto memberJoinDto) {
		Member member = new Member();
		member.setEmail(memberJoinDto.getEmail());
		member.setName(memberJoinDto.getName());
		member.setNickname(memberJoinDto.getNickname());
		member.setPassword(new BCryptPasswordEncoder().encode(memberJoinDto.getPassword()));
		member.setPhone(memberJoinDto.getPhone());
		member.setGender(memberJoinDto.getGender());
		member = memberRepository.save(member);

		if (member.getIdx() > 0) {
			return new ResultDto<>(ResultDto.SUCCESS);
		} else {
			return new ResultDto<>(ResultDto.FAIL, "가입 중 오류가 발생했습니다.");
		}
	}

	// Member Detail
	public ResultDto<MemberDetailDto> getMemberDetail(int memberIdx) {
		Member member = memberRepository.findById(memberIdx).orElse(null);
		MemberDetailDto memberDetailDto = new MemberDetailDto();

		if (member.getIdx() > 0) {
			memberDetailDto.setName(member.getName());
			memberDetailDto.setNickname(member.getNickname());
			memberDetailDto.setEmail(member.getEmail());
			memberDetailDto.setPhone(member.getPhone());
			memberDetailDto.setGender(member.getGender());
		}

		return new ResultDto<>(memberDetailDto);
	}

	// Member List (Search & The Last Order)
	public ResultDto<Page<MemberListDto>> getMemberList(MemberSearchDto searchDto, Pageable page) {
		Page<Member> memberList = new PageImpl<>(new ArrayList<>());
		Pageable paging = PageRequest.of(page.getPageNumber(), page.getPageSize() == 0 ? 10 : page.getPageSize());
		
		if (searchDto.getName() == null && searchDto.getEmail() == null) {
			memberList = memberRepository.findAll(paging);
		} else if (searchDto.getName() != null && searchDto.getEmail() == null) {
			memberList = memberRepository.findByNameContaining(paging, searchDto.getName());
		} else if (searchDto.getName() == null && searchDto.getEmail() != null) {
			memberList = memberRepository.findByEmailContainingIgnoreCase(paging, searchDto.getEmail());
		} else if (searchDto.getName() != null && searchDto.getEmail() != null) {
			memberList = memberRepository.findByNameContainingAndEmailContainingIgnoreCase(paging, searchDto.getName(),
					searchDto.getEmail());
		}
		
		List<MemberListDto> memberDtoList = new ArrayList<>();
		
		for (Member member : memberList) {
			MemberListDto memberListDto = new MemberListDto();

			if (member.getIdx() > 0) {
				memberListDto.setName(member.getName());
				memberListDto.setNickname(member.getNickname());
				memberListDto.setEmail(member.getEmail());
				memberListDto.setPhone(member.getPhone());
				memberListDto.setGender(member.getGender());
				if (member.getOrder().size() > 0) {
					memberListDto.setOrder(member.getOrder().get(0));
				}
			}
			
			memberDtoList.add(memberListDto);
		}
		
		Page<MemberListDto> memberDtoPageList = new PageImpl<>(memberDtoList);

		return new ResultDto<>(memberDtoPageList);
	}

	// Security Login Check
	@Override
	public Member loadUserByUsername(String email) throws UsernameNotFoundException {
		return memberRepository.findByEmail(email);
	}

}
