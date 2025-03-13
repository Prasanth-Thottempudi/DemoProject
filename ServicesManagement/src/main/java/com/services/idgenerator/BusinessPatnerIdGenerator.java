package com.services.idgenerator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import jakarta.persistence.Query;

public class BusinessPatnerIdGenerator implements IdentifierGenerator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "NEX-GEN";
        // Use native SQL query to fetch the last service_id
        String query = "SELECT business_id FROM business_patner_details ORDER BY business_id DESC LIMIT 1";
        
        // Ensure to use createNativeQuery for raw SQL
        Query nativeQuery = session.createNativeQuery(query);
        String lastId = (String) nativeQuery.getResultStream().findFirst().orElse(prefix + "000");
        
        int id = Integer.parseInt(lastId.replace(prefix, ""));
        return prefix + String.format("%03d", id + 1);
    }
}