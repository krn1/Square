
package com.square.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeList implements Parcelable {

    @SerializedName("employees")
    @Expose
    private List<Employee> employees = null;

    public List<Employee> get() {
        return employees;
    }

    public void set(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.employees);
    }

    public EmployeeList() {
    }

    protected EmployeeList(Parcel in) {
        this.employees = new ArrayList<Employee>();
        in.readList(this.employees, Employee.class.getClassLoader());
    }

    public static final Parcelable.Creator<EmployeeList> CREATOR = new Parcelable.Creator<EmployeeList>() {
        @Override
        public EmployeeList createFromParcel(Parcel source) {
            return new EmployeeList(source);
        }

        @Override
        public EmployeeList[] newArray(int size) {
            return new EmployeeList[size];
        }
    };
}
