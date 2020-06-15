package com.machine.DAO;

import com.machine.DAO.AbstractDAO;
import com.machine.models.cashbox.CashBox;
import com.machine.models.report.Report;

public interface CashBoxDAO<E> extends AbstractDAO<E> {
    Long open(E entity);

    CashBox getByNumber(Integer number);
}
