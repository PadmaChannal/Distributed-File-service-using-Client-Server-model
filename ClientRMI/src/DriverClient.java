
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
 

public class DriverClient {

	public static void main(String[] args) {
		
		
		
		
		//String cmd="get";
		String cmd="dropbox";

		String filename="100MB.zip";
		String newName="hoda69.png";
		String fName = "",actionName = "";
		 String change="";
		 ClientImp c = null;
		 ServerInterface server = null;
		 //System.out.println("hhhh");
		try {
			c = new ClientImp(filename);
			
			 server=(ServerInterface)Naming.lookup("rmi://localhost/FILE-SERVER");
			 System.out.println("Client 1");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
	    ArrayList<String> eventsH = new ArrayList<String>();
	    
		if(cmd.equalsIgnoreCase("get")){
	    	get( server,  c);
	    	
	    }
		if(cmd.equalsIgnoreCase("put")){
	    	put( server,  c,filename);
	    	
	    }
		if(cmd.equalsIgnoreCase("delete")){
	    	delete( server, filename);
	    	
	    }
		if(cmd.equalsIgnoreCase("rename")){
	    	rename(  server, filename,  newName);
	    	
	    }
		if(cmd.equalsIgnoreCase("dropbox")){
		try{
            Path dir = Paths.get("/Users/Padma/Documents/workspace-sts-3.8.1.RELEASE/ClientRMI");
            //Watcher w= new Watcher();
			
			 boolean a=true;
			 WatchService watcher = FileSystems.getDefault().newWatchService();
	            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	             
	            System.out.println("Watch Service registered for dir: " + dir.getFileName());
			while(a==true){
			        //ArrayList<String> mod=w.watch(dir);
			        //System.out.println(mod+"0000000");
			       // for(int i = 0; i < mod.size(); i++){
			        	//System.out.println(mod.get(i)+" **********");
				 WatchKey key;
	                try {
	                    key = watcher.take();
	                } catch (InterruptedException ex) {
	                    return ;
	                }
	                 
	                for (WatchEvent<?> event : key.pollEvents()) {
	                    WatchEvent.Kind<?> kind = event.kind();
	                     
	                    @SuppressWarnings("unchecked")
	                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
	                    Path fileName = ev.context();
	                    
	                    System.out.println(kind.name() + ": " + fileName);
	                  if(!fileName.toString().equalsIgnoreCase(".DS_Store")){
	                      change=kind.name() + "|"+ fileName; 
	                      eventsH.add(change);

	                     }
	                   
	                }
	                 
	                for(int i = 0; i < eventsH.size(); i++){
	                String[] output = eventsH.get(i).split("\\|");
	                for(int j = 0; j < output.length; j++){
	                	if(2*j+1<output.length){
	                actionName=output[2*j];
			        fName=output[2*j+1];}
	                	else{
	                		
	                		//System.out.println("hereeeee");
	                		continue;
	                	}
			        //String[] output = change.split("\\|");
			       // actionName=output[0];
			        //System.out.println(actionName+"-------");
			       // fName=output[1];
			       //System.out.println(fName+"-------");
			       System.out.println(fName+"   "+actionName+"----0000---");

			        if(actionName.equalsIgnoreCase("ENTRY_DELETE")){
			        	//cmd="delete";
			        	filename=fName;
			        	delete( server, filename);
			        	
			        }
			        if(actionName.equalsIgnoreCase("ENTRY_CREATE")){
			        	//cmd="put";
			        	filename=fName;
			        	//System.out.println(cmd+" ****** "+fName);
			        	put( server,  c,  filename);
			        	
			        }
			        if(actionName.equalsIgnoreCase("ENTRY_MODIFY")){
				         filename=fName;
			        	delete( server, filename);
			        	put( server,  c,  filename);
			        	//server.DeleteFile(filename);
			        	 //cmd="put";
				         filename=fName;
			        	
			        }
			        actionName="";
            		fName="";
			        }
	                boolean valid = key.reset();
	                if (!valid) {
	                    break;
	                }
	                }
			        //System.out.println("!!!!!!! "+mod);
			/*	
			if(cmd.equalsIgnoreCase("get")){
			server.getFile(c);
			System.out.println("Listening.....");			
			Scanner s=new Scanner(System.in);			
			while(true){
				String line=s.nextLine();
			}
			}
			
			if(cmd.equalsIgnoreCase("put")){
				File file = new File(filename);
                byte[] content = new byte[(int)file.length()];

                BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.getName()));
                input.read(content);
                ClientInterface fileif1 = new ClientImp( filename);
                fileif1.setInfo(filename, content);
                server.PutFile(fileif1);   

                input.close();
				}
			if(cmd.equalsIgnoreCase("rename")){
				
                server.RenameFile(filename,newName);               
				}
			if(cmd.equalsIgnoreCase("delete")){
				
                server.DeleteFile(filename);               
				}*/
			//}
			       
			       
			}
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
}
	public static void get(ServerInterface server, ClientImp c){
		try {
			server.getFile(c);
			System.out.println("Listening.....");			
			Scanner s=new Scanner(System.in);			
			while(true){
				String line=s.nextLine();
		}
		} catch (RemoteException e) {
			//e.printStackTrace();
		}
	
		}
	public static void put(ServerInterface server, ClientImp c, String filename){
		try {
			File file = new File(filename);
            byte[] content = new byte[(int)file.length()];

            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.getName()));
            input.read(content);
            ClientInterface fileif1 = new ClientImp( filename);
            fileif1.setInfo(filename, content);
            server.PutFile(fileif1);   

            input.close();
		
		} catch (RemoteException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	
		}
	
	public static void rename(ServerInterface server,String filename, String newName){
		
			try {
				server.RenameFile(filename,newName);
			} catch (RemoteException e) {
				//e.printStackTrace();
			}
		
	
		}
	public static void delete(ServerInterface server,String filename){
		
		try {
			 server.DeleteFile(filename);
			 } catch (RemoteException e) {
			//e.printStackTrace();
		}
	

	}
	

}