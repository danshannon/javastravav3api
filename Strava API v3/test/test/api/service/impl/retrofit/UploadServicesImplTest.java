package test.api.service.impl.retrofit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaUploadResponse;
import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.service.ActivityServices;
import stravajava.api.v3.service.UploadServices;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.ActivityServicesImpl;
import stravajava.api.v3.service.impl.retrofit.UploadServicesImpl;
import test.utils.TestUtils;

public class UploadServicesImplTest {

	/**
	 * Test method for {@link stravajava.api.v3.service.impl.retrofit.StreamServicesImpl#implementation(java.lang.String)}.
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		UploadServices service = UploadServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Didn't get a service implementation using a valid token",service);
		StravaUploadResponse response = service.checkUploadStatus(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(response);
	}
	
	@Test
	public void testImplementation_invalidToken() {
		try {
			UploadServices service = UploadServicesImpl.implementation(TestUtils.INVALID_TOKEN);
			service.checkUploadStatus(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a usable implementation from an invalid token");
	}

	@Test
	public void testUpload_valid() throws InterruptedException, UnauthorizedException, NotFoundException, BadRequestException {
		UploadServices service = getService();
		File file = new File("hyperdrive.gpx");
		StravaUploadResponse response = service.upload(StravaActivityType.RIDE, "UploadServicesImplTest", null, null, null, "gpx", "ABC", file);
		Integer id = response.getId();
		boolean loop = true;
		while (loop) {
			response = service.checkUploadStatus(id);
			System.out.println(response);
			if (!response.getStatus().equals("Your activity is still being processed.")) {
				loop = false;
			} else {
				Thread.sleep(1000);
			}
		}
		if (response.getStatus().equals("Your activity is ready.")) {
			ActivityServices activityService = ActivityServicesImpl.implementation(TestUtils.getValidToken());
			loop = true;
			while (loop) {
				StravaActivity activity = activityService.getActivity(response.getActivityId());
				if (activity != null && activity.getResourceState() != StravaResourceState.UPDATING) {
					loop = false;
				} else {
					Thread.sleep(1000);
				}
			}
			activityService.deleteActivity(response.getActivityId());
		}
	}
	
	@Test
	public void testUpload_noWriteAccess() throws BadRequestException, UnauthorizedException, InterruptedException, NotFoundException {
		UploadServices service = getServiceWithoutWriteAccess();
		File file = new File("hyperdrive.gpx");
		try {
			service.upload(StravaActivityType.RIDE, "UploadServicesImplTest.testUpoad_noWriteAccess", null,Boolean.TRUE, null, "gpx", "testUpload_noWriteAccess", file);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		
		// Fail
		fail("Uploaded an activity without write access!");

	}

	@Test
	public void testCheckUploadStatus() throws UnauthorizedException {
		UploadServices service = getService();
		StravaUploadResponse response = service.checkUploadStatus(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(response);
	}
	
	private UploadServices getService() {
		return UploadServicesImpl.implementation(TestUtils.getValidToken());
	}
	
	private UploadServices getServiceWithoutWriteAccess() {
		return UploadServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}

}