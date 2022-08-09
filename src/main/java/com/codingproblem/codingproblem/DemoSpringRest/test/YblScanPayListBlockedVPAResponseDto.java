package com.codingproblem.codingproblem.DemoSpringRest.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class YblScanPayListBlockedVPAResponseDto {

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPayloadStr() {
        return payloadStr;
    }

    public void setPayloadStr(String payloadStr) {
        this.payloadStr = payloadStr;
    }

    public YblScanPayListBlockedVPAValidationPayloadDto getValidationPayload() {
        return validationPayload;
    }

    public void setValidationPayload(YblScanPayListBlockedVPAValidationPayloadDto validationPayload) {
        this.validationPayload = validationPayload;
    }

    @JsonProperty("payload")
    String payloadStr;

    YblScanPayListBlockedVPAValidationPayloadDto validationPayload;


    public static class YblScanPayListBlockedVPAValidationPayloadDto {

        String merchantChannelId;
        String merchantId;
        String customerMobileNumber;
        String merchantCustomerId;
        @JsonProperty("blockedVpas")
        List<YblScanPayListBlockedVPADto> blockedVpasList;

        public String getMerchantChannelId() {
            return merchantChannelId;
        }

        public void setMerchantChannelId(String merchantChannelId) {
            this.merchantChannelId = merchantChannelId;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getCustomerMobileNumber() {
            return customerMobileNumber;
        }

        public void setCustomerMobileNumber(String customerMobileNumber) {
            this.customerMobileNumber = customerMobileNumber;
        }

        public String getMerchantCustomerId() {
            return merchantCustomerId;
        }

        public void setMerchantCustomerId(String merchantCustomerId) {
            this.merchantCustomerId = merchantCustomerId;
        }

//        public String getBlockedVpas() {
//            return blockedVpas;
//        }
//
//        public void setBlockedVpas(String blockedVpas) {
//            this.blockedVpas = blockedVpas;
//        }

        public List<YblScanPayListBlockedVPADto> getBlockedVpasList() {
            return blockedVpasList;
        }

        public void setBlockedVpasList(List<YblScanPayListBlockedVPADto> blockedVpasList) {
            this.blockedVpasList = blockedVpasList;
        }

//        List<YblScanPayListBlockedVPADto> blockedVpasList;
    }
    public static class YblScanPayListBlockedVPADto {
        public String getPayeeVpa() {
            return payeeVpa;
        }

        public void setPayeeVpa(String payeeVpa) {
            this.payeeVpa = payeeVpa;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBlockedAt() {
            return blockedAt;
        }

        public void setBlockedAt(String blockedAt) {
            this.blockedAt = blockedAt;
        }

        String payeeVpa;
        String name;
        String blockedAt;
    }
}
