/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nosql_jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author Batuhan
 */
@Embeddable
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Tags implements Serializable {

    @Basic
    private String tag;

    public Tags() {

    }

    public Tags(String tag) {
       this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
