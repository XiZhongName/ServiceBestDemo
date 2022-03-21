package com.example.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeWork01 {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();
        dao.save("001",new User(1,12,"张三"));
        dao.save("002",new User(2,16,"李四"));
        dao.delete("002");
        dao.get("001");
        dao.list();
    }
}

 class DAO<T>{
    private Map<String,T> map = new HashMap<>();

    public void save(String id,T entity){
        map.put(id,entity);
    }
    public T get(String id){

        return map.get(id);
    }
    public void update(String id,T entity){
        map.put(id,entity);
    }
    public void delete(String id){
        map.remove(id);
    }
    public List<T> list(){
        List<T> list = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            list.add(map.get(key));
        }
        return list;
    }
}
class User{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}





















