package com.s63d.invoice.repository;

import com.s63d.generic.Repository;
import com.s63d.invoice.domain.Invoice;

import javax.ejb.Stateless;

@Stateless
public class InvoiceRepository extends Repository<Invoice, Long> {
    public InvoiceRepository() {
        super(Invoice.class);
    }
}
