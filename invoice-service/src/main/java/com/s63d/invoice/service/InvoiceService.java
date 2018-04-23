package com.s63d.invoice.service;

import com.s63d.generic.DomainService;
import com.s63d.invoice.domain.Invoice;
import com.s63d.invoice.repository.InvoiceRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class InvoiceService extends DomainService<Invoice,Long, InvoiceRepository> {
    @Inject
    public InvoiceService(InvoiceRepository repo) { super(repo); }
    public InvoiceService() { super(); }

}
