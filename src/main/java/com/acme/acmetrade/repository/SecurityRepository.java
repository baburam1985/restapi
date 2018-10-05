package com.acme.acmetrade.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acme.acmetrade.domain.Sector;
import com.acme.acmetrade.domain.Security;

@Repository
public class SecurityRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public SecurityRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int saveSecurity(Security security) {
		return jdbcTemplate.update(
				"INSERT INTO SECURITIES(SYMBOL, COMPANY_NAME, COMPANY_DESC, SECTOR_ID) VALUES(?,?,?,?)",
				security.getSymbol(), security.getCompanyName(), security.getDescription(), security.getSectorId());
	}

	public int updateSecurity(Security security) {
		return jdbcTemplate.update("UPDATE SECURITIES SET SECTOR_ID = ?, COMPANY_NAME = ?, COMPANY_DESC = ? WHERE SYMBOL = ?",
				security.getSectorId(), security.getCompanyName(), security.getDescription(), security.getSymbol());
	}

	public int deleteSecurityBySymbol(String symbol) {
		return jdbcTemplate.update("DELETE SECURITIES WHERE SYMBOL = ?",
				symbol);
	}

	public int deleteSecurityBySectorId(int sectorId) {
		return jdbcTemplate.update("DELETE SECURITIES WHERE SECTOR_ID = ?",
				sectorId);
	}

	public List<Security> retrieveAllSecurities() {
		return jdbcTemplate.query("SELECT * from SECURITIES", new SecurityRowMapper());
	}

	public List<Security> searchAllSecurities(String symbol) {
		return jdbcTemplate.query("SELECT * from SECURITIES where SYMBOL like ?",new SecurityRowMapper(), new Object[] {symbol+"%"});
	}


	public Security retrieveSecurityBySymbol(String symbol) {
		return (Security)jdbcTemplate.queryForObject("SELECT * from SECURITIES where SYMBOL = ?",new SecurityRowMapper(), new Object[] {symbol});
	}

	public List<Security> retrieveSecuritiesBySectorId(int sectorId) {
		return jdbcTemplate.query(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement stmt = con.prepareStatement("SELECT * from SECURITIES where SECTOR_ID = ?");
				stmt.setInt(1, sectorId);
				return stmt;
			}
		}, new SecurityRowMapper());
	}

	class SecurityRowMapper implements RowMapper<Security> {

		@Override
		public Security mapRow(ResultSet rs, int rowNum) throws SQLException {
			Security security = new Security();
			security.setSymbol(rs.getString("SYMBOL"));
			security.setCompanyName(rs.getString("COMPANY_NAME"));
			security.setDescription(rs.getString("COMPANY_DESC"));
			security.setSectorId(rs.getInt("SECTOR_ID"));
			return security;
		}
	}

}
