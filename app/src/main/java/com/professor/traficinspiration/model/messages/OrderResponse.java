package com.professor.traficinspiration.model.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_MAC")
    @Expose
    private String idMAC;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_user_MAC")
    @Expose
    private String idUserMAC;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_MAC")
    @Expose
    private String nameMAC;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("package_name_MAC")
    @Expose
    private String packageNameMAC;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("img_url_MAC")
    @Expose
    private String imgUrlMAC;
    @SerializedName("key_words")
    @Expose
    private String keyWords;
    @SerializedName("key_words_MAC")
    @Expose
    private String keyWordsMAC;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("payment_MAC")
    @Expose
    private String paymentMAC;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("active_MAC")
    @Expose
    private String activeMAC;
    @SerializedName("open_count")
    @Expose
    private String openCount;
    @SerializedName("open_count_MAC")
    @Expose
    private String openCountMAC;
    @SerializedName("open_interval")
    @Expose
    private String openInterval;
    @SerializedName("open_interval_MAC")
    @Expose
    private String openIntervalMAC;
    @SerializedName("count_execusions")
    @Expose
    private String countExecusions;
    @SerializedName("count_execusions_MAC")
    @Expose
    private String countExecusionsMAC;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_date_MAC")
    @Expose
    private String startDateMAC;
    @SerializedName("needed_reviews")
    @Expose
    private String neededReviews;
    @SerializedName("needed_reviews_MAC")
    @Expose
    private String neededReviewsMAC;
    @SerializedName("done_reviews")
    @Expose
    private String doneReviews;
    @SerializedName("done_reviews_MAC")
    @Expose
    private String doneReviewsMAC;
    @SerializedName("reopening_price")
    @Expose
    private String reopeningPrice;
    @SerializedName("reopening_price_MAC")
    @Expose
    private String reopeningPriceMAC;
    @SerializedName("review_price")
    @Expose
    private String reviewPrice;
    @SerializedName("review_price_MAC")
    @Expose
    private String reviewPriceMAC;
    @SerializedName("summary_price")
    @Expose
    private String summaryPrice;
    @SerializedName("summary_price_MAC")
    @Expose
    private String summaryPriceMAC;
    @SerializedName("summary_price_progress")
    @Expose
    private String summaryPriceProgress;
    @SerializedName("summary_price_progress_MAC")
    @Expose
    private String summaryPriceProgressMAC;
    private boolean comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMAC() {
        return idMAC;
    }

    public void setIdMAC(String idMAC) {
        this.idMAC = idMAC;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUserMAC() {
        return idUserMAC;
    }

    public void setIdUserMAC(String idUserMAC) {
        this.idUserMAC = idUserMAC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMAC() {
        return nameMAC;
    }

    public void setNameMAC(String nameMAC) {
        this.nameMAC = nameMAC;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageNameMAC() {
        return packageNameMAC;
    }

    public void setPackageNameMAC(String packageNameMAC) {
        this.packageNameMAC = packageNameMAC;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrlMAC() {
        return imgUrlMAC;
    }

    public void setImgUrlMAC(String imgUrlMAC) {
        this.imgUrlMAC = imgUrlMAC;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWordsMAC() {
        return keyWordsMAC;
    }

    public void setKeyWordsMAC(String keyWordsMAC) {
        this.keyWordsMAC = keyWordsMAC;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentMAC() {
        return paymentMAC;
    }

    public void setPaymentMAC(String paymentMAC) {
        this.paymentMAC = paymentMAC;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveMAC() {
        return activeMAC;
    }

    public void setActiveMAC(String activeMAC) {
        this.activeMAC = activeMAC;
    }

    public String getOpenCount() {
        return openCount;
    }

    public void setOpenCount(String openCount) {
        this.openCount = openCount;
    }

    public String getOpenCountMAC() {
        return openCountMAC;
    }

    public void setOpenCountMAC(String openCountMAC) {
        this.openCountMAC = openCountMAC;
    }

    public String getOpenInterval() {
        return openInterval;
    }

    public void setOpenInterval(String openInterval) {
        this.openInterval = openInterval;
    }

    public String getOpenIntervalMAC() {
        return openIntervalMAC;
    }

    public void setOpenIntervalMAC(String openIntervalMAC) {
        this.openIntervalMAC = openIntervalMAC;
    }

    public String getCountExecusions() {
        return countExecusions;
    }

    public void setCountExecusions(String countExecusions) {
        this.countExecusions = countExecusions;
    }

    public String getCountExecusionsMAC() {
        return countExecusionsMAC;
    }

    public void setCountExecusionsMAC(String countExecusionsMAC) {
        this.countExecusionsMAC = countExecusionsMAC;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDateMAC() {
        return startDateMAC;
    }

    public void setStartDateMAC(String startDateMAC) {
        this.startDateMAC = startDateMAC;
    }

    public String getNeededReviews() {
        return neededReviews;
    }

    public void setNeededReviews(String neededReviews) {
        this.neededReviews = neededReviews;
    }

    public String getNeededReviewsMAC() {
        return neededReviewsMAC;
    }

    public void setNeededReviewsMAC(String neededReviewsMAC) {
        this.neededReviewsMAC = neededReviewsMAC;
    }

    public String getDoneReviews() {
        return doneReviews;
    }

    public void setDoneReviews(String doneReviews) {
        this.doneReviews = doneReviews;
    }

    public String getDoneReviewsMAC() {
        return doneReviewsMAC;
    }

    public void setDoneReviewsMAC(String doneReviewsMAC) {
        this.doneReviewsMAC = doneReviewsMAC;
    }

    public String getReopeningPrice() {
        return reopeningPrice;
    }

    public void setReopeningPrice(String reopeningPrice) {
        this.reopeningPrice = reopeningPrice;
    }

    public String getReopeningPriceMAC() {
        return reopeningPriceMAC;
    }

    public void setReopeningPriceMAC(String reopeningPriceMAC) {
        this.reopeningPriceMAC = reopeningPriceMAC;
    }

    public String getReviewPrice() {
        return reviewPrice;
    }

    public void setReviewPrice(String reviewPrice) {
        this.reviewPrice = reviewPrice;
    }

    public String getReviewPriceMAC() {
        return reviewPriceMAC;
    }

    public void setReviewPriceMAC(String reviewPriceMAC) {
        this.reviewPriceMAC = reviewPriceMAC;
    }

    public String getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(String summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public String getSummaryPriceMAC() {
        return summaryPriceMAC;
    }

    public void setSummaryPriceMAC(String summaryPriceMAC) {
        this.summaryPriceMAC = summaryPriceMAC;
    }

    public String getSummaryPriceProgress() {
        return summaryPriceProgress;
    }

    public void setSummaryPriceProgress(String summaryPriceProgress) {
        this.summaryPriceProgress = summaryPriceProgress;
    }

    public String getSummaryPriceProgressMAC() {
        return summaryPriceProgressMAC;
    }

    public void setSummaryPriceProgressMAC(String summaryPriceProgressMAC) {
        this.summaryPriceProgressMAC = summaryPriceProgressMAC;
    }


    public void setComment(boolean comment) {
        this.comment = comment;
    }
}
