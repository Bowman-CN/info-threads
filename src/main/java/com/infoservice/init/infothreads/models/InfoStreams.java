package com.infoservice.init.infothreads.models;

import org.bson.BsonTimestamp;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/*
 * At least for MONGODB,
 * */
@Document(collection = "infostreams")
public class InfoStreams {
    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Id
    public ObjectId _id;
    public String infoBody;
    public String infoLink;
    public Boolean hasImage;
    public String image;
    public String imgLink;
    public Date infoTimestamp;
    public String author;

    public InfoStreams(ObjectId _id, String infoBody, String infoLink, Boolean hasiamge, String imaged, String imgLink, Date infoTimestamp, String author) {
        this._id = _id;
        this.infoBody = infoBody;
        this.infoLink = infoLink;
        this.hasImage = hasiamge;
        this.image = imaged;
        this.imgLink = imgLink;
        this.infoTimestamp = infoTimestamp;
        this.author = author;
    }

    public InfoStreams() {
    }
}
