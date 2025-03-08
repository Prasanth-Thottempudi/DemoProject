package com.services.entity;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "services")
@Data
public class ServicesEntity {

	@Id
    @GeneratedValue(generator = "service-id-generator")
    @GenericGenerator(
        name = "service-id-generator", 
        strategy = "com.services.idgenerator.ServiceIdGenerator"  // Ensure this is the correct fully qualified path
    )
    @Column(name = "service_id", updatable = false, nullable = false)
    private String serviceId;

    private String serviceName;
    private String serviceDescription;
    private String serviceImageUrl;

}
