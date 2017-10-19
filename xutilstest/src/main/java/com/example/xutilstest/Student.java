package com.example.xutilstest;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Qiyan on 2017/10/15.
 */
    @Table(name = "stu")

    public class Student{
        Student(){

        }
        Student(String name, String age){
            this.name = name;
            this.age = age;
        }
        @Column(name = "_id",isId = true,autoGen = true,property = "NOT NULL")
        private int id;
        @Column(name = "stuName")
        private String name;
        @Column(name = "stuAge")
        private String age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
