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
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderTransRepository orderTransRepository;  
    
    public static final String str = "Order Saved";
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String timeStamp = dateFormat.format(cal.getTime());
	
      
    @Transactional  
    public String saveOrder(Order order){		
    	saveOrderMast(order);
    	String currOrderIds = orderRepository.getlatestOrder();
    	int currOrderId = Integer.parseInt(currOrderIds);
    	order.setOrderId(currOrderId);
    	saveOrderTrans(order);    	
    	return str;
   }
    @Transactional
    public int saveOrderMast(Order order){
    	   try {      		  
    		   order.setLastUpdate(timeStamp);
    		   	return orderRepository.saveOrder(order);
    		}  catch (Exception e) {    			
    			e.printStackTrace();    			
    		}  
    	   return 0;
    	   
    }
    @Transactional
    public int saveOrderTrans(Order order){ 	    	
    	 try {
    			
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Calendar cal = Calendar.getInstance();
    		String timeStamp = dateFormat.format(cal.getTime());
    	
    		OrderTrans orderTrans = new OrderTrans();		
	 		orderTrans.setTransOrderId(order.getOrderId());	
	 		orderTrans.setTransCreationDate(timeStamp);
	 		orderTrans.setTransOrderStatus("Completed");
	 		orderTrans.setTransLastUpdate(timeStamp);    
	 		return orderTransRepository.saveOrderTrans(orderTrans); 	 		
	    }  catch (Exception e) {    			
			e.printStackTrace();    			
		}  
	   return 0;
 }
    
    public List<Order> getAllOrders() {		
		return orderRepository.getAllOrders();
	}
    
}