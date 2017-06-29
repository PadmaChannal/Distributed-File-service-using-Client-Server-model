import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DriverServer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			//java.rmi.registry.LocateRegistry.createRegistry(1098);
			
			ServerImp fs=new ServerImp();
			//fs.setFile("1.txt");	
			System.setProperty("java.rmi.server.hostname","localhost");
			Naming.rebind("rmi://localhost/FILE-SERVER", fs);
			System.out.println("File Server is Ready");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}