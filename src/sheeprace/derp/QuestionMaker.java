package sheeprace.derp;

import java.io.IOException;
import java.lang.reflect.Field;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Log;


/**
 * Creates a question and returns it to whomever asked for it.
 * Must implement QuestionMakerInterface.
 */

public class QuestionMaker implements QuestionMakerInterface{
	private MainActivity main;
	private String info;
	private static final String TAG = "CompXML";
	
	
	public QuestionMaker(MainActivity main) {
		info="";
		int qid=2;
		try {
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					
					String s = xrp.getName();
					if(s.equals("id")){
						xrp.next();
						System.out.println("ID er: "+xrp.getText());
					}
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
					}
	/*				if(s.equals("mytag")){
						// Get the resource id; this will be retrived
						// as a resolved hex value
						int resid = xrp.getAttributeResourceValue(null, "id", 0);
						info+="Attribute id has value "
								+Integer.toHexString(resid)
								+" from tag " + s + "\n";
						
						// Get our custom string attribute.
						
						String sn = xrp.getAttributeValue(null, "mystr");
						
						info+="Attribute id has value" + sn
								+" from tag " + s + "\n";
						
						// Get our custom int attribute
						int i = xrp.getAttributeIntValue(null, "myint", 0);
						
						info+="Attribute myint has value " + i
								+ " from tag " + s + "\n\n";

					}else if(s.equals("artag")){
						//Get the resource id; this will be retrived
						//as a resolved hex value
						
						int resid = xrp.getAttributeResourceValue(null, "id", 0);
						
						info+="Attribute id has value"
								+ Integer.toHexString(resid)
								+ " from tag " + s + "\n";
						
						//Get our custom string attribute
						String sn = xrp.getAttributeValue(null, "mystr");
						
						info+="Attribute mystr has value" + sn
								+ " from tag " + s + "\n";
						
						// Get our custom float attribute
						float f = xrp.getAttributeFloatValue(null, "myfloat", 0);
						info += "Attribute myfloat has value " + f
								+" from tag " + "\n\n";
					}else if(s.equals("plaintag")){
						//Get the element tag name here;
						// the value is gotten on the next TEXT event
						info+= "Tag " + s + " has value";
					}*/
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
		System.out.println(info);
	}

	@Override
	public Question createQuestion(MainActivity main) {
		// TODO Auto-generated method stub
		return null;
	}

}


