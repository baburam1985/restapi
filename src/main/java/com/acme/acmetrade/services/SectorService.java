package com.acme.acmetrade.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.acme.acmetrade.domain.Sector;
import com.acme.acmetrade.repository.MarketSectorRepository;

public class SectorService {

    @Autowired
    private MarketSectorRepository marketSectorRepository;

    public void addMarketSector(Sector sector){
    	marketSectorRepository.addMarketSector(sector);
    }
    
    public void updateMarketSector(Sector sector) {
    	marketSectorRepository.updateMarketSector(sector);
    }
    
    public void getMarketSectorByName(String name) {
    	marketSectorRepository.getMarketSectorByName(name);
    }
    
    public void getAllMarketSectors() {
    	marketSectorRepository.getAllMarketSectors();
    }  
    
    public void deleteMarketSector(Sector sector) {
    	marketSectorRepository.deleteMarketSector(sector);
    }  
}