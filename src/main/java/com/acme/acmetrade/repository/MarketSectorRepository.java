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
	
	public Sector getMarketSectorById(int id) {
		return jdbcTemplate.queryForObject("SELECT * from MARKET_SECTOR WHERE SECTOR_ID = ?", new Object[] {id},  new MarketSectorRowMapper());
	}
	
	public Sector getMarketSectorByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * from MARKET_SECTOR WHERE SECTOR_NAME = ?", new Object[] {name},  new MarketSectorRowMapper());
	}
	
	public List<Sector> getAllMarketSectors() {
		return jdbcTemplate.query("SELECT * from MARKET_SECTOR", new MarketSectorRowMapper());
	}
	
	public int deleteMarketSector (Sector sector) {
		return jdbcTemplate.update("DELETE MARKET_SECTOR WHERE SECTOR_ID = ?", sector.getId());
	}
	
	public int getSectorId(String sectorName){
       return (Integer)jdbcTemplate.queryForObject("SELECT SECTOR_ID FROM MARKET_SECTOR WHERE SECTOR_NAME = ?",
    		   new Object[] {sectorName},(new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("SECTOR_ID");
            }
        }));
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
