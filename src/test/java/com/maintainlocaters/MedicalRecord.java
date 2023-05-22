package com.maintainlocaters;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reusablelibrary.CommonOperation;

public class MedicalRecord extends CommonOperation {

	public MedicalRecord() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "Outpatient Clinic")
	public WebElement location;

	@FindBy(id = "loginButton")
	public WebElement loginbtn;

	@FindBy(xpath = "//div[@class='col-12 col-sm-12 col-md-12 col-lg-12']/h4")
	public WebElement dashboardPage;

	@FindBy(partialLinkText = "Register")
	public WebElement registerPatient;

	@FindBy(name = "givenName")
	public WebElement givenName;

	@FindBy(name = "familyName")
	public WebElement familyName;

	@FindBy(xpath = "//button[@id='next-button']")
	public WebElement rightbtn;

	@FindBy(xpath = "//select[@name='gender']")
	public WebElement gender;

	@FindBy(xpath = "//input[@id='birthdateDay-field']")
	public WebElement day;

	@FindBy(xpath = "//select[@id='birthdateMonth-field']")
	public WebElement month;

	@FindBy(xpath = "//input[@id='birthdateYear-field']")
	public WebElement year;

	@FindBy(id = "address1")
	public WebElement address;

	@FindBy(xpath = "//input[@id='cityVillage']")
	public WebElement cityVillage;

	@FindBy(xpath = "//input[@id='stateProvince']")
	public WebElement state;

	@FindBy(xpath = "//input[@id='country']")
	public WebElement country;

	@FindBy(xpath = "//input[@id='postalCode']")
	public WebElement postalcode;

	@FindBy(xpath = "//input[@name='phoneNumber']")
	public WebElement phoneNumber;

	@FindBy(xpath = "//div[@id='dataCanvas']/div/p/span[contains(text(),'Name')]/parent::p")
	public WebElement verifyName;

	@FindBy(xpath = "//div[@id='dataCanvas']/div/p/span[contains(text(),'Gender')]/parent::p")
	public WebElement verifyGender;

	@FindBy(xpath = "//div[@id='dataCanvas']/div/p/span[contains(text(),'Birthdate')]/parent::p")
	public WebElement verifyDob;

	@FindBy(xpath = "//div[@id='dataCanvas']/div/p/span[contains(text(),'Address')]/parent::p")
	public WebElement verifyAddress;

	@FindBy(xpath = "//div[@id='dataCanvas']/div/p/span[contains(text(),'Phone')]/parent::p")
	public WebElement verifyPhoneNumber;

	@FindBy(id = "submit")
	public WebElement confirm;

	@FindBy(xpath = "//span[@id='edit-patient-demographics']/preceding-sibling::span[not(contains(text(),'ale'))]")
	public WebElement verifyage;

	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	public WebElement startVisit;

	@FindBy(xpath = "//button[@id='start-visit-with-visittype-confirm']")
	public WebElement visitconfirm;

	@FindBy(id = "attachments.attachments.visitActions.default")
	public WebElement attachment;

	@FindBy(xpath = "//div[contains(text(),'Click or drop a file here.')]")
	public WebElement dropaFile;

	@FindBy(xpath = "//textarea[@placeholder='Enter a caption']")
	public WebElement caption;

	@FindBy(xpath = "//button[contains(text(),'Upload')]")
	public WebElement uploadFile;

	@FindBy(xpath = "//p[contains(text(),'successfully')]")
	public WebElement successuploaded;

	@FindBy(xpath = "//ul[@id='breadcrumbs']/li[2]/a")
	public WebElement patientDetailsScreen;

	@FindBy(xpath = "//div[@ng-hide='editMode']/p")
	public WebElement attachementsection;

	@FindBy(xpath = "//div[contains(text(),'Attachment Upload')]/preceding-sibling::a")
	public WebElement currentDateUploadTag;

	@FindBy(xpath = "//ul[@class='float-left d-none d-lg-block']/h3[contains(text(),'Current')]/parent::ul/descendant::div[contains(text(),'End')]")
	public WebElement endvisit;

//	@FindBy(xpath = "//div[@class='dialog-header']/h3[contains(text(),'End Visit')]/parent::div/following::div/button[text()='Yes']")
//	public WebElement endvisitYes;
//	
	@FindBy(xpath = "//a[@id='referenceapplication.realTime.vitals']")
	public WebElement capturevitals;

	@FindBy(xpath = "//input[@name='w8']")
	public WebElement height;

	@FindBy(xpath = "//input[@name='w10']")
	public WebElement weight;

	@FindBy(xpath = "//span[@id='calculated-bmi']")
	public WebElement calculatedBMI;

	@FindBy(xpath = "//a[@id='save-form']")
	public WebElement saveform;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement save;

	@FindBy(xpath = "//a[text()=' End Visit']")
	public WebElement endvisit2;

	@FindBy(xpath = "//div[@class='dialog-header']/h3[text()='End Visit']/following::div[@class='dialog-content']/button[text()='Yes']")
	public WebElement endvisityes;

	@FindBy(xpath = "//ul[@id='breadcrumbs']/li[2]/a")
	public WebElement patientDetailsScreen1;

	@FindBy(xpath = "//span[@id='height']/span[@class='value']")
	public WebElement verifyheight;

	@FindBy(xpath = "//span[@id='weight']/span[@class='value']")
	public WebElement verifyweight;

	@FindBy(xpath = "//h3[contains(text(),'Calculated')]/following::span[@id='calculated-bmi-wrapper']/span")
	public WebElement verifyBMI;

	@FindBy(xpath = "//div[contains(text(),'Vitals')]/preceding-sibling::a")
	public WebElement currentDateVitalsTag;

	@FindBy(xpath = "//div[contains(text(),'Merge Visits')]")
	public WebElement mergevisits;

	@FindBy(xpath = "(//input[@class='selectVisit'])[1]")
	public WebElement checkbox1;

	@FindBy(xpath = "(//input[@class='selectVisit'])[2]")
	public WebElement checkbox2;

	@FindBy(xpath = "//input[@id='mergeVisitsBtn']")
	public WebElement mergeSelectedVisits;

	@FindBy(xpath = "//input[@class='cancel']")
	public WebElement returnBtn;

	@FindBy(xpath = "//div[normalize-space()='Vitals, Attachment Upload']/preceding-sibling::a")
	public WebElement vitalsAttachmentUpload;

	@FindBy(xpath = "//div[contains(text(),'Past')]")
	public WebElement addPastVisit;

	@FindBy(xpath = "(//div[@class='datetimepicker-days']/table/descendant::td[@class='day disabled'])")
	public List<WebElement> futureDate;

	@FindBy(xpath = "(//div[@class='dialog-header']/h3[contains(text(),'Add Past Visit')])[2]/following::div[@class='dialog-content form']/button[@class='cancel']")
	public WebElement cancel;

	@FindBy(xpath = "//div[@class='float-sm-right']/child::em[contains(text(),'Patient')]/following-sibling::span")
	public WebElement patientId;

	@FindBy(xpath = "//div[contains(text(),'Delete')]")
	public WebElement deletePatient;

	@FindBy(id = "delete-reason")
	public WebElement deleteReason;

	@FindBy(xpath = "//label[contains(text(),'Reason')]/following::button[@class='confirm right']")
	public WebElement deletepersonConfirm;

	@FindBy(xpath = "//p[contains(text(),'deleted')]")
	public WebElement deleteSuccessfully;

	@FindBy(id = "patient-search")
	public WebElement patientSearch;

	@FindBy(xpath = "//table[@class='table table-sm dataTable']/tbody/tr/td")
	public WebElement noRecordsFound;

}
