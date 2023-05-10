package team.kalpeva.poc.mobile.router.comm.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import team.kalpeva.poc.mobile.router.model.Client;
import team.kalpeva.poc.mobile.router.model.Line;
import team.kalpeva.poc.mobile.router.model.Service;

import java.util.Arrays;

public class OrderRequest {
    @JsonProperty("orderId")
    private String id;
    @JsonProperty("orderType")
    private String type;
    private String brand;
    private String createdBy;
    private String createdDate;
    private Client client;
    private Line line;
    private Service[] services;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Client getClient() {
        return client;
    }

    public Line getLine() {
        return line;
    }

    public Service[] getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", client=" + client +
                ", line=" + line +
                ", services=" + Arrays.toString(services) +
                '}';
    }
}
