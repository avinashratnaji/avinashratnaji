package resources;

import java.util.List;

import jdk.internal.org.jline.utils.Log;



public class base {

	public void Listtoelement(List abc) {
	
		String s=abc.toString();
		String result=s.substring(2,s.length()-2);
		String arr[]=result.split(",");
		for(int i = 0;i<6;i++) {
			System.out.println(arr[i]);
			
		}
}
}
