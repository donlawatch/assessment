package com.kbtg.bootcamp.posttest.dtos.responses;

import java.util.List;

public record PurchaseListResponse(List<String> tickets, int count, double price) {
}
