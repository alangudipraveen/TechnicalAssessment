package com.assessment;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.config.PropertiesFile;
import com.maintainlocaters.MedicalRecord;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.reusablelibrary.CommonOperation;

public class PatientMedicalRecord extends CommonOperation {

	ExtentTest test;
	ExtentReports report;

	PropertiesFile prop = new PropertiesFile();;
	MedicalRecord medicalRecord = new MedicalRecord();

	String Pid;
	static String dateAndTime;

	@BeforeClass
	public static void launchurl() throws IOException {

		PropertiesFile prop = new PropertiesFile();

		getDriver(prop.getProperties("browser"), prop.getProperties("driverPath"));
		maximize();
		pageLoadTimeout(30);
		implicitWait(30);
		deleteAllCookies();
		dateAndTime = new SimpleDateFormat("dd-MM-yyyy (hh-mm)").format(new Date());
	}

	@BeforeMethod
	public void setup() {

		report = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/Reports/ExtentReport" + dateAndTime + ".html", false);
		report.addSystemInfo("Environment", "Test");
		report.addSystemInfo("Host Name", "Local sys");
		report.addSystemInfo("Browser", "chrome");

	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Testcase is failed", test.addScreenCapture(getscreenshot(driver, "Failed")));
			test.log(LogStatus.FAIL, "Test Case Failed " + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Testcase is skipped",
					test.addScreenCapture(getscreenshot(driver, result.getName())));
		}
		report.endTest(test);
		report.flush();

	}

	@AfterClass
	public static void getResult() {
		driver.quit();

	}

	@Test(priority = 1, description = "URL launched successfully")
	public void test1() throws IOException {
		test = report.startTest("URL launched successfully");

		medicalRecord = new MedicalRecord();
		getUrl(prop.getProperties("url"));
		test.log(LogStatus.PASS, "URL launched successfully", test.addScreenCapture(getscreenshot(driver, "URL")));

	}

	@Test(priority = 2, description = "Enter Username and password")
	public void test2() throws IOException {
		test = report.startTest("Enter Username and password");

		medicalRecord.username.sendKeys(prop.getProperties("username"));
		test.log(LogStatus.PASS, "Username is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Username")));

		medicalRecord.password.sendKeys(prop.getProperties("password"));
		test.log(LogStatus.PASS, "Password is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Password")));

	}

	@Test(priority = 3, description = "Pick any location below and click on Login")
	public void test3() throws IOException {
		test = report.startTest("Pick any location and click on Login");

		findWebelementByText(prop.getProperties("location")).click();
		test.log(LogStatus.PASS, "Location selected successfully",
				test.addScreenCapture(getscreenshot(driver, "Select Location")));

		medicalRecord.loginbtn.click();
		test.log(LogStatus.PASS, "Login clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "Click login")));

	}

	@Test(priority = 4, description = "Verify dashboard page is redirected using Assertion")
	public void test4() throws IOException {
		test = report.startTest("Verify dashboard page");

		Assert.assertEquals(driver.getCurrentUrl(),
				"https://qa-refapp.openmrs.org/openmrs/referenceapplication/home.page");
		Assert.assertEquals(driver.getTitle(), "Home");
		test.log(LogStatus.PASS, "Dashboard page is Verified ",
				test.addScreenCapture(getscreenshot(driver, "verified Dashboard")));

	}

	@Test(priority = 5, description = "Cick on Register a patient menu")
	public void test5() throws IOException {
		test = report.startTest("Register a patient menu");
		medicalRecord.registerPatient.click();
		test.log(LogStatus.PASS, "Register Patient is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "clicked RegisterPatient")));

	}

	@Test(priority = 6, description = "Enter the detail of Demographics(Name, Gender, Birthdate) and Contact Info(Address, Phone number")
	public void test6() throws IOException {
		test = report.startTest(
				"Enter the Demographics detail and Contact Info");
		String name = prop.getProperties("name");
		String givenname = name.split(" ")[0].trim();
		String familyname = name.split(" ")[1].trim();
		medicalRecord.givenName.sendKeys(givenname);
		medicalRecord.familyName.sendKeys(familyname);

		medicalRecord.rightbtn.click();
		selectByVisibleText(medicalRecord.gender, prop.getProperties("gender"));

		medicalRecord.rightbtn.click();
		String dob = prop.getProperties("dob");
		String date = dob.split("-")[0].trim();
		String month = dob.split("-")[1].trim();
		int months = Integer.parseInt(month);
		String year = dob.split("-")[2].trim();
		medicalRecord.day.sendKeys(date);
		selectByIndex(medicalRecord.month, months);
		medicalRecord.year.sendKeys(year);
		test.log(LogStatus.PASS, "Detail of Demographics is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Enter Demographics")));

		medicalRecord.rightbtn.click();
		medicalRecord.address.sendKeys(prop.getProperties("address"));
		medicalRecord.cityVillage.sendKeys(prop.getProperties("city"));
		medicalRecord.state.sendKeys(prop.getProperties("state"));
		medicalRecord.country.sendKeys(prop.getProperties("country"));
		medicalRecord.postalcode.sendKeys(prop.getProperties("pincode"));

		medicalRecord.rightbtn.click();
		medicalRecord.phoneNumber.sendKeys(prop.getProperties("phonenumber"));
		test.log(LogStatus.PASS, "Detail of contactInfo is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Entered ContactInfo")));

		medicalRecord.rightbtn.click();
		medicalRecord.rightbtn.click();

	}

	@Test(priority = 7, description = "Then at Confirm page, verify the given Name, Gender, Birthdate, Address, Phone number are populated correctly using Assertion")
	public void test7() throws IOException {
		test = report.startTest(
				" Verify the Demographics detail and Contact Info");
		String givenName2 = medicalRecord.verifyName.getText().split(",")[0];
		String familyName2 = medicalRecord.verifyName.getText().split(",")[1];
		Assert.assertEquals(givenName2 + familyName2, "Name: " + prop.getProperties("name"));
		test.log(LogStatus.PASS, "Verified the given Name and family name are populated correctly using Assertion",
				test.addScreenCapture(getscreenshot(driver, "verified given family name")));

		Assert.assertEquals(medicalRecord.verifyGender.getText(), "Gender: " + prop.getProperties("gender"));
		test.log(LogStatus.PASS, "Given Gender are populated correctly using Assertion is Verified ",
				test.addScreenCapture(getscreenshot(driver, "verified gender")));

		String dob = prop.getProperties("dob");
		String date = dob.split("-")[0].trim();
		String month = dob.split("-")[1].trim();
		int months = Integer.parseInt(month);
		String year = dob.split("-")[2].trim();

		DateFormatSymbols sym = new DateFormatSymbols();
		String dmonth = sym.getMonths()[months - 1];
		Assert.assertEquals(medicalRecord.verifyDob.getText(), "Birthdate: " + date + ", " + dmonth + ", " + year);
		test.log(LogStatus.PASS, "Given Birthdate are populated correctly using Assertion is Verified",
				test.addScreenCapture(getscreenshot(driver, "verified Birthdate")));

		Assert.assertEquals(medicalRecord.verifyAddress.getText(),
				"Address: " + prop.getProperties("address") + ", " + prop.getProperties("city") + ", "
						+ prop.getProperties("state") + ", " + prop.getProperties("country") + ", "
						+ prop.getProperties("pincode"));
		test.log(LogStatus.PASS, "Given Address are populated correctly using Assertion is Verified",
				test.addScreenCapture(getscreenshot(driver, "verified address")));

		Assert.assertEquals(medicalRecord.verifyPhoneNumber.getText(),
				"Phone Number: " + prop.getProperties("phonenumber"));
		test.log(LogStatus.PASS, "Given Phone number are populated correctly using Assertion is Verified",
				test.addScreenCapture(getscreenshot(driver, "verified phonenumber")));

	}

	@Test(priority = 8, description = "Click on Confirm and verify Patient details page is redirected and verify the age is calculated correctly based on the date of birth provided")
	public void test8() throws IOException {
		test = report.startTest(
				"Verify Patient details page and age");
		medicalRecord.confirm.click();

		Assert.assertEquals(driver.getCurrentUrl(),
				"https://qa-refapp.openmrs.org/openmrs/registrationapp/registerPatient.page?appId=referenceapplication.registrationapp.registerPatient");
		test.log(LogStatus.PASS, "Patient details page is Verified",
				test.addScreenCapture(getscreenshot(driver, "verify patientdetailspage")));

		String dob = prop.getProperties("dob");
		String date = dob.split("-")[0].trim();
		String month = dob.split("-")[1].trim();
		String year = dob.split("-")[2].trim();

		String date2 = currentSysDate("YYYY,MM,dd");
		String[] sysdate = date2.split(",");
		LocalDate sysdate1 = LocalDate.of(Integer.parseInt(sysdate[0]), Integer.parseInt(sysdate[1]),
				Integer.parseInt(sysdate[2]));
		LocalDate birthdate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
		int years = Period.between(birthdate, sysdate1).getYears();
		String age = String.valueOf(years);
		String matchyear = medicalRecord.verifyage.getText();
		String getage = matchyear.split(" ")[0].trim();
		Assert.assertEquals(getage, age);
		test.log(LogStatus.PASS, "Age is calculated correctly based on the date of birth provided is Verified",
				test.addScreenCapture(getscreenshot(driver, "verify age")));
	}

	@Test(priority = 9, description = "Click on Start Visit and Confirm the visit")
	public void test9() throws IOException {
		test = report.startTest("Click on Start Visit");
		medicalRecord.startVisit.click();
		test.log(LogStatus.PASS, "Start Visit is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click StartVisit")));

		medicalRecord.visitconfirm.click();
		test.log(LogStatus.PASS, "Confirm visit is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click confirmVisit")));

	}

	@Test(priority = 10, description = " Click on Attachment and complete the upload process")
	public void test10() throws AWTException, IOException {
		test = report.startTest("Attachment and complete the upload process");
		medicalRecord.attachment.click();
		medicalRecord.dropaFile.click();
		uploadFile(prop.getProperties("documentpath"));
		medicalRecord.caption.sendKeys(prop.getProperties("caption"));
		medicalRecord.uploadFile.click();
		test.log(LogStatus.PASS, "Attachment and upload process is completed successfully",
				test.addScreenCapture(getscreenshot(driver, "upload process")));
	}

	@Test(priority = 11, description = "Verify toaster message is appeared for the successful attachment")
	public void test11() throws IOException {
		test = report.startTest("Verify toaster message is appeared for attachment");
		SoftAssert assert1 = new SoftAssert();
		assert1.assertEquals(medicalRecord.successuploaded.getText(), "The attachment was successfully uploaded.");
		test.log(LogStatus.PASS, "Toaster message is appeared and Verified  successfully ",
				test.addScreenCapture(getscreenshot(driver, "verified Toaster Message")));
		assert1.assertAll();
	}

	@Test(priority = 12, description = " Redirect to Patient details screen")
	public void test12() throws IOException {
		test = report.startTest("Redirect to Patient details screen");
		medicalRecord.patientDetailsScreen.click();
		test.log(LogStatus.PASS, " Redirect to Patient details screen is clicked successfully ",
				test.addScreenCapture(getscreenshot(driver, "Patient details Screen page")));

	}

	@Test(priority = 13, description = "Verify Attachment section has attachment")
	public void test13() throws IOException {
		test = report.startTest("Verify Attachment section has attachment");
		SoftAssert assert2 = new SoftAssert();
		assert2.assertEquals(medicalRecord.attachementsection.getText(), prop.getProperties("caption"));
		test.log(LogStatus.PASS, "Attachment section has attachment is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verified Attachment section")));
		assert2.assertAll();
	}

	@Test(priority = 14, description = "Verfiy Recent Visit has one entry for current date with Attachment Upload tag")
	public void test14() throws IOException {
		test = report.startTest("Verfiy Recent Visit for current date with Attachment Upload tag");
		SoftAssert assert3 = new SoftAssert();
		assert3.assertEquals(medicalRecord.currentDateUploadTag.getText(), currentSysDate("dd.MMM.YYYY"));
		test.log(LogStatus.PASS,
				"Recent Visit has one entry for current date with Attachment Upload tag is Verfied successfully",
				test.addScreenCapture(getscreenshot(driver, "verified date attachUploadTag")));
		assert3.assertAll();
	}

	@Test(priority = 15, description = "Click on the End Visit action at RHS")
	public void test15() throws IOException {
		test = report.startTest("Click on the End Visit");
		medicalRecord.endvisit.click();
		medicalRecord.endvisityes.click();
		test.log(LogStatus.PASS, "End Visit clicked successfully ",
				test.addScreenCapture(getscreenshot(driver, "clicked End visit")));
	}

	@Test(priority = 16, description = "Start Visit again and Click on Capture Vitals menu")
	public void test16() throws IOException {
		test = report.startTest("Start Visit again and Click on Capture Vitals menu");
		medicalRecord.startVisit.click();
		medicalRecord.visitconfirm.click();
		test.log(LogStatus.PASS, "Start Visit again Clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "clicked Start visit")));

		medicalRecord.capturevitals.click();
		test.log(LogStatus.PASS, "Capture Vitals menu is clicked successfully ",
				test.addScreenCapture(getscreenshot(driver, "clicked Capture Vitals menu")));

	}

	@Test(priority = 17, description = " Enter Height & Weight and Verify displayed BMI is calculated correctly using BMI formula")
	public void test17() throws IOException {
		test = report.startTest("Enter Height,Weight and Verify BMI");
		SoftAssert assert4 = new SoftAssert();
		
		medicalRecord.height.sendKeys(prop.getProperties("height"));
		test.log(LogStatus.PASS, "Height is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Enter height ")));

		medicalRecord.rightbtn.click();
		medicalRecord.weight.sendKeys(prop.getProperties("weight"));
		test.log(LogStatus.PASS, "Weight is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Enter weight")));

		medicalRecord.rightbtn.click();
		double height = (double) Integer.parseInt(prop.getProperties("height")) / 100;
		double weight = (double) Integer.parseInt(prop.getProperties("weight"));
		double BMI = weight / (height * height);
		DecimalFormat df = new DecimalFormat("##.#");
		BMI = Double.valueOf(df.format(BMI));
		String j = BMI + "";
		assert4.assertEquals(medicalRecord.calculatedBMI.getText(), j);
		test.log(LogStatus.PASS, "Display BMI is calculated correctly using BMI formula is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify BMI")));
		assert4.assertAll();
	}

	@Test(priority = 18, description = "Click on Save Form and Save button")
	public void test18() throws IOException {
		test = report.startTest("Click on Save Form and Save button");
		medicalRecord.saveform.click();
		medicalRecord.save.click();
		test.log(LogStatus.PASS, "Save button clicked successfully ",
				test.addScreenCapture(getscreenshot(driver, "Click save")));

	}

	@Test(priority = 19, description = "Click on End Visit and redirect to Patient details page")
	public void test19() throws IOException {
		test = report.startTest("Click on End Visit");
		medicalRecord.endvisit2.click();
		medicalRecord.endvisityes.click();
		test.log(LogStatus.PASS, "End Visit clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "Click End visit")));

		try {
			medicalRecord.patientDetailsScreen.click();
			test.log(LogStatus.PASS, "Redirect to Patient details page is clicked successfully",
					test.addScreenCapture(getscreenshot(driver, "Patient details page")));
		} catch (StaleElementReferenceException e) {
			medicalRecord.patientDetailsScreen.click();
			test.log(LogStatus.PASS, "Redirect to Patient details page is clicked successfully",
					test.addScreenCapture(getscreenshot(driver, "Patient details page")));
		}
	}

	@Test(priority = 20, description = "In Patient details screen, verify the given Height and Weight is displayed correctly along with calculated BMI")
	public void test20() throws IOException {
		test = report.startTest("Verify Height,Weight and BMI");
		SoftAssert assert5 = new SoftAssert();
		
		double height = (double) Integer.parseInt(prop.getProperties("height")) / 100;
		double weight = (double) Integer.parseInt(prop.getProperties("weight"));
		double BMI = weight / (height * height);
		DecimalFormat df = new DecimalFormat("##.#");
		BMI = Double.valueOf(df.format(BMI));
		String j = BMI + "";

		assert5.assertEquals(medicalRecord.verifyheight.getText(), prop.getProperties("height"));
		test.log(LogStatus.PASS, "Height is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify Height")));
		assert5.assertEquals(medicalRecord.verifyweight.getText(), prop.getProperties("weight"));
		test.log(LogStatus.PASS, "weight is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify weight")));
		assert5.assertEquals(medicalRecord.verifyBMI.getText(), j);
		test.log(LogStatus.PASS, "BMI value is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify BMI")));
		assert5.assertAll();
	}

	@Test(priority = 21, description = "Verfiy Recent Visit has one more new entry for current date with Vitals tag")
	public void test21() throws IOException {
		test = report.startTest("Verfiy Recent Visit current date with Vitals tag");
		SoftAssert assert6 = new SoftAssert();
		
		assert6.assertEquals(medicalRecord.currentDateVitalsTag.getText(), currentSysDate("dd.MMM.YYYY"));
		test.log(LogStatus.PASS, "New entry for current date with Vitals tag is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify vitals tag")));
		assert6.assertAll();
	}

	@Test(priority = 22, description = "Click on Merge Visits, select these 2 visit and click on Merge Selected Visits button")
	public void test22() throws IOException {
		test = report
				.startTest("Click Merge Visits and Merge Selected Visits button");
		medicalRecord.mergevisits.click();
		test.log(LogStatus.PASS, "Merge Visits is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click merge visit")));

		medicalRecord.checkbox1.click();
		medicalRecord.checkbox2.click();
		test.log(LogStatus.PASS, "Merge Selected checkbox is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click Merge selectButton")));

		medicalRecord.mergeSelectedVisits.click();
		test.log(LogStatus.PASS, "Merge Selected Visits button is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click Merge selectButton")));

	}

	@Test(priority = 23, description = "Redirect to patient details page by clicking on Return button")
	public void test23() throws IOException {

		test = report.startTest("Click on Return button");
		medicalRecord.returnBtn.click();
		test.log(LogStatus.PASS, "Return button is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click ReturnButton")));
	}

	@Test(priority = 24, description = "Verfiy Recent Visit has become one entry for current date with Vitals,Attachment Upload tag")
	public void test24() throws IOException {
		test = report.startTest(
				"Verfiy Recent Visit current date with Vitals,Attachment Upload tag");
		SoftAssert assert7 = new SoftAssert();
		assert7.assertEquals(medicalRecord.vitalsAttachmentUpload.getText(), currentSysDate("dd.MMM.YYYY"));
		test.log(LogStatus.PASS, "Current date with Vitals,Attachment Upload tag is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify vitalsAttachment tag")));
		assert7.assertAll();
	}

	@Test(priority = 25, description = "Click on Add Past Visit and verify the future date is not clickable in the date picker")
	public void test25() throws IOException {
		test = report
				.startTest("Click Add Past Visit and verify the future date");
		medicalRecord.addPastVisit.click();
		test.log(LogStatus.PASS, "Add Past Visit is Clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click Add past visit")));

		List<WebElement> futureDate = medicalRecord.futureDate;
		for (WebElement fdate : futureDate) {
			if (fdate.getAttribute("class").equalsIgnoreCase("day disabled")) {
				test.log(LogStatus.PASS, "Future date is not clickable in the date picker is verified",
						test.addScreenCapture(getscreenshot(driver, "Future date is not clickable")));
				break;
			} else {
				test.log(LogStatus.FAIL, "Testcase is failed", test.addScreenCapture(getscreenshot(driver, "Failed")));
			}
		}
		
	}

	@Test(priority = 26, description = "Redirect to patient details page ")
	public void test26() throws IOException {
		test = report.startTest("Redirect to patient details page");
		medicalRecord.cancel.click();
		test.log(LogStatus.PASS, "Redirect to patient details page is successfully",
				test.addScreenCapture(getscreenshot(driver, "click Delete Patient")));
		Pid = medicalRecord.patientId.getText();
	}

	@Test(priority = 27, description = "Click on Delete Patient and provide the reason")
	public void test27() throws IOException {
		test = report.startTest("Click delete Patient and provide reason");
		medicalRecord.deletePatient.click();
		test.log(LogStatus.PASS, "Delete Patient is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click Delete Patient")));

		medicalRecord.deleteReason.sendKeys(prop.getProperties("deleteReason"));
		test.log(LogStatus.PASS, "Delete Patient reason is entered successfully",
				test.addScreenCapture(getscreenshot(driver, "Enter delete patient reason")));

	}

	@Test(priority = 28, description = " Click on confirm button and verify the toaster message")
	public void test28() throws IOException {
		test = report.startTest("Click confirm button and verify toaster message");
		SoftAssert assert8 = new SoftAssert();
		medicalRecord.deletepersonConfirm.click();
		test.log(LogStatus.PASS, "Confirm button is clicked successfully",
				test.addScreenCapture(getscreenshot(driver, "click confirm")));

		assert8.assertEquals(medicalRecord.deleteSuccessfully.getText(), "Patient has been deleted successfully");
		test.log(LogStatus.PASS, "Toaster message is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify toaster message")));
		assert8.assertAll();
	}

	@Test(priority = 29, description = "It will redirect you to Find Patient Record menu where verify the deleted patient should not listed out in the table using search options")
	public void test29() throws InterruptedException, IOException {
		test = report.startTest(
				"Verify the deleted patient should not listed out in the table");
		SoftAssert assert9 = new SoftAssert();
		medicalRecord.patientSearch.sendKeys(Pid);
		Thread.sleep(2000);
		assert9.assertEquals(medicalRecord.noRecordsFound.getText(), "No matching records found");
		test.log(LogStatus.PASS, "Deleted patient should not be listed is verified successfully",
				test.addScreenCapture(getscreenshot(driver, "verify deletepatient")));

		assert9.assertAll();

	}

}
