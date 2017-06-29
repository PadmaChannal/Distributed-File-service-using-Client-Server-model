import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class ServerImp  extends UnicastRemoteObject implements ServerInterface {

	private String file="";
	protected ServerImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setFile(String f){
		file=f;
	}

	public boolean getFile(ClientInterface c) throws RemoteException{
		/*
		 *
		 * Sending The File...
		 * 
		 */
		try{
			// File f1=new File(file);
			File f1=new File(c.getName());			 

			FileInputStream in=new FileInputStream(f1);			 				 
			byte [] mydata=new byte[1024*1024];						
			int mylen=in.read(mydata);
			while(mylen>0){
				c.sendData(f1.getName(), mydata, mylen);	 
				mylen=in.read(mydata);				 
			}
		}catch(Exception e){
			e.printStackTrace();

		}

		return true;
	}	
	public void PutFile(ClientInterface c) {
				 
		try {
			File f=new File(c.getName());	
			f.createNewFile();
			FileOutputStream out=new FileOutputStream(f,false);
			out.write(c.getContent());
			out.flush();
        	out.close();
        	System.out.println("Done writing data into server...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void RenameFile(String filename, String newName) throws RemoteException {	
		File f=new File(filename);	
		if (!f.exists()){
			 
				System.out.println("file does not exists");
		}
		File f2 = new File(newName);
		
		
			if(f.exists() ){	f.renameTo(f2);
			
	    	System.out.println("Done renaming ...");}
		
		
	}

	public void DeleteFile(String filename) throws RemoteException {
		File f=new File(filename);	
		if (!f.exists()){
			 
			System.out.println("file does not exists");
		
	}
		else{
			f.delete();
			System.out.println("file is deleted ");
		}
	}	

	}




