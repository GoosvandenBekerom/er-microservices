package com.s63d.invoice.resource;

import com.s63d.generic.JsonResource;
import com.s63d.invoice.domain.Invoice;
import com.s63d.invoice.repository.InvoiceRepository;
import com.s63d.invoice.service.InvoiceService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("location")
public class InvoiceResource extends JsonResource<Invoice, Long, InvoiceRepository, InvoiceService> {

    @Inject
    public InvoiceResource(InvoiceService service) { super(service); }

}