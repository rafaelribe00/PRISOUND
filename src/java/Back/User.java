/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

/**
 *
 * @author ASUS
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String age;
    private String nIdentify;
    private String email;


    public User() {
    }    
    // getters and setters are not shown for brevity   

    void setUsername(String username) {
        this.username=username;
    }
    
    void setPassword(String password) {
        this.password=password;
    }
    
    void setName(String name) {
        this.name=name;
    }
    
    void setAge(String age) {
        this.age=age;
    }
    
    void setNIdentify(String nIdentify) {
        this.nIdentify=nIdentify;
    }
    
    void setEmail(String email) {
        this.email=email;
    }
     
}