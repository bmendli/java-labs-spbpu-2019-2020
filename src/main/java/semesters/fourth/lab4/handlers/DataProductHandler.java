package semesters.fourth.lab4.handlers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.presenters.Presenter;

public class DataProductHandler implements Handler {

    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_ADD = "/add";
    public static final String COMMAND_DELETE = "/delete";
    public static final String COMMAND_SHOW_ALL = "/show_all";
    public static final String COMMAND_PRICE = "/price";
    public static final String COMMAND_UPDATE_PRICE = "/change_price";
    public static final String COMMAND_FILTER_BY_PRICE = "/filter_by_price";
    public static final String COMMAND_EXIT = "/exit";

    @NotNull
    private final Presenter presenter;

    public DataProductHandler(@NotNull final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(@Nullable final String data) {
        if (data == null || data.isBlank()) {
            presenter.showErrorMsg(ErrorType.INCORRECT_INPUT_DATA);
            return;
        }
        handleData(data.trim().split(" "));
    }

    private void handleData(@NotNull final String[] parameters) {
        switch (parameters[0]) {
            case COMMAND_ADD:
                if (parameters.length == 4) {
                    try {
                        final int productId = Integer.parseInt(parameters[1]);
                        final int price = Integer.parseInt(parameters[3]);
                        if (productId <= 0 || price <= 0) {
                            presenter.showErrorMsg(ErrorType.INCORRECT_INPUT_DATA);
                        } else {
                            presenter.addExecute(productId, parameters[2], price);
                        }
                    } catch (final NumberFormatException nfe) {
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT);
                    }
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_UPDATE_PRICE:
                if (parameters.length == 3) {
                    try {
                        final int price = Integer.parseInt(parameters[2]);
                        if (price <= 0) {
                            presenter.showErrorMsg(ErrorType.INCORRECT_INPUT_DATA);
                        } else {
                            presenter.updatePriceExecute(parameters[1], price);
                        }
                    } catch (final NumberFormatException nfe) {
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT);
                    }
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_FILTER_BY_PRICE:
                if (parameters.length == 3) {
                    try {
                        int from = Integer.parseInt(parameters[1]);
                        int to = Integer.parseInt(parameters[2]);
                        if (from > to) {
                            int temp = from;
                            from = to;
                            to = temp;
                        }

                        if (to <= 0) {
                            presenter.showErrorMsg(ErrorType.INCORRECT_INPUT_DATA);
                        } else {
                            presenter.filterByPriceExecute(from, to);
                        }
                    } catch (final NumberFormatException nfe) {
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT);
                    }
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_DELETE:
                if (parameters.length == 2) {
                    presenter.deleteExecute(parameters[1]);
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_PRICE:
                if (parameters.length == 2) {
                    presenter.priceExecute(parameters[1]);
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_SHOW_ALL:
                if (parameters.length == 1) {
                    presenter.showAllExecute();
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_EXIT:
                if (parameters.length == 1) {
                    presenter.exitExecute();
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_HELP:
                if (parameters.length == 1) {
                    presenter.helpExecute();
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            default:
                presenter.showErrorMsg(ErrorType.INCORRECT_COMMAND);
        }
    }
}
