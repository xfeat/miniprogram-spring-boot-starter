package cn.ocoop.framwork.miniprogram.user;

import lombok.Data;

@Data
public class PhoneNumberDecryptResponse {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}
