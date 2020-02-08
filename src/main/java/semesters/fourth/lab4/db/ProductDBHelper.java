package semesters.fourth.lab4.db;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.ProductContract;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.presenters.Presenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ProductDBHelper implements DBHelper {

    private static final String INSERT_REQUEST = "INSERT IGNORE INTO " + ProductContract.TABLE_PRODUCT_NAME + "("
            + ProductContract.ProductColumns.COLUMN_PRODUCT_ID + ", "
            + ProductContract.ProductColumns.COLUMN_TITLE + ", "
            + ProductContract.ProductColumns.COLUMN_COST + ") VALUES (?, ?, ?)";

    private static final String UPDATE_REQUEST = "UPDATE " + ProductContract.TABLE_PRODUCT_NAME + " SET "
            + ProductContract.ProductColumns.COLUMN_COST + " = ? WHERE "
            + ProductContract.ProductColumns.COLUMN_TITLE + " = ?";

    private static final String SELECT_REQUEST_PRICE_IN_RANGE = "SELECT * FROM " + ProductContract.TABLE_PRODUCT_NAME + " WHERE "
            + ProductContract.ProductColumns.COLUMN_COST + " >= ? AND "
            + ProductContract.ProductColumns.COLUMN_COST + " <= ?";

    private static final String SELECT_REQUEST_PRICE_BY_NAME = "SELECT " + ProductContract.ProductColumns.COLUMN_COST
            + " FROM " + ProductContract.TABLE_PRODUCT_NAME + " WHERE "
            + ProductContract.ProductColumns.COLUMN_TITLE + " = ?";

    private static final String SELECT_ALL = "SELECT * FROM " + ProductContract.TABLE_PRODUCT_NAME;

    private static final String DELETE_PRODUCT_REQUEST = "DELETE FROM " + ProductContract.TABLE_PRODUCT_NAME + " WHERE "
            + ProductContract.ProductColumns.COLUMN_TITLE + " = ?";

    private static final String CREATE_TABLE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS " + ProductContract.TABLE_PRODUCT_NAME + " ("
            + ProductContract.ProductColumns.COLUMN_ID + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + ProductContract.ProductColumns.COLUMN_PRODUCT_ID + " INT NOT NULL UNIQUE, "
            + ProductContract.ProductColumns.COLUMN_TITLE + " CHAR(50) NOT NULL UNIQUE, "
            + ProductContract.ProductColumns.COLUMN_COST + " BIGINT NOT NULL)";

    private static final String TRUNCATE = "TRUNCATE " + ProductContract.TABLE_PRODUCT_NAME;

    @NotNull
    private final Presenter presenter;

    public ProductDBHelper(@NotNull final Presenter presenter) {
        this.presenter = presenter;

        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final Statement statement = connection.createStatement()) {
                    statement.executeUpdate(CREATE_TABLE_IF_NOT_EXIST);
                    statement.executeUpdate(TRUNCATE);
                    addRandomCountRecords();
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void add(final int productId, @NotNull final String productName, final long price) {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
                    preparedStatement.setInt(1, productId);
                    preparedStatement.setString(2, productName);
                    preparedStatement.setLong(3, price);
                    preparedStatement.executeUpdate();
                    presenter.showResult(null);
                } catch (final SQLException e) {
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void update(@NotNull final String productName, final long price) {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
                    preparedStatement.setLong(1, price);
                    preparedStatement.setString(2, productName);
                    preparedStatement.executeUpdate();
                    presenter.showResult(null);
                } catch (final SQLException e) {
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void selectProductsByPriceInRange(final long priceFrom, final long priceTo) {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_PRICE_IN_RANGE)) {
                    preparedStatement.setLong(1, priceFrom);
                    preparedStatement.setLong(2, priceTo);
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    List<Product> products = buildProductsRequest(resultSet);
                    if (products.isEmpty()) return;
                    presenter.showResult(products);
                } catch (final SQLException e) {
                    System.out.println(e.getMessage());
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void delete(@NotNull final String productName) {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_REQUEST)) {
                    preparedStatement.setString(1, productName);
                    preparedStatement.executeUpdate();
                    presenter.showResult(null);
                } catch (final SQLException e) {
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void selectPriceByName(@NotNull final String productName) {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_PRICE_BY_NAME)) {
                    preparedStatement.setString(1, productName);
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        final List<Product> products = Collections.singletonList(
                                new Product(
                                        0,
                                        "",
                                        resultSet.getLong(ProductContract.ProductColumns.COLUMN_COST)));
                        if (resultSet.next()) {
                            presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                            return;
                        }
                        presenter.showResult(products);
                    } else {
                        presenter.showErrorMsg(ErrorType.NOT_DATA);
                    }
                } catch (final SQLException e) {
                    System.out.println(e.getMessage());
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @Override
    public void selectAll() {
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                try (final Statement statement = connection.createStatement()) {
                    final ResultSet resultSet = statement.executeQuery(SELECT_ALL);
                    List<Product> products = buildProductsRequest(resultSet);
                    if (products.isEmpty()) return;
                    presenter.showResult(products);
                } catch (final SQLException e) {
                    System.out.println(e.getMessage());
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }

    @NotNull
    private List<Product> buildProductsRequest(@NotNull final ResultSet resultSet) throws SQLException {
        final List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(
                    new Product(
                            resultSet.getInt(ProductContract.ProductColumns.COLUMN_PRODUCT_ID),
                            resultSet.getString(ProductContract.ProductColumns.COLUMN_TITLE),
                            resultSet.getLong(ProductContract.ProductColumns.COLUMN_COST)));
        }

        if (products.isEmpty()) {
            presenter.showErrorMsg(ErrorType.NOT_DATA);
        }
        return products;
    }

    private void addRandomCountRecords() {
        final int count = new Random().nextInt(50) + 10;
        try {
            Class.forName(ProductContract.DRIVER_NAME);
            try (final Connection connection = DriverManager.getConnection(
                    ProductContract.CONNECTION_URL,
                    ProductContract.USER_NAME,
                    ProductContract.USER_PASSWORD)) {
                for (int i = 1; i <= count; i++) {
                    try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
                        preparedStatement.setInt(1, i);
                        preparedStatement.setString(2, String.format("item%d", i));
                        preparedStatement.setLong(3, i * 10);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                    }
                }
            } catch (final SQLException e) {
                presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
            }
        } catch (final ClassNotFoundException e) {
            presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
        }
    }
}
