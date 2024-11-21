package com.udistrital.library.models.requests.loans;

import java.util.Date;

public record RegisterLoanRequest(Short user, Short[] books, Date startDate, Date endDate) {}
