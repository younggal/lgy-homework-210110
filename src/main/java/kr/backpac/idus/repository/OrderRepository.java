package kr.backpac.idus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.backpac.idus.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByMemberIdx(int memberIdx);
}
