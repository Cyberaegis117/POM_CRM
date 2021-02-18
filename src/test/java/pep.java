
public class pep {
	public static void main(String[] args) throws Exception {

		  Generic.TestScriptStart("vbntest");
		  System.out.println(Constant.Map.get("pepLogin").get("LOGIN"));
		  Control.OpenApplication("Chrome","https://pep.prolifics.com");
		  Control.click("pepLogin","LOGIN"); 
		  Control.compareText("pepLogin","Errormessage", "Please Enter the fields below.");
			/*
			 * Generic.WriteTestCase("001", "Login into pep application",
			 * "Valid login credentials should allow to login into pep",
			 * "logging into pep with valid credentials only");
			 Control.OpenApplication("Chrome","https://pep.prolifics.com");
			 * Control.click("pepLogin", "LOGIN"); Control.compareText("pepLogin",
			 * "Errormessage", "Please Enter the fields below.");
			 * Control.compareText("pepLogin", "Errormessage",
			 * "Please Enter VBN the fields below.");
			 * 
			 * Generic.WriteTestCase("002", "Login into pep application",
			 * "Valid login credentials should allow to login into pep",
			 * "logging into pep with valid credentials only");
			 * Control.OpenApplication("Chrome","https://pep.prolifics.com");
			 * Control.click("pepLogin", "LOGIN"); Control.compareText("pepLogin",
			 * "Errormessage", "Please Enter the fields below.");
			 * Control.compareText("pepLogin", "Errormessage",
			 * "Please Enter VBN the fields below.");
			 */
			/*
			 * Generic.WriteTestCase("002", "Login into pep application",
			 * "Valid login credentials should allow to login into pep",
			 * "logging into pep with valid credentials only");
			 * //Control.OpenApplication("Chrome","https://pep.prolifics.com");
			 * Control.enterText("pepLogin","ÜserID","basavaiah.vemuru@prolifics.com");
			 * Control.enterText("pepLogin","Password","Nellore@6699");
			 * Control.click("pepLogin", "LOGIN");
			 * 
			 * Generic.WriteTestCase("003", "Login into pep application",
			 * "Valid login credentials should allow to login into pep",
			 * "logging into pep with valid credentials only");
			 * Control.enterText("pepLogin","ÜserID","basavaiah.vemuru@prolifics.com");
			 * Control.enterText("pepLogin","Password","Nellore@6699");
			 * Control.click("pepLogin", "LOGIN"); //Generic.GenerateHTMLReport();
			 */Generic.TestScriptEnds();
		  
		 
	}
}
