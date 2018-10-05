package com.acme.acmetrade.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acme.acmetrade.domain.Order;

@Repository
public class OrderRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public OrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String getlatestOrder() {
		return jdbcTemplate.queryForObject("SELECT order_id FROM ORDERS order by order_id desc limit 1", new OrderIdMapper());
		
	}	
	
	public List<Order> getAllOrders() {
		return jdbcTemplate.query("SELECT * from ORDERS", new OrderRowMapper());
	}
	
	public int saveOrder(Order order) {
		
		return jdbcTemplate.update(
				"INSERT INTO ORDERS(TRADER_ID, SYMBOL, SIDE, ORDER_TYPE, PRICE, SHARES, LAST_UPDATE) VALUES(?,?,?,?,?,?,?)",
				order.getTraderId(), order.getSymbol(), order.getSide(), order.getOrderType(), order.getPrice(), order.getShares(), order.getLastUpdate());
	}
		
	
	class OrderRowMapper implements RowMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setTraderId(rs.getInt("TRADER_ID"));
			order.setOrderId(rs.getInt("ORDER_ID"));	
			order.setSymbol(rs.getString("SYMBOL"));
			order.setSide(rs.getString("SIDE"));
			order.setOrderType(rs.getString("ORDER_TYPE"));
			order.setPrice(rs.getInt("PRICE"));			
			order.setShares(rs.getInt("SHARES"));
			order.setLastUpdate(rs.getString("LAST_UPDATE"));					
			return order;
		}
	}
	
	class OrderIdMapper implements RowMapper<String> {

		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String orderId;
			orderId = rs.getString("ORDER_ID");	
							
			return orderId;
		}
	}
	
	
}
