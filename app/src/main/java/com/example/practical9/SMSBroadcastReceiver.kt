package com.example.practical9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony

class SMSBroadcastReceiver : BroadcastReceiver() {
    interface Listener{
        fun onTextReceived(sPhone: String?, sMsg : String?)
    }
    var listener : Listener? = null
    override fun onReceive(p0: Context?, intent: Intent) {
        if(intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            var sPhone =""
            var sSMSBODY = ""
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                    sPhone = smsMessage.displayOriginatingAddress
                    sSMSBODY += smsMessage.messageBody
                }
                if (listener != null){
                    listener?.onTextReceived(sPhone,sSMSBODY)
                }
            }
        }
    }
}