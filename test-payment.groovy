import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType


// Buat Request Object
String authToken = "7540|rYNzrA4xl8kcE0M5RkVpmmskMgZtHQvCUWwFlvNz";
RequestObject requestObject = new RequestObject('MyRequestObject')
requestObject.setRestRequestMethod('POST')
requestObject.setRestUrl('https://dev.api.onklas.id/api/mobile/canteen/payment-qr') // Ganti dengan URL API Anda
requestObject.setHttpHeaderProperties([
	new TestObjectProperty('Authorization', ConditionType.EQUALS, "Bearer " + authToken),
	new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])

// JSON Body
String requestBody = '''
{
    "uuid": "7400470f6fb34afbbd16262044e0ee6e",
    "pin": 123456,
    "nominal": 200,
    "note": "Catatan Pembayaran"
}
'''

// Loop dan Kirim Request 30 kali
for (int i = 0; i < 30; i++) {
	requestObject.setBodyContent(new HttpTextBodyContent(requestBody))
	WS.sendRequest(requestObject)
}