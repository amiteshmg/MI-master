package com.example.aadyam.mi.model;

import com.google.gson.annotations.SerializedName;

public class DistributorList {

    @SerializedName("ContactPhone2")
    private String contactPhone2;

    @SerializedName("DistributorId")
    private int distributorId;

    @SerializedName("StaffInitials")
    private String staffInitials;

    @SerializedName("UserName")
    private Object userName;

    @SerializedName("WhetherDeliveryBoy")
    private String whetherDeliveryBoy;

    @SerializedName("LastUpdateDateTime")
    private Object lastUpdateDateTime;

    @SerializedName("StaffId")
    private int staffId;

    @SerializedName("ActiveTo")
    private Object activeTo;

    @SerializedName("ContactPhone1")
    private String contactPhone1;

    @SerializedName("DistributorName")
    private String distributorName;

    @SerializedName("WhetherMechanic")
    private String whetherMechanic;

    @SerializedName("StaffAddress")
    private Object staffAddress;

    @SerializedName("StatusCode")
    private String statusCode;

    @SerializedName("StaffName")
    private String staffName;

    @SerializedName("ActiveFrom")
    private Object activeFrom;

    @SerializedName("Result")
    private String result;

    @SerializedName("WhetherOther")
    private String whetherOther;

    @SerializedName("AreaRefNo")
    private int areaRefNo;

    @SerializedName("StaffRefNo")
    private long staffRefNo;

    @SerializedName("WhetherDeliveryBoyND")
    private String whetherDeliveryBoyND;

    @SerializedName("WhetherEmployee")
    private String whetherEmployee;

    @SerializedName("WhetherESCMechanic")
    private String whetherESCMechanic;

    @SerializedName("StaffStatusCode")
    private String staffStatusCode;

    @SerializedName("Password")
    private Object password;

    public void setContactPhone2(String contactPhone2){
        this.contactPhone2 = contactPhone2;
    }

    public String getContactPhone2(){
        return contactPhone2;
    }

    public void setDistributorId(int distributorId){
        this.distributorId = distributorId;
    }

    public int getDistributorId(){
        return distributorId;
    }

    public void setStaffInitials(String staffInitials){
        this.staffInitials = staffInitials;
    }

    public String getStaffInitials(){
        return staffInitials;
    }

    public void setUserName(Object userName){
        this.userName = userName;
    }

    public Object getUserName(){
        return userName;
    }

    public void setWhetherDeliveryBoy(String whetherDeliveryBoy){
        this.whetherDeliveryBoy = whetherDeliveryBoy;
    }

    public String getWhetherDeliveryBoy(){
        return whetherDeliveryBoy;
    }

    public void setLastUpdateDateTime(Object lastUpdateDateTime){
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Object getLastUpdateDateTime(){
        return lastUpdateDateTime;
    }

    public void setStaffId(int staffId){
        this.staffId = staffId;
    }

    public int getStaffId(){
        return staffId;
    }

    public void setActiveTo(Object activeTo){
        this.activeTo = activeTo;
    }

    public Object getActiveTo(){
        return activeTo;
    }

    public void setContactPhone1(String contactPhone1){
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone1(){
        return contactPhone1;
    }

    public void setDistributorName(String distributorName){
        this.distributorName = distributorName;
    }

    public String getDistributorName(){
        return distributorName;
    }

    public void setWhetherMechanic(String whetherMechanic){
        this.whetherMechanic = whetherMechanic;
    }

    public String getWhetherMechanic(){
        return whetherMechanic;
    }

    public void setStaffAddress(Object staffAddress){
        this.staffAddress = staffAddress;
    }

    public Object getStaffAddress(){
        return staffAddress;
    }

    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatusCode(){
        return statusCode;
    }

    public void setStaffName(String staffName){
        this.staffName = staffName;
    }

    public String getStaffName(){
        return staffName;
    }

    public void setActiveFrom(Object activeFrom){
        this.activeFrom = activeFrom;
    }

    public Object getActiveFrom(){
        return activeFrom;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    public void setWhetherOther(String whetherOther){
        this.whetherOther = whetherOther;
    }

    public String getWhetherOther(){
        return whetherOther;
    }

    public void setAreaRefNo(int areaRefNo){
        this.areaRefNo = areaRefNo;
    }

    public int getAreaRefNo(){
        return areaRefNo;
    }

    public void setStaffRefNo(int staffRefNo){
        this.staffRefNo = staffRefNo;
    }

    public long getStaffRefNo(){
        return staffRefNo;
    }

    public void setWhetherDeliveryBoyND(String whetherDeliveryBoyND){
        this.whetherDeliveryBoyND = whetherDeliveryBoyND;
    }

    public String getWhetherDeliveryBoyND(){
        return whetherDeliveryBoyND;
    }

    public void setWhetherEmployee(String whetherEmployee){
        this.whetherEmployee = whetherEmployee;
    }

    public String getWhetherEmployee(){
        return whetherEmployee;
    }

    public void setWhetherESCMechanic(String whetherESCMechanic){
        this.whetherESCMechanic = whetherESCMechanic;
    }

    public String getWhetherESCMechanic(){
        return whetherESCMechanic;
    }

    public void setStaffStatusCode(String staffStatusCode){
        this.staffStatusCode = staffStatusCode;
    }

    public String getStaffStatusCode(){
        return staffStatusCode;
    }

    public void setPassword(Object password){
        this.password = password;
    }

    public Object getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return
                "SendOTPSMSResultItem{" +
                        "contactPhone2 = '" + contactPhone2 + '\'' +
                        ",distributorId = '" + distributorId + '\'' +
                        ",staffInitials = '" + staffInitials + '\'' +
                        ",userName = '" + userName + '\'' +
                        ",whetherDeliveryBoy = '" + whetherDeliveryBoy + '\'' +
                        ",lastUpdateDateTime = '" + lastUpdateDateTime + '\'' +
                        ",staffId = '" + staffId + '\'' +
                        ",activeTo = '" + activeTo + '\'' +
                        ",contactPhone1 = '" + contactPhone1 + '\'' +
                        ",distributorName = '" + distributorName + '\'' +
                        ",whetherMechanic = '" + whetherMechanic + '\'' +
                        ",staffAddress = '" + staffAddress + '\'' +
                        ",statusCode = '" + statusCode + '\'' +
                        ",staffName = '" + staffName + '\'' +
                        ",activeFrom = '" + activeFrom + '\'' +
                        ",result = '" + result + '\'' +
                        ",whetherOther = '" + whetherOther + '\'' +
                        ",areaRefNo = '" + areaRefNo + '\'' +
                        ",staffRefNo = '" + staffRefNo + '\'' +
                        ",whetherDeliveryBoyND = '" + whetherDeliveryBoyND + '\'' +
                        ",whetherEmployee = '" + whetherEmployee + '\'' +
                        ",whetherESCMechanic = '" + whetherESCMechanic + '\'' +
                        ",staffStatusCode = '" + staffStatusCode + '\'' +
                        ",password = '" + password + '\'' +
                        "}";
    }
}
