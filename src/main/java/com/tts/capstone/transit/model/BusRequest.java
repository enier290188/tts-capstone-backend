package com.tts.capstone.transit.model;

import lombok.Data;

@Data
public class BusRequest {
    public String address;
    public String city;

    public BusRequest() {
    }

    public BusRequest(String address, String city) {
        this.address = address;
        this.city = city;
    }
}
