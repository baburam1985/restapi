package com.acme.acmetrade.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.acmetrade.domain.Sector;
import com.acme.acmetrade.domain.Security;
import com.acme.acmetrade.repository.MarketSectorRepository;
import com.acme.acmetrade.repository.SecurityRepository;

@Service
public class SecurityService {

    @Autowired
    private SecurityRepository securityRepository ;

    @Autowired
    private MarketSectorRepository marketSectorRepository;

    public int addSecurity(Security security){
       try {
		return securityRepository.saveSecurity(security);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return 0;
    }

    public int saveSecurity(Security security){
        try {
			return securityRepository.saveSecurity(security);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
    public int updateSecurity(Security security){
        try {
			return securityRepository.updateSecurity(security);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
    public int deleteSecurityBySymbol(String symbol){
        try {
			return securityRepository.deleteSecurityBySymbol(symbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }

    public int deleteSecurityBySectorId(int sectorId){
        try {
            return securityRepository.deleteSecurityBySectorId(sectorId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    public List<Security> getAllSecurities() 
    {
    	try {
			return securityRepository.retrieveAllSecurities();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Collections.emptyList();
    }
    public Security getSecurityBySymbol(String symbol) {
    	try {
			return securityRepository.retrieveSecurityBySymbol(symbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Security();
    }
    public List<Security> getSecuritiesBySectorId(int sectorId) {
    	try {
			return securityRepository.retrieveSecuritiesBySectorId(sectorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Collections.emptyList();

    }
}
