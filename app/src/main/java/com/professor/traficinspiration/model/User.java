package com.professor.traficinspiration.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class User {

    private long id;
    private long idReferrer;
    private String name;
    private String password;
    private double balance;
    private String email;
//    private long createdAt;
    private long ordersCompleted;
    private long referralsCount;
    private Uri photoUrl;
    private double referralIncome;

    private Drawable photo;

    public User() {
    }

//    public User(long id, long idReferrer, String name, String password, double balance, String email, long createdAt, long ordersCompleted, long referralsCount) {
//        super();
//        this.id = id;
//        this.idReferrer = idReferrer;
//        this.name = name;
//        this.password = password;
//        this.balance = balance;
//        this.email = email;
//        this.createdAt = createdAt;
//        this.ordersCompleted = ordersCompleted;
//        this.referralsCount = referralsCount;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdReferrer() {
        return idReferrer;
    }

    public void setIdReferrer(long idReferrer) {
        this.idReferrer = idReferrer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public long getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(long createdAt) {
//        this.createdAt = createdAt;
//    }

    public long getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(long ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public long getReferralsCount() {
        return referralsCount;
    }

    public void setReferralsCount(long referralsCount) {
        this.referralsCount = referralsCount;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getReferralIncome() {
        return referralIncome;
    }

    public void setReferralIncome(double referralIncome) {
        this.referralIncome = referralIncome;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }
}
