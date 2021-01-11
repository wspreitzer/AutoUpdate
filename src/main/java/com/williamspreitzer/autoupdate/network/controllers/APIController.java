package com.williamspreitzer.autoupdate.network.controllers;

import java.io.IOException;

import com.williamspreitzer.autoupdate.domain.APIResponse;

public abstract class APIController implements Callback<APIResponse> {
	
	public abstract APIResponse start(String[] props) throws IOException;
}
