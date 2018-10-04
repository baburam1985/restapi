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

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Order> getAllOrders() {
		return jdbcTemplate.query("SELECT * from ORDERS", new OrderRowMapper());
	}	
	
	public int saveOrder(Order order) {
		return jdbcTemplate.update(
				"INSERT INTO ORDERS(TRADER_ID, ORDER_ID, SYMBOL, SIDE, ORDER_TYPE, PRICE, SHARES, LAST_UPDATE) VALUES(?,?,?,?,?,?,?,?)",
				order.getTraderId(), order.getOrderId(), order.getSymbol(), order.getSide(), order.getOrderType(), order.getPrice(), order.getShares(), order.getLastUpdate());
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
}
