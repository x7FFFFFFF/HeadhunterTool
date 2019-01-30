package ru.alex.vic.hhtool.jpa;


import ru.alex.vic.hhtool.json.HHLocationJson;

import javax.persistence.Entity;


@Entity
public class HHLocation extends Location {


    public HHLocation(HHLocationJson json) {
        this.setVendorId(json.getId());
        this.setName(parenthesesRemove(json.getName()));
        this.setParentVendorId(json.getParentId());
        this.setHasChilds(json.isHasChilds());
        if (json.getParentId() == null) {
            this.setLocationType(LocationType.COUNTRY);
        } else if (json.isHasChilds()) {
            this.setLocationType(LocationType.REGION);
        } else {
            this.setLocationType(LocationType.CITY);
        }
    }

    static String parenthesesRemove(String name) {
        final int start = name.indexOf('(');
        if (start != -1) {

            return name.substring(0, start).trim();

        }
        return name;
    }


}
