package ru.practicum.shareit_gateway.booking.dto;

import java.util.Optional;

/**
 * Состояния бронирования:
 * ALL - все,
 * CURRENT - текущие,
 * FUTURE - будущие
 * PAST - завершенные,
 * REJECTED - отклоненные,
 * WAITING - ожидают подтверждения
 */
public enum BookingState {
	ALL,
	CURRENT,
	FUTURE,
	PAST,
	REJECTED,
	WAITING;

	public static Optional<BookingState> from(String stringState) {
		for (BookingState state : values()) {
			if (state.name().equalsIgnoreCase(stringState)) {
				return Optional.of(state);
			}
		}
		return Optional.empty();
	}
}