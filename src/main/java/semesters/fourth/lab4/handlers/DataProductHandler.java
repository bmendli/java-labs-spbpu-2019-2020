package semesters.fourth.lab4.handlers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;

public class DataProductHandler implements Handler {

    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_ADD = "/add";
    public static final String COMMAND_DELETE = "/delete";
    public static final String COMMAND_SHOW_ALL = "/show_all";
    public static final String COMMAND_PRICE = "/price";
    public static final String COMMAND_UPDATE_PRICE = "/change_price";
    public static final String COMMAND_FILTER_BY_PRICE = "/filter_by_price";
    public static final String COMMAND_EXIT = "/exit";

    StateType state;
    @Nullable
    ErrorType errorType;

    @Nullable
    @Override
    public String[] handle(final String data) {
        if (data == null || data.isEmpty()) {
            errorType = ErrorType.INCORRECT_INPUT_DATA;
            return null;
        }
        return handleData(data.trim().split(" "));
    }

    private String[] handleData(@NotNull final String[] parameters) {
        switch (parameters[0]) {
            case COMMAND_ADD:
                if (parameters.length == 4) {
                    try {
                        final int productId = Integer.parseInt(parameters[1]);
                        final long price = Long.parseLong(parameters[3]);
                        if (productId <= 0 || price <= 0) {
                            errorType = ErrorType.INCORRECT_INPUT_DATA;
                        } else {
                            errorType = null;
                            state = StateType.ADD;
                            return parameters;
                        }
                    } catch (final NumberFormatException nfe) {
                        errorType = ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT;
                    }
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_UPDATE_PRICE:
                if (parameters.length == 3) {
                    try {
                        final long price = Long.parseLong(parameters[2]);
                        if (price <= 0) {
                            errorType = ErrorType.INCORRECT_INPUT_DATA;
                        } else {
                            errorType = null;
                            state = StateType.UPDATE;
                            return parameters;
                        }
                    } catch (final NumberFormatException nfe) {
                        errorType = ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT;
                    }
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_FILTER_BY_PRICE:
                if (parameters.length == 3) {
                    try {
                        long from = Long.parseLong(parameters[1]);
                        long to = Long.parseLong(parameters[2]);
                        if (from > to) {
                            String temp = parameters[1];
                            parameters[1] = parameters[2];
                            parameters[2] = temp;
                        }

                        if (to <= 0) {
                            errorType = ErrorType.INCORRECT_INPUT_DATA;
                        } else {
                            errorType = null;
                            state = StateType.SHOW_PRODUCTS_BY_PRICE_IN_RANGE;
                            return parameters;
                        }
                    } catch (final NumberFormatException nfe) {
                        errorType = ErrorType.INCORRECT_PRICE_OR_PRICE_FORMAT;
                    }
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_DELETE:
                if (parameters.length == 2) {
                    errorType = null;
                    state = StateType.DELETE;
                    return parameters;
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_PRICE:
                if (parameters.length == 2) {
                    errorType = null;
                    state = StateType.SHOW_PRICE;
                    return parameters;
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_SHOW_ALL:
                if (parameters.length == 1) {
                    errorType = null;
                    state = StateType.SHOW_ALL;
                    return parameters;
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_EXIT:
                if (parameters.length == 1) {
                    errorType = null;
                    state = StateType.EXIT;
                    return parameters;
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            case COMMAND_HELP:
                if (parameters.length == 1) {
                    errorType = null;
                    state = StateType.HELP;
                    return parameters;
                } else {
                    errorType = ErrorType.INCORRECT_COUNT_PARAMS;
                }
                return null;
            default:
                errorType = ErrorType.INCORRECT_COMMAND;
                return null;
        }
    }

    public StateType getState() {
        return state;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
