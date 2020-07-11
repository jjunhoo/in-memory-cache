package com.amorepacific.inmemorycache.common;

import lombok.Data;

/**
 * The type Common error.
 * 공통 Error 메시지 처리 클래스
 */
@Data
public class CommonError {
    private String errorCode;
    private String errorMsg;
}
