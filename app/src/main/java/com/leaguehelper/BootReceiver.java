package com.leaguehelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        System.out.println("onReceive");
        Toast.makeText(context, "repeating alarm",Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, TargetService.class));
    }
}