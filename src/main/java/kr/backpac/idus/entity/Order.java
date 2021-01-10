package kr.backpac.idus.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	
	@ApiModelProperty(
			  value = "Order No(주문 번호)",
			  name = "orderNo",
			  dataType = "String",
			  example = "ABCDE0000001")
	@Column(name="order_no", nullable = false, length = 12)
	private String orderNo;
	
	@ApiModelProperty(
			  value = "Member IDX(멤버 IDX)",
			  name = "memberIdx",
			  dataType = "int",
			  example = "1")
	@Column(name="member_idx", nullable = false)
	private int memberIdx;
	
	@ApiModelProperty(
			  value = "Product Name(제품명)",
			  name = "productName",
			  dataType = "String",
			  example = "PRODUCT1")
	@Column(name="product_name", nullable = false, length = 100)
	private String productName;
	
	@ApiModelProperty(
			  value = "Order Time(결제일시)",
			  name = "orderTime",
			  dataType = "LocalDateTime")
	@Column(name="order_time", nullable = false)
	private LocalDateTime orderTime;
	
	
}
