package semesters.fourth.lab4.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;
import semesters.fourth.lab4.presenters.Presenter;

public class DataProductHandler implements Handler {

    private static final String COMMAND_ADD = "/add";
    private static final String COMMAND_DELETE = "/delete";
    private static final String COMMAND_SHOW_ALL = "/show_all";
    private static final String COMMAND_PRICE = "/price";
    private static final String COMMAND_UPDATE_PRICE = "/change_price";
    private static final String COMMAND_FILTER_BY_PRICE = "/filter_by_price";
    private static final String COMMAND_EXIT = "/exit";

    @NotNull
    private final Presenter presenter;
    @Nullable
    private StateType state;

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
                        state = StateType.ADD;
                        presenter.addExecute(parameters[1], parameters[2], Integer.parseInt(parameters[3]));
                    } catch (final NumberFormatException nfe) {
                        state = StateType.ERROR;
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_FORMAT);
                    }
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_UPDATE_PRICE:
                if (parameters.length == 3) {
                    try {
                        state = StateType.UPDATE;
                        presenter.updatePriceExecute(parameters[1], Integer.parseInt(parameters[2]));
                    } catch (final NumberFormatException nfe) {
                        state = StateType.ERROR;
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_FORMAT);
                    }
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_FILTER_BY_PRICE:
                if (parameters.length == 3) {
                    try {
                        state = StateType.SHOW_PRODUCTS_BY_PRICE_IN_RANGE;
                        presenter.filterByPriceExecute(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
                    } catch (final NumberFormatException nfe) {
                        state = StateType.ERROR;
                        presenter.showErrorMsg(ErrorType.INCORRECT_PRICE_FORMAT);
                    }
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_DELETE:
                if (parameters.length == 2) {
                    state = StateType.DELETE;
                    presenter.deleteExecute(parameters[1]);
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_PRICE:
                if (parameters.length == 2) {
                    state = StateType.SHOW_PRICE;
                    presenter.priceExecute(parameters[1]);
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_SHOW_ALL:
                if (parameters.length == 1) {
                    state = StateType.SHOW_ALL;
                    presenter.showAllExecute();
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            case COMMAND_EXIT:
                if (parameters.length == 1) {
                    state = StateType.EXIT;
                    presenter.exitExecute();
                } else {
                    state = StateType.ERROR;
                    presenter.showErrorMsg(ErrorType.INCORRECT_COUNT_PARAMS);
                }
                break;
            default:
                state = StateType.ERROR;
                presenter.showErrorMsg(ErrorType.INCORRECT_COMMAND);
        }
    }

    @Nullable
    public StateType getState() {
        return state;
    }
}
