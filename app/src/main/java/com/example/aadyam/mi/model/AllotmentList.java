package com.example.aadyam.mi.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllotmentList

{

    @NonNull
    @Override
    public String toString()
    {
        return "{"+"address:"+address+" mobileNo:"+mobileNo+" Consumer Name:"+consumerName+" Consumer Number:"+consumerNo;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAreaRefNo(Long areaRefNo) {
        this.areaRefNo = areaRefNo;
    }

    private int id;
        @SerializedName("Address")
        @Expose
        private String address;
        @SerializedName("AllotmentId")
        @Expose
        private Integer allotmentId;
        @SerializedName("AllottedDate")
        @Expose
        private String allottedDate;
        @SerializedName("AreaName")
        @Expose
        private String areaName;
        @SerializedName("AreaRefNo")
        @Expose
        private Long areaRefNo;
        @SerializedName("ConsumerName")
        @Expose
        private String consumerName;
        @SerializedName("ConsumerNo")
        @Expose
        private Integer consumerNo;
        @SerializedName("Image1")
        @Expose
        private Integer image1;
        @SerializedName("Image2")
        @Expose
        private Integer image2;
        @SerializedName("Image3")
        @Expose
        private Integer image3;
        @SerializedName("Image4")
        @Expose
        private Integer image4;
        @SerializedName("Image5")
        @Expose
        private Integer image5;
        @SerializedName("Image6")
        @Expose
        private Integer image6;
        @SerializedName("Image7")
        @Expose
        private Integer image7;
        @SerializedName("Image8")
        @Expose
        private Integer image8;
        @SerializedName("InspFormId")
        @Expose
        private String inspFormId;
        @SerializedName("IsCompleted")
        @Expose
        private Integer isCompleted;
        @SerializedName("IsDenied")
        @Expose
        private Integer isDenied;
        @SerializedName("IsInMobile")
        @Expose
        private Integer isInMobile;
        @SerializedName("IsMobCompleted")
        @Expose
        private Integer isMobCompleted;
        @SerializedName("IsNotAvailable")
        @Expose
        private Integer isNotAvailable;
        @SerializedName("IsPrevDenied")
        @Expose
        private Integer isPrevDenied;
        @SerializedName("IsPrevNotAvailable")
        @Expose
        private Integer isPrevNotAvailable;
        @SerializedName("IsSatisfactory")
        @Expose
        private boolean isSatisfactory;
        @SerializedName("IsSyncCompleted")
        @Expose
        private Integer isSyncCompleted;
        @SerializedName("IsUnsafe")
        @Expose
        private Integer isUnsafe;
        @SerializedName("IsUnsafeReallot")
        @Expose
        private Integer isUnsafeReallot;
        @SerializedName("Location")
        @Expose
        private Integer location;
        @SerializedName("MobileNo")
        @Expose
        private Long mobileNo;
        @SerializedName("QuestionVersion")
        @Expose
        private String questionVersion;
        @SerializedName("RefAllotmentId")
        @Expose
        private Integer refAllotmentId;
        @SerializedName("RefInspFormId")
        @Expose
        private Integer refInspFormId;
        @SerializedName("SignatureFlag")
        @Expose
        private Integer signatureFlag;
        @SerializedName("TotalCount")
        @Expose
        private Integer totalCount;
        @SerializedName("UniqueConsumerId")
        @Expose
        private Long uniqueConsumerId;
        @SerializedName("UnsafeInspId")
        @Expose
        private Integer unsafeInspId;
        @SerializedName("UnsafeSignatureFlag")
        @Expose
        private Integer unsafeSignatureFlag;
        @SerializedName("UploadedQuestions")
        @Expose
        private Integer uploadedQuestions;
        @SerializedName("lastInspDate")
        @Expose
        private String lastInspDate;
        @SerializedName("lastupdateddate")
        @Expose
        private String lastupdateddate;


    public String getAddress()
    {
            return address;
    }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getAllotmentId() {
            return allotmentId;
        }

        public void setAllotmentId(Integer allotmentId) {
            this.allotmentId = allotmentId;
        }

        public String getAllottedDate() {
            return allottedDate;
        }

        public void setAllottedDate(String allottedDate) {
            this.allottedDate = allottedDate;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public Long getAreaRefNo() {
            return areaRefNo;
        }

        public void setAreaRefNo(long areaRefNo) {
            this.areaRefNo = areaRefNo;
        }

        public String getConsumerName() {
            return consumerName;
        }

        public void setConsumerName(String consumerName) {
            this.consumerName = consumerName;
        }

        public Integer getConsumerNo() {
            return consumerNo;
        }

        public void setConsumerNo(Integer consumerNo) {
            this.consumerNo = consumerNo;
        }

        public Integer getImage1() {
            return image1;
        }

        public void setImage1(Integer image1) {
            this.image1 = image1;
        }

        public Integer getImage2() {
            return image2;
        }

        public void setImage2(Integer image2) {
            this.image2 = image2;
        }

        public Integer getImage3() {
            return image3;
        }

        public void setImage3(Integer image3) {
            this.image3 = image3;
        }

        public Integer getImage4() {
            return image4;
        }

        public void setImage4(Integer image4) {
            this.image4 = image4;
        }

        public Integer getImage5() {
            return image5;
        }

        public void setImage5(Integer image5) {
            this.image5 = image5;
        }

        public Integer getImage6() {
            return image6;
        }

        public void setImage6(Integer image6) {
            this.image6 = image6;
        }

        public Integer getImage7() {
            return image7;
        }

        public void setImage7(Integer image7) {
            this.image7 = image7;
        }

        public Integer getImage8() {
            return image8;
        }

        public void setImage8(Integer image8) {
            this.image8 = image8;
        }

        public String getInspFormId() {
            return inspFormId;
        }

        public void setInspFormId(String inspFormId) {
            this.inspFormId = inspFormId;
        }

        public Integer getIsCompleted() {
            return isCompleted;
        }

        public void setIsCompleted(Integer isCompleted) {
            this.isCompleted = isCompleted;
        }

        public Integer getIsDenied() {
            return isDenied;
        }

        public void setIsDenied(Integer isDenied) {
            this.isDenied = isDenied;
        }

        public Integer getIsInMobile() {
            return isInMobile;
        }

        public void setIsInMobile(Integer isInMobile) {
            this.isInMobile = isInMobile;
        }

        public Integer getIsMobCompleted() {
            return isMobCompleted;
        }

        public void setIsMobCompleted(Integer isMobCompleted) {
            this.isMobCompleted = isMobCompleted;
        }

        public Integer getIsNotAvailable() {
            return isNotAvailable;
        }

        public void setIsNotAvailable(Integer isNotAvailable) {
            this.isNotAvailable = isNotAvailable;
        }

        public Integer getIsPrevDenied() {
            return isPrevDenied;
        }

        public void setIsPrevDenied(Integer isPrevDenied) {
            this.isPrevDenied = isPrevDenied;
        }

        public Integer getIsPrevNotAvailable() {
            return isPrevNotAvailable;
        }

        public void setIsPrevNotAvailable(Integer isPrevNotAvailable) {
            this.isPrevNotAvailable = isPrevNotAvailable;
        }

        public boolean getIsSatisfactory() {
            return isSatisfactory;
        }

        public void setIsSatisfactory(boolean isSatisfactory) {
            this.isSatisfactory = isSatisfactory;
        }

        public Integer getIsSyncCompleted() {
            return isSyncCompleted;
        }

        public void setIsSyncCompleted(Integer isSyncCompleted) {
            this.isSyncCompleted = isSyncCompleted;
        }

        public Integer getIsUnsafe() {
            return isUnsafe;
        }

        public void setIsUnsafe(Integer isUnsafe) {
            this.isUnsafe = isUnsafe;
        }

        public Integer getIsUnsafeReallot() {
            return isUnsafeReallot;
        }

        public void setIsUnsafeReallot(Integer isUnsafeReallot) {
            this.isUnsafeReallot = isUnsafeReallot;
        }

        public Integer getLocation() {
            return location;
        }

        public void setLocation(Integer location) {
            this.location = location;
        }

        public Long getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(Long mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getQuestionVersion() {
            return questionVersion;
        }

        public void setQuestionVersion(String questionVersion) {
            this.questionVersion = questionVersion;
        }

        public Integer getRefAllotmentId() {
            return refAllotmentId;
        }

        public void setRefAllotmentId(Integer refAllotmentId) {
            this.refAllotmentId = refAllotmentId;
        }

        public Integer getRefInspFormId() {
            return refInspFormId;
        }

        public void setRefInspFormId(Integer refInspFormId) {
            this.refInspFormId = refInspFormId;
        }

        public Integer getSignatureFlag() {
            return signatureFlag;
        }

        public void setSignatureFlag(Integer signatureFlag) {
            this.signatureFlag = signatureFlag;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Long getUniqueConsumerId() {
            return uniqueConsumerId;
        }

        public void setUniqueConsumerId(Long uniqueConsumerId) {
            this.uniqueConsumerId = uniqueConsumerId;
        }

        public Integer getUnsafeInspId() {
            return unsafeInspId;
        }

        public void setUnsafeInspId(Integer unsafeInspId) {
            this.unsafeInspId = unsafeInspId;
        }

        public Integer getUnsafeSignatureFlag() {
            return unsafeSignatureFlag;
        }

        public void setUnsafeSignatureFlag(Integer unsafeSignatureFlag) {
            this.unsafeSignatureFlag = unsafeSignatureFlag;
        }

        public Integer getUploadedQuestions() {
            return uploadedQuestions;
        }

        public void setUploadedQuestions(Integer uploadedQuestions) {
            this.uploadedQuestions = uploadedQuestions;
        }

        public String getLastInspDate()
        {
            return lastInspDate;
        }

        public void setLastInspDate(String lastInspDate)
        {
            this.lastInspDate = lastInspDate;
        }

        public String getLastupdateddate()
        {
            return lastupdateddate;
        }

        public void setLastupdateddate(String lastupdateddate)
        {
            this.lastupdateddate = lastupdateddate;
        }


    public void setJsonData(String json)
    {

    }
}
