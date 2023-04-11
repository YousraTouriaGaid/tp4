import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixAdditionInterface extends Remote {
    boolean authenticateUser(String username, String password) throws RemoteException;
    int[][] addMatrices(int[][] matrix1, int[][] matrix2) throws RemoteException;
}