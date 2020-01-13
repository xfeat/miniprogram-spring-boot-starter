package cn.ocoop.framwork.miniprogram.user;

import lombok.Data;

@Data
public class Code2SessionResponse {
    private String openid;
    private String session_key;
    private String unionid;
    private int errcode;
    private String errmsg;

    public boolean success() {
        return errcode == 0;
    }
}
