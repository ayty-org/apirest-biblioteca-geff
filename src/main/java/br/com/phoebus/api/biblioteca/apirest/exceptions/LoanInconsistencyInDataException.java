package br.com.phoebus.api.biblioteca.apirest.exceptions;

public class LoanInconsistencyInDataException extends RuntimeException {
    public LoanInconsistencyInDataException(){
        super("Inconsistency in loan data");
    }
}
