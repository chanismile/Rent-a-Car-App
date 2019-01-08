package com.example.rentacar.model.entities;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Branch
{
    private String address;
    private String  numOfParking;
    private int branchNo;
    //..................................constructors...............................................
    public Branch() {
    }

    public Branch(String address, String numOfParking) {
        this.address = address;
        this.numOfParking = numOfParking;

    }
    //....................................getters ans setters......................................

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumOfParking() {
        return numOfParking;
    }

    public void setNumOfParking(String numOfParking) {
        this.numOfParking = numOfParking;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }
//............................Administration.......................................................
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (getNumOfParking() != branch.getNumOfParking()) return false;
        if (getBranchNo() != branch.getBranchNo()) return false;
        return getAddress() != null ? getAddress().equals(branch.getAddress()) : branch.getAddress() == null;

    }

    @Override
    public String toString() {
        return
                "  address: " + address + "\n"+
                "  numOfParking: " + numOfParking +"\n"+
                "  branchNo: " + branchNo +"\n";
    }


}
