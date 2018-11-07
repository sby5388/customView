package com.by5388.xw.searchframe;

/**
 * 联系人
 *
 * @author by5388  on 2018/11/7.
 */
public class LinkMan {
    private String name;
    private String telephone;
    private long id;

    public long getId() {
        return id;
    }

    public LinkMan setId(long id) {
        this.id = id;
        return this;
    }

    public LinkMan() {
    }

    public String getName() {
        return name;
    }

    public LinkMan setName(String name) {
        this.name = name;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public LinkMan setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
