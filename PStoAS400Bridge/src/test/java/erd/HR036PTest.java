package erd;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import erd.model.HR036P;

/**
 * Unit tests for HR036P entity class.
 * @author John Tutton john@tutton.net
 */
public class HR036PTest {

	@Test
	public void testGetLegacyOprId() {
		Integer employeeNumber = 1137;
		Integer indexNumber = 0;
		HR036P result = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, indexNumber);
		assertNotNull(result);
		if(result != null) {
			System.out.println(result.toString());
		}
	}

	@Test
	public void testDescribe() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	@SuppressWarnings("unchecked")
			List<Object> resultList = (List<Object>)em.createNativeQuery(
	    		    "DESCRIBE HR036P")
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		for(Object result : resultList) {
	    			System.out.println(result.toString());
	    		}
	    	}
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	}

}
