package com.fooddelivery.idgenerator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import jakarta.persistence.Query;

public class RecipeIdGenerator implements IdentifierGenerator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "RECIPE";
        String query = "SELECT recipe_id FROM recipe_data ORDER BY recipe_id DESC LIMIT 1";
        
        Query nativeQuery = session.createNativeQuery(query);
        String lastId = (String) nativeQuery.getResultStream().findFirst().orElse(prefix + "000");
        
        int id = Integer.parseInt(lastId.replace(prefix, ""));
        return prefix + String.format("%03d", id + 1);
    }
}
