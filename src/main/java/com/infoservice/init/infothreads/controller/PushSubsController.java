package com.infoservice.init.infothreads.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.infoservice.init.infothreads.models.InfoStreams;
import com.infoservice.init.infothreads.models.PushMessageContent;
import com.infoservice.init.infothreads.models.PushSubscriptions;
import com.infoservice.init.infothreads.repos.PushSubsRepository;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/subs")
public class PushSubsController {
    private static final String sw_private_key = "2lkcbFfPPTx0HroaBOueExNxjugBc_aG-TWEJo_WoIg";
    private static final String sw_public_key = "BPHE2DRD5op34awPO6JDAss95zuAy4x7aa9GMqQNZ2TjzhX05xVB8gFizhsJ4ONaD4yIyo5jysDp7_Aw5kmKvO8";
    @Autowired
    private PushSubsRepository pushSubsRepo;

    @GetMapping
    public List<PushSubscriptions> getAll() {
        return pushSubsRepo.findAll();
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public PushSubscriptions addPushSub(@Valid @RequestBody PushSubscriptions pushSub) {
        pushSub.set_id(ObjectId.get());
        pushSubsRepo.save(pushSub);
        return pushSub;
    }

    @RequestMapping(value = "/push", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public String send(@RequestBody PushMessageContent payload) {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notification", payload);
        List<PushSubscriptions> allSubs = this.pushSubsRepo.findAll();
        PushService pService = new PushService();
        Notification notification;
        try {
            pService.setPrivateKey(this.sw_private_key);
            pService.setPublicKey(this.sw_public_key);

            for (PushSubscriptions sub : allSubs) {
                notification = new Notification(
                        sub.endpoint, sub.keys.p256dh, sub.keys.auth, JSON.toJSONString(jsonObject)
                );

                pService.send(notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Notification Pushed";
    }
}
