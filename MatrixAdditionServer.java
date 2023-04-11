import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixAdditionServer extends UnicastRemoteObject implements MatrixAdditionInterface {
    protected MatrixAdditionServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            MatrixAdditionServer server = new MatrixAdditionServer();
            Naming.rebind("MatrixAddition", server);
            System.out.println("Matrix Addition Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(String username, String password) throws RemoteException {
        return username.equals("user") && password.equals("password");
    }

    @Override
    public int[][] addMatrices(int[][] matrix1, int[][] matrix2) throws RemoteException {
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }
}