
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.*;

public interface ClientInterface extends Remote{

	public boolean sendData(String filename, byte[] data, int len) throws RemoteException;	
	public String getName() throws RemoteException;
	public byte[] getContent() throws RemoteException;;
	public void setInfo(String name, byte[] content) throws RemoteException;;
	public void setName(String n) throws RemoteException;

	
}
