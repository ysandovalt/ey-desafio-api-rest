package com.challenge.payload;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author ysand
 */
public class PhoneCreateRequest {

    @NotBlank
    private String contrycode;
    @NotBlank
    private String citycode;
    @NotBlank
    private String number;

    public PhoneCreateRequest(String contrycode, String citycode, String number) {
        this.contrycode = contrycode;
        this.citycode = citycode;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getCitycode() {
        return citycode;
    }

    public String getContrycode() {
        return contrycode;
    }

}
