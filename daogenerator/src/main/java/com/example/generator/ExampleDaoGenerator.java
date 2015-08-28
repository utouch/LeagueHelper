/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.generator;


import com.example.daogenerator.DaoGenerator;
import com.example.daogenerator.Entity;
import com.example.daogenerator.Property;
import com.example.daogenerator.Schema;
import com.example.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.leaguehelper.beans");
        schema.setDefaultJavaPackageDao("com.leaguehelper.dao");
        addServerArea(schema);
        addHero(schema);
        new DaoGenerator().generateAll(schema, "/Users/utouch/Documents/LeagueHelper/app/src/main/java");
    }

    private static void addServerArea(Schema schema) {
        Entity note = schema.addEntity("ServerArea");
        note.setTableName("bb");
        note.addIdProperty().primaryKey().autoincrement();
        note.addStringProperty("sn").notNull();
        note.addStringProperty("fn").notNull();
        note.addStringProperty("snEn").notNull();
    }


    private static void addHero(Schema schema) {
        Entity note = schema.addEntity("Hero");
        note.addIdProperty().primaryKey().autoincrement().unique();
        note.addStringProperty("sn").notNull();
        note.addStringProperty("fn").notNull();
        note.addStringProperty("snEn").notNull();
    }


    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

}
