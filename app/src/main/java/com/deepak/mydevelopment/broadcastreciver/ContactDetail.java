package com.deepak.mydevelopment.broadcastreciver;

/**
 * Created by dsk on 13-Apr-18.
 */

public class ContactDetail {
    String mob;
    int id;

    public ContactDetail(int id,String number)
    {
        this.setId(id);
        this.setMob(number);

    }
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
