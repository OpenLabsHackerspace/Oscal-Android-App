package org.almotech.oscal.model;

/**
 * Created by Armando Shkurti on 2015-04-02.
 */
public class AppModel {

    public String name;
    public String packageName;

    public AppModel(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
