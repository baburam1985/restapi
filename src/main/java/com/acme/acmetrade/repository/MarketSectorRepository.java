package com.acme.acmetrade.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acme.acmetrade.domain.Sector;

@Repository
public class MarketSectorRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addMarketSector (Sector sector) {
		return jdbcTemplate.update("INSERT INTO MARKET_SECTOR (SECTOR_NAME, SECTOR_DESC) VALUES (?,?)",
				sector.getSectorName(), sector.getSectorDesc());
	}

	public int updateMarketSector (Sector sector) {
		return jdbcTemplate.update("UPDATE MARKET_SECTOR SET SECTOR_NAME = ?, SECTOR_DESC = ? WHERE SECTOR_ID = ?", 
				sector.getSectorName(), sector.getSectorDesc(), sector.getId());
	}
	
	public Sector getMarketSectorByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * from MARKET_SECTOR WHERE SECTOR_NAME = ?", new MarketSectorRowMapper());
	}
	
	public List<Sector> getAllMarketSectors() {
		return jdbcTemplate.query("SELECT * from MARKET_SECTOR", new MarketSectorRowMapper());
	}
	
	public int deleteMarketSector (Sector sector) {
		return jdbcTemplate.update("DELETE MARKET_SECTOR WHERE SECTOR_ID = ?", sector.getId());
	}
	
	public void getSectorId(String sectorName){
        jdbcTemplate.query("SELECT SECTOR_ID FROM MARKET_SECTOR WHERE SECTOR_NAME = ?",(new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String sectorId;
                sectorId = rs.getString("SECTOR_ID");
                return sectorId;
            }
        }),sectorName);
    }
	
	class MarketSectorRowMapper implements RowMapper<Sector> {

		@Override
		public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sector sector = new Sector();
			sector.setId(rs.getInt("SECTOR_ID"));
			sector.setSectorDesc(rs.getString("SECTOR_DESC"));
			sector.setSectorName(rs.getString("SECTOR_NAME"));			
			return sector;
		}
		
	}
}
