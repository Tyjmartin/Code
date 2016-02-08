/*
Tyler Martin
4836318

So this just handles some mundane work.
So everything is a string or a file and just makes it seamless.
*/



import java.io.*;



public class chatMsg implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	static final int  MESSAGE = 0, LOGOUT = 1,SENDING_FILE=2, RECEIVE_FILE=4;
	private int type;
	private String message;
	private File file;

	chatMsg (int type, String message){
		this.type = type;
		this.message = message;
		file = null;
	}

	chatMsg (int type, File buffer){
		file = buffer;
		this.type = type;
		message = null;
	}

	int getType(){
		return type;
	}

	String getMessage(){
		return message;
	}

	File getFile(){
		return file;
	}

}