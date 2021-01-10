package kr.backpac.idus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.backpac.idus.entity.Order;
import kr.backpac.idus.model.ResultDto;
import kr.backpac.idus.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	// Order List By Member Idx
	public ResultDto<List<Order>> getOrderListByMember(int memberIdx) {
		List<Order> orderList = orderRepository.findByMemberIdx(memberIdx);
		return new ResultDto<>(orderList);
	}

}
