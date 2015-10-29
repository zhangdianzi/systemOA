package com.manager.admin.common.type;

/**
 * 角色作用人员，1内勤人员、2外勤人员、3渠道人员
 * @author fei
 *
 */
public enum RoleType {
    ADMIN(1, "运营人员"), BUSINESS(2, "商务人员"), CHANNEL(3, "渠道人员");

    private Integer id;

    private String name;

    private RoleType(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public static RoleType getById(Integer id) {
        if(null==id){
            return null;
        }
        for(RoleType tmp: RoleType.values()) {
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
