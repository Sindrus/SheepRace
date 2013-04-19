package sheeprace.derp;

import android.content.res.XmlResourceParser;

/**
 * LevelMaker
 * Parse the level from some levelinputfile and return it to the levelobject
 * @see Level
 */

public class LevelMaker{

	public static Level createLevel(MainActivity main, String theme, int levelnum) {
		Level l = new Level();
		try{
			
			XmlResourceParser xrp = main.getResources().getXml(R.xml.level);
			while(xrp.getEventType() != xrp.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					if(xrp.getName().equals("levels"))
					if(xrp.getAttributeValue(0).equals(theme)){
						while(xrp.getEventType() != xrp.END_DOCUMENT){
							if(xrp.getName().equals("level")){
								System.out.println("Level funnet");
								if(xrp.getAttributeIntValue(0, -1)==levelnum){
									xrp.next();
									while(xrp.getEventType() != xrp.END_DOCUMENT){
										if(xrp.getName().equals("qbox")){
											float x = Float.parseFloat(xrp.getAttributeValue(0));
											float y = Float.parseFloat(xrp.getAttributeValue(1));
											l.addQBox(new QuestionBox(x, y));
										}else if(xrp.getName().equals("bbox")){
											float x = Float.parseFloat(xrp.getAttributeValue(0));
											float y = Float.parseFloat(xrp.getAttributeValue(1));
											l.addBBox(new BlockBox(x, y));
										}else if(xrp.getName().equals("end")){
											System.out.println(l.toString());
											return l;
										}
										xrp.next();
										xrp.next();
									}
								}
							}
							xrp.next();
						}
					}
				}
				xrp.next();
			}
			xrp.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("An error has occured! Oh noooo!");
		}
		return null;
	}

}
