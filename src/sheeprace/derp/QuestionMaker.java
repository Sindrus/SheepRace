package sheeprace.derp;

import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;
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
		try {
			/*
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			
			QuestionParser qp = new QuestionParser();
			xr.setContentHandler(qp);
			
			xr.parse(new InputSou)*/
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.question);
			
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					String s = xrp.getName();
					if(s.equals("pictag")){
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
					}
				}else if(xrp.getEventType() == XmlResourceParser.END_TAG){
					;
				}else if(xrp.getEventType() == XmlResourceParser.TEXT){
					//Get our value from the plantag element
					//Since this is a value and not an
					//attribute, we retrive it with the
					//generic .getText()
					String s1 = xrp.getText();
					info+=s1+"\n\n";
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


