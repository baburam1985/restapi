package com.acme.acmetrade.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acme.acmetrade.domain.OrderTrans;

@Repository
public class OrderTransRepository {	         
		
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<OrderTrans> getAllOrderTrans() {
		return jdbcTemplate.query("SELECT * from ORDERTRANS", new OrderTransRowMapper());
	}	
		
	public int saveOrderTrans(OrderTrans orderTrans) {	
		return jdbcTemplate.update(
				"INSERT INTO ORDERTRANS(TRANS_ORDER_ID, TRANS_CREATION_DATE, TRANS_ORDER_STATUS, TRANS_LAST_UPDATE) VALUES(?,?,?,?)",
				 orderTrans.getTransOrderId(), orderTrans.getTransCreationDate(), orderTrans.getTransOrderStatus(), orderTrans.getTransLastUpdate());
	}	
		
	class OrderTransRowMapper implements RowMapper<OrderTrans> {
		@Override
		public OrderTrans mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderTrans orderTrans = new OrderTrans();			
			orderTrans.setTransId(rs.getInt("TRANS_ID"));
			orderTrans.setTransOrderId(rs.getInt("TRANS_ORDER_ID"));	
			orderTrans.setTransCreationDate(rs.getString("TRANS_CREATION_DATE"));
			orderTrans.setTransOrderStatus(rs.getString("TRANS_ORDER_STATUS"));
			orderTrans.setTransLastUpdate(rs.getString("TRANS_LAST_UPDATE"));							
			return orderTrans;
		}
	}
}
