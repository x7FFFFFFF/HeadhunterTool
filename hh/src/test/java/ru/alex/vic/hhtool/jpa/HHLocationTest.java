package ru.alex.vic.hhtool.jpa;

import org.junit.Assert;
import org.junit.Test;
import ru.alex.vic.hhtool.json.HHLocationJson;

public class HHLocationTest {

    @Test
    public void parenthesesRemoveTest() {
        final String res = HHLocation.parenthesesRemove("Каменка (Кабардино-Балкария)");
        Assert.assertEquals("Каменка", res);
    }
}