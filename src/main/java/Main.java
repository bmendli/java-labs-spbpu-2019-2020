import semesters.fourth.lab4.ProductContract;
import semesters.fourth.lab4.presenters.DataProductPresenter;
import semesters.fourth.lab4.presenters.Presenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Presenter presenter = new DataProductPresenter();
        presenter.start();
        Class.forName(ProductContract.DRIVER_NAME);
        try (Connection connection = DriverManager.getConnection(
                ProductContract.CONNECTION_URL,
                ProductContract.USER_NAME,
                ProductContract.USER_PASSWORD)) {
            System.out.println("rgrgrgrg");
        }
    }
}
