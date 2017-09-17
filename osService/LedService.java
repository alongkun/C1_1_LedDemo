package com.android.server;

import android.os.ILedService;

public class LedService extends ILedService.Stub {
	private static final String TAG = "LedService";

	public LedService() {
		native_ledOpen();	
	}

	public int ledCtrl(int led, int status) throws android.os.RemoteException {
		return nativa_ledCtrl(led, status);
	}
	
	public int native native_ledOpen();
	public void native native_ledClose();
	public int native native_ledCtrl(int led, int status);
}
 
