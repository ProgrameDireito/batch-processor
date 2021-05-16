/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sitetune.batch;

import java.util.List;

/**
 *
 * @author marcos
 */
public class ItemRepository {

    public List<Item> itemList;

    public boolean add(Item e) {
        return itemList.add(e);
    }

}
