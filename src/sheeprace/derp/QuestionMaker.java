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
	
	public static Question createQuestion(MainActivity main, int qid, String category) {
		try {
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					if(xrp.getName().equals("q") && xrp.getAttributeValue(0).equals(category)){
						System.out.println("I riktig kategori");
						int qs = xrp.getAttributeIntValue(1, -1);
						System.out.println("qs: "+ qs);
						while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
							
								String s="";
								s = xrp.getName();
								if(s==null)
									s="";
								
								if(s.equals("question"))
								if(xrp.getAttributeIntValue(0, -1)==qid){
									
									System.out.println("Parsing question");
									Question q = new Question();
									xrp.next();
									
									String title = xrp.nextText();
									System.out.println("the title is: "+title);
									
									q.setQuestion(title);
									xrp.next();
									
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
									System.out.println(q.toString());
									return q;
								}
							//}
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
		return null;
	}
}


