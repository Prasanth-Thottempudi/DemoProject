package com.usermanagement.idgenerator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import jakarta.persistence.Query;

public class RoleIdGenerator implements IdentifierGenerator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "ROLE";
        String query = "SELECT role_id FROM roles_data ORDER BY role_id DESC LIMIT 1";
        
        Query nativeQuery = session.createNativeQuery(query);
        String lastId = (String) nativeQuery.getResultStream().findFirst().orElse(prefix + "000");
        
        int id = Integer.parseInt(lastId.replace(prefix, ""));
        return prefix + String.format("%03d", id + 1);
    }
}
