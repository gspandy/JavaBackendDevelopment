package com.lavor.springboot.sql.jpa;

import javax.persistence.*;

/**
 * Created by lei.zeng on 2017/8/2.
 * @Entity注解表示实体类，@Table(name = "user")表示对应数据库中的user表
 */
@Entity
@Table(name = "user")
public class User {
    /**
     * @Id注解表示主键，@GeneratedValue(strategy = GenerationType.AUTO)表示自动获取值，获取策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * @Column(name="name")表示对应数据库表中的name字段
     */
    @Column(name="name")
    private String name;
    public User() {
    }
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
