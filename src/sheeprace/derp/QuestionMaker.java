package sheeprace.derp;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Log;


/**
 * Creates a question and returns it to whomever asked for it.
 * Must implement QuestionMakerInterface.
 */

public class QuestionMaker implements QuestionMakerInterface{
	private MainActivity main;
	private String title;	
	private List<Boolean> correct;
	private List<String> option;
	private static final String TAG = "CompXML";
	
	
	public QuestionMaker(MainActivity main) {
		int qid=2;
		try {
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					
					String s = xrp.getName();
		//			System.out.println("At tag: "+s);
					if(s.equals("question") && xrp.getAttributeIntValue(0, -1)==qid){
						System.out.println("This is it!");
						xrp.next();
						title = xrp.nextText();
						System.out.println("the title is: "+title);
						xrp.next();
						System.out.println("Now at: "+xrp.getName());
						while (xrp.getName().equals("option")){
							xrp.next();
							System.out.println("svar: "+xrp.nextText());
							xrp.next();
							System.out.println("corr: "+xrp.nextText());
							xrp.next();
							xrp.next();
						}
					}
					xrp.next();
					
/*					System.out.println("now at: "+s);
					if(s.equals("id") && Integer.parseInt(xrp.getText())==qid){
						xrp.next();
						xrp.next();
						if(s.equals("title") && xrp.getEventType()==XmlResourceParser.TEXT){
							System.out.println("Question is: "+xrp.getText());
						}
						else if(s.equals("option")){
							try {
								xrp.next();
								System.out.println("Alt 1 is: "+xrp.getText());
								xrp.next();
								xrp.next();
								xrp.next();
								System.out.println("Det er "+xrp.getText());
							} catch (Exception e) {
								//Gotta catch 'em all
								System.out.println("Feil :(");
							}
						}
					}else*/
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
	}

	@Override
	public Question createQuestion(MainActivity main) {
		// TODO Auto-generated method stub
		return null;
	}

}


