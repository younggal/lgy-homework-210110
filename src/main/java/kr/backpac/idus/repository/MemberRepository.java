package kr.backpac.idus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.backpac.idus.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Member findByEmail(String email);
	Page<Member> findByNameContaining(Pageable pageable, String searchName);
	Page<Member> findByEmailContainingIgnoreCase(Pageable pageable, String searchEmail);
	Page<Member> findByNameContainingAndEmailContainingIgnoreCase(Pageable pageable, String searchName, String searchEmail);
}
