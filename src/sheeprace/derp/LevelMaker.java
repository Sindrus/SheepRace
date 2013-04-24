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
			// Initiating xmlresourceparser
			XmlResourceParser xrp = main.getResources().getXml(R.xml.level);
			
			// Go through the document
			while(xrp.getEventType() != xrp.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					// check for starttag for levels with themes
					if(xrp.getName().equals("levels"))
					if(xrp.getAttributeValue(0).equals(theme)){
						Constants.maxLevels=xrp.getAttributeIntValue(1, -1);
						System.out.println("Max antall levler: "+Constants.maxLevels);
						// Must not run out of the document
						while(xrp.getEventType() != xrp.END_DOCUMENT){
							// Check to see if we found the level we are looking for
							if(xrp.getName().equals("level")){
								if(xrp.getAttributeIntValue(0, -1)==levelnum){
									xrp.next();
									
									// Get all the elements on that level
									while(!xrp.getName().equals("end") || 
											xrp.getEventType() != xrp.END_DOCUMENT){
										// Differentiate between questionbox and blockbox
										if(xrp.getName().equals("qbox")){
											float x = Float.parseFloat(xrp.getAttributeValue(0));
											float y = Float.parseFloat(xrp.getAttributeValue(1));
											l.addQBox(new QuestionBox(x, y));
										}else if(xrp.getName().equals("bbox")){
											float x = Float.parseFloat(xrp.getAttributeValue(0));
											float y = Float.parseFloat(xrp.getAttributeValue(1));
											l.addBBox(new BlockBox(x, y));
										}
										// For some reason it is necessary to call next() twice
										xrp.next();
										if(xrp.getName().equals("end"))
											break;
										xrp.next();
									}
									return l;
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
		System.out.println("Couldn't create the level, donno what happened.");
		return null;
	}

}
