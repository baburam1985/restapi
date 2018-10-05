package com.acme.acmetrade.services;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acme.acmetrade.domain.Order;
import com.acme.acmetrade.repository.OrderRepository;
import com.acme.acmetrade.domain.OrderTrans;
import com.acme.acmetrade.repository.OrderTransRepository;

@Service
public class OrderTransService {

  
    @Autowired
    private OrderTransRepository orderTransRepository;  
     
    public List<OrderTrans> getAllOrderTrans() {		
		return orderTransRepository.getAllOrderTrans();
	}
    
}