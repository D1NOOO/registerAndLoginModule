package com.dino.registermodule.removal;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class MailVerificationCodeRemovalListener implements RemovalListener<String,Object>{

    @Override
    public void onRemoval(RemovalNotification<String, Object> removalNotification) {

    }
}
