package org.almotech.oscal.utils;

import com.squareup.otto.Bus;

/**
 * Created by Armando Shkurti on 2015-03-23.
 */
public class BusProvider {

    private static final Bus BUS = new Bus(); // me lib

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){}
}
