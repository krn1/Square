
package com.square.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.square.gson.JsonRequired;

public class Employee implements Parcelable {

    @SerializedName("uuid")
    @Expose
    @JsonRequired
    private String uuid;
    @SerializedName("full_name")
    @Expose
    @JsonRequired
    private String fullName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("email_address")
    @Expose
    @JsonRequired
    private String emailAddress;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("photo_url_small")
    @Expose
    private String photoUrlSmall;
    @SerializedName("photo_url_large")
    @Expose
    private String photoUrlLarge;
    @SerializedName("team")
    @Expose
    @JsonRequired
    private String team;
    @SerializedName("employee_type")
    @Expose
    private String employeeType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotoUrlSmall() {
        return photoUrlSmall;
    }

    public void setPhotoUrlSmall(String photoUrlSmall) {
        this.photoUrlSmall = photoUrlSmall;
    }

    public String getPhotoUrlLarge() {
        return photoUrlLarge;
    }

    public void setPhotoUrlLarge(String photoUrlLarge) {
        this.photoUrlLarge = photoUrlLarge;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.fullName);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.emailAddress);
        dest.writeString(this.biography);
        dest.writeString(this.photoUrlSmall);
        dest.writeString(this.photoUrlLarge);
        dest.writeString(this.team);
        dest.writeString(this.employeeType);
    }

    public Employee() {
    }

    protected Employee(Parcel in) {
        this.uuid = in.readString();
        this.fullName = in.readString();
        this.phoneNumber = in.readString();
        this.emailAddress = in.readString();
        this.biography = in.readString();
        this.photoUrlSmall = in.readString();
        this.photoUrlLarge = in.readString();
        this.team = in.readString();
        this.employeeType = in.readString();
    }

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
