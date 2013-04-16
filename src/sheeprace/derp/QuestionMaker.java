package sheeprace.derp;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

/**
 * Creates a question and returns it to whomever asked for it.
 * Must implement QuestionMakerInterface.
 */

public class QuestionMaker implements QuestionMakerInterface{
	private MainActivity main;
	@Override
	public Question createQuestion(MainActivity main) {
		XmlResourceParser r = main.getResources().getXml(R.xml.question);
		
		System.err.println("print, damit!");
		System.out.println("lololololol");
		System.out.println(r.getText());
	
		return null;
	}

}


