import java.rmi.*;
public interface ServerInterface extends Remote{
	public boolean getFile(ClientInterface c) throws RemoteException;
	  public  void PutFile(ClientInterface fileif) throws RemoteException;
	public void RenameFile(String filename, String newName) throws RemoteException;
	public void DeleteFile(String filename) throws RemoteException;

}
