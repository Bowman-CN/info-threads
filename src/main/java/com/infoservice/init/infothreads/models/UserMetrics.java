package com.infoservice.init.infothreads.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "userMetrics")
public class UserMetrics {
    public UserMetrics(ObjectId _id, String name, String email, String mobile, String avatar, String password, List<Role> roles) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.avatar = avatar;
        this.password = password;
        this.roles = roles;
    }

    public UserMetrics() {
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Id
    public ObjectId _id;
    public String name;
    public String email;
    public String mobile;
    public String avatar;
    public String password;
    public List<Role> roles;

}
