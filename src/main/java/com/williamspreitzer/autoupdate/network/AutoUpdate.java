package com.williamspreitzer.autoupdate.network;

import java.io.IOException;

import com.williamspreitzer.autoupdate.domain.Release;
import com.williamspreitzer.autoupdate.network.controllers.GetLatestReleaseController;

public class AutoUpdate {

	private static String version;

	public static void main(String[] args) {
		version = args[2];
		GetLatestReleaseController controller = new GetLatestReleaseController();
		try {
			Release release = (Release) controller.start(args);
			if(version.equals(release.getId())) {
				System.out.println("You are up to date");
			} else {
				System.exit(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}