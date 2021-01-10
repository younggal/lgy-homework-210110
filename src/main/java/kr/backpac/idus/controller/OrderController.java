package kr.backpac.idus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.backpac.idus.entity.Order;
import kr.backpac.idus.model.ResultDto;
import kr.backpac.idus.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@ApiOperation(value="단일 회원의 주문 목록 조회 (Order List By Member)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberIdx", value = "멤버 IDX", required = true, dataType = "int", paramType = "path")
    })
	@GetMapping("/list/{memberIdx}")
	public ResultDto<List<Order>> orderListByMember(@PathVariable(name = "memberIdx") int memberIdx) {
		return orderService.getOrderListByMember(memberIdx);
	}

}
