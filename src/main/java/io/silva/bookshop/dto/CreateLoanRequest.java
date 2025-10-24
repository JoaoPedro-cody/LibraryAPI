package io.silva.bookshop.dto;

import java.util.UUID;

public record CreateLoanRequest(UUID bookId, UUID userId) {
}
