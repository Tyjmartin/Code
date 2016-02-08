/*
Tyler Martin
4836318

The Server
Handles threads for multiple clients
really not easy
*/



import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Server{
	private static int uniqueId;
	private List<ClientThread> al;
	private ServerGUI sg;
	private SimpleDateFormat sdf;
	private int port;
	private boolean working;



	public Server(int port){
		this(port, null);
	}

	public Server(int port, ServerGUI sg){
		this.sg = sg;
		this.port = port;
		sdf = new SimpleDateFormat("HH:mm:ss");
		al = new ArrayList<ClientThread>();

	}

	public void start(){

		working = true;

		try{
			ServerSocket serverSocket = new ServerSocket(port);
			while(working){
				display("Server waiting for Clients on port " + port + ".");
				Socket socket = serverSocket.accept();
				if(!working)
					break;

				ClientThread t = new ClientThread(socket);
				al.add(t);
				t.start();
			}
			try {
				serverSocket.close();
				for (int i =0; i<al.size();i++){
					ClientThread tc = al.get(i);
					try{
						tc.sInput.close();
						tc.sOutput.close();
						tc.socket.close();
					}
					catch(IOException ioE){

					}
				}
			}
			catch (Exception e){
				display("Exception closing the server and clients: "+ e);
			}
		}
		catch (IOException e){
			String msg = sdf.format(new Date())+ " Exception on new ServerSocket: "+ e + "\n";
			display(msg);
		}
	}

	public void stop(){
		working = false;

		try{
			new Socket("localhost",port);
		}

		catch(Exception e){

		}
	}

	private void display (String msg){
		String time = sdf.format(new Date()) + " " + msg;

		if (sg == null)
			System.out.println(time);
		else
			sg.appendEvent(time + "\n");
	}

	private synchronized void broadcast (String message){
		String time = sdf.format(new Date());
		String messageLF = time + " "+ message+ "\n";

		if (sg == null)
			System.out.print(messageLF);
		else
			sg.appendRoom(messageLF);

		for (int i = al.size(); --i >=0;){
			ClientThread ct = al.get(i);

			if (!ct.writeMsg(messageLF)){
				al.remove(i);
				display("Disconnected Client " +ct.username+" removed from list.");
			}
		}
	}
	synchronized void remove (int id){
		for (int i = 0; i < al.size(); i++){
			ClientThread ct = al.get(i);

			if(ct.id == id){
				al.remove(i);
				return;
			}
		}
	}

	public static void main (String[] args){

		int portNum = 22222;

		switch(args.length){
			case 1:
				try{
					portNum = Integer.parseInt(args[0]);
				}
				catch (Exception e){
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java server [portNum]");
					return;
				}
			case 0:
				break;
			default:
				System.out.println("Usage is: > java Server [portNum]");
				return;
		}

		Server server = new Server(portNum);
		server.start();
	}


	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;

		int id;

		String username;
		chatMsg cm;
		String date;

		ClientThread (Socket socket){
			id = ++uniqueId;
			this.socket = socket;
			System.out.println("Thread trying to create I/O");

			try{
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput = new ObjectInputStream(socket.getInputStream());

				username = (String) sInput.readObject();
				display(username + " just connected.");
			}

			catch (IOException e){
				display("Exception creating new Input/output stream: "+ e);
				return;
			}
			catch (ClassNotFoundException e){
			}
			date = new Date().toString() + "\n";
		}

		public void run(){
			boolean keepGoing = true;
			while(keepGoing){
				try{
					cm = (chatMsg) sInput.readObject();
				}
				catch (IOException e){
					display(username + "Exception reading Streams: "+ e);
					break;
				}
				catch (ClassNotFoundException e2){
					break;
				}
				String message = cm.getMessage();

				switch(cm.getType()){

					case chatMsg.MESSAGE:
						broadcast(username + ": "+ message);
						break;
					case chatMsg.LOGOUT:
						display(username + " disconnected with a LOGOUT message.");
						keepGoing = false;
						break;
					case chatMsg.SENDING_FILE:
						File file = cm.getFile();
						try{
						sOutput.writeObject(file);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						break;
					case chatMsg.RECEIVE_FILE:
						try{
							File received = (File) sInput.readObject();
							received.createNewFile();
						}
						catch (IOException e){
							e.printStackTrace();
						}
						catch (ClassNotFoundException e){
							e.printStackTrace();
						}
						break;
				}
			}
			remove(id);
			close();
		}

		private void close(){
			try{
				if(sOutput != null) sOutput.close();
			}
			catch (Exception e){}
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e){}
			try {
				if(socket !=null) socket.close();
			}
			catch (Exception e){}
		}
		private boolean writeMsg(String msg){
			if(!socket.isConnected()){
				close();
				return false;
			}
			try{
				sOutput.writeObject(msg);
			}
			catch (IOException e){
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}