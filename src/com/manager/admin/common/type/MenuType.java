package com.manager.admin.common.type;

public enum MenuType {
    PUBLIC(0, "共用"), ADMIN(1, "运营"), BUSINESS(2, "商务");

    private Integer id;

    private String name;

    private MenuType(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public static MenuType getById(Integer id) {
        if(null==id){
            return null;
        }
        for(MenuType tmp: MenuType.values()) {
            if(tmp.id.intValue() == id.intValue()) {
                return tmp;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

}
