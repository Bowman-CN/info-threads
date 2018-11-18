package com.infoservice.init.infothreads.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "pushsubs")
public class PushSubscriptions {
    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Id
    public ObjectId _id;
    public String endpoint;
    public Date expirationTime;
    public PushKeys keys;

    public PushSubscriptions(ObjectId _id, String endpoint, Date expirationTime, PushKeys keys) {
        this._id = _id;
        this.endpoint = endpoint;
        this.expirationTime = expirationTime;
        this.keys = keys;
    }

    public PushSubscriptions() {
    }
}

