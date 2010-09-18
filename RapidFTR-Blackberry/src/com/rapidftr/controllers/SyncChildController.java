package com.rapidftr.controllers;

import com.rapidftr.controllers.internal.RequestAwareController;
import com.rapidftr.model.Child;
import com.rapidftr.screens.SyncChildScreen;
import com.rapidftr.screens.internal.CustomScreen;
import com.rapidftr.screens.internal.UiStack;
import com.rapidftr.services.ChildSyncService;

public class SyncChildController extends RequestAwareController {
	public SyncChildController(CustomScreen screen, UiStack uiStack,
			ChildSyncService childSyncService) {
		super(screen, uiStack, childSyncService);
	}

	public void cancelUpload() {
		service.cancelRequest();
	}

	public void login() {
		dispatcher.login();
	}

	public void syncChildRecord(Child child) {
		((ChildSyncService) service).uploadChildRecord(child);
		((SyncChildScreen) currentScreen).resetProgressBar();
		show();
	}

	public void syncAllChildRecords() {
		try {
			((ChildSyncService) service).syncAllChildRecords();
			//((SyncChildScreen) screen).resetProgressBar();
			show();
		} catch (Exception e) {
			requestHandler.markProcessFailed();
		}
	}

	public void clearOfflineData() {
		((ChildSyncService) service).clearState();		
	}

}
