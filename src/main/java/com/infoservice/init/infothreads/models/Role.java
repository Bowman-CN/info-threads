package com.infoservice.init.infothreads.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userMetrics")
public class Role {
    public Role(ObjectId _id, String name, String rolefor, String description) {
        this._id = _id;
        this.name = name;
        this.rolefor = rolefor;
        this.description = description;
    }

    public Role() {
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
    public String rolefor;
    public String description;
}
