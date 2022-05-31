package app.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ServiceTypeEntity extends Entity{

    private String idServiceType;
    private String serviceName;

    public ServiceTypeEntity(String idServiceType, String serviceName) {
        this.idServiceType = idServiceType;
        this.serviceName = serviceName;
    }

    public ServiceTypeEntity(String serviceName) {
        this.idServiceType = String.valueOf(UUID.randomUUID());
        this.serviceName = serviceName;
    }
}
