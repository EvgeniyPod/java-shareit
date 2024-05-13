package ru.practicum.shareit_gateway.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit_gateway.request.dto.ItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/requests")
public class ItemRequestController {
    private final ItemRequestClient requestClient;
    public static final String HEADER_USER_ID = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> createItemRequest(@RequestHeader(HEADER_USER_ID) long userId,
                                                    @Valid @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Request for request for item {} from user {} creation", itemRequestDto.getDescription(), userId);
        return requestClient.createItemRequest(userId, itemRequestDto);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getItemRequestById(@RequestHeader(HEADER_USER_ID) long userId,
                                                     @Positive @PathVariable long requestId) {
        log.info("Request for get item's request {} from user {}", requestId, userId);
        return requestClient.getItemRequestById(userId, requestId);
    }

    @GetMapping
    public ResponseEntity<Object> getItemRequestsByOwnerId(@RequestHeader(HEADER_USER_ID) long userId) {
        log.info("Request for get  user's {} requests for items", userId);
        return requestClient.getItemRequestsByOwnerId(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getItemRequests(@RequestHeader(HEADER_USER_ID) long userId,
               @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
               @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Request for get {} requests for items from {} request", size, from);
        return requestClient.getItemRequests(userId, from, size);
    }
}