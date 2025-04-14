package listener;

import com.aventstack.extentreports.Status;
import extentreportmanager.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReport extends ExtentManager implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest( result.getMethod().getMethodName());
        test.info("Test Start With->"+ result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if(result.getStatus() == ITestResult.SUCCESS){
                test.log(Status.PASS ,result.getMethod().getMethodName()+ " is passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
