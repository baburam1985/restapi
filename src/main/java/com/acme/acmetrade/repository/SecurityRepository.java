package com.acme.acmetrade.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
   
    public int saveSecurity(Security security){
        return jdbcTemplate.update("INSERT INTO SECURITIES(SYMBOL, COMPANY_NAME, COMPANY_DESC, SECTOR_ID) VALUES(?,?,?,?)",
                security.getSymbol(), security.getCompanyName(), security.getDescription(), security.getSectorId());
    }
    
    public void updateSecurity(Security security) {
    	throw new UnsupportedOperationException();
    }
    
    public void deleteSecurity(Security security) {
    	throw new UnsupportedOperationException();
    }
    
    public List<Security> getAllSecurities() {
    	throw new UnsupportedOperationException();
    }

    public Security getSecurityBySymbol() {
    	throw new UnsupportedOperationException();
    }
    
    public List<Security> getSecuritiesBySector(Sector sector) {
    	throw new UnsupportedOperationException();
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
