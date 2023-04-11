import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.BufferedReader;  
import java.io.InputStreamReader;

public class MatrixAdditionClient {
    public static void main(String[] args) {
        try {
            // Locate the server
            MatrixAdditionInterface server = (MatrixAdditionInterface) Naming.lookup("//localhost/MatrixAddition");  
            
            // Declare the matrix variables
            int[][] matrix1;  
            int[][] matrix2;
            
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // read username and password from console
            System.out.print("Username: ");
            String username = consoleIn.readLine();
            System.out.print("Password: ");
            String password = consoleIn.readLine();

            // authenticate user
            if (!server.authenticateUser(username, password)) {
                System.out.println("Authentication failed");
                return;
            } else {
                System.out.println("Authentication successful");
            }
            
            // read first matrix from console
            System.out.println("Enter the number of rows and columns in the first matrix: ");
            int rows = Integer.parseInt(consoleIn.readLine());
            int cols = Integer.parseInt(consoleIn.readLine());
            matrix1 = new int[rows][cols];
            System.out.println("Enter the elements of the first matrix: ");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix1[i][j] = Integer.parseInt(consoleIn.readLine());
                }
            }
            
            // read second matrix from console
            System.out.println("Enter the number of rows and columns in the second matrix: ");
            rows = Integer.parseInt(consoleIn.readLine());
            cols = Integer.parseInt(consoleIn.readLine()); 
            matrix2 = new int[rows][cols];
            System.out.println("Enter the elements of the second matrix: ");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix2[i][j] = Integer.parseInt(consoleIn.readLine()); 
                }
            }

            // compute sum of matrices
            if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
                System.out.println("Error: Matrices are not of the same size.");
            } else {
                int[][] sum = new int[matrix1.length][matrix1[0].length];
                for (int i = 0; i < matrix1.length; i++) {
                    for (int j = 0; j < matrix1[0].length; j++) {
                        sum[i][j] = matrix1[i][j] + matrix2[i][j];
                    }
                }

                // print result
                System.out.println("Sum of the matrices:");
                for (int i = 0; i < sum.length; i++) {
                    for (int j = 0; j < sum[0].length; j++) {
                        System.out.print(sum[i][j] + " "); 
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
