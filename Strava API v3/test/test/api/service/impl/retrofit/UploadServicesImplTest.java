package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaUploadResponse;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.ActivityServices;
import javastrava.api.v3.service.UploadServices;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.ActivityServicesImpl;
import javastrava.api.v3.service.impl.retrofit.UploadServicesImpl;

import org.junit.Test;

import test.utils.TestUtils;

public class UploadServicesImplTest {

	/**
	 * Test method for {@link javastrava.api.v3.service.impl.retrofit.StreamServicesImpl#implementation(java.lang.String)}.
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		UploadServices service = UploadServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Didn't get a service implementation using a valid token", service);
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
	public void testImplementation_revokedToken() {
		try {
			UploadServices service = UploadServicesImpl.implementation(TestUtils.getRevokedToken());
			service.checkUploadStatus(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		fail("Got a service implementation with a valid token!");
	}

	@Test
	public void testImplementation_implementationIsCached() {
		String token = TestUtils.getValidToken();
		UploadServices service = UploadServicesImpl.implementation(token);
		UploadServices service2 = UploadServicesImpl.implementation(token);
		assertEquals(service, service2);
	}

	@Test
	public void testImplementation_differentImplementationIsNotCached() {
		UploadServices service = UploadServicesImpl.implementation(TestUtils.getValidToken());
		UploadServices service2 = UploadServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}

	@Test
	public void testUpload_valid() throws InterruptedException, UnauthorizedException, NotFoundException, BadRequestException {
		UploadServices service = getService();
		File file = new File("hyperdrive.gpx");
		StravaUploadResponse response = service.upload(StravaActivityType.RIDE, "UploadServicesImplTest", null, null, null, "gpx", "ABC", file);
		waitForCompletionAndDelete(response);
	}

	private void waitForCompletionAndDelete(final StravaUploadResponse response) {
		UploadServices service = getService();
		Integer id = response.getId();
		StravaUploadResponse localResponse = null;
		boolean loop = true;
		while (loop) {
			localResponse = service.checkUploadStatus(id);
			if (!localResponse.getStatus().equals("Your activity is still being processed.")) {
				loop = false;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Ignore and continue
				}
			}
		}
		if (localResponse.getStatus().equals("Your activity is ready.")) {
			ActivityServices activityService = ActivityServicesImpl.implementation(TestUtils.getValidToken());
			loop = true;
			while (loop) {
				StravaActivity activity = activityService.getActivity(localResponse.getActivityId());
				if (activity != null && activity.getResourceState() != StravaResourceState.UPDATING) {
					loop = false;
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// Ignore and continue
					}
				}
			}
			activityService.deleteActivity(localResponse.getActivityId());
		}

	}

	@Test
	public void testUpload_noWriteAccess() throws BadRequestException, UnauthorizedException, InterruptedException, NotFoundException {
		UploadServices service = getServiceWithoutWriteAccess();
		File file = new File("hyperdrive.gpx");
		try {
			service.upload(StravaActivityType.RIDE, "UploadServicesImplTest.testUpoad_noWriteAccess", null, Boolean.TRUE, null, "gpx",
					"testUpload_noWriteAccess", file);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}

		// Fail
		fail("Uploaded an activity without write access!");

	}

	@Test
	public void testUpload_badActivityType() {
		UploadServices service = getService();
		File file = new File("hyperdrive.gpx");
		StravaUploadResponse response = service.upload(StravaActivityType.UNKNOWN, "UploadServicesImplTest,testUpload_badActivityType", null, null, null,
				"gpx", "ABC", file);
		waitForCompletionAndDelete(response);
	}

	@Test
	public void testUpload_badDataType() {
		UploadServices service = getService();
		File file = new File("hyperdrive.gpx");
		try {
			service.upload(StravaActivityType.RIDE, "UploadServicesImplTest.testUpload_badDataType", null, null, null, "UNKNOWN", "ABC", file);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Uploaded a file with a bad data type!");
	}

	@Test
	public void testUpload_noName() throws UnauthorizedException, BadRequestException {
		UploadServices service = getService();
		File file = new File("hyperdrive.gpx");
		StravaUploadResponse response = service.upload(StravaActivityType.RIDE, null, "UploadServicesImplTest.testUpload_noName", null, null, "gpx", "ABC",
				file);
		waitForCompletionAndDelete(response);
	}

	@Test
	public void testUpload_noFile() {
		UploadServices service = getService();
		File file = null;
		try {
			service.upload(StravaActivityType.RIDE, "UploadServicesImplTest.testUpload_noName", null, null, null, "gpx", "ABC", file);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Uploaded a file with no actual file!");
	}

	@Test
	public void testUpload_badFileContent() {
		UploadServices service = getService();
		File file = new File("Plan");
		try {
			service.upload(StravaActivityType.RIDE, "UploadServicesImplTest.testUpload_noName", null, null, null, "gpx", "ABC", file);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Uploaded a file with an invalid file!");
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