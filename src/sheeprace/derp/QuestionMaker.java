package sheeprace.derp;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;
import android.util.Log;


/**
 * Creates a question and returns it to whomever asked for it.
 * Must implement QuestionMakerInterface.
 */

public class QuestionMaker{
	private static final String TAG = "CompXML";
	
	/**
	 * Returns a questionobject back to the method asking for it. 
	 * @param main need the MainActivity to access the xml-sheet
	 * @param qid Need to get an ID for what question to get
	 * @param category From what category shoudl the question come from?
	 * @return a Questionobject, loaded with a question and options.
	 */
	public static Question createQuestion(MainActivity main, int qid, String category) {
		try {
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			// Running through the xml-sheet until it reaches the end
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					
					// Check if the next questions are in the correct category
					if(xrp.getName().equals("q") && xrp.getAttributeValue(0).equals(category)){
						
						// Get the number of questions in the category
						int qs = xrp.getAttributeIntValue(1, -1);
						while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
							
							// FIXME: do this in a nicer way
								String s="";
								s = xrp.getName();
								// Apparently it is impossible to compare a 
								// string with value null to another string
								if(s==null)
									s="";
								
								// Have we found a questiontag?
								if(s.equals("question"))
									// Indeed we have, is it the correct question?
									if(xrp.getAttributeIntValue(0, -1)==qid){
										
										// Create the new question
										Question q = new Question();
										xrp.next();
										
										// Set the question
										q.setQuestion(xrp.nextText());
										xrp.next();
										// Look for answeroptions to the question 
										while (xrp.getName().equals("option")){
											xrp.next();
											String answer = xrp.nextText();
											xrp.next();
											
											boolean tf = xrp.nextText().equals("true");
											
											xrp.next();
											xrp.next();
											q.addOption(answer, tf);
										}
										System.out.println(q.toString());
										// And we are done!
										return q;
									}
							xrp.next();
						}
					}else
						xrp.next();
				}
				xrp.next();
			}
			xrp.close();
			
		} catch (XmlPullParserException xppe) {
			Log.e(TAG, "Failure of .getEventType or .next, probably bad file format");
			xppe.toString();
		} catch (IOException ioe){
			Log.e(TAG, "Unable to read resource file");
			ioe.printStackTrace();
		}
		// The question we were looking for does not exist. What to do?!
		return null;
	}
	
	/**
	 * Call this method once, to get all the categories that are available
	 * @param main Need the mainactivity to access the xml-sheet.
	 */
	public static void getCategories(MainActivity main){
		try {
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			System.out.println("Getting categories");
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					
					
					
					String s="";
					s = xrp.getName();
					// Apparently it is impossible to compare a 
					// string with value null to another string
					if(s==null)
						s="";
					// Have we found a questiontag?
					if(s.equals("q")){
						System.out.println("Category: "+xrp.getAttributeValue(0));
						System.out.println("qs: " + xrp.getAttributeIntValue(1, -1));
					}
				}
				xrp.next();
			}
			
		} catch (Exception e) {
			// Catch ALL the exceptions!
			System.err.println("Ops, something went wrong, lol!");
		}
	}
}


