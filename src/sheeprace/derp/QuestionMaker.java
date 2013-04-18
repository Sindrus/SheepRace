package sheeprace.derp;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Log;


/**
 * Creates a question and returns it to whomever asked for it.
 * Must implement QuestionMakerInterface.
 */

public class QuestionMaker{
	private static final String TAG = "CompXML";
	
	public static Question createQuestion(MainActivity main, int qid) {
		try {
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					
					String s = xrp.getName();
					if(s.equals("question") && xrp.getAttributeIntValue(0, -1)==qid){
						System.out.println("Parsing question");
						Question q = new Question();
						xrp.next();
						String title = xrp.nextText();
						System.out.println("the title is: "+title);
						q.setQuestion(title);
						xrp.next();
						System.out.println("Now at: "+xrp.getName());
						while (xrp.getName().equals("option")){
							xrp.next();
							String answer = xrp.nextText();
							xrp.next();
							String corr = xrp.nextText();
							boolean tf = corr.equals("true");
							xrp.next();
							xrp.next();
							q.addOption(answer, tf);
						}
					}
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
		return null;
	}
}


