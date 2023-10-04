package entity;

import base.domain.BaseEntity;

import java.util.List;

public class Cart extends BaseEntity<Integer> {
    private int userId;
    private int shoeId;
    private int electronicAppliancesId;


    public Cart(Integer integer, int userId, int electronicAppliancesId, int shoeId) {
        super(integer);
        this.userId = userId;
        this.electronicAppliancesId = electronicAppliancesId;
        this.shoeId = shoeId;
    }

    public Cart(Integer integer) {
        super(integer);
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    public int getElectronicAppliancesId() {
        return electronicAppliancesId;
    }

    public void setElectronicAppliancesId(int electronicAppliancesId) {
        this.electronicAppliancesId = electronicAppliancesId;
    }

}
